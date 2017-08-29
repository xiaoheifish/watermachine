package com.terabits.meta.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/28.
 */
public class Demo implements Serializable{
    private static final long serialVersionUID = -1959528436584592183L;
    private String commandId;

    private long beginTime;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String toString() {
        return "Demo[" +
                "commandId='" + commandId + '\'' +
                ", beginTime=" + beginTime +
                ']';
    }
}
