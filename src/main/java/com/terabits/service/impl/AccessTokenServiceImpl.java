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

    //插入新的token
    public int insertToken(AccessTokenPO accessTokenPO)throws Exception{
        return accessTokenMapper.insertToken(accessTokenPO);
    }

    //更新token
    public int updateToken(AccessTokenPO accessTokenPO)throws Exception{
        return accessTokenMapper.updateToken(accessTokenPO);
    }

    //取回最新有效的token
    public AccessTokenPO getLatestToken() throws Exception{
        AccessTokenPO accessTokenPO = null;
        try {
            accessTokenPO = accessTokenMapper.selectToken();
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
            accessTokenMapper.updateToken(accessTokenPO1);
            return accessTokenPO1;
        }
    }
}
