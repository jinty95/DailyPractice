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

    //数字与字母
    public static final char[] numberAndLetter = {
            '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G',
            'H','I','J','K','L','M','N',
            'O','P','Q','R','S','T',
            'U','V','W','X','Y','Z'
    };

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

    /**
     * 从1到N的累加和
     *
     * @param n 正整数
     * @return 累加和
     */
    public static long sumFromOneToN(int n){
        if(n<1){
            throw new IllegalArgumentException("n不能小于1");
        }
        return (1L+n) * n / 2;
    }

    /**
     * 获取整数位数最低的1
     *
     * @param num 整型数字
     * @return 位数最低的1
     */
    public static int lowBit(int num){
        //-num是num按位取反+1，num&(-num)即可得到最低的1
        //例如: num=0110, -num=1010  num*(-num)=0010
        return num & (-num);
    }

    /**
     * 加法的实现
     *
     * @param a 整数
     * @param b 整数
     * @return 和
     */
    public static long add(int a,int b){
        //无进位和
        long sum = a;
        //进位
        long carry = b;
        //循环直到进位等于0
        while(carry!=0){
            long temp = sum ^ carry;
            carry = (sum & carry)<<1;
            sum = temp;
        }
        return sum;
    }

    /**
     * 乘法的实现
     * 把b表示为2次幂的和，利用乘法分配律，将a*b转为多个a*2^k的和，而a*2^k可以用左移k位实现
     *
     * @param a 整数
     * @param b 整数
     * @return 乘积
     */
    public static long multiply(int a,int b){
        //乘0得0
        if(a==0 || b==0) return 0L;
        //负负得正
        if(a<0 && b<0) return multiply(-a,-b);
        //正数
        long positive = a>0 ? a : b;
        //另一个数
        long another = a>0 ? b : a;
        //乘积
        long sum = 0;
        //按位分解正数
        for(int k=0;k<32;k++){
            if(((positive>>k)&1) == 1){
                sum += (another<<k);
            }
        }
        return sum;
    }

    /**
     * 指数运算
     *
     * @param a base 底数
     * @param b exponent 指数
     * @return 结果
     */
    public static double pow(double a, int b){
        double ans = 1;
        //负数次：a^(-b)=(1/a)^b
        if(b<0){
            a = 1/a;
            b = -b;
        }
        double temp = a;
        //将次方拆分为2次幂的和：a^b=a^(2^k1+2^k2...)=a^(2^k1)*a^(2^k2)*...
        while(b>0){
            //判断指数的每一位是否为1
            if((b&1)==1){
                ans *= temp;
            }
            b >>= 1;
            //底数倍增：temp=a^(2^k)
            temp *= temp;
        }
        return ans;
    }

    /**
     * 十进制转二进制
     *
     * @param number 十进制整数
     * @return 二进制字符串
     */
    public static String toBinaryString(int number){
        return toString(number,2);
    }

    /**
     * 十进制转N进制
     *
     * @param number 十进制整数
     * @param radix N进制
     * @throws IllegalArgumentException 进制范围必须在[2,36]
     * @return N进制字符串
     */
    public static String toString(int number, int radix){
        if(radix<2 || radix>36){
            throw new IllegalArgumentException("radix must in [2,36], illegal radix : "+radix);
        }
        StringBuilder sb = new StringBuilder();
        while(number!=0){
            sb.append(numberAndLetter[number % radix]);
            number /= radix;
        }
        return sb.reverse().toString();
    }

}
