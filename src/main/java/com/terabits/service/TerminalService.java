package com.terabits.service;

import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.NumberBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.TerminalPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface TerminalService {

    //插入新终端数据
    public int insertTerminal(TerminalPO terminalPO);
    //更新终端sim卡信息或状态
    public int updateTerminal(TerminalUpdateBO terminalUpdateBO);
    //删除终端
    public int deleteTerminal(String displayId);
    //根据displayId查询某个终端
    public TerminalPO selectOneTerminal(String displayId);
    //根据displayId查询imei号和deviceId
    public CommunicationBO getTerminalDeviceId(String displayId);
    //查询全部终端
    public List<TerminalPO> selectAllTerminal();
    //查询当前在线终端的imei号和display号
    public List<NumberBO> selectTerminalNumber();
    //根据displayid查询地址
    public String selectLocation(String displayId);
    //根据webId查询displayId，用于在微信应用内部扫码的场景
    public String getTerminalDisplayId(String webId);
    //根据imei号查询displaId
    public String getDisplayIdFromImei(String imei);
    //根据deviceId查询imei号
    public String selectImeiFromDeviceId(String deviceId);
}
