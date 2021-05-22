package cn.jinty.leetcode.function;

import cn.jinty.leetcode.TreeNode;

import java.util.*;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/5/8
 **/
public class Fun5 {

    /**
     * 1723. 完成所有工作的最短时间
     * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
     * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
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
            //提前剪枝，如果i工人无法分配到工作，那么显然i+1工人也无法分配到工作
            if(workerLoads[i]==0){
                break;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     * 实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 数值（按顺序）可以分成以下几个部分：
     *     若干空格
     *     一个 小数 或者 整数
     *     （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     *     若干空格
     * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
     *
     * @param s 字符串
     * @return 是否表示数值
     */
    public boolean isNumber(String s) {
        if(s==null) return false;
        s = s.trim();
        if(s.length()==0) return false;
        //小数点个数
        int countPoint = 0;
        //E(e)个数
        int countE = 0;
        //数字个数
        int countNumBeforeE = 0;
        int countNumAfterE = 0;
        //排除非法情况
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='+' || c=='-'){
                //加减号只能在首部或者E(e)后面
                if(i!=0){
                    char pre = s.charAt(i-1);
                    if(pre!='E' && pre!='e'){
                        return false;
                    }
                }
            }else if(c=='.'){
                //小数点不能超过1个
                if(countPoint==1) return false;
                //E(e)后面不能出现小数点
                if(countE==1) return false;
                countPoint++;
            }else if(c=='E' || c=='e'){
                //E(e)不能超过1个
                if(countE==1) return false;
                countE++;
            }else if(c>='0' && c<='9'){
                if(countE==0) countNumBeforeE++;
                else countNumAfterE++;
            }else{
                return false;
            }
        }
        return countE==0 ? countNumBeforeE>0 : countNumBeforeE>0 && countNumAfterE>0;
    }

    /**
     * 1482. 制作 m 束花所需的最少天数
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     *
     * @param bloomDay 开花时间
     * @param m 花束数
     * @param k 每束花的花朵数
     * @return 最少天数
     */
    public int minDays(int[] bloomDay, int m, int k) {

        //二分查找
        //锁定天数范围，在范围内二分，对于一个给定天数，判断是否可以制作m束花

        int flowerNum = m * k;
        if(flowerNum > bloomDay.length) return -1;
        if(flowerNum == bloomDay.length){
            return Arrays.stream(bloomDay).max().getAsInt();
        }
        int left = bloomDay[0], right = bloomDay[0];
        for(int day : bloomDay){
            left = Math.min(left,day);
            right = Math.max(right,day);
        }
        while(left < right){
            int mid = left+(right-left)/2;
            if(canMakeFlower(bloomDay,m,k,mid)){
                //mid天可以制作m束花，则可能存在更小的天数解
                right = mid;
            }else{
                //mid天不可制作m束花，则需要更多的天数
                left = mid + 1;
            }
        }
        return left;

    }
    //判断在给定天数下能否制作m束花
    private boolean canMakeFlower(int[] bloomDay, int m, int k, int day){
        int num = 0;
        for(int i=0;i<bloomDay.length;i++){
            if(bloomDay[i]<=day){
                num++;
                if(num==k){
                    m--;
                    if(m==0) return true;
                    num = 0;
                }
            }else{
                num = 0;
            }
        }
        return false;
    }

    /**
     * 872. 叶子相似的树
     * 一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个叶值序列。
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     *
     * @param root1 二叉树1
     * @param root2 二叉树2
     * @return 是否相似
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        preOrder(root1,leaf1);
        preOrder(root2,leaf2);
        return leaf1.equals(leaf2);
    }
    private void preOrder(TreeNode root, List<Integer> leaf){
        if(root==null) return;
        if(root.left==null && root.right==null){
            leaf.add(root.val);
            return;
        }
        preOrder(root.left,leaf);
        preOrder(root.right,leaf);
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
        /*//空串
        if(s==null || s.length()==0){
            return p == null || p.length() == 0;
        }
        if(p==null || p.length()==0) return false;
        //非空串
        int si=0, pi=0;
        while(si<s.length() && pi<p.length()){
            char c1 = s.charAt(si);
            char c2 = p.charAt(pi);
            if(c2!='.' && c2!='*'){
                if(c1!=c2){
                    if(pi<p.length()-1 && p.charAt(pi+1)=='*'){
                        pi+=2;
                    }else{
                        break;
                    }
                }
                else{
                    si++;
                    pi++;
                }
            }else{
                if(c2=='.'){
                    si++;
                    pi++;
                }else{
                    char pre = p.charAt(pi-1);
                    if(pre=='.'){
                        si++;
                        if(si==s.length()){
                            pi++;
                        }
                    }else{
                        if(c1==pre){
                            si++;
                            if(si==s.length()){
                                pi++;
                            }
                        }else{
                            pi++;
                        }
                    }
                }
            }
        }
        return si>=s.length() && pi>=p.length();*/

        //上述做法无法解决 s与p前段匹配而p仍有剩余 的问题

        //递归算法
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        return isMatch(sArr,pArr,0,0);

    }
    //判断从si及pi开始的s和p能否匹配
    private boolean isMatch(char[] s,char[] p,int si,int pi){
        if(pi==p.length){
            return si==s.length;
        }
        //当pi的后一位不是*
        if(pi==p.length-1 || p[pi+1]!='*'){
            return si!=s.length && (s[si]==p[pi] || p[pi]=='.') && isMatch(s,p,si+1,pi+1);
        }
        //当pi的后一位是*
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
     * 1734. 解码异或后的排列
     * 给你一个整数数组 perm ，它是前 n 个 正整数 的排列，且 n 是个 奇数 。
     * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
     *
     * @param encoded 密文
     * @return 原文
     */
    public int[] decode(int[] encoded) {
        /*int[] perm = new int[encoded.length+1];
        //encoded[i] = perm[0] XOR perm[i + 1]
        for(int i=1; i<encoded.length; i++){
            encoded[i] ^= encoded[i-1];
        }
        //perm[i]的取值范围为[1,n]，枚举perm[0]
        int n = perm.length;
        loop : for(int i=1; i<=n; i++){
            perm[0] = i;
            for(int j=1;j<perm.length;j++){
                perm[j] = encoded[j-1] ^ perm[0];
                //超出取值范围
                if(perm[j]<=0 || perm[j]>n){
                    continue loop;
                }
            }
            break;
        }
        return perm;*/

        //上述做法时间复杂度为O(n^2)，执行超时
        //而且存在BUG，如果perm中出现重复元素，也会被认为是一个解

        //基于条件找规律，求出perm[0]
        //1. perm是[1,n]的排列，基于此可以得到所有元素的异或结果
        int[] perm = new int[encoded.length+1];
        int total = 0;
        for(int i=1;i<=perm.length;i++){
            total ^= i;
        }
        //2. n为奇数，且 encoded[i] = perm[i] ^ perm[i + 1]
        //  encoded[0] = perm[0] ^ perm[1]
        //  encoded[1] = perm[1] ^ perm[2]
        //  encoded[2] = perm[2] ^ perm[3]
        //  encoded[3] = perm[3] ^ perm[4]
        //有上述条件可知，基于encoded[2k+1]可以得到除了perm[0]外所有元素的异或结果
        int odd = 0;
        for(int i=1;i<encoded.length;i+=2){
            odd ^= encoded[i];
        }
        //得到perm[0]
        perm[0] = total ^ odd;
        //递推得到答案
        for(int i=1;i<perm.length;i++){
            perm[i] = perm[i-1] ^ encoded[i-1];
        }
        return perm;
    }

    /**
     * 1310. 子数组异或查询
     * 有一个正整数数组arr，现给你一个对应的查询数组queries，其中queries[i] = [Li,Ri]。
     * 对于每个查询i，请你计算从Li到Ri的XOR值（即arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     * 并返回一个包含给定查询queries所有结果的数组。
     *
     * @param arr 数组
     * @param queries 子数组异或
     * @return 结果
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        //前缀异或结果
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            prefix[i] = prefix[i-1] ^ arr[i];
        }
        //子数组异或查询
        int[] result = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            result[i] = prefix[queries[i][1]]
                    ^ (queries[i][0] > 0 ? prefix[queries[i][0]-1] : 0);
        }
        return result;
    }

    /**
     * 79. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * @param board 二维网格 [1,6][1,6]
     * @param word 单词 [1,15]
     * @return 是否存在
     */
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        if(row*col < word.length()) return false;
        char[] words = word.toCharArray();
        //存放已匹配的坐标
        Set<Long> set = new HashSet<>();
        //枚举起点
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(exist(board,i,j,set,words,0)){
                    return true;
                }
            }
        }
        return false;
    }
    //递归回溯 以(i,j)为起点搜索words[idx,words.length-1]
    private boolean exist(char[][] board, int i, int j, Set<Long> set, char[] words, int idx){
        //完成words匹配
        if(idx==words.length) return true;
        //用一个long来保存(i,j)坐标
        Long locate = (long)i << 32 | (long)j & 0xFFFFFFFFL;
        //坐标已被占用
        if(set.contains(locate)) return false;
        //当前字符不匹配
        if(board[i][j]!=words[idx]) return false;
        //当前字符匹配
        if(idx==words.length-1) return true;
        //保存坐标
        set.add(locate);
        //向上下左右四个方向递归
        if(i>0 && exist(board,i-1,j,set,words,idx+1)) return true;
        if(i<board.length-1 && exist(board,i+1,j,set,words,idx+1)) return true;
        if(j>0 && exist(board,i,j-1,set,words,idx+1)) return true;
        if(j<board[0].length-1 && exist(board,i,j+1,set,words,idx+1)) return true;
        //移除坐标
        set.remove(locate);
        return false;
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0,0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     * 请问该机器人能够到达多少个格子？
     *
     * @param m 行
     * @param n 列
     * @param k 限制
     * @return 范围大小(格子数)
     */
    public int movingCount(int m, int n, int k) {
        //计算数位和
        int len = Math.max(m,n);
        int[] arr = new int[len];
        for(int i=0;i<len;i++){
            int temp = i;
            while(temp>=10){
                arr[i] += temp%10;
                temp /= 10;
            }
            arr[i] += temp;
        }
        //递归求解
        Set<Long> set = new HashSet<>();
        return movingCount(m,n,k,arr,0,0,set);
    }
    //递归函数
    private int movingCount(int m, int n, int k, int[] arr, int i, int j, Set<Long> set){
        //超出范围
        if(i<0 || i>=m || j<0 || j>=n) return 0;
        //不可进入的点
        if(arr[i]+arr[j]>k) return 0;
        //用long保存坐标(i,j)
        Long locate = (long)i << 32 | (long)j & 0xFFFFFFFFL;
        //已经走过的点
        if(set.contains(locate)) return 0;
        int count = 1;
        set.add(locate);
        //当前点可以进入，则向上下左右走
        count += movingCount(m,n,k,arr,i-1,j,set);
        count += movingCount(m,n,k,arr,i+1,j,set);
        count += movingCount(m,n,k,arr,i,j-1,set);
        count += movingCount(m,n,k,arr,i,j+1,set);
        return count;
    }

    /**
     * 1269. 停在原地的方案数
     * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     * 由于答案可能会很大，请返回方案数 模 1000000007 后的结果。
     *
     * @param steps 步骤数
     * @param arrLen 数组长度
     * @return 方案数
     */
    public int numWays(int steps, int arrLen) {
        //动态规划
        //含义：dp[i][j]表示在 i+1 步操作之后，指针位于数组下标 j 的方案数
        //由于steps最远只能到arr[steps]，所以当数组长度很长时，只取steps+1长度的数组
        int len = Math.min(arrLen, steps + 1);
        int[][] dp = new int[steps][len];
        //枚举最后一步，得到递推方程：dp[i][j] = dp[i-1][j]+dp[i-1][j-1]+dp[i-1][j+1]
        for(int j=0;j<len && j<2;j++){
            dp[0][j] = 1;
        }
        for(int i=1;i<steps;i++){
            for(int j=0;j<len;j++){
                //j>i+1时，j一定到不了，可以提前剪枝
                if(j>i+1) break;
                dp[i][j] = dp[i-1][j];
                if(j>0) dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % 1000000007;
                if(j<len-1) dp[i][j] = (dp[i][j] + dp[i-1][j+1]) % 1000000007;
            }
        }
        return dp[steps-1][0];
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     *
     * @param n 骰子个数
     * @return 点数概率
     */
    public double[] dicesProbability(int n) {
        //确定值范围
        int min = n;
        int max = n*6;
        double[] answer = new double[max-min+1];
        //确定所有可能出现的情况总数
        int sum = 6;
        int count = n;
        while(count>1){
            sum *= 6;
            count--;
        }
        //确定每个值对应的情况数
        //dp[i][j]表示用i+1个骰子得到j的情况数
        int[][] dp = new int[n][max+1];
        //1个骰子，值为1~6的情况各有一种
        for(int j=1;j<=6;j++){
            dp[0][j] = 1;
        }
        //大于1个骰子：枚举最后一个骰子的值，剩余值由剩余骰子组成
        for(int i=1;i<n;i++){
            //i+1个骰子的值范围
            int minJ = i+1, maxJ = minJ*6;
            for(int j=i+1;j<=maxJ;j++){
                //最后一个骰子的值可以是1~6
                for(int k=1; k<=6 && j>k; k++){
                    dp[i][j] += dp[i-1][j-k];
                }
            }
        }
        //计算每种值对应的概率
        for(int k=0;k<answer.length;k++){
            answer[k] = dp[n-1][k+min]*1.0 / sum;
        }
        return answer;
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

        //1、排序：时间复杂度O(NlogN)，空间复杂度O(1)
        int[] ans = new int[2];
        int min = 1, max = nums.length+2;
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
            int val = Math.abs(nums[i]);
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
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角。机器人每次只能向下或者向右移动一步。
     * 机器人试图达到网格的右下角。问总共有多少条不同的路径？
     *
     * @param m 行
     * @param n 列
     * @return 路径数
     */
    public int uniquePaths(int m, int n) {
        //动态规划
        //dp[i][j]表示从起点到达(i,j)的路径数
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0] = 1;
        }
        for(int j=0;j<n;j++){
            dp[0][j] = 1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                //由机器人只能向下或向右得到递推方程
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 421. 数组中两个数的最大异或值
     * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     * 1 <= nums.length <= 2 * 10^4
     * 0 <= nums[i] <= 2^31 - 1
     *
     * @param nums 数组
     * @return 最大异或值
     */
    public int findMaximumXOR(int[] nums) {

        /*//1、时间复杂度O(N^2)
        //因为输入数组是正数，所以异或结果最小为0
        int max = 0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                max = Math.max(max,nums[i]^nums[j]);
            }
        }
        return max;*/

        //2、时间复杂度O(31N)=O(N)
        //从高位往低位确定x的值
        int x = 0;
        loop : for(int k=30;k>=0;k--){
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                //保留所有数从最高位开始到第 k 个二进制位为止的部分
                set.add(num >> k);
            }
            //假设x的下一位为1
            int xNext = 2*x+1;
            for(int num : nums){
                //x的下一位可以为1
                if(set.contains(xNext ^ num>>k)){
                    x = xNext;
                    continue loop;
                }
            }
            //x的下一位不能为1
            x = xNext - 1;
        }
        return x;

    }

    /**
     * 1528. 重新排列字符串
     * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     * 返回重新排列后的字符串。
     *
     * @param s 字符串
     * @param indices 新索引
     * @return 排列字符串
     */
    public String restoreString(String s, int[] indices) {
        char[] ans = new char[s.length()];
        for(int i=0;i<s.length();i++){
            ans[indices[i]] = s.charAt(i);
        }
        return new String(ans);
    }

    /**
     * 877. 石子游戏
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子piles[i]。
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     * 亚历克斯和李轮流进行，亚历克斯先开始。每回合，玩家从行的开始或结束处取走整堆石头。
     * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回true，当李赢得比赛时返回false。
     *
     * @param piles 石子堆数组 (2 <= piles.length <= 500 且 piles.length 是偶数)
     * @return 先手能否获胜
     */
    public boolean stoneGame(int[] piles) {

        /*//1、暴力递归：时间复杂度O(2^N)
        return stoneGame(piles,0,0,0,piles.length-1,0);*/

        //2、动态规划：时间复杂度O(N^2)
        //dp[i][j]表示在piles[i...j]中先手多拿的石子数(相应的，-dp[i][j]表示后手多拿的石子数)
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = piles[i];
        }
        //枚举区间长度
        for(int len=1;len<n;len++){
            //枚举起点
            for(int i=0;i<n-len;i++){
                //计算终点
                int j = i+len;
                //递推方程：先手拿左边石子或拿右边石子，子问题中当前先手成为后手
                dp[i][j] = Math.max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
            }
        }
        return dp[0][n-1] > 0;

    }
    /**
     * 暴力递归：枚举所有情况
     *
     * @param piles 石子堆数组
     * @param sum0 先手分数
     * @param sum1 后手分数
     * @param left 左指针
     * @param right 右指针
     * @param player 当前玩家：0为Alex，1为Lee
     * @return 当前玩家能否取胜
     */
    private boolean stoneGame(int[] piles,int sum0,int sum1,int left,int right,int player){
        if(left > right){
            if(player==0) return sum0 > sum1;
            else return sum1 > sum0;
        }
        if(player == 0){
            //玩家0有两种选择，选择其中一种可以使得玩家1必输，则玩家0必赢
            return ! stoneGame(piles,sum0+piles[left],sum1,left+1,right,1) ||
                    ! stoneGame(piles,sum0+piles[right],sum1,left,right-1,1);
        }else{
            //玩家1有两种选择，选择其中一种可以使得玩家0必输，则玩家1必赢
            return ! stoneGame(piles,sum0,sum1+piles[left],left+1,right,0) ||
                    ! stoneGame(piles,sum0,sum1+piles[right],left,right-1,0);
        }
    }

    /*
     * 993. 二叉树的堂兄弟节点
     * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
     * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
     *
     * @param root 根节点
     * @param x 节点值1
     * @param y 节点值2
     * @return x和y是否为堂兄弟
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer,Integer> map = new HashMap<>();
        nodeParentMap(root,map);
        int depthX = 0, depthY = 0;
        Integer valX = x, valY = y;
        while(valX!=null){
            depthX++;
            valX = map.get(valX);
        }
        while(valY!=null){
            depthY++;
            valY = map.get(valY);
        }
        return depthX==depthY && !map.get(x).equals(map.get(y));
    }
    //用哈希表存储节点与父节点的映射
    private void nodeParentMap(TreeNode root, Map<Integer,Integer> map){
        if(root==null) return;
        if(root.left!=null){
            map.put(root.left.val,root.val);
            nodeParentMap(root.left,map);
        }
        if(root.right!=null){
            map.put(root.right.val,root.val);
            nodeParentMap(root.right,map);
        }
    }

    /**
     * 1442. 形成两个异或相等数组的三元组数目
     * 从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     * a 和 b 定义如下：
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     * @param arr 数组 1 <= arr.length <= 300
     * @return 三元组数目
     */
    public int countTriplets(int[] arr) {
        int count = 0;
        //前缀异或
        int[] preXor = new int[arr.length];
        preXor[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            preXor[i] = preXor[i-1] ^ arr[i];
        }
        /*//1、枚举所有三元组 时间复杂度O(N^3)
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j;k<arr.length;k++){
                    int a = (i==0) ? preXor[j-1] : preXor[j-1]^preXor[i-1];
                    int b = preXor[k]^preXor[j-1];
                    if(a==b) count++;
                }
            }
        }
        return count;*/

        //2、枚举所有的二元组 时间复杂度O(N^2)
        //判断 preXor[j-1]^preXor[i-1]==preXor[k]^preXor[j-1]
        //只需 preXor[i-1]==preXor[k] j可以取[i+1,k]的任意值
        for(int i=0;i<arr.length-1;i++){
            for(int k=i+1;k<arr.length;k++){
                if((i==0 && preXor[k]==0) || (i>0 && preXor[i-1]==preXor[k])){
                    count += (k-i);
                }
            }
        }
        return count;

    }

    /**
     * 1738. 找出第 K 大的异或坐标值
     * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
     * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
     * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
     *
     * @param matrix 矩阵 1 <= m, n <= 1000
     * @param k 正整数
     * @return 第K大的异或坐标值
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        //前缀异或值
        int[][] preXor = new int[m][n];
        preXor[0][0] = matrix[0][0];
        for(int j=1;j<n;j++){
            preXor[0][j] = preXor[0][j-1] ^ matrix[0][j];
        }
        for(int i=1;i<m;i++){
            preXor[i][0] = preXor[i-1][0] ^ matrix[i][0];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                preXor[i][j] = preXor[i-1][j] ^ preXor[i][j-1] ^ preXor[i-1][j-1] ^ matrix[i][j];
            }
        }
        //找前k个大值，存入小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(queue.size()<k) queue.offer(preXor[i][j]);
                else if(preXor[i][j]>queue.peek()){
                    queue.poll();
                    queue.offer(preXor[i][j]);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 692. 前K个高频单词
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * @param words 单词数组
     * @param k 整数
     * @return 前K个高频单词
     */
    public List<String> topKFrequent(String[] words, int k) {
        //收集词频 时间O(N)、空间O(N)
        Map<String,Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        //小根堆排序 时间O(NlogK)、空间O(K)
        PriorityQueue<Map.Entry<String,Integer>> queue = new PriorityQueue<>(
            k, (o1, o2) -> {
                 if(o1.getValue().equals(o2.getValue())){
                     return o2.getKey().compareTo(o1.getKey());
                 }
                 return o1.getValue() - o2.getValue();
            }
        );
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(queue.size()<k){
                queue.offer(entry);
            }else{
                Map.Entry<String,Integer> min = queue.peek();
                if(entry.getValue().equals(min.getValue())){
                    if(entry.getKey().compareTo(min.getKey()) < 0){
                        queue.poll();
                        queue.offer(entry);
                    }
                }else{
                    if(entry.getValue()>min.getValue()){
                        queue.poll();
                        queue.offer(entry);
                    }
                }
            }
        }
        //构建结果
        List<String> ans = new ArrayList<>();
        while( ! queue.isEmpty()){
            ans.add(queue.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }

    /**
     * 1035. 不相交的线
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
     * 1、nums1[i] == nums2[j]
     * 2、绘制的直线不与任何其他连线（非水平线）相交。
     * 3、每个数字只能属于一条连线。
     * 以这种方法绘制线条，返回可以绘制的最大连线数。
     *
     * @param nums1 直线1(数字数组) 1 <= nums1.length <= 500
     * @param nums2 直线2(数字数组) 1 <= nums2.length <= 500
     * @return 最大连线数
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        //动态规划
        //dp[i][j]表示nums1[0...i]与nums2[0...j]的最大连线数
        int[][] dp = new int[nums1.length][nums2.length];
        dp[0][0] = (nums1[0]==nums2[0] ? 1 : 0);
        for(int i=1;i<nums1.length;i++){
            dp[i][0] = (dp[i-1][0]==1 || nums1[i]==nums2[0] ? 1 : 0);
        }
        for(int j=1;j<nums2.length;j++){
            dp[0][j] = (dp[0][j-1]==1 || nums1[0]==nums2[j] ? 1 : 0);
        }
        for(int i=1;i<nums1.length;i++){
            for(int j=1;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    //尾部相等，最后一对组成一个连线
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    //尾部不相等，最后一对不能组成连线
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[nums1.length-1][nums2.length-1];
    }

}
