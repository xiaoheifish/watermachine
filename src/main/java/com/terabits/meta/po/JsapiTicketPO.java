package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/6/29.
 * 调用微信网页开发相关接口需提供jsapiticket，有效期为2小时
 */
public class JsapiTicketPO {
    private int id;
    //jspaiticket
    private String jsapiTicket;
    //插入数据库的时间，判断是否过期与此时间相比较
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
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
        return "JsapiTicketPO[" +
                "id=" + id +
                ", jsapiTicket='" + jsapiTicket + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
