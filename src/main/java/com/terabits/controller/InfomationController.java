package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.CredentialService;
import com.terabits.service.TerminalService;
import com.terabits.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/19.
 */
@Controller
public class InfomationController {

    @Autowired
    private ConsumeOrderService consumeOrderService;
    @Autowired
    private TerminalService terminalService;

    @RequestMapping(value="/info/{displayId}",method= RequestMethod.GET)
    public String getProductInfo(@PathVariable("displayId") String displayId, ModelMap model) throws Exception {
        //根据终端的displayId取出终端数据，如果state是未使用的话，则返回空闲状态
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        if(terminalPO.getState() == Constants.OFF_STATE){
            model.addAttribute("status","空闲");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            model.addAttribute("usingtime",null);
            model.addAttribute("lefttime",null);
            return "main/information.jsp";
        }
        else{
            //如果state是使用中的话，则去consumeOrder中查询此设备对应的最后一笔交易，取出该记录产生的时间，用以算使用时间，取出该记录的水量，用以算剩余时间
            ConsumeOrderPO consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
            String time = consumeOrderPO.getGmtCreate();
            double flow = consumeOrderPO.getFlow();
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long between = 0;
            try {
                Date begin =dfs.parse(time);
                Date end = new Date();
                between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            long totalTime = TimeUtils.FlowToTime(flow);
            long leftTime = totalTime - between;
            model.addAttribute("status","使用中");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            model.addAttribute("water", String.valueOf(consumeOrderPO.getFlow()));
            model.addAttribute("lefttime", String.valueOf(leftTime));
            return "main/using.jsp";
        }
    }
}
