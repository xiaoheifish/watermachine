package com.terabits.service.impl;

import com.terabits.mapper.RechargeOrderMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RechargeOrderPO;
import com.terabits.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private RechargeOrderMapper orderMapper;

    public int insertOrder(RechargeOrderPO orderPO) {
        int result = 0;
        try {
            result = orderMapper.insertOrder(orderPO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectCountByTime(TimeSpanBO timeSpanBO) {
        int result = 0;
        try {
            result = orderMapper.selectCountByTime(timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result + 1;
    }

    public int updatePaymentStatus(String tradeNo, String orderId) {
        int result = 0;
        try {
            result = orderMapper.updatePaymentStatus(tradeNo, orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<RechargeOrderPO> selectAllPayment(TimeSpanBO timeSpanBO) {
        List<RechargeOrderPO> orderPOS = new ArrayList<RechargeOrderPO>();
        try {
            orderPOS = orderMapper.selectAllPayment(timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderPOS;
    }

    public RechargeOrderPO selectPaymentByOrderId(String orderId){
        RechargeOrderPO orderPO = new RechargeOrderPO();
        try {
            orderPO = orderMapper.selectPaymentByOrderId(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderPO;
    }


}
