package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.ConsumeOrderPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface ConsumeOrderMapper {

    /**
     * 新增消费数据,最初插入的时候不带orderno，根据返回的id去生成orderno，再更新一下
     * @param consumeOrderPO
     * @return
     * @throws Exception
     */
    public int insertOrder(ConsumeOrderPO consumeOrderPO)throws Exception;

    /**
     * 根据insertOrder返回的id，生成orderNo，更新这笔订单的orderNo，比计算当日总消费笔数要好一些
     * @param orderNo
     * @param id
     * @return
     * @throws Exception
     */
    public int updateOrderNoById(@Param("orderNo")String orderNo, @Param("id")int id)  throws Exception;


    /**查询当日全部消费数量，计算消费单号
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public int selectCountByTime(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 根据时间查询消费记录
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectAllConsumption(TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 查询某设备对应的全部消费
     * @param displayId
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectConsumptionByDisplayId(@Param("displayId")String displayId)throws Exception;

    /**
     * 查询某设备对应的最后一笔消费
     * @param displayId
     * @return
     * @throws Exception
     */
    public ConsumeOrderPO selectLastConsumption(@Param("displayId")String displayId)throws Exception;

    /**
     * 查询某用户对应的全部消费
     * @param openId
     * @return
     * @throws Exception
     */
    public List<ConsumeOrderPO> selectConsumptionByOpenId(@Param("openId")String openId)throws Exception;

    /**
     * 根据orderNo更新state,需要先通过displayId取出最后一笔消费
     * @param orderNo
     * @return
     * @throws Exception
     */
    public int updateStateById(@Param("orderNo")String orderNo)throws Exception;

}
