package com.terabits.controller.xhr;

import com.terabits.config.WeixinGlobal;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.TerminalService;
import com.terabits.utils.GenerateOrderId;
import com.terabits.utils.TimeSpanUtil;
import com.terabits.utils.WeixinUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4.
 */
@Controller
public class WeixinGetCode {
   /* @Autowired
    private TerminalService terminalService;
    @RequestMapping(value="/callback/{info}",method= RequestMethod.GET)
    public String wxPay(@PathVariable("info") String info, HttpSession session, HttpServletRequest request, ModelMap modelMap) throws IOException, ParserConfigurationException, JDOMException,JSONException {
        System.out.println(info);
        String code = request.getParameter("code");
        JSONObject jsonObject = WeixinUtil.getOpenid(code, WeixinGlobal.APP_ID, WeixinGlobal.APP_SECRET);
        String openId = jsonObject.getString("openid");
        System.out.println("openid:" + openId);
        session.setAttribute("openid",openId);
        String[] information = info.split("_");
        String displayId = information[0];
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        int finalmoney = Integer.parseInt(information[1]);
        modelMap.addAttribute("status","空闲");
        modelMap.addAttribute("id", displayId);
        modelMap.addAttribute("location",terminalPO.getLocation());
        modelMap.addAttribute("money",finalmoney);
        modelMap.addAttribute("water", finalmoney * 3);
        return "main/confirm.jsp";
    }*/
}
