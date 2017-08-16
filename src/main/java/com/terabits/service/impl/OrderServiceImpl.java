package com.terabits.service.impl;

import com.terabits.mapper.OrderMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.OrderPO;
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
    private OrderMapper orderMapper;

    public int insertOrder(OrderPO orderPO) {
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

    public List<OrderPO> selectAllPayment(TimeSpanBO timeSpanBO) {
        List<OrderPO> orderPOS = new ArrayList<OrderPO>();
        try {
            orderPOS = orderMapper.selectAllPayment(timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderPOS;
    }

    public OrderPO selectPaymentByOrderId(String orderId){
        OrderPO orderPO = new OrderPO();
        try {
            orderPO = orderMapper.selectPaymentByOrderId(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderPO;
    }


}
