package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.MedalExchangePO;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface MedalExchangeService {

    //插入一条兑换勋章数据
    public int insertMedalExchange(MedalExchangePO medalExchangePO) throws Exception;

    //根据openId和Timespan返回用户兑换勋章历史
    public List<MedalExchangePO> selectMedalExchange(String openId, TimeSpanBO timeSpanBO) throws Exception;

    //提交兑换勋章请求，需要在medalexchange表中记录此笔兑换请求，同时修改consumesign表中的exchangehistory列
    public int exchangeRequest(String openId, int number);

    //获取某用户本月的勋章兑换历史
    public JSONArray getMonthlyExchange(String openId);


}
