package com.terabits.service;

import com.terabits.meta.po.HeartBeatPO;

/**
 * Created by Administrator on 2017/8/27.
 */
public interface HeartBeatService {
    /*
    插入第一次上电设备的心跳包数据
    */
    public int insertHeartBeat(HeartBeatPO heartBeatPO) throws Exception;

    /*
    根据deviceId更新心跳包
     */
    public int updateHeartBeat(String deviceId) throws Exception;

    /*
    根据deviceId取出心跳包，将gmtModified与当前时间比较，若大于120s，则表明丢失上次心跳包，设备很可能已经掉线，所以判断为禁止使用状态
     */
    public HeartBeatPO selectHeartBeat(String deviceId) throws Exception;


}
