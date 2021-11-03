package cn.jinty.util;

import cn.jinty.entity.DateRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间 - 工具类
 *
 * @author Jinty
 * @date 2020/3/25.
 */
public final class DateUtil {

    /**
     * 时间单位 (毫秒表示)
     */
    public static final long MILLISECOND = 1;
    public static final long SECOND = MILLISECOND * 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;

    /**
     * 常用的时间格式
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @return 时间对象
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @param sdf     时间格式
     * @return 时间对象
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf) {
        if (dateStr == null || sdf == null) {
            return null;
        }
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化时间
     *
     * @param date 时间对象
     * @return 字符串
     */
    public static String format(Date date) {
        return format(date, new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 格式化时间
     *
     * @param date 时间对象
     * @param sdf  时间格式
     * @return 字符串
     */
    public static String format(Date date, SimpleDateFormat sdf) {
        if (date == null || sdf == null) {
            return null;
        }
        return sdf.format(date);
    }

    /**
     * 根据输入数值构建时间对象
     *
     * @param year  年
     * @param month 月
     * @param date  日
     * @return 时间
     */
    public static Date buildDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, date);
        return calendar.getTime();
    }

    /**
     * 根据输入数值构建时间对象
     *
     * @param year   年
     * @param month  月
     * @param date   日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return 时间
     */
    public static Date buildDate(int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 时间增加或减少
     *
     * @param date 时间
     * @param num  增减数量 (正数增加，负数减少)
     * @param unit 时间单位 (使用Calendar内置的时间单位)
     * @return 时间
     */
    public static Date add(Date date, int num, int unit) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, num);
        return calendar.getTime();
    }

    /**
     * 计算时间间隔
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @param unit  时间单位 (毫秒数)
     * @return 间隔
     */
    public static Long countInterval(Date begin, Date end, long unit) {
        if (begin == null || end == null) {
            return null;
        }
        if (unit < 1) {
            throw new IllegalArgumentException("time unit cannot less than 1");
        }
        return (begin.getTime() - end.getTime()) / unit;
    }

    /**
     * 计算时间间隔 (单位:天)
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 间隔
     */
    public static Long countIntervalForDay(Date begin, Date end) {
        return countInterval(begin, end, DAY);
    }

    /**
     * 判断时间的某个单位的值是否是目标值
     *
     * @param date   时间
     * @param target 目标值
     * @param unit   时间单位 (使用Calendar内置的时间单位)
     * @return 布尔
     */
    public static boolean isTarget(Date date, int target, int unit) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (Calendar.MONTH == unit) {
            return calendar.get(unit) + 1 == target;
        }
        return calendar.get(unit) == target;
    }

    /**
     * 判断时间是否是今天
     *
     * @param date 时间
     * @return 布尔
     */
    public static boolean isToday(Date date) {
        Date now = new Date();
        return isSameDate(now, date);
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 布尔
     */
    public static boolean isSameDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        String date1Str = sdf.format(date1);
        String date2Str = sdf.format(date2);
        return date1Str.equals(date2Str);
    }

    /**
     * 判断时间是否在一个时间区间内
     *
     * @param date  时间
     * @param begin 起始时间
     * @param end   结束时间
     * @return 布尔
     */
    public static boolean isBetween(Date date, Date begin, Date end) {
        if (date == null || begin == null || end == null) {
            return false;
        }
        return date.compareTo(begin) >= 0 && date.compareTo(end) <= 0;
    }

    /**
     * 获取一天的开始时刻
     *
     * @param date 时间
     * @return 一天的开始时刻
     */
    public static Date getDayBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的结束时刻
     *
     * @param date 时间
     * @return 一天的结束时刻
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取一年的所有星期
     *
     * @param year           年份
     * @param firstDayOfWeek 星期的第一天
     * @return 一年的所有星期
     */
    public static Map<Integer, DateRange> getAllWeekOfYear(int year, int firstDayOfWeek) {
        Map<Integer, DateRange> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        for (int i = 1; i <= 53; i++) {
            calendar.setFirstDayOfWeek(firstDayOfWeek);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, i);
            Date begin = calendar.getTime();
            Date end = getDayEnd(add(begin, 6, Calendar.DATE));
            if (isTarget(end, year, Calendar.YEAR)) {
                map.put(i, new DateRange(begin, end));
            }
            calendar.clear();
        }
        return map;
    }

}
