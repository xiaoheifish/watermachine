package com.terabits.meta.po;

/**
 * 用户直接使用微信支付消费对应的PO
 * Created by Administrator on 2017/10/22.
 */

public class WechatConsumePO {
    private int id;
    //直接使用微信下单的用户openId
    private String openId;
    //本次消费下单金额，以元为单位
    private double money;
    //本次消费对接微信的商户订单号，可用于用户退款，订单查询
    private String orderId;
    //本次消费微信平台生成的订单号
    private String tradeNo;
    //本次消费的消费编号
    private String consumeNo;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getConsumeNo() {
        return consumeNo;
    }

    public void setConsumeNo(String consumeNo) {
        this.consumeNo = consumeNo;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "WechatConsumePO[" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", money=" + money +
                ", orderId='" + orderId + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", consumeNo='" + consumeNo + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
