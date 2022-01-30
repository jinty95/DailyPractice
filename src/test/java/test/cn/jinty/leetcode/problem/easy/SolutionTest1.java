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

    @Test
    public void testDayOfYear() {
        System.out.println(solution.dayOfYear("2021-02-05"));
        System.out.println(solution.dayOfYear("2021-12-21"));
    }

    @Test
    public void testDayOfTheWeek() {
        System.out.println(solution.dayOfTheWeek(1, 1, 1971));
        System.out.println(solution.dayOfTheWeek(7, 1, 2022));
        System.out.println(solution.dayOfTheWeek(1, 2, 2022));
    }

    @Test
    public void testContainsNearbyDuplicate() {
        System.out.println(solution.containsNearbyDuplicate(new int[]{1, 0, 1, 2}, 1));
        System.out.println(solution.containsNearbyDuplicate(new int[]{1, 0, 1, 2}, 2));
    }

    @Test
    public void testRemovePalindromeSub() {
        System.out.println(solution.removePalindromeSub("aba")); // 1
        System.out.println(solution.removePalindromeSub("aabb")); // 2
        System.out.println(solution.removePalindromeSub("ababaabb")); // 2
    }

    @Test
    public void testUncommonFromSentences() {
        System.out.println(Arrays.toString(solution.uncommonFromSentences("this apple is sweet", "this apple is sour")));
    }

}
