package com.terabits.service.impl;

import com.terabits.meta.model.CommandNoModel;
import com.terabits.meta.model.Demo;
import com.terabits.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/20.
 */
@Component
public class CredentialServiceImpl implements CredentialService{
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    //插入指令编号，永久有效

    public void createCommand(CommandNoModel commandNoModel) {

        ValueOperations<String, String> stringOperations = redisTemplate
                .opsForValue();
        //String类型数据存储，设置过期时间，采用TimeUnit控制时间单位
        stringOperations.set(commandNoModel.getCommandId(), commandNoModel.getNumber());

    }
    //获取某个编号数值
    public String getCommandNo(String commandId){
        ValueOperations<String, String> stringOperations = redisTemplate
                .opsForValue();
        String number = stringOperations.get(commandId);
        return number;
    }

    //将最新收到回复的设备插入device hash表中，key为deviceId, value为当前时间
    public void updateDeviceTime(String deviceId){

        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();
        Map<String, Long> map = new HashMap<String, Long>();
        Date now = new Date();
        map.put(deviceId, now.getTime());
        hashOperations.putAll("device", map);
    }

    public Map<Object, Object> getCurentDevice(){
        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();
        return hashOperations.entries("device");
    }

    public void deleteExpireDevice(String deviceId){
        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();
        hashOperations.delete("device",deviceId);
    }

    //插入某退款用户的openId, 30min过期
    public void createRefundUser(String openId) {

        ValueOperations<String, String> stringOperations = redisTemplate
                .opsForValue();
        //String类型数据存储，设置过期时间，采用TimeUnit控制时间单位
        Date now = new Date();
        stringOperations.set(openId, String.valueOf(now.getTime() / 1000) , 30, TimeUnit.MINUTES);

    }

    //查询某个退款用户是否在redis缓存中,若在,则不允许此次退款操作
    public String getRefundUserTime(String openId){
        ValueOperations<String, String> stringOperations = redisTemplate
                .opsForValue();
        String value1 = stringOperations.get(openId);
        return value1;
    }

    // 查询指令编号并更新
    public List<String> UpdateCommandNo(){
        List<String> commandNo = new ArrayList<String>(2);

        String commandOne = getCommandNo("commandOne");
        String commandTwo = getCommandNo("commandTwo");
        CommandNoModel commandNoModel1 = new CommandNoModel();
        CommandNoModel commandNoModel2 = new CommandNoModel();
        commandNoModel1.setCommandId("commandOne");
        commandNoModel2.setCommandId("commandTwo");
        int tempCommandOne = Integer.parseInt(commandOne);
        int tempCommandTwo = Integer.parseInt(commandTwo) + 1;
        //若编号2到127了，则回退到1，同时编号1加1；若编号1到127了，则编号1和2一同回退到1
        if(tempCommandTwo == 127) {
            tempCommandTwo = 1;
            tempCommandOne += 1;
            if (tempCommandOne == 127) {
                tempCommandOne = 1;
                tempCommandTwo = 1;
            }
            commandNoModel1.setNumber(String.valueOf(tempCommandOne));
            createCommand(commandNoModel1);
        }
        commandNoModel2.setNumber(String.valueOf(tempCommandTwo));
        createCommand(commandNoModel2);
        commandNo.add(commandOne);
        commandNo.add(commandTwo);
        return commandNo;
    }

/*        hashOperations.delete("hash", "map1");
        System.out.println(hashOperations.entries("hash"));*/
     /*   for (int i = 0; i < 5; i++) {
            Demo listDemo = new Demo();
            now = new Date();
            listDemo.setCommandId("\"" + i + "\"");
            listDemo.setBeginTime(now.getTime());
            listOperations.leftPush("list1", listDemo);
            listOperations.rightPush("list2", listDemo);
        }
        // 可给数据排序
        for(int i = 0; i<2;i++) {
            Demo demo2 = (Demo) listOperations.leftPop("list1");

            Demo demo3 = (Demo) listOperations.rightPop("list2");
            System.out.println(demo2.toString());
            System.out.println(demo3.toString());
        }*/

    //获取某个id对应插座的剩余使用时间
   /* public String getLeftTime(String terminalId){
        long lefttime = redisTemplate.getExpire(terminalId);
        return String.valueOf(lefttime);
    }*/

    /*    // -----------------其他值类型数据操作 start--------------------
        Demo demo = new Demo();
        demo.setId("1");
        demo.setName("fiala");
        List<Demo> demos = new ArrayList<Demo>();
        ValueOperations<String, Object> valueOperations = redisTemplate
                .opsForValue();
        // 设置value为对象类型，且不设置过期时间，默认永久
        valueOperations.set("value1", demo);
        // 设置value为对象类型，设置过期时间为80秒，时间单位由TimeUnit控制
        valueOperations.set("value2", demos, 80, TimeUnit.SECONDS);
        Demo demo1 = (Demo) valueOperations.get("value1");
        System.out.println(demo1.toString());
        // -----------------其他值类型数据操作 end--------------------

        // -----------------List数据类型操作 start------------------
        ListOperations<String, Object> listOperations = redisTemplate
                .opsForList();
        for (int i = 0; i < 5; i++) {
            Demo listDemo = new Demo();
            listDemo.setId("\"" + i + "\"");
            listDemo.setName("fiala" + i);
            listOperations.leftPush("list1", listDemo);
            listOperations.rightPush("list2", listDemo);
        }
        // 可给数据排序
        Demo demo2 = (Demo) listOperations.leftPop("list1");
        Demo demo3 = (Demo) listOperations.rightPop("list2");
        System.out.println(demo2.toString());
        System.out.println(demo3.toString());
        // -----------------List数据类型操作 end------------------

        // -----------------set数据类型操作 start------------------
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        for (int i = 0; i < 5; i++) {
            Demo setDemo = new Demo();
            setDemo.setId("\"" + i + "\"");
            setDemo.setName("fiala" + i);
            setOperations.add("set1", setDemo);
        }
        Demo demo4 = (Demo) setOperations.pop("set1");
        System.out.println(demo4.toString());
        // -----------------set数据类型操作 end------------------

        // -----------------zset数据类型操作 start------------------
        ZSetOperations<String, Object> zSetOperations = redisTemplate
                .opsForZSet();
        zSetOperations.add("zset", "fiala", 0);
        zSetOperations.add("zset", "my fiala", 1);
        System.out.println(zSetOperations.rangeByScore("zset", 0, 1));
        // -----------------zset数据类型操作 end------------------

        // -----------------hash数据类型操作 start------------------
        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();
        Map<String, String> map = new HashMap<String, String>();
        map.put("map1", "fiala1");
        map.put("map2", "fiala2");
        hashOperations.putAll("hash", map);
        System.out.println(hashOperations.entries("hash"));
        // -----------------hash数据类型操作 start------------------  */

}

