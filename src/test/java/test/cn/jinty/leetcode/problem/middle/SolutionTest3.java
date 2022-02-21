package test.cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.problem.middle.Solution3;
import org.junit.Test;

/**
 * LeetCode - 中等题 - 测试
 *
 * @author Jinty
 * @date 2022/2/21
 **/
public class SolutionTest3 {

    private final Solution3 solution = new Solution3();

    @Test
    public void testPushDominoes() {
        System.out.println(solution.pushDominoes("RR.L"));
        System.out.println(solution.pushDominoes(".L.R...LR..L.."));
    }

}
