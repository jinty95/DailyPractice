package test.cn.jinty.util;

import cn.jinty.enums.CycleTypeEnum;
import cn.jinty.entity.date.DateRange;
import cn.jinty.entity.date.Week;
import cn.jinty.util.DateUtil;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.*;

/**
 * 时间 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/5/18
 **/
public class DateUtilTest {

    @Test
    public void testSupportedFormat() {
        for (String str : DateUtil.SUPPORTED_FORMAT) {
            System.out.println(str + " 长度为 " + str.length());
        }
    }

    @Test
    public void testParseDateCompatibly() {
        System.out.println(DateUtil.format(DateUtil.parseDateCompatibly("20220905")));
        System.out.println(DateUtil.format(DateUtil.parseDateCompatibly("2022/9/5")));
        System.out.println(DateUtil.format(DateUtil.parseDateCompatibly("2022/09/5")));
        System.out.println(DateUtil.format(DateUtil.parseDateCompatibly("2022-9-05")));
        System.out.println(DateUtil.format(DateUtil.parseDateCompatibly("2022-09-05")));
        System.out.println(DateUtil.format(DateUtil.parseDateCompatibly("2022年9月5日")));
    }

    @Test
    public void testParseDatetimeCompatibly() {
        System.out.println(DateUtil.format(DateUtil.parseDatetimeCompatibly("20220905193600")));
        System.out.println(DateUtil.format(DateUtil.parseDatetimeCompatibly("2022/9/5 19:36:0")));
        System.out.println(DateUtil.format(DateUtil.parseDatetimeCompatibly("2022/09/5 20:0:1")));
        System.out.println(DateUtil.format(DateUtil.parseDatetimeCompatibly("2022-9-05 0:0:0")));
        System.out.println(DateUtil.format(DateUtil.parseDatetimeCompatibly("2022-09-05 19:40:59")));
        System.out.println(DateUtil.format(DateUtil.parseDatetimeCompatibly("2022年9月5日 19时40分59秒")));
    }

    @Test
    public void testParseAndFormat() {
        System.out.println(DateUtil.format(DateUtil.parse("1-1-1 18:00:00")));
        System.out.println(DateUtil.format(DateUtil.parse("2021-07-15 18:00:00")));
        System.out.println(DateUtil.format(DateUtil.parse("2021-07-15 18:00:00 this is also ok")));

        System.out.println(DateUtil.format(DateUtil.parse("2022/9/5", DateUtil.FORMAT_DATE_1), DateUtil.FORMAT_DATETIME_1));
        System.out.println(DateUtil.format(DateUtil.parse("2022/09/05 17:50:00.000", DateUtil.FORMAT_DATETIME_MILLI_1), DateUtil.FORMAT_DATETIME_1));

        System.out.println(DateUtil.format(DateUtil.parse("20220905", DateUtil.FORMAT_DATE_2), DateUtil.FORMAT_DATETIME_2));
        System.out.println(DateUtil.format(DateUtil.parse("20220905175000000", DateUtil.FORMAT_DATETIME_MILLI_2), DateUtil.FORMAT_DATETIME_2));
    }

    @Test
    public void testCheckFormat() {
        System.out.println(DateUtil.checkFormat("2023", DateUtil.FORMAT_MONTH));
        System.out.println(DateUtil.checkFormat("2023-01", DateUtil.FORMAT_MONTH));
        System.out.println(DateUtil.checkFormat("2023-01-16", DateUtil.FORMAT_MONTH));
        System.out.println(DateUtil.checkFormat("AA2023-01-16", DateUtil.FORMAT_MONTH));
        System.out.println(DateUtil.checkFormat("2023-01-16AA", DateUtil.FORMAT_MONTH));
    }

    @Test
    public void testTimeZone() {
        System.out.println(DateUtil.EPOCH.getTime());
        System.out.println(DateUtil.format(DateUtil.EPOCH, DateUtil.FORMAT_DATETIME));
        System.out.println(DateUtil.format(DateUtil.EPOCH, DateUtil.FORMAT_DATETIME, DateUtil.EUROPE_LONDON));
        System.out.println(DateUtil.format(DateUtil.EPOCH, DateUtil.FORMAT_DATETIME, DateUtil.ASIA_SHANGHAI));
        System.out.println(DateUtil.format(DateUtil.EPOCH, DateUtil.FORMAT_DATETIME, DateUtil.ASIA_TOKYO));
    }

    @Test
    public void testTransfer() {
        // 北京时间与伦敦时间有时候差7小时，有时候差8小时
        String srcDateStr = "2022-01-01 08:00:00";
        System.out.println("北京时间：" + srcDateStr);
        System.out.println("伦敦时间：" + DateUtil.transfer(srcDateStr, DateUtil.FORMAT_DATETIME, DateUtil.ASIA_SHANGHAI, DateUtil.EUROPE_LONDON));
        System.out.println("东京时间：" + DateUtil.transfer(srcDateStr, DateUtil.FORMAT_DATETIME, DateUtil.ASIA_SHANGHAI, DateUtil.ASIA_TOKYO));
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
        System.out.println(DateUtil.get(new Date(), Calendar.HOUR_OF_DAY));
        System.out.println(DateUtil.get(new Date(), Calendar.MINUTE));
        System.out.println(DateUtil.get(new Date(), Calendar.SECOND));
        System.out.println(DateUtil.get(new Date(), Calendar.MILLISECOND));
        System.out.println(DateUtil.get(new Date(), Calendar.DAY_OF_YEAR));
    }

    @Test
    public void testAdd() {
        System.out.println(DateUtil.format(DateUtil.add(new Date(), 180, Calendar.DATE)));
        System.out.println(DateUtil.format(DateUtil.add(new Date(), 6, Calendar.MONTH)));
    }

    @Test
    public void testGetDiff() {
        Date d1 = DateUtil.parse("2022-08-22 17:00:00.865", DateUtil.FORMAT_DATETIME_MILLI);
        Date d2 = DateUtil.parse("2022-08-22 17:00:00.998", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiff(d1, d2) + " 毫秒");

        d1 = DateUtil.parse("2022-08-22 17:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        d2 = DateUtil.parse("2022-08-22 17:00:01.500", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiffSecond(d1, d2) + " 秒");

        d1 = DateUtil.parse("2022-08-22 17:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        d2 = DateUtil.parse("2022-08-22 17:01:45.000", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiffMinute(d1, d2) + " 分钟");

        d1 = DateUtil.parse("2022-08-22 17:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        d2 = DateUtil.parse("2022-08-22 18:10:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiffHour(d1, d2) + " 小时");

        d1 = DateUtil.parse("2019-07-03 09:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        d2 = DateUtil.parse("2022-08-22 20:11:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiffDay(d1, d2) + " 天");

        d1 = DateUtil.parse("2019-07-03 00:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        d2 = DateUtil.parse("2022-08-22 00:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiffMonth(d1, d2) + " 月");

        d1 = DateUtil.parse("2019-07-03 00:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        d2 = DateUtil.parse("2022-08-22 00:00:00.000", DateUtil.FORMAT_DATETIME_MILLI);
        System.out.println(DateUtil.format(d1, DateUtil.FORMAT_DATETIME_MILLI) + " 到 " +
                DateUtil.format(d2, DateUtil.FORMAT_DATETIME_MILLI) + " 有 " + DateUtil.getDiffYear(d1, d2) + " 年");
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
        Date begin = DateUtil.parse("2022-11-21", DateUtil.FORMAT_DATE);
        Date end = DateUtil.parse("2022-11-23", DateUtil.FORMAT_DATE);
        System.out.println(DateUtil.isBetween(rightNow, DateUtil.getDayBegin(rightNow), DateUtil.getDayEnd(rightNow)));
        Date d1 = DateUtil.parse("2022-11-21", DateUtil.FORMAT_DATE);
        Date d2 = DateUtil.parse("2022-11-21", DateUtil.FORMAT_DATE);
        System.out.println(DateUtil.isBetween(d1, d2, begin, end));
    }

    @Test
    public void testCompareDay() {
        Date d1 = DateUtil.parse("2022-11-21 23:59:59");
        Date d2 = DateUtil.parse("2022-11-21 00:00:00");
        System.out.println(DateUtil.compareDay(d1, d2));
        d1 = DateUtil.parse("2022-11-20 23:59:59");
        d2 = DateUtil.parse("2022-11-21 00:00:00");
        System.out.println(DateUtil.compareDay(d1, d2));
        d1 = DateUtil.parse("2022-11-22 23:59:59");
        d2 = DateUtil.parse("2022-11-21 00:00:00");
        System.out.println(DateUtil.compareDay(d1, d2));
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
    public void testGetWeekBeginAndEnd() {
        System.out.println(DateUtil.format(DateUtil.getWeekBegin(new Date(), Calendar.MONDAY)));
        System.out.println(DateUtil.format(DateUtil.getWeekEnd(new Date(), Calendar.MONDAY)));
        System.out.println(DateUtil.format(DateUtil.getWeekBegin(new Date(), Calendar.TUESDAY)));
        System.out.println(DateUtil.format(DateUtil.getWeekEnd(new Date(), Calendar.TUESDAY)));
        System.out.println(DateUtil.format(DateUtil.getWeekBegin(new Date(), Calendar.SUNDAY)));
        System.out.println(DateUtil.format(DateUtil.getWeekEnd(new Date(), Calendar.SUNDAY)));
    }

    @Test
    public void testGetMonthBeginAndEnd() {
        System.out.println(DateUtil.format(DateUtil.getMonthBegin(new Date())));
        System.out.println(DateUtil.format(DateUtil.getMonthEnd(new Date())));
        System.out.println(DateUtil.format(DateUtil.getMonthBegin(DateUtil.buildDate(2022, 2, 1))));
        System.out.println(DateUtil.format(DateUtil.getMonthEnd(DateUtil.buildDate(2022, 2, 1))));
    }

    @Test
    public void testGetYearBeginAndEnd() {
        System.out.println(DateUtil.format(DateUtil.getYearBegin(new Date())));
        System.out.println(DateUtil.format(DateUtil.getYearEnd(new Date())));
        System.out.println(DateUtil.format(DateUtil.getYearBegin(DateUtil.buildDate(2023, 2, 1))));
        System.out.println(DateUtil.format(DateUtil.getYearEnd(DateUtil.buildDate(2023, 2, 1))));
    }

    @Test
    public void testSplitByCycle() {
        Date begin = DateUtil.parse("2022-06-16 16:00:00");
        Date end = DateUtil.parse("2023-01-21 12:00:00");
        for (CycleTypeEnum cycleType : CycleTypeEnum.values()) {
            System.out.printf("按%s分割时间%n", cycleType.getDesc());
            for (DateRange range : DateUtil.splitByCycle(begin, end, cycleType)) {
                System.out.println(range);
            }
        }
    }

    @Test
    public void testSplitByDay() {
        Date begin = DateUtil.parse("2022-03-16 16:00:00");
        Date end = DateUtil.parse("2022-03-16 19:00:00");
        for (DateRange range : DateUtil.splitByDay(begin, end)) {
            System.out.println(range);
        }
    }

    @Test
    public void testSplitByWeek() {
        Date begin = DateUtil.parse("2022-01-18 16:00:00");
        Date end = DateUtil.parse("2022-01-19 12:00:00");
        for (DateRange range : DateUtil.splitByWeek(begin, end, Calendar.MONDAY)) {
            System.out.println(range);
        }
    }

    @Test
    public void testSplitByMonth() {
        Date begin = DateUtil.parse("2022-03-15 16:00:00");
        Date end = DateUtil.parse("2022-03-17 12:00:00");
        for (DateRange range : DateUtil.splitByMonth(begin, end)) {
            System.out.println(range);
        }
    }

    @Test
    public void testSplitByYear() {
        Date begin = DateUtil.parse("2022-03-16 16:00:00");
        Date end = DateUtil.parse("2022-03-21 12:00:00");
        for (DateRange range : DateUtil.splitByYear(begin, end)) {
            System.out.println(range);
        }
    }

    @Test
    public void testSplit() {
        Date[] arr = DateUtil.split(DateUtil.parse("2022-1-11 00:00:00"), DateUtil.parse("2022-1-13 23:59:59"), DateUtil.parse("2022-1-13 00:00:00"));
        for (Date a : arr) {
            System.out.println(DateUtil.format(a));
        }
        arr = DateUtil.split(DateUtil.parse("2022-1-15 00:00:00"), DateUtil.parse("2022-1-13 23:59:59"), DateUtil.parse("2022-1-13 00:00:00"));
        for (Date a : arr) {
            System.out.println(DateUtil.format(a));
        }
        arr = DateUtil.split(DateUtil.parse("2022-1-11 00:00:00"), DateUtil.parse("2022-1-13 23:59:59"), DateUtil.parse("2022-1-13 23:59:59"));
        for (Date a : arr) {
            System.out.println(DateUtil.format(a));
        }
        arr = DateUtil.split(DateUtil.parse("2022-1-11 00:00:00"), DateUtil.parse("2022-1-13 23:59:59"), DateUtil.parse("2022-1-11 00:00:00"));
        for (Date a : arr) {
            System.out.println(DateUtil.format(a));
        }
    }

    @Test
    public void testGetAllMonth() {
        for (String month : DateUtil.getAllMonth("2021-10", "2022-10")) {
            System.out.println(month);
        }
    }

    @Test
    public void testGetAllWeekOfYear() {
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
    public void testGetDayNum() {
        for (int i = 2020; i <= 2022; i++) {
            System.out.printf("%d年有%d天%n", i, DateUtil.getDayNumOfYear(i));
            for (int j = 1; j <= 12; j++) {
                System.out.printf("%d年%d月有%d天%n", i, j, DateUtil.getDayNumOfMonth(i, j));
            }
            System.out.println();
        }
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

    @Test
    public void testGetConstellation() {
        System.out.println(DateUtil.getConstellation(DateUtil.buildDate(2021, 11, 22)));
        System.out.println(DateUtil.getConstellation(DateUtil.buildDate(2021, 11, 23)));
    }

    @Test
    public void testGetChineseZodiac() {
        System.out.println(DateUtil.getChineseZodiac(DateUtil.buildDate(2023, 1, 1)));
        System.out.println(DateUtil.getChineseZodiac(DateUtil.buildDate(2022, 1, 1)));
        System.out.println(DateUtil.getChineseZodiac(DateUtil.buildDate(1995, 1, 1)));
        System.out.println(DateUtil.getChineseZodiac(DateUtil.buildDate(1970, 1, 1)));
        System.out.println(DateUtil.getChineseZodiac(DateUtil.buildDate(1, 1, 1)));
    }

    @Test
    public void testGetAge() {
        Date now = new Date();
        Date birthday = DateUtil.buildDate(1996, 1, 1);
        System.out.println("今天：" + DateUtil.format(now, DateUtil.FORMAT_DATE));
        System.out.println("生日：" + DateUtil.format(birthday, DateUtil.FORMAT_DATE));
        System.out.println("年龄：" + DateUtil.getAge(now, birthday));
        System.out.println("生肖：" + DateUtil.getChineseZodiac(birthday));
        System.out.println("星座：" + DateUtil.getConstellation(birthday));
    }

    @Test
    public void testTimeStampToDate() {
        long millisecond = 1656518400000L;
        System.out.println("时间戳(毫秒)：" + millisecond);
        Date d1 = new Date(millisecond);
        System.out.println("时间：" + DateUtil.format(d1));
        System.out.println();
        long second = 1656518400L;
        System.out.println("时间戳(秒)：" + second);
        Date d2 = new Date(second * DateUtil.SECOND);
        System.out.println("时间：" + DateUtil.format(d2));
    }

    @Test
    public void testDateToTimeStamp() {
        Date date = DateUtil.buildDate(2022, 6, 30);
        System.out.println("时间：" + DateUtil.format(date));
        System.out.println("时间戳(毫秒)：" + date.getTime());
        System.out.println("时间戳(秒)：" + date.getTime() / DateUtil.SECOND);
        System.out.println("时间戳(分)：" + date.getTime() / DateUtil.MINUTE);
        System.out.println("时间戳(时)：" + date.getTime() / DateUtil.HOUR);
        System.out.println("时间戳(日)：" + date.getTime() / DateUtil.DAY);
        System.out.println("时间戳(月)：" + date.getTime() / DateUtil.MONTH);
        System.out.println("时间戳(年)：" + date.getTime() / DateUtil.YEAR);
    }

}
