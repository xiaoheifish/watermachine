package com.terabits.controller;

import com.terabits.meta.po.InvitationPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.InvitationService;
import com.terabits.service.SmsService;
import com.terabits.service.UserService;
import com.terabits.service.impl.SmsServiceImpl;
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
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/19.
 */
@Controller
public class RegisterController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationService invitationService;
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @RequestMapping(value="/sendmessage",method= RequestMethod.GET)
    public void sendmessage(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String tel = request.getParameter("tel");
        try {
            UserPO userPO = userService.userExist(tel);
            if(userPO != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("auth", "existence");
                response.getWriter().print(jsonObject);
                return;
            }
        }catch (Exception e){
            logger.error("userService.userExist error in RegisterController");
        }
        String code = smsService.sendMessage(tel, "zh_CN");
        String auth = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth",auth);
        session.setAttribute("auth", auth);
        session.setAttribute("tel", tel);
        session.setAttribute("code",code);
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value="/testcode",method=RequestMethod.POST)
    public void testcode(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String tempAuth = (String)session.getAttribute("auth");
        String tempId = (String)session.getAttribute("tel");
        String tempCode = (String)session.getAttribute("code");
        JSONObject jsonObject = new JSONObject();
        String auth = request.getParameter("auth");
        String id = request.getParameter("tel");
        String code = request.getParameter("code");

        if(!(tempAuth.equals(auth))){
            jsonObject.put("testpass","no");
            response.getWriter().print(jsonObject);
            return;
        }
        if(!(tempId.equals(id))){
            jsonObject.put("testpass","no");
            response.getWriter().print(jsonObject);
            return;
        }

        if(tempCode.equals(code)){
        	if(request.getParameter("openid")!=null){
        	    String openId = request.getParameter("openid");
        	    UserPO userPO = userService.selectUser(openId);
        	    if(userPO != null){
                    try{
                        userService.updatePhone(id, openId);
                        userService.updateRemain(0.0,5.0, openId);
                        session.setAttribute("openid", openId);
                        if(request.getParameter("phone") != null){
                            invitationService.insertInvitation(request.getParameter("phone"), request.getParameter("tel"));
                            UserPO inviterPO = userService.userExist(request.getParameter("phone"));
                            userService.updateRemain(inviterPO.getRecharge(), inviterPO.getPresent() + 5.0, inviterPO.getOpenId());
                        }
                        jsonObject.put("testpass","yes");
                        response.getWriter().print(jsonObject);
                        return;
                    }catch (Exception e){
                        logger.error("userService.update phone and remain error!");
                        jsonObject.put("testpass","no");
                        response.getWriter().print(jsonObject);
                        return;
                    }

                }else{
                    UserPO inviteeUserPO = new UserPO();
                    inviteeUserPO.setOpenId(request.getParameter("openid"));
                    inviteeUserPO.setPhone(request.getParameter("tel"));
                    inviteeUserPO.setRecharge(0.0);
                    inviteeUserPO.setPresent(5.0);
                    try{
                        userService.insertUser(inviteeUserPO);
                        invitationService.insertInvitation(request.getParameter("phone"), request.getParameter("tel"));
                        UserPO inviterPO = userService.userExist(request.getParameter("phone"));
                        userService.updateRemain(inviterPO.getRecharge(), inviterPO.getPresent() + 5.0, inviterPO.getOpenId());
                        session.setAttribute("openid", openId);
                        jsonObject.put("testpass","yes");
                        response.getWriter().print(jsonObject);
                        return;
                    }catch(Exception e){
                        jsonObject.put("testpass","no");
                        response.getWriter().print(jsonObject);
                        return;
                    }

                }

        	}else{
                jsonObject.put("testpass","no");
                response.getWriter().print(jsonObject);
                return;
            }

        }
    }

}
