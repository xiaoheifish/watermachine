package com.terabits.controller;

import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.service.ConsumeOrderService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 * 消费记录
 */
@Controller
public class RecordController {
    @Autowired
    private ConsumeOrderService consumeOrderService;
    private static Logger logger = LoggerFactory.getLogger(RecordController.class);

    //显示消费记录页
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public String showRecord(){
        return "main/record.jsp";
    }

    //获取消费记录
    @RequestMapping(value = "/menu/record", method = RequestMethod.POST)
    public void getRecord(HttpServletRequest request, HttpServletResponse response){
        String openId = request.getParameter("openid");
        List<ConsumeOrderPO> consumeOrderPOmeOList = new ArrayList<ConsumeOrderPO>();
        try{
            consumeOrderPOmeOList = consumeOrderService.selectConsumptionByOpenId(openId);
            JSONArray jsonObject = JSONArray.fromObject(consumeOrderPOmeOList);
            System.out.println("jsonarray"+jsonObject);
            response.getWriter().print(jsonObject);
        }catch (Exception e){
            logger.error("consumeOrderService.selectConsumptionByDisplayId error in recordcontroller");
        }

    }
}
