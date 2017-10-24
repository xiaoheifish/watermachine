package com.terabits.service;

import com.terabits.meta.po.PresentPO;

/**
 * Created by Administrator on 2017/10/21.
 */
public interface PresentService {
    //插入不同种类的赠送记录
    public int insertPresent(PresentPO presentPO) throws Exception;
}
