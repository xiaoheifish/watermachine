package com.terabits.mapper;

import com.terabits.meta.po.AccessTokenPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface AccessTokenMapper {
    //插入新的token
    public int insertToken(AccessTokenPO accessTokenPO) throws Exception;
    //取回token
    public AccessTokenPO selectToken() throws Exception;
    //更新token
    public int updateToken(AccessTokenPO accessTokenPO) throws Exception;


}
