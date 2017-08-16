package com.terabits.mapper;

import com.terabits.meta.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface UserMapper
{
    public User getUser(@Param("id") int id);
    public int insertUser(List<User> userList);
}
