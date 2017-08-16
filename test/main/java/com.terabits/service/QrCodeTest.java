package com.terabits.service;

import com.terabits.config.Constants;
import com.terabits.utils.WeixinUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/8/2.
 */
public class QrCodeTest extends BaseTest {
    @Autowired(required = false)  //自动注入,默认按名称
    private AccessTokenService accessTokenService;
    @Test  //标明是测试方法
    @Transactional(value="transactionManager")//标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void main() throws Exception{
        String accessToken = accessTokenService.getLatestToken().getAccessToken();
        String qrCodeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
        qrCodeUrl = qrCodeUrl.replace("ACCESS_TOKEN", accessToken);
        String jsonStr = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
        String response = WeixinUtil.httpRequest(qrCodeUrl,"POST",jsonStr);
        System.out.println(response);
    }
}
