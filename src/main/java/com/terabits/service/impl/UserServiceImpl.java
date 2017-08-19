package com.terabits.service.impl;


import com.terabits.mapper.UserMapper;
import com.terabits.meta.bo.WeixinUserBO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 新增用户数据
     */
    public int insertUser(UserPO userPO) throws Exception{
        return userMapper.insertUser(userPO);
    }

    /**
     *更新用户数据
     */
    public int updateInfo(UserPO userPO)throws Exception{
        return userMapper.updateInfo(userPO);
    }

    /**
     * 根据openId更新余额
     */
    public int updateRemain(Double remain, String openId) throws Exception{
        return userMapper.updateRemain(remain, openId);
    }

    /**
     * 根据openId更新手机号
     */
    public int updatePhone(String phone, String openId) throws Exception{
        return userMapper.updatePhone(phone, openId);
    }

    /**
     * 根据openId查询某位用户的信息
     */
    public UserPO selectUser(String openId)throws Exception{
        return userMapper.selectUser(openId);
    }

    /**
     *根据openId查询手机号，判断用户是否已经注册
     */
    public WeixinUserBO userRegistered(String openId)throws Exception{
        return userMapper.userRegistered(openId);
    }

}
