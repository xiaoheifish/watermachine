package com.terabits.service.impl;

import com.terabits.mapper.PresentMapper;
import com.terabits.meta.po.PresentPO;
import com.terabits.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/21.
 */
@Service("presentService")
public class PresentServiceImpl implements PresentService{
    @Autowired(required = false)
    private PresentMapper presentMapper;

    //插入不同种类的赠送记录
    public int insertPresent(PresentPO presentPO) throws Exception{
        return presentMapper.insertPresent(presentPO);
    }
}
