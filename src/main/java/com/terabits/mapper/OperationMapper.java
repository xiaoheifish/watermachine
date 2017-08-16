package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.OperationPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface OperationMapper {
    /**
     * 新增操作数据
     * @param operationPO
     * @return
     * @throws Exception
     */
    public int insertOperation(OperationPO operationPO) throws Exception;

    //查询操作数据
    public List<OperationPO> selectOperation(TimeSpanBO timeSpanBO) throws Exception;

}
