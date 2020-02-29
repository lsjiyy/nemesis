package com.lsjyy.nemesis.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-06 20:57
 * @Description: 日期转换类
 */
public class TimestampUtil {

    public static final String DATE_TIME_NORMAL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME = "yyyyMMddHHmmss";
    public static final String DATE = "yyyyMMdd";
    public static final String YEAR_MONTH = "yyyyMM";
    public static final String TIME = "HHmmss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String HH_MM = "HH:mm";

    /**
     * 字符串转日期
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Timestamp parse(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return new Timestamp(sdf.parse(date).getTime());
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String format(Timestamp timestamp, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(timestamp);
    }

    /**
     * 计算给定日期偏移指定年数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移年数，正值增加，负值减少
     * @return
     */
    public static Timestamp addYears(Date date, int amount) {
        return new Timestamp(DateUtils.addYears(date, amount).getTime());
    }

    /**
     * 计算给定日期偏移指定月数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移月数，正值增加，负值减少
     * @return
     */
    public static Timestamp addMonths(Date date, int amount) {
        return new Timestamp(DateUtils.addMonths(date, amount).getTime());
    }

    /**
     * 计算给定日期偏移指定星期数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移星期数，正值增加，负值减少
     * @return
     */
    public static Timestamp addWeeks(Date date, int amount) {
        return new Timestamp(DateUtils.addWeeks(date, amount).getTime());
    }

    /**
     * 计算给定日期偏移指定日数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移日数，正值增加，负值减少
     * @return
     */
    public static Timestamp addDays(Date date, int amount) {
        return new Timestamp(DateUtils.addDays(date, amount).getTime());
    }


    /**
     * 计算给定日期偏移指定小时数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移小时数，正值增加，负值减少
     * @return
     */

    public static Timestamp addHours(Date date, int amount) {
        return new Timestamp(DateUtils.addHours(date, amount).getTime());
    }

    /**
     * 计算给定日期偏移指定分钟数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移分钟数，正值增加，负值减少
     * @return
     */

    public static Timestamp addMinutes(Date date, int amount) {
        return new Timestamp(DateUtils.addMinutes(date, amount).getTime());
    }

    /**
     * 计算给定日期偏移指定秒数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移秒数，正值增加，负值减少
     * @return
     */

    public static Timestamp addSeconds(Date date, int amount) {
        return new Timestamp(DateUtils.addSeconds(date, amount).getTime());
    }

    /**
     * 计算给定日期偏移指定毫秒数后的日期
     *
     * @param date   给定日期
     * @param amount 偏移毫秒数，正值增加，负值减少
     * @return
     */

    public static Timestamp addMilliseconds(Date date, int amount) {
        return new Timestamp(DateUtils.addMilliseconds(date, amount).getTime());
    }

    /**
     * 日期截断
     *
     * @param date  给定日期
     * @param field
     * @return
     * @see Calendar
     * @see DateUtils
     */
    public static Timestamp truncate(Date date, int field) {
        return new Timestamp(DateUtils.truncate(date, field).getTime());
    }

    /**
     * 日期向上取整
     *
     * @param date  给定日期
     * @param field
     * @return
     * @see Calendar
     * @see DateUtils
     */
    public static Timestamp ceiling(Date date, int field) {
        return new Timestamp(DateUtils.ceiling(date, field).getTime());
    }

    /**
     * 日期四舍五入
     *
     * @param date  给定日期
     * @param field
     * @return
     * @see Calendar
     * @see DateUtils
     */
    public static Timestamp round(Date date, int field) {
        return new Timestamp(DateUtils.round(date, field).getTime());
    }

    /**
     * 计算星期
     *
     * @param timestamp 给定日期
     * @return 周一 = 1 周日 = 7
     */
    public static int getDayOfWeek(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        int num = calendar.get(Calendar.DAY_OF_WEEK);
        if (1 == num)
            num = 7;
        else
            num = num - 1;
        return num;
    }

    /**
     * 获取日期的年,格式: YYYY, 例如: 2016
     *
     * @param timestamp 给定日期
     * @return
     */
    public static int getYear(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月份,格式: 1 - 12
     *
     * @param timestamp 给定日期
     * @return
     */
    public static int getMonth(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的天,格式: 1 - 31
     *
     * @param timestamp 给定日期
     * @return
     */
    public static int getDay(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的小时,格式: 0 - 23
     *
     * @param timestamp 给定日期
     * @return
     */
    public static int getHour(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟,格式: 0 - 59
     *
     * @param timestamp 给定日期
     * @return
     */
    public static int getMinute(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒,格式: 0 - 60
     *
     * @param timestamp 给定日期
     * @return
     */
    public static int getSecond(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取给定日期之间月数，包含当前月（非整月算一个月,精确到月）
     *
     * @param beginTime 起始时间
     * @param endTime   结束时间
     * @return 月数
     */
    public static int monthsBetween(Timestamp beginTime, Timestamp endTime) {
        int years = TimestampUtil.getYear(endTime) - TimestampUtil.getYear(beginTime);
        int months = TimestampUtil.getMonth(endTime) - TimestampUtil.getMonth(beginTime);
        return years * 12 + months;
    }

    public static long dayBetween(Timestamp beginTime, Timestamp endTime) {
        return (endTime.getTime() - beginTime.getTime()) / (1000 * 60 * 60 * 24);
    }

    public static long hourBetween(Timestamp beginTime, Timestamp endTime) {
        return (endTime.getTime() - beginTime.getTime()) / (1000 * 60 * 60);
    }

    /**
     * 间隔分钟数
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long minuteBetween(Timestamp beginTime, Timestamp endTime) {
        return (endTime.getTime() - beginTime.getTime()) / (1000 * 60);
    }

    public static long secondBetween(Timestamp beginTime, Timestamp endTime) {
        return (endTime.getTime() - beginTime.getTime()) / (1000);
    }

    public static Timestamp string2Timestamp(String time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Timestamp(date.getTime());
    }

    /**
     * 指定月最后一天
     *
     * @return
     */
    public static Timestamp getMonthEndDay(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getMonthFirstDay(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 下月第一天
     *
     * @return
     */
    public static String getFirstDayOfNextMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    //--指定日期当天结束时间
    public static Timestamp endDay(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //指定当天的时间
    public static Timestamp getTime(String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(System.currentTimeMillis()));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(time.split(":")[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //--指定日期当天开始时间
    public static Timestamp beginDay(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取指定月份的天数
     *
     * @param nYear
     * @param nMonth
     * @return
     */
    public static int getMaxDay(Integer nYear, Integer nMonth) {
        if (nMonth < 1 || nMonth > 12) return -1;
        if (nYear < 1000 || nYear > 9999) return -1;
        int[] maxday = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (nMonth != 2) {
            return maxday[nMonth - 1];
        } else {
            return IsLeapYear(nYear) == 0 ? 29 : 28;
        }
    }

    /**
     * 指定年份是不是闰年
     *
     * @param nYear
     * @return
     */
    public static int IsLeapYear(int nYear) {
        if (nYear % 4 == 0 && nYear % 100 != 0 || nYear % 400 == 0)
            return 0;
        else
            return -1;
    }

    public static Timestamp changeDayOfMonth(Timestamp timestamp, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp changeDayOfWeek(Timestamp timestamp, int weekDay) {
        if (7 == weekDay) {
            weekDay = 1;
            timestamp = addWeeks(new Date(timestamp.getTime()), 1);
        } else
            weekDay = weekDay + 1;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.DAY_OF_WEEK, weekDay);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM( 2017-02)
     *
     * @param strDate
     * @return
     */
    public static Date strToDateNotDD(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取指定日期的第一天
     *
     * @param datestr
     * @return
     */
    public static Timestamp getFirstDayOfMonth(String datestr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = strToDateNotDD(datestr);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return Timestamp.valueOf(sdf.format(dates));
    }

    /**
     * 获取指定日期的最后一天
     *
     * @param datestr
     * @return
     */
    public static Timestamp getLastDayOfMonth(String datestr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = strToDateNotDD(datestr);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return Timestamp.valueOf(sdf.format(dates));
    }
    
}
