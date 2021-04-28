package leetcode;

import cn.jinty.leetcode.ListNode;
import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.function.Fun4;
import org.junit.Test;

/**
 * LeetCode算法题 - 单元测试
 *
 * @author jinty
 * @date 2021/4/25
 **/
public class Test4 {

    private final Fun4 fun4 = new Fun4();

    @Test
    public void testIncreasingBST(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(TreeNode.serialize(root));
        TreeNode newHead = fun4.increasingBST(root);
        System.out.println(TreeNode.serialize(newHead));
    }

    @Test
    public void testCoinChange(){
        int[] coins = {186,419,83,408};
        int amount1 = 6249;
        int amount3 = 1;
        int amount4 = 0;
        System.out.println(fun4.coinChange(coins,amount1));
        System.out.println(fun4.coinChange(coins,amount3));
        System.out.println(fun4.coinChange(coins,amount4));
    }

    @Test
    public void testChange(){
        int[] coins = {1,2,5,10};
        int amount1 = 20;
        System.out.println(fun4.change(amount1,coins));
    }

    @Test
    public void testShipWithinDays(){
        int[] weights = {
                361,321,186,186,67,283,36,471,304,218,60,78,149,166,282,
                384,61,242,426,275,236,221,27,261,487,90,468,19,453,241
        };
        int D = 15;
        System.out.println(fun4.shipWithinDays(weights,D));
    }

    @Test
    public void testMinEatingSpeed(){
        int[] piles = {4,6,8,9,11,5};
        int h = 12;
        System.out.println(fun4.minEatingSpeed(piles,h));
    }

    @Test
    public void testRangeSumBST(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(9);
        System.out.println(fun4.rangeSumBST(root,3,9));
        System.out.println(fun4.rangeSumBST(root,3,8));
    }

    @Test
    public void testJudgeSquareSum(){
        System.out.println(fun4.judgeSquareSum(1));
        System.out.println(fun4.judgeSquareSum(4));
        System.out.println(fun4.judgeSquareSum(85));
        System.out.println(fun4.judgeSquareSum(12132321));
        System.out.println(fun4.judgeSquareSum(Integer.MAX_VALUE));
    }

    @Test
    public void testNumIslands(){
        char[][] grid = {
                {'1','1','0'},
                {'0','0','0'},
                {'1','1','1'}
        };
        System.out.println(fun4.numIslands(grid));
    }

    @Test
    public void testSortedListToBST(){
        ListNode head = new ListNode(3);
        ListNode next = new ListNode(4);
        head.next = next;
        next.next = new ListNode(5);
        System.out.println(fun4.sortedListToBST(head).toString());
    }

}
