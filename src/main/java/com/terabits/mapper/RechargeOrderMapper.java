package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RechargeOrderPO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface RechargeOrderMapper {

    /**
     * 新增充值数据
     * @param orderPO
     * @return
     * @throws Exception
     */
    public int insertOrder(RechargeOrderPO orderPO) throws Exception;

    /**查询当日全部充值数量，计算交易单号
     *
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public int selectCountByTime(TimeSpanBO timeSpanBO) throws Exception;

    /**查询当日充值记录
     *
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<RechargeOrderPO> selectAllPayment(TimeSpanBO timeSpanBO) throws Exception;

    /**根据微信回调更新tradeNo，tradeNo不为空表明该笔交易成功
     *
     * @param tradeNo
     * @param orderId
     * @return
     * @throws Exception
     */
    public int updatePaymentStatus(@Param("tradeNo")String tradeNo, @Param("orderId")String orderId)throws Exception;

    /**根据orderId查询订单，主要为了查这笔交易的金额，与微信回调做比对，防止交易被篡改
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public RechargeOrderPO selectPaymentByOrderId(@Param("orderId")String orderId)throws Exception;

    /**查询某用户全部充值记录
     *
     * @param openId
     * @return
     * @throws Exception
     */
    public List<RechargeOrderPO> selectPaymentByOpenId(@Param("openId")String openId)throws Exception;

}
