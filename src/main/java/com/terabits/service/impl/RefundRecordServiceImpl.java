package com.terabits.service.impl;

import com.terabits.config.Constants;
import com.terabits.controller.ConsumeController;
import com.terabits.mapper.RefundRecordMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RefundRecordPO;
import com.terabits.service.RefundRecordService;
import com.terabits.utils.GenerateOrderId;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service("refundRecordService")
public class RefundRecordServiceImpl implements RefundRecordService{

    @Autowired(required = false)
    private RefundRecordMapper refundRecordMapper;

    private static Logger logger = LoggerFactory
            .getLogger(RefundRecordServiceImpl.class);
    //插入一条退款数据，并返回数据编号
    public int insertRefund(String openId, double money) throws Exception{
        RefundRecordPO refundRecordPO = new RefundRecordPO();
        refundRecordPO.setMoney(money);
        refundRecordPO.setOpenId(openId);
        refundRecordPO.setState(Constants.IN_HAND);
        try{
            refundRecordMapper.insertRefund(refundRecordPO);
        }catch (Exception e){
            logger.error("insertRefund error in refundRecordServiceImpl" + refundRecordPO);
        }
        return refundRecordPO.getId();
    }
    //根据id更新平台订单编号
    public String updateRefundNoById(int id) throws Exception{
        String refundNo = GenerateOrderId.generateRefundId(id);
        refundRecordMapper.updateRefundNoById(refundNo, id);
        return refundNo;
    }

    //根据时间段查询退款数据
    public List<RefundRecordPO> selectAllRefund(TimeSpanBO timeSpanBO) throws Exception{
        return refundRecordMapper.selectAllRefund(timeSpanBO);
    }

    //根据refundNo更新paymentNo
    public int updateRefundStatus(String paymentNo, String refundNo) throws Exception{
        return refundRecordMapper.updateRefundStatus(paymentNo, refundNo);
    }

    //查询某位用户的退款情况
    public List<RefundRecordPO> selectRefundByOpenId(String openId) throws Exception{
        return refundRecordMapper.selectRefundByOpenId(openId);
    }


}
