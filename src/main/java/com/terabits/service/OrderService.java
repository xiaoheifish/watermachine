package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RechargeOrderPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface OrderService {
    //插入订单数据
    public int insertOrder(RechargeOrderPO orderPO);

    //查询今日总订单数
    public int selectCountByTime(TimeSpanBO timeSpanBO);

    //微信支付成功后，更新tradeNo，若未更新则表明支付未成功
    public int updatePaymentStatus(String tradeNo, String orderId);

    //查询全部充值数据
    public List<RechargeOrderPO> selectAllPayment(TimeSpanBO timeSpanBO);

    //根据orderId查询Order
    public RechargeOrderPO selectPaymentByOrderId(String orderId);
}
