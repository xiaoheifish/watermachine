package com.terabits.service;

import com.terabits.meta.po.WechatConsumePO;

/**
 * Created by Administrator on 2017/10/22.
 */
public interface WechatConsumeService {

    /**
     * 插入一条微信消费下单数据
     */
    public int insertWechatConsume(WechatConsumePO wechatConsumePO) throws Exception;

    /**
     * 根据orderId更新tradeNo
     */
    public int updatePaymentStatus(String tradeNo, String orderId) throws Exception;

    /**
     * 根据id更新orderId，用于生成不重复订单号
     */
    public int updateOrderIdById(String orderId, int id) throws Exception;

    /**
     * 根据orderId更新consumeNo，用于之后可能需要的查询
     */
    public int updateLastWechatConsume(String consumeNo, String orderId) throws Exception;

    /**
     * 查询某用户的最后一笔订单
     */
    public String selectLastWechatConsume(String openId) throws Exception;

    /**
     * 根据orderId查询一笔订单
     */
    public WechatConsumePO selectWechatConsumeByOrderId(String orderId) throws Exception;


}
