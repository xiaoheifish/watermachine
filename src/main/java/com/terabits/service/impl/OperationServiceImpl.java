package com.terabits.service.impl;

import com.terabits.mapper.OperationMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.OperationPO;
import com.terabits.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
@Service("operationService")
public class OperationServiceImpl implements OperationService{

    @Autowired(required = false)
    private OperationMapper operationMapper;

    public int insertOperation(OperationPO operationPO) {
        int result = 0;
        try{
            result = operationMapper.insertOperation(operationPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<OperationPO> selectOperation(TimeSpanBO timeSpanBO){
        List<OperationPO> operationPOS = new ArrayList<OperationPO>();
        try{
            operationPOS = operationMapper.selectOperation(timeSpanBO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return operationPOS;
    }
}
