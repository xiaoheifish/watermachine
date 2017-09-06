package com.terabits.service.impl;

import com.terabits.controller.ConsumeController;
import com.terabits.service.AccessTokenService;
import com.terabits.service.QrcodeService;
import com.terabits.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/9/5.
 */
@Component
public class QrcodeServiceImpl implements QrcodeService {
    @Autowired
    private AccessTokenService accessTokenService;

    private static Logger logger = LoggerFactory
            .getLogger(QrcodeServiceImpl.class);

    public JSONObject getWebId(String displayId){
        String accessToken = null;
        try {
            accessToken = accessTokenService.getLatestToken().getAccessToken();
        }catch (Exception e){
            logger.error("get accesstoken error in qrcodeserviceimpl");
            return null;
        }
        String data = WeixinUtil.generateQrcode(accessToken, displayId);
        JSONObject tempData = JSONObject.fromObject(data);
        String webId = (String)tempData.get("url");
        String[] urlArray = webId.split("/");
        String finalWebId = urlArray[urlArray.length - 1];
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("webId",finalWebId);
        return jsonObject;
    }

}
