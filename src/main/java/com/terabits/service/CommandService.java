package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.CommandPO;
import com.terabits.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface CommandService {

    /**
     * 新建一条命令
     */
    public int insertCommand(CommandPO commandPO) throws Exception;

    /**
     * 根据deviceId更新state，用于收到回复确认和执行完成命令时使用
     */
    public int updateState(String state, String deviceId) throws Exception;

    /**
     * 根据deviceId更新commandIdTwo，目前只重发一次，所以一共只有两个commandId
     */
    public int updateCommandId(String commandIdTwo, String deviceId) throws Exception;


    /**
     * 查询最近10分钟未执行完成的命令并返回deviceId,去terminal表中查gmtModified过久的，更新回10
     */
    public List<String> getUndoneDevice()throws Exception;
}
