package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/9/13.
 */
public class RefundRecordPO {
    //id
    private int id;
    //openid 申请退款用户的openid
    private String openId;
    //money 退款金额
    private double money;
    //refundno 退款编号
    private String refundNo;
    //paymentno, 微信生成的付款单号
    private String paymentNo;
    //state 退款状态，未完成是10， 已完成是11
    private int state;
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

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
        return "RefundRecordPO[" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", money=" + money +
                ", refundNo='" + refundNo + '\'' +
                ", paymentNo='" + paymentNo + '\'' +
                ", state=" + state +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
