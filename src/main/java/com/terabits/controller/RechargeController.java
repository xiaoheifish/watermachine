package com.terabits.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.terabits.config.MyConfig;
import com.terabits.config.MyWXPay;
import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.meta.po.RefundRecordPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.CredentialService;
import com.terabits.service.RechargeOrderService;
import com.terabits.service.RefundRecordService;
import com.terabits.service.UserService;
import com.terabits.utils.MD5Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/19.
 * 充值记录
 */
@Controller
public class RechargeController {
    @Autowired
    private RechargeOrderService rechargeOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private RefundRecordService refundRecordService;
    @Autowired
    private CredentialService credentialService;

    private static Logger logger = LoggerFactory.getLogger(RechargeController.class);

    //显示我的钱包页,同时显示余额
    @RequestMapping(value = "/wallet", method = RequestMethod.GET)
    public String showRecharge(HttpServletRequest request, ModelMap model, HttpSession session){
        if(session.getAttribute("openid") == null){
            return "main/timeout.jsp";
        }
        String openId = request.getParameter("openid");
        try{
            UserPO userPO = userService.selectUser(openId);
            double balance = userPO.getRecharge() + userPO.getPresent();
            model.addAttribute("balance", String.valueOf(balance));
            model.addAttribute("recharge", String.valueOf(userPO.getRecharge()));
            model.addAttribute("present", String.valueOf(userPO.getPresent()));
        }catch (Exception e){
            logger.error("consumeOrderService.selectConsumptionByDisplayId error in recordcontroller");
        }
        return "main/wallet.jsp";
    }

    //点击充值按钮跳转至此页面
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String recharge(){return "main/recharge.jsp";}

    //显示充值记录页面
    @RequestMapping(value = "/rechargerec", method = RequestMethod.GET)
    public String rechargerec(){return "main/rechargerec.jsp";}

    //获取充值记录
    @RequestMapping(value = "/menu/rechargerecord", method = RequestMethod.POST)
    public void getRechargeRecord(HttpServletRequest request, HttpServletResponse response){
        String openId = request.getParameter("openid");
        List<RechargeOrderPO> rechargeOrderPOArrayList = new ArrayList<RechargeOrderPO>();
        try{
            rechargeOrderPOArrayList = rechargeOrderService.selectPaymentByOpenId(openId);
            JSONArray jsonObject = JSONArray.fromObject(rechargeOrderPOArrayList);
            response.getWriter().print(jsonObject);
        }catch (Exception e){
            logger.error("rechargeOrderService.selectPaymentByOpenId error in rechargeController");
        }

    }

    //提交退款请求
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public void refund(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String openId = request.getParameter("openid");
        String strMoney = request.getParameter("money");
        String auth = request.getParameter("auth");
        if(auth.equals(MD5Util.MD5Encode(openId + "D2FFD4FAEF6778E26813CB08FE3CB3C5","UTF-8"))) {
            UserPO userPO = userService.selectUser(openId);
            //若余额为0，则不允许退款
            if (userPO.getRecharge() == 0.0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", "notenough");
                response.getWriter().print(jsonObject);
                return;
            }
            //半小时内只允许一次退款操作
            String time = credentialService.getRefundUserTime(openId);
            if(time != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", "frequent");
                response.getWriter().print(jsonObject);
                return;
            }
            credentialService.createRefundUser(openId);
            System.out.println(strMoney);
            double money = Double.parseDouble(strMoney);
            int requestMoney = (int)(money * 100);
            int id = refundRecordService.insertRefund(openId, money);
            String refundNo = null;
            try {
                refundNo = refundRecordService.updateRefundNoById(id);
            } catch (Exception e) {
                logger.error("updateRefundNo error in RechargeController" + id);
            }

            MyConfig config = new MyConfig();
            MyWXPay wxpay = new MyWXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("partner_trade_no", refundNo);
            data.put("openid", openId);
            data.put("check_name", "NO_CHECK");
            data.put("amount", String.valueOf(requestMoney));
            data.put("desc", "退款");
            data.put("spbill_create_ip", "119.23.210.52");
            try {
                Map<String, String> resp = wxpay.personalPay(data);
                if (resp.get("payment_no") != null) {
                    //退款成功，更新退款订单状态，更新用户余额
                    refundRecordService.updateRefundStatus(resp.get("payment_no"), refundNo);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("result", "success");
                    response.getWriter().print(jsonObject);
                    userService.updateRemain(0.0, 0.0, openId);
                }
                if (resp.get("err_code") != null) {
                    //用户为非实名制的，提醒用户绑卡
                    if (resp.get("err_code").equals("V2_ACCOUNT_SIMPLE_BAN")) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("result", "simpleban");
                        response.getWriter().print(jsonObject);
                    }
                    if(resp.get("err_code").equals("NOTENOUGH")){
                    	System.out.println("++++++++++++++++NOTENOUGH");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //显示退款记录页面
    @RequestMapping(value = "/refundrec", method = RequestMethod.GET)
    public String refundrec(){return "main/refundrec.jsp";}

    //查询退款记录
    @RequestMapping(value = "/menu/refundrecord", method = RequestMethod.POST)
    public void getRefundRecord(HttpServletRequest request, HttpServletResponse response){
        String openId = request.getParameter("openid");
        List<RefundRecordPO> refundRecordPOS = new ArrayList<RefundRecordPO>();
        try{
            refundRecordPOS = refundRecordService.selectRefundByOpenId(openId);
            JSONArray jsonArray = JSONArray.fromObject(refundRecordPOS);
            response.getWriter().print(jsonArray);
        }catch (Exception e){
            logger.error("refundRecordService.selectRefundByOpenId error in rechargeController");
        }
    }

}
