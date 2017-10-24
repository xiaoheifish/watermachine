package com.terabits.mapper;

import com.terabits.meta.po.WechatConsumePO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/10/22.
 */
public interface WechatConsumeMapper {
    /**
     * 插入一条微信消费下单数据
     * @param wechatConsumePO
     * @return
     * @throws Exception
     */
    int insertWechatConsume(WechatConsumePO wechatConsumePO) throws Exception;

    /**
     * 根据orderId更新tradeNo
     * @param tradeNo
     * @param orderId
     * @return
     * @throws Exception
     */
    int updatePaymentStatus(@Param("tradeNo")String tradeNo, @Param("orderId") String orderId) throws Exception;

    /**
     * 根据id更新orderId，用于生成不重复订单号
     * @param orderId
     * @param id
     * @return
     * @throws Exception
     */
    int updateOrderIdById(@Param("orderId")String orderId, @Param("id")int id) throws Exception;

    /**
     * 根据orderId更新consumeNo，用于之后可能需要的查询
     * @param consumeNo
     * @param orderId
     * @return
     * @throws Exception
     */
    int updateLastWechatConsume(@Param("consumeNo")String consumeNo, @Param("orderId")String orderId) throws Exception;

    /**
     * 查询某用户的最后一笔订单
     * @param openId
     * @return
     * @throws Exception
     */
    String selectLastWechatConsume(@Param("openId")String openId) throws Exception;

    /**
     * 根据orderId取回一笔订单，用于微信回调时判断tradeNo是否为空
     * @param orderId
     * @return
     * @throws Exception
     */
    WechatConsumePO selectWechatConsumeByOrderId(@Param("orderId") String orderId) throws Exception;

}
