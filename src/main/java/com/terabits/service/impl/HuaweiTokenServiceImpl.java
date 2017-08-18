package com.terabits.service.impl;

import com.terabits.controller.TestController;
import com.terabits.mapper.HuaweiTokenMapper;
import com.terabits.meta.po.HuaweiTokenPO;
import com.terabits.service.HuaweiTokenService;
import com.terabits.utils.huawei.HttpsUtil;
import com.terabits.utils.huawei.PlatformGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/18.
 */
@Service("huaweiTokenService")
public class HuaweiTokenServiceImpl implements HuaweiTokenService {
    @Autowired(required = false)
    private HuaweiTokenMapper huaweiTokenMapper;
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public int insertToken(HuaweiTokenPO huaweiTokenPO) throws Exception{
        return huaweiTokenMapper.insertToken(huaweiTokenPO);
    }

    public int updateToken(HuaweiTokenPO huaweiTokenPO) throws Exception{
        return huaweiTokenMapper.updateToken(huaweiTokenPO);
    }
    //取出数据库中的huaweiToken，若存入时间和当前时间比较，小于7000秒，则重新获取一个token
    public HuaweiTokenPO getLatestToken() throws Exception{
        HuaweiTokenPO huaweiTokenPO = null;
        try {
            huaweiTokenPO = huaweiTokenMapper.selectToken();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("get huawei token error!");
        }
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
            Date begin =dfs.parse(huaweiTokenPO.getGmtCreate());
            Date end = new Date();
            between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(between < 7000){
            return huaweiTokenPO;
        }
        else {
            HttpsUtil httpsUtil = new HttpsUtil();
            String huaweiToken = null;
            try {
                httpsUtil.initSSLConfigForTwoWay();
                huaweiToken = PlatformGlobal.login(httpsUtil);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("huawei httpsutil error!");
            }
            HuaweiTokenPO huaweiTokenPO1 = new HuaweiTokenPO();
            huaweiTokenPO1.setHuaweiToken(huaweiToken);
            huaweiTokenMapper.updateToken(huaweiTokenPO1);
            return huaweiTokenPO1;
        }
    }

}
