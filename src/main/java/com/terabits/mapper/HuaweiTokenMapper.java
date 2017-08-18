package com.terabits.mapper;

import com.terabits.meta.po.HuaweiTokenPO;

/**
 * Created by Administrator on 2017/8/18.
 */
public interface HuaweiTokenMapper {

    //huaweiToken///////////////////////////////////////////////////////////
    /**
     * 插入华为Token
     * */
    public int insertToken(HuaweiTokenPO huaweiTokenPO) throws Exception;

    /**更新华为Token*/
    public int updateToken(HuaweiTokenPO huaweiToken) throws Exception;

    /**取出华为Token*/
    public HuaweiTokenPO selectToken() throws Exception;

}
