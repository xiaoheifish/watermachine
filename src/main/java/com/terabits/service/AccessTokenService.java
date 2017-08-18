package com.terabits.service;

import com.terabits.meta.po.AccessTokenPO;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface AccessTokenService {

    //插入新的token
    public int insertToken(AccessTokenPO accessTokenPO) throws Exception;

    //更新token
    public int updateToken(AccessTokenPO accessTokenPO) throws Exception;

    //取回最新有效的token
    public AccessTokenPO getLatestToken() throws Exception;
}
