package test.cn.jinty.util.math;

import cn.jinty.util.math.NumberCastUtil;
import cn.jinty.util.object.ClassUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 数字类型转换 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public class NumberCastUtilTest {

    @Test
    public void testCast() {
        Number[] numbers = {null, (byte) 1, (short) 2, 3, 4L, 5.0F, 6.0D};
        List<Class<? extends Number>> classes = Arrays.asList(
                null, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
        for (Number num : numbers) {
            for (Class<? extends Number> toType : classes) {
                Number res = NumberCastUtil.cast(num, toType);
                System.out.printf("数字类型转换：输入数字=%s，输入类型=%s，预期输出类型=%s，输出类型=%s，输出数字=%s%n",
                        num, ClassUtil.getClass(num), toType, ClassUtil.getClass(res), res);
            }
            System.out.println();
        }
    }

}
