package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/6/27.
 * 设备表terminal对应的实体类
 */
public class TerminalPO {
    private int id;
    //设备imei号
    private String imei;
    //设备与华为平台通信使用的编号
    private String deviceId;
    //设备在我们平台的编号
    private String displayId;
    //与设备一一对应的二维码的编号
    private String webId;
    //设备的状态，断开用10表示，闭合用11表示
    private int state;
    //设备放置的位置
    private String location;
    //设备所插sim卡id
    private String simId;
    //设备所插sim卡余额
    private double simRemain;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public double getSimRemain() {
        return simRemain;
    }

    public void setSimRemain(double simRemain) {
        this.simRemain = simRemain;
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
        return "TerminalPO[" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", displayId='" + displayId + '\'' +
                ", webId='" + webId + '\'' +
                ", state=" + state +
                ", location='" + location + '\'' +
                ", simId='" + simId + '\'' +
                ", simRemain=" + simRemain +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
