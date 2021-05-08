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

}
