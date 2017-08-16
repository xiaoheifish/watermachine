package com.terabits.controller;

import com.terabits.manager.RedisTemplateTest;
import com.terabits.manager.TerminalManager;
import com.terabits.meta.po.TerminalPO;
import com.terabits.meta.po.User;
import com.terabits.service.OperationService;
import com.terabits.service.OrderService;
import com.terabits.service.TerminalService;
import com.terabits.service.UserService;
import com.terabits.utils.TimeSpanUtil;
import com.terabits.utils.TimeUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/21.
 */
@Controller
public class MainController
{
    @Autowired
    private RedisTemplateTest redisTemplateTest;
    @Autowired
    private TerminalService terminalService;
    @RequestMapping(value="/info/{displayId}",method=RequestMethod.GET)
    public String getProductInfo(@PathVariable("displayId") String displayId, HttpServletRequest request, ModelMap model) throws Exception {
        System.out.println("displayId"+displayId);
        Long display = Long.parseLong(displayId);
        String time = redisTemplateTest.getTerminalTime(displayId);
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
            String leftTime = redisTemplateTest.getLeftTime(displayId);
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
    public String main(){
        return "main/login.jsp";
    }

}
