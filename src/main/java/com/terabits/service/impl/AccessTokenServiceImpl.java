package com.terabits.service.impl;

import com.terabits.mapper.AccessTokenMapper;
import com.terabits.meta.bo.AccessTokenBO;
import com.terabits.meta.po.AccessTokenPO;
import com.terabits.service.AccessTokenService;
import com.terabits.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.terabits.config.WeixinGlobal.APP_ID;
import static com.terabits.config.WeixinGlobal.APP_SECRET;

/**
 * Created by Administrator on 2017/6/28.
 */
@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired(required = false)
    private AccessTokenMapper accessTokenMapper;

    public int insertToken(AccessTokenPO accessTokenPO){
        int result = 0;
        try {
            result = accessTokenMapper.insertToken(accessTokenPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public AccessTokenPO getLatestToken(){
        AccessTokenPO accessTokenPO = null;
        try {
            accessTokenPO = accessTokenMapper.selectLastToken();
        }catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
            Date begin =dfs.parse(accessTokenPO.getGmtCreate());
            Date end = new Date();
            between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
            System.out.println(between);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(between < 7000){
            return accessTokenPO;
        }
        else {
            AccessTokenBO accessTokenBO = WeixinUtil.getAccessToken(APP_ID, APP_SECRET);
            AccessTokenPO accessTokenPO1 = new AccessTokenPO();
            accessTokenPO1.setAccessToken(accessTokenBO.getToken());
            insertToken(accessTokenPO1);
            return accessTokenPO1;
        }
    }
}
