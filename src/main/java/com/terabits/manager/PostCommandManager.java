package com.terabits.manager;

import com.terabits.config.Constants;
import com.terabits.mapper.ConsumeOrderMapper;
import com.terabits.meta.po.ConsumeOrderPO;
import com.terabits.service.ConsumeOrderService;
import com.terabits.service.impl.ConsumeOrderServiceImpl;
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
@Service
public class PostCommandManager {

    @Autowired
    private ConsumeOrderService consumeOrderService;

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
    public Executor executor = Executors.newFixedThreadPool(5);


    // 单元测试方法
    public void postCommand(final String displayId) {
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
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                System.out.println(tid + "时间前："+sdf.format(date) +consumeOrderPO);
                try {
                    Thread.sleep(5000L);
                }catch (Exception e){
                    e.printStackTrace();
                }
                date = new Date();
                System.out.println(tid + "时间后："+sdf.format(date) + consumeOrderPO);
            }
        };


        executor.execute(task);
        System.out.println("+1");
        executor.execute(task);
        System.out.println("+2");
        executor.execute(task);
        System.out.println("+3");
        executor.execute(task);
        System.out.println("+4");
        executor.execute(task);
        System.out.println("+5");
    }
}
