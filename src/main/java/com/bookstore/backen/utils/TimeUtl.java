package com.bookstore.backen.utils;

import java.sql.Timestamp;

public class TimeUtl {
    /*获取当前的时间*/
    public static Timestamp getNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
    // 将long类型转为时间戳
    public static String longToDate(long lo) {

        int allSeconds = (int) (lo/1000);
        int allMin = allSeconds/60;
        int allHour = allMin / 60;
        int hour = allHour;
        int min = allMin - hour*60;
        int second = allSeconds - hour*60*60-min*60;
        return hour+"小时"+min+"分钟"+second+"秒";

    }


}
