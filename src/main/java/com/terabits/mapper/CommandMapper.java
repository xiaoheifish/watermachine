package com.terabits.mapper;

import com.terabits.meta.po.CommandPO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/8/19.
 */
public interface CommandMapper {

    /** 插入命令，state默认是未执行的23
     * @param commandPO
     * @return
     * @throws Exception
     */
    public int insertCommand(CommandPO commandPO) throws Exception;

    /**根据imei号和指令编号更新status状态
     * @param imei
     * @param commandNo
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusByImeiAndCommandNo(@Param("imei")String imei, @Param("commandNo")int commandNo, @Param("status") int status) throws Exception;

    /** 根据imei号和指令编号查询status状态
     * @param imei
     * @param commandNo
     * @return
     * @throws Exception
     */
    public int getStatusByImeiAndCommandNo(@Param("imei")String imei,@Param("commandNo")int commandNo) throws Exception;
}
