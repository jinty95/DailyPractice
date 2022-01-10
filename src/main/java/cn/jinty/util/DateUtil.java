package cn.jinty.util;

import cn.jinty.entity.Week;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public static final long MILLISECOND = 1L;
    public static final long SECOND = MILLISECOND * 1000L;
    public static final long MINUTE = SECOND * 60L;
    public static final long HOUR = MINUTE * 60L;
    public static final long DAY = HOUR * 24L;

    /**
     * 常用的时间格式
     */
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "HH:mm:ss";
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String WHOLE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String COMPACT_WHOLE = "yyyyMMddHHmmssSSS";

    /**
     * 星期的每一天 (英文+中文)
     */
    public static String[] dayOfWeekEn = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
    public static String[] dayOfWeekCn = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @return 时间对象
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, DATETIME);
    }

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @param format  时间格式
     * @return 时间对象
     */
    public static Date parse(String dateStr, String format) {
        if (dateStr == null || format == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(dateStr);
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
        return format(date, DATETIME);
    }

    /**
     * 格式化时间
     *
     * @param date   时间对象
     * @param format 时间格式
     * @return 字符串
     */
    public static String format(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
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
     * 获取下一个时间点 (满足指定的时分秒)
     *
     * @param date   参考时间点
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒
     * @return 下一个时间点
     */
    public static Date nextTime(Date date, int hour, int minute, int second) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        int nowMinute = calendar.get(Calendar.MINUTE);
        int nowSecond = calendar.get(Calendar.SECOND);
        if (nowHour > hour || (nowHour == hour && nowMinute > minute)
                || (nowHour == hour && nowMinute == minute && nowSecond >= second)) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取下一个时间点 (满足指定的分秒)
     *
     * @param date   参考时间点
     * @param minute 分钟
     * @param second 秒
     * @return 下一个时间点
     */
    public static Date nextTime(Date date, int minute, int second) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int nowMinute = calendar.get(Calendar.MINUTE);
        int nowSecond = calendar.get(Calendar.SECOND);
        if (nowMinute > minute || (nowMinute == minute && nowSecond >= second)) {
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取下一个时间点 (满足指定的秒)
     *
     * @param date   参考时间点
     * @param second 秒
     * @return 下一个时间点
     */
    public static Date nextTime(Date date, int second) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int nowSecond = calendar.get(Calendar.SECOND);
        if (nowSecond >= second) {
            calendar.add(Calendar.MINUTE, 1);
        }
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取时间的某个时间单位上的值
     *
     * @param date 时间
     * @param unit 时间单位 (使用Calendar内置的时间单位)
     * @return 整数数值
     */
    public static int get(Date date, int unit) {
        if (date == null) {
            throw new IllegalArgumentException("输入时间不能为空");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (Calendar.MONTH == unit) {
            return calendar.get(unit) + 1;
        }
        return calendar.get(unit);
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
     * 计算相差月份 (只考虑年月，其余忽略)
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差月份
     */
    public static Long getDiffMonth(Date begin, Date end) {
        if (begin == null || end == null) {
            return null;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(begin);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        return (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12L
                + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
    }

    /**
     * 计算相差天数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差天数
     */
    public static Long getDiffDay(Date begin, Date end) {
        return getDiff(begin, end, DAY);
    }

    /**
     * 计算相差小时数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差小时数
     */
    public static Long getDiffHour(Date begin, Date end) {
        return getDiff(begin, end, HOUR);
    }

    /**
     * 计算相差分钟数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差分钟数
     */
    public static Long getDiffMinute(Date begin, Date end) {
        return getDiff(begin, end, MINUTE);
    }

    /**
     * 计算相差秒数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差秒数
     */
    public static Long getDiffSecond(Date begin, Date end) {
        return getDiff(begin, end, SECOND);
    }

    /**
     * 计算相差毫秒数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差毫秒数
     */
    public static Long getDiff(Date begin, Date end) {
        return getDiff(begin, end, MILLISECOND);
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
        return get(date, unit) == target;
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
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DATE) == c2.get(Calendar.DATE);
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
     * @return 开始时刻
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
     * @return 结束时刻
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
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月份的开始时刻
     *
     * @param date 时间
     * @return 开始时刻
     */
    public static Date getMonthBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月份的结束时刻
     *
     * @param date 时间
     * @return 结束时刻
     */
    public static Date getMonthEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 先获取下个月的开始时刻
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        // 然后减1秒
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    /**
     * 获取一年的所有星期
     *
     * @param year           年份
     * @param firstDayOfWeek 星期的第一天 (使用Calendar内置的星期)
     * @return 一年的所有星期
     */
    public static List<Week> getAllWeekOfYear(int year, int firstDayOfWeek) {
        List<Week> weeks = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <= 53; i++) {
            calendar.clear();
            calendar.setFirstDayOfWeek(firstDayOfWeek);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, i);
            Date begin = calendar.getTime();
            Date end = getDayEnd(add(begin, 6, Calendar.DATE));
            if (isTarget(end, year, Calendar.YEAR)) {
                weeks.add(new Week(i, String.format("第%d周", i), begin, end));
            }
        }
        return weeks;
    }

    /**
     * 判断时间是星期几 (英文)
     *
     * @param date 时间
     * @return 星期几 (英文)
     */
    public static String getDayOfWeekEn(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return dayOfWeekEn[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 判断时间是星期几 (中文)
     *
     * @param date 时间
     * @return 星期几 (中文)
     */
    public static String getDayOfWeekCn(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return dayOfWeekCn[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 判断是否为闰年
     *
     * @param year 年份
     * @return 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        // 四年一闰，百年不闰，四百年再闰
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 判断是否为闰年
     *
     * @param date 时间
     * @return 是否为闰年
     */
    public static boolean isLeapYear(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("输入时间不能为空");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return isLeapYear(calendar.get(Calendar.YEAR));
    }

    /**
     * 获取最大时间
     *
     * @param d1 时间1
     * @param d2 时间2
     * @return 最大时间
     */
    public static Date max(Date d1, Date d2) {
        if (d1 == null) {
            return d2;
        }
        if (d2 == null) {
            return d1;
        }
        return d1.after(d2) ? d1 : d2;
    }

    /**
     * 获取最大时间
     *
     * @param dates 时间列表
     * @return 最大时间
     */
    public static Date max(List<Date> dates) {
        if (dates == null || dates.isEmpty()) {
            return null;
        }
        Date res = dates.get(0);
        for (int i = 1; i < dates.size(); i++) {
            res = max(res, dates.get(i));
        }
        return res;
    }

    /**
     * 获取最小时间
     *
     * @param d1 时间1
     * @param d2 时间2
     * @return 最小时间
     */
    public static Date min(Date d1, Date d2) {
        if (d1 == null) {
            return d2;
        }
        if (d2 == null) {
            return d1;
        }
        return d1.before(d2) ? d1 : d2;
    }

    /**
     * 获取最小时间
     *
     * @param dates 时间列表
     * @return 最小时间
     */
    public static Date min(List<Date> dates) {
        if (dates == null || dates.isEmpty()) {
            return null;
        }
        Date res = dates.get(0);
        for (int i = 1; i < dates.size(); i++) {
            res = min(res, dates.get(i));
        }
        return res;
    }

    /* 以下为内部函数 */

    /**
     * 计算时间差
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @param unit  时间单位 (毫秒数)
     * @return 时间差
     */
    private static Long getDiff(Date begin, Date end, long unit) {
        if (begin == null || end == null) {
            return null;
        }
        if (unit < 1) {
            throw new IllegalArgumentException("时间单位(毫秒数)不能小于1");
        }
        return (begin.getTime() - end.getTime()) / unit;
    }

}
