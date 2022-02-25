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

    @Test
    public void testPancakeSort() {
        System.out.println(solution.pancakeSort(new int[]{1, 2, 3}));
        System.out.println(solution.pancakeSort(new int[]{3, 2, 4, 1}));
    }

    @Test
    public void testComplexNumberMultiply() {
        System.out.println(solution.complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(solution.complexNumberMultiply("1+-1i", "1+-1i"));
        System.out.println(solution.complexNumberMultiply("1+0i", "1+0i"));
        System.out.println(solution.complexNumberMultiply("78+-76i", "-86+72i"));
    }

}
