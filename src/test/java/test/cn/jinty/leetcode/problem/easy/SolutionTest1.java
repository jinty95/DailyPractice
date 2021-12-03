package test.cn.jinty.leetcode.problem.easy;

import cn.jinty.leetcode.problem.easy.Solution1;
import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode - 简单题 - 测试
 *
 * @author Jinty
 * @date 2021/11/23
 **/
public class SolutionTest1 {

    private static final Solution1 solution = new Solution1();

    @Test
    public void testBuddyStrings() {
        System.out.println(solution.buddyStrings("aa", "aa"));
        System.out.println(solution.buddyStrings("ab", "ab"));
        System.out.println(solution.buddyStrings("abc", "acb"));
    }

    @Test
    public void testMaximumScore() {
        System.out.println(solution.maximumScore(new int[]{1, 2, 8, 9}, 3));
        System.out.println(solution.maximumScore(new int[]{1, 2, 8, 7}, 2));
        System.out.println(solution.maximumScore(new int[]{1, 3, 5}, 1));
        System.out.println(solution.maximumScore(new int[]{1, 3, 4, 5}, 4));
    }

    @Test
    public void testFindRelativeRanks() {
        System.out.println(Arrays.toString(solution.findRelativeRanks(new int[]{1, 2})));
        System.out.println(Arrays.toString(solution.findRelativeRanks(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(solution.findRelativeRanks(new int[]{10, 1, 3, 5, 2, 9, 8, 6})));
    }

    @Test
    public void testLargestSumAfterKNegations() {
        System.out.println(solution.largestSumAfterKNegations(new int[]{2, 3, 4}, 1));
        System.out.println(solution.largestSumAfterKNegations(new int[]{-5, -4, 2, 3, 4}, 3));
        System.out.println(solution.largestSumAfterKNegations(new int[]{-8, -5, -5, -3, -2, 3}, 6));
        System.out.println(solution.largestSumAfterKNegations(new int[]{-8, -3, -2}, 4));
    }

}
