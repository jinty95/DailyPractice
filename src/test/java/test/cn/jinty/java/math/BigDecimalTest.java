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
    public void testNewBigDecimal() {
        String[] arr = {"100", "0E-7", "5000", "5,305.28"};
        // 金额按千分位格式化后，无法解析成功，所以在代码中，要避免金额格式化，如有必要，在前端实现
        for (String num : arr) {
            try {
                System.out.println(new BigDecimal(num));
            } catch (Exception ex) {
                try {
                    throw new IllegalArgumentException("参数异常", ex);
                } catch (Exception e) {
                    System.out.println("系统异常：num=" + num + ", error=" + ExceptionUtil.getMessage(e) + ", deepError=" + ExceptionUtil.getDeepMessage(e));
                    System.err.println(ExceptionUtil.getStackTrace(e));
                }
            }
        }
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

}
