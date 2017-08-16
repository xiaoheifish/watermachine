package com.terabits.config;

/**
 * Created by Administrator on 2017/6/21.
 */
public class Constants {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时），过期自动从redis中删除
     */
    public static final int ONE_EXPIRES_HOUR = 1;
    public static final int TWO_EXPIRES_HOUR = 2;
    public static final int FIVE_EXPIRES_HOUR = 5;
    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    //数据库中字段，标记插座发生状态转换，从闭合到断开
    public static final int ON_TO_OFF = 102;

    //数据库中字段，标记插座发生状态转换，从断开到闭合
    public static final int OFF_TO_ON = 21;

    //数据库中字段，标记当前正在使用
    public static final int ON_STATE = 11;

    //数据库中字段，标记未被使用
    public static final int OFF_STATE = 10;

    /**
     * 硬件通信命令
     */
    //对终端下发控制命令开始标志
    public static final byte SEND_COMMAND_START = (byte)0xAA;

    //对终端下发断电命令
    public static final byte CUT_OFF_COMMAND = (byte)0x0D;

    //对终端下发上电命令
    public static final byte
            POWER_ON_COMMAND = (byte)0x0C;

    //对终端下发控制命令结束标志
    public static final byte SEND_COMMAND_END = (byte)0xEA;
}
