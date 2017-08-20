package com.terabits.controller.xhr;

import com.github.wxpay.sdk.WXPay;
import com.terabits.config.MyConfig;
import com.terabits.config.WeixinGlobal;
import com.terabits.controller.MailController;
import com.terabits.meta.bo.JsapiConfigBO;
import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.service.RechargeOrderService;
import com.terabits.service.UnifiedOrderService;
import com.terabits.utils.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */
@Controller
public class WeixinPay {
    @Autowired
    private RechargeOrderService orderService;
    @Autowired
    private UnifiedOrderService unifiedOrderService;

    private static Logger logger = LoggerFactory.getLogger(WeixinPay.class);
    @RequestMapping(value="/wxpay",method= RequestMethod.POST)

    public void wxPay(HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
        //获取发起该笔充值的用户id和充值金额
        String requestopenId = request.getParameter("id");
        String money = request.getParameter("money");

        //微信支付金额，以分为单位
        int totalmoney = Integer.parseInt(money);

        //查询当日交易量，生成新的orderId
        int count = orderService.selectCountByTime(TimeSpanUtil.generateTimeSpan());
        String orderId = GenerateOrderId.generateOrderId(count);

        //获取存在session中的openid，和前端发来的比对，不同则支付存在问题，返回
        String openId = (String)session.getAttribute("openid");
        if(!requestopenId.equals(openId)){
            response.getWriter().print("error");
            return;
        }

        String prepaidId = null;
        MyConfig myConfig = new MyConfig();
        WXPay wxpay = new WXPay(myConfig);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "智慧饮水");
        data.put("out_trade_no", orderId);
        data.put("fee_type", "CNY");
        data.put("total_fee", money);
        data.put("spbill_create_ip", request.getRemoteAddr());
        data.put("notify_url", WeixinGlobal.NOTIFY_URL);
        data.put("trade_type", "JSAPI");  // 此处指定为JSAPI支付
        data.put("openid",openId);
        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println("resp:::"+resp);
            prepaidId = (String)resp.get("prepay_id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //生成前端可调用的config参数
        JsapiConfigBO config = unifiedOrderService.createPayConfig(prepaidId);
        JSONObject jsonConfig = JSONObject.fromObject(config);

        //将该条交易记录插入数据库
        RechargeOrderPO orderPO = new RechargeOrderPO();
        orderPO.setPayment(totalmoney);
        orderPO.setOrderId(orderId);
        orderPO.setOpenId(openId);
        try {
            orderService.insertOrder(orderPO);
        }catch (Exception e){
            logger.error("orderService.insertOrder error in weixinPay" + orderPO);
        }
        try {
            response.getWriter().print(jsonConfig);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
