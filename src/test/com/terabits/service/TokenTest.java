package com.terabits.service;

import com.terabits.config.Constants;

import com.terabits.mapper.CommandMapper;
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
    private UserService userService;
    //private TerminalManager terminalManager;
    @Test   //标明是测试方法
    @Transactional(value="transactionManager")//标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void token() throws Exception{
    /*    CommandNoModel terminalModel = new CommandNoModel();
        terminalModel.setTerminalId("0001");
        terminalModel.setTime("1234325");
        terminalModel.setHour(8);
        //terminalManager.createNewTerminal(terminalModel);
        //redisTemplateTest.createTerminal(terminalModel);
        String re = redisTemplateTest.getTerminalTime("000001");
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
            Date begin =dfs.parse(re);
            Date end = new Date();
            between = (end.getTime() - begin.getTime())/1000;// 得到两者的秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String formatre = TimeUtils.secToTime(between);
        String lefttime = redisTemplateTest.getLeftTime("000001");*/
       /* String re = redisTemplateTest.getTerminalTime("000001");
        System.out.println("lefttime:::::"+re);*/
    /*    CommandPO commandPO = new CommandPO();
        commandPO.setCommandNo(123);
        commandPO.setImei("fgsg");
        commandPO.setStatus(23);
        String imei = "fgsg";
        int commandNo = 123;
        int state = 66;
        //commandMapper.getStatusByImeiAndCommandNo(imei,commandNo,state);*/
        UserPO userPO = userService.selectUser("o1S07wuDO9ivY_55p3OT4bEMNUL0");
        System.out.println(userPO);
    }
}
