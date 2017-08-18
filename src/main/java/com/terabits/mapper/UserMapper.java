package com.terabits.mapper;

import com.terabits.meta.po.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface UserMapper
{
    /**
     * 新增用户数据
     * @param userPO
     * @return
     * @throws Exception
     */
    public int insertUser(UserPO userPO) throws Exception;

    /**
     * 根据openId更新余额
     * @param remain
     * @param openId
     * @return
     * @throws Exception
     */
    public int updateRemain(@Param("remain")Double remain, @Param("openId")String openId) throws Exception;

    /**
     * 根据openId更新手机号
     * @param phone
     * @param openId
     * @return
     * @throws Exception
     */
    public int updatePhone(@Param("phone")String phone, @Param("openId")String openId) throws Exception;
}
