package com.terabits.service;

import com.terabits.meta.po.HuaweiTokenPO;

/**
 * Created by Administrator on 2017/8/18.
 */
public interface HuaweiTokenService {
    //插入第一个token
    public int insertToken(HuaweiTokenPO huaweiTokenPO) throws Exception;

    //更新token
    public int updateToken(HuaweiTokenPO huaweiTokenPO) throws Exception;

    //取出数据库中的huaweiToken，若存入时间和当前时间比较，小于7000秒，则重新获取一个token
    public HuaweiTokenPO getLatestToken() throws Exception;


}
