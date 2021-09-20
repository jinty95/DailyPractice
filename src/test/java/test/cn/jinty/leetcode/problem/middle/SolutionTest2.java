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

}
