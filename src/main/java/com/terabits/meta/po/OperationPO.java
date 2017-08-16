package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/6/27.
 * 表示对设备的每一次操作，如编号为000001的插座从断开到闭合，就需要将imei号，状态的变化存入数据库
 */
public class OperationPO {
    private int id;
    //设备imei号
    private String imei;
    //设备状态的变化，由闭合到断开是102，由断开到闭合是21
    private int status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return "OperationPO[" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", status=" + status +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
