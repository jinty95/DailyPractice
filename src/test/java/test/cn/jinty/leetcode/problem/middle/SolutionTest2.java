package test.cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.problem.middle.Solution2;
import org.junit.Test;

/**
 * LeetCode - 算法题 - 测试
 *
 * @author jinty
 * @date 2021/9/20
 */
public class SolutionTest2 {

    private final Solution2 solution = new Solution2();

    @Test
    public void testFindNumberOfLIS() {
        System.out.println(solution.findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(solution.findNumberOfLIS(new int[]{2,2,2,2,2}));
        System.out.println(solution.findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }

    @Test
    public void testMinDistance() {
        System.out.println(solution.minDistance("sea","eat"));
        System.out.println(solution.minDistance("hello","hell"));
    }

    @Test
    public void testComputeArea() {
        System.out.println(solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        System.out.println(solution.computeArea(0, 0, 2, 2, 4, 4, 10, 10));
    }

    @Test
    public void testFractionToDecimal() {
        System.out.println(solution.fractionToDecimal(1, 2));
        System.out.println(solution.fractionToDecimal(1, 3));
        System.out.println(solution.fractionToDecimal(4, 333));
        System.out.println(solution.fractionToDecimal(-50, 8));
        System.out.println(solution.fractionToDecimal(-1, Integer.MIN_VALUE));
        System.out.println(solution.fractionToDecimal(Integer.MIN_VALUE, -1));
    }

}
