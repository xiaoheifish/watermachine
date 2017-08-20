package com.terabits.service.impl;

import com.terabits.mapper.ConsumeOrderMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.service.ConsumeOrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
@Service("consumeOrderService")
public class ConsumeOrderServiceImpl implements ConsumeOrderService {

    @Autowired(required = false)
    private ConsumeOrderMapper consumeOrderMapper;

    /**
     *新增消费数据
     */
    public int insertOrder(ConsumeOrderPO consumeOrderPO)throws Exception{
        return consumeOrderMapper.insertOrder(consumeOrderPO);
    }

    /**
     *查询当日全部消费笔数，用于生成消费订单号
     */
    public int selectCountByTime(TimeSpanBO timeSpanBO) throws Exception{
        int result = 0;
        result = consumeOrderMapper.selectCountByTime(timeSpanBO) + 1;
        return result;
    }

    /**
     * 根据时间查询消费记录
     */
    public List<ConsumeOrderPO> selectAllConsumption(TimeSpanBO timeSpanBO) throws Exception{
        return consumeOrderMapper.selectAllConsumption(timeSpanBO);
    }

    /**
     * 查询某设备对应的全部消费
     */
    public List<ConsumeOrderPO> selectConsumptionByDisplayId(String displayId)throws Exception{
        return consumeOrderMapper.selectConsumptionByDisplayId(displayId);
    }

    /**
     * 查询某设备对应的最后一笔消费
     */
    public ConsumeOrderPO selectLastConsumption(String displayId)throws Exception{
        return consumeOrderMapper.selectLastConsumption(displayId);
    }

    /**
     * 查询某用户对应的全部消费
     */
    public List<ConsumeOrderPO> selectConsumptionByOpenId(String openId)throws Exception{
        return consumeOrderMapper.selectConsumptionByOpenId(openId);
    }

    /**
     *根据orderNo更新订单状态，用于接收到硬件回复时更新订单状态
     */
    public int updateStateById(String orderNo)throws Exception{
        return consumeOrderMapper.updateStateById(orderNo);
    }


}
