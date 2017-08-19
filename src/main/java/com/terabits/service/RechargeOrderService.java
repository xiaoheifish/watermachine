package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RechargeOrderPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface RechargeOrderService {

    //插入充值订单数据
    public int insertOrder(RechargeOrderPO orderPO)throws Exception;

    //查询今日总充值订单数
    public int selectCountByTime(TimeSpanBO timeSpanBO)throws Exception;

    //微信支付成功后，更新tradeNo，若未更新则表明支付未成功
    public int updatePaymentStatus(String tradeNo, String orderId)throws Exception;

    //查询全部充值数据
    public List<RechargeOrderPO> selectAllPayment(TimeSpanBO timeSpanBO)throws Exception;

    //根据orderId查询RechargeOrder，主要目的是比对金额，防止交易被篡改
    public RechargeOrderPO selectPaymentByOrderId(String orderId)throws Exception;

    //根据openId查询RechargeOrder,查询某用户的全部充值记录
    public List<RechargeOrderPO> selectPaymentByOpenId(String openId)throws Exception;
}
