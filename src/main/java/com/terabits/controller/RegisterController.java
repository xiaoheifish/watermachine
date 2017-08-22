package com.terabits.controller;

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

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @RequestMapping(value="/sendmessage",method= RequestMethod.GET)
    public void sendmessage(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String tel = request.getParameter("tel");
        System.out.println(tel);
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
        System.out.println(tempAuth +auth+ tempId+id+tempCode+code);
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
            String openId = (String)session.getAttribute("openid");
            try{
                userService.updatePhone(id, openId);
                userService.updateRemain(5.0, openId);
            }catch (Exception e){
                logger.error("userService.update phone and remain error!");
                jsonObject.put("testpass","no");
                response.getWriter().print(jsonObject);
                return;
            }
            jsonObject.put("testpass","yes");
            response.getWriter().print(jsonObject);
            return;
        }
    }

}
