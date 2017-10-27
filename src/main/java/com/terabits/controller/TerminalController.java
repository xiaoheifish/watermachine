package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.CredentialService;
import com.terabits.service.TerminalService;
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

/**
 * Created by Administrator on 2017/10/25.
 */
@Controller
public class TerminalController {

    @Autowired
    private TerminalService terminalService;
    @Autowired
    private CredentialService credentialService;

    private static Logger logger = LoggerFactory.getLogger(TerminalController.class);

    @RequestMapping(value="/terminal/state",method= RequestMethod.POST)
    public void getTerminalState(HttpServletRequest request, HttpServletResponse response){
        String displayId = request.getParameter("displayid");
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        JSONObject jsonObject = new JSONObject();
        String state = "";
        if(terminalPO.getState() == Constants.ORDER_STATE){
            state = "下单中";
        }else if(terminalPO.getState() == Constants.ON_STATE){
            state = "使用中";
        }else if(terminalPO.getState() == Constants.OFF_STATE){
            credentialService.createWechatConsume(displayId, "123");
            state = "空闲";
        }else{
            state = "不可使用";
        }
        String openId = credentialService.getWechatConsumer(displayId);
        jsonObject.put("state", state);
        jsonObject.put("openid", openId);
        try {
        	response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject);
        }catch (Exception e){
            logger.error("response.getWriter().error in TerminalController" + e.toString());
        }
    }

}
