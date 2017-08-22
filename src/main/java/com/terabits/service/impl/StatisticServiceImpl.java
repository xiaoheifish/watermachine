package com.terabits.service.impl;

import com.terabits.mapper.StatisticMapper;
import com.terabits.meta.po.AuxcalPO;
import com.terabits.meta.po.TotalPO;
import com.terabits.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {
    @Autowired(required = false)
    private StatisticMapper statisticMapper;


    //auxcal////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取所有天的统计值
     */
    public List<AuxcalPO> selectAllAuxcal() throws Exception{
        return statisticMapper.selectAllAuxcal();
    }

    /**
     * 获取某一天的统计值
     */
    public AuxcalPO selectTodayAuxcal(String day) throws Exception{
        return statisticMapper.selectTodayAuxcal(day);
    }

    /**
     * 更新当天的统计值
     */
    public int updateTodayAuxcal(AuxcalPO auxcalPO) throws Exception{
        return statisticMapper.updateTodayAuxcal(auxcalPO);
    }

    //total/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 更新历史的统计值,加上对象中的对应值,用于充值情况
     */
    public int updateTotalRecharge(TotalPO totalPO) throws Exception{
        return statisticMapper.updateTotalRecharge(totalPO);
    }


    /**
     * 更新历史的统计值,减去对象中的对应值,用于消费情况
     */
    public int updateTotalConsume(TotalPO totalPO) throws Exception{
        return statisticMapper.updateTotalConsume(totalPO);
    }

    /**
     * 获取历史的统计值
     */
    public TotalPO selectTotal() throws Exception{
        return statisticMapper.selectTotal();
    }

}

