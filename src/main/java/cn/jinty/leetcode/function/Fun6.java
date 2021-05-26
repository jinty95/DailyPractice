package cn.jinty.leetcode.function;

import cn.jinty.util.ArrayUtil;

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

}
