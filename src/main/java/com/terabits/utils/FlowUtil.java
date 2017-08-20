package com.terabits.utils;

import com.terabits.config.Constants;

/**
 * Created by Administrator on 2017/8/20.
 */
public class FlowUtil {
    public static double costToFlow(double cost){
        double flow = 0.0;
        if(cost == 0.1){
            flow = 0.2;
        }else if(cost == 0.2){
            flow = 0.5;
        }else if(cost == 0.3){
            flow = 1;
        }else if(cost == 0.5){
            flow = 2;
        }
        return flow;
    }

    public static byte flowToCommand(double flow){
        byte command = 0x0;
        if(flow == 0.2){
            command = Constants.TWO_M_WATER;
        }else if(flow == 0.5){
            command = Constants.FIVE_M_WATER;
        }else if(flow == 1){
            command = Constants.ONE_L_WATER;
        }else if(flow == 2){
            command = Constants.TWO_L_WATER;
        }
        return command;
    }

}
