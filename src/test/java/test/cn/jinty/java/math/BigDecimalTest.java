package test.cn.jinty.java.math;

import cn.jinty.util.ExceptionUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 大小数 - 测试
 *
 * @author Jinty
 * @date 2022/5/18
 **/
public class BigDecimalTest {

    @Test
    public void testNew() {
        String[] arr = {"100", "0E-7", "5000", "5,305.28"};
        // 金额按千分位格式化后，无法解析成功，所以在代码中，要避免金额格式化，如有必要，在前端实现
        for (String num : arr) {
            try {
                System.out.println(new BigDecimal(num));
            } catch (Exception ex) {
                try {
                    throw new IllegalArgumentException(null, ex);
                } catch (Exception e) {
                    System.out.println("解析数字失败：num=" + num + ", error=" + ExceptionUtil.getMessage(e) + ", deepError=" + ExceptionUtil.getDeepMessage(e));
                    String stackTrace = ExceptionUtil.getStackTrace(e);
                    System.out.println("异常堆栈长度：" + stackTrace.length());
                    System.err.println(stackTrace);
                }
            }
        }
    }

    @Test
    public void testNew1() {
        BigDecimal num = new BigDecimal("1.11");
        System.out.println(num.longValue());
        num = new BigDecimal("aaa");
        System.out.println(num);
    }

    @Test
    public void testToString() {
        BigDecimal b = new BigDecimal("0.0000000");
        System.out.println(b);
        System.out.println(b.toPlainString());
        System.out.println(b.stripTrailingZeros().toPlainString());
        System.out.println(b.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    @Test
    public void testSetScale() {
        BigDecimal b = BigDecimal.valueOf(3.1415926);
        System.out.println(b);
        b = b.setScale(2, RoundingMode.HALF_UP);
        System.out.println(b);
        b = b.setScale(4, RoundingMode.HALF_UP);
        System.out.println(b);
    }

    @Test
    public void testCompare() {
        BigDecimal b1 = new BigDecimal("0.00");
        BigDecimal b2 = new BigDecimal("0");
        System.out.println(b1.equals(b2)); // 值跟精度都相等时才相等
        System.out.println(b1.compareTo(b2)); // 值相等时相等
    }

}
