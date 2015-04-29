package com.jiezhu.pms.comm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Description: 日期时间工具类
 * @author
 * @create
 */
public class DateUtil {
    /**
     * @Title:dataPlusInterval
     * @Description:日期增加(Date格式操作)
     * @param source
     * @param interval
     * @return Date
     */
    public static Date dataPlusInterval(Date source, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + interval);
        return calendar.getTime();
    }

    /**
     * @Title:getAppointDay
     * @Description:TODO(得到指定日期)
     * @param @param num
     * @param @param fmt
     * @param @return 设定文件
     * @return String
     * @throws
     *
     * @param num
     * @param fmt
     * @return
     */
    public static String getAppointDay(String fmt, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date tmp = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        String date = sdf.format(tmp);
        return date;
    }

    /**
     * @Description: 获取当前系统日期 格式:yyyy-mm-dd
     * @return String 日期字符串
     * @throws
     */
    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd");
    }

    /**
     *
     * @Description: 获取当前系统日期
     * @param format
     *            日期格式
     * @return String 日期字符串
     * @throws
     */
    public static String getCurrentDate(String format) {
        return getCurrentDateTimeStr(format);
    }

    /**
     *
     * @Description: 获取当前日期时间字符串
     * @param format
     *            日期时间格式
     * @return String 日期时间字符串
     * @throws
     */
    public static String getCurrentDateTimeStr(String format) {
        DateFormat formater = new SimpleDateFormat(format);
        return formater.format(new Date());
    }

    /**
     * @Description: 获取当前系统时间 格式:HH:mm:ss
     * @return String 时间字符串
     * @throws
     */
    public static String getCurrentTime() {
        return getCurrentDate("HH:mm:ss");
    }

    /**
     *
     * @Description: 获取当前系统时间
     * @param format
     *            时间格式
     * @return String 时间字符串
     * @throws
     */
    public static String getCurrentTime(String format) {
        return getCurrentDateTimeStr(format);
    }

    /**
     * @Description: 获取当前系统时间戳 格式:yyyy-mm-dd HH:mm:ss
     * @return String 时间字符串
     * @throws
     */
    public static String getCurrentTimeStamp() {
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     *
     * @Description: 获取当前系统时间戳
     * @param format
     *            时间格式
     * @return String 时间字符串
     * @throws
     */
    public static String getCurrentTimeStamp(String format) {
        return getCurrentDateTimeStr(format);
    }

    /**
     * @Title: getDate
     * @Description: 获取当前系统日期 格式:yyyymmdd
     * @return String
     * @throws
     */
    public static String getDate() {
        return getCurrentDate("yyyyMMdd");
    }

    /**
     *
     * 功能描述：根据给定的格式将str转化为Date类型
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getDate(String dateStr, String format) {
        DateFormat formater = new SimpleDateFormat(format);
        Date result;
        try {
            result = formater.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @Description:日期转化为字符串
     * @param date
     *            日期
     * @param format
     *            日期格式
     * @return 日期字符串
     */
    public static String getDateStr(Date date, String format) {
        DateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.format(date);
    }

    /**
     *
     * @Description: 将字符串从一种格式转化的
     * @param @param source 日期原串
     * @param @param infmt 日期输入格式
     * @param @param outfmt 日期输出格式
     * @param @return 目标日期字符串
     * @throws ParseException
     */
    public static String getFmtDate(String source, String infmt, String outfmt)
            throws ParseException {
        // 输入格式
        DateFormat informater = new SimpleDateFormat(infmt);
        // 输出格式
        DateFormat outfomater = new SimpleDateFormat(outfmt);
        return outfomater.format(informater.parse(source));
    }

    /**
     *
     * @Description: 将字符串从一种格式转化的
     * @param @param source 日期原串
     * @param @param infmt 日期输入格式
     * @param @param outfmt 日期输出格式
     * @param @return 目标日期字符串
     * @throws ParseException
     */
    public static String getFmtDateStr(String source, String infmt,
            String outfmt) {
        String result = "";
        try {
            // 输入格式
            DateFormat informater = new SimpleDateFormat(infmt);
            // 输出格式
            DateFormat outfomater = new SimpleDateFormat(outfmt);
            result = outfomater.format(informater.parse(source));
        } catch (Exception e) {
            result = source;
        }
        return result;
    }

    public static String secondsToHourMinute(long sTime) {
        String format;
        if (sTime >= 3600) {
            format = "H小时m分钟";
        } else {
            format = "m分钟";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);// 初始化Formatter的转换格式。
        long ms = sTime * 1000;
        return formatter.format(ms - TimeZone.getDefault().getRawOffset());
    }

    /**
     * @Title:sourcePlusInterval
     * @Description:日期增加
     * @param source
     * @param interval
     * @return
     * @throws Exception
     * @return String
     */
    public static String sourcePlusInterval(String source, int interval) {
        DateFormat informater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(informater.parse(source));
        } catch (ParseException e) {
        }
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + interval);
        return informater.format(calendar.getTime());
    }
}