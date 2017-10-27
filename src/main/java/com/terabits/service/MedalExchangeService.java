package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.MedalExchangePO;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface MedalExchangeService {

    //插入一条兑换勋章数据
    public int insertMedalExchange(MedalExchangePO medalExchangePO) throws Exception;
    //根据openId和Timespan返回用户兑换勋章历史
    public List<MedalExchangePO> selectMedalExchange(String openId, TimeSpanBO timeSpanBO) throws Exception;
    //返回用户本月已兑换勋章的数目
    public int getMonthlyExchange(String openId);

}
