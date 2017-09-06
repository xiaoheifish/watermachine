package com.terabits.controller;

import com.terabits.config.Constants;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.meta.po.HeartBeatPO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.CredentialService;
import com.terabits.service.HeartBeatService;
import com.terabits.service.TerminalService;
import com.terabits.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private HeartBeatService heartBeatService;

    @RequestMapping(value="/info/{displayId}",method= RequestMethod.GET)
    public String getProductInfo(@PathVariable("displayId") String displayId, ModelMap model, HttpSession session) throws Exception {
        if(session.getAttribute("openid") == null){
            return "main/timeout.jsp";
        }
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //根据终端的displayId取出终端数据
        TerminalPO terminalPO = terminalService.selectOneTerminal(displayId);
        
        //先查询心跳包，判断是否离线,数据为空和gmtModified在两分钟以前都判读为离线
        HeartBeatPO heartBeatPO = heartBeatService.selectHeartBeat(terminalPO.getDeviceId());
        if(heartBeatPO == null){
            model.addAttribute("status","不可使用");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            return "main/order.jsp";
        }else{
            String gmtModified = heartBeatPO.getGmtModified();
            Date now = new Date();
            Date bein = dfs.parse(gmtModified);
            long timeSpan = (now.getTime() - bein.getTime()) / 1000;
            if(timeSpan > 120){
                model.addAttribute("status","不可使用");
                model.addAttribute("id",displayId);
                model.addAttribute("location",terminalPO.getLocation());
                return "main/order.jsp";
            }
        }

        //如果state是未使用的话，则返回空闲状态;如果state是下单中，则返回下单中；如果是使用中，则计算剩余时间，后续可能用到

        if(terminalPO.getState() == Constants.OFF_STATE){
            model.addAttribute("status","空闲");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            return "main/information.jsp";
        }else if((terminalPO.getState() == Constants.ORDER_STATE)|| (terminalPO.getState() == 0)){
            model.addAttribute("status","下单中");
            model.addAttribute("id",displayId);
            model.addAttribute("location",terminalPO.getLocation());
            return "main/order.jsp";
        }else if (terminalPO.getState() == Constants.NO_RESPONSE){
        	  model.addAttribute("status","不可使用");
              model.addAttribute("id",displayId);
              model.addAttribute("location",terminalPO.getLocation());
              return "main/order.jsp";
        }
        else{
            //如果state是使用中的话，则去consumeOrder中查询此设备对应的最后一笔交易，取出该记录产生的时间，用以算使用时间，取出该记录的水量，用以算剩余时间
            ConsumeOrderPO consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
            String time = consumeOrderPO.getGmtCreate();
            double flow = consumeOrderPO.getFlow();
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
