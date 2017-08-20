package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.ConsumeOrderPO;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface ConsumeOrderService {

    /**
     *新增消费数据
     */
    public int insertOrder(ConsumeOrderPO consumeOrderPO)throws Exception;

    /**
     *查询当日全部消费笔数，用于生成消费订单号
     */
    public int selectCountByTime(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 根据时间查询消费记录
     */
    public List<ConsumeOrderPO> selectAllConsumption(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 查询某设备对应的全部消费
     */
    public List<ConsumeOrderPO> selectConsumptionByDisplayId(String displayId)throws Exception;

    /**
     * 查询某设备对应的最后一笔消费
     */
    public ConsumeOrderPO selectLastConsumption(String displayId)throws Exception;

    /**
     * 查询某用户对应的全部消费
     */
    public List<ConsumeOrderPO> selectConsumptionByOpenId(String openId)throws Exception;

    /**
     *根据orderNo更新订单状态，用于接收到硬件回复时更新订单状态
     */
    public int updateStateById(String orderNo)throws Exception;


}
