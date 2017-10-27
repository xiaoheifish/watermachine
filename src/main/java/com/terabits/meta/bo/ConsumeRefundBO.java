package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/10/25.
 */
public class ConsumeRefundBO {
    //待退款用户openId
    private String openId;
    //退款金额
    private double money;
    //退款对应的交易tradeNo
    private String tradeNo;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Override
    public String toString() {
        return "ConsumeRefundBO[" +
                "openId='" + openId + '\'' +
                ", money=" + money +
                ", tradeNo='" + tradeNo + '\'' +
                ']';
    }
}
