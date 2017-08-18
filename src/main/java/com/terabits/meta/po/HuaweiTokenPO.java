package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/8/18.
 */
public class HuaweiTokenPO {

    private int id;
    //调用华为平台API所用接口，2小时有效
    private String huaweiToken;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHuaweiToken() {
        return huaweiToken;
    }

    public void setHuaweiToken(String huaweiToken) {
        this.huaweiToken = huaweiToken;
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
        return "HuaweiTokenPO[" +
                "id=" + id +
                ", huaweiToken='" + huaweiToken + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
