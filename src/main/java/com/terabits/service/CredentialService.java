package com.terabits.service;

import com.terabits.meta.model.TerminalModel;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface CredentialService {
    //向redis中存入一个键值对
    public void createTerminal(TerminalModel terminalModel);
    //查询某个id对应的记录的存入时间
    public String getTerminalTime(String terminalId);
    //查询某个id对应的记录的剩余有效时间
    public String getLeftTime(String terminalId);
}
