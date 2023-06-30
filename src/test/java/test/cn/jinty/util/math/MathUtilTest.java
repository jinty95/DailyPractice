package test.cn.jinty.util.math;

import cn.jinty.util.math.MathUtil;
import cn.jinty.util.collection.ListUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * 数学工具类测试
 *
 * @author Jinty
 * @date 2021/4/13
 **/
public class MathUtilTest {

    @Test
    public void testIsPrime() {
        System.out.println(MathUtil.isPrime(1));
        System.out.println(MathUtil.isPrime(5));
        System.out.println(MathUtil.isPrime(99));
    }

    @Test
    public void testSplitPrimeFactor() {
        System.out.println(MathUtil.splitPrimeFactor(5));
        System.out.println(MathUtil.splitPrimeFactor(999));
    }

    @Test
    public void testGreatestCommonDivisor() {
        System.out.println(MathUtil.greatestCommonDivisor(1, 99999));
        System.out.println(MathUtil.greatestCommonDivisor(4, 6));
        System.out.println(MathUtil.greatestCommonDivisor(81, 9));
    }

    @Test
    public void testTwoIntToLong() {
        long l1 = MathUtil.twoIntToLong(1, 1);
        System.out.println(l1);
        System.out.println(Arrays.toString(MathUtil.longToTwoInt(l1)));
        long l2 = MathUtil.twoIntToLong(2021, 1103);
        System.out.println(l2);
        System.out.println(Arrays.toString(MathUtil.longToTwoInt(l2)));
    }

    @Test
    public void testFourByteToInt() {
        byte[] bytes = {-1, -1, -1, -1};
        int i = MathUtil.fourByteToInt(bytes);
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Arrays.toString(MathUtil.intToFourByte(i)));
    }

    @Test
    public void testDoubleOperation() {
        //Double
        Double d1 = 4.015;
        Double d2 = 1000.0;
        System.out.println(d1 + " + " + d2 + " = " + (d1 + d2));
        System.out.println(d1 + " - " + d2 + " = " + (d1 - d2));
        System.out.println(d1 + " * " + d2 + " = " + (d1 * d2));
        System.out.println(d1 + " / " + d2 + " = " + (d1 / d2));
        System.out.println();
        //BigDecimal
        BigDecimal b1 = new BigDecimal("4.015");
        BigDecimal b2 = new BigDecimal("1000.0");
        System.out.println(b1 + " + " + b2 + " = " + (b1.add(b2)));
        System.out.println(b1 + " - " + b2 + " = " + (b1.subtract(b2)));
        System.out.println(b1 + " * " + b2 + " = " + (b1.multiply(b2)));
        System.out.println(b1 + " / " + b2 + " = " + (b1.divide(b2)));
        System.out.println(b1 + " / " + b2 + " = " + (b1.divide(b2, RoundingMode.HALF_UP)));
        System.out.println(b1 + " / " + b2 + " = " + (b1.divide(b2, 5, RoundingMode.HALF_UP)));
        System.out.println();
    }

    @Test
    public void testLog() {
        System.out.println(MathUtil.log(2.0, 1024.0));
        System.out.println(MathUtil.log(10.0, 100000.0));
        System.out.println(MathUtil.log(3.0, 81.0));
    }

    @Test
    public void testIntMultiply() {
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE / 2;
        //直接相乘溢出
        System.out.println(i * j);
        //使用long保证任何int相乘都不会溢出
        System.out.println((long) i * (long) j);
        //使用大整数保证不会溢出
        BigInteger bigIntegerI = new BigInteger(String.valueOf(i));
        BigInteger bigIntegerJ = new BigInteger(String.valueOf(j));
        System.out.println(bigIntegerI.multiply(bigIntegerJ));
        //使用大十进制保证不会溢出
        BigDecimal bigDecimalI = new BigDecimal(String.valueOf(i));
        BigDecimal bigDecimalJ = new BigDecimal(String.valueOf(j));
        System.out.println(bigDecimalI.multiply(bigDecimalJ));
    }

    @Test
    public void testFactorial() {
        System.out.println(MathUtil.factorial(4));
        System.out.println(MathUtil.factorial(5));
        System.out.println(MathUtil.factorial(6));
        System.out.println(MathUtil.factorial(33));
    }

    @Test
    public void testCombinationNum() {
        System.out.println(MathUtil.combinationNum(3, 1));
        System.out.println(MathUtil.combinationNum(4, 2));
        System.out.println(MathUtil.combinationNum(6, 5));
        System.out.println(MathUtil.combinationNum(33, 2)); //溢出得到错误结果
    }

    @Test
    public void testLowBit() {
        int num = 9999;
        System.out.println(Integer.toBinaryString(num));
        while (num > 0) {
            int lowBit = MathUtil.lowBit(num);
            System.out.println(Integer.toBinaryString(lowBit));
            num -= lowBit;
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testAdd() {
        System.out.println(MathUtil.add(1, 1) + " " + (1 + 1));
        System.out.println(MathUtil.add(7, 8) + " " + (7 + 8));
        System.out.println(MathUtil.add(999, 1001) + " " + (999 + 1001));
        System.out.println(MathUtil.add(Integer.MAX_VALUE, Integer.MAX_VALUE) + " " + (Integer.MAX_VALUE + Integer.MAX_VALUE));
    }

    @Test
    public void testMultiply() {
        System.out.println(MathUtil.multiply(2, 2));
        System.out.println(MathUtil.multiply(20, 25));
        System.out.println(MathUtil.multiply(-2, 2));
        System.out.println(MathUtil.multiply(2, -30));
        System.out.println(MathUtil.multiply(1000000000, 3));
    }

    @Test
    public void testDivide() {
        System.out.println(MathUtil.divide(10, 3));
        System.out.println(MathUtil.divide(10, -3));
        System.out.println(MathUtil.divide(10000000, 2));
        System.out.println(MathUtil.divide(Integer.MIN_VALUE, -1));
        System.out.println(MathUtil.divide(Integer.MIN_VALUE, 2));
        System.out.println(MathUtil.divide(Integer.MIN_VALUE, 1));
        System.out.println(MathUtil.divide(Integer.MAX_VALUE, 1));
    }

    @Test
    public void testPow() {
        System.out.println(Math.pow(10, 3));
        System.out.println(MathUtil.pow(10, 3));
        System.out.println(Math.pow(2, 10));
        System.out.println(MathUtil.pow(2, 10));
    }

    @Test
    public void testToString() {
        System.out.println(MathUtil.toBinaryString(15));
        System.out.println(MathUtil.toString(36, 36));
        System.out.println(MathUtil.toString(36, 2));
        System.out.println(MathUtil.toString(-36, 2)); // 负数转二进制不是标准的二进制数
        System.out.println(MathUtil.toString(1000, 5));
        System.out.println(MathUtil.toString(-1000, 5));
        System.out.println(MathUtil.toString(0, 5));
    }

    @Test
    public void testDecimalToPercentage() {
        System.out.println(MathUtil.decimalToPercentage(0.45454));
        System.out.println(MathUtil.decimalToPercentage(0.45455));
        System.out.println(MathUtil.decimalToPercentage(0.45456));
        System.out.println(Math.round(45.4));
        System.out.println(Math.round(45.5));
        System.out.println(Math.round(45.6));
        System.out.println(MathUtil.decimalToPercentage(-0.45454));
        System.out.println(MathUtil.decimalToPercentage(-0.45455));
        System.out.println(MathUtil.decimalToPercentage(-0.45456));
        System.out.println(Math.round(-45.4));
        System.out.println(Math.round(-45.5));
        System.out.println(Math.round(-45.6));
    }

    @Test
    public void testSplit() {
        System.out.println(Arrays.toString(MathUtil.split(100, 2)));
        System.out.println(Arrays.toString(MathUtil.split(100, 3)));
        System.out.println(Arrays.toString(MathUtil.split(100, 5)));
        System.out.println(Arrays.toString(MathUtil.split(100, 8)));
        System.out.println(Arrays.toString(MathUtil.split(100, 10)));
    }

    @Test
    public void testDivideAndRound() {
        System.out.println(MathUtil.divideAndRound(3, 2)); // 1.5
        System.out.println(MathUtil.divideAndRound(5, 3)); // 1.6
        System.out.println(MathUtil.divideAndRound(3, 5)); // 0.6
        System.out.println(MathUtil.divideAndRound(2, 5)); // 0.4
        System.out.println(MathUtil.divideAndRound(3L, 2L)); // 1.5
        System.out.println(MathUtil.divideAndRound(5L, 3L)); // 1.6
        System.out.println(MathUtil.divideAndRound(3L, 5L)); // 0.6
        System.out.println(MathUtil.divideAndRound(2L, 5L)); // 0.4
    }

    @Test
    public void testGenMultiplicationTable() {
        for (List<String> list : MathUtil.genMultiplicationTable()) {
            System.out.println(ListUtil.toString(list, ", "));
        }
    }

}
