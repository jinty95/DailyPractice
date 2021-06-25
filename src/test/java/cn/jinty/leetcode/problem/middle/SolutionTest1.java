package cn.jinty.leetcode.problem.middle;

import org.junit.Test;

/**
 * LeetCode - 算法题 - 测试
 *
 * @author jinty
 * @date 2021/6/25
 **/
public class SolutionTest1 {

    private final Solution1 solution = new Solution1();

    @Test
    public void testOpenLock(){
        String[] deadEnds = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(solution.openLock(deadEnds,target));
    }

}
