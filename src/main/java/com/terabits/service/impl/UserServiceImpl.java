package com.terabits.service.impl;


import com.terabits.mapper.UserMapper;
import com.terabits.meta.po.User;
import com.terabits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    public User getUser(int id){
        return userMapper.getUser(id);
    }

    public int insertUser(List<User> userList){
        return userMapper.insertUser(userList);
    }
}
