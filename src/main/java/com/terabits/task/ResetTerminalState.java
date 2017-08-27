package com.terabits.task;

import com.terabits.config.Constants;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.CommandPO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.CommandService;
import com.terabits.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
@Component
@Transactional
public class ResetTerminalState {

/*
    @Autowired
    private CommandService commandService;

    @Autowired
    private TerminalService terminalService;
    //每隔1分钟去查一次，近10分钟内订单状态,不包含最近3分钟的，找出未完成订单，去terminal中更新状态在2分钟内没变化过的，并且state不是off_state的
    @Scheduled(cron = "0 0/1 * * * *")
    void resetState()throws Exception{
        List<String> commandPOList = commandService.getUndoneDevice();
        for(String deviceId : commandPOList){
            TerminalPO terminalPO = terminalService.selectTerminalByDeviceId(deviceId);
            String gmtModified = terminalPO.getGmtModified();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Date begin = sdf.parse(gmtModified);
            long time = (now.getTime() - begin.getTime()) / 1000;
            if(terminalPO.getState()!= Constants.OFF_STATE && time > 120){
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setState(Constants.OFF_STATE);
                terminalUpdateBO.setDeviceId(deviceId);
                terminalService.updateTerminal(terminalUpdateBO);
            }
        }
    }*/
}
