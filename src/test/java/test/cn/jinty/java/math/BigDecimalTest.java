package test.cn.jinty.java.math;

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
