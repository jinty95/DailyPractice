package test.cn.jinty.leetcode.problem.hard;

import cn.jinty.leetcode.problem.hard.Solution1;
import cn.jinty.struct.tree.TreeNode;
import org.junit.Test;

/**
 * LeetCode - 困难题 - 测试
 *
 * @author jinty
 * @date 2021/7/31
 **/
public class SolutionTest1 {

    private final static Solution1 solution = new Solution1();

    @Test
    public void testVerticalTraversal(){
        TreeNode root = TreeNode.deserialize(
                "[1,2,3,4,6,5,7,null,null,null,null,null,null,null,null]"
        );
        System.out.println(solution.verticalTraversal(root));
    }

    @Test
    public void testShortestPathLength(){
        System.out.println(solution.shortestPathLength(
                new int[][]{{1,2,3},{0},{0},{0}}
        ));
    }

    @Test
    public void testNumberOfArithmeticSlices(){
        System.out.println(solution.numberOfArithmeticSlices(new int[]{1,2,3}));
        System.out.println(solution.numberOfArithmeticSlices(new int[]{2,4,6,8,10}));
    }

    @Test
    public void testCheckRecord(){
        System.out.println(solution.checkRecord(1));
        System.out.println(solution.checkRecord(2));
        System.out.println(solution.checkRecord(10101));
    }

}