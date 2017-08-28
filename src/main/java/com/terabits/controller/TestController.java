package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.*;
import com.terabits.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/8/17.
 */
@Controller
public class TestController {

    @Autowired
    private JsapiTicketService jsapiTicketService;

    @Autowired
    private TerminalService terminalService;
    @Autowired
    private ConsumeOrderService consumeOrderService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private CommandService commandService;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value="/testuser",method= RequestMethod.GET)
    public String testuser(){
        CommandPO commandPO = new CommandPO();
        commandPO.setDeviceId("123");
        commandPO.setFlow(0.3);
        commandPO.setCommandIdOne("1232423");
        commandPO.setState("10");
        try {
            commandService.insertCommand(commandPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "main/login.jsp";
    }

    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String mainpage(){
        return "main/register.jsp";
    }

    @RequestMapping(value="/stopdevice")
    public void stopdevice(HttpServletRequest request, HttpServletResponse response){
        String imei = request.getParameter("imei");
        //收到执行完成命令，更新设备表里的状态
        TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
        terminalUpdateBO.setState(Constants.OFF_STATE);
        terminalUpdateBO.setImei(imei);
        terminalService.updateTerminal(terminalUpdateBO);
        //在操作记录中增加此次操作
        OperationPO operationPO = new OperationPO();
        operationPO.setStatus(Constants.ON_TO_OFF);
        operationPO.setImei(imei);
        operationService.insertOperation(operationPO);
    }


}
