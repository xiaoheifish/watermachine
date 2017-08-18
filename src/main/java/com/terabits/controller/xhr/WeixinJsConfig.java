package com.terabits.controller.xhr;

import com.terabits.service.JsapiTicketService;
import com.terabits.utils.JssdkConfigUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
@Controller
public class WeixinJsConfig {
    @Autowired
    private JsapiTicketService jsapiTicketService;
    @RequestMapping(value = {"/wxconfig"},method ={RequestMethod.POST})
    public void getconfig(@RequestParam(value = "url", required = true) String url, HttpServletResponse response) throws ParseException,IOException,Exception {
        //String url = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort() + request.getServletPath();
        System.out.println("url"+url);
        String ticket = jsapiTicketService.getLatestJsapi().getJsapiTicket();
        Map<String, String> configMap = JssdkConfigUtil.sign(ticket,url);
        JSONObject jsonObject = JSONObject.fromObject(configMap);
        System.out.println("configMap:::::::::::::::"+configMap);
        response.getWriter().print(jsonObject);
    }
}
