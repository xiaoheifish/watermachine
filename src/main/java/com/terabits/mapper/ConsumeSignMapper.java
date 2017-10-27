package com.terabits.mapper;

import com.terabits.meta.po.ConsumeSignPO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface ConsumeSignMapper {

    /**
     * 插入每个用户的第一笔消费签到信息
     * @param consumeSignPO
     * @return
     * @throws Exception
     */
    int insertConsumeSign(ConsumeSignPO consumeSignPO) throws Exception;

    /**
     * 更新签到信息，包括本月签到历史，连续签到天数以及最后签到时间
     * @param consumeSignPO
     * @return
     * @throws Exception
     */
    int updateConsumeSign(ConsumeSignPO consumeSignPO) throws Exception;

    /**
     * 根据用户openId查询签到情况
     * @param openId
     * @return
     * @throws Exception
     */
    ConsumeSignPO selectConsumeSign(@Param("openId")String openId) throws Exception;


}
