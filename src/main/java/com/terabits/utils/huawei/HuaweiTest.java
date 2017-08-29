package com.terabits.utils.huawei;

import com.terabits.config.Constants;
import com.terabits.utils.FlowUtil;

/**
 * Created by Administrator on 2017/7/20.
 */
public class HuaweiTest {
    public static void main(String[] args) throws Exception{
       /* //下发开启插座命令给终端
        byte[] openbytes = new byte[6];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = FlowUtil.flowToCommand(0.2);
        openbytes[3] = (byte)0x01;
        openbytes[4] = (byte)0x04;
        openbytes[5] = Constants.SEND_COMMAND_END;
        try {
            PlatformGlobal
                    .command(openbytes, "afd12a84-94d6-40a9-bb82-8abdc48b871a");
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
