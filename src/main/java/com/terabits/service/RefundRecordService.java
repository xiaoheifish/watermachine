package com.terabits.service;

import com.terabits.config.Constants;
import com.terabits.meta.bo.ConsumeRefundBO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RefundRecordPO;
import com.terabits.utils.GenerateOrderId;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface RefundRecordService {

    //插入一条退款数据，并返回数据编号
    public int insertRefund(String openId, double money, double remain) throws Exception;

    //根据id更新平台订单编号
    public String  updateRefundNoById(int id) throws Exception;

    //根据时间段查询退款数据
    public List<RefundRecordPO> selectAllRefund(TimeSpanBO timeSpanBO) throws Exception;

    //根据refundNo更新paymentNo
    public int updateRefundStatus(String paymentNo, String refundNo) throws Exception;

    //查询某位用户的退款情况
    public List<RefundRecordPO> selectRefundByOpenId(String openId) throws Exception;

    //微信支付消费下单，开启设备失败时，插入一条退款数据
    public int insertConsumeRefund(ConsumeRefundBO consumeRefundBO) throws Exception;

    //查找待退款记录
    public List<RefundRecordPO> selectUnsolveRefund() throws Exception;

}
