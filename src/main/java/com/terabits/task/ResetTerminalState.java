package com.terabits.task;

import com.terabits.config.Constants;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.CommandPO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.CommandService;
import com.terabits.service.CredentialService;
import com.terabits.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/26.
 */
@Component
@Transactional
public class ResetTerminalState {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private TerminalService terminalService;
    //查redis中尚在工作的，去terminal中更新在线过久的，并在redis中删除
    @Scheduled(cron = "0 0/2 * * * *")
    void resetState()throws Exception{
        Map<Object, Object> map = credentialService.getCurentDevice();
        Date now = new Date();
        for(Object key : map.keySet()){
            now = new Date();
            Long beginTime = (Long)map.get(key);
            Long timeSpan = (now.getTime() - beginTime) / 1000;
            if (timeSpan > 150){
                TerminalUpdateBO terminalUpdateBO = new TerminalUpdateBO();
                terminalUpdateBO.setDeviceId((String)key);
                terminalUpdateBO.setState(Constants.OFF_STATE);
                terminalService.updateTerminal(terminalUpdateBO);
                credentialService.deleteExpireDevice((String)key);
            }
        }
    }
}
