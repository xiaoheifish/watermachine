package com.terabits.service.impl;

import com.terabits.mapper.RechargeOrderMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.service.RechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
@Service("rechargeOrderService")
public class RechargeOrderServiceImpl implements RechargeOrderService {

    @Autowired(required = false)
    private RechargeOrderMapper orderMapper;

    public int insertOrder(RechargeOrderPO orderPO) throws Exception {

        return orderMapper.insertOrder(orderPO);
    }

    public int selectCountByTime(TimeSpanBO timeSpanBO) throws Exception {
        int result = 0;
        result = orderMapper.selectCountByTime(timeSpanBO);
        return result + 1;
    }

    public int updatePaymentStatus(String tradeNo, String orderId)throws Exception{
        return orderMapper.updatePaymentStatus(tradeNo, orderId);
    }

    public List<RechargeOrderPO> selectAllPayment(TimeSpanBO timeSpanBO) throws Exception {
        List<RechargeOrderPO> orderPOS = new ArrayList<RechargeOrderPO>();
        orderPOS = orderMapper.selectAllPayment(timeSpanBO);
        return orderPOS;
    }

    public RechargeOrderPO selectPaymentByOrderId(String orderId)throws Exception{
        RechargeOrderPO orderPO = new RechargeOrderPO();
        orderPO = orderMapper.selectPaymentByOrderId(orderId);
        return orderPO;
    }

    public List<RechargeOrderPO> selectPaymentByOpenId(String openId)throws Exception{
        List<RechargeOrderPO> rechargeOrderPOS = new ArrayList<RechargeOrderPO>();
        rechargeOrderPOS = orderMapper.selectPaymentByOpenId(openId);
        return rechargeOrderPOS;
    }
}
