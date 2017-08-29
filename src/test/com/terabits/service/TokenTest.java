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
        CommandPO commandPO = new CommandPO();
        commandPO.setDeviceId("123");
        commandPO.setFlow(0.3);
        commandPO.setCommandIdOne("1232423");
        commandPO.setState(Constants.BEGIN_STATE);
        commandService.insertCommand(commandPO);
        CommunicationBO communicationBO = terminalService.getTerminalDeviceId("1002");
        credentialService.createList(communicationBO.getDeviceId());
    }
}
