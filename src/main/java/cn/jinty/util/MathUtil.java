package cn.jinty.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 数字处理工具
 *
 * @author jinty
 * @date 2021/3/5
 **/
public final class MathUtil {

    /**
     * 判断数字是否为素数
     *
     * @param num 数字
     * @return 布尔
     */
    public static boolean isPrime(int num){
        if(num<2) return false;
        if(num==2 || num==3) return true;
        for(int i=2;i<=num/2;i++){
            if(num%i==0) return false;
        }
        return true;
    }

    /**
     * 分解质因数
     *
     * @param num 数字
     * @return 质因数
     */
    public static List<Integer> splitPrimeFactor(int num){
        List<Integer> list = new ArrayList<>();
        if(num<2) return list;
        int i=2;
        while(i<=num){
            if(num%i==0){
                list.add(i);
                num /= i;
            }else{
                i++;
            }
        }
        return list;
    }

    /**
     * 用一个long存储两个int
     *
     * @param h 高位
     * @param l 低位
     * @return long
     */
    public static long combineInt2Long(int h, int l){
        return (long)h << 32 | (long)l & 0xFFFFFFFFL;
    }

    /**
     * 浮点型的加法运算
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return 结果
     */
    public static Double doubleAdd(Double d1, Double d2){
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 浮点型的减法运算
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return 结果
     */
    public static Double doubleSubtract(Double d1, Double d2){
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 浮点型的乘法运算
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return 结果
     */
    public static Double doubleMultiply(Double d1, Double d2){
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 浮点型的除法运算
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return 结果
     */
    public static Double doubleDivide(Double d1, Double d2){
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.divide(b2).doubleValue();
    }

    /**
     * 对数运算
     *
     * @param base 底
     * @param value 真数
     * @return 对数
     */
    public static Double log(Double base,Double value){
        return Math.log(value) / Math.log(base);
    }

    /**
     * 阶乘运算
     *
     * @param n 阶乘值
     * @return 阶乘结果
     */
    public static long factorial(int n){
        long result = 1;
        for(int i=1;i<=n;i++){
            result *= i;
        }
        return result;
    }

    /**
     * 组合运算
     * C(n,m) = n!/(m!(n-m)!)
     *
     * @param n C的下标
     * @param m C的上标
     * @return 组合数
     */
    public static long combinationNum(int n,int m){
        if(n<0 || m<0){
            throw new IllegalArgumentException("n值与m值不能为负数");
        }
        if(n<m){
            throw new IllegalArgumentException("n值不能小于m值");
        }
        return factorial(n) / factorial(m) / factorial(n-m);
    }

}
