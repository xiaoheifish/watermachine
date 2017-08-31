package com.terabits.service;

import com.terabits.config.Constants;

import com.terabits.mapper.CommandMapper;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.CommandPO;
import com.terabits.meta.po.UserPO;
import com.terabits.service.BaseTest;
import com.terabits.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
public class TokenTest extends BaseTest {
    @Autowired(required = false)
    private CommandMapper commandMapper;
    @Autowired
    private CommandService commandService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private TerminalService terminalService;
    //private TerminalManager terminalManager;
    @Test   //标明是测试方法
    @Transactional(value="transactionManager")//标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void token() throws Exception {
        //credentialService.updateDeviceTime("132342421");
        credentialService.updateDeviceTime("123");
        Map<Object, Object> map = credentialService.getCurentDevice();
        for(Object key : map.keySet()){
            Date now = new Date();
            Long beginTime = (Long)map.get(key);
            Long timeSpan = now.getTime() - beginTime;
            System.out.println(timeSpan);
        }
    }
}
