package cn.jinty.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间 - 工具类
 *
 * @author Jinty
 * @date 2020/3/25.
 */
@SuppressWarnings("unused")
public final class DateUtil {

    /**
     * 时间单位(毫秒表示)
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
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY = "yyyy";

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @return 时间对象
     */
    public static Date parse(String dateStr){
        return parse(dateStr, new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @param sdf 时间格式
     * @return 时间对象
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf){
        if(dateStr==null) return null;
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
    public static String format(Date date){
        return format(date, new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 格式化时间
     *
     * @param date 时间对象
     * @param sdf 时间格式
     * @return 字符串
     */
    public static String format(Date date, SimpleDateFormat sdf){
        if(date==null) return null;
        return sdf.format(date);
    }

    /**
     * 根据输入数值构建时间对象
     *
     * @param year 年
     * @param month 月
     * @param date 日
     * @return 时间
     */
    public static Date buildDate(Integer year,Integer month,Integer date){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DATE,date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 根据输入数值构建时间对象
     *
     * @param year 年
     * @param month 月
     * @param date 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 时间
     */
    public static Date buildDate(Integer year,Integer month,Integer date,Integer hour,Integer minute,Integer second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DATE,date);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        return calendar.getTime();
    }

    /**
     * 时间增加指定天数
     *
     * @param date 时间
     * @param dayNum 指定天数
     * @return 时间
     */
    public static Date addDate(Date date,Integer dayNum){
        if(date==null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,dayNum);
        return calendar.getTime();
    }

    /**
     * 时间增加指定小时数
     *
     * @param date 时间
     * @param hourNum 指定小时数
     * @return 时间
     */
    public static Date addHour(Date date,Integer hourNum){
        if(date==null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,hourNum);
        return calendar.getTime();
    }

    /**
     * 计算时间间隔
     *
     * @param begin 起始时间
     * @param end 结束时间
     * @param unit 时间单位(毫秒数)
     * @return 间隔
     */
    public static Long countInterval(Date begin, Date end, long unit){
        if(begin==null || end==null) return null;
        if(unit<1) throw new IllegalArgumentException("time unit cannot less than 1");
        return (begin.getTime() - end.getTime()) / unit;
    }

    /**
     * 计算时间间隔(单位:天)
     *
     * @param begin 起始时间
     * @param end 结束时间
     * @return 间隔
     */
    public static Long countIntervalForDay(Date begin, Date end){
        return countInterval(begin,end,DAY);
    }

    /**
     * 判断时间是否为当月第一天
     *
     * @param date 时间
     * @return 布尔
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        return day == 1;
    }

    /**
     * 判断时间是否为当月指定的天数
     *
     * @param date 时间
     * @param targetDay 当月指定天数
     * @return 布尔
     */
    public static boolean isTargetDayOfMonth(Date date,Integer targetDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE) == targetDay;
    }

    /**
     * 判断时间是否为当天指定的小时数
     *
     * @param date 时间
     * @param targetHour 给定小时数
     * @return 布尔
     */
    public static boolean isTargetHourOfDay(Date date,Integer targetHour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY)==targetHour;
    }

    /**
     * 判断时间是否是今天
     *
     * @param date 时间
     * @return 布尔
     */
    public static boolean isToday(Date date){
        Date now = new Date();
        return isTheSameDate(now,date);
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 布尔
     */
    public static boolean isTheSameDate(Date date1,Date date2){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        String date1Str = sdf.format(date1);
        String date2Str = sdf.format(date2);
        return date1Str.equals(date2Str);
    }

    /**
     * 判断时间是否在一个时间区间内
     *
     * @param date 时间
     * @param begin 起始时间
     * @param end 结束时间
     * @return 布尔
     */
    public static boolean isWithinInterval(Date date,Date begin,Date end){
        if(date==null || begin==null || end==null) return false;
        return date.compareTo(begin)>=0 && date.compareTo(end)<=0;
    }

    /**
     * 当前时间是否在开始时间的后24小时内
     *
     * @param now 当前时间
     * @param begin 开始时间
     * @return 是否
     */
    public static boolean isWithin24hAfter(Date now, Date begin){
        if(now==null || begin==null) return false;
        Date end = addDate(begin,1);
        return isWithinInterval(now,begin,end);
    }

    /**
     * 获取今天的开始时间
     *
     * @return 时间
     */
    public static Date getTodayBegin(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取今天的结束时间
     *
     * @return 时间
     */
    public static Date getTodayEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }

    /**
     * 获取昨天的开始时间
     *
     * @return 时间
     */
    public static Date getYesterdayBegin(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取昨天的结束时间
     *
     * @return 时间
     */
    public static Date getYesterdayEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }

    /**
     * 获取上个月的开始时间
     *
     * @return 时间
     */
    public static Date getLastMonthBegin(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取上个月的结束时间
     *
     * @return 时间
     */
    public static Date getLastMonthEnd(){
        //先得到本月的开始时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        //秒数减1
        calendar.add(Calendar.SECOND,-1);
        return calendar.getTime();
    }

    /**
     * 获取一年的开始时刻
     *
     * @param year 年份
     * @return 时间
     */
    public static Date getBeginOfYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        return calendar.getTime();
    }

    /**
     * 获取一年的结束时刻
     *
     * @param year 年份
     * @return 时间
     */
    public static Date getEndOfYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year+1);
        calendar.add(Calendar.SECOND,-1);
        return calendar.getTime();
    }

    /**
     * 获取今年的开始时刻
     *
     * @return 时间
     */
    public static Date getBeginOfThisYear(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        return calendar.getTime();
    }

    /**
     * 获取今年的结束时刻
     *
     * @return 时间
     */
    public static Date getEndOfThisYear(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR,year+1);
        calendar.add(Calendar.SECOND,-1);
        return calendar.getTime();
    }

    /**
     * 当前时间距离目标时间倒计时
     *
     * @param target 目标时间
     * @return 倒计时文本提示
     */
    public static String getCountDown(Date target){
        if(target==null) return "";
        Date now = new Date();
        //大于一天时显示天数
        long day = countIntervalForDay(target,now);
        if(day<0) return "";
        if(day>=1) return day + "天后截止";
        //一天内显示小时与分钟
        long minute = countInterval(target,now,MINUTE);
        if(minute<0) return "";
        return (minute/60) + "时" + (minute%60) + "分后截止";
    }

}
