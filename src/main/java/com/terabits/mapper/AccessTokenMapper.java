package com.terabits.mapper;

import com.terabits.meta.po.AccessTokenPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface AccessTokenMapper {
    //插入新的token
    public int insertToken(AccessTokenPO accessTokenPO) throws Exception;
    //取回最后一个token
    public AccessTokenPO selectLastToken() throws Exception;
    //取回全部token
    public List<AccessTokenPO> selectAllToken() throws Exception;
}
