package com.terabits.utils;

import com.terabits.config.Constants;

/**
 * Created by Administrator on 2017/10/23.
 */
public class HuaweiCommandUtil {

    public static byte[] generateOpenbytes(double flow, String commandOne, String commandTwo){
        // 下发指令
        int cmdOne = Integer.parseInt(commandOne);
        int cmdTwo = Integer.parseInt(commandTwo);
        byte[] openbytes = new byte[6];
        openbytes[0] = Constants.SEND_COMMAND_START;
        openbytes[1] = Constants.POWER_ON_COMMAND;
        openbytes[2] = FlowUtil.flowToCommand(flow);
        openbytes[3] = (byte) cmdOne;
        openbytes[4] = (byte) cmdTwo;
        openbytes[5] = Constants.SEND_COMMAND_END;
        return openbytes;
    }
}
