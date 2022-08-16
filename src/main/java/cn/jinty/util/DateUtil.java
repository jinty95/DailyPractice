package cn.jinty.util;

import cn.jinty.enums.CycleTypeEnum;
import cn.jinty.entity.date.DateRange;
import cn.jinty.entity.date.Week;

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
    public static final long MILLISECOND = 1L;
    public static final long SECOND = MILLISECOND * 1000L;
    public static final long MINUTE = SECOND * 60L;
    public static final long HOUR = MINUTE * 60L;
    public static final long DAY = HOUR * 24L;
    public static final long MONTH = DAY * 30L;
    public static final long YEAR = DAY * 365L;

    /**
     * 常用的时间格式
     */
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "HH:mm:ss";
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String WHOLE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String COMPACT_DATE = "yyyyMMdd";
    public static final String COMPACT_WHOLE = "yyyyMMddHHmmssSSS";
    public static final String SIMPLE_DATE = "yy-MM-dd";
    public static final String SIMPLE_COMPACT_DATE = "yyMMdd";

    /**
     * 时区
     */
    public static final String ASIA_SHANGHAI = "Asia/Shanghai"; // 东八区
    public static final String ASIA_TOKYO = "Asia/Tokyo"; // 东九区
    public static final String EUROPE_LONDON = "Europe/London"; // 东一区
    public static final String GMT_0 = "GMT+0"; // 零时区

    /**
     * 时间纪元 (时间戳的起点) (零时区)
     */
    public static final String EPOCH_STR = "1970-01-01 00:00:00";
    public static final Date EPOCH = parse(EPOCH_STR, DATETIME, GMT_0);

    /**
     * 星期的每一天 (英文+中文)
     */
    private static final String[] DAY_OF_WEEK_EN = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
    private static final String[] DAY_OF_WEEK_CN = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 十二星座
     */
    private static final int[] CONSTELLATION_DAYS = {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] CONSTELLATIONS = {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    /**
     * 十二生肖
     */
    private static final String[] CHINESE_ZODIACS = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    /**
     * 解析时间
     *
     * @param dateStr 时间字符串
     * @return 时间对象
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, DATETIME);
    }

    /**
     * 解析时间
     *
     * @param dateStr 时间字符串
     * @param format  时间格式
     * @return 时间对象
     */
    public static Date parse(String dateStr, String format) {
        return parse(dateStr, format, null);
    }

    /**
     * 解析时间
     *
     * @param dateStr  时间字符串
     * @param format   时间格式
     * @param timezone 时区
     * @return 时间对象
     */
    public static Date parse(String dateStr, String format, String timezone) {
        if (dateStr == null || format == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (timezone != null) {
                sdf.setTimeZone(TimeZone.getTimeZone(timezone));
            }
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
     * @return 时间字符串
     */
    public static String format(Date date) {
        return format(date, DATETIME);
    }

    /**
     * 格式化时间
     *
     * @param date   时间对象
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String format(Date date, String format) {
        return format(date, format, null);
    }

    /**
     * 格式化时间
     *
     * @param date     时间对象
     * @param format   时间格式
     * @param timezone 时区
     * @return 时间字符串
     */
    public static String format(Date date, String format, String timezone) {
        if (date == null || format == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (timezone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
        }
        return sdf.format(date);
    }

    /**
     * 来自某时区的时间字符串，转为另一个时区的时间字符串
     *
     * @param srcDateStr   源时间字符串
     * @param srcFormat    源时间格式
     * @param srcTimeZone  源时区
     * @param destTimeZone 目标时区
     * @return 目标时间字符串
     */
    public static String transfer(String srcDateStr, String srcFormat, String srcTimeZone, String destTimeZone) {
        if (srcDateStr == null || srcFormat == null) {
            return null;
        }
        return format(parse(srcDateStr, srcFormat, srcTimeZone), srcFormat, destTimeZone);
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
        checkNull(date);
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
        checkNull(date);
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
        checkNull(date);
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
        checkNull(date);
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
        checkNull(date);
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
    public static long getDiffMonth(Date begin, Date end) {
        checkNull(begin, end);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(begin);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        return Math.abs((c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12L + (c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH)));
    }

    /**
     * 计算相差天数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差天数
     */
    public static long getDiffDay(Date begin, Date end) {
        return getDiff(begin, end, DAY);
    }

    /**
     * 计算相差小时数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差小时数
     */
    public static long getDiffHour(Date begin, Date end) {
        return getDiff(begin, end, HOUR);
    }

    /**
     * 计算相差分钟数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差分钟数
     */
    public static long getDiffMinute(Date begin, Date end) {
        return getDiff(begin, end, MINUTE);
    }

    /**
     * 计算相差秒数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差秒数
     */
    public static long getDiffSecond(Date begin, Date end) {
        return getDiff(begin, end, SECOND);
    }

    /**
     * 计算相差毫秒数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相差毫秒数
     */
    public static long getDiff(Date begin, Date end) {
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
        checkNull(date);
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
        if (date1 == null) {
            throw new IllegalArgumentException("时间1不能为空");
        }
        if (date2 == null) {
            throw new IllegalArgumentException("时间2不能为空");
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
        checkNull(date);
        checkNull(begin, end);
        return date.compareTo(begin) >= 0 && date.compareTo(end) <= 0;
    }

    /**
     * 获取一天的开始时刻
     *
     * @param date 时间
     * @return 开始时刻
     */
    public static Date getDayBegin(Date date) {
        checkNull(date);
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
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取一周的开始时刻
     *
     * @param date           时间
     * @param firstDayOfWeek 星期的第一天 (使用Calendar内置的星期)
     * @return 开始时刻
     */
    public static Date getWeekBegin(Date date, int firstDayOfWeek) {
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -(dayOfWeek + 7 - firstDayOfWeek) % 7);
        return calendar.getTime();
    }

    /**
     * 获取一周的结束时刻
     *
     * @param date           时间
     * @param firstDayOfWeek 星期的第一天 (使用Calendar内置的星期)
     * @return 结束时刻
     */
    public static Date getWeekEnd(Date date, int firstDayOfWeek) {
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, (firstDayOfWeek + 6 - dayOfWeek) % 7);
        return calendar.getTime();
    }

    /**
     * 获取一个月份的开始时刻
     *
     * @param date 时间
     * @return 开始时刻
     */
    public static Date getMonthBegin(Date date) {
        checkNull(date);
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
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 先获取下个月的开始时刻
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        // 然后减1毫秒
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 获取一年的开始时刻
     *
     * @param date 时间
     * @return 开始时刻
     */
    public static Date getYearBegin(Date date) {
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一年的结束时刻
     *
     * @param date 时间
     * @return 结束时刻
     */
    public static Date getYearEnd(Date date) {
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 时间分割 (根据周期类型将一个时间段分为多个时间段)
     *
     * @param begin     开始时间
     * @param end       结束时间
     * @param cycleType 周期类型
     * @return 时间段列表
     */
    public static List<DateRange> splitByCycle(Date begin, Date end, CycleTypeEnum cycleType) {
        switch (cycleType) {
            case DAY:
                return splitByDay(begin, end);
            case WEEK:
                return splitByWeek(begin, end, Calendar.MONDAY);
            case MONTH:
                return splitByMonth(begin, end);
            case YEAR:
                return splitByYear(begin, end);
            default:
                throw new IllegalArgumentException("不支持的周期类型");
        }
    }

    /**
     * 时间分割 (按天分割)
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 时间段列表
     */
    public static List<DateRange> splitByDay(Date begin, Date end) {
        checkNull(begin, end);
        List<DateRange> list = new ArrayList<>();
        Date firstDayEnd = getDayEnd(begin);
        if (end.before(firstDayEnd)) {
            list.add(new DateRange(begin, end));
            return list;
        }
        list.add(new DateRange(begin, firstDayEnd));
        for (Date t1 = add(getDayBegin(begin), 1, Calendar.DATE); t1.before(end); t1 = add(t1, 1, Calendar.DATE)) {
            Date t2 = getDayEnd(t1);
            if (end.before(t2)) {
                list.add(new DateRange(t1, end));
            } else {
                list.add(new DateRange(t1, t2));
            }
        }
        return list;
    }

    /**
     * 时间分割 (按周分割)
     *
     * @param begin          开始时间
     * @param end            结束时间
     * @param firstDayOfWeek 星期的第一天 (使用Calendar内置的星期)
     * @return 时间段列表
     */
    public static List<DateRange> splitByWeek(Date begin, Date end, int firstDayOfWeek) {
        checkNull(begin, end);
        List<DateRange> list = new ArrayList<>();
        Date firstWeekEnd = getWeekEnd(begin, firstDayOfWeek);
        if (end.before(firstWeekEnd)) {
            list.add(new DateRange(begin, end));
            return list;
        }
        list.add(new DateRange(begin, firstWeekEnd));
        for (Date t1 = add(getWeekBegin(begin, firstDayOfWeek), 7, Calendar.DATE); t1.before(end); t1 = add(t1, 7, Calendar.DATE)) {
            Date t2 = getWeekEnd(t1, firstDayOfWeek);
            if (end.before(t2)) {
                list.add(new DateRange(t1, end));
            } else {
                list.add(new DateRange(t1, t2));
            }
        }
        return list;
    }

    /**
     * 时间分割 (按月分割)
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 时间段列表
     */
    public static List<DateRange> splitByMonth(Date begin, Date end) {
        checkNull(begin, end);
        List<DateRange> list = new ArrayList<>();
        Date firstMonthEnd = getMonthEnd(begin);
        if (end.before(firstMonthEnd)) {
            list.add(new DateRange(begin, end));
            return list;
        }
        list.add(new DateRange(begin, firstMonthEnd));
        for (Date t1 = add(getMonthBegin(begin), 1, Calendar.MONTH); t1.before(end); t1 = add(t1, 1, Calendar.MONTH)) {
            Date t2 = getMonthEnd(t1);
            if (end.before(t2)) {
                list.add(new DateRange(t1, end));
            } else {
                list.add(new DateRange(t1, t2));
            }
        }
        return list;
    }

    /**
     * 时间分割 (按年分割)
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 时间段列表
     */
    public static List<DateRange> splitByYear(Date begin, Date end) {
        checkNull(begin, end);
        List<DateRange> list = new ArrayList<>();
        Date firstYearEnd = getYearEnd(begin);
        if (end.before(firstYearEnd)) {
            list.add(new DateRange(begin, end));
            return list;
        }
        list.add(new DateRange(begin, firstYearEnd));
        for (Date t1 = add(getYearBegin(begin), 1, Calendar.YEAR); t1.before(end); t1 = add(t1, 1, Calendar.YEAR)) {
            Date t2 = getYearEnd(t1);
            if (end.before(t2)) {
                list.add(new DateRange(t1, end));
            } else {
                list.add(new DateRange(t1, t2));
            }
        }
        return list;
    }

    /**
     * 时间分割
     *
     * @param begin      开始时间
     * @param end        结束时间
     * @param splitPoint 分割点
     * @return 长度为4的数组，前两个表示位于分割点前的一段时间，后两个表示位于分割点后的一段时间
     */
    public static Date[] split(Date begin, Date end, Date splitPoint) {
        Date[] result = new Date[4];
        if (begin == null || end == null || splitPoint == null || begin.after(end)) {
            return result;
        }
        if (end.compareTo(splitPoint) <= 0) {
            result[0] = begin;
            result[1] = end;
        } else if (begin.compareTo(splitPoint) >= 0) {
            result[2] = begin;
            result[3] = end;
        } else {
            result[0] = begin;
            result[1] = DateUtil.add(splitPoint, -1, Calendar.MILLISECOND);
            result[2] = splitPoint;
            result[3] = end;
        }
        return result;
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
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return DAY_OF_WEEK_EN[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 判断时间是星期几 (中文)
     *
     * @param date 时间
     * @return 星期几 (中文)
     */
    public static String getDayOfWeekCn(Date date) {
        checkNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return DAY_OF_WEEK_CN[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取月份对应的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    public static int getDayNumOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取年份对应的天数
     *
     * @param year 年份
     * @return 天数
     */
    public static int getDayNumOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
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
        checkNull(date);
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

    /**
     * 根据时间判断星座
     *
     * @param date 时间
     * @return 星座
     */
    public static String getConstellation(Date date) {
        checkNull(date);
        int month = get(date, Calendar.MONTH);
        int day = get(date, Calendar.DATE);
        return day < CONSTELLATION_DAYS[month - 1] ? CONSTELLATIONS[month - 1] : CONSTELLATIONS[month];
    }

    /**
     * 根据时间判断生肖
     *
     * @param date 时间
     * @return 生肖
     */
    public static String getChineseZodiac(Date date) {
        checkNull(date);
        int year = get(date, Calendar.YEAR);
        // 已知2022为虎年，以此为基准判断
        return year >= 2022 ? CHINESE_ZODIACS[(year - 2022 + 2) % 12] : CHINESE_ZODIACS[(12 - (2022 - year) % 12 + 2) % 12];
    }

    /**
     * 根据生日求年龄
     *
     * @param date     当前时间
     * @param birthday 生日
     * @return 年龄 (周岁)
     */
    public static int getAge(Date date, Date birthday) {
        if (date == null) {
            throw new IllegalArgumentException("当前时间不能为空");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("生日不能为空");
        }
        if (date.before(birthday)) {
            throw new IllegalArgumentException("当前时间不能早于生日");
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date);
        c2.setTime(birthday);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        c2.set(Calendar.YEAR, year1);
        Date birthdayOfThisYear = c2.getTime();
        return year1 - year2 - (birthdayOfThisYear.after(date) ? 1 : 0);
    }

    /* 以下为内部函数 */

    /**
     * 计算时间差
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @param unit  时间单位 (毫秒数)
     * @return 时间差 (绝对值)
     */
    private static long getDiff(Date begin, Date end, long unit) {
        checkNull(begin, end);
        if (unit < 1) {
            throw new IllegalArgumentException("时间单位(毫秒数)不能小于1");
        }
        return Math.abs((begin.getTime() - end.getTime()) / unit);
    }

    /**
     * 时间空校验
     *
     * @param date 时间
     */
    private static void checkNull(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("输入时间不能为空");
        }
    }

    /**
     * 时间空校验
     *
     * @param begin 起始时间
     * @param end   结束时间
     */
    private static void checkNull(Date begin, Date end) {
        if (begin == null) {
            throw new IllegalArgumentException("起始时间不能为空");
        }
        if (end == null) {
            throw new IllegalArgumentException("结束时间不能为空");
        }
    }

}
