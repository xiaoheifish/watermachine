package com.terabits.controller;

import com.terabits.config.WeixinGlobal;
import com.terabits.service.CredentialService;

import com.terabits.meta.po.TerminalPO;
import com.terabits.service.TerminalService;
import com.terabits.utils.SmsDemo;
import com.terabits.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/21.
 */
@Controller
public class MainController
{
    @Autowired
    private CredentialService CredentialService;
    @Autowired
    private TerminalService terminalService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value="/info/{displayId}",method=RequestMethod.GET)
    public String getProductInfo(@PathVariable("displayId") String displayId, HttpServletRequest request, ModelMap model) throws Exception {
        System.out.println("displayId"+displayId);
        Long display = Long.parseLong(displayId);
        String time = CredentialService.getTerminalTime(displayId);
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        if(time == null){
            model.addAttribute("status","空闲");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            model.addAttribute("usingtime",null);
            model.addAttribute("lefttime",null);
            return "main/information.jsp";
        }
        else{
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long between = 0;
            try {
                Date begin =dfs.parse(time);
                Date end = new Date();
                between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String leftTime = CredentialService.getLeftTime(displayId);
            System.out.println("lefttime:::::::::" + leftTime);
            long money = (between + Long.parseLong(leftTime)) / 40;
            System.out.println("money::::::" + String.valueOf(money));
            model.addAttribute("status","使用中");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            model.addAttribute("usingtime", String.valueOf(between));
            model.addAttribute("lefttime", leftTime);
            model.addAttribute("money",String.valueOf(money));
            model.addAttribute("water",String.valueOf(money));
            return "main/using.jsp";
        }
    }

    @RequestMapping(value="/mainpage",method=RequestMethod.GET)
    public String mainpage(HttpServletRequest request){

        String code = request.getParameter("code");
        JSONObject jsonObject = WeixinUtil.getOpenid(code, WeixinGlobal.APP_ID, WeixinGlobal.APP_SECRET);
        String openId = jsonObject.getString("openid");
        String accesstoken = jsonObject.getString("access_token");
        JSONObject jsonObject1 = WeixinUtil.getUserInfo(accesstoken, openId);
        System.out.println("watermachineusrinfo:::::::::"+jsonObject1);
        return "main/login.jsp";
    }

    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String register(HttpServletRequest request){
        return "main/register.jsp";
    }

    @RequestMapping(value="/sendmessage",method=RequestMethod.GET)
    public void sendmessage(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        System.out.println(id);
        String code = SmsDemo.sendMessage(id);
        String auth = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth",auth);
        session.setAttribute("auth", auth);
        session.setAttribute("number", id);
        session.setAttribute("code",code);
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value="/testcode",method=RequestMethod.POST)
    public void testcode(HttpServletRequest request, HttpServletResponse response)throws Exception{
        HttpSession session = request.getSession();
        String tempAuth = (String)session.getAttribute("auth");
        String tempId = (String)session.getAttribute("number");
        String tempCode = (String)session.getAttribute("code");
        JSONObject jsonObject = new JSONObject();
        String auth = request.getParameter("auth");
        String id = request.getParameter("id");
        String code = request.getParameter("watermachine");
        System.out.println(tempAuth +auth+ tempId+id+tempCode+code);
        if(!(tempAuth.equals(auth))){
            jsonObject.put("testpass","no");
            response.getWriter().print(jsonObject);
            return;
        }
        if(!(tempId.equals(id))){
            jsonObject.put("testpass","no");
            response.getWriter().print(jsonObject);
            return;
        }

        if(tempCode.equals(code)){
            jsonObject.put("testpass","yes");
            response.getWriter().print(jsonObject);
            return;
        }
    }


}
