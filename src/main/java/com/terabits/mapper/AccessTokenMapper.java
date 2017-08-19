package com.terabits.mapper;

import com.terabits.meta.po.AccessTokenPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface AccessTokenMapper {
    /**插入新的token
     *
     * @param accessTokenPO
     * @return
     * @throws Exception
     */
    public int insertToken(AccessTokenPO accessTokenPO) throws Exception;

    /**取回token
     *
     * @return
     * @throws Exception
     */
    public AccessTokenPO selectToken() throws Exception;

    /**更新token
     *
     * @param accessTokenPO
     * @return
     * @throws Exception
     */
    public int updateToken(AccessTokenPO accessTokenPO) throws Exception;


}
