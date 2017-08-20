package com.terabits.service.impl;

import com.terabits.meta.model.CommandNoModel;
import com.terabits.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

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

