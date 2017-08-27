package com.terabits.meta.po;

/**
 * Created by Administrator on 2017/8/19.
 */
public class CommandPO {
    private int id;
    //待下发命令的设备编号
    private String deviceId;
    //命令编号
    private String commandIdOne;
    //重发一次的命令编号
    private String commandIdTwo;
    //流量，方便核对
    private double flow;
    //本次命令下发的状态，刚创建是BEGIN_STATE(10)，收到回复确认是HALF_STATE(16)，收到执行完成是END_STATE(26)
    private String  state;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommandIdOne() {
        return commandIdOne;
    }

    public void setCommandIdOne(String commandIdOne) {
        this.commandIdOne = commandIdOne;
    }

    public String getCommandIdTwo() {
        return commandIdTwo;
    }

    public void setCommandIdTwo(String commandIdTwo) {
        this.commandIdTwo = commandIdTwo;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "CommandPO[" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", commandIdOne='" + commandIdOne + '\'' +
                ", commandIdTwo='" + commandIdTwo + '\'' +
                ", flow=" + flow +
                ", state=" + state +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
