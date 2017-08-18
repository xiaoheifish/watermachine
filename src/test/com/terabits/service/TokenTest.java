package com.terabits.service;

import com.terabits.config.Constants;

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
    private UserService userService;

    //private TerminalManager terminalManager;
    @Test   //标明是测试方法
    @Transactional(value="transactionManager")//标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void token() throws Exception{
    /*    TerminalModel terminalModel = new TerminalModel();
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
        String openid = "1234567";
        double remain = 5.0;
        userService.updateRemain(remain, openid);
    }
}
