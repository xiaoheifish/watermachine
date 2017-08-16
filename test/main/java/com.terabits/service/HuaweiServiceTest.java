package com.terabits.service;

import com.terabits.config.Constants;
import com.terabits.utils.huawei.PlatformGlobal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/13.
 */
public class HuaweiServiceTest {
    @Autowired(required = false)  //自动注入,默认按名称
    //private OperationMapper operationMapper;
    @Test   //标明是测试方法
    @Transactional(value="transactionManager")//标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void main() throws Exception{
       //下发开启插座命令给终端
        byte[] openbytes = new byte[3];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = Constants.SEND_COMMAND_END;
        PlatformGlobal.command(openbytes, "a78ff7d4-2fb9-41d3-acb8-5ff0152b2a0e" );
    }
}
