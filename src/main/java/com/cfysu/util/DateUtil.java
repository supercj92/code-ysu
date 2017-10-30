package com.cfysu.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public final String FORMATE_FULL = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] arg){
//        Calendar calendarNow = Calendar.getInstance();
//        System.out.println(calendarNow.getTime());
//        System.out.println(calendarNow.get(Calendar.HOUR_OF_DAY));
//        System.out.println(calendarNow.get(Calendar.MINUTE));
//        System.out.println(calendarNow.get(Calendar.SECOND));
//
//        Calendar calendarStart = Calendar.getInstance();
//        calendarStart.set(Calendar.HOUR_OF_DAY, 25);
//        calendarStart.set(Calendar.MINUTE, 12);
//        calendarStart.set(Calendar.SECOND, 12);
//        System.out.println("startTime:" + calendarStart.getTime());
        //String toTrimStr = " 1 0 ";
        //System.out.println("trimed:" + toTrimStr.trim());
//
//        System.out.println("日期比较结果：" + calendarStart.after(calendarNow));
        DateUtil dateUtil = new DateUtil();
        dateUtil.testGetDeliveryInfo();
    }

    public void testGetDeliveryInfo(){
        Date startDate = parseStr2Date("2017-9-25 09:00:00", FORMATE_FULL);
        Date endDate = parseStr2Date("2017-9-28 24:00:00", FORMATE_FULL);
        String deliverInfo = getDeliveryInfo(startDate, endDate);
        System.out.println("deliverInfo:" + deliverInfo);
    }

    private String getDeliveryInfo(Date start, Date end){

        StringBuilder deliveryInfo = new StringBuilder();
        if(start == null || end == null){
            return deliveryInfo.toString();
        }

        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(start);
        calendarEnd.setTime(end);

        setYMD(calendarStart, calendarNow);
        setYMD(calendarEnd, calendarNow);

        int hour = calendarStart.get(Calendar.HOUR_OF_DAY);
        String hourStr = hour < 10 ? "0" + hour : "" + hour;
        int minute = calendarStart.get(Calendar.MINUTE);
        String minuteStr = minute < 10 ? "0" + minute : "" + minute;

        if(calendarNow.before(calendarEnd) || (calendarEnd.get(Calendar.HOUR_OF_DAY) == 0 && calendarEnd.get(Calendar.MINUTE) == 0  && calendarEnd.get(Calendar.SECOND) == 0)){
            deliveryInfo.append("今日").append(hourStr).append(":").append(minuteStr).append("起送");
        }else if(calendarNow.after(calendarEnd)){
            deliveryInfo.append("明日").append(hourStr).append(":").append(minuteStr).append("起送");
        }

        return deliveryInfo.toString();
    }

    public Date parseStr2Date(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setYMD(Calendar calendarFrom, Calendar calendarTo){
        calendarFrom.set(Calendar.YEAR, calendarTo.get(Calendar.YEAR));
        calendarFrom.set(Calendar.MONTH, calendarTo.get(Calendar.MONTH));
        calendarFrom.set(Calendar.DAY_OF_MONTH, calendarTo.get(Calendar.DAY_OF_MONTH));
    }
}
