package com.terabits.utils;

import com.terabits.meta.bo.TimeSpanBO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/28.
 */
public class TimeSpanUtil{

    public static TimeSpanBO generateTimeSpan(){
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String begin = dfs.format(date) + " 00:00:00";
        String end = dfs.format(date) + " 23:59:59";
        timeSpanBO.setBeginTime(begin);
        timeSpanBO.setEndTime(end);
        return timeSpanBO;
    }
}
