package com.terabits.service;

import com.terabits.meta.model.CommandNoModel;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface CredentialService {
    //插入指令编号，永久有效

    public void createCommand(CommandNoModel commandNoModel);
    //获取某个编号数值
    public String getCommandNo(String commandId);

    //根据deviceId更新时间，每次一个设备被重新使用时间都需要更新
    public void updateDeviceTime(String deviceId);

    //获取当前全部在使用的设备
    public Map<Object, Object> getCurentDevice();

    //删除已过期的设备
    public void deleteExpireDevice(String deviceId);

    //插入某退款用户的openId, 30min过期
    public void createRefundUser(String openId);


    //查询某个退款用户是否在redis缓存中,若在,则不允许此次退款操作
    public String getRefundUserTime(String openId);

    // 查询指令编号并更新
    public List<String> UpdateCommandNo();

}
