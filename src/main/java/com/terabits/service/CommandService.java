package com.terabits.service;

import com.terabits.meta.po.CommandPO;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface CommandService {

    /** 插入命令，state默认是未执行的23
     */
    public int insertCommand(CommandPO commandPO) throws Exception;

    /**根据imei号和指令编号更新status状态
     */
    public int updateStatusByImeiAndCommandNo(String imei, int commandNo, int status) throws Exception;

    /** 根据imei号和指令编号查询status状态
     */
    public int getStatusByImeiAndCommandNo(String imei, int commandNo) throws Exception;
}
