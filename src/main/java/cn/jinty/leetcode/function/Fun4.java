package cn.jinty.leetcode.function;

import cn.jinty.leetcode.TreeNode;

import java.util.Arrays;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/4/25
 **/
public class Fun4 {

    /**
     * 897. 递增顺序搜索树
     * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
     * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     *
     * @param root 二叉搜索树
     * @return 递增搜索树
     */
    public TreeNode increasingBST(TreeNode root) {
        if(root==null) return null;
        inOrder(root);
        return headNode;
    }
    private TreeNode preNode;
    private TreeNode headNode;
    private void inOrder(TreeNode root){
        if(root==null) return;
        //递归左子
        inOrder(root.left);
        //业务处理
        if(preNode==null){
            preNode = root;
            headNode = root;
        }else{
            root.left = null;
            preNode.right = root;
            preNode = root;
        }
        //递归右子
        inOrder(root.right);
    }

    /**
     * 322. 零钱兑换
     * 计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
     * 每种硬币的数量是无限的。
     *
     * @param coins 不同面额的硬币
     * @param amount 目标金额
     * @return 最少硬币数
     */
    public int coinChange(int[] coins, int amount) {

        /*//贪心算法：从面值最大的硬币开始找钱
        Arrays.sort(coins);
        int count = 0;
        int idx = coins.length-1;
        while(amount>0 && idx>=0){
            if(amount>=coins[idx]){
                count++;
                amount -= coins[idx];
            }else{
                idx--;
            }
        }
        return amount==0 ? count : -1;*/

        //贪心算法在某些案例下无法通过
        //例如：[80,90] 160，上述算法得-1，而真实解为2
        //因为贪心拿了大钱，导致后面的钱找不开

        //动态规划 dp[i][j]表示用coins[0..i]换j的最少硬币数
        int[][] dp = new int[coins.length][amount+1];
        //第0列：amount为0，不需要硬币
        //第0行：每凑够一个coins[0]，硬币加一
        for(int i=1;i<=amount;i++){
            dp[0][i] = -1;
            if(i==coins[0]) dp[0][i] = 1;
            else if(i>coins[0]) dp[0][i] = (dp[0][i-coins[0]] == -1 ? -1 : dp[0][i-coins[0]]+1);
        }
        //其余行列
        for(int i=1;i<coins.length;i++){
            for(int j=1;j<=amount;j++){
                dp[i][j] = -1;
                if(dp[i-1][j] != -1){
                    dp[i][j] = dp[i-1][j];
                }
                if(j-coins[i]>=0 && dp[i][j-coins[i]] != -1){
                    dp[i][j] = (dp[i][j]==-1 ? (dp[i][j-coins[i]]+1) : Math.min(dp[i][j],dp[i][j-coins[i]]+1));
                }
            }
        }
        return dp[coins.length-1][amount];

    }

}
