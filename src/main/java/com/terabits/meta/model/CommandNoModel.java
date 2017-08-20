package com.terabits.meta.model;

/**
 * Created by Administrator on 2017/7/20.
 */
public class CommandNoModel{
    private static final long serialVersionUID = -1959528436584592183L;
    private String commandId;
    private String number;
    private int hour;

    public CommandNoModel(){}

    public CommandNoModel(String commandId, String number, int hour) {
        this.commandId = commandId;
        this.number = number;
        this.hour = hour;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "CommandNoModel[" +
                "commandId='" + commandId + '\'' +
                ", number=" + number +
                ", hour=" + hour +
                ']';
    }
}
