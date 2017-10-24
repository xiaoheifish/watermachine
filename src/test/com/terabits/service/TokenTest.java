package com.terabits.service;

import com.terabits.config.Constants;

import com.terabits.mapper.CommandMapper;
import com.terabits.mapper.FeedbackMapper;
import com.terabits.mapper.TerminalMapper;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.CommandPO;
import com.terabits.meta.po.FeedbackPO;
import com.terabits.meta.po.PresentPO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.meta.po.UserPO;
import com.terabits.meta.po.WechatConsumePO;
import com.terabits.service.BaseTest;
import com.terabits.service.UserService;
import com.terabits.utils.GenerateOrderId;
import com.terabits.utils.TimeSpanUtil;
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
    private WechatConsumeService wechatConsumeService;
    //private TerminalManager terminalManager;
    @Test   //标明是测试方法
    @Transactional(value="transactionManager")//标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void token() throws Exception{
       /* WechatConsumePO wechatConsumePO = new WechatConsumePO();
        wechatConsumePO.setOpenId("o1S07wuDO9ivY_55p3OT4bEMNUL0");
        wechatConsumePO.setMoney(0.3);
        int id = 0;
        wechatConsumeService.insertWechatConsume(wechatConsumePO);
        id = wechatConsumePO.getId();
        System.out.println("id::::::::::::::"+ id);
        String orderId = GenerateOrderId.generateWechatConsumeId(id);
        wechatConsumeService.updateOrderIdById(orderId, id);*/
        System.out.println("WechatConsumePO="+wechatConsumeService.selectWechatConsumeByOrderId("2017102264000002"));
    }
}
