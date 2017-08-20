package com.terabits.service.impl;

import com.terabits.mapper.CommandMapper;
import com.terabits.meta.po.CommandPO;
import com.terabits.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/19.
 */
@Service("commandService")
public class CommandServiceImpl implements CommandService {
    @Autowired(required = false)
    private CommandMapper commandMapper;

    /** 插入命令，state默认是未执行的23
     */
    public int insertCommand(CommandPO commandPO) throws Exception{
        return commandMapper.insertCommand(commandPO);
    }

    /**根据imei号和指令编号更新status状态
     */
    public int updateStatusByImeiAndCommandNo(String imei, int commandNo, int status) throws Exception{
        return commandMapper.updateStatusByImeiAndCommandNo(imei, commandNo, status);
    }

    /** 根据imei号和指令编号查询status状态
     */
    public int getStatusByImeiAndCommandNo(String imei, int commandNo) throws Exception{
        return commandMapper.getStatusByImeiAndCommandNo(imei, commandNo);
    }
}
