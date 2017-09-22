package com.terabits.meta.po;

public class TotalPO {
    private int id;
    //总消费水流量
    private Double flow;
    //总充值金额
    private Double recharge;
    //总赠送金额
    private Double present;
    //总消费金额
    private Double payment;
    //总余额
    private Double remain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPresent() {
        return present;
    }

    public void setPresent(Double present) {
        this.present = present;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "TotalPO{" +
                "id=" + id +
                ", flow=" + flow +
                ", recharge=" + recharge +
                ", present=" + present +
                ", payment=" + payment +
                ", remain=" + remain +
                '}';
    }

    public TotalPO(){
        this.flow = 0.0;
        this.recharge = 0.0;
        this.present = 0.0;
        this.payment = 0.0;
        this.remain = 0.0;
    }
}
