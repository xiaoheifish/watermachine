package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.TemperaturePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface TemperatureMapper {

    /**
     * 新增温度数据
     * @param
     * @return
     * @throws Exception
     */
    public int insertTemperature(TemperaturePO temperaturePO) throws Exception;

    //查询一个终端的全部温度数据
    public List<TemperaturePO> selectOneTemperature(@Param("imei") String imei) throws Exception;

    //根据时间查询全部终端对应的温度数据
    public List<TemperaturePO> selectAllTemperature(TimeSpanBO timeSpanBO)throws Exception;


}
