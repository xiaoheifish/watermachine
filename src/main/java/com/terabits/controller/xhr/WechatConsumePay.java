package com.terabits.controller.xhr;

import com.github.wxpay.sdk.WXPay;
import com.terabits.config.MyConfig;
import com.terabits.config.WeixinGlobal;
import com.terabits.manager.QueryPayStatus;
import com.terabits.meta.bo.JsapiConfigBO;
import com.terabits.meta.po.WechatConsumePO;
import com.terabits.service.TerminalService;
import com.terabits.service.UnifiedOrderService;
import com.terabits.service.WechatConsumeService;
import com.terabits.utils.GenerateOrderId;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/22.
 */
@Controller
public class WechatConsumePay{

    @Autowired
    private WechatConsumeService wechatConsumeService;
    @Autowired
    private UnifiedOrderService unifiedOrderService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private QueryPayStatus queryPayStatus;

    private static Logger logger = LoggerFactory.getLogger(WechatConsumePay.class);
    @RequestMapping(value="/wechatconsume", method= RequestMethod.POST)

    public void wechatConsumePay(HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{

        //获取发起该笔微信消费的用户id和充值金额
        String requestopenId = request.getParameter("openid");
        String money = request.getParameter("cost");
        String displayId = request.getParameter("displayid");

        JSONObject jsonObject = new JSONObject();

       /* //获取存在session中的openid，和前端发来的比对，不同则支付存在问题，返回
        String openId = (String)session.getAttribute("openid");
        if(!requestopenId.equals(openId)){
            jsonObject.put("result", "openid not match");
            response.getWriter().print(jsonObject);
            return;
        }*/

        //将设备状态更改为下单中，防止多人同时下单
        try{
            int result = terminalService.updateStatusWhenOrder(displayId);
            if(result == 0){
                jsonObject.put("result", "in order service");
                response.getWriter().print(jsonObject);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("updateStatusWhenOrder error in WechatConsumePayController, displayId = " + displayId);
            jsonObject.put("result", "in order service");
            response.getWriter().print(jsonObject);
            return;
        }

        //微信支付金额，以元为单位
        double totalmoney = Double.parseDouble(money);
        String strmoney = String.valueOf((int)(totalmoney * 100));
        System.out.println("strmoney::::"+strmoney);
        //将该条微信消费记录插入数据库
        WechatConsumePO wechatConsumePO = new WechatConsumePO();
        wechatConsumePO.setOpenId(requestopenId);
        wechatConsumePO.setMoney(totalmoney);
        try {
            wechatConsumeService.insertWechatConsume(wechatConsumePO);
        }catch (Exception e){
            logger.error("wechatConsumeService.insertWechatConsume error in WechatConsumePay" + wechatConsumePO);
        }

        //根据主键id，生成新的orderId，将displayId信息作为orderId的头四位
        int count = wechatConsumePO.getId();
        String orderId = displayId + GenerateOrderId.generateWechatConsumeId(count);
        try{
            wechatConsumeService.updateOrderIdById(orderId, wechatConsumePO.getId());
        }catch (Exception e){
            logger.error("wechatConsumeService.updateOrderIdById error in WechatConsumePay" + orderId);
        }

        String prepaidId = null;
        MyConfig myConfig = new MyConfig();
        WXPay wxpay = new WXPay(myConfig);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "智慧饮水");
        data.put("out_trade_no", orderId);
        data.put("fee_type", "CNY");
        data.put("total_fee", strmoney);
        data.put("spbill_create_ip", request.getRemoteAddr());
        data.put("notify_url", WeixinGlobal.WECHAT_CONSUME_URL);
        data.put("trade_type", "JSAPI");  // 此处指定为JSAPI支付
        data.put("openid", requestopenId);

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
        jsonConfig.put("result", "success");

        try {
            response.getWriter().print(jsonConfig);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        queryPayStatus.getStatus(orderId, displayId);
    }

}
