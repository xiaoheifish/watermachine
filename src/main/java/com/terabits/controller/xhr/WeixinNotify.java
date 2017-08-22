package com.terabits.controller.xhr;

import com.terabits.config.WeixinGlobal;
import com.terabits.meta.po.AuxcalPO;
import com.terabits.meta.po.TotalPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.*;
import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.utils.PayCommonUtil;
import com.terabits.utils.XMLUtil;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private UserService userService;
    @Autowired
    private StatisticService statisticService;

    private static Logger logger = LoggerFactory.getLogger(WeixinNotify.class);
    @RequestMapping(value="/notify", method= RequestMethod.POST)
    public String callback(HttpSession session, HttpServletRequest request) {

        try {
            String responseStr = parseWeixinCallback(request);
            Map<String, Object> map = XMLUtil.doXMLParse(responseStr);
            // 校验签名 防止数据泄漏导致出现“假通知”，造成资金损失
            if (!PayCommonUtil.checkIsSignValidFromResponseString(responseStr)) {
                logger.error("微信回调失败,签名可能被篡改");
                return PayCommonUtil.setXML("FAIL", "invalid sign");
            }
            if (WeixinGlobal.FAIL.equalsIgnoreCase(map.get("result_code")
                    .toString())) {
                logger.error("微信回调失败");
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDay = sdf.format(new Date());
                    int result = orderService.updatePaymentStatus(tradeNo, orderId);
                    if (result == 1){
                        UserPO userPO = userService.selectUser((String)map.get("openid"));
                        double remain = userPO.getRemain();
                        remain = remain + payment;
                        try{
                            //更新用户余额
                            userService.updateRemain(remain, (String)map.get("openid"));
                        }catch (Exception e){
                            logger.error("更新用户余额失败，订单号="+orderId);
                        }
                        try{
                            //当日统计数据，更新总充值
                            AuxcalPO auxcalPO = new AuxcalPO();
                            auxcalPO.setRecharge(payment);
                            auxcalPO.setGmtCreate(currentDay);
                            statisticService.updateTodayAuxcal(auxcalPO);
                            //历史统计数据，更新总充值和总余额
                            TotalPO totalPO = new TotalPO();
                            totalPO.setRecharge(payment);
                            totalPO.setRemain(payment);
                            statisticService.updateTotalRecharge(totalPO);
                        }catch (Exception e){
                            logger.error("更新统计数据失败，订单号="+orderId);
                        }
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
