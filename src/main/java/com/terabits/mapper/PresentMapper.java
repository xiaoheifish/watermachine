package com.terabits.mapper;

import com.terabits.meta.po.PresentPO;

/**
 * Created by Administrator on 2017/10/21.
 */
public interface PresentMapper {
    /**
     * 插入不同种类的赠送数据
     * @param presentPO
     * @return
     * @throws Exception
     */
    public int insertPresent(PresentPO presentPO) throws Exception;

}
