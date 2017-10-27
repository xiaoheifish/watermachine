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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        int remainMedal =consumeSignService.getRemainMedal(openId);
        ConsumeSignPO consumeSignPO = new ConsumeSignPO();
        try{
            consumeSignPO = consumeSignService.selectConsumeSign(openId);
        }catch (Exception e){
            logger.error("consumeSignService.selectConsumeSign error in MedalController, openId =" + openId + e.toString());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number",remainMedal);
        jsonObject.put("day",consumeSignPO.getSignCount());
        response.getWriter().print(jsonObject);
    }

}
