package com.terabits.meta.po;

public class AuxcalPO {
    private int id;
    private String gmtCreate;
    //每一笔消费对应的流量
    private Double flow;
    //每一笔消费充值对应的金额
    private Double recharge;
    //每一笔消费对应的金额
    private Double payment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public Double getRecharge() {
        return recharge;
    }

    public void setRecharge(Double recharge) {
        this.recharge = recharge;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "AuxcalPO[" +
                "id=" + id +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", flow=" + flow +
                ", recharge=" + recharge +
                ", payment=" + payment +
                ']';
    }

    public AuxcalPO() {
        this.flow = 0.0;
        this.recharge = 0.0;
        this.payment = 0.0;
    }
}
