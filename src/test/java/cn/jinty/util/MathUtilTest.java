package cn.jinty.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    @Test
    public void testLog(){
        System.out.println(MathUtil.log(2.0,1024.0));
        System.out.println(MathUtil.log(10.0,100000.0));
        System.out.println(MathUtil.log(3.0,81.0));
    }

    @Test
    public void testIntMultiply(){
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE/2;
        //直接相乘溢出
        System.out.println(i*j);
        //使用long保证任何int相乘都不会溢出
        System.out.println((long)i * (long)j);
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
    public void testFactorial(){
        System.out.println(MathUtil.factorial(4));
        System.out.println(MathUtil.factorial(5));
        System.out.println(MathUtil.factorial(6));
    }

    @Test
    public void testCombinationNum(){
        System.out.println(MathUtil.combinationNum(3,1));
        System.out.println(MathUtil.combinationNum(4,2));
        System.out.println(MathUtil.combinationNum(4,5));
    }

    @Test
    public void testSumFromOneToN(){
        System.out.println(MathUtil.sumFromOneToN(1));
        System.out.println(MathUtil.sumFromOneToN(2));
        System.out.println(MathUtil.sumFromOneToN(100));
        System.out.println(MathUtil.sumFromOneToN(101));
        System.out.println(MathUtil.sumFromOneToN(Integer.MAX_VALUE));
    }

    @Test
    public void testLowBit(){
        int num = 9999;
        System.out.println(Integer.toBinaryString(num));
        while(num>0){
            int lowBit = MathUtil.lowBit(num);
            System.out.println(Integer.toBinaryString(lowBit));
            num -= lowBit;
        }
    }

    @Test
    public void testAdd(){
        System.out.println(MathUtil.add(1,1));
        System.out.println(MathUtil.add(7,8));
        System.out.println(MathUtil.add(999,1001));
        System.out.println(MathUtil.add(Integer.MAX_VALUE,Integer.MAX_VALUE));
    }

    @Test
    public void testMultiply(){
        System.out.println(MathUtil.multiply(2,2));
        System.out.println(MathUtil.multiply(20,25));
        System.out.println(MathUtil.multiply(-2,2));
        System.out.println(MathUtil.multiply(2,-30));
        System.out.println(MathUtil.multiply(1000000000,3));
    }

    @Test
    public void testPow(){
        System.out.println(Math.pow(10,-3));
        System.out.println(MathUtil.pow(10,-3));
    }

}