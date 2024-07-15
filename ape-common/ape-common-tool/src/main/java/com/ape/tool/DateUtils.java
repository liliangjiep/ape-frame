
package com.ape.tool;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类，提供日期相关的操作方法。
 */
@Slf4j
public class DateUtils {

    // 定义常用的日期格式字符串
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String MINUTE_FORMAT = "HH:mm";

    /**
     * 构造函数，私有化以防止外部实例化工具类。
     */
    private DateUtils() {
    }

    /**
     * 获取给定日历日期的开始时间，即当天的零点。
     *
     * @param calendar 日历对象
     * @return 当天开始时间的 Date 对象
     */
    public static Date getStartDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取给定日历日期的结束时间，即当天的23:59:59.999。
     *
     * @param calendar 日历对象
     * @return 当天结束时间的 Date 对象
     */
    public static Date getEndDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 在给定日期上增加指定的天数。
     *
     * @param date 原始日期
     * @param day  需要增加的天数
     * @return 增加天数后的日期
     */
    public static Date addDate(Date date, int day) {
        long millis = date.getTime() + day * 24L * 3600L * 1000L;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 获取给定日期的开始时间，即当天的零点。
     *
     * @param date 日期对象
     * @return 当天开始时间的 Date 对象
     */
    public static Date getStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取给定日期的结束时间，即当天的23:59:59.999。
     *
     * @param date 日期对象
     * @return 当天结束时间的 Date 对象
     */
    public static Date getEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间的周数。
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期之间的周数
     */
    public static int weeksOfTwoDates(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        int i = 0;
        while (!endDate.before(startDate)) {
            i++;
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            endDate = calendar.getTime();
        }
        return i;
    }

    /**
     * 获取当前日期是本周的第几天，周一为第一天。
     *
     * @return 当前日期是本周的第几天
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 ? -6 : 2 - dayOfWeek;
    }

    /**
     * 获取当前日期所在周的周一日期。
     *
     * @return 当前周的周一日期
     */
    public static Date getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DAY_OF_MONTH, mondayPlus);
        return currentDate.getTime();
    }

    /**
     * 获取当前日期所在周的周日日期。
     *
     * @return 当前周的周日日期
     */
    public static Date getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DAY_OF_MONTH, mondayPlus + 6);
        return currentDate.getTime();
    }

    /**
     * 获取最小月份的日期。
     *
     * @return 最小月份的日期
     */
    public static Calendar getMinMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.getActualMinimum(5));
        return calendar;
    }

    /**
     * 获取当前月份的最大日期。
     *
     * @return 当前月份的最大日期
     */
    public static Calendar getMaxMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * 获取上周的最后一天（周日）。
     *
     * @return 上周的最后一天日期
     */
    public static Calendar getLastMonday() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 1 - dayOfWeek;
        calendar.add(Calendar.DAY_OF_MONTH, offset - 7);
        return calendar;
    }

    /**
     * 获取上周的第一天（周一）。
     *
     * @return 上周的第一天日期
     */
    public static Calendar getLastSunday() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = Calendar.DAY_OF_WEEK - dayOfWeek;
        calendar.add(Calendar.DAY_OF_MONTH, offset - 7);
        return calendar;
    }

    /**
     * 获取上个月的第一天日期。
     *
     * @return 上个月的第一天日期
     */
    public static Calendar getLastMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar;
    }

    /**
     * 获取上个月的最后一天日期。
     *
     * @return 上个月的最后一天日期
     */
    public static Calendar getLastMonthEndDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar;
    }

    /**
     * 将日期转换为指定格式的字符串。
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串
     */
    public static String getDateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return date != null ? sdf.format(date) : "";
    }

    /**
     * 获取指定日期之前的日期。
     *
     * @param date 原始日期
     * @param num  需要减去的天数
     * @return 指定天数之前的日期
     */
    public static Date getPreDateStr(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -num);
        Date time = calendar.getTime();
        return time;
    }

    /**
     * 获取本周一的日期。
     *
     * @return 本周一的日期
     */
    public static Date getMondayStrOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            calendar.add(Calendar.DAY_OF_MONTH, -6);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, -(dayWeek - 2));
        }
        Date time = calendar.getTime();
        return time;
    }

    /**
     * 判断给定日期是否为月份的最后一天。
     *
     * @param date 日期对象
     * @return 如果是最后一天返回true，否则返回false
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int now = calendar.get(Calendar.DAY_OF_MONTH);
        return now == lastDay;
    }

    /**
     * 计算两个日期之间的天数。
     *
     * @param startDateStr 开始日期字符串
     * @param endDateStr   结束日期字符串
     * @return 两个日期之间的天数
     * @throws Exception 日期格式不正确时抛出异常
     */
    public static int daysOfTwo(String startDateStr, String endDateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);
        return (int) ((endDate.getTime() - startDate.getTime()) / 1000L / 60L / 60L / 24L);
    }

    /**
     * 获取最近12天的日期列表。
     *
     * @return 最近12天的日期字符串列表
     */
    public static List<String> getContinue12Days() {
        List<String> list = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -11);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        for (int i = 0; i <= 11; ++i) {
            Date time = calendar.getTime();
            list.add(sdf.format(time));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 根据日期字符串获取该周的周数。
     *
     * @param dateStr 日期字符串
     * @return 该日期所在的周数
     * @throws Exception 日期格式不正确时抛出异常
     */
    public static String getWeeksOfYear(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        if (i >= 10) {
            return dateStr.substring(2, 4) + "-" + i;
        } else {
            return dateStr.substring(2, 4) + "-0" + i;
        }
    }

    /**
     * 将日期字符串转换为日期对象。
     *
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws Exception 日期格式不正确时抛出异常
     */
    public static Date getDateByStr(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.parse(dateStr);
    }
}