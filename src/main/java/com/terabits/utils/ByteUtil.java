package com.terabits.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.terabits.utils.huawei.JsonUtil;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/30.
 */
public class ByteUtil {
    public static void main(String args[])throws Exception{
        byte byte1 = (byte) 0xAB;
        byte byte2 = (byte) 0xAD;
        byte[] bytearray = new byte[3];
        bytearray[0] = (byte)0x7F;
        bytearray[1] = (byte)0x1F;
        bytearray[2] = (byte)0x02;
        for(int i=0;i<bytearray.length;i++){
            System.out.println(bytearray[i]);
        }

       /* String finalbyte =""+ byte1 + byte2;
        System.out.println("finalbyte:"+ finalbyte);
        String paraStr = "{";
        paraStr += "\"meterId\":" + 0x01;
        paraStr += "\"state\":" + state;
        paraStr += "}";
        ObjectNode node = JsonUtil.convertObject2ObjectNode(paraStr);*/
    }
}
