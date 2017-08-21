package com.terabits.mapper;

import com.terabits.meta.bo.CommunicationBO;
import com.terabits.meta.bo.NumberBO;
import com.terabits.meta.bo.TerminalUpdateBO;
import com.terabits.meta.po.TerminalPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface TerminalMapper {

    /**
     * 新增终端数据
     * @param terminalPO
     * @return
     * @throws Exception
     */
    public int insertTerminal(TerminalPO terminalPO) throws Exception;

    /**
     * 更新终端数据
     * @param terminalUpdateBO
     * @return
     * @throws Exception
     */
    public int updateTerminal(TerminalUpdateBO terminalUpdateBO) throws Exception;

    /**
     * 删除终端数据
     * @param displayId
     * @return
     * @throws Exception
     */
    public int deleteTerminal(@Param("displayId") String displayId) throws Exception;

    /**
     * 根据displayid查询对应终端
     * @param displayId
     * @return
     * @throws Exception
     */
    public TerminalPO selectOneTerminal(@Param("displayId") String displayId) throws Exception;

    /**
     * 根据displayid返回imei号deviceid
     * @param displayId
     * @return
     * @throws Exception
     */
    public CommunicationBO getTerminalDeviceId(@Param("displayId") String displayId) throws Exception;

    /**
     * 返回全部终端数据
     * @return
     * @throws Exception
     */
    public List<TerminalPO> selectAllTerminal() throws Exception;

    /**
     * 返回当前还在使用的插座
     * @return
     * @throws Exception
     */
    public List<NumberBO> selectTerminalNumber() throws Exception;

    /**
     * 返回当前查询插座的地址
     * @return
     * @throws Exception
     */
    public String selectLocation(@Param("displayId") String displayId) throws Exception;

    /**
     * 根据webId查询displayId,用于在应用内部扫码查询某插座的信息
     * @return
     * @throws Exception
     */
    public String getTerminalDisplayId(@Param("webId") String webId) throws Exception;

    /**
     * 根据imei号查displayId，用于接收硬件发来的消息时使用
     * @param imei
     * @return
     * @throws Exception
     */
    public String getDisplayIdFromImei(@Param("imei") String imei) throws Exception;

    /**
     * 根据deviceId查询imei号
     * @param deviceId
     * @return
     * @throws Exception
     */
    public String selectImeiFromDeviceId(@Param("deviceId")String deviceId)throws Exception;

}
