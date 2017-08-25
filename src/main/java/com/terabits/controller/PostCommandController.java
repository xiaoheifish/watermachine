package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.manager.PostCommandManager;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.model.CommandNoModel;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.CredentialService;
import com.terabits.service.TerminalService;
import com.terabits.utils.FlowUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/8/24.
 */
@Controller
public class PostCommandController {

    @Autowired
    private ConsumeOrderService consumeOrderService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private TerminalService terminalService;

    private static Logger logger = LoggerFactory.getLogger(PostCommandController.class);

    @RequestMapping(value = "/postcommand/{flow}/{displayid}", method = {
            RequestMethod.GET, RequestMethod.POST })
    public void postcommand(@PathVariable("flow") String flow, @PathVariable("displayid") String displayId, HttpServletResponse response)throws Exception{
        response.getWriter().print("success");
        System.out.println("flow--------------------------------------"+flow);
        System.out.println("displayid---------------------------------"+displayId);
        // 查询指令编号并更新
        String commandOne = credentialService.getCommandNo("commandOne");
        String commandTwo = credentialService.getCommandNo("commandTwo");
        CommandNoModel commandNoModel = new CommandNoModel();
        commandNoModel.setCommandId("commandTwo");
        int tempCommandTwo = Integer.parseInt(commandTwo) + 1;
        commandNoModel.setNumber(String.valueOf(tempCommandTwo));
        credentialService.createCommand(commandNoModel);
        // 下发指令
        int cmdOne = Integer.parseInt(commandOne);
        int cmdTwo = Integer.parseInt(commandTwo);
        CommunicationBO communicationBO = terminalService.getTerminalDeviceId(displayId);
        String deviceId = communicationBO.getDeviceId();
        logger.error("communicationBO::::::" + deviceId);
        byte[] openbytes = new byte[6];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = FlowUtil.flowToCommand(Double.parseDouble(flow));
        openbytes[3] = (byte) cmdOne;
        openbytes[4] = (byte) cmdTwo;
        openbytes[5] = Constants.SEND_COMMAND_END;
        Date now = new Date();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = dfs.format(now);
        PlatformGlobal.command(openbytes, communicationBO.getDeviceId());
        now = new Date();
        String time2 = dfs.format(now);
        logger.error("platform global ok:::::"+ time1 +"-------"+ time2);
    }
}
