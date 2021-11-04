package test.cn.jinty.util;

import cn.jinty.entity.DateRange;
import cn.jinty.util.DateUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
    public void testGetWeekOfYear() {
        Map<Integer, DateRange> map = DateUtil.getAllWeekOfYear(2020, Calendar.SATURDAY);
        for (Integer week : map.keySet()) {
            System.out.printf("第%d周 : %s%n", week, map.get(week));
        }
    }

    @Test
    public void testGetDayOfWeek() {
        System.out.println(DateUtil.getEnDayOfWeek(new Date()));
        System.out.println(DateUtil.getCnDayOfWeek(new Date()));
    }

}
