package test.cn.jinty.leetcode.problem.easy;

import cn.jinty.leetcode.problem.easy.Solution1;
import org.junit.Test;

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

}
