package cn.jinty.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间相关工具类
 *
 * @author Jinty
 * @date 2020/3/25.
 */
public final class DateUtil {

    private static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat YYYY_MM = new SimpleDateFormat("yyyy-MM");

    private static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

    /**
     * 返回(年月日时分秒)文本字符串
     * @param date 时间对象
     * @return 字符串
     */
    public static String getFullDateStr(Date date){
        return YYYY_MM_DD_HH_MM_SS.format(date);
    }

    /**
     * 返回(年月日)文本字符串
     * @param date 时间对象
     * @return 字符串
     */
    public static String getDateStr(Date date){
        return YYYY_MM_DD.format(date);
    }

    /**
     * 返回(年月)文本字符串
     * @param date 时间对象
     * @return 字符串
     */
    public static String getMonthStr(Date date){
        return YYYY_MM.format(date);
    }

    /**
     * 返回(年)文本字符串
     * @param date 时间对象
     * @return 字符串
     */
    public static String getYearStr(Date date){
        return YYYY.format(date);
    }

    /**
     * 按指定格式生成时间对象
     * @param date 时间字符串
     * @param format 格式
     * @return 时间对象
     * @throws ParseException 解析异常
     */
    public static Date buildDate(String date,SimpleDateFormat format) throws ParseException {
        return format.parse(date);
    }

    /**
     * 根据输入数值构建时间对象
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
     * 判断时间是否为当月第一天
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
     * @param date 时间
     * @param targetDay 当月指定天数
     * @return 布尔
     */
    public static boolean isTargetDayOfMonth(Date date,Integer targetDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        return day == targetDay;
    }

    /**
     * 判断时间是否是今天
     * @param date 时间
     * @return 布尔
     */
    public static boolean isToday(Date date){
        Date now = new Date();
        String nowStr = YYYY_MM_DD.format(now);
        String dateStr = YYYY_MM_DD.format(date);
        return nowStr.equals(dateStr);
    }

    /**
     * 判断两个时间是否是同一天
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
     * @param date 时间
     * @param begin 起始时间
     * @param end 结束时间
     * @return 布尔
     */
    public static boolean isWithinInterval(Date date,Date begin,Date end){
        if(date==null || begin==null || end==null){
            return false;
        }
        return date.compareTo(begin)>=0 && date.compareTo(end)<=0;
    }

    /**
     * 判断时间的小时数是否为给定的小时数
     * @param date 时间
     * @param targetHour 给定小时数
     * @return 布尔
     */
    public static boolean isTargetHour(Date date,Integer targetHour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY)==targetHour;
    }

    /**
     * 获取今天的开始时间
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
     * 时间增加指定天数
     * @param date 时间
     * @param dayNum 指定天数
     * @return 时间
     */
    public static Date addDate(Date date,Integer dayNum){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,dayNum);
        return calendar.getTime();
    }

    /**
     * 时间增加指定小时数
     * @param date 时间
     * @param hourNum 指定小时数
     * @return 时间
     */
    public static Date addHour(Date date,Integer hourNum){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,hourNum);
        return calendar.getTime();
    }

    /**
     * 计算时间间隔天数
     * @param begin 起始时间
     * @param end 结束时间
     * @return 天数
     */
    public static Integer countInterval(Date begin,Date end){
        return (int)((begin.getTime() - end.getTime())/(1000*60*60*24));
    }

    /**
     * 计算时间间隔天数(忽略时分秒)
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
     * 时分秒置0
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
