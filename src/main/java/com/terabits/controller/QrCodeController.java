package com.terabits.controller;

import com.terabits.service.QrcodeService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/9/5.
 */
@Controller
public class QrCodeController {
    @Autowired
    private QrcodeService qrcodeService;

    private static Logger logger = LoggerFactory
            .getLogger(QrCodeController.class);

    //点击充值按钮跳转至此页面
    @RequestMapping(value = "/getwebid", method = RequestMethod.POST)
    public void getQrcode(HttpServletRequest request, HttpServletResponse response)
    {
        JSONObject jsonObject = qrcodeService.getWebId(request.getParameter("displayId"));
        try {
            response.getWriter().print(jsonObject);
        }catch (Exception e){
            logger.error("response.getWriter.print error in QrCodeController " + jsonObject.toString());
        }
    }
}
