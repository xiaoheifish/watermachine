package com.terabits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/8/21.
 */
@Controller
public class RuleController {
    @RequestMapping(value="/rules",method= RequestMethod.GET)
    public String shouRule(HttpSession session){
        if(session.getAttribute("openid") == null){
            return "main/timeout.jsp";
        }
        return "main/rules.jsp";
    }

}
