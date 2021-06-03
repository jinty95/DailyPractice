package cn.jinty.leetcode.function;

import cn.jinty.leetcode.linear.ListNode;
import cn.jinty.leetcode.tree.TreeNode;
import cn.jinty.leetcode.entity.Employee;

import java.util.*;

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

    /**
     * 875. 爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * 珂珂可以决定她吃香蕉的速度 K（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
     * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K为整数）。
     *
     * @param piles 香蕉数量
     * @param h 小时
     * @return 最小速度
     */
    public int minEatingSpeed(int[] piles, int h) {
        //二分查找：先确定速度范围，针对一个速度，计算吃完香蕉的时间，判断时间与h的关系，决定如何缩小速度区间
        //最小速度为1(时间最长)，最大速度为max(piles)(时间最短)
        int max = piles[0];
        for(int pile : piles) max = Math.max(max,pile);
        int left = 1, right = max;
        while(left<right){
            int mid = left + (right-left)/2;
            int hour = 0;
            for(int pile : piles) hour += (pile/mid + (pile%mid==0 ? 0 : 1));
            if(hour>h){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     *
     * @param root 二叉搜索树
     * @param low 小值
     * @param high 大值
     * @return 范围和
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if(root==null) return sum;
        sum += (root.val>=low) ? rangeSumBST(root.left,low,high) : 0;
        sum += (root.val>=low && root.val<=high) ? root.val : 0;
        sum += (root.val<=high) ? rangeSumBST(root.right,low,high) : 0;
        return sum;
    }

    /**
     * 633. 平方数之和
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
     *
     * @param c 非负整数(0 <= c <= 2^31 - 1)
     * @return 是否
     */
    public boolean judgeSquareSum(int c) {
        /*//枚举a^2的所有整数，直到超出c
        //在c范围内，b^2=c-a^2，对b^2开方求b，判断是否为整数
        if(c==0) return true;
        int i = 1;
        int a2 = i*i;
        while(a2<c){
            int b2 = c - a2;
            if(Math.sqrt(b2)%1==0){
                return true;
            }
            i++;
            a2 = i*i;
        }
        return a2==c;*/

        //上述方法超时

        //费马平方和定理：一个非负整数能够表示为两个整数的平方和，当且仅当该整数的所有形如4k+3的质因子的出现次数均为偶数次
        //例如：441=3*3*7*7，3出现2次，7出现2次，它们都出现偶数次，所以441可以表示为平方和，441=21^2+0^2
        int i=2;
        //i<=c为什么可以优化为i*i<=c？
        //因为i*i>c这种情况下，c一定是最后一个质数，如果c还可以继续分解，则i*i<=c一定成立
        //如果使用i<=c，则剩余最后一个质数，且该质数只有一个时，i需要从上一个质数遍历++到下一个质数，时间比较久
        while(i*i<=c){
            if(c%i==0){
                //能够整除，则统计i质因子的数量
                int count = 0;
                while(c%i==0){
                    count++;
                    c /= i;
                }
                //判断i是否形如4k+3，若是且数量为奇数，返回false
                if(i%4==3 && count%2!=0){
                    return false;
                }
            }else{
                i++;
            }
        }
        return c%4!=3;

    }

    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * @param grid 二维网格
     * @return 数量
     */
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    count++;
                    //将相邻陆地涂色
                    pantIsland(grid,i,j);
                }
            }
        }
        return count;
    }
    private void pantIsland(char[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length) return;
        if(grid[i][j]!='1') return;
        grid[i][j] = '2';
        pantIsland(grid,i-1,j);
        pantIsland(grid,i+1,j);
        pantIsland(grid,i,j-1);
        pantIsland(grid,i,j+1);
    }

    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * @param head 有序链表
     * @return 二叉搜索树
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return new TreeNode(head.val);
        //快慢指针寻找链表中点
        ListNode pre = head;
        ListNode low = head, fast = head;
        while(fast!=null){
            fast = fast.next;
            if(fast!=null){
                fast = fast.next;
                pre = low;
                low = low.next;
            }
        }
        //链表中点作为根节点，前后分别作为根节点的左右子
        TreeNode root = new TreeNode(low.val);
        root.right = sortedListToBST(low.next);
        if(pre!=low){
            pre.next = null;
            root.left = sortedListToBST(head);
        }
        return root;
    }

    /**
     * 973. 最接近原点的 K 个点
     * 有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * @param points 二维数组
     * @param k 正整数
     * @return k个离原点最近的点
     */
    public int[][] kClosest(int[][] points, int k) {
        if(points==null || points.length==0) return null;
        int[][] ans = new int[k][2];
        //排序
        Arrays.sort(points, Comparator.comparingInt(o -> o[0] * o[0] + o[1] * o[1]));
        //取前k个
        if (k >= 0) System.arraycopy(points, 0, ans, 0, k);
        return ans;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
     * 设计一个算法来计算你所能获取的最大利润。返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * @param prices 价格数组
     * @return 最大收益
     */
    public int maxProfit(int[] prices) {
        //最大收益
        int maxProfit = 0;
        if(prices==null || prices.length==0) return maxProfit;
        //最小价格，滑动更新
        int minPrice = prices[0];
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices 价格数组
     * @return 最大收益
     */
    public int maxProfit2(int[] prices) {
        //最大收益
        int maxProfit = 0;
        if(prices==null || prices.length==0) return maxProfit;
        //动态更新的最小及最大价格
        int minPrice = prices[0];
        int maxPrice = prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i-1]>prices[i]){
                //存在获利区间
                if(maxPrice>minPrice){
                    maxProfit += maxPrice - minPrice;
                }
                //价格下降
                minPrice = prices[i];
                maxPrice = prices[i];
            }else{
                //价格上升
                maxPrice = prices[i];
            }
        }
        //存在获利区间
        if(maxPrice>minPrice){
            maxProfit += maxPrice - minPrice;
        }
        return maxProfit;
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你都需要支付一次手续费。
     *
     * @param prices 价格数组
     * @param fee 手续费
     * @return 最大收益
     */
    public int maxProfit(int[] prices, int fee) {
        if(prices==null || prices.length<2) return 0;
        //动态规划 i表示第i天，j取0或者1，代表第i天不持有或持有股票，dp[i][j]表示对应情况下的最大收益
        int[][] dp = new int[prices.length][2];
        //第0天不持有，收益为0
        dp[0][0] = 0;
        //第0天持有，收益为-prices[0]
        dp[0][1] = -prices[0];
        for(int i=1;i<prices.length;i++){
            //第i天不持有：第i-1天不持有则今天保持，第i-1天持有则今天卖出，取其中的最大值
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            //第i天持有：第i-1天持有则今天保持，第i-1天不持有则今天买入，取其中的最大值
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 1、你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 2、卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     *
     * @param prices 价格数组
     * @return 最大收益
     */
    public int maxProfitWithFreeze(int[] prices) {
        if(prices==null || prices.length<2) return 0;
        //动态规划 i表示第i天，j取0、1、2，分别代表第i天不持有(非冷冻)、不持有(冷冻)或持有股票，dp[i][j]表示对应情况下的最大收益
        int[][] dp = new int[prices.length][3];
        //第0天不持有，收益为0
        dp[0][0] = 0;
        dp[0][1] = 0;
        //第0天持有，收益为-prices[0]
        dp[0][2] = -prices[0];
        for(int i=1;i<prices.length;i++){
            //第i天不持有(非冷冻)：第i-1天不持有(非冷冻、冷冻)，今天保持
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            //第i天不持有(冷冻)：第i-1天持有，今天卖出
            dp[i][1] = dp[i-1][2]+prices[i];
            //第i天持有：第i-1天持有则今天保持，第i-1天不持有(非冷冻)则今天买入
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][0]-prices[i]);
        }
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
    }

    /**
     * 403. 青蛙过河
     * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
     * 给你石子的位置列表 stones（按单元格序号升序表示），请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
     * 开始时，青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
     * 如果青蛙上一步跳跃了k个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。另请注意，青蛙只能向前方（终点的方向）跳跃。
     *
     * @param stones 石头位置
     * @return 能否在最后一步跳至最后一块石子上
     */
    public boolean canCross(int[] stones) {
        if(stones==null || stones.length==0) return false;
        if(stones.length==1) return true;
        //动态规划
        //对于每个石头，可以从之前的任意石头跳过来，上一步的跳跃单位有多种可能，收集所有可能情况，并推测下一步跳跃可选距离。
        //当前石头stones[i]->上一步跳跃单位[k1,k2,...]->下一步跳跃单位[k1-1,k1,k1+1,...]，可以用列表+集合的结构存储。
        List<Set<Integer>> dp = new ArrayList<>();
        //第一个石头，下一步跳跃单位只有1
        Set<Integer> dp0 = new HashSet<>();
        dp0.add(1);
        dp.add(dp0);
        //其余石头，下一步跳跃单位都由之前历史来推测
        for(int i=1;i<stones.length;i++){
            Set<Integer> dpi = new HashSet<>();
            for(int j=0;j<dp.size();j++){
                //从j跳到i的距离为dis，判断这个距离是否为j的下一步可选跳跃单位
                int dis = stones[i]-stones[j];
                if(dp.get(j).contains(dis)){
                    dpi.add(dis);
                    dpi.add(dis-1);
                    dpi.add(dis+1);
                }
            }
            dp.add(dpi);
        }
        return dp.get(dp.size()-1).size() != 0;
    }

    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * @param nums 数组
     * @return 只出现一次的数字
     */
    public int singleNumber(int[] nums) {

        /*//1、哈希表 时间复杂度O(N) 空间复杂度O(N)
        //统计数字的出现频率
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //找到出现一次的数字
        int ans = 0;
        for(int key : map.keySet()){
            if(map.get(key)==1){
                ans = key;
                break;
            }
        }
        return ans;*/

        //2、位运算 时间复杂度O(N) 空间复杂度O(1)
        //答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数。
        int ans = 0;
        int bit = 0;
        while(bit<32){
            int bitCount = 0;
            for(int num : nums){
                bitCount += (bit == 0 ? num&1 : (num>>>bit)&1);
            }
            ans |= (bit == 0 ? bitCount%3 : (bitCount%3)<<bit);
            bit++;
        }
        return ans;

    }

    /**
     * 690. 员工的重要性
     * 输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
     *
     * @param employees 员工列表
     * @param id 员工id
     * @return 员工和他所有下属的重要度之和
     */
    public int getImportance(List<Employee> employees, int id) {

        /*//时间复杂度O(N^2)
        int importance = 0;
        for(Employee employee : employees){
            if(employee.id == id){
                importance += employee.importance;
                if(employee.subordinates!=null && employee.subordinates.size()>0){
                    for(Integer subId : employee.subordinates){
                        importance += getImportance(employees, subId);
                    }
                }
                break;
            }
        }
        return importance;*/

        //时间复杂度O(N)
        Map<Integer,Employee> map = new HashMap<>();
        for(Employee employee : employees){
            map.put(employee.id,employee);
        }
        return getImportance(map,id);

    }
    //深度优先遍历
    private int getImportance(Map<Integer,Employee> map, int id){
        int importance = 0;
        Employee employee = map.get(id);
        importance += employee.importance;
        if(employee.subordinates!=null){
            for(Integer subId : employee.subordinates){
                importance += getImportance(map,subId);
            }
        }
        return importance;
    }

    /**
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * @param head 链表
     * @param k 正整数
     * @return 翻转后的链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k<2) return head;
        //顺序收集节点
        List<ListNode> list = new ArrayList<>();
        ListNode tmp = head;
        while(tmp!=null){
            list.add(tmp);
            tmp = tmp.next;
        }
        //k个一组反转
        ListNode newHead = null;
        ListNode newTail = null;
        for(int i=0;i<list.size();i++){
            //不够k个
            if(list.size()-i<k){
                if(newHead==null) return head;
                newTail.next = list.get(i);
                return newHead;
            }
            //小组内部反转
            int num = k;
            while(num>1){
                list.get(i+1).next = list.get(i);
                i++;
                num--;
            }
            if(newHead==null){
                //确定新的头节点
                newHead = list.get(i);
                newTail = list.get(0);
            }else{
                //相邻小组的头尾连接
                newTail.next = list.get(i);
                newTail = list.get(i-(k-num));
            }
            //尾节点next置空，否则会产生环
            newTail.next = null;
        }
        return newHead;
    }

    /**
     * 554. 砖墙
     * 你现在要画一条自顶向下的、穿过最少砖块的垂线。
     * 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。
     * 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
     *
     * @param wall 一堵矩形的、由 n 行砖块组成的砖墙
     * @return 穿过的砖块数量的最小值
     */
    public int leastBricks(List<List<Integer>> wall) {
        /*//特殊情况
        if(wall==null || wall.size()==0 || wall.get(0)==null) return 0;
        //最多时每行都穿过一个砖
        int min = wall.size();
        //墙的总长度
        int len = 0;
        //所有的前缀和
        Set<Integer> preSumSet = new HashSet<>();
        //每行的前缀和
        List<Set<Integer>> preSumList = new ArrayList<>();
        for(List<Integer> row : wall){
            int rowLen = 0;
            Set<Integer> preSum = new HashSet<>();
            for(Integer brickLen : row){
                rowLen += brickLen;
                preSum.add(rowLen);
                preSumSet.add(rowLen);
            }
            if(len==0) len = rowLen;
            preSumList.add(preSum);
        }
        //在某个位置画下垂线，如果能在前缀和中找到，说明从缝隙经过
        for(Integer i : preSumSet){
            if(i==len) continue;
            int count = 0;
            for(Set<Integer> preSum : preSumList){
                if(!preSum.contains(i)) count++;
            }
            min = Math.min(min,count);
        }
        return min;*/

        //遍历砖墙的每一行，从左到右地扫描每一块砖，将除了最右侧的砖块以外的其他砖块的右边缘到砖墙的左边缘的距离加入到哈希表中。
        //遍历该哈希表，找到出现次数最多的砖块边缘，垂线从这里经过时穿过的砖块最少。
        //最少砖块数量 = 砖墙的高度 - 最大砖块边缘数量。
        int min = wall.size();
        Map<Integer,Integer> map = new HashMap<>();
        for(List<Integer> row : wall){
            int sum = 0;
            for(int i=0;i<row.size()-1;i++){
                sum += row.get(i);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int maxCount = 0;
        for(Integer key : map.keySet()){
            maxCount = Math.max(maxCount,map.get(key));
        }
        return min-maxCount;

    }

    /**
     * 7. 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1]，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * @param x 整数
     * @return 反转结果
     */
    public int reverse(int x) {

        /*//使用字符串收集反转后的结果，再解析为数字
        StringBuilder sb = new StringBuilder();
        if(x<0){
            x = -x;
            sb.append('-');
        }
        while(x!=0){
            sb.append(x%10);
            x /= 10;
        }
        try{
            return Integer.parseInt(sb.toString());
        }catch(Exception e){
            return 0;
        }*/

        //只使用数学方法，不借助字符串
        //弹出 num = x % 10; x /= 10;
        //压入 res = res * 10 + num;
        int res = 0;
        while (x != 0) {
            int tmp = res * 10 + x % 10;
            if (tmp / 10 != res) { // 溢出!!!
                return 0;
            }
            res = tmp;
            x /= 10;
        }
        return res;

    }

    /**
     * 1473. 粉刷房子 III
     * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
     * 我们将连续相同颜色尽可能多的房子称为一个街区。请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
     *
     * @param houses houses[i]是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
     * @param cost cost[i][j]是将第 i 个房子涂成颜色 j+1 的花费。
     * @param m 房子数量
     * @param n 颜色种类
     * @param target 目标街区数量
     * @return 最小花费
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        //动态规划 dp[i][j][k]表示有i+1个房子，第i+1个房子被染成j+1颜色，且有k+1个街区时的最小花费
        int[][][] dp = new int[m][n][target];
        //第1个房子
        if(houses[0]==0){
            //未染色
            for(int j=0;j<n;j++){
                for(int k=0;k<target;k++){
                    dp[0][j][k] = (k==0 ? cost[0][j] : -1);
                }
            }
        }else{
            //已染色
            for(int j=0;j<n;j++){
                for(int k=0;k<target;k++){
                    dp[0][j][k] = (j+1==houses[0] && k==0 ? 0 : -1);
                }
            }
        }
        //第2个房子到第m个房子
        for(int i=1;i<m;i++){
            if(houses[i]==0){
                //未染色
                for(int j=0;j<n;j++){
                    for(int k=0;k<target;k++){
                        dp[i][j][k] = Integer.MAX_VALUE;
                        for(int color=0;color<n;color++){
                            if(color==j){
                                //与上一个房子同色
                                if(dp[i-1][color][k]!=-1){
                                    //总花费 = 之前的花费 + 当前房子染j+1颜色的花费
                                    dp[i][j][k] = Math.min(dp[i][j][k],dp[i-1][color][k]+cost[i][j]);
                                }
                            } else {
                                //与上一个房子不同色
                                if(k>0 && dp[i-1][color][k-1]!=-1){
                                    //总花费 = 之前的花费 + 当前房子染j+1颜色的花费
                                    dp[i][j][k] = Math.min(dp[i][j][k],dp[i-1][color][k-1]+cost[i][j]);
                                }
                            }
                        }
                        dp[i][j][k] = dp[i][j][k] == Integer.MAX_VALUE ? -1 : dp[i][j][k];
                    }
                }
            }else{
                //已染色
                for(int j=0;j<n;j++){
                    for(int k=0;k<target;k++){
                        if(j+1==houses[i]){
                            dp[i][j][k] = Integer.MAX_VALUE;
                            //只能染j+1的颜色
                            for(int color=0;color<n;color++){
                                if(color==j){
                                    //与上一个房子同色
                                    if(dp[i-1][color][k]!=-1){
                                        //总花费 = 之前的花费
                                        dp[i][j][k] = Math.min(dp[i][j][k],dp[i-1][color][k]);
                                    }
                                }else{
                                    //与上一个房子不同色
                                    if(k>0 && dp[i-1][color][k-1]!=-1){
                                        //总花费 = 之前的花费
                                        dp[i][j][k] = Math.min(dp[i][j][k],dp[i-1][color][k-1]);
                                    }
                                }
                            }
                            dp[i][j][k] = dp[i][j][k] == Integer.MAX_VALUE ? -1 : dp[i][j][k];
                        }else{
                            //其余颜色都不能染
                            dp[i][j][k] = -1;
                        }
                    }
                }
            }
        }
        //dp[m-1][j][target-1]中找最小值
        int minCost = Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            if(dp[m-1][j][target-1]!=-1){
                minCost = Math.min(minCost,dp[m-1][j][target-1]);
            }
        }
        return minCost==Integer.MAX_VALUE ? -1 : minCost;
    }

    /**
     * 740. 删除并获得点数
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
     * 之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * @param nums 正整数数组 (1 <= nums[i] <= 10^4)
     * @return 最大点数
     */
    public int deleteAndEarn(int[] nums) {
        /*//收集数字及其数量，并按照数字排序
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for(int num : nums){
            treeMap.put(num,treeMap.getOrDefault(num,0)+1);
        }
        //动态规划 dp[i]表示从0-i的数组中操作时获得的最大点数
        int[] dp = new int[treeMap.size()];
        int i = 0;
        Integer pre = null;
        for(Integer key : treeMap.keySet()){
            if(i==0){
                dp[i] = treeMap.get(key) * key;
            } else {
                if(key-pre>1){
                    dp[i] = dp[i-1] + treeMap.get(key) * key;
                }else{
                    if(i==1) dp[i] = Math.max(dp[i-1],treeMap.get(key) * key);
                    else dp[i] = Math.max(dp[i-1],dp[i-2]+treeMap.get(key) * key);
                }
            }
            pre = key;
            i++;
        }
        return dp[treeMap.size()-1];*/

        //求最大值
        int maxVal = 0;
        for(int num : nums){
            maxVal = Math.max(maxVal,num);
        }
        //sum[i]表示数字i对应的总和，没有这个数字时，总和为0
        int[] sum = new int[maxVal+1];
        for(int num : nums){
            sum[num] += num;
        }
        if(maxVal==1) return sum[1];
        //基于sum数组做动态规划解
        int first = sum[1]; int second = Math.max(sum[1],sum[2]);
        for(int i=3;i<sum.length;i++){
            int temp = second;
            second = Math.max(first+sum[i],second);
            first = temp;
        }
        return second;

    }

    /**
     * 1833. 雪糕的最大数量
     * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
     * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
     * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
     * 注意：Tony 可以按任意顺序购买雪糕。
     *
     * @param costs 雪糕价格数组
     * @param coins 现金量
     * @return 最大雪糕数量
     */
    public int maxIceCream(int[] costs, int coins) {

        /*//1、动态规划
        //dp[i][j]代表在0-i的雪糕中用j元能够买到的最大数量
        int[][] dp = new int[costs.length][coins+1];
        //j==0时，什么都买不到
        //i==0时，满足costs[0]元能够买一个
        for(int j=1;j<=coins;j++){
            if(j==costs[0]){
                while(j<=coins) dp[0][j++] = 1;
            }
        }
        //其余情况
        for(int i=1;i<costs.length;i++){
            for(int j=1;j<=coins;j++){
                //不买costs[i]的雪糕
                dp[i][j] = dp[i-1][j];
                //买costs[i]的雪糕
                if(j>=costs[i]) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-costs[i]]+1);
            }
        }
        return dp[costs.length-1][coins];*/

        //上述做法空间复杂度较高，超出空间限制

        //2、贪心算法
        //从最便宜的开始买，直到钱不足
        Arrays.sort(costs);
        int count = 0;
        for(int i=0;i<costs.length;i++){
            if(coins>=costs[i]){
                count++;
                coins -= costs[i];
            }else{
                break;
            }
        }
        return count;

    }

    /**
     * 978. 最长湍流子数组
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     *
     * 注意：是子数组而不是子序列
     *
     * @param arr 数组
     * @return 最大湍流子数组的长度
     */
    public int maxTurbulenceSize(int[] arr) {
        //最大长度
        int maxSize = 1;
        //临时长度
        int tmpSize = 1;
        //下一个符号是否应为大于
        boolean gt = false;
        //遍历原数组
        for(int i=1;i<arr.length;i++){
            if(tmpSize==1){
                //子数组只有一个元素时，下一个符号可大于或小于，等于则忽略
                if(arr[i-1]<arr[i]){
                    tmpSize++;
                    gt = true;
                }else if(arr[i-1]>arr[i]){
                    tmpSize++;
                    gt = false;
                }
            }else{
                //子数组大于1个元素时，下一个符号由gt指定
                if(gt){
                    if(arr[i-1]>arr[i]){
                        //符合gt，子数组加1，gt反转
                        tmpSize++;
                        gt = false;
                    }else if(arr[i-1]<arr[i]){
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 2;
                        gt = true;
                    }else{
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 1;
                    }
                }else{
                    if(arr[i-1]<arr[i]){
                        //符合gt，子数组加1，gt反转
                        tmpSize++;
                        gt = true;
                    }else if(arr[i-1]>arr[i]){
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 2;
                        gt = false;
                    }else{
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 1;
                    }
                }
            }
        }
        maxSize = Math.max(maxSize,tmpSize);
        return maxSize;
    }

}
