package com.terabits.manager;

/**
 * Created by Administrator on 2017/8/25.
 */
public interface PostCommandManager
{
    public String command(byte[] data, String terminalId) throws Exception;
}
