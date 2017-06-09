package com.cfysu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 时间工具类
 * @author Ruibron
 */
public class DateUtil {
	public static final String SIMPLE_DATE_FORMAT_CN_MIN = "yyyy年MM月dd日 HH点mm分";
	public static final String SIMPLE_DATE_FORMAT_CN_HOUR = "yyyy年MM月dd日 HH点整";
	public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
	public static final String YMD_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 解析date类型为string
	 * @return
	 */
	public static String parseDate2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
		return sdf.format(date);
	}
	
	/**
	 * 按指定格式解析date类型
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String parseDate2StringFormat(Date date ,String formatString){
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(date);
	}
	
	/**
	 * 解析date类型为string，为团购时间显示专用
	 * @return
	 */
	public static String parseFullDate2String(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.MINUTE);
		SimpleDateFormat sdf = null;
		if(i==0){
			sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT_CN_HOUR);
		}else{
			sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT_CN_MIN);
		}
		return sdf.format(date);
	}
	
	/**
	 * 解析date类型为string，为团购时间显示专用
	 * @return
	 */
	public static String parseDate(Date date) {
		Calendar c = Calendar.getInstance();
		if(date!=null){
			c.setTime(date);
			SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE_FORMAT);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * 格式化时间，格式
	 * @title parseString2Date
	 * @author changpeng
	 * @date 2015-4-20 下午7:54:16
	 * @param date
	 * @return Date
	 */
	public static Date parseString2Date(String date, String format){
		if(StringUtils.isBlank(format)){
			format = YMD_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			if(StringUtils.isBlank(date)){
				return sdf.parse(parseDate2StringFormat(new Date(),YMD_DATE_FORMAT));
			}
			return sdf.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	/**
     * 日期-新增天
     * @param date
     * @param hours
     * @return
     */
    public static Date addDays(Date date, int days){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, days);
    	return calendar.getTime();
    }
    
    /**
     * 日期-新增天,到计算完日期的00:00:00
     * @param date
     * @param hours
     * @return
     */
    public static Date addDaysStart(Date date, int days){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, days);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return calendar.getTime();
    }
    
    /**
     * 日期-新增天,到计算完日期的23:59:59
     * @param date
     * @param hours
     * @return
     */
    public static Date addDaysEnd(Date date, int days){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, days);
    	calendar.set(Calendar.HOUR_OF_DAY, 23);
    	calendar.set(Calendar.MINUTE, 59);
    	calendar.set(Calendar.SECOND, 59);
    	return calendar.getTime();
    }

    public static Date getDay(int before){
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, before);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
    }
    
    /**
     * 获取几天前的日期字符串
     * 2015-09-25
     * @author weichao
     * @param after
     * @return
     */
    public static String getDayString(int after) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, after);
        return parseDate2StringFormat(cal.getTime(), YMD_DATE_FORMAT);
    }
    
    /**
     * 根据某天的日期字符串和间隔的天数，获取另外一天的日期字符串
     * @author weichao
     * @param dayString 
     * @param after
     * @return
     */
    public static String getDayString(String dayString, int after) {
        Date date = parseString2Date(dayString, YMD_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, after);
        return parseDate2StringFormat(calendar.getTime(), YMD_DATE_FORMAT);
    }
    
    /**
     * 判断一个日期类型是否合法
     * @param dateString
     * @return
     */
    public static boolean isDateValid(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(dateString);
        
        if (!matcher.matches()) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_DATE_FORMAT);
        try {
            sdf.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
