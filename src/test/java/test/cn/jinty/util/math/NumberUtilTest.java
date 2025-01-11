package test.cn.jinty.util.math;

import cn.jinty.util.math.NumberUtil;
import cn.jinty.util.object.ClassUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 数字 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public class NumberUtilTest {

    private int[] getArr() {
        return new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 9901, 9902};
    }

    @Test
    public void testSplitByNum() {
        NumberUtil.NumberRange range = new NumberUtil.NumberRange(1, 100);
        int[] arr = this.getArr();
        System.out.println(range);
        for (int n : arr) {
            System.out.printf("按数量分组，每组%s个：%s%n", n, NumberUtil.splitByNum(range, n));
        }
    }

    @Test
    public void testSplitToNGroup() {
        NumberUtil.NumberRange range = new NumberUtil.NumberRange(100, 10000);
        int[] arr = this.getArr();
        System.out.println(range);
        for (int n : arr) {
            System.out.printf("分为%s组：%s%n", n, NumberUtil.splitToNGroup(range, n));
        }
    }

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

    @Test
    public void testNumberProrate() {
        List<NumberUtil.NumberProrateEntity> numberList = Arrays.asList(
                new NumberUtil.NumberProrateEntity(1L, new BigDecimal("100"), null),
                new NumberUtil.NumberProrateEntity(2L, new BigDecimal("60"), null),
                new NumberUtil.NumberProrateEntity(3L, new BigDecimal("50"), null),
                new NumberUtil.NumberProrateEntity(4L, new BigDecimal("40"), null),
                new NumberUtil.NumberProrateEntity(5L, new BigDecimal("50"), null)
        );
        BigDecimal number = new BigDecimal("280");
        System.out.println(number);
        System.out.println(numberList);
        NumberUtil.numberProrate(number, numberList);
        System.out.println(numberList);
        NumberUtil.numberProrate(number, numberList, 1);
        System.out.println(numberList);
        NumberUtil.numberProrate(number, numberList, 0);
        System.out.println(numberList);
    }

}
