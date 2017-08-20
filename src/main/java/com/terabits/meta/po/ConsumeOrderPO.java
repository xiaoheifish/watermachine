package com.terabits.meta.po;

import com.terabits.meta.enums.ConsumeState;

/**
 * Created by Administrator on 2017/8/19.
 * 用户每次接水，会产生一笔消费，从它的余额里减去当次消费的金额
 */
public class ConsumeOrderPO {
    private int id;
    //该笔消费状态
    private int state;
    //该笔消费订单编号
    private String orderNo;
    //产生该笔消费的用户
    private String openId;
    //设备编号
    private String displayId;
    //消费金额
    private double payment;
    //该笔消费的水量
    private double flow;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ConsumeOrderPO[" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", openId='" + openId + '\'' +
                ", displayId='" + displayId + '\'' +
                ", payment=" + payment +
                ", flow=" + flow +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
