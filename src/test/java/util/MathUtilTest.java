package util;

import cn.jinty.utils.MathUtil;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 数学工具类测试
 *
 * @author jinty
 * @date 2021/4/13
 **/
public class MathUtilTest {

    @Test
    public void testDoubleOperation(){

        //Double
        Double d1 = 4.015;
        Double d2 = 1000.0;
        System.out.println(d1+" + "+d2+" = "+(d1+d2));
        System.out.println(d1+" - "+d2+" = "+(d1-d2));
        System.out.println(d1+" * "+d2+" = "+(d1*d2));
        System.out.println(d1+" / "+d2+" = "+(d1/d2));
        System.out.println();

        //BigDecimal
        BigDecimal b1 = new BigDecimal(4.015);
        BigDecimal b2 = new BigDecimal(1000.0);
        System.out.println(b1+" + "+b2+" = "+(b1.add(b2)));
        System.out.println(b1+" - "+b2+" = "+(b1.subtract(b2)));
        System.out.println(b1+" * "+b2+" = "+(b1.multiply(b2)));
        System.out.println(b1+" / "+b2+" = "+(b1.divide(b2)));
        System.out.println();

        //MathUtil
        System.out.println(d1+" + "+d2+" = "+MathUtil.doubleAdd(d1,d2));
        System.out.println(d1+" - "+d2+" = "+MathUtil.doubleSubtract(d1,d2));
        System.out.println(d1+" * "+d2+" = "+MathUtil.doubleMultiply(d1,d2));
        System.out.println(d1+" / "+d2+" = "+MathUtil.doubleDivide(d1,d2));
        System.out.println();

    }

}
