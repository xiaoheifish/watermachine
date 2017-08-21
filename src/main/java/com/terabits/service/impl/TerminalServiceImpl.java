package com.terabits.service.impl;

import com.terabits.mapper.TerminalMapper;
import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.NumberBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.TerminalPO;
import com.terabits.service.TerminalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service("TerminalService")
public class TerminalServiceImpl implements TerminalService{

    @Autowired(required = false)
    private TerminalMapper terminalMapper;

    @Transactional(value="transactionManager")//标明此方法需使用事务
    public int insertTerminal(TerminalPO terminalPO){
        int result = 0;
        try {
            result = terminalMapper.insertTerminal(terminalPO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateTerminal(TerminalUpdateBO terminalUpdateBO){
        int result = 0;
        try {
            result = terminalMapper.updateTerminal(terminalUpdateBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteTerminal(String displayId){
        int result = 0;
        try{
            result = terminalMapper.deleteTerminal(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public TerminalPO selectOneTerminal(String displayId){
        TerminalPO terminalPO = new TerminalPO();
        try{
            terminalPO = terminalMapper.selectOneTerminal(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return terminalPO;
    }

    public CommunicationBO getTerminalDeviceId(String displayId){
        CommunicationBO communicationBO = new CommunicationBO();
        try {
            communicationBO = terminalMapper.getTerminalDeviceId(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return communicationBO;
    }

    public List<TerminalPO> selectAllTerminal(){
        List<TerminalPO> terminalPOS = new ArrayList<TerminalPO>();
        try{
            terminalPOS = terminalMapper.selectAllTerminal();
        }catch (Exception e){
            e.printStackTrace();
        }
        return terminalPOS;
    }

    public List<NumberBO> selectTerminalNumber(){
        List<NumberBO> numberBOS = new ArrayList<NumberBO>();
        try{
            numberBOS = terminalMapper.selectTerminalNumber();
        }catch (Exception e){
            e.printStackTrace();
        }
        return numberBOS;
    }

    public String selectLocation(String displayId){
        String location = null;
        try{
            location = terminalMapper.selectLocation(displayId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }

    public String getTerminalDisplayId(String webId){
        String displayId = null;
        try{
            displayId = terminalMapper.getTerminalDisplayId(webId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return displayId;
    }

    public String getDisplayIdFromImei(String imei){
        String displayId = null;
        try{
            displayId = terminalMapper.getDisplayIdFromImei(imei);
        }catch (Exception e){
            e.printStackTrace();
        }
        return displayId;
    }

    public String selectImeiFromDeviceId(String deviceId){
        String imei = null;
        try{
            imei = terminalMapper.selectImeiFromDeviceId(deviceId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return imei;
    }

}


