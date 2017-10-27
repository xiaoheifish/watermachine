package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.MedalExchangePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface MedalExchangeMapper {

    /**
     * 插入兑换勋章数据
     * @param medalExchangePO
     * @return
     * @throws Exception
     */
    int insertMedalExchange(MedalExchangePO medalExchangePO) throws Exception;

    /**
     * 根据openId查询兑换勋章历史
     * @param openId
     * @return
     * @throws Exception
     */
    List<MedalExchangePO> selectMedalExchange(@Param("openId")String openId, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;


}
