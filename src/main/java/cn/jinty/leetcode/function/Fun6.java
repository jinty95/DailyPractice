package cn.jinty.leetcode.function;

import cn.jinty.struct.linear.ListNode;

import java.util.*;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/5/24
 **/
public class Fun6 {

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
     * 475. 供暖器
     * 设计一个有固定加热半径的供暖器向所有房屋供暖，使得在加热器的加热半径范围内的每个房屋都可以获得供暖。
     * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
     * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
     *
     * @param houses 房子位置
     * @param heaters 加热器位置
     * @return 最小半径
     */
    public int findRadius(int[] houses, int[] heaters) {
        //排序
        Arrays.sort(houses);
        Arrays.sort(heaters);
        //房子在左，供暖器在右，求每个房子到供暖器的最短距离
        int[] leftDis = new int[houses.length];
        int j = 0;
        for(int i=0;i<houses.length;i++){
            if(j==heaters.length){
                leftDis[i] = Integer.MAX_VALUE;
            }else if(houses[i]<=heaters[j]){
                leftDis[i] = heaters[j]-houses[i];
            }else{
                j++;
                i--;
            }
        }
        //房子在右，供暖器在左，求每个房子到供暖器的最短距离
        int[] rightDis = new int[houses.length];
        j = heaters.length-1;
        for(int i=houses.length-1;i>=0;i--){
            if(j==-1){
                rightDis[i] = Integer.MAX_VALUE;
            }else if(houses[i]>=heaters[j]){
                rightDis[i] = houses[i]-heaters[j];
            }else{
                j--;
                i++;
            }
        }
        //对于每个房子，求到左右供暖器的最小距离，所有最小距离中的最大值即为加热半径
        int radix = 0;
        for(int i=0;i<houses.length;i++){
            radix = Math.max(radix,Math.min(leftDis[i],rightDis[i]));
        }
        return radix;
    }

    /**
     * 1262. 可被三整除的最大和
     * 给你一个整数数组 nums，请你找出并返回能被三整除的最大和。
     *
     * @param nums 数组  1 <= nums.length <= 4 * 10^4
     * @return 能被三整除的最大和
     */
    public int maxSumDivThree(int[] nums) {

        /*//1、递归回溯：时间复杂度O(2^N)
        maxSumDivThree(nums,0,0);
        return maxSumDivThree;*/

        //上述做法复杂度太高，执行超时

        //2、动态规划：时间复杂度O(3N)
        //dp[i][j]表示数组nums[0...i]模3得j的最大和
        int[][] dp = new int[nums.length][3];
        for(int j=0;j<3;j++){
            dp[0][j] = nums[0]%3 == j ? nums[0] : 0;
        }
        for(int i=1;i<nums.length;i++){
            int mod = nums[i]%3;
            for(int j=0;j<3;j++){
                dp[i][j] = Math.max(mod == j ? nums[i] : 0, dp[i-1][j]);
                int offset = (j+3-mod)%3;
                if(dp[i-1][offset]!=0){
                    dp[i][j] = Math.max(
                            dp[i][j], dp[i-1][offset] + nums[i]
                    );
                }
            }
        }
        return dp[nums.length-1][0];

    }
    private int maxSumDivThree = 0;
    //递归函数：枚举所有组合
    private void maxSumDivThree(int[] nums,int index,int sum){
        if(sum%3==0){
            maxSumDivThree = Math.max(maxSumDivThree,sum);
        }
        for(int i=index;i<nums.length;i++){
            sum += nums[i];
            maxSumDivThree(nums,i+1,sum);
            sum -= nums[i];
        }
    }

    /**
     * 1190. 反转每对括号间的子串
     * 给出一个字符串 s（仅含有小写英文字母和括号）。
     * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
     * 注意，您的结果中 不应 包含任何括号。
     *
     * @param s 字符串
     * @return 结果
     */
    public String reverseParentheses(String s) {
        //借助栈完成括号内的反转操作
        Deque<Character> deque = new LinkedList<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c==')'){
                //找到一个右括号，则把对应区间内字符取出
                List<Character> list = new ArrayList<>();
                while(deque.peek()!='('){
                    list.add(deque.pop());
                }
                //删除对应的左括号
                deque.poll();
                //区间字符反转后重新入栈
                for(Character one : list){
                    deque.push(one);
                }
            }else{
                //除了右括号的字符直接入栈
                deque.push(c);
            }
        }
        //从栈中取出最终结果
        StringBuilder sb = new StringBuilder();
        while( ! deque.isEmpty()){
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }

    /**
     * 289. 生命游戏
     * 生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 1、如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 2、如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 3、如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 4、如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
     *
     * @param board 二维网格面板
     */
    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        //下一个状态
        int[][] next = new int[row][col];
        int[] around = {-1,0,1};
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //基于周围8个点统计活细胞数量
                int live = 0;
                for (int a : around) {
                    if ((i == 0 && a == -1) || (i == row - 1 && a == 1)) continue;
                    for (int b : around) {
                        if ((j == 0 && b == -1) || (j == col - 1 && b == 1)) continue;
                        if (a == 0 && b == 0) continue;
                        if (board[i + a][j + b] == 1) {
                            live++;
                        }
                    }
                }
                //推出当前点的下一个状态
                if(board[i][j]==0){
                    if(live==3) next[i][j]=1;
                }else{
                    if(live==2 || live==3) next[i][j]=1;
                }
            }
        }
        //将下一个状态写回原数组
        for(int i=0;i<row;i++){
            System.arraycopy(next[i], 0, board[i], 0, col);
        }
    }

    /**
     * 6. Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *          P   A   H   N
     *          A P L S I I G
     *          Y   I   R
     * 之后，输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * @param s 字符串
     * @param numRows 行数
     * @return 新字符串
     */
    public String convert(String s, int numRows) {

        /*//解法1：时间复杂度O(NlogN)
        //不需要处理的情况
        if(numRows==1 || s.length()<=numRows) return s;
        //记录每个字符的坐标位置
        int[][] pos = new int[s.length()][3];
        int i=0, j=0, k=0;
        boolean down = true;
        while(k<s.length()){
            if(down){
                if(i==numRows-1) down = false;
                else{
                    //下降
                    pos[k] = new int[]{s.charAt(k), i++, j};
                    k++;
                }
            }else{
                if(i==0) down = true;
                else{
                    //上升
                    pos[k] = new int[]{s.charAt(k), i--, j++};
                    k++;
                }
            }
        }
        //先按行后按列排序
        Arrays.sort(pos,(o1,o2)->{
            if(o1[1]==o2[1]) return o1[2]-o2[2];
            return o1[1]-o2[1];
        });
        //构建结果字符串
        StringBuilder result = new StringBuilder();
        for (int[] po : pos) {
            result.append((char) po[0]);
        }
        return result.toString();*/

        //解法2：时间复杂度O(N)
        if(numRows==1) return s;
        StringBuilder sb = new StringBuilder();
        //Z字形变换是一个周期性结构，且行数增一周期增二，对于每一行，一个周期会贡献1~2个元素
        int cycle = 2*numRows-2;
        //遍历行
        for(int i=0;i<numRows;i++){
            //收集属于该行的元素
            for(int j=i;j<s.length();j+=cycle){
                sb.append(s.charAt(j));
                if(i>0 && i<numRows-1){
                    int k = j-i+cycle-i;
                    if(k<s.length()) sb.append(s.charAt(k));
                }
            }
        }
        return sb.toString();

    }

    /**
     * 139. 单词拆分
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
     * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明：
     * 1、拆分时可以重复使用字典中的单词。
     * 2、字典中没有重复的单词。
     *
     * @param s 字符串
     * @param wordDict 字典
     * @return 字符串能否由字典的单词组成
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        /*//1、暴力递归
        //将列表转为哈希表
        Set<String> wordSet = new HashSet<>(wordDict);
        return wordBreak1(s, wordSet);*/

        /*//2、记忆搜索
        //将列表转为哈希表
        Set<String> wordSet = new HashSet<>(wordDict);
        //创建记忆表
        Map<Integer,Boolean> map = new HashMap<>();
        return wordBreak2(s, 0, map, wordSet);*/

        //3、动态规划
        //将列表转为哈希表
        Set<String> wordSet = new HashSet<>(wordDict);
        //dp[i]表示s[i...len-1]能否拆分
        boolean[] dp = new boolean[s.length()+1];
        dp[s.length()] = true;
        //i为子串起点
        for(int i=s.length()-1;i>=0;i--){
            StringBuilder sb = new StringBuilder();
            //j为子串终点，j+1为剩余串
            for(int j=i;j<s.length();j++){
                sb.append(s.charAt(j));
                //若s[i...j]能在字典找到，判断s[j+1...len-1]能否拆分
                if(wordSet.contains(sb.toString())){
                    if(dp[j+1]){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];

    }
    //递归函数1：暴力递归
    private boolean wordBreak1(String s, Set<String> wordSet){
        if(s.equals("")) return true;
        //枚举字符串从0开始的子串，若能匹配单词，则将剩余部分递归
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i));
            if(wordSet.contains(sb.toString())){
                if(wordBreak1(s.substring(sb.length()),wordSet)) return true;
            }
        }
        return false;
    }
    //递归函数2：使用记忆表避免重复计算
    private boolean wordBreak2(String s, int begin, Map<Integer,Boolean> map, Set<String> wordSet){
        if(begin==s.length()) return true;
        //枚举字符串从0开始的子串，若能匹配单词，则将剩余部分递归
        StringBuilder sb = new StringBuilder();
        for(int i=begin;i<s.length();i++){
            //收集字符
            sb.append(s.charAt(i));
            //匹配到单词
            if(wordSet.contains(sb.toString())){
                //剩余字符串的起点
                int nextBegin = begin + sb.length();
                //查记忆表
                Boolean flag = map.get(nextBegin);
                //查不到则递归求解
                if(flag==null){
                    flag = wordBreak2(s, nextBegin, map, wordSet);
                    map.put(nextBegin,flag);
                }
                if(flag) return true;
            }
        }
        return false;
    }

    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * @param nums 数组，满足以下条件
     *               1 <= nums.length <= 20
     *               0 <= nums[i] <= 1000
     *               0 <= sum(nums[i]) <= 1000
     * @param target 目标值，满足以下条件
     *               -1000 <= target <= 100
     * @return 符合条件的表达式数目
     */
    public int findTargetSumWays(int[] nums, int target) {

        /*//1、暴力递归：时间复杂度O(2^N)
        findTargetSumWays(nums,0,0,target);
        return targetSumWays;*/

        //2、动态规划：时间复杂度O(N*Range)
        //dp[i][j]表示用nums[0...i]构造值为j的表达式数目
        //j的取值范围为[-1000,1000]，用[0,2000]表示
        int[][] dp = new int[nums.length][2001];
        dp[0][1000-nums[0]] += 1;
        dp[0][1000+nums[0]] += 1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<=2000;j++){
                if(dp[i-1][j]>0){
                    //加
                    dp[i][j+nums[i]] += dp[i-1][j];
                    //减
                    dp[i][j-nums[i]] += dp[i-1][j];
                }
            }
        }
        return dp[nums.length-1][1000+target];

    }
    //全局变量
    private int targetSumWays = 0;
    //递归函数
    private void findTargetSumWays(int[] nums, int index, int sum, int target){
        if(index==nums.length){
            if(sum==target) targetSumWays++;
            return;
        }
        //加
        findTargetSumWays(nums,index+1,sum+nums[index],target);
        //减
        findTargetSumWays(nums,index+1,sum-nums[index],target);
    }

    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 表示和的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int carry = 0;
        while(l1!=null || l2!=null){
            //按位求和
            int sum = carry;
            if(l1!=null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                sum += l2.val;
                l2 = l2.next;
            }
            //求进位
            carry = sum >= 10 ? 1 : 0;
            //保留低位
            sum %= 10;
            //构建节点
            temp.next = new ListNode(sum);
            temp = temp.next;
        }
        //可能遗漏的进位
        if(carry==1){
            temp.next = new ListNode(1);
        }
        return head.next;
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
     * 477. 汉明距离总和
     * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
     * 计算一个数组中，任意两个数之间汉明距离的总和。
     *
     * @param nums 数组  数组的长度不超过10^4
     * @return 汉明距离总和
     */
    public int totalHammingDistance(int[] nums) {

        /*//1、暴力枚举：时间复杂度O(N^2)，最大为10^8，可能会超时
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                sum += Integer.bitCount(nums[i]^nums[j]);
            }
        }
        return sum;*/

        //2、数位01计数：时间复杂度O(32N)，最大为32*10^4
        //固定一个位，统计该位1和0出现的数量，二者的乘积即为该位贡献的汉明距离
        int sum = 0;
        for(int i=0;i<=31;i++){
            int count0 = 0;
            int count1 = 0;
            for(int num : nums){
                if(((num>>i)&1)==0){
                    count0++;
                }else{
                    count1++;
                }
            }
            sum += count0 * count1;
        }
        return sum;

    }

    /**
     * 621. 任务调度器
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的 最短时间 。
     *
     * @param tasks 任务列表
     *              1 <= task.length <= 10^4
     *              tasks[i]是大写英文字母
     * @param n 相同任务的间隔时间
     *              0 <= n <= 100
     * @return 最短时间
     */
    public int leastInterval(char[] tasks, int n) {
        if(n==0) return tasks.length;
        //词频统计
        int[] freqs = new int[26];
        for(char task : tasks){
            freqs[task-'A']++;
        }
        //最高频率
        int maxFreq = 0;
        //最高频率的出现次数
        int count = 0;
        for(int freq : freqs){
            if(freq>maxFreq){
                maxFreq = freq;
                count = 1;
            }else if(freq==maxFreq){
                count += 1;
            }
        }
        //出现次数最多的字符按n间隔执行，需要的最短时间
        int maxLen = (maxFreq-1) * (n+1) + count;
        //若数组长度大于最短时间，则最短时间为数组长度
        return Math.max(tasks.length, maxLen);
    }

    /**
     * 279. 完全平方数
     * 给定正整数 n ，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * 完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * @param n 正整数  1 <= n <= 10^4
     * @return 组成n的完全平方数最少数量
     */
    public int numSquares(int n) {
        //动态规划
        //所有小于等于n的完全平方数
        List<Integer> squares = new ArrayList<>();
        for(int i=1;i*i<=n;i++){
            squares.add(i*i);
        }
        //dp[i][j]表示用squares[0...i]的完全平方数组成j的最少个数
        int[][] dp = new int[squares.size()][n+1];
        //j==0时最少个数为0
        //i==0时最少个数为j
        for(int j=1;j<=n;j++){
            dp[0][j] = j;
        }
        for(int i=1;i<squares.size();i++){
            for(int j=1;j<=n;j++){
                //不使用square[i]
                dp[i][j] = dp[i-1][j];
                //使用1个或以上square[i]
                int square = squares.get(i);
                for(int k=1; k*square<=j ; k++){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k*square]+k);
                }
            }
        }
        return dp[squares.size()-1][n];
    }

    /**
     * 739. 每日温度
     * 请根据每日气温列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * @param temperatures 气温列表
     * @return 结果列表
     */
    public int[] dailyTemperatures(int[] temperatures) {
        //单调栈(保存索引)
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[temperatures.length];
        for(int i=0; i<temperatures.length; i++){
            while( ! stack.isEmpty() && temperatures[stack.peek()]<temperatures[i]){
                int j = stack.pop();
                ans[j] = i-j;
            }
            stack.push(i);
        }
        while( ! stack.isEmpty()){
            ans[stack.pop()] = 0;
        }
        return ans;
    }

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * @param nums 数组
     * @return 重复数字
     */
    public int findDuplicate(int[] nums) {
        //一次遍历：时间复杂度O(N)
        //把nums[i]放在nums[i]-1的位置，出现值重复时可以得到重复数
        for(int i=0;i<nums.length;i++){
            int next = nums[i]-1;
            if(next==i) continue;
            if(nums[next]==nums[i]) return nums[i];
            else{
                nums[next] ^= nums[i];
                nums[i] ^= nums[next];
                nums[next] ^= nums[i];
                i--;
            }
        }
        return -1;
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
     * 560. 和为K的子数组
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * @param nums 数组(数组长度为[1, 20,000])
     * @param k 整数
     * @return 和为k的子数组个数
     */
    public int subarraySum(int[] nums, int k) {
        
        /*//1、前缀和+枚举子数组：时间复杂度O(N^2)
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            prefixSum[i] = prefixSum[i-1]+nums[i];
        }
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                int sum;
                if(i==0) sum = prefixSum[j];
                else sum = prefixSum[j]-prefixSum[i-1];
                if(sum==k) count++;
            }
        }
        return count;*/

        /*//2、枚举子数组：时间复杂度O(N^2)
        int count = 0;
        for(int i=0;i<nums.length;i++){
            int sum = 0;
            for(int j=i;j<nums.length;j++){
                sum += nums[j];
                if(sum==k) count++;
            }
        }
        return count;*/

        //3、前缀和+哈希表：时间复杂度O(N)
        int count = 0;
        //哈希表保存"前缀和->出现次数"
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        //前缀和pre[i]表示nums[0...i]的和
        int[] pre = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(i==0) pre[i] = nums[i];
            else pre[i] = pre[i-1]+nums[i];
            //哈希表中已经收集了pre[0...i-1]，和为pre[i]-k的个数，即为以i结尾的子数组符合条件的个数
            count += map.getOrDefault(pre[i]-k,0);
            //nums[0...i]可能是符合条件的子数组
            //if(pre[i]==k) count += 1;
            //pre[i]存入哈希表
            map.put(pre[i],map.getOrDefault(pre[i],0)+1);
        }
        return count;

    }

    /**
     * 5774. 使用服务器处理任务
     * 给你两个 下标从 0 开始 的整数数组 servers 和 tasks ，长度分别为 n 和 m 。servers[i] 是第 i 台服务器的 权重 ，而 tasks[j] 是处理第 j 项任务 所需要的时间（单位：秒）。
     * 你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 0 项任务在第 0 秒可以开始处理，相应地，第 j 项任务在第 j 秒可以开始处理。
     * 处理第 j 项任务时，你需要为它分配一台 权重最小 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 下标最小 的服务器。如果一台空闲服务器在第 t 秒分配到第 j 项任务，那么在 t + tasks[j] 时它将恢复空闲状态。
     * 如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 尽可能早地处理剩余任务。 如果有多项任务等待分配，则按照 下标递增 的顺序完成分配。
     * 如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
     * 构建长度为 m 的答案数组 ans ，其中 ans[j] 是第 j 项任务分配的服务器的下标。
     *
     * @param servers  服务器列表
     * @param tasks 任务列表
     * @return 分配结果
     */
    public int[] assignTasks(int[] servers, int[] tasks) {
        //使用两个小根堆，分别维护空闲服务器及工作中服务器
        PriorityQueue<int[]> idle = new PriorityQueue<>((o1, o2) -> {
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            return o1[1]-o2[1];
        });
        PriorityQueue<int[]> busy = new PriorityQueue<>(((o1, o2) -> {
            if(o1[2]==o2[2]){
                if(o1[1]==o2[1]) return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
            return o1[2]-o2[2];
        }));
        //复制服务器
        int[][] copyServers = new int[servers.length][3];
        for(int i=0;i<servers.length;i++){
            //索引
            copyServers[i][0] = i;
            //权重
            copyServers[i][1] = servers[i];
            //可用时间
            copyServers[i][2] = 0;
            //空闲服务器初始化
            idle.offer(copyServers[i]);
        }
        //当前时间
        int curTime = 0;
        //分配任务
        int[] ans = new int[tasks.length];
        for(int i=0;i<tasks.length;i++){
            curTime = Math.max(curTime,i);
            //服务器恢复空闲状态
            while( ! busy.isEmpty() && busy.peek()[2]<=curTime){
                idle.offer(busy.poll());
            }
            //若不存在空闲服务器
            if(idle.isEmpty()){
                curTime = busy.peek()[2];
                while( ! busy.isEmpty() && busy.peek()[2]<=curTime){
                    idle.offer(busy.poll());
                }
            }
            //分配服务器
            int[] server = idle.poll();
            ans[i] = server[0];
            server[2] = curTime+tasks[i];
            busy.offer(server);
        }
        return ans;
    }

    /**
     * 1878. 矩阵中最大的三个菱形和
     * 给你一个 m x n 的整数矩阵 grid 。
     * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。
     * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们全部返回。
     *
     * @param grid 网格 (1 <= grid[i][j] <= 10^5)
     * @return 最大的三个菱形和
     */
    public int[] getBiggestThree(int[][] grid) {
        //前缀和+枚举菱形
        int row = grid.length, col = grid[0].length;
        //sum1[i][j]表示grid[i][j]向左上角直到边界的和
        int[][] sum1 = new int[row][col];
        //sum2[i][j]表示grid[i][j]向右上角直到边界的和
        int[][] sum2 = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==0 || j==0){
                    sum1[i][j] = grid[i][j];
                }else{
                    sum1[i][j] = sum1[i-1][j-1] + grid[i][j];
                }
                if(i==0 || j==col-1){
                    sum2[i][j] = grid[i][j];
                }else{
                    sum2[i][j] = sum2[i-1][j+1] + grid[i][j];
                }
            }
        }
        int[] ans = new int[3];
        //枚举菱形所占正方形的左上角坐标以及边长
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int k=1;k+i<=row && k+j<=col;k+=2){
                    //求菱形和
                    int sum;
                    if(k==1) sum = grid[i][j];
                    else{
                        int midI = i+k/2, midJ = j+k/2, maxI = i+k-1, maxJ = j+k-1;
                        sum = sum2[midI][j] - (i==0 ? 0 : sum2[i-1][midJ+1])
                                + sum1[midI][maxJ] - (i==0 ? 0 : sum1[i-1][midJ-1])
                                + sum2[maxI][midJ] - (maxJ==col-1 ? 0 : sum2[midI-1][maxJ+1])
                                + sum1[maxI][midJ] - (j==0 ? 0 : sum1[midI-1][j-1])
                                - grid[midI][j] - grid[midI][maxJ] - grid[maxI][midJ] - grid[i][midJ];
                    }
                    //保存最大的三个和，不重复
                    if(sum==ans[0] || sum==ans[1] || sum==ans[2]) continue;
                    int min = 0;
                    for(int a=1;a<3;a++){
                        if(ans[a]<ans[min]){
                            min = a;
                        }
                    }
                    ans[min] = Math.max(sum,ans[min]);
                }
            }
        }
        Arrays.sort(ans);
        if(ans[1]==0) return new int[]{ans[2]};
        if(ans[0]==0) return new int[]{ans[2],ans[1]};
        return new int[]{ans[2],ans[1],ans[0]};
    }

    /**
     * 423. 从英文中重建数字
     * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
     * 注意:
     * 1、输入只包含小写英文字母。
     * 2、输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
     * 3、输入字符串的长度小于 50,000。
     *
     * @param s 英文字符串
     * @return 数字字符串
     */
    public String originalDigits(String s) {
        //字母出现次数
        int[] map = new int[26];
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)-'a']++;
        }
        //数字及其数量
        int[] num = new int[10];
        num[0] = map[25]; //z的数量确定0的数量
        num[2] = map[22]; //w的数量确定2的数量
        num[4] = map[20]; //u的数量确定4的数量
        num[6] = map[23]; //x的数量确定6的数量
        num[8] = map[6]; //g的数量确定8的数量
        num[1] = map[14] - num[0] - num[2] - num[4]; //o的数量减去0,2,4的数量确定1的数量
        num[3] = map[17] - num[0] - num[4]; //r的数量减去0,4的数量确定3的数量
        num[5] = map[5] - num[4]; //f的数量减去4的数量确定5的数量
        num[7] = map[18] - num[6]; //s的数量减去6的数量确定7的数量
        num[9] = map[8] - num[5] - num[6] - num[8]; //i的数量减去5,6,8的数量确定9的数量
        //构建数字字符串
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num.length;i++){
            if(num[i]>0){
                for(int j=0;j<num[i];j++){
                    sb.append(i);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。
     * 同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
     * 你按照如下规则进行一场游戏：
     * 1、你从第 0 天开始吃糖果。
     * 2、你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
     * 3、在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
     * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：
     * 在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；
     * 否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
     *
     * @param candiesCount 糖果数量
     * @param queries 数组[最喜欢的糖果-最喜欢的天数-每天食量]
     * @return 能否在最喜欢的那天吃到最喜欢的糖果
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        //糖果数量前缀和
        long[] preSum = new long[candiesCount.length];
        preSum[0] = candiesCount[0];
        for(int i=1;i<candiesCount.length;i++) preSum[i] = preSum[i-1]+candiesCount[i];
        //每天最低食量1，最高食量queries[i][2]
        boolean[] ans = new boolean[queries.length];
        for(int i=0;i<queries.length;i++){
            long favoriteDay = queries[i][1]+1;
            //favoriteDay天的最低累计食量
            long minCandy = favoriteDay;
            //favoriteDay天的最高累计食量
            long maxCandy = favoriteDay * queries[i][2];
            //最喜欢糖果前缀和是否在最低及最高累计食量的范围内
            ans[i] = minCandy <= preSum[queries[i][0]]
                    && maxCandy > (queries[i][0]==0 ? 0 : preSum[queries[i][0]-1]);
        }
        return ans;
    }

    /**
     * 523. 连续的子数组和
     * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     * 子数组大小 至少为 2 ，且子数组元素总和为 k 的倍数。如果存在，返回 true ；否则，返回 false 。
     *
     * @param nums 数组
     *        1 <= nums.length <= 10^5
     *        0 <= nums[i] <= 10^9
     *        0 <= sum(nums[i]) <= 2^31 - 1
     * @param k 整数
     *        1 <= k <= 2^31 - 1
     * @return 是否存在
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        /*//1、暴力枚举：时间复杂度O(N^2)，可能超时
        for(int i=0;i<nums.length-1;i++){
            int sum = nums[i];
            for(int j=i+1;j<nums.length;j++){
                sum += nums[j];
                if(sum % k == 0){
                    return true;
                }
            }
        }
        return false;*/

        //2、前缀和+哈希表：时间复杂度O(N)
        //哈希表保存<前缀和对k取模的余数，前缀和最后一个元素的索引>
        Map<Integer,Integer> map = new HashMap<>();
        //第一个元素特殊处理
        int sum = nums[0];
        map.put(sum % k,0);
        //第二个元素开始遍历
        for(int i=1;i<nums.length;i++){
            sum += nums[i];
            int mod = sum % k;
            if(mod==0) return true;
            //如果存在两个相同的余数，则这两个余数之间的区间和是k的倍数，若区间长度大于1，则是符合题意的一个子数组
            Integer index = map.get(mod);
            if(index==null) map.put(mod,i);
            else if(i-index>1) return true;
        }
        return false;
    }

    /**
     * 525. 连续数组
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     *
     * @param nums 数组
     *             1 <= nums.length <= 10^5
     *             nums[i] 不是 0 就是 1
     * @return 最长连续子数组长度
     */
    public int findMaxLength(int[] nums) {
        //前缀和+哈希表：时间复杂度O(N)
        int max = 0;
        //前缀和表示nums[0...i]中0比1多出的数量
        int pre = 0;
        //哈希表表示<前缀和，数组下标>
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) pre++;
            else pre--;
            //如果有两个前缀0比1多出的数量相同，则之间子数组0和1数量相同
            if(map.get(pre)==null) map.put(pre,i);
            else max = Math.max(max,i-map.get(pre));
        }
        return max;
    }

    /**
     * 16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * @param nums 数组
     * @param target 目标值
     * @return 最接近目标的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        //排序+双指针：时间复杂度O(N^2)
        Arrays.sort(nums);
        int ans = 0;
        int diff = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            //左右指针
            int left = i+1, right=nums.length-1;
            while(left < right){
                //三数和
                int sum = nums[i] + nums[left] + nums[right];
                //等于目标值直接返回
                if(sum==target) return sum;
                //求差值
                int abs = Math.abs(sum-target);
                if(abs<diff){
                    diff = abs;
                    ans = sum;
                }
                //指针移动
                if(sum>target){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return ans;
    }

    /**
     * 907. 子数组的最小值之和
     * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 为 arr 的每个（连续）子数组。
     * 由于答案可能很大，因此返回答案模 10^9 + 7 。
     *
     * @param arr 数组
     *            1 <= arr.length <= 3 * 10^4
     *            1 <= arr[i] <= 3 * 10^4
     * @return 子数组的最小值总和
     */
    public int sumSubarrayMins(int[] arr) {

        /*//1、暴力枚举：时间复杂度O(N^2)
        int sum = 0;
        for(int i=0;i<arr.length;i++){
            int min = Integer.MAX_VALUE;
            for(int j=i;j<arr.length;j++){
                min = Math.min(min,arr[j]);
                sum += min;
                sum %= 1_000_000_007;
            }
        }
        return sum;*/

        //2、单调栈：时间复杂度O(N)
        //栈保存元素的下标，并按照元素的值从栈底到栈顶单调递增
        Deque<Integer> s = new LinkedList<>();
        long res = 0;
        //枚举所有元素arr[j]，计算以arr[j]为最小值的子数组数量
        for (int i = 0; i <= arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] > (i == arr.length ? 0 : arr[i])) {
                //出现递减，说明栈顶为一个凸起，即左右两个相邻元素都比它小
                //所有以arr[j]为最小值的子数组在(k,i)之间，子数组数量为(i-j)*(j-k)
                int j = s.pop();
                int k = s.isEmpty() ? -1 : s.peek();
                res = (res + ((long)arr[j] * (i - j) * (j - k)) % 1000000007) % 1000000007;
            }
            s.push(i);
        }
        return (int)res;

    }

    /**
     * 474. 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *
     * @param strs 字符串数组 (1 <= strs.length <= 600)
     * @param m 0的最大个数
     * @param n 1的最大个数
     * @return 最大子集大小
     */
    public int findMaxForm(String[] strs, int m, int n) {

        //1、暴力枚举：每个字符串有取或不取两种选择，时间复杂度为O(2^k)，执行会超时

        //2、动态规划：类似于01背包问题的解题思路，时间复杂度为O(k * m * n)
        /*//dp[k][i][j]表示strs[0...k]的最多有i个0和j个1的最大子集大小
        int[][][] dp = new int[strs.length][m+1][n+1];
        for(int k=0;k<strs.length;k++){
            int num0 = countChar(strs[k],'0');
            int num1 = strs[k].length() - num0;
            for(int i=0;i<=m;i++){
                for(int j=0;j<=n;j++){
                    if(k==0) dp[k][i][j] = (i>=num0 && j>=num1 ? 1 : 0);
                    else dp[k][i][j] = Math.max(dp[k-1][i][j],
                            i>=num0 && j>=num1 ? dp[k-1][i-num0][j-num1]+1 : 0
                    );
                }
            }
        }
        return dp[strs.length-1][m][n];*/

        //3、动态规划：空间压缩(由于dp[k]只取决于dp[k-1]，更早的历史不会再用到，所以不需要保留，可以滚动覆盖更新)
        int[][] dp = new int[m+1][n+1];
        for(int k=0;k<strs.length;k++){
            int num0 = countChar(strs[k],'0');
            int num1 = strs[k].length() - num0;
            for(int i=m;i>=0;i--){
                for(int j=n;j>=0;j--){
                    if(k==0) dp[i][j] = (i>=num0 && j>=num1 ? 1 : 0);
                    else dp[i][j] = Math.max(dp[i][j],
                            i>=num0 && j>=num1 ? dp[i-num0][j-num1]+1 : 0
                    );
                }
            }
        }
        return dp[m][n];

    }
    //计算字符串中指定字符的数量
    private int countChar(String str, char c){
        int num = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == c) num++;
        }
        return num;
    }

}
