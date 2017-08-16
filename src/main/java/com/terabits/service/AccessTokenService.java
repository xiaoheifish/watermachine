package com.terabits.service;

import com.terabits.meta.po.AccessTokenPO;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface AccessTokenService {

    //插入accesstoken
    public int insertToken(AccessTokenPO accessTokenPO);

    //获取最新的accesstoken，先从数据库中拿，若过期则重新获取
    public AccessTokenPO getLatestToken();
}
