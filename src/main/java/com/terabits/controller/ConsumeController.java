package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.model.CommandNoModel;
import com.terabits.meta.po.*;
import com.terabits.service.*;
import com.terabits.utils.FlowUtil;
import com.terabits.utils.GenerateOrderId;
import com.terabits.utils.TimeSpanUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/20.
 */
@Controller
public class ConsumeController {
    @Autowired
    private ConsumeOrderService consumeOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private HuaweiPostCommandService huaweiPostCommandService;

    private static Logger logger = LoggerFactory.getLogger(ConsumeController.class);


    /**
     * 用户消费订单，需要生成消费订单，state为未收到回复，即23；`
     * 查询当前指令编号，然后下发开启命令
     * 查询订单状态是否被更新，若被更新，表明收到硬件回复，开启成功
     * 则更新设备状态，插入终端变化的操作，用户余额，以及统计余额
     */
    @RequestMapping(value = "/consume/order", method = RequestMethod.POST)
    public void consume(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String requestOpenId = request.getParameter("openid");
        String openId = (String) session.getAttribute("openid");
        String displayId = request.getParameter("displayid");
        String cost = request.getParameter("cost");
        double actualCost = Double.parseDouble(cost);
        double flow = FlowUtil.costToFlow(actualCost);
        if (!requestOpenId.equals(openId)) {
            response.getWriter().print("error");
        }
        //生成交易订单号
        int count = consumeOrderService.selectCountByTime(TimeSpanUtil.generateTimeSpan());
        String consumeOrder = GenerateOrderId.generateConsumeId(count);
        ConsumeOrderPO consumeOrderPO = new ConsumeOrderPO();
        consumeOrderPO.setDisplayId(displayId);
        consumeOrderPO.setOpenId(openId);
        consumeOrderPO.setFlow(flow);
        consumeOrderPO.setOrderNo(consumeOrder);
        consumeOrderPO.setPayment(actualCost);
        try {
            consumeOrderService.insertOrder(consumeOrderPO);
        } catch (Exception e) {
            logger.error("consumeOrderService.insertOrder error in 生成消费订单");
        }
        //查询指令编号并更新
        String commandOne = credentialService.getCommandNo("commandOne");
        String commandTwo = credentialService.getCommandNo("commandTwo");
        CommandNoModel commandNoModel = new CommandNoModel();
        commandNoModel.setCommandId("commandTwo");
        int tempCommandTwo = Integer.parseInt(commandTwo) + 1;
        commandNoModel.setNumber(String.valueOf(tempCommandTwo));
        credentialService.createCommand(commandNoModel);
        //下发指令
        int cmdOne = Integer.parseInt(commandOne);
        int cmdTwo = Integer.parseInt(commandTwo);
        CommunicationBO communicationBO = terminalService.getTerminalDeviceId(displayId);
        //下发开启插座命令给终端
        byte[] openbytes = new byte[6];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = FlowUtil.flowToCommand(flow);
        openbytes[3] = (byte) cmdOne;
        openbytes[4] = (byte) cmdTwo;
        openbytes[5] = Constants.SEND_COMMAND_END;
        Date now = new Date();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = dfs.format(now);
        huaweiPostCommandService.command(openbytes, communicationBO.getDeviceId());
        now = new Date();
        String time2 = dfs.format(now);
        //response.getWriter().print("power on ok: " + time1 + " " + time2);
        //此处为调试方便先直接更新订单状态
        consumeOrderService.updateStateById(consumeOrderPO.getOrderNo());
        for (int i = 0; i < 1; i++) {
            //之后加一个根据消费订单编号查询的方法
            ConsumeOrderPO consumeOrderPO1 = consumeOrderService.selectLastConsumption(displayId);
            if (consumeOrderPO1.getState() == Constants.NO_RESPONSE) {
                //下发开启插座命令给终端
                time1 = dfs.format(now);
                PlatformGlobal.command(openbytes, communicationBO.getDeviceId());
                now = new Date();
                time2 = dfs.format(now);
                response.getWriter().print("power on ok: " + time1 + " " + time2);
            } else{
                //更新设备表中的设备状态
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setState(Constants.ON_STATE);
                terminalUpdateBO.setDisplayId(displayId);
                terminalService.updateTerminal(terminalUpdateBO);
                //在数据库中添加此次操作记录,operationPO里可以记录下指令编号，用于调试！！
                OperationPO operationPO = new OperationPO();
                operationPO.setStatus(Constants.OFF_TO_ON);
                operationPO.setImei(communicationBO.getImei());
                operationService.insertOperation(operationPO);
                //更新用户余额
                UserPO userPO = userService.selectUser(openId);
                userService.updateRemain(userPO.getRemain() - actualCost, openId);
                //更新统计余额及流量
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                AuxcalPO auxcalPO = statisticService.selectTodayAuxcal(sdf.format(new Date()));
                auxcalPO.setPayment(auxcalPO.getPayment() + actualCost);
                auxcalPO.setFlow(auxcalPO.getFlow() + flow);
                statisticService.updateTodayAuxcal(auxcalPO);
                //更新历史总余额及流量
                TotalPO totalPO = statisticService.selectTotal();
                totalPO.setFlow(totalPO.getFlow() + flow);
                totalPO.setPayment(totalPO.getPayment() + actualCost);
                totalPO.setRemain(totalPO.getRemain() - actualCost);
                statisticService.updateTotal(totalPO);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", "success");
                response.getWriter().print(jsonObject);
            }
        }
    }
}
