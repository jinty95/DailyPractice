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
     * 常用的时间格式
     */
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat YYYY_MM = new SimpleDateFormat("yyyy-MM");

    public static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @return 时间对象
     */
    public static Date parse(String dateStr){
        return parse(dateStr,YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 解析时间字符串
     *
     * @param dateStr 时间字符串
     * @param sdf 时间格式
     * @return 时间对象
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf){
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
        return format(date,YYYY_MM_DD_HH_MM_SS);
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
     * 计算时间间隔天数
     *
     * @param begin 起始时间
     * @param end 结束时间
     * @return 天数
     */
    public static Integer countInterval(Date begin,Date end){
        return (int)((begin.getTime() - end.getTime())/(1000*60*60*24));
    }

    /**
     * 计算时间间隔天数(忽略时分秒)
     *
     * @param begin 起始时间
     * @param end 结束时间
     * @return 天数
     */
    public static Integer countIntervalOnlyDay(Date begin,Date end){
        begin = setHourMinSecToZero(begin);
        end = setHourMinSecToZero(end);
        return countInterval(begin,end);
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
        String date1Str = YYYY_MM_DD.format(date1);
        String date2Str = YYYY_MM_DD.format(date2);
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


    /* 以下为内部函数 */


    /**
     * 时分秒置0
     *
     * @param date 时间
     * @return 时间
     */
    private static Date setHourMinSecToZero(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

}
