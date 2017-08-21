package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.meta.po.NotifyDataPO;
import com.terabits.meta.po.OperationPO;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.NotifyDataService;
import com.terabits.service.OperationService;
import com.terabits.service.TerminalService;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
@Controller
public class ReceiveController {
    @Autowired
    private NotifyDataService notifyDataService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private ConsumeOrderService consumeOrderService;
    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/receive/data", method = RequestMethod.POST)
    public void data(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        JSONObject json = JSONObject.fromObject(wholeStr);
        Map<String, Object> map = (Map<String, Object>) json;
        String deviceId = (String) map.get("deviceId");
        Map<String, Object> service = (Map<String, Object>) map.get("service");
        Map<String, Object> data = (Map<String, Object>) service.get("data");
        String info = (String)data.get("terminalState");
        byte [] rawInfo = new byte[info.length()];
        String content = "";
        System.out.print("rawdata:::::::::::");
        for (int i = 0; i < info.length(); i++) {
            rawInfo[i] = (byte) info.charAt(i);
            System.out.print(rawInfo[i]+" ");
            content += rawInfo[i];
            content += " ";
        }
        NotifyDataPO notifyDataPO = new NotifyDataPO();
        notifyDataPO.setContent(content);
        notifyDataService.insertNotifyData(notifyDataPO);
        if(rawInfo[0] == (byte)0x1B){
            String imei = terminalService.selectImeiFromDeviceId(deviceId);
            String displayId = terminalService.getDisplayIdFromImei(imei);
            ConsumeOrderPO consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
            try {
                consumeOrderService.updateStateById(consumeOrderPO.getOrderNo());
            }catch (Exception e){
                e.printStackTrace();
            }
            //更新设备表中的设备状态
            TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
            terminalUpdateBO.setState(Constants.ON_STATE);
            terminalUpdateBO.setDisplayId(displayId);
            terminalService.updateTerminal(terminalUpdateBO);
            //在数据库中添加此次操作记录,operationPO里可以记录下指令编号，用于调试！！
            OperationPO operationPO = new OperationPO();
            operationPO.setStatus(Constants.OFF_TO_ON);
            operationPO.setImei(imei);
            operationService.insertOperation(operationPO);
        }
        else if(rawInfo[0] == (byte)0x1C){
            String imei = terminalService.selectImeiFromDeviceId(deviceId);
            System.out.println("doneimei:::::"+imei);
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
}