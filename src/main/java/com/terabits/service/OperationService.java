package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.OperationPO;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface OperationService {

    //在每次插座开断的时候插入状态的变化
    public int insertOperation(OperationPO operationPO);
    //根据时段查看所有的变换
    public List<OperationPO> selectOperation(TimeSpanBO timeSpanBO);
}
