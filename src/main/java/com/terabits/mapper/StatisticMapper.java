package com.terabits.mapper;

import com.terabits.meta.po.AuxcalPO;
import com.terabits.meta.po.TotalPO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticMapper {

    //auxcal////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取所有天的统计值
     */
    public List<AuxcalPO> selectAllAuxcal() throws Exception;

    /**
     * 获取某一天的统计值
     */
    public AuxcalPO selectTodayAuxcal(@Param("day") String day) throws Exception;

    /**
     * 更新当天的统计值
     */
    public int updateTodayAuxcal(AuxcalPO auxcalPO) throws Exception;

    //total/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 更新历史的统计值,加上对象中的对应值,用于充值情况
     */
    public int updateTotalRecharge(TotalPO totalPO) throws Exception;

    /**
     * 更新历史的统计值,减去对象中的对应值,用于消费情况
     */
    public int updateTotalConsume(TotalPO totalPO) throws Exception;

    /**
     * 获取历史的统计值
     */
    public TotalPO selectTotal() throws Exception;


}
