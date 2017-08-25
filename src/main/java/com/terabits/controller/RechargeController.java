package com.terabits.controller;

import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.RechargeOrderService;
import com.terabits.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

    private static Logger logger = LoggerFactory.getLogger(RechargeController.class);

    //显示我的钱包页,同时显示余额
    @RequestMapping(value = "/wallet", method = RequestMethod.GET)
    public String showRecharge(HttpServletRequest request, ModelMap model){
        String openId = request.getParameter("openid");
        try{
            UserPO userPO = userService.selectUser(openId);
            double balance = userPO.getRemain();
            model.addAttribute("balance", String.valueOf(balance));
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

    //获取充值
    @RequestMapping(value = "/menu/recharge", method = RequestMethod.POST)
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
}
