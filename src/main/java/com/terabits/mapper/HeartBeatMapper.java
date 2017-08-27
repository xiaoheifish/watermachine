package com.terabits.mapper;

import com.terabits.meta.po.HeartBeatPO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/8/27.
 */
public interface HeartBeatMapper {

    /**
     * 插入新增设备的心跳包
     * @param heartBeatPO
     * @return
     * @throws Exception
     */
    public int insertHeartBeat(HeartBeatPO heartBeatPO) throws Exception;

    /**
     * 更新设备心跳包的最后更新时间
     * @param deviceId
     * @return
     * @throws Exception
     */
    public int updateHeartBeat(@Param("deviceId")String deviceId) throws Exception;

    /**
     * 根据deviceId查询心跳包
     * @param deviceId
     * @return
     * @throws Exception
     */
    public HeartBeatPO selectHeartBeat(@Param("deviceId")String deviceId)throws Exception;



}
