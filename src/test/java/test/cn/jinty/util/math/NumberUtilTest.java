package test.cn.jinty.util.math;

import cn.jinty.util.math.NumberUtil;
import cn.jinty.util.object.ClassUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 数字 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public class NumberUtilTest {

    private List<Class<? extends Number>> getNumberClasses() {
        return Arrays.asList(Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
    }

    @Test
    public void testCastType() {
        Number[] numbers = {null, (byte) 1, (short) 2, 3, 4L, 5.0F, 6.0D};
        List<Class<? extends Number>> classes = getNumberClasses();
        for (Number num : numbers) {
            for (Class<? extends Number> toType : classes) {
                Number res = NumberUtil.castType(num, toType);
                System.out.printf("数字类型转换：输入数字=%s，输入类型=%s，预期输出类型=%s，输出类型=%s，输出数字=%s%n",
                        num, ClassUtil.getClass(num), toType, ClassUtil.getClass(res), res);
            }
            System.out.println();
        }
    }

    @Test
    public void testAdd() {
        Number[][][] numArr3 = {
                {{null, null, null}, {null, (byte) 1, (byte) 1}, {(byte) 1, null, (byte) 1}, {(byte) 1, (byte) 1, (byte) 2}},
                {{null, null, null}, {null, (short) 1, (short) 1}, {(short) 1, null, (short) 1}, {(short) 1, (short) 1, (short) 2}},
                {{null, null, null}, {null, 1, 1}, {1, null, 1}, {1, 1, 2}},
                {{null, null, null}, {null, 1L, 1L}, {1L, null, 1L}, {1L, 1L, 2L}},
                {{null, null, null}, {null, 1.0F, 1.0F}, {1.0F, null, 1.0F}, {1.0F, 1.0F, 2.0F}},
                {{null, null, null}, {null, 1.0, 1.0}, {1.0, null, 1.0}, {1.0, 1.0, 2.0}},
        };
        for (Number[][] numArr2 : numArr3) {
            for (Number[] numArr1 : numArr2) {
                Assert.assertEquals(NumberUtil.add(numArr1[0], numArr1[1]), numArr1[2]);
            }
        }
    }

    @Test
    public void testValueOf() {
        String[] arr = {null, "", "1", "64"};
        List<Class<? extends Number>> classes = getNumberClasses();
        for (String s : arr) {
            System.out.println("输入字符串：" + s);
            for (Class<? extends Number> clazz : classes) {
                System.out.printf("转为%s：%s%n", clazz.getSimpleName(), NumberUtil.valueOf(s, clazz));
            }
            System.out.println();
        }
    }

}
