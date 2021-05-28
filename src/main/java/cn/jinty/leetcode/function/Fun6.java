package cn.jinty.leetcode.function;

import cn.jinty.leetcode.entity.ListNode;

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

}
