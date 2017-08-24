package com.terabits.manager;

import com.terabits.config.Constants;
import com.terabits.controller.ConsumeController;
import com.terabits.mapper.ConsumeOrderMapper;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.impl.ConsumeOrderServiceImpl;
import com.terabits.utils.FlowUtil;
import com.terabits.utils.huawei.PlatformGlobal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/8/22.
 */
@Service("postCommandManager")
public class PostCommandManager {

    @Autowired
    private ConsumeOrderService consumeOrderService;
    
    private static Logger logger = LoggerFactory
            .getLogger(PostCommandManager.class);

  /*  public void getPO(String displayId){
        ConsumeOrderPO consumeOrderPO = new ConsumeOrderPO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("thread:::"+consumeOrderPO + sdf.format(date));
        try{
            Thread.sleep(5000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("thread:::"+consumeOrderPO + sdf.format(new Date()));
        try{
            Thread.sleep(5000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("thread:::"+consumeOrderPO + sdf.format(new Date()));
    }*/
    // 固定大小为2的线程池
    public Executor executor = Executors.newFixedThreadPool(1);


    // 单元测试方法
    public void postCommand(final byte[] openbytes, final String displayId, final String deviceId) {
        final Runnable task = new Runnable() {

            public void run(){
                // 线程id
                long tid = Thread.currentThread().getId();
                ConsumeOrderPO consumeOrderPO = new ConsumeOrderPO();
                try {
                    System.out.println("displayId:::" + displayId);
                    consumeOrderPO = consumeOrderService.selectLastConsumption(displayId);
                }catch (Exception e){
                    e.printStackTrace();
                } Date now = new Date();
                SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time1 = dfs.format(now);  
             
           /*   byte[] openbytes = new byte[6];
                openbytes[0] = Constants.SEND_COMMAND_START;
                openbytes[1] = Constants.POWER_ON_COMMAND;
                openbytes[2] = Constants.TWO_M_WATER;
                openbytes[3] = Constants.COMMAND_ONE;
                openbytes[4] = Constants.COMMAND_TWO;
                openbytes[5] = Constants.SEND_COMMAND_END;*/
                try {
                    PlatformGlobal.command(openbytes, deviceId);
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(8000L);
                }catch (Exception e){
                    e.printStackTrace();
                }
                now = new Date();
                String time2 = dfs.format(now);
                logger.error("to huaweiplatform power on ok: " + time1 + " " + time2);
            }
        };


        executor.execute(task);
        System.out.println("+1");

    }
}
