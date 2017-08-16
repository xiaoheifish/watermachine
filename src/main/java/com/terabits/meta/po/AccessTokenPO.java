package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/6/27.
 * 调用微信接口需提供accesstoken，有效期为2小时，将该accesstoken存入数据库中
 */
public class AccessTokenPO {
    private int id;
    //accesstoken
    private String accessToken;
    //插入accesstoken的时间，利用该字段判断是否过期
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
        return "AccessTokenPO[" +
                "id='" + id + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
