package com.terabits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/8/21.
 */
@Controller
public class RuleController {
    @RequestMapping(value="/rules",method= RequestMethod.GET)
    public String mainpage(){
        return "main/rules.jsp";
    }

}
