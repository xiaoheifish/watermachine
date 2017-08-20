package com.terabits.service;

/**
 * Created by Administrator on 2017/8/20.
 */
public interface HuaweiPostCommandService {
    //模拟透传下发命令
    public String command(byte[] data, String terminalId) throws Exception;
}
