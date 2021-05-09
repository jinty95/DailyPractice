package leetcode;

import cn.jinty.leetcode.function.Fun5;
import org.junit.Test;

/**
 * LeetCode算法题 - 测试
 *
 * @author jinty
 * @date 2021/5/8
 **/
public class Test5 {

    private Fun5 fun5 = new Fun5();

    @Test
    public void testMinimumTimeRequired(){
        int[] jobs = {1,2,4,7,8};
        int k = 5;
        System.out.println(fun5.minimumTimeRequired(jobs,k));
    }

    @Test
    public void testIsNumber(){
        System.out.println("+" + "=" + fun5.isNumber("+"));
        System.out.println("." + "=" + fun5.isNumber("."));
        System.out.println("+." + "=" + fun5.isNumber("+."));
        System.out.println("  +.5  " + "=" + fun5.isNumber("  +.5  "));
        System.out.println("4.4.4" + "=" + fun5.isNumber("4.4.4"));
        System.out.println("1a3.14" + "=" + fun5.isNumber("1a3.14"));
        System.out.println("5E1.4" + "=" + fun5.isNumber("5E1.4"));
        System.out.println("12e+" + "=" + fun5.isNumber("12e+"));
        System.out.println("+e15" + "=" + fun5.isNumber("+e15"));
        System.out.println("-1E-16" + "=" + fun5.isNumber("-1E-16"));
        System.out.println("-+5" + "=" + fun5.isNumber("-+5"));
        System.out.println("-5" + "=" + fun5.isNumber("-5"));
    }

    @Test
    public void testMinDays(){
        int[] bloomDay = {1,2,3,4,5,6};
        System.out.println(fun5.minDays(bloomDay,3,2));
        System.out.println(fun5.minDays(bloomDay,3,1));
        System.out.println(fun5.minDays(bloomDay,2,2));
    }

}
