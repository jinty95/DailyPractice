package cn.jinty.util;

import cn.jinty.util.collection.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数学 - 工具类
 *
 * @author Jinty
 * @date 2021/3/5
 **/
public final class MathUtil {

    private MathUtil() {
    }

    private static final Random RANDOM = new Random();

    // 数字与字母
    public static final char[] numberAndLetter = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 判断数字是否为素数
     *
     * @param num 数字
     * @return 布尔
     */
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * 分解质因数
     *
     * @param num 数字
     * @return 质因数
     */
    public static List<Integer> splitPrimeFactor(int num) {
        List<Integer> list = new ArrayList<>();
        if (num < 2) return list;
        int i = 2;
        while (i <= num) {
            if (num % i == 0) {
                list.add(i);
                num /= i;
            } else {
                i++;
            }
        }
        return list;
    }

    /**
     * 获取最小质因数
     *
     * @param num 数字
     * @return 最小质因数
     */
    public static int minPrimeFactor(int num) {
        if (num < 2) {
            throw new IllegalArgumentException("输入数字必须大于1");
        }
        int i = 2;
        while (i <= num) {
            if (num % i == 0) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    /**
     * 求最大公约数 (辗转相除法)
     *
     * @param a 整数1
     * @param b 整数2
     * @return 最大公约数
     */
    public static int greatestCommonDivisor(int a, int b) {
        if (a == 0) {
            return b;
        }
        return greatestCommonDivisor(b % a, a);
    }

    /**
     * 两个int转为一个long
     *
     * @param h 高位整数
     * @param l 低位整数
     * @return 长整数
     */
    public static long twoIntToLong(int h, int l) {
        return (long) h << 32 | (long) l & 0xFFFFFFFFL;
    }

    /**
     * 一个long转为两个int
     *
     * @param l 长整数
     * @return 两个整数
     */
    public static int[] longToTwoInt(long l) {
        int[] res = new int[2];
        res[0] = (int) ((l >> 32) & 0xFFFFFFFFL);
        res[1] = (int) (l & 0xFFFFFFFFL);
        return res;
    }

    /**
     * 四个byte转为一个int
     *
     * @param bytes 四个字节(依次从高8位到低8位)
     * @return 整数
     */
    public static int fourByteToInt(byte[] bytes) {
        int num = 0;
        for (int i = 3; i >= 0; i--) {
            num |= ((int) bytes[3 - i] << i * 8) & (255 << i * 8);
        }
        return num;
    }

    /**
     * 一个int转为四个byte
     *
     * @param num 整数
     * @return 四个字节(依次从高8位到低8位)
     */
    public static byte[] intToFourByte(int num) {
        byte[] bytes = new byte[4];
        for (int i = 3; i >= 0; i--) {
            bytes[3 - i] = (byte) (num >> i * 8);
        }
        return bytes;
    }

    /**
     * 对数运算
     *
     * @param base  底
     * @param value 真数
     * @return 对数
     */
    public static Double log(Double base, Double value) {
        return Math.log(value) / Math.log(base);
    }

    /**
     * 阶乘运算
     *
     * @param n 阶乘值
     * @return 阶乘结果
     */
    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
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
    public static int combinationNum(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("n值与m值不能为负数");
        }
        if (n < m) {
            throw new IllegalArgumentException("n值不能小于m值");
        }
        return factorial(n) / factorial(m) / factorial(n - m);
    }

    /**
     * 获取整数位数最低的1
     *
     * @param num 整型数字
     * @return 位数最低的1
     */
    public static int lowBit(int num) {
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
    public static int add(int a, int b) {
        //无进位和
        int sum = a;
        //进位
        int carry = b;
        //循环直到进位等于0
        while (carry != 0) {
            int temp = sum ^ carry;
            carry = (sum & carry) << 1;
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
    public static int multiply(int a, int b) {
        //乘0得0
        if (a == 0 || b == 0) return 0;
        //负负得正
        if (a < 0 && b < 0) return multiply(-a, -b);
        //正数
        int positive = a > 0 ? a : b;
        //另一个数
        int another = a > 0 ? b : a;
        //乘积
        int sum = 0;
        //按位分解正数
        for (int k = 0; k < 32; k++) {
            if (((positive >> k) & 1) == 1) {
                sum += (another << k);
            }
        }
        return sum;
    }

    /**
     * 除法的实现(输入输出都为整数)
     *
     * @param a 被除数
     * @param b 除数
     * @return 整数结果
     */
    public static int divide(int a, int b) {
        //非法除数
        if (b == 0) throw new IllegalArgumentException("除数不能为0");
        //转为long防止溢出
        long la = a;
        long lb = b;
        //确定正负号
        boolean minus = (a < 0 && b > 0) || (a > 0 && b < 0);
        //转为正数
        if (la < 0) la = -la;
        if (lb < 0) lb = -lb;
        //除数倍增，记录倍数N，被除数若大于除数，说明被除数可以分解出N个除数
        long result = 0;
        long count = 1;
        long multi = lb;
        while (la >= multi) {
            multi <<= 1;
            count <<= 1;
        }
        //除数倍增达到最大值后开始倍减，同时累计被除数可以分解出的除数数量
        while (multi > lb) {
            multi >>= 1;
            count >>= 1;
            if (la >= multi) {
                la -= multi;
                result += count;
            }
        }
        if (minus) {
            return (int) -result;
        }
        return (int) (result > Integer.MAX_VALUE ? Integer.MAX_VALUE : result);
    }

    /**
     * 指数运算 (快速幂)
     * 将次方拆分为2次幂的和：a^b = a^(2^0 + 2^1 + ...) = a^(2^0) * a^(2^1) * ...
     *
     * @param base     底数 (正数)
     * @param exponent 指数 (正数)
     * @return 结果
     */
    public static int pow(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= base;
            }
            exponent >>= 1;
            base *= base;
        }
        return result;
    }

    /**
     * 十进制转二进制
     *
     * @param number 十进制整数
     * @return 二进制字符串
     */
    public static String toBinaryString(int number) {
        return toString(number, 2);
    }

    /**
     * 十进制转N进制
     * 以十进制转二进制为例：7 = 1 * 2^2 + 1 * 2^1 + 1 * 2^0 = (111)₂
     * 最后一个 1 可以通过 7 % 2 得到，然后 7 / 2 = 3 ，倒数第二个 1 可以通过 3 % 2 得到，以此类推，直到被除数为 0 。
     * 另外，注意特殊处理负数及零的情况。
     *
     * @param number 十进制整数
     * @param radix  N进制
     * @return N进制字符串
     * @throws IllegalArgumentException 进制范围必须在[2,36]
     */
    public static String toString(int number, int radix) {
        if (radix < 2 || radix > 36) {
            throw new IllegalArgumentException("进制范围限制[2,36], 当前输入为" + radix);
        }
        if (number == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean positive = true;
        if (number < 0) {
            positive = false;
            number = -number;
        }
        while (number != 0) {
            sb.append(numberAndLetter[number % radix]);
            number /= radix;
        }
        return positive ? sb.reverse().toString() : "-" + sb.reverse();
    }

    /**
     * 小数转为百分比
     *
     * @param decimal 小数
     * @return 百分比
     */
    public static String decimalToPercentage(Double decimal) {
        if (decimal == null) {
            return "";
        }
        return String.format("%.2f", decimal * 100) + "%";
    }

    /**
     * 将一个数额随机切分为几个部分 (最小切分为1)
     *
     * @param amount 数额
     * @param n      个数
     * @return 结果
     */
    public static int[] split(int amount, int n) {
        // 无法切分
        if (n < 1 || amount < 1 || amount < n) {
            return new int[0];
        }
        // 随机切分
        int max = amount - (n - 1);
        int[] result = new int[n];
        // 前n-1个随机生成
        for (int i = 1; i < n; i++) {
            result[i - 1] = RANDOM.nextInt(max) + 1;
            amount -= result[i - 1];
            max = amount - (n - i - 1);
        }
        // 第n个直接取剩余数额
        result[n - 1] = amount;
        // 打乱数组
        ArrayUtil.shuffle(result);
        return result;
    }

    /**
     * 包装类加法 - Integer
     *
     * @param a 操作数1
     * @param b 操作数2
     * @return 和
     */
    public static Integer add(Integer a, Integer b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

    /**
     * 包装类加法 - Long
     *
     * @param a 操作数1
     * @param b 操作数2
     * @return 和
     */
    public static Long add(Long a, Long b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

    /**
     * 整数相除，结果四舍五入
     *
     * @param a 被除数
     * @param b 除数
     * @return 结果
     */
    public static int divideAndRound(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为0");
        }
        double res = a * 1.0 / b;
        return (int) Math.round(res);
    }

    /**
     * 整数相除，结果四舍五入
     *
     * @param a 被除数
     * @param b 除数
     * @return 结果
     */
    public static long divideAndRound(long a, long b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为0");
        }
        double res = a * 1.0 / b;
        return Math.round(res);
    }

    /**
     * 生成九九乘法表
     *
     * @return 九九乘法表
     */
    public static List<List<String>> genMultiplicationTable() {
        List<List<String>> lists = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                list.add(String.format("%dx%d=%2d", j, i, j * i));
            }
            lists.add(list);
        }
        return lists;
    }

}
