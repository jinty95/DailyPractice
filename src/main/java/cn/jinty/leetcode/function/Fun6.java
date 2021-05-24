package cn.jinty.leetcode.function;

import java.util.Arrays;

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

}
