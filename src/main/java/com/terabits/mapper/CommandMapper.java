package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.CommandPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface CommandMapper {

    /**
     * 新建一条命令
     * @param commandPO
     * @return
     * @throws Exception
     */
    public int insertCommand(CommandPO commandPO) throws Exception;

    /**
     * 根据deviceId更新state，用于收到回复确认和执行完成命令时使用
     * @param state
     * @param deviceId
     * @return
     * @throws Exception
     */
    public int updateState(@Param("state")String state, @Param("deviceId")String deviceId) throws Exception;


    /**
     * 根据deviceId更新commandIdTwo，目前只重发一次，所以一共只有两个commandId
     * @param commandIdTwo
     * @param deviceId
     * @return
     * @throws Exception
     */
    public int updateCommandId(@Param("commandIdTwo")String commandIdTwo, @Param("deviceId")String deviceId) throws Exception;

    /**
     * 根据时间跨度选取未执行完成的命令并返回deviceId,去terminal表中查gmtModified过久的，更新回10
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<String> getUndoneDevice(TimeSpanBO timeSpanBO)throws Exception;

}
