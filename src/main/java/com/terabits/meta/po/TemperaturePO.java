package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/6/30.
 * 温度数据，插座会上报温度数据，在温度过高时可以下发关断命令
 */
public class TemperaturePO {
    private int id;
    //设备imei号
    private String imei;
    //插座温度
    private double temperature;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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
        return "TemperaturePO[" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", temperature=" + temperature +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
