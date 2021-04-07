package cn.jinty.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字处理工具
 *
 * @author jinty
 * @date 2021/3/5
 **/
public class MathUtil {

    /**
     * 判断数字是否为素数
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
     * @param h 高位
     * @param l 低位
     * @return long
     */
    public static long combineInt2Long(int h, int l){
        return (long)h << 32 | (long)l & 0xFFFFFFFFL;
    }

}
