package com.terabits.controller;

import com.terabits.meta.po.ConsumeSignPO;
import com.terabits.service.ConsumeSignService;
import com.terabits.service.MedalExchangeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/10/26.
 */
@Controller
public class MedalController {

    @Autowired
    private MedalExchangeService medalExchangeService;
    @Autowired
    private ConsumeSignService consumeSignService;

    private static Logger logger = LoggerFactory.getLogger(MedalController.class);


    @RequestMapping(value = "/medal/number", method = RequestMethod.POST)
    public void medalNumber(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String openId = request.getParameter("openid");
        JSONObject remainMedal =consumeSignService.getRemainMedal(openId);
        response.getWriter().print(remainMedal);
    }

    @RequestMapping(value = "/medal", method = RequestMethod.GET)
    public String showMedalPage() throws Exception{
        return "main/exchange_medal.jsp";
    }

    @RequestMapping(value = "/medalhistory", method = RequestMethod.GET)
    public String showMedalHistory() throws Exception{
        return "main/exmedalrec.jsp";
    }

    @RequestMapping(value = "/medal/exchange", method = RequestMethod.POST)
    public void pullExchangeRequest(@RequestParam(value = "openid") String openId,
                                    @RequestParam(value = "number") int number, HttpServletResponse response) throws Exception{
        int result = medalExchangeService.exchangeRequest(openId, number);
        JSONObject jsonObject = new JSONObject();
        if(result == 200){
            jsonObject.put("result", "success");
        }else {
            jsonObject.put("result", "error");
        }
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "/medalrecord", method = RequestMethod.POST)
    public @ResponseBody
    JSONArray pullExchangeRequest(@RequestParam(value = "openid") String openId) throws Exception{
        return  medalExchangeService.getMonthlyExchange(openId);
    }

    @RequestMapping(value = "/medal/test", method = RequestMethod.POST)
    public void pullExchangeRequest(@RequestParam(value = "openid") String openId,
                                    @RequestParam(value = "gmtmodified") String gmtModified, HttpServletResponse response) throws Exception{
        consumeSignService.updateSign(openId,gmtModified);
    }

}
