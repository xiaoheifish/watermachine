package com.terabits.controller;

import com.terabits.meta.po.NotifyDataPO;
import com.terabits.service.NotifyDataService;
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
    @RequestMapping(value = "/receive/data", method = RequestMethod.POST)
    public void data(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println("watermachine:::::::::::"+wholeStr);
        JSONObject json = JSONObject.fromObject(wholeStr);
        Map<String, Object> map = (Map<String, Object>) json;
        String terminalId = (String) map.get("deviceId");
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
        System.out.println();
    }
}