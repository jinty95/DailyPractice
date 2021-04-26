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

    /**
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     *
     * @param amount 目标金额
     * @param coins 不同面额的硬币
     * @return 组合数
     */
    public int change(int amount, int[] coins) {

        //动态规划：dp[i][j]表示用coins[0..i]换j的组合数
        int[][] dp = new int[coins.length][amount+1];
        //第0列：不需要硬币，故为1种
        for(int i=0;i<coins.length;i++){
            dp[i][0] = 1;
        }
        //第0行：只用coins[0]，合成j只有能或不能
        for(int i=1;i<=amount;i++){
            if(i==coins[0]) dp[0][i] = 1;
            else if(i>coins[0]) dp[0][i] = (dp[0][i-coins[0]]==1 ? 1 : 0);
        }
        //其余行列
        for(int i=1;i<coins.length;i++){
            for(int j=1;j<=amount;j++){
                dp[i][j] = dp[i-1][j] + (j-coins[i]>=0 ? dp[i][j-coins[i]] : 0);
            }
        }
        return dp[coins.length-1][amount];

    }

    /**
     * 1011. 在D天内送达包裹的能力
     * 传送带上的包裹必须在D天内从一个港口运送到另一个港口。传送带上的第i个包裹的重量为weights[i]。
     * 每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     * 返回能在D天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     * @param weights 包裹重量数组
     * @param D 天数
     * @return 最低运载能力
     */
    public int shipWithinDays(int[] weights, int D) {

        //问题分析：把weights分成D份，怎么划分可以使得子数组和的最大值最小

        /*//天数比数组长度长，那么数组可以拆分为一个一个的元素，取其中最大的一个
        if(D>=weights.length){
            int max = weights[0];
            for(int weight : weights) max = Math.max(max,weight);
            return max;
        }
        //计算前缀和
        int[] sum = new int[weights.length];
        sum[0] = weights[0];
        for(int i=1;i<weights.length;i++){
            sum[i] = sum[i-1] + weights[i];
        }*/
        /*//1、暴力递归
        return shipWithinDays(weights,D,0,sum);*/

        //上述递归存在重复计算，时间复杂度较高，计算超时
        //重复计算：第一天[0]，第二天[1,2]，递归剩余，第一天[0,1]，第二天[2]，递归剩余

        /*//2、记忆搜素
        int[][] memory = new int[D+1][weights.length];
        return shipWithinDays(weights,D,0,sum,memory);*/

        //记忆搜索的递归深度太大导致内存溢出
        //记忆搜索中存在递进过程，可以优化为动态规划，避免函数递归

        /*//3、动态规划 dp[i][j]表示在i天内运送[0...j]的货物的最低运载能力
        int[][] dp = new int[D][weights.length];
        //只有1天
        for(int j=0;j<weights.length;j++){
            dp[0][j] = sum[j];
        }
        //大于1天
        for(int i=1;i<D;i++){
            for(int j=0;j<weights.length;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=0;k<j;k++){
                    //前N天运送[0...k]的货物，当前天运送剩余货物
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][k],sum[j]-sum[k]));
                }
            }
        }
        return dp[D-1][weights.length-1];*/

        //上述动态规划的时间复杂度为O(DN^2)，仍然超时

        //4、二分查找
        //首先确定运载能力x的范围[max(weights),sum(weights)]，在这个范围内做二分查找
        //对于一个运载能力x，遍历weights，基于贪心算法计算这种情况所需要的最小天数
        //如果大于D，说明在D天内完成需要更大的运力，否则，在D天内完成只需要更小的运力
        int max = weights[0], sum = 0;
        for(int weight : weights){
            max = Math.max(max,weight);
            sum += weight;
        }
        int left = max, right = sum;
        while(left<right){
            int mid = left + (right-left)/2;
            int day = 1;
            int temp = 0;
            for (int weight : weights) {
                temp += weight;
                if (temp > mid) {
                    day++;
                    temp = weight;
                }
            }
            if(day>D){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;

    }
    //递归函数
    private int shipWithinDays(int[] weights, int D, int idx, int[] sum){
        //数组角标越界，返回0
        if(idx>=weights.length) return 0;
        //记录最大值
        int max = Integer.MAX_VALUE;
        //只剩一天，那么后面的元素只能作为一个子数组
        if(D==1){
            return sum[weights.length-1] - (idx==0 ? 0 : sum[idx-1]);
        }
        //枚举第一天的包裹数，天数减一，剩余包裹递归求解
        for(int i=idx;i<weights.length;i++){
            int curMax = Math.max(
                    (i==idx ? weights[i] : (idx==0 ? sum[i] : sum[i]-sum[idx-1])),
                    shipWithinDays(weights,D-1,i+1,sum)
            );
            max = Math.min(max,curMax);
        }
        return max;
    }
    //递归函数+记忆表
    private int shipWithinDays(int[] weights, int D, int idx, int[] sum, int[][] memory){
        //数组角标越界，返回0
        if(idx>=weights.length) return 0;
        //查找记忆表，命中则直接返回结果
        if(memory[D][idx]!=0) return memory[D][idx];
        //记录最大值
        int max = Integer.MAX_VALUE;
        //只剩一天，那么后面的元素只能作为一个子数组
        if(D==1){
            max = sum[weights.length-1] - (idx==0 ? 0 : sum[idx-1]);
            memory[D][idx] = max;
            return max;
        }
        //枚举第一天的包裹数，天数减一，剩余包裹递归求解
        for(int i=idx;i<weights.length;i++){
            int curMax = Math.max(
                    (i==idx ? weights[i] : (idx==0 ? sum[i] : sum[i]-sum[idx-1])),
                    shipWithinDays(weights,D-1,i+1,sum,memory)
            );
            max = Math.min(max,curMax);
        }
        memory[D][idx] = max;
        return max;
    }

}
