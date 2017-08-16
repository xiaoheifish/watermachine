package com.terabits.utils.huawei;

import com.terabits.config.Constants;

/**
 * Created by Administrator on 2017/7/20.
 */
public class HuaweiTest {
    public static void main(String[] args) throws Exception{
        //下发开启插座命令给终端
        byte[] openbytes = new byte[3];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = Constants.SEND_COMMAND_END;
        try {
            PlatformGlobal.command(openbytes, "a78ff7d4-2fb9-41d3-acb8-5ff0152b2a0e");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
