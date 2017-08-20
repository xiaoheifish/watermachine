package com.terabits.controller;


import com.terabits.config.Constants;
import com.terabits.service.CredentialService;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.model.CommandNoModel;
import com.terabits.meta.po.OperationPO;
import com.terabits.service.OperationService;
import com.terabits.service.TerminalService;
import com.terabits.utils.huawei.PlatformGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
public class RedisController {

    @Autowired
    private CredentialService CredentialService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private OperationService operationService;
    @RequestMapping(value = {"/add", "/add.html" }, method = { RequestMethod.POST })
    public void addMember(HttpServletRequest request,
                          HttpServletResponse response,
                          @ModelAttribute("terminalModel")CommandNoModel commandNoModel) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(commandNoModel);
        CredentialService.createCommand(commandNoModel);
        System.out.println("插入成功");
    }

    @RequestMapping(value = {"/query", "/query.html" }, method = { RequestMethod.GET})
    public void queryMember(HttpServletRequest request,
                          HttpServletResponse response) throws Exception, IOException {
       String id = request.getParameter("commandId");
       String no =  CredentialService.getCommandNo(id);
       System.out.println("取回成功" + no);
       response.getWriter().print(no);
    }

    @RequestMapping(value = {"/huawei"},method={RequestMethod.GET})
    public void huawei(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String command = request.getParameter("command");
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /*CommandNoModel terminalModel = new CommandNoModel();
        terminalModel.setTerminalId("000002");
        terminalModel.setTime(time);
        terminalModel.setHour(1);
        redisTemplateTest.createTerminal(terminalModel);
        System.out.println("插入terminalModel");*/

        //更新终端状态
      /*  TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
        terminalUpdateBO.setDisplayId("000002");
        terminalUpdateBO.setState(Constants.ON_STATE);
        terminalService.updateTerminal(terminalUpdateBO);
        System.out.println("更改终端状态");*/
        if (command.equals("on")) {
            //在数据库中添加此次操作记录
            CommunicationBO communicationBO = terminalService.getTerminalDeviceId("100002");
            OperationPO operationPO = new OperationPO();
            operationPO.setStatus(Constants.OFF_TO_ON);
            operationPO.setImei(communicationBO.getImei());
            operationService.insertOperation(operationPO);
            System.out.println("插入终端变化");
            //下发开启插座命令给终端
            byte[] openbytes = new byte[6];
            openbytes[0] = Constants.SEND_COMMAND_START;
            openbytes[1] = Constants.POWER_ON_COMMAND;
            openbytes[2] = Constants.ONE_L_WATER;
            openbytes[3] = Constants.COMMAND_ONE;
            openbytes[4] = Constants.COMMAND_TWO;
            openbytes[5] = Constants.SEND_COMMAND_END;
            Date now = new Date();
            String time1 = dfs.format(now);
            PlatformGlobal.command(openbytes, communicationBO.getDeviceId());
            now = new Date();
            String time2 = dfs.format(now);
            response.getWriter().print("power on ok: " + time1 + " " + time2);
        }
        else if (command.equals("off")){
            //在数据库中添加此次操作记录
            CommunicationBO communicationBO = terminalService.getTerminalDeviceId("100002");
            OperationPO operationPO = new OperationPO();
            operationPO.setStatus(Constants.ON_TO_OFF);
            operationPO.setImei(communicationBO.getImei());
            operationService.insertOperation(operationPO);
            System.out.println("插入终端变化");
            //下发开启插座命令给终端
            byte[] openbytes = new byte[3];
            openbytes[0] = Constants.SEND_COMMAND_START;
            openbytes[1] = Constants.CUT_OFF_COMMAND;
            openbytes[2] = Constants.SEND_COMMAND_END;
            Date now = new Date();
            String time1 = dfs.format(now);
            PlatformGlobal.command(openbytes, communicationBO.getDeviceId());
            now = new Date();
            String time2 = dfs.format(now);
            response.getWriter().print("cut off ok: " + time1 + " "+ time2);
        }
    }

}
