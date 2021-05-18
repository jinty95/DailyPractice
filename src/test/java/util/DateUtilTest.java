package util;

import cn.jinty.util.DateUtil;
import org.junit.Test;

/**
 * 日期时间 - 工具类 - 测试
 *
 * @author jinty
 * @date 2021/5/18
 **/
public class DateUtilTest {

    @Test
    public void getBeginAndEndOfYear(){
        int year = 2020;
        System.out.println(
                DateUtil.YYYY_MM_DD_HH_MM_SS.format(
                        DateUtil.getBeginOfYear(year)
                )
        );
        System.out.println(
                DateUtil.YYYY_MM_DD_HH_MM_SS.format(
                        DateUtil.getEndOfYear(year)
                )
        );
        System.out.println(
                DateUtil.YYYY_MM_DD_HH_MM_SS.format(
                        DateUtil.getBeginOfThisYear()
                )
        );
        System.out.println(
                DateUtil.YYYY_MM_DD_HH_MM_SS.format(
                        DateUtil.getEndOfThisYear()
                )
        );
    }

}
