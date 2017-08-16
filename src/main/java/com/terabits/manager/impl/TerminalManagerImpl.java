package com.terabits.manager.impl;


import com.terabits.manager.TerminalManager;
import com.terabits.meta.model.TerminalModel;
import com.terabits.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/29.
 */
@Component
public class TerminalManagerImpl implements TerminalManager {
    private RedisTemplate<Long, String> redis;

   /* public static void main(String[] args){
        System.out.println(secToTime(7200));
    }*/
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public void createNewTerminal(TerminalModel terminalModel) {
        //存储到redis并设置过期时间
        //Long terminalId = terminalModel.getTerminalId();
        //redis.boundValueOps().set(terminalModel.getTime(), terminalModel.getHour(), TimeUnit.HOURS);
    }

    public String getTerminalTime(long terminalId) throws Exception{
        String time = null;
        try {
            time = redis.boundValueOps(terminalId).get();
        }catch (Exception e){
            return null;
        }
        return time;
    }

    public String getLeftTime(long terminalId){
        long lefttime = redis.getExpire(terminalId);
        return TimeUtils.secToTime(lefttime/1000);
    }
    public void deleteTerminal(long terminalId) {
        redis.delete(terminalId);
    }



}
