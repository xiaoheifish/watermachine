package com.terabits.controller.xhr;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.terabits.config.Constants;
import com.terabits.config.WeixinGlobal;
import com.terabits.manager.PostCommandManager;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.ConsumeRefundBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.AuxcalPO;
import com.terabits.meta.po.CommandPO;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.meta.po.TotalPO;
import com.terabits.meta.po.WechatConsumePO;
import com.terabits.service.CommandService;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.CredentialService;
import com.terabits.service.HuaweiTokenService;
import com.terabits.service.RefundRecordService;
import com.terabits.service.StatisticService;
import com.terabits.service.TerminalService;
import com.terabits.service.WechatConsumeService;
import com.terabits.utils.FlowUtil;
import com.terabits.utils.GenerateOrderId;
import com.terabits.utils.HuaweiCommandUtil;
import com.terabits.utils.PayCommonUtil;
import com.terabits.utils.XMLUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/22.
 */
@Controller
public class WechatConsumeNotify {

    @Autowired
    private WechatConsumeService wechatConsumeService;
    @Autowired
    private ConsumeOrderService consumeOrderService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private RefundRecordService refundRecordService;
    @Autowired
    private CommandService commandService;
    @Autowired
    private HuaweiTokenService huaweiTokenService;

    private static Logger logger = LoggerFactory.getLogger(WechatConsumeNotify.class);
    @RequestMapping(value="/consumenotify", method= RequestMethod.POST)
    public String wechatConsumeCallback(HttpSession session, HttpServletRequest request) {

        try {
            String responseStr = parseWeixinCallback(request);
            Map<String, Object> map = XMLUtil.doXMLParse(responseStr);
            // 校验签名 防止数据泄漏导致出现“假通知”，造成资金损失
            if (!PayCommonUtil.checkIsSignValidFromResponseString(responseStr)) {
                logger.error("wechatConsume callback fails, signature might have been garbled");
                return PayCommonUtil.setXML("FAIL", "invalid sign");
            }
            if (WeixinGlobal.FAIL.equalsIgnoreCase(map.get("result_code")
                    .toString())) {
                logger.error("wechatConsume callbakc fails");
                return PayCommonUtil.setXML("FAIL", "weixin pay fail");
            }
            if (WeixinGlobal.SUCCESS.equalsIgnoreCase(map.get("result_code")
                    .toString())) {
                //获取应用服务器需要的数据进行持久化操作
                boolean isOk = false;
                String orderId = (String)map.get("out_trade_no");
                String tradeNo = (String)map.get("transaction_id");
                String money = (String)map.get("total_fee");

                //这里会返回用户的openid给后端，更新一下session中的，以防过期
                session.setAttribute("openid", (String)map.get("openid"));
                WechatConsumePO wechatConsumePO = wechatConsumeService.selectWechatConsumeByOrderId(orderId);

                //若数据库中tradeNo不为空，表明该笔微信直接消费订单已经过确认，则直接返回ok
                if(wechatConsumePO.getTradeNo()!=null){
                    return PayCommonUtil.setXML(WeixinGlobal.SUCCESS, "OK");
                }
                //比对金额是否相等，注意实际使用中money的单位是分，将微信发回来的值与数据库中的值比较
                double payment = wechatConsumePO.getMoney();
                double premoney = Double.parseDouble(money);

                if(payment * 100 == premoney){

                    int result = wechatConsumeService.updatePaymentStatus(tradeNo, orderId);
                    if (result == 1){
                        //准备openId, displayId和flow
                        String openId = (String)map.get("openid");
                        String displayId = orderId.substring(0,4);
                        //插入缓存
                        credentialService.createWechatConsume(displayId, openId);
                        double flow = FlowUtil.costToFlow(payment);
                        // 查询指令编号并更新
                        List<String> commandNo = credentialService.UpdateCommandNo();
                        String commandOne = commandNo.get(0);
                        String commandTwo = commandNo.get(1);
                        byte[] openbytes = HuaweiCommandUtil.generateOpenbytes(flow, commandOne, commandTwo);

                        CommunicationBO communicationBO = terminalService.getTerminalDeviceId(displayId);

                        String huaweiToken = huaweiTokenService.getToken().getHuaweiToken();
                        //String huaweiToken = huaweiTokenService.getLatestToken().getHuaweiToken();
                        Date now = new Date();
                        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time1 = dfs.format(now);
                        String commandResult = PlatformGlobal.command(openbytes, huaweiToken, communicationBO.getDeviceId());
                        now = new Date();
                        String time2 = dfs.format(now);
                        logger.error("platform global ok:::::"+ time1 +"-------"+ time2);

                        //判断是否下发成功，如果不成功则将本次消费金额退给用户，插入退款表，定时处理退款任务，同时将termianl的状态更新回可使用
                        //前端查询状态时，发现状态由12变为10了，则可提醒用户重新下单
                        if(commandResult == null){
                            //更新terminal状态
                            TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                            terminalUpdateBO.setDisplayId(displayId);
                            terminalUpdateBO.setState(Constants.OFF_STATE);
                            terminalService.updateTerminal(terminalUpdateBO);
                            //插入待退款数据
                            ConsumeRefundBO consumeRefundBO = new ConsumeRefundBO();
                            consumeRefundBO.setMoney(payment);
                            consumeRefundBO.setOpenId(openId);
                            consumeRefundBO.setTradeNo(tradeNo);
                            refundRecordService.insertConsumeRefund(consumeRefundBO);
                            //返回ok结果给微信
                            return PayCommonUtil.setXML(WeixinGlobal.SUCCESS, "OK");
                        }

                        //取出返回里面的commandId,在自己的平台上做出记录
                        Gson gson = new Gson();
                        Map<String, Object> commandMap = new HashMap<String, Object>();
                        commandMap = gson.fromJson(commandResult, commandMap.getClass());

                        //将这条命令插入command表
                        CommandPO commandPO = new CommandPO();
                        commandPO.setDeviceId(communicationBO.getDeviceId());
                        commandPO.setCommandIdOne((String)commandMap.get("commandId"));
                        commandPO.setFlow(flow);
                        commandPO.setState(Constants.BEGIN_STATE);
                        commandService.insertCommand(commandPO);

                        // 生成交易订单，并插入数据库
                        ConsumeOrderPO consumeOrderPO = new ConsumeOrderPO();
                        consumeOrderPO.setDisplayId(displayId);
                        consumeOrderPO.setOpenId(openId);
                        consumeOrderPO.setFlow(flow);
                        consumeOrderPO.setPayment(payment);
                        try {
                            consumeOrderService.insertOrder(consumeOrderPO);
                        } catch (Exception e) {
                            logger.error("consumeOrderService.insertOrder error in WechatConsumeNotify");
                        }

                        //根据id生成交易单号
                        int count = consumeOrderPO.getId();
                        String consumeOrder = GenerateOrderId.generateConsumeId(count);
                        try{
                            consumeOrderService.updateOrderNoById(consumeOrder, consumeOrderPO.getId());
                        }catch (Exception e){
                            logger.error("consumeOrderService.updateOrderNoById error in WechatConsumeNotify");
                        }

                        try{
                            wechatConsumeService.updateLastWechatConsume(consumeOrder, orderId);
                        }catch (Exception e){
                            logger.error("wechatConsumeService.updateLastWechatConsume error in WechatConsumeNotify, consumeOrder = " + consumeOrder + e.toString());
                        }

                        // 更新统计余额及流量
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        AuxcalPO auxcalPO = new AuxcalPO();
                        auxcalPO.setGmtCreate(sdf.format(new Date()));
                        auxcalPO.setPayment(payment);
                        auxcalPO.setFlow(flow);
                        statisticService.updateTodayAuxcal(auxcalPO);
                        // 更新历史总余额及流量
                        TotalPO totalPO = new TotalPO();
                        totalPO.setFlow(flow);
                        totalPO.setPayment(payment);
                        totalPO.setRemain(payment);
                        totalPO.setRecharge(0.0);
                        statisticService.updateTotalConsume(totalPO);


                        //返回ok结果给微信
                        return PayCommonUtil.setXML(WeixinGlobal.SUCCESS, "OK");
                    } else {
                        return PayCommonUtil
                                .setXML(WeixinGlobal.FAIL, "pay fail");
                    }
                }
                else{
                    return PayCommonUtil.setXML(WeixinGlobal.FAIL, "mismatch money");
                }
            }
        } catch (Exception e) {
            return PayCommonUtil.setXML(WeixinGlobal.FAIL,
                    "weixin pay server exception");
        }
        return PayCommonUtil.setXML(WeixinGlobal.FAIL, "weixin pay fail");
    }

    private String parseWeixinCallback(HttpServletRequest request) throws IOException {
        // 获取微信调用我们的notify_url的返回信息
        String result = "";
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            result = new String(outSteam.toByteArray(), Charsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(outSteam != null){
                    outSteam.close();
                    outSteam = null; // help GC
                }
                if(inStream != null){
                    inStream.close();
                    inStream = null;// help GC
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

