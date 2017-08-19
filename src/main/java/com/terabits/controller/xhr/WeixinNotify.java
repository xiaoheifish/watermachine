package com.terabits.controller.xhr;


import com.terabits.config.Constants;
import com.terabits.config.WeixinGlobal;
import com.terabits.service.CredentialService;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.model.TerminalModel;
import com.terabits.meta.po.OperationPO;
import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.service.OperationService;
import com.terabits.service.RechargeOrderService;
import com.terabits.service.TerminalService;
import com.terabits.utils.PayCommonUtil;
import com.terabits.utils.XMLUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */
@Controller
@Transactional
public class WeixinNotify{

    @Autowired
    private RechargeOrderService orderService;
    @Autowired
    private CredentialService CredentialService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private OperationService operationService;
    @RequestMapping(value="/notify", method= RequestMethod.POST)
    public String callback(HttpSession session, HttpServletRequest request) {

        System.out.println("又来确认了！！！！");
        try {
            String responseStr = parseWeixinCallback(request);
            Map<String, Object> map = XMLUtil.doXMLParse(responseStr);
            // 校验签名 防止数据泄漏导致出现“假通知”，造成资金损失
            if (!PayCommonUtil.checkIsSignValidFromResponseString(responseStr)) {
                //logger.error("微信回调失败,签名可能被篡改");
                return PayCommonUtil.setXML("FAIL", "invalid sign");
            }
            if (WeixinGlobal.FAIL.equalsIgnoreCase(map.get("result_code")
                    .toString())) {
                //logger.error("微信回调失败");
                return PayCommonUtil.setXML("FAIL", "weixin pay fail");
            }
            if (WeixinGlobal.SUCCESS.equalsIgnoreCase(map.get("result_code")
                    .toString())) {
                //获取应用服务器需要的数据进行持久化操作
                boolean isOk = false;
                String orderId = (String)map.get("out_trade_no");
                String tradeNo = (String)map.get("transaction_id");
                String money = (String)map.get("total_fee");
                RechargeOrderPO orderPO = orderService.selectPaymentByOrderId(orderId);
                //若数据库中tradeNo不为空，表明该笔订单已经过确认，则直接返回ok
                if(orderPO.getTradeNo()!=null){
                    return PayCommonUtil.setXML(WeixinGlobal.SUCCESS, "OK");
                }
                //比对金额是否相等，注意实际使用中money的单位是分
                double payment = orderPO.getPayment();
                int prepayment = (int)payment;
                int premoney = Integer.parseInt(money);
                if(prepayment == premoney){
                    int result = orderService.updatePaymentStatus(tradeNo, orderId);
                    System.out.println("result:"+result);
                    if (result == 1){

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
