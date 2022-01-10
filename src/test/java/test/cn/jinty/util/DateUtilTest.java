package test.cn.jinty.util;

import cn.jinty.entity.Week;
import cn.jinty.util.DateUtil;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 日期时间 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/5/18
 **/
public class DateUtilTest {

    @Test
    public void testParseAndFormat() {
        System.out.println(DateUtil.format(DateUtil.parse("2021-07-15 18:00:00")));
        System.out.println(DateUtil.format(DateUtil.parse("0000-00-00 00:00:00")));
        System.out.println(DateUtil.format(DateUtil.parse("0000-01-01 00:00:00")));
        System.out.println(DateUtil.format(DateUtil.parse("0001-01-01 00:00:00")));
        System.out.println(DateUtil.format(DateUtil.parse("20211218", "yyyyMMdd")));
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
    public void testNextTime() {
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:29:59"), 16, 30, 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:30:00"), 16, 30, 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:30:01"), 16, 30, 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:29:59"), 30, 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:30:00"), 30, 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:30:01"), 30, 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:29:59"), 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:30:00"), 0)));
        System.out.println(DateUtil.format(DateUtil.nextTime(DateUtil.parse("2021-11-4 16:30:01"), 0)));
        System.out.println();
        Date date = new Date();
        for (int i = 0; i < 30; i++) {
            date = DateUtil.nextTime(date, 9, 0, 0);
            System.out.println(DateUtil.format(date));
        }
    }

    @Test
    public void testGet() {
        System.out.println(DateUtil.get(new Date(), Calendar.YEAR));
        System.out.println(DateUtil.get(new Date(), Calendar.MONTH));
        System.out.println(DateUtil.get(new Date(), Calendar.DATE));
        System.out.println(DateUtil.get(new Date(), Calendar.HOUR));
        System.out.println(DateUtil.get(new Date(), Calendar.MINUTE));
        System.out.println(DateUtil.get(new Date(), Calendar.SECOND));
        System.out.println(DateUtil.get(new Date(), Calendar.MILLISECOND));
    }

    @Test
    public void testAdd() {
        System.out.println(DateUtil.format(DateUtil.add(new Date(), 180, Calendar.DATE)));
        System.out.println(DateUtil.format(DateUtil.add(new Date(), 6, Calendar.MONTH)));
    }

    @Test
    public void testGetDiff() {
        Date d1 = DateUtil.parse("2021-11-03 10:00:00");
        Date d2 = DateUtil.parse("2021-11-05 00:00:00");
        System.out.println(DateUtil.getDiffDay(d1, d2));
        System.out.println(DateUtil.getDiffHour(d1, d2));
        System.out.println(DateUtil.getDiffMinute(d1, d2));
        System.out.println(DateUtil.getDiffSecond(d1, d2));
        System.out.println(DateUtil.getDiff(d1, d2));
        Date d3 = DateUtil.buildDate(2020, 11, 1);
        Date d4 = DateUtil.buildDate(2021, 4, 15);
        System.out.println(DateUtil.getDiffMonth(d3, d4));
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
    public void testIsSameDate() {
        System.out.println(DateUtil.isSameDate(DateUtil.buildDate(2022, 1, 10), DateUtil.buildDate(2022, 1, 10)));
        System.out.println(DateUtil.isSameDate(DateUtil.buildDate(2022, 1, 10), DateUtil.buildDate(2022, 1, 11)));
    }

    @Test
    public void testIsBetween() {
        Date rightNow = new Date();
        System.out.println(DateUtil.isBetween(rightNow, DateUtil.getDayBegin(rightNow), DateUtil.getDayEnd(rightNow)));
    }

    @Test
    public void testGetDayBeginAndEnd() {
        System.out.println(DateUtil.format(DateUtil.getDayBegin(DateUtil.add(new Date(), -1, Calendar.DATE))));
        System.out.println(DateUtil.format(DateUtil.getDayEnd(DateUtil.add(new Date(), -1, Calendar.DATE))));
        System.out.println(DateUtil.format(DateUtil.getDayBegin(new Date())));
        System.out.println(DateUtil.format(DateUtil.getDayEnd(new Date())));
        System.out.println(DateUtil.format(DateUtil.getDayBegin(DateUtil.add(new Date(), 1, Calendar.DATE))));
        System.out.println(DateUtil.format(DateUtil.getDayEnd(DateUtil.add(new Date(), 1, Calendar.DATE))));
        System.out.println(DateUtil.format(DateUtil.getDayBegin(DateUtil.buildDate(2021, 1, 1))));
        System.out.println(DateUtil.format(DateUtil.getDayEnd(DateUtil.buildDate(2021, 1, 1))));
    }

    @Test
    public void testGetMonthBeginAndEnd() {
        System.out.println(DateUtil.format(DateUtil.getMonthBegin(new Date())));
        System.out.println(DateUtil.format(DateUtil.getMonthEnd(new Date())));
        System.out.println(DateUtil.format(DateUtil.getMonthBegin(DateUtil.buildDate(2022, 2, 1))));
        System.out.println(DateUtil.format(DateUtil.getMonthEnd(DateUtil.buildDate(2022, 2, 1))));
    }

    @Test
    public void testGetWeekOfYear() {
        List<Week> weeks = DateUtil.getAllWeekOfYear(2020, Calendar.SATURDAY);
        for (Week week : weeks) {
            System.out.println(week);
        }
    }

    @Test
    public void testGetDayOfWeek() {
        System.out.println(DateUtil.getDayOfWeekEn(new Date()));
        System.out.println(DateUtil.getDayOfWeekCn(new Date()));
    }

    @Test
    public void testIsLeapYear() {
        System.out.println("2000 is leap year ? " + DateUtil.isLeapYear(2000));
        System.out.println("2004 is leap year ? " + DateUtil.isLeapYear(2004));
        System.out.println("2100 is leap year ? " + DateUtil.isLeapYear(2100));
        System.out.println("now is leap year ? " + DateUtil.isLeapYear(new Date()));
    }

    @Test
    public void testMaxAndMin() {
        Date d1 = DateUtil.parse("2021-11-04 00:00:00");
        Date d2 = DateUtil.parse("2021-11-05 00:00:00");
        System.out.println(DateUtil.format(DateUtil.max(d1, d2)));
        System.out.println(DateUtil.format(DateUtil.max(ListUtil.asList(null, null))));
        System.out.println(DateUtil.format(DateUtil.max(ListUtil.asList(d2, null))));
        System.out.println(DateUtil.format(DateUtil.max(ListUtil.asList(d1, d2))));
        System.out.println();
        System.out.println(DateUtil.format(DateUtil.min(d1, d2)));
        System.out.println(DateUtil.format(DateUtil.min(ListUtil.asList(null, null))));
        System.out.println(DateUtil.format(DateUtil.min(ListUtil.asList(d1, null))));
        System.out.println(DateUtil.format(DateUtil.min(ListUtil.asList(d1, d2))));
    }

    @Test
    public void testRangeOfDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("GTM+0"));
        Date d1 = new Date(-1L);
        System.out.println(DateUtil.format(d1));
        Date d2 = new Date(0L);
        System.out.println(DateUtil.format(d2));
        Date d3 = new Date(Long.MAX_VALUE);
        System.out.println(DateUtil.format(d3));
    }

}
