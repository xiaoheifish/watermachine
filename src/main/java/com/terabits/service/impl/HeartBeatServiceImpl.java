package com.terabits.service.impl;

import com.terabits.mapper.HeartBeatMapper;
import com.terabits.meta.po.HeartBeatPO;
import com.terabits.service.HeartBeatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/27.
 */
@Service("heartBeatService")
public class HeartBeatServiceImpl implements HeartBeatService{
	
	private static Logger logger = LoggerFactory.getLogger(HeartBeatServiceImpl.class);

    @Autowired(required = false)
    private HeartBeatMapper heartBeatMapper;
    /*
    插入第一次上电设备的心跳包数据
     */
    public int insertHeartBeat(HeartBeatPO heartBeatPO) throws Exception{
        return heartBeatMapper.insertHeartBeat(heartBeatPO);
    }
    /*
    根据deviceId更新心跳包
     */
    public int updateHeartBeat(String deviceId) throws Exception{
        return heartBeatMapper.updateHeartBeat(deviceId);
    }
    /*
    根据deviceId取出心跳包，将gmtModified与当前时间比较，若大于120s，则表明丢失上次心跳包，设备很可能已经掉线，所以判断为禁止使用状态
     */
    public HeartBeatPO selectHeartBeat(String deviceId) throws Exception{
    	logger.error("[op:selectHeartBeat] print here");
        return heartBeatMapper.selectHeartBeat(deviceId);
    }

}
