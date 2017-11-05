package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/10/24.
 * 用户每天的第一笔消费自动记入签到表，签到可用于勋章累计，勋章可用于兑换余额
 */
public class ConsumeSignPO {
    private int id;
    //用户openId
    private String openId;
    //用户本月签到历史
    private long signHistory;
    //用户兑换勋章后本月的签到历史
    private long exchangeHistory;
    //用户连续签到天数
    private int signCount;
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

    public long getSignHistory() {
        return signHistory;
    }

    public void setSignHistory(long signHistory) {
        this.signHistory = signHistory;
    }

    public long getExchangeHistory() {
        return exchangeHistory;
    }

    public void setExchangeHistory(long exchangeHistory) {
        this.exchangeHistory = exchangeHistory;
    }

    public int getSignCount() {
        return signCount;
    }

    public void setSignCount(int signCount) {
        this.signCount = signCount;
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
        return "ConsumeSignPO[" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", signHistory=" + signHistory +
                ", exchangeHistory=" + exchangeHistory +
                ", signCount=" + signCount +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
