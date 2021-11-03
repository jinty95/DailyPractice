package test.cn.jinty.util;

import cn.jinty.util.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间 - 工具类 - 测试
 *
 * @author jinty
 * @date 2021/5/18
 **/
public class DateUtilTest {

    @Test
    public void testParseAndFormat() {
        Date date = DateUtil.parse("2021-07-15 18:00:00");
        System.out.println(DateUtil.format(date));
    }

    @Test
    public void testBuildDate() {
        System.out.println(DateUtil.format(
                DateUtil.buildDate(2021, 7, 15)
        ));
        System.out.println(DateUtil.format(
                DateUtil.buildDate(2021, 7, 15, 18, 0, 0)
        ));
    }

    @Test
    public void testAdd() {
        System.out.println(DateUtil.format(DateUtil.add(new Date(), 180, Calendar.DATE)));
        System.out.println(DateUtil.format(DateUtil.add(new Date(), 6, Calendar.MONTH)));
    }

    @Test
    public void testCountInterval() {
        Date d1 = DateUtil.parse("2021-07-14 19:00:00");
        Date d2 = DateUtil.parse("2021-07-15 18:00:00");
        System.out.println(DateUtil.countIntervalForDay(d1, d2));
        System.out.println(DateUtil.countInterval(d1, d2, DateUtil.HOUR));
        System.out.println(DateUtil.countInterval(d1, d2, DateUtil.MINUTE));
    }

    @Test
    public void testIsTarget() {
        System.out.println(DateUtil.isTarget(new Date(), 1, Calendar.DATE));
        System.out.println(DateUtil.isTarget(new Date(), 11, Calendar.MONTH));
        System.out.println(DateUtil.isTarget(new Date(), 2021, Calendar.YEAR));
    }

    @Test
    public void testIsToday() {
        System.out.println(DateUtil.isToday(new Date()));
        System.out.println(DateUtil.isToday(DateUtil.parse("2021-07-14 00:00:00")));
    }

    @Test
    public void testIsBetween() {
        System.out.println(DateUtil.isBetween(new Date(), DateUtil.getYesterdayBegin(), DateUtil.getTodayEnd()));
    }

    @Test
    public void getBeginAndEnd() {
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
