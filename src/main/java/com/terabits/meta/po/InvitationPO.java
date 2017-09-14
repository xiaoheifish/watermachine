package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/9/13.
 */
public class InvitationPO {
    private int id;
    //邀请人手机号
    private String inviterPhone;
    //被邀请人手机号
    private String inviteePhone;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInviterPhone() {
        return inviterPhone;
    }

    public void setInviterPhone(String inviterPhone) {
        this.inviterPhone = inviterPhone;
    }

    public String getInviteePhone() {
        return inviteePhone;
    }

    public void setInviteePhone(String inviteePhone) {
        this.inviteePhone = inviteePhone;
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
        return "InvitationPO[" +
                "id=" + id +
                ", inviterphone='" + inviterPhone + '\'' +
                ", inviteephone='" + inviteePhone + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
