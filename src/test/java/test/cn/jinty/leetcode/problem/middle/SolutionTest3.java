package test.cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.problem.middle.Solution3;
import cn.jinty.struct.tree.TreeNode;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void testPlatesBetweenCandles() {
        System.out.println(Arrays.toString(solution.platesBetweenCandles(
                "***|**|*****|**||**|*",
                new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
    }

    @Test
    public void testRob() {
        TreeNode root = TreeNode.deserialize("[3,2,3,null,3,null,1,null,null,null,null]");
        System.out.println(solution.rob(root));
        root = TreeNode.deserialize("[3,4,5,1,3,null,1,null,null,null,null,null,null]");
        System.out.println(solution.rob(root));
    }

    @Test
    public void testDecodeString() {
        System.out.println(solution.decodeString("victory"));
        System.out.println(solution.decodeString("3[a]2[bc]"));
        System.out.println(solution.decodeString("3[a2[c]]"));
    }

}
