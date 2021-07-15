package cn.jinty.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期时间 - 工具类 - 测试
 *
 * @author jinty
 * @date 2021/5/18
 **/
public class DateUtilTest {

    @Test
    public void testParseAndFormat(){
        Date date = DateUtil.parse("2021-07-15 18:00:00");
        System.out.println(DateUtil.format(date));
    }

    @Test
    public void testBuildDate(){
        System.out.println(DateUtil.format(
                DateUtil.buildDate(2021,7,15)
        ));
        System.out.println(DateUtil.format(
                DateUtil.buildDate(2021,7,15,18,0,0)
        ));
    }

    @Test
    public void testAddHour(){
        System.out.println(DateUtil.format(DateUtil.addHour(new Date(),2)));
        System.out.println(DateUtil.format(DateUtil.addHour(new Date(),24)));
    }

    @Test
    public void testCountInterval(){
        Date d1 = DateUtil.parse("2021-07-14 19:00:00");
        Date d2 = DateUtil.parse("2021-07-15 18:00:00");
        System.out.println(DateUtil.countInterval(d1,d2));
        System.out.println(DateUtil.countIntervalOnlyDay(d1,d2));
    }

    @Test
    public void testIsTarget(){
        System.out.println(DateUtil.isTargetDayOfMonth(new Date(),15));
        System.out.println(DateUtil.isTargetHourOfDay(new Date(),18));
    }

    @Test
    public void testIsToday(){
        System.out.println(DateUtil.isToday(new Date()));
        System.out.println(DateUtil.isToday(DateUtil.parse("2021-07-14 00:00:00")));
    }

    @Test
    public void testIsWithin24hAfter(){
        try {
            Date now = DateUtil.YYYY_MM_DD_HH_MM_SS.parse("2021-07-15 00:00:00");
            Date begin1 = DateUtil.YYYY_MM_DD_HH_MM_SS.parse("2021-07-16 00:00:01");
            Date begin2 = DateUtil.YYYY_MM_DD_HH_MM_SS.parse("2021-07-15 00:00:00");
            Date begin3 = DateUtil.YYYY_MM_DD_HH_MM_SS.parse("2021-07-14 00:00:00");
            Date begin4 = DateUtil.YYYY_MM_DD_HH_MM_SS.parse("2021-07-13 23:59:59");
            System.out.println(DateUtil.isWithin24hAfter(now,begin1));
            System.out.println(DateUtil.isWithin24hAfter(now,begin2));
            System.out.println(DateUtil.isWithin24hAfter(now,begin3));
            System.out.println(DateUtil.isWithin24hAfter(now,begin4));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBeginAndEnd(){
        //年
        System.out.println(DateUtil.format(DateUtil.getBeginOfYear(2019)));
        System.out.println(DateUtil.format(DateUtil.getEndOfYear(2019)));
        System.out.println(DateUtil.format(DateUtil.getBeginOfThisYear()));
        System.out.println(DateUtil.format(DateUtil.getEndOfThisYear()));
        System.out.println();
        //月
        System.out.println(DateUtil.format(DateUtil.getLastMonthBegin()));
        System.out.println(DateUtil.format(DateUtil.getLastMonthEnd()));
        System.out.println();
        //日
        System.out.println(DateUtil.format(DateUtil.getTodayBegin()));
        System.out.println(DateUtil.format(DateUtil.getTodayEnd()));
        System.out.println(DateUtil.format(DateUtil.getYesterdayBegin()));
        System.out.println(DateUtil.format(DateUtil.getYesterdayEnd()));
    }

}
