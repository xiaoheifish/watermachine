package com.terabits.controller;

import com.terabits.meta.po.AccessTokenPO;
import com.terabits.meta.po.HuaweiTokenPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.AccessTokenService;
import com.terabits.service.HuaweiTokenService;
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
    @Autowired
    private HuaweiTokenService huaweiTokenService;
    @Autowired
    private AccessTokenService accessTokenService;
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value="/testuser",method= RequestMethod.GET)
    public String testuser(){


        HuaweiTokenPO huaweiTokenPO = new HuaweiTokenPO();
        //huaweiTokenPO.setHuaweiToken("1rwerwe3");
        AccessTokenPO accessTokenPO = new AccessTokenPO();
        //accessTokenPO.setAccessToken("2343423");
       try {
           accessTokenPO = accessTokenService.getLatestToken();
       }catch (Exception e){
           e.printStackTrace();
           logger.error("error!");
       }
       System.out.println(accessTokenPO);
       return "main/login.jsp";
    }

}
