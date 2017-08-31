package com.terabits.controller;

import com.google.gson.Gson;
import com.terabits.config.Constants;
import com.terabits.config.HuaweiPlatformGlobal;
import com.terabits.manager.PostCommandManager;
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
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2017/8/28.
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
    private PostCommandManager postCommandManager;
    @Autowired
    private CommandService commandService;
    @Autowired
    private HuaweiTokenService huaweiTokenService;

    private static Logger logger = LoggerFactory
            .getLogger(ConsumeController.class);

    /**
     * 用户消费订单，需要生成消费订单，state为未收到回复，即23；` 查询当前指令编号，然后下发开启命令
     * 查询订单状态是否被更新，若被更新，表明收到硬件回复，开启成功 则更新设备状态，插入终端变化的操作，用户余额，以及统计余额
     */
    @RequestMapping(value = "/consumeorder", method = RequestMethod.POST)
    public void consume(HttpServletRequest request,
                        HttpServletResponse response, HttpSession session) throws Exception {

        String requestOpenId = request.getParameter("openid");
        String openId = (String) session.getAttribute("openid");
        openId = requestOpenId;
        String displayId = request.getParameter("displayid");
        String cost = request.getParameter("cost");
        double actualCost = Double.parseDouble(cost);
        double flow = FlowUtil.costToFlow(actualCost);
        if (!requestOpenId.equals(openId)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "openid not match");
            response.getWriter().print(jsonObject);
            return;
        }
        try{
            int result = terminalService.updateStatusWhenOrder(displayId);
            if(result == 0){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", "in order service");
                response.getWriter().print(jsonObject);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("updateStatusWhenOrder error in consumeorController" );
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "in order service");
            response.getWriter().print(jsonObject);
            return;
        }

        // 查询指令编号并更新
        String commandOne = credentialService.getCommandNo("commandOne");
        String commandTwo = credentialService.getCommandNo("commandTwo");
        CommandNoModel commandNoModel1 = new CommandNoModel();
        CommandNoModel commandNoModel2 = new CommandNoModel();
        commandNoModel1.setCommandId("commandOne");
        commandNoModel2.setCommandId("commandTwo");
        int tempCommandOne = Integer.parseInt(commandOne);
        int tempCommandTwo = Integer.parseInt(commandTwo) + 1;
        //若编号2到127了，则回退到1，同时编号1加1；若编号1到127了，则编号1和2一同回退到1
        if(tempCommandTwo == 127) {
            tempCommandTwo = 1;
            tempCommandOne += 1;
            if (tempCommandOne == 127) {
                tempCommandOne = 1;
                tempCommandTwo = 1;
            }
            commandNoModel1.setNumber(String.valueOf(tempCommandOne));
            credentialService.createCommand(commandNoModel1);
        }
        commandNoModel2.setNumber(String.valueOf(tempCommandTwo));
        credentialService.createCommand(commandNoModel2);

        // 下发指令
        int cmdOne = Integer.parseInt(commandOne);
        int cmdTwo = Integer.parseInt(commandTwo);
        CommunicationBO communicationBO = terminalService.getTerminalDeviceId(displayId);
        byte[] openbytes = new byte[6];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = FlowUtil.flowToCommand(flow);
        openbytes[3] = (byte) cmdOne;
        openbytes[4] = (byte) cmdTwo;
        openbytes[5] = Constants.SEND_COMMAND_END;
        //String result = postCommandManager.command(openbytes, communicationBO.getDeviceId());
        String huaweiToken = huaweiTokenService.getLatestToken().getHuaweiToken();
        Date now = new Date();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = dfs.format(now);
        String result = PlatformGlobal.command(openbytes, huaweiToken, communicationBO.getDeviceId());
        now = new Date();
        String time2 = dfs.format(now);
        logger.error("platform global ok:::::"+ time1 +"-------"+ time2);
        //判断是否下发成功，如果不成功则直接返回给前端，提醒用户重试，同时将termianl的状态更新回可使用
        if(result == null){
            TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
            terminalUpdateBO.setDisplayId(displayId);
            terminalUpdateBO.setState(Constants.OFF_STATE);
            terminalService.updateTerminal(terminalUpdateBO);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "order later");
            response.getWriter().print(jsonObject);
            return;
        }

        //若下发成功，则取出返回里面的commandId,在自己的平台上做出记录，
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(result, map.getClass());

        //将这条命令插入command表
        CommandPO commandPO = new CommandPO();
        commandPO.setDeviceId(communicationBO.getDeviceId());
        commandPO.setCommandIdOne((String)map.get("commandId"));
        commandPO.setFlow(flow);
        commandPO.setState(Constants.BEGIN_STATE);
        commandService.insertCommand(commandPO);

        // 生成交易订单号
        int count = consumeOrderService.selectCountByTime(TimeSpanUtil
                .generateTimeSpan());
        String consumeOrder = GenerateOrderId.generateConsumeId(count, openId);
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

      
        //更新redis缓存中该设备的开始时间
        credentialService.updateDeviceTime(communicationBO.getDeviceId());

        Boolean flag = false;
        for (int i = 0; i < 21; i++) {
            // 之后加一个根据消费订单编号查询的方法
        /*    ConsumeOrderPO consumeOrderPO1 = consumeOrderService
                    .selectLastConsumption(displayId);*/
            ConsumeOrderPO consumeOrderPO1 = new ConsumeOrderPO();
            consumeOrderPO1.setState(Constants.HAVE_RESPONSE);
            if (i == 20) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", "error");
                response.getWriter().print(jsonObject);
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setState(Constants.OFF_STATE);
                terminalUpdateBO.setDisplayId(displayId);
                terminalService.updateTerminal(terminalUpdateBO);
                return;
            }
            if (consumeOrderPO1.getState() == Constants.HAVE_RESPONSE) {
                if (flag == false) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("result", "success");
                    response.getWriter().print(jsonObject);
                    flag = true;
                    // 更新用户余额
                    UserPO userPO = userService.selectUser(openId);
                    userService.updateRemain(userPO.getRemain() - actualCost,
                            openId);
                    // 更新统计余额及流量
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    AuxcalPO auxcalPO = new AuxcalPO();
                    auxcalPO.setGmtCreate(sdf.format(new Date()));
                    auxcalPO.setPayment(actualCost);
                    auxcalPO.setFlow(flow);
                    statisticService.updateTodayAuxcal(auxcalPO);
                    // 更新历史总余额及流量
                    TotalPO totalPO = new TotalPO();
                    totalPO.setFlow(flow);
                    totalPO.setPayment(actualCost);
                    totalPO.setRemain(actualCost);
                    totalPO.setRecharge(0.0);
                    statisticService.updateTotalConsume(totalPO);
                    return;
                }
            }
            if ((consumeOrderPO1.getState() == Constants.NO_RESPONSE)
                    && (i % 12 == 0)&&(i != 0)) {
                // 每隔12秒，重新下发开启插座命令给终端
                result = postCommandManager.command(openbytes, communicationBO.getDeviceId());
                map = gson.fromJson(result, map.getClass());
                //记录第二次下发的commandId，方便去华为平台上做查询比对
                commandService.updateCommandId((String)map.get("commandId"), communicationBO.getDeviceId());
                logger.error("result:::"+ result);
            } else {
                // 隔一秒取一次数据库中记录
                Thread.sleep(1000L);
            }
        }
    }
}
