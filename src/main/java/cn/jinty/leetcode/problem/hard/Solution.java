package cn.jinty.leetcode.problem.hard;

import cn.jinty.struct.linear.ListNode;
import cn.jinty.struct.tree.IntTrie;

import java.util.*;

/**
 * LeetCode - 困难题
 *
 * @author jinty
 * @date 2021/6/10
 **/
public class Solution {

    /**
     * 354. 俄罗斯套娃信封问题
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * 注意：不允许旋转信封。
     *
     * @param envelopes 信封数组
     * @return 套娃的最大深度
     */
    public int maxEnvelopes(int[][] envelopes) {
        //动态规划
        int deepH = 0;
        //按宽度升序，如果宽度相同，高度倒序(目的是破坏这段宽度相同序列的高度递增)
        Arrays.sort(envelopes, ((o1, o2) -> o1[0]!=o2[0] ? o1[0]-o2[0] : o2[1]-o1[1]));
        //宽度有序后，求高度数组的最长递增子序列
        int[] dpH = new int[envelopes.length];
        for(int i=0; i<envelopes.length; i++){
            for(int j=0; j<i; j++){
                if(envelopes[j][1] < envelopes[i][1]){
                    dpH[i] = Math.max(dpH[j]+1,dpH[i]);
                }
            }
            if(dpH[i]==0) dpH[i] = 1;
            deepH = Math.max(deepH,dpH[i]);
        }
        return deepH;
    }

    /**
     * 42. 接雨水
     *
     * @param height 墙高度数组
     * @return 雨水量
     */
    public int trap(int[] height) {

        //突破口：每个位置的雨水量 = 两边最大高度中较小的一个 - 当前高度
        int sum = 0;

        /*//1、暴力破解：对于每一个点，都向左右寻两边的最大高度
        for(int i=0;i<height.length;i++){
            int left = 0, right = 0;
            for(int l=0;l<i;l++){
                left = Math.max(left,height[l]);
            }
            for(int r=i+1;r<height.length;r++){
                right = Math.max(right,height[r]);
            }
            int shorter = Math.min(left,right);
            if(shorter>height[i]) sum += shorter-height[i];
        }*/

        /*//2、动态编程：向右遍历求每个点的左边最大高度，向左遍历求每个点的右边最大高度
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int temp = 0;
        for(int i=0;i<height.length;i++){
            if(height[i]>temp) temp = height[i];
            maxLeft[i] = temp;
        }
        temp = 0;
        for(int i=height.length-1;i>=0;i--){
            if(height[i]>temp) temp = height[i];
            maxRight[i] = temp;
        }
        for(int i=0;i<height.length;i++){
            sum += (Math.min(maxLeft[i],maxRight[i]) - height[i]);
        }*/

        //3、单调递增栈：当前点小于等于栈顶，则入栈(索引)，当前点大于栈顶，说明栈顶可以在当前点及栈中的前一个点之间蓄水
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<height.length;i++){
            if(stack.isEmpty()) stack.push(i);
            else{
                if(height[stack.peek()]>=height[i]){
                    stack.push(i);
                }else{
                    Integer cur = stack.pop();
                    if(!stack.isEmpty()){
                        Integer left = stack.peek();
                        sum += (Math.min(height[left],height[i])-height[cur]) * (i-left-1);
                    }
                    i--;
                }
            }
        }
        return sum;

    }

    /**
     * 115. 不同的子序列
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
     * （例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * @param s 字符串 (0 <= s.length <= 1000)
     * @param t 字符串 (0 <= t.length <= 1000)
     * @return s中有多少种等于t的子序列
     */
    public int numDistinct(String s, String t) {
        //动态规划：时间复杂度O(MN)
        if(s.length()<t.length()) return 0;
        //dp[j]表示从s中有多少种t[0...j]的子序列
        int[] dp = new int[t.length()];
        //s[0...i]，i递增
        for(int i=0; i<s.length(); i++){
            //t[0...j]，j递减，由于dp是滚动更新的数组，若j递增会丢失上一层的数据
            for(int j=t.length()-1; j>=0; j--){
                //尾部匹配，s[i]匹配t[j]
                if(s.charAt(i)==t.charAt(j)){
                    //dp[j-1]是s[0...i-1]中t[0...j-1]的个数
                    dp[j] += (j==0? 1 : dp[j-1]);
                }
            }
        }
        return dp[t.length()-1];
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     *
     * @param nums 数组
     * @param k 窗口大小
     * @return 每个窗内部的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        /*if(k<2) return nums;
        int[] ans = new int[nums.length-k+1];
        //记录每个窗口的最大值及其位置
        int max = Integer.MIN_VALUE;
        int idx = -1;
        //left表示窗口的左边界
        for(int left=0;left<=nums.length-k;left++){
            //right表示窗口的右边界
            int right = left+k-1;
            if(idx>=left){
                //最大值在窗口内
                if(nums[right]>=max){
                    max = nums[right];
                    idx = right;
                }
            }else{
                //最大值不在窗口内
                max = nums[left];
                for(int i=left+1;i<=right;i++){
                    if(nums[i]>=max){
                        max = nums[i];
                        idx = i;
                    }
                }
            }
            ans[left] = max;
        }
        return ans;*/

        //使用单调队列(从队头->队尾递减)
        if(k<2) return nums;
        int[] ans = new int[nums.length-k+1];
        //队列存放索引
        Deque<Integer> queue = new LinkedList<>();
        //left为窗口左边界，right为窗口右边界
        for(int left=0;left<=nums.length-k;left++){
            int right = left+k-1;
            if(queue.isEmpty()){
                //初始化队列
                for(int i=left;i<=right;i++){
                    while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                        queue.pollLast();
                    }
                    queue.offerLast(i);
                }
            }else{
                //窗口滑动时动态更新队列
                //队头不在窗口内则移除队头
                if(queue.peekFirst()==left-1) queue.pollFirst();
                //维持队列单调性
                while(!queue.isEmpty() && nums[queue.peekLast()]<nums[right]){
                    queue.pollLast();
                }
                queue.offerLast(right);
            }
            ans[left] = nums[queue.peekFirst()];
        }
        return ans;

    }

    /**
     * 233. 数字 1 的个数
     * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
     *
     * @param n 整数 (0 <= n <= 2 * 10^9)
     * @return 1的个数
     */
    public int countDigitOne(int n) {
        //个位1：每10个数有1个，10个数里面第2个。
        //十位1：每100个数有10个，100个数里面第11个-第20个。
        //百位1：每1000个数有100个，1000个里面第101个-第200个。
        //故n具有m位1(设k=10^(m-1))的数量为：n/(k*10)*k+min(max(n%(k*10)-i+1,0),k)
        long count = 0;
        for(long i=1;i<=n;i*=10){
            long division = i * 10;
            count += (n/division*i);
            count += Math.min(Math.max(n%division-i+1,0),i);
        }
        return (int)count;
    }

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     *
     * @param nums 旋转排序数组(存在重复值)(例如：4,5,6,0,0,1,1,2,3)
     * @return 最小值
     */
    public int findMin(int[] nums) {
        if(nums == null || nums.length==0) return -1;
        //定义双指针
        int left = 0, right = nums.length-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid]>nums[right]){
                //中间值大于右边值，说明最小值在右区间，且不可能是当前中间值
                left = mid+1;
            }else if(nums[mid]<nums[right]){
                //中间值小于右边值，说明最小值在左区间，且可能为当前中间值
                right = mid;
            }else{
                //中间值等于右边值，说明可以把右边值去除，不会影响最小值判断
                //在数组仅有一个值的情况下，导致时间复杂度从O(logN)退化为O(N)
                right--;
            }
        }
        return nums[left];
    }

    /**
     * 87. 扰乱字符串
     * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
     * 如果字符串的长度为 1 ，算法停止
     * 如果字符串的长度 > 1 ，执行下述步骤：
     * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
     * 随机决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
     * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
     *
     * @param s1 字符串1(小写)
     * @param s2 字符串2(小写)
     * @return 是否互为扰乱字符串
     */
    public boolean isScramble(String s1, String s2) {

        /*//长度不同
        if(s1.length()!=s2.length()) return false;
        //值相同
        if(s1.equals(s2)) return true;

        //字符种类数量是否一致
        int[] map = new int[26];
        for(int i=0;i<s1.length();i++){
            map[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            int idx = s2.charAt(i)-'a';
            if(map[idx]==0) return false;
            map[idx]--;
        }

        //s = x + y 或者 s = y + x
        //如果s1、s2互扰，那么在s1中一定存在一个点将s1分为s11,s12，在s2中一定存在一个点将s2分为s21,s22
        //使得s11、s21互扰且s12、s22互扰，或者s11、s22互扰且s12、s21互扰
        for(int i=1;i<s1.length();i++){
            String s11 = s1.substring(0,i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0,i);
            String s22 = s2.substring(i);
            if(isScramble(s11,s21) && isScramble(s12,s22)) return true;
            s21 = s2.substring(0,s1.length()-i);
            s22 = s2.substring(s1.length()-i);
            if(isScramble(s11,s22) && isScramble(s12,s21)) return true;
        }

        return false;*/

        //以上递归操作存在重复计算、可以用记忆搜索优化
        //定义int三维数组dp，dp[i][j][len]记录s1从i开始长度为len的子串与s2从j开始长度为len的子串是否互扰，0为未知，-1为否，1为是
        if(s1.length()!=s2.length()) return false;
        int len = s1.length();
        scrambleS1 = s1;
        scrambleS2 = s2;
        dp = new int[len][len][len+1];
        return isScramble(0,0,len);

    }

    private String scrambleS1;
    private String scrambleS2;
    private int[][][] dp;

    //s1从i开始，s2从j开始，长度为len的字符串，是否互相干扰
    private boolean isScramble(int i,int j,int len){

        //命中记忆
        if(dp[i][j][len]!=0){
            return dp[i][j][len]==1;
        }

        //值相同
        if(scrambleS1.substring(i,i+len).equals(scrambleS2.substring(j,j+len))){
            dp[i][j][len] = 1;
            return true;
        }

        //字符种类数量是否一致
        int[] map = new int[26];
        for(int k=i;k<i+len;k++){
            map[scrambleS1.charAt(k)-'a']++;
        }
        for(int k=j;k<j+len;k++){
            int idx = scrambleS2.charAt(k)-'a';
            if(map[idx]==0){
                dp[i][j][len] = -1;
                return false;
            }
            map[idx]--;
        }

        //枚举分割点
        for(int k=1;k<len;k++){
            //不交换
            if(isScramble(i,j,k) && isScramble(i+k,j+k,len-k)){
                dp[i][j][len] = 1;
                return true;
            }
            //交换
            if(isScramble(i,j+len-k,k) && isScramble(i+k,j,len-k)){
                dp[i][j][len] = 1;
                return true;
            }
        }

        dp[i][j][len] = -1;
        return false;

    }

    /**
     * 剑指 Offer 51. 数组中的逆序对
     *
     * @param nums 数组
     * @return 逆序对的数量
     */
    public int reversePairs(int[] nums) {

        /*//1、暴力枚举：时间复杂度O(N^2)
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    count++;
                }
            }
        }
        return count;*/

        /*//2、单调递减栈：时间复杂度在数组递减情况下O(N)，数组递增情况下O(N^2)
        int count = 0;
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(queue.isEmpty()) queue.push(nums[i]);
            else{
                if(nums[i]<queue.peek()){
                    count += queue.size();
                    queue.push(nums[i]);
                }else{
                    List<Integer> list = new ArrayList<>();
                    while(!queue.isEmpty() && nums[i]>=queue.peek()){
                        list.add(queue.pop());
                    }
                    count += queue.size();
                    queue.push(nums[i]);
                    for(int j=list.size()-1;j>=0;j--){
                        queue.push(list.get(j));
                    }
                }
            }
        }
        return count;*/

        //3、归并排序：过程中计算逆序对，时间复杂度O(NlogN)
        if(nums==null || nums.length==0) return 0;
        mergeSort(nums,0,nums.length-1);
        return reversePairCount;

    }
    //成员变量记录逆序对数量
    private int reversePairCount = 0;
    //归并排序
    private void mergeSort(int[] arr,int begin,int end){
        if(begin<end){
            //+的优先级比>>>高，所以要加括号
            int mid = begin + ((end-begin)>>>1);
            mergeSort(arr,begin,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,begin,mid,end);
        }
    }
    //有序表的合并
    private void merge(int[] arr,int begin,int mid,int end){
        int[] temp = new int[end-begin+1];
        int i=0;
        int leftBgin = begin;
        int rightBegin = mid+1;
        while(leftBgin<=mid && rightBegin<=end){
            if(arr[leftBgin]<=arr[rightBegin]){
                temp[i++] = arr[leftBgin++];
            }else{
                temp[i++] = arr[rightBegin++];
                reversePairCount += (mid - leftBgin + 1);
            }
        }
        while(leftBgin<=mid){
            temp[i++] = arr[leftBgin++];
        }
        while(rightBegin<=end){
            temp[i++] = arr[rightBegin++];
        }
        for(int j=begin;j<=end;j++){
            arr[j] = temp[j-begin];
        }
    }

    /**
     * 363. 矩形区域不超过K的最大数值和
     * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
     * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
     *
     * @param matrix 矩阵
     * @param k 整数
     * @return 不超过k的最大数值和
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix==null || matrix.length==0) return -1;
        Integer ans = null;
        int row = matrix.length;
        int col = matrix[0].length;
        //sum[i][j]表示matrix[0][0]到matrix[i][j]矩阵区域的数值和
        int[][] sum = new int[row][col];
        sum[0][0] = matrix[0][0];
        //第一行
        for(int i=1;i<col;i++){
            sum[0][i] = sum[0][i-1] + matrix[0][i];
        }
        //第一列
        for(int i=1;i<row;i++){
            sum[i][0] = sum[i-1][0] + matrix[i][0];
        }
        //其它
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + matrix[i][j];
            }
        }
        //需要求任意点与matrix[i][j]组成的矩阵区域的数值和
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int m=i;m<row;m++){
                    for(int n=j;n<col;n++){
                        int diff;
                        if(i==0&&j==0) diff = sum[m][n];
                        else if(i==0) diff = sum[m][n]-sum[m][j-1];
                        else if(j==0) diff = sum[m][n]-sum[i-1][n];
                        else diff = sum[m][n]-sum[m][j-1]-sum[i-1][n]+sum[i-1][j-1];
                        if(diff<=k){
                            if(ans==null) ans = diff;
                            else ans = Math.max(ans,diff);
                            //最大为k，可以提前剪枝
                            if(ans==k) return ans;
                        }
                    }
                }
            }
        }
        return ans;
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
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * @param head 链表
     * @param k 正整数
     * @return 翻转后的链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        //1、收集节点后重构：时间复杂度O(N)，空间复杂度O(N)
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
        //动态规划
        //dp[i][j][k]表示有i+1个房子，第i+1个房子被染成j+1颜色，且有k+1个街区时的最小花费
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
     * 1723. 完成所有工作的最短时间
     * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
     * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。
     * 工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
     * 请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
     *
     * @param jobs 每项工作需花费的时间
     * @param k 工人数
     * @return 完成所有工作的最短时间
     */
    public int minimumTimeRequired(int[] jobs, int k) {

        //二分查找 + 回溯算法

        //倒序排序，因为先分配大工作能更快找到一个可行的分配方案
        Arrays.sort(jobs);
        int low = 0, high = jobs.length-1;
        while(low<high){
            jobs[low] ^= jobs[high];
            jobs[high] ^= jobs[low];
            jobs[low] ^= jobs[high];
            low++;
            high--;
        }
        //求jobs的最大值及总和
        int max = jobs[0];
        int sum = jobs[0];
        for(int job : jobs){
            max = Math.max(max,job);
            sum += job;
        }
        //当k>=jobs.length时，ans=max。当k=1时，ans=sum。
        int left = max, right = sum;
        while(left<right){
            int mid = left + (right-left)/2;
            //判断在这个时间下k个工人能否完成工作
            if(check(jobs,k,mid)){
                //能完成工作，则缩短工时
                right = mid;
            }else{
                //不能完成工作，则增加工时
                left = mid+1;
            }
        }
        return left;

    }
    //判断在limit时间限制内k个工人能否完成工作
    private boolean check(int[] jobs,int k,int limit){
        //每个工人的负载
        int[] workerLoads = new int[k];
        //回溯
        return backTrack(jobs,workerLoads,0,limit);
    }
    //回溯算法，穷举所有分配情况
    private boolean backTrack(int[] jobs,int[] workerLoads,int jobIndex,int limit){
        if(jobIndex>=jobs.length){
            return true;
        }
        //当前工作
        int job = jobs[jobIndex];
        //分配给任意工人
        for(int i=0;i<workerLoads.length;i++){
            if(workerLoads[i]+job<=limit){
                workerLoads[i] += job;
                boolean flag = backTrack(jobs,workerLoads,jobIndex+1,limit);
                if(flag) return true;
                workerLoads[i] -= job;
            }
            //提前剪枝，如果i工人无法分配到工作(工作量为0)，那么显然i+1工人也无法分配到工作
            if(workerLoads[i]==0){
                break;
            }
        }
        return false;
    }

    /**
     * 10. 正则表达式匹配
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     *
     * @param s 文本串
     * @param p 模式串
     * @return 是否匹配
     */
    public boolean isMatch(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        return isMatch(sArr,pArr,0,0);
    }
    //判断从si及pi开始的s和p能否匹配：关键在于*的处理，需要枚举*匹配零个或多个的所有情况
    private boolean isMatch(char[] s,char[] p,int si,int pi){
        if(pi==p.length) return si==s.length;
        //1、当pi的后一位不是*
        if(pi==p.length-1 || p[pi+1]!='*'){
            return si!=s.length && (s[si]==p[pi] || p[pi]=='.') && isMatch(s,p,si+1,pi+1);
        }
        //2、当pi的后一位是*
        while(si!=s.length && p[pi+1]=='*' && (s[si]==p[pi] || p[pi]=='.')){
            //si能匹配pi，枚举匹配零个或多个
            if(isMatch(s,p,si,pi+2)){
                return true;
            }
            si++;
        }
        //si不能匹配pi，直接跳过
        return isMatch(s,p,si,pi+2);
    }

    /**
     * 面试题 17.19. 消失的两个数字
     * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。
     * 找到消失的两个数字，以任意顺序返回这两个数字均可。
     *
     * @param nums 数组
     * @return 消失的两个数字
     */
    public int[] missingTwo(int[] nums) {

        //1、排序+遍历：时间复杂度O(NlogN)，空间复杂度O(1)
        int[] ans = new int[2];
        int max = nums.length+2;
        Arrays.sort(nums);
        int delta = 1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+delta){
                ans[delta-1] = i+delta;
                delta++;
                if(delta>2) return ans;
                i--;
            }
        }
        if(delta==1){
            ans[0] = max-1;
            ans[1] = max;
        }else if(delta==2){
            ans[1] = max;
        }
        return ans;

        //2、可以在O(N)时间复杂度+O(1)时间复杂度得到结果吗？

    }

    /**
     * 41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * @param nums 数组
     * @return 缺失的第一个正数
     */
    public int firstMissingPositive(int[] nums) {

        //1、排序：时间复杂度O(NlogN)，空间复杂度O(1)
        /*Arrays.sort(nums);
        int first = 1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                if(nums[i]==first){
                    first++;
                }else if(first<nums[i]){
                    return first;
                }
            }
        }
        return first;*/

        //2、哈希：时间复杂度O(N)，空间复杂度O(N)
        /*Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        for(int i=1;i<=nums.length;i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return nums.length+1;*/

        //3、原地哈希：时间复杂度O(N)，空间复杂度O(1)
        //第一个缺失的正数取值范围在[1,N+1]
        //原数组的负数和0是无用的，可以把它们换成N+1，使得数组都是正数
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=0) nums[i] = nums.length+1;
        }
        //对于[1,N]范围内的数，可以用原数组的下标表示，用负号来标识其出现
        for(int i=0;i<nums.length;i++){
            //通过绝对值处理得到原数组的原值
            int val = Math.abs(nums[i]);
            //值在[1,N]范围且对应元素还未被标识
            if(val<=nums.length && nums[val-1]>0){
                nums[val-1] = -nums[val-1];
            }
        }
        //找到第一个非负数，其下标加1即为答案，若找不到，则N+1为答案
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                return i+1;
            }
        }
        return nums.length+1;

    }

    /**
     * 810. 黑板异或游戏
     * 黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。
     * 如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 0 的话，当前玩家游戏失败。
     * (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
     * 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
     * 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
     *
     * @param nums 非负整数数组 1 <= nums.length <= 1000
     * @return 先手能否获胜
     */
    public boolean xorGame(int[] nums) {

        /*//1、暴力递归：时间复杂度(N!)
        int sum = 0;
        for(int num : nums){
            sum ^= num;
        }
        int[] rob = new int[nums.length];
        return xorGame(nums,nums.length,sum,rob,0);*/

        //上述做法复杂度太高，数组稍微长一点就超时了

        //2、数学：时间复杂度O(N)
        //要么一开始异或结果为0，要么数组长度为偶数，二者满足其一，则先手必赢
        if(nums.length%2 == 0){
            return true;
        }
        int sum = 0;
        for(int num : nums){
            sum ^= num;
        }
        return sum == 0;

    }
    /**
     * 递归函数：在剩余的nums中player能否取胜
     * @param nums 原数组
     * @param remainSize 剩余大小
     * @param sum 所有剩余元素的异或结果
     * @param rob 已删除的元素(值为1表示删除)
     * @param player 当前玩家：0、 1
     * @return 当前玩家能否取胜
     */
    private boolean xorGame(int[] nums, int remainSize, int sum, int[] rob, Integer player){
        //剩余异或结果为0，当前玩家获胜
        if(remainSize==0 || sum==0){
            return true;
        }
        //在剩余的数组中删除一个元素，如果导致对手必输，那么当前玩家必赢
        for(int i=0;i<nums.length;i++){
            if(rob[i] == 0){
                int nextSum = sum ^ nums[i];
                if(nextSum != 0){
                    rob[i] = 1;
                    boolean flag = ! xorGame(nums, remainSize-1, nextSum, rob, player==0 ? 1 : 0);
                    rob[i] = 0;
                    if(flag) return true;
                }
            }
        }
        return false;
    }

    /**
     * 1707. 与数组中元素的最大异或值
     * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
     * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。
     * 换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
     * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
     *
     * @param nums 数组(非负整数)  1 <= nums.length <= 10^5
     * @param queries 查询(非负整数)  1 <= queries.length <= 10^5
     * @return 结果
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {

        /*//1、暴力枚举：时间复杂度 O(NQ)
        int[] result = new int[queries.length];
        //排序
        Arrays.sort(nums);
        //遍历
        for(int i=0;i<queries.length;i++){
            int[] query = queries[i];
            int max = -1;
            for(int num : nums){
                if(num>query[1]) break;
                max = Math.max(max,num^query[0]);
            }
            result[i] = max;
        }
        return result;*/

        //2、前缀树：时间复杂度O(NlogN+QlogQ+(N+Q)31)
        int[] result = new int[queries.length];
        //对nums排序
        Arrays.sort(nums);
        //复制queries，为了排序后保存原索引
        int[][] copyQueries = new int[queries.length][3];
        for(int i=0;i<queries.length;i++){
            copyQueries[i][0] = queries[i][0];
            copyQueries[i][1] = queries[i][1];
            copyQueries[i][2] = i;
        }
        //对copyQueries按照copyQueries[i][1]排序
        Arrays.sort(copyQueries,((o1, o2) -> o1[1]-o2[1]));
        //遍历copyQueries，一边构建前缀树，一边查询最大值
        int j = 0;
        IntTrie trie = new IntTrie();
        for(int i=0;i<copyQueries.length;i++){
            //把所有小于copyQueries[i][1]的nums[j]都存入前缀树
            while(j<nums.length && nums[j]<=copyQueries[i][1]){
                trie.insert(nums[j++]);
            }
            if(j==0){
                //前缀树还没有存入整数
                result[copyQueries[i][2]] = -1;
            }else{
                result[copyQueries[i][2]] = trie.getMaxXor(copyQueries[i][0]);
            }
        }
        return result;

    }

    /**
     * 664. 奇怪的打印机
     * 有台奇怪的打印机有以下两个特殊要求：
     * 1、打印机每次只能打印由同一个字符组成的连续序列。
     * 2、每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
     * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
     *
     * @param s 字符串  1 <= s.length <= 100
     * @return 最少打印次数
     */
    public int strangePrinter(String s) {
        //动态规划：时间复杂度O(N^3)
        //dp[i][j]表示打印s[i...j]的最少次数
        int[][] dp = new int[s.length()][s.length()];
        //单个字符
        for(int i=0;i<s.length();i++){
            dp[i][i] = 1;
        }
        //两个及以上字符
        for(int len=1;len<s.length();len++){
            for(int i=0;i+len<s.length();i++){
                int j=i+len;
                if(s.charAt(i)==s.charAt(j)){
                    //首尾相等，打第一个字符同时打最后一个字符
                    dp[i][j] = dp[i][j-1];
                }else{
                    //首尾不等，至少需要打印两次，任意切分为两部分分别打印，取次数最小的一种组合
                    dp[i][j] = len+1;
                    for(int k=0;k<len;k++){
                        dp[i][j] = Math.min(dp[i][j],dp[i][i+k]+dp[i+k+1][j]);
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }

    /**
     * 23. 合并K个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param lists 链表数组
     * @return 合并链表
     */
    public ListNode mergeKLists(ListNode[] lists) {

        /*//1、从左到右一个一个合并
        //先将lists[0]、lists[1]合并，然后将结果与lists[2]合并，以此类推
        if(lists.length<1){
            return null;
        }else if(lists.length==1){
            return lists[0];
        }
        ListNode head = new ListNode();
        ListNode temp = head;
        ListNode temp1 = lists[0];
        for(int i=1;i<lists.length;i++){
            ListNode temp2 = lists[i];
            while(temp1!=null && temp2!=null){
                if(temp1.val<temp2.val){
                    temp.next = temp1;
                    temp = temp.next;
                    temp1 = temp1.next;
                }else{
                    temp.next = temp2;
                    temp = temp.next;
                    temp2 = temp2.next;
                }
            }
            if(temp1!=null) temp.next = temp1;
            if(temp2!=null) temp.next = temp2;
            temp1 = head.next;
            temp = head;
        }
        return head.next;*/

        //2、分治法：两两合并，合并结果再两两合并
        if(lists.length==0) return null;
        return mergeKLists(lists,0,lists.length-1);

    }
    //将数组按照区间中点二分，完成左右两个区间的内部合并后，再将这两个区间合并
    private ListNode mergeKLists(ListNode[] lists, int left, int right){
        if(left==right){
            return lists[left];
        }
        int mid = left+(right-left)/2;
        ListNode l1 = mergeKLists(lists,left,mid);
        ListNode l2 = mergeKLists(lists,mid+1,right);
        return mergeTwoLists(l1,l2);
    }
    //合并两个链表
    private ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode head = new ListNode();
        ListNode temp = head;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            }else{
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        if(l1!=null) temp.next = l1;
        if(l2!=null) temp.next = l2;
        return head.next;
    }

    /**
     * 1074. 元素和为目标值的子矩阵数量
     * 给出矩阵matrix和目标值target，返回元素总和等于目标值的非空子矩阵的数量。
     * 子矩阵x1, y1, x2, y2是满足 x1 <= x <= x2且y1 <= y <= y2的所有单元matrix[x][y]的集合。
     * 如果(x1, y1, x2, y2) 和(x1', y1', x2', y2')两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
     *
     * @param matrix 矩阵
     * @param target 目标值
     * @return 元素和为目标值的子矩阵数量
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        //1、前缀和+枚举：时间复杂度O((MN)^2)，空间复杂度O(MN)
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] prefixSum = new int[row][col];
        prefixSum[0][0] = matrix[0][0];
        for(int i=1;i<row;i++){
            prefixSum[i][0] = prefixSum[i-1][0] + matrix[i][0];
        }
        for(int j=1;j<col;j++){
            prefixSum[0][j] = prefixSum[0][j-1] + matrix[0][j];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i][j];
            }
        }
        int count = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int a=i;a<row;a++){
                    for(int b=j;b<col;b++){
                        int sum;
                        if(i==0 && j==0) sum = prefixSum[a][b];
                        else if(i==0) sum = prefixSum[a][b] - prefixSum[a][j-1];
                        else if(j==0) sum = prefixSum[a][b] - prefixSum[i-1][b];
                        else sum = prefixSum[a][b] - prefixSum[i-1][b] - prefixSum[a][j-1] + prefixSum[i-1][j-1];
                        if(sum == target) count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 879. 盈利计划
     * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
     * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
     * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
     * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
     *
     * @param n 员工总数 (1 <= n <= 100)
     * @param minProfit 最低利润 (0 <= minProfit <= 100)
     * @param group 工作人力列表 (1 <= group.length <= 100 && 1 <= group[i] <= 100)
     * @param profit 工作利润列表 (profit.length == group.length && 0 <= profit[i] <= 100)
     * @return 盈利计划数
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        /*//1、动态规划
        int mod = 1000000007;
        //计算最大利润
        int maxProfit = 0;
        for(int one : profit){
            maxProfit += one;
        }
        //dp[i][j][k]表示完成[0...i]的任意工作，最多有j名员工，得到k利润的方案数
        int[][][] dp = new int[profit.length][n+1][maxProfit+1];
        //i==0
        for(int j=0;j<=n;j++){
            //利润为0有一种方案，就是什么都不做
            dp[0][j][0] = 1;
            //当员工足够时，利润为profit[0]有一种方案
            if(j>=group[0]) dp[0][j][profit[0]] = 1;
        }
        //i>0
        for(int i=1;i<profit.length;i++){
            for(int j=0;j<=n;j++){
                for(int k=0;k<=maxProfit;k++){
                    //不做i工作
                    dp[i][j][k] += dp[i-1][j][k];
                    dp[i][j][k] %= mod;
                    //做i工作
                    if(j>=group[i] && k>=profit[i]){
                        dp[i][j][k] += dp[i-1][j-group[i]][k-profit[i]];
                        dp[i][j][k] %= mod;
                    }
                }
            }
        }
        //计算盈利方案数
        int count = 0;
        for(int k=minProfit;k<=maxProfit;k++){
            count += dp[profit.length-1][n][k];
            count %= mod;
        }
        return count;*/

        //上述做法最大空间需要100*100*10000=10^8，数量级为亿，超出空间限制

        //2、动态规划：基于第一种做法优化dp数组的第三维
        int mod = 1000000007;
        //dp[i][j][k]表示完成[0...i-1]的任意工作，最多有j名员工，利润至少为k的方案数
        int[][][] dp = new int[profit.length+1][n+1][minProfit+1];
        for(int j=0;j<=n;j++) dp[0][j][0] = 1;
        for(int i=1;i<=profit.length;i++){
            for(int j=0;j<=n;j++){
                for(int k=0;k<=minProfit;k++){
                    if(j<group[i-1]){
                        dp[i][j][k] = dp[i-1][j][k];
                    }else{
                        dp[i][j][k] = (dp[i-1][j][k] + dp[i-1][j-group[i-1]][Math.max(0,k-profit[i-1])]) % mod;
                    }
                }
            }
        }
        return dp[profit.length][n][minProfit];

    }

    /**
     * 1449. 数位成本和为目标值的最大数字
     * 给你一个整数数组cost和一个整数target。请你返回满足如下规则可以得到的最大整数：
     * 给当前结果添加一个数位（i + 1）的成本为cost[i]（cost数组下标从0开始）。总成本必须恰好等于target。添加的数位中没有数字0。
     * 由于答案可能会很大，请你以字符串形式返回。如果按照上述要求无法得到任何整数，请你返回"0"。
     *
     * @param cost 1~9的成本
     * @param target 目标值
     * @return 最大数字
     */
    public String largestNumber(int[] cost, int target) {

        //最大数：1、长度尽可能长(100>99)，2、长度相同情况下字典序尽可能大(100<999)

        //完全背包问题：给定一组资源，单元素可无限获取，给定限制，求最值

        //动态规划
        //dp[i]表示目标值为i时的最大数
        String[] dp = new String[target+1];
        Arrays.fill(dp,"");
        //枚举目标值
        for(int i=1;i<=target;i++){
            //枚举最后一个数位
            for(int j=0;j<cost.length;j++){
                if(i >= cost[j] && ! "0".equals(dp[i-cost[j]])){
                    String temp = dp[i-cost[j]] + String.valueOf(j+1);
                    if(dp[i].length()<temp.length() || (dp[i].length()==temp.length() && dp[i].compareTo(temp)<0)){
                        dp[i] = temp;
                    }
                }
            }
            //如果不能消耗完目标值，做一个标记
            if("".equals(dp[i])) dp[i] = "0";
        }
        return dp[target];

    }

}
