package com.terabits.service.impl;

import com.terabits.mapper.CommandMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.CommandPO;
import com.terabits.service.CommandService;
import com.terabits.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
@Service("commandService")
public class CommandServiceImpl implements CommandService {
    @Autowired(required = false)
    private CommandMapper commandMapper;

    private static Logger logger = LoggerFactory
            .getLogger(CommandServiceImpl.class);

    /**
     * 新建一条命令
     */
    public int insertCommand(CommandPO commandPO) throws Exception{
        return commandMapper.insertCommand(commandPO);
    }

    /**
     * 根据deviceId更新state，用于收到回复确认和执行完成命令时使用
     */
    public int updateState(String state, String deviceId) throws Exception{
        return  commandMapper.updateState(state, deviceId);
    }


    /**
     * 根据deviceId更新commandIdTwo，目前只重发一次，所以一共只有两个commandId
     */
    public int updateCommandId(String commandIdTwo, String deviceId) throws Exception{
        return  commandMapper.updateCommandId(commandIdTwo, deviceId);
    }


    /**
     * 根据时间跨度选取未执行完成的命令并返回deviceId,去terminal表中查gmtModified过久的，更新回10
     */
    public List<String> getUndoneDevice()throws Exception{
        List<String> deviceList = new ArrayList<String>();
        TimeSpanBO timeSpanBO = TimeUtils.getTimeSpan();
        try{
            deviceList = commandMapper.getUndoneDevice(timeSpanBO);
        }catch (Exception e){
            logger.error("getUndoneDevice error in CommandService");
        }
        return deviceList;
    }
}
