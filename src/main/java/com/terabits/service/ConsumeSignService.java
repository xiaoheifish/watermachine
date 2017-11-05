package com.terabits.service;

import com.terabits.meta.po.ConsumeSignPO;
import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface ConsumeSignService {

    public int insertConsumeSign(ConsumeSignPO consumeSignPO) throws Exception;

    public int updateConsumeSign(ConsumeSignPO consumeSignPO) throws Exception;

    public ConsumeSignPO selectConsumeSign(String openId) throws Exception;

    //根据openId和最后消费时间更新消费历史记录表
    public int updateSign(String openId, String gmtModified);

    //根据openId获取某个用户本月剩余的勋章数量
    public JSONObject getRemainMedal(String openId);
}
