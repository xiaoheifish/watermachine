package com.terabits.service.impl;

import com.terabits.mapper.NotifyDataMapper;
import com.terabits.meta.po.NotifyDataPO;
import com.terabits.service.NotifyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service("notifyDataService")
public class NotifyDataServiceImpl implements NotifyDataService
{
    @Autowired(required = false)
    private NotifyDataMapper notifyDataMapper;
    public int insertNotifyData(NotifyDataPO notifyDataPO)throws Exception{
        int result = 0;
       /* try {
            result = notifyDataMapper.insertNotifyData(notifyDataPO);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        result = notifyDataMapper.insertNotifyData(notifyDataPO);
        return result;
    }
}
