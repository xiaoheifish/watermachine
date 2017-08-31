package com.terabits.service;

import com.terabits.meta.model.CommandNoModel;
import org.springframework.data.redis.core.HashOperations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

}
