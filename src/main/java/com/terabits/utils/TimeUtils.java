package com.terabits.utils;

import com.terabits.meta.bo.TimeSpanBO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/14.
 */
public class TimeUtils {
    public static String secToTime(long time) {
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static TimeSpanBO getTimeSpan(){
        SimpleDateFormat dfs = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.setTime(now);
        calendar.add(calendar.MINUTE, -3);
        Date end = calendar.getTime();
        calendar.add(calendar.MINUTE, -7);
        Date begin = calendar.getTime();
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        timeSpanBO.setBeginTime(dfs.format(begin));
        timeSpanBO.setEndTime(dfs.format(end));
        return timeSpanBO;
    }

    //根据每笔交易水量，计算最长使用时间
    public static long FlowToTime(double flow){
        long time = 0;
        if(flow == 0.2){
            time = 60;
        }else if(flow == 0.5){
            time = 60;
        }else if(flow == 1){
            time = 60;
        }else if(flow == 2){
            time = 60;
        }
        return time;
    }
}
