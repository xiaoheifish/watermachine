package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.meta.po.HeartBeatPO;
import com.terabits.meta.po.NotifyDataPO;
import com.terabits.meta.po.OperationPO;
import com.terabits.service.CommandService;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.CredentialService;
import com.terabits.service.HeartBeatService;
import com.terabits.service.NotifyDataService;
import com.terabits.service.OperationService;
import com.terabits.service.TerminalService;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private CommandService commandService;
    @Autowired
    private HeartBeatService heartBeatService;
    @Autowired
    private CredentialService credentialService;

    private static Logger logger = LoggerFactory.getLogger(ReceiveController.class);

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
        String info = (String) data.get("terminalState");
        byte[] rawInfo = new byte[info.length()];
        String content = "";
        //System.out.print("rawdata:::::::::::");
        for (int i = 0; i < info.length(); i++) {
            rawInfo[i] = (byte) info.charAt(i);
            System.out.print(rawInfo[i] + " ");
            content += rawInfo[i];
            content += " ";
        }
        NotifyDataPO notifyDataPO = new NotifyDataPO();
        notifyDataPO.setContent(content);
        if (rawInfo[0] != (byte) 0x1A){
            notifyDataService.insertNotifyData(notifyDataPO);
        }
        if (rawInfo[0] == (byte) 0x1B) {
            String imei = terminalService.selectImeiFromDeviceId(deviceId);
            String displayId = terminalService.getDisplayIdFromImei(imei);
            ConsumeOrderPO consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
            //如果订单状态没有被更新过，表明是第一次收到回复，则更新订单状态及设备状态
            if(consumeOrderPO.getState() == Constants.HAVE_RESPONSE) {}
                /*try {
                    consumeOrderService.updateStateById(consumeOrderPO.getOrderNo());
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                //更新设备表中的设备状态
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setState(Constants.ON_STATE);
                terminalUpdateBO.setDisplayId(displayId);
                terminalService.updateTerminal(terminalUpdateBO);
                //更新命令表中此条命令的状态
                commandService.updateState(Constants.HALF_STATE, deviceId);
            
        } else if (rawInfo[0] == (byte) 0x1C) {
            String imei = terminalService.selectImeiFromDeviceId(deviceId);
            System.out.println("doneimei:::::" + imei);
            //收到执行完成命令，更新设备表里的状态
            TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
            terminalUpdateBO.setState(Constants.OFF_STATE);
            terminalUpdateBO.setImei(imei);
            terminalService.updateTerminal(terminalUpdateBO);
            //删除redis缓存中的该设备
            credentialService.deleteExpireDevice(deviceId);
            //更新命令表中此条命令的状态
            commandService.updateState(Constants.END_STATE, deviceId);
        } else if (rawInfo[0] == (byte) 0x19) {
            int strength = (int)rawInfo[1];
            String hexStrenght = Integer.toHexString(strength);
            terminalService.updateStrength(Integer.parseInt(hexStrenght), deviceId);
        } else if (rawInfo[0] == (byte) 0x1A){
            HeartBeatPO heartBeatPO = heartBeatService.selectHeartBeat(deviceId);
            if(heartBeatPO == null){
            	heartBeatPO = new HeartBeatPO();
                heartBeatPO.setDeviceId(deviceId);
                heartBeatService.insertHeartBeat(heartBeatPO);
            }else {
                heartBeatService.updateHeartBeat(deviceId);
            }
        }
        String url = "http://112.124.6.31/watermachine/transferdata" ;
        HttpClient httpClient = new DefaultHttpClient();
        // get method
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringentity = new StringEntity(json.toString(),
                ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringentity);
        HttpResponse dataResponse = null;
        try{
            //之后可加上对dataRespons的处理
            dataResponse = httpClient.execute(httpPost);
        }catch (Exception e) {

        }
    }

}