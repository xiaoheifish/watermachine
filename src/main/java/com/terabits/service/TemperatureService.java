package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.TemperaturePO;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface TemperatureService {

    //插入一条温度数据
    public int insertTemperature(TemperaturePO temperaturePO);
    //根据imei号查取该终端的全部温度数据
    public List<TemperaturePO> selectOneTemperature(String imei);
    //根据时间查询该时间段内的全部温度数据
    public List<TemperaturePO> selectAllTemperature(TimeSpanBO timeSpanBO);

}
