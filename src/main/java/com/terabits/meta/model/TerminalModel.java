package com.terabits.meta.model;

/**
 * Created by Administrator on 2017/7/20.
 */
public class TerminalModel {
    private static final long serialVersionUID = -1959528436584592183L;
    private String terminalId;
    private String time;
    private int hour;

    public TerminalModel(){}

    public TerminalModel(String terminalId, String time, int hour) {
        this.terminalId = terminalId;
        this.time = time;
        this.hour = hour;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String  terminalId) {
        this.terminalId = terminalId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "TerminalModel[" +
                "terminalId='" + terminalId + '\'' +
                ", time='" + time + '\'' +
                ", hour=" + hour +
                ']';
    }
}
