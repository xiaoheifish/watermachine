package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.RefundRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface RefundRecordMapper {

    /**
     * 插入一条退款数据
     * @param refundRecordPO
     * @return
     * @throws Exception
     */
    int insertRefund(RefundRecordPO refundRecordPO) throws Exception;

    /**
     * 根据时间段查询退款数据
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    List<RefundRecordPO> selectAllRefund(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 根据refundNo更新paymentNo,其中refundNo是平台生成的，paymentNo是微信生成的
     * @param paymentNo
     * @param refundNo
     * @return
     * @throws Exception
     */
    int updateRefundStatus(@Param("paymentNo") String paymentNo, @Param("refundNo") String refundNo) throws Exception;

    /**
     * 根据openId查询某位用户的退款情况
     * @param openId
     * @return
     * @throws Exception
     */
    List<RefundRecordPO> selectRefundByOpenId(@Param("openId") String openId) throws Exception;

    /**
     * 根据id更新refundNo，用于生成平台的退款订单号
     * @param refundNo
     * @param id
     * @return
     * @throws Exception
     */
    int updateRefundNoById(@Param("refundNo") String refundNo, @Param("id") int id) throws Exception;

}
