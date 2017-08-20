package com.terabits.service;

import com.terabits.meta.model.CommandNoModel;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface CredentialService {
    //插入指令编号，永久有效

    public void createCommand(CommandNoModel commandNoModel);
    //获取某个编号数值
    public String getCommandNo(String commandId);

}
