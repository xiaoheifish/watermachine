package com.terabits.manager;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.terabits.config.HuaweiPlatformGlobal;
import com.terabits.service.HuaweiTokenService;
import com.terabits.utils.huawei.HttpsUtil;
import com.terabits.utils.huawei.JsonUtil;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.terabits.utils.huawei.PlatformGlobal.callbackUrl;
import static com.terabits.utils.huawei.PlatformGlobal.expireTime;

/**
 * Created by Administrator on 2017/8/22.
 */
@Service("postCommandManager")
public class PostCommandManagerImpl implements PostCommandManager {

    @Autowired
    private HuaweiTokenService huaweiTokenService;

    private static Logger logger = LoggerFactory
            .getLogger(PostCommandManagerImpl.class);

    //模拟透传的模式，下发命令用这个方法
    public String command(byte[] data, String terminalId) throws Exception{
        System.out.println("----------------------------------------------");
        System.out.println("terminalId::::::"+terminalId);
        System.out.println("----------------------------------------------");
        String command = Base64.encodeBase64String(data);

        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        String accessToken = huaweiTokenService.getLatestToken().getHuaweiToken();
        String urlPostAsynCmd = HuaweiPlatformGlobal.APP_URL+ "/iocm/app/cmd/v1.2.0/devices/" + terminalId + "/commands";

        String method = "START";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"rawData\":\"" + command +"\"}");

        Map<String, Object> paramCommand = new HashMap<String, Object>();
        paramCommand.put("serviceId", "RawData");
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);

        Map<String, Object> paramPostAsynCmd = new HashMap<String, Object>();
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", callbackUrl);
        paramPostAsynCmd.put("expireTime",expireTime);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);

        Map<String, String> header = new HashMap<String, String>();
        header.put("app_key", HuaweiPlatformGlobal.APP_ID);
        header.put("Authorization", "Bearer " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(httpResponse);
        return responseBody;
    }
}
