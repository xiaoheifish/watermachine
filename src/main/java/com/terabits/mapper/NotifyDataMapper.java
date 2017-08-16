package com.terabits.mapper;

import com.terabits.meta.po.NotifyDataPO;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface NotifyDataMapper {
    //插入新的notifyData
    public int insertNotifyData(NotifyDataPO notifyDataPO) throws Exception;
}
