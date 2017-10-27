package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/10/24.
 */
public class MedalExchangePO {
    private int id;
    //使用勋章兑换余额的用户openId
    private String openId;
    //使用勋章情况
    private String exchange;
    //本次兑换获得的奖励金额
    private Double money;
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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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
        return "MedalExchangePO[" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", exchange='" + exchange + '\'' +
                ", money=" + money +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
