package com.terabits.controller;

import com.terabits.service.ConsumeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/8/24.
 */
@Controller
public class PostCommandController {

    @Autowired
    private ConsumeOrderService consumeOrderService;

    @RequestMapping(value = "/postcommand", method = RequestMethod.POST)
    public void postcommand(HttpServletRequest request){

    }
}
