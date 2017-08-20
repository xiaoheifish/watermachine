package com.terabits.meta.enums;

/**
 * Created by Administrator on 2017/8/20.
 */
public enum ConsumeState {
    PAID(1, "已支付"),
    OPERATED(2, "已执行");

    private final int value;

    private final String desc;

    ConsumeState(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
