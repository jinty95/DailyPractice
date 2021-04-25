package leetcode;

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

}
