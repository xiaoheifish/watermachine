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

    //查询当日全部充值数量
    public int selectCountByTime(TimeSpanBO timeSpanBO) throws Exception;

    //查询充值记录
    public List<RechargeOrderPO> selectAllPayment(TimeSpanBO timeSpanBO) throws Exception;

    //根据微信回调更新tradeNo，tradeNo不为空表明该笔交易成功
    public int updatePaymentStatus(@Param("tradeNo")String tradeNo, @Param("orderId")String orderId);

    //根据orderId查询订单
    public RechargeOrderPO selectPaymentByOrderId(@Param("orderId")String orderId);

}
