package cn.jinty.leetcode.problem.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode - 中等题
 *
 * @author jinty
 * @date 2021/9/20
 */
public class Solution2 {

    /**
     * 673. 最长递增子序列的个数
     * 给定一个未排序的整数数组，找到最长递增子序列的个数。
     *
     * @param numbers 数组
     * @return 最长递增子序列的个数
     */
    public int findNumberOfLIS(int[] numbers) {
        // 动态规划：时间复杂度O(N^2)，N为数组长度
        // dp[i][0]表示以numbers[i]为结尾的最长递增子序列长度，dp[i][1]表示该长度的子序列有多少个
        int[][] dp = new int[numbers.length][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        // 最长递增子序列的长度
        int lis = 1;
        // 枚举结尾元素
        for (int i = 1; i < numbers.length; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            // 在当前元素的前区间寻找结尾比当前元素小的最长递增子序列
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    int len = dp[j][0] + 1;
                    // 判断是否存在同样长度的最长递增子序列
                    if (len == dp[i][0]) {
                        dp[i][1] += dp[j][1];
                    } else if (len > dp[i][0]) {
                        dp[i][0] = len;
                        dp[i][1] = dp[j][1];
                    }
                }
            }
            lis = Math.max(lis, dp[i][0]);
        }
        // 计算最长递增子序列的个数
        int count = 0;
        for (int[] arr : dp) {
            if (arr[0] == lis) {
                count += arr[1];
            }
        }
        return count;
    }

    /**
     * 583. 两个字符串的删除操作
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，
     * 每步可以删除任意一个字符串中的一个字符。
     *
     * @param word1 单词1
     * @param word2 单词2
     * @return 最小步数
     */
    public int minDistance(String word1, String word2) {
        // 求最大公共子序列
        int maxCommonSubSeq = maxCommonSubSeq(word1, word2);
        return word1.length() - maxCommonSubSeq + word2.length() - maxCommonSubSeq;
    }

    // 通过动态规划求最大公共子序列的长度
    private int maxCommonSubSeq(String word1, String word2) {
        if (word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0) {
            return 0;
        }
        // dp[i][j]表示word1[0...i-1]与word2[0...j-1]的最大公共子序列的长度
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * 223. 矩形面积
     * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
     * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
     * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
     * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
     *
     * @param ax1 a左下横坐标
     * @param ay1 a左下纵坐标
     * @param ax2 a右上横坐标
     * @param ay2 a右上纵坐标
     * @param bx1 b左下横坐标
     * @param by1 b左下纵坐标
     * @param bx2 b右上横坐标
     * @param by2 b右上纵坐标
     * @return 并集面积
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 各自面积
        int area1 = (ay2 - ay1) * (ax2 - ax1);
        int area2 = (by2 - by1) * (bx2 - bx1);
        // 交集面积
        int overlap = getOverlapLength(ax1, ax2, bx1, bx2) * getOverlapLength(ay1, ay2, by1, by2);
        // 并集面积
        return area1 + area2 - overlap;
    }

    // 求同向边的重叠长度
    private int getOverlapLength(int a1, int a2, int b1, int b2) {
        // 不重叠
        if (b1 >= a2 || a1 >= b2) return 0;
        // 重叠
        return Math.min(a2, b2) - Math.max(a1, b1);
    }

    /**
     * 166. 分数到小数
     * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
     * 如果小数部分为循环小数，则将循环的部分括在括号内。
     * 如果存在多个答案，只需返回 任意一个 。
     * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
     *
     * @param numerator   被除数
     * @param denominator 除数
     * @return 小数形式的结果
     */
    public String fractionToDecimal(int numerator, int denominator) {
        // 防止溢出
        long num = numerator;
        long den = denominator;
        // 可整除
        if (num % den == 0) return String.valueOf(num / den);
        // 不可整除
        StringBuilder sb = new StringBuilder();
        // 处理负号
        if (num < 0 && den < 0) {
            num = -num;
            den = -den;
        } else if (num < 0) {
            num = -num;
            sb.append('-');
        } else if (den < 0) {
            den = -den;
            sb.append('-');
        }
        // 整数部分
        sb.append(num / den);
        // 小数点
        sb.append('.');
        // 小数部分：借位相除，根据余数是否重复判断循环小数
        Map<Long, Integer> seen = new HashMap<>();
        num = (num % den) * 10;
        seen.put(num, sb.length());
        while (num != 0) {
            sb.append(num / den);
            num = (num % den) * 10;
            if (seen.containsKey(num)) {
                int index = seen.get(num);
                return sb.substring(0, index) + "(" + sb.substring(index, sb.length()) + ")";
            }
            seen.put(num, sb.length());
        }
        return sb.toString();
    }

    /**
     * 38. 外观数列
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * countAndSay(1) = "1"
     * countAndSay(2) = "11"
     * countAndSay(3) = "21"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     *
     * @param n 正整数
     * @return 第 n 项
     */
    public String countAndSay(int n) {
        // 递归
        if (n == 1) return "1";
        StringBuilder ans = new StringBuilder();
        String pre = countAndSay(n - 1);
        char c = pre.charAt(0);
        int count = 1;
        for (int i = 1; i < pre.length(); i++) {
            if (pre.charAt(i) == c) {
                count++;
            } else {
                ans.append(count).append(c);
                c = pre.charAt(i);
                count = 1;
            }
        }
        ans.append(count).append(c);
        return ans.toString();
    }

    /**
     * 229. 求众数 II
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 n/3 次的元素。
     *
     * @param numbers 整数数组
     * @return 众数
     */
    public List<Integer> majorityElement(int[] numbers) {
        // 1、哈希表：时间复杂度O(N)，空间复杂度O(N)
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : numbers) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        int limit = numbers.length / 3;
        for (Integer key : map.keySet()) {
            if (map.get(key) > limit) {
                ans.add(key);
            }
        }
        return ans;
    }

}
