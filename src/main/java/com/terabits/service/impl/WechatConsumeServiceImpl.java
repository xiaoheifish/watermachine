package com.terabits.service.impl;

import com.terabits.mapper.WechatConsumeMapper;
import com.terabits.meta.po.WechatConsumePO;
import com.terabits.service.WechatConsumeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/22.
 */
@Service("wechatConsumeService")
public class WechatConsumeServiceImpl implements WechatConsumeService {
    @Autowired(required = false)
    private WechatConsumeMapper wechatConsumeMapper;

    /**
     * 插入一条微信消费下单数据
     */
    public int insertWechatConsume(WechatConsumePO wechatConsumePO) throws Exception{
        return wechatConsumeMapper.insertWechatConsume(wechatConsumePO);
    }

    /**
     * 根据orderId更新tradeNo
     */
    public int updatePaymentStatus(String tradeNo, String orderId) throws Exception{
        return wechatConsumeMapper.updatePaymentStatus(tradeNo, orderId);
    }

    /**
     * 根据id更新orderId，用于生成不重复订单号
     */
    public int updateOrderIdById(String orderId, int id) throws Exception{
        return wechatConsumeMapper.updateOrderIdById(orderId, id);
    }

    /**
     * 根据orderId更新consumeNo，用于之后可能需要的查询
     */
    public int updateLastWechatConsume(String consumeNo, String orderId) throws Exception{
        return wechatConsumeMapper.updateLastWechatConsume(consumeNo, orderId);
    }

    /**
     * 查询某用户的最后一笔订单
     */
    public String selectLastWechatConsume(String openId) throws Exception{
        return wechatConsumeMapper.selectLastWechatConsume(openId);
    }

    /**
     * 根据orderId查询一笔订单
     */
    public WechatConsumePO selectWechatConsumeByOrderId(String orderId) throws Exception{
        return wechatConsumeMapper.selectWechatConsumeByOrderId(orderId);
    }

}
