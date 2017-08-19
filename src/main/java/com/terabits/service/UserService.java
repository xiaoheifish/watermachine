package com.terabits.service;


import com.terabits.meta.bo.WeixinUserBO;
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
     * 更新用户数据
     */
    public int updateInfo(UserPO userPO)throws Exception;

    /**
     * 根据openId更新余额
     */
    public int updateRemain(Double remain, String openId) throws Exception;

    /**
     * 根据openId更新手机号
     */
    public int updatePhone(String phone, String openId) throws Exception;

    /**
     * 根据openId查询某位用户的信息
     */
    public UserPO selectUser(String openId)throws Exception;

    /**
     *根据openId查询手机号，判断用户是否已经注册
     */
    public WeixinUserBO userRegistered(String openId)throws Exception;
}
