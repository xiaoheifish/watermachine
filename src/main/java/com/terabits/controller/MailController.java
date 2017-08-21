package com.terabits.controller;

import com.terabits.meta.po.UserPO;
import com.terabits.service.MailService;
import com.terabits.service.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/8/20.
 */
@Controller
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(MailController.class);

    //显示邮件页
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String showRecord(){
        return "main/service.jsp";
    }

    //发送邮件接口
    @RequestMapping(value = "/mail/feedback", method = RequestMethod.POST)
    public void sendMail(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //根据openid获取
        String openid = request.getParameter("openid");
        UserPO userPO = userService.selectUser(openid);
        String email = request.getParameter("email");
        String feedback = request.getParameter("suggestion");
        String content = "邮件内容：" +
                "openid=" + openid + '\n' +
                ", nickname=" + userPO.getNickname() + '\n' +
                ", phone =" + userPO.getPhone() + '\n' +
                ", email =" + email + '\n' +
                ", feedback =" + feedback + '\n';
        mailService.userFeedback(content);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","ok");
        response.getWriter().print(jsonObject);
    }
}
