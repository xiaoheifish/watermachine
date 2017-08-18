package com.terabits.service;


import com.terabits.meta.po.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface UserService {
    /**
     * 新增用户数据
     */
    public int insertUser(UserPO userPO) throws Exception;

    /**
     * 根据openId更新余额
     */
    public int updateRemain(Double remain, String openId) throws Exception;

    /**
     * 根据openId更新手机号
     */
    public int updatePhone(String phone, String openId) throws Exception;
}
