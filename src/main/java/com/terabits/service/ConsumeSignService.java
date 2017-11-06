package com.terabits.service;

import com.terabits.meta.po.ConsumeSignPO;
import com.terabits.utils.ScoreRuleUtil;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface ConsumeSignService {

    public int insertConsumeSign(ConsumeSignPO consumeSignPO) throws Exception;

    public int updateConsumeSign(ConsumeSignPO consumeSignPO) throws Exception;

    public ConsumeSignPO selectConsumeSign(String openId) throws Exception;

    //根据openId和最后消费时间更新消费历史记录表
    public int updateSign(String openId, String gmtModified);

    //根据openId获取某个用户本月剩余的勋章数量和连续签到天数
    public JSONObject getRemainMedal(String openId);

    //根据openId以及兑换勋章请求，修改exchangeHistory
    public int pullExchangeRequest(String openId, List<ScoreRuleUtil.MedalType> medalType);
}
