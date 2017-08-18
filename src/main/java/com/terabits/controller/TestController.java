package com.terabits.controller;

import com.terabits.meta.po.UserPO;
import com.terabits.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/8/17.
 */
@Controller
public class TestController {
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value="/testuser",method= RequestMethod.GET)
    public String testuser(){

       UserPO userPO = new UserPO();
       userPO.setOpenId("1234567");
       userPO.setNickname("baibai");
       String openid = "1234567";
       String phone = "18868183238";
       Double remain = 10.0;
       try {
           userService.updateRemain(remain, openid);
       }catch (Exception e){
           e.printStackTrace();
           logger.error("error!");
       }
       return "main/login.jsp";
    }

}
