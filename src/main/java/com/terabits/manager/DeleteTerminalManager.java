package com.terabits.manager;

import com.terabits.config.Constants;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.NumberBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.OperationPO;
import com.terabits.service.OperationService;
import com.terabits.service.TerminalService;
import com.terabits.utils.huawei.PlatformGlobal;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.terabits.service.CredentialService;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
@Component
@Transactional
public class DeleteTerminalManager {

  /*  @Autowired
    private TerminalService terminalService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private CredentialService CredentialService;
    @Scheduled(cron = "0 0/1 * * * *")

        //原本的思路是找出数据库中修改时间为1小时，2小时或5小时的，后来发现只需要找出当前在线的终端，若发现redis中已经被清除了，表明
        //充值已经结束，那再去修改数据库中的状态即可。redis大法好。
    void calculateSum()throws Exception{
        List<NumberBO> numberBOS = terminalService.selectTerminalNumber();
        for(NumberBO numberBO : numberBOS){
            String displayId = numberBO.getDisplayId();
            Date date = new Date();
            System.out.println("taskdate:::::"+date.getTime());
            String time = CredentialService.getTerminalTime(displayId);
            if(time == null){
                System.out.println("time==null"+displayId);
                //下发关断插座命令给终端
                byte[] openbytes = new byte[3];
                openbytes[0] = Constants.SEND_COMMAND_START;
                openbytes[1] = Constants.CUT_OFF_COMMAND;
                openbytes[2] = Constants.SEND_COMMAND_END;
                PlatformGlobal.command(openbytes, numberBO.getDeviceId());
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setState(Constants.OFF_STATE);
                terminalUpdateBO.setDisplayId(numberBO.getDisplayId());
                terminalService.updateTerminal(terminalUpdateBO);
                OperationPO operationPO = new OperationPO();
                operationPO.setImei(numberBO.getImei());
                operationPO.setStatus(Constants.ON_TO_OFF);
                operationService.insertOperation(operationPO);
            }
        }
    }*/

   /* private static String getBeginTime(){
        SimpleDateFormat dfs = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Calendar calendar = Calendar.getInstance();
        Date end = new Date();
        calendar.setTime(end);
        calendar.add(calendar.HOUR, -2);
        Date begin = calendar.getTime();
        String beginTime = dfs.format(begin);
        return beginTime;
    }*/
}
