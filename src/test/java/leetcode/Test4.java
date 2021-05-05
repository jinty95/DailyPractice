package leetcode;

import cn.jinty.leetcode.ListNode;
import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.function.Fun4;
import cn.jinty.utils.ArrayUtil;
import cn.jinty.utils.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testKClosest(){
        int[][] points = {
                {1,3},{2,-2}
        };
        ArrayUtil.print2DArray(fun4.kClosest(points,1));
    }

    @Test
    public void testMaxProfit(){
        int[] prices = {1,9,3,10,9,88,100};
        System.out.println(fun4.maxProfit(prices));
        System.out.println(fun4.maxProfit2(prices));
        System.out.println(fun4.maxProfit(prices,7));
        System.out.println(fun4.maxProfitWithFreeze(prices));
    }

    @Test
    public void testCanCross(){
        int[] stones1 = {1,9,10,20};
        int[] stones2 = {1,2,3,5,6,8,12,17};
        System.out.println(fun4.canCross(stones1));
        System.out.println(fun4.canCross(stones2));
    }

    @Test
    public void testSingleNumber(){
        int[] nums = {0,9,0,0,8,3,8,8,9,9};
        System.out.println(fun4.singleNumber(nums));
    }

    @Test
    public void testReverseKGroup(){
        ListNode head = ListNodeUtil.fromArray(
                new int[]{1,2,3,4,5,6,7,8,9}
        );
        System.out.println(head);
        System.out.println(fun4.reverseKGroup(head,9));
    }

    @Test
    public void testLeastBricks(){
        List<List<Integer>> wall = new ArrayList<>();
        List<Integer> row1 = Arrays.asList(6);
        List<Integer> row2 = Arrays.asList(2,3);
        List<Integer> row3 = Arrays.asList(1,5);
        wall.add(row1);
        wall.add(row2);
        wall.add(row3);
        System.out.println(fun4.leastBricks(wall));
    }

    @Test
    public void testReverse(){
        System.out.println(fun4.reverse(123));
        System.out.println(fun4.reverse(-123));
        System.out.println(fun4.reverse(1534236469));
    }

    @Test
    public void testMinCost(){
        int[] houses = {0,0,0,0,0};
        int[][] cost = {
                {1,10},{10,1},{10,1},{1,10},{5,1}
        };
        System.out.println(fun4.minCost(houses,cost,5,2,3));
    }

    @Test
    public void testDeleteAndEarn(){
        int[] nums1 = new int[]{4,10,10,8,1,4,10,9,7,6};
        int[] nums2 = new int[]{1,1,1,1,1,1};
        System.out.println(fun4.deleteAndEarn(nums1));
        System.out.println(fun4.deleteAndEarn(nums2));
    }

    @Test
    public void testMaxIceCream(){
        int[] costs = {1,2,3,4,1};
        System.out.println(fun4.maxIceCream(costs,7));
    }

}
