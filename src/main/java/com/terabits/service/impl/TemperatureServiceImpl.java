package com.terabits.service.impl;

import com.terabits.mapper.TemperatureMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.TemperaturePO;
import com.terabits.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
@Service("temperatureService")
public class TemperatureServiceImpl implements TemperatureService {
    @Autowired(required = false)
    private TemperatureMapper temperatureMapper;
    public int insertTemperature(TemperaturePO temperaturePO){
        int result = 0;
        try {
            result = temperatureMapper.insertTemperature(temperaturePO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<TemperaturePO> selectOneTemperature(String imei){
        List<TemperaturePO> temperaturePOS = new ArrayList<TemperaturePO>();
        try{
            temperaturePOS = temperatureMapper.selectOneTemperature(imei);
        }catch (Exception e){
            e.printStackTrace();
        }
        return temperaturePOS;
    }

    public List<TemperaturePO> selectAllTemperature(TimeSpanBO timeSpanBO){
        List<TemperaturePO> temperaturePOS = new ArrayList<TemperaturePO>();
        try{
            temperaturePOS = temperatureMapper.selectAllTemperature(timeSpanBO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return temperaturePOS;
    }
}
