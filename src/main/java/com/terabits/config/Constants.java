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

    //数据库中字段，标记正在下单
    public static final int ORDER_STATE = 12;

    //数据库中字段，标记设备出现其他故障导致不可使用
    public static final int BREAKDOWN_STATE = 13;

    //数据库中字段，标记命令尚未收到回复,尚未执行
    public static final int NO_RESPONSE = 23;

    //数据库中字段，标记命令已经被执行，收到回复
    public static final int HAVE_RESPONSE = 66;

    /**
     * 硬件通信命令
     */
    //对终端下发控制命令开始标志
    public static final byte SEND_COMMAND_START = (byte)0xBA;

    //对终端下发断电命令
    public static final byte CUT_OFF_COMMAND = (byte)0x0D;

    //对终端下发上电命令
    public static final byte POWER_ON_COMMAND = (byte)0x0C;

    //对终端下发控制命令结束标志
    public static final byte SEND_COMMAND_END = (byte)0xFA;

    //流量值标记
    public static final byte THREE_M_WATER = (byte)0x01;

    public static final byte FIVE_M_WATER = (byte)0x02;

    public static final byte ONE_L_WATER = (byte)0x03;

    public static final byte TWO_L_WATER = (byte)0x04;

    //指令编号
    public static final byte COMMAND_ONE = (byte)0x1f;

    public static final byte COMMAND_TWO = (byte)0x2f;

    //消费编号

    //项目标号
    public static final String CONSUME_PRO_NUMBER = "36";
    //退款编号
    public static final String REFUND_PRO_NUMBER = "25";

    //下发命令执行状态
    public static final String BEGIN_STATE = "10";

    public static final String HALF_STATE = "16";

    public static final String END_STATE = "26";

    //反馈处理状态
    public static final int FEEDBACK_UNRESOLVED = 23;

    public static final int FEEDBACK_SOLVED = 66;

    //退款订单状态, 10表示处理中, 11表示处理完成
    public static final int IN_HAND = 10;

    public static final int FINISHED = 11;

    //赠送金额类别,10表示注册赠送，11表示充值赠送，12表示邀请赠送，13表示兑换勋章赠送

    public static final int REGISTER_PRESENT = 10;

    public static final int RECHARGE_PRESENT = 11;

    public static final int INVITE_PRESENT = 12;

    public static final int MEDAL_PRESENT = 13;
}
