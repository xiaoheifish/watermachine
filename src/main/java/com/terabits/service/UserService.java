package com.terabits.service;


import com.terabits.meta.po.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface UserService {
    //public void setUserMapper(UserMapper userMapper);
    public User getUser(int id);
    public int insertUser(List<User> userList);
}
