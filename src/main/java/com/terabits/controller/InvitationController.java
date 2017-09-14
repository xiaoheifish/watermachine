package com.terabits.controller;

import com.terabits.config.WeixinGlobal;
import com.terabits.meta.bo.WeixinUserBO;
import com.terabits.service.UserService;
import com.terabits.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
public class InvitationController {
    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory
            .getLogger(InvitationController.class);

    @RequestMapping(value = "/showinvitation", method = RequestMethod.GET)
    public String showInvitation(HttpServletRequest request, HttpSession session, ModelMap model){
        String openId = request.getParameter("openid");
        String platformOpenId = (String)session.getAttribute("openid");
        WeixinUserBO weixinUserBO = null;
        if(openId.equals(platformOpenId)){
            try{
                weixinUserBO = userService.userRegistered(openId);
            }catch (Exception e){
                logger.error("userService.userRegistered error in invitationController");
            }
            model.addAttribute("phone", weixinUserBO.getPhone());
            return "main/invitation.jsp";
        }
        return null;
    }


    @RequestMapping(value = "/invitation/{phone}", method = RequestMethod.GET)
    public String showRegister(@PathVariable("phone") String phone, ModelMap model){
        model.addAttribute("phone", phone);
        return "main/signup.jsp";
    }
    //后面还得继续更新。。。晚上跟师妹讨论一下
    @RequestMapping(value = "/userexist", method = RequestMethod.GET)
    public void userExist(HttpServletRequest request, HttpSession session) throws Exception{
        String code = request.getParameter("code");
        JSONObject jsonObject = WeixinUtil.getOpenid(code, WeixinGlobal.APP_ID, WeixinGlobal.APP_SECRET);
        String openId = jsonObject.getString("openid");
        System.out.println("openid:" + openId);
        session.setAttribute("openid",openId);

    }
}
