package com.terabits.utils.huawei;

/**
 * Created by Administrator on 2017/6/6.
 */
public class CodecComponent {

    public static final byte RECEIVE_DATA = (byte) 0xAD;
    public static final byte REPLY_COMMAND = (byte) 0xAB;
    public static final byte TERMINAL_ONLINE = (byte) 0xCC;
    public static final byte TERMINAL_INFORMATION = (byte) 0xAA;
    public static final byte SEND_COMMAND = (byte) 0xAA;
    public static final byte SEND_ADDRESS =(byte)  0xAD;
    public static final byte REPLY_DATA = (byte) 0xAB;

    public static final byte HAS_MORE_YES = (byte) 0x1C;
    public static final byte HAS_MORE_NO = (byte) 0x1D;
    public static final byte METER_STATE_OFF = (byte) 0x2D;
    public static final byte METER_STATE_ON = (byte) 0x2C;
    public static final byte METER_STATE_ERROR = (byte) 0x2B;
    public static final byte METER_ADRRESS_RECEIVED = (byte) 0x2E;

    public static final byte METER_COMMAND_OFF = (byte) 0x0D;
    public static final byte METER_COMMAND_ON = (byte) 0x0C;
    public static final byte END_MARK = (byte) 0xED;
    public static final byte DATA_RECEIVED_SUCC = (byte) 0x1C;
    public static final byte DATA_RECEIVED_ERROR = (byte) 0x1D;

}
