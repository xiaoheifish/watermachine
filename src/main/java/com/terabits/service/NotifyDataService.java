package com.terabits.service;

import com.terabits.meta.po.NotifyDataPO;

/**
 * 所有设备发来的消息的原始数据存入此表
 * Created by Administrator on 2017/7/21.
 */
public interface NotifyDataService {
    public int insertNotifyData(NotifyDataPO notifyDataPO)throws Exception;
}
