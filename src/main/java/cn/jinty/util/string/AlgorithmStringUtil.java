package cn.jinty.util.string;

import cn.jinty.util.collection.ArrayUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 算法 - 字符串 - 工具类
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class AlgorithmStringUtil {

    private AlgorithmStringUtil() {
    }

    /*
     * LCS是以下两个词语的缩写
     * 1、LongestCommonSubsequence (最长公共子序列)
     * 2、LongestCommonSubstring (最长公共子串)
     */

    /**
     * 求两个字符串的最长公共子串
     * (存在多个时，返回最早出现的一个)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子串
     */
    public static String longestCommonSubstring(String s1, String s2) {
        if (StringUtil.isEmpty(s1) || StringUtil.isEmpty(s2)) {
            return StringUtil.EMPTY;
        }
        int[][] dp = getDpArrayForLongestCommonSubstring(s1, s2);
        int max = ArrayUtil.getMax(dp);
        int[] index = ArrayUtil.indexOf(dp, max);
        assert index != null;
        return s1.substring(index[0] - max, index[0]);
    }

    /**
     * 求两个字符串的最长公共子串的长度
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子串的长度
     */
    public static int longestCommonSubstringLength(String s1, String s2) {
        if (StringUtil.isEmpty(s1) || StringUtil.isEmpty(s2)) {
            return 0;
        }
        int[][] dp = getDpArrayForLongestCommonSubstring(s1, s2);
        return ArrayUtil.getMax(dp);
    }

    /**
     * 获取动态规划数组 (用于求两个字符串的最长公共子串的长度)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 动态规划数组
     */
    private static int[][] getDpArrayForLongestCommonSubstring(String s1, String s2) {
        // 动态规划
        // dp[i][j]表示s1[0...i-1]与s2[0...j-1]的包含最后一个字符的最长公共子串的长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        return dp;
    }

    /**
     * 求两个字符串的最长公共子序列
     * (满足条件的子序列一定只有一个，不可能存在两个字符不同的最长公共子序列)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子序列
     */
    public static String longestCommonSubsequence(String s1, String s2) {
        if (StringUtil.isEmpty(s1) || StringUtil.isEmpty(s2)) {
            return StringUtil.EMPTY;
        }
        int[][] dp = getDpArrayForLongestCommonSubsequence(s1, s2);
        int max = dp[s1.length()][s2.length()];
        char[] res = new char[max];
        int idx1 = s1.length();
        int idx2 = s2.length();
        while (max > 0) {
            if (idx2 > 0 && dp[idx1][idx2] == dp[idx1][idx2 - 1]) {
                idx2--;
            } else if (idx1 > 0 && dp[idx1][idx2] == dp[idx1 - 1][idx2]) {
                idx1--;
            } else {
                res[--max] = s1.charAt(idx1 - 1);
                idx1--;
                idx2--;
            }
        }
        return new String(res);
    }

    /**
     * 求两个字符串的最长公共子序列的长度
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子序列的长度
     */
    public static int longestCommonSubsequenceLength(String s1, String s2) {
        if (StringUtil.isEmpty(s1) || StringUtil.isEmpty(s2)) {
            return 0;
        }
        int[][] dp = getDpArrayForLongestCommonSubsequence(s1, s2);
        return dp[s1.length()][s2.length()];
    }

    /**
     * 获取动态规划数组 (用于求两个字符串的最长公共子序列的长度)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 动态规划数组
     */
    private static int[][] getDpArrayForLongestCommonSubsequence(String s1, String s2) {
        // 动态规划
        // dp[i][j]表示s1[0...i-1]与s2[0...j-1]的最大公共子序列的长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp;
    }

    /**
     * 从字符串中移除掉一个给定的子序列
     *
     * @param s           字符串 (长度 n)
     * @param subsequence 子序列 (长度 m)
     * @return 移除后的字符串 (长度 n-m)
     */
    public static String removeSubsequence(String s, String subsequence) {
        if (StringUtil.isEmpty(s)) {
            return StringUtil.EMPTY;
        }
        if (StringUtil.isEmpty(subsequence)) {
            return s;
        }
        int start = 0;
        Set<Integer> removeIdx = new HashSet<>();
        for (char sub : subsequence.toCharArray()) {
            int idx = s.indexOf(sub, start);
            removeIdx.add(idx);
            start = idx + 1;
        }
        char[] res = new char[s.length() - subsequence.length()];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (removeIdx.contains(i)) {
                continue;
            }
            res[j++] = s.charAt(i);
        }
        return new String(res);
    }

    /**
     * 获取两个字符串的差异部分
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 分别去除共同部分留下的差异字符串
     */
    public static String[] diff(String s1, String s2) {
        if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
            return new String[]{StringUtil.EMPTY, StringUtil.EMPTY};
        }
        if (StringUtil.isEmpty(s1)) {
            return new String[]{StringUtil.EMPTY, s2};
        }
        if (StringUtil.isEmpty(s2)) {
            return new String[]{s1, StringUtil.EMPTY};
        }
        String common = AlgorithmStringUtil.longestCommonSubsequence(s1, s2);
        return new String[]{removeSubsequence(s1, common), removeSubsequence(s2, common)};
    }

    /**
     * 求两个字符串的最小编辑距离
     * <p>
     * 可以对字符串进行如下三种操作，每种操作开销都为1，使其变成另一个字符串：
     * 1、插入一个字符 2、删除一个字符 3、替换一个字符
     * <p>
     * 可用于相似判断场景，存在一个字符串集合，给定一个字符串，求其与集合中各个字符串的最小编辑距离，距离最小即为最相似
     * 比如git命令有一个固定的命令集合，当用户输入了错误命令时，可以检测与哪个正确命令最相似，提醒用户可能想要输入的是什么
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最小编辑距离（最小0(什么都不做)、最大len1+len2(删除字符串1，添加字符串2)）
     */
    public static int minDistance(String s1, String s2) {
        // 动态规划
        char[] w1 = s1.toCharArray(), w2 = s2.toCharArray();
        // dp[i][j]表示w1[0...i-1]与w2[0...j-1]的最短编辑距离
        int[][] dp = new int[w1.length + 1][w2.length + 1];
        // 边界
        dp[0][0] = 0;
        for (int i = 1; i <= w1.length; i++) dp[i][0] = i;
        for (int j = 1; j <= w2.length; j++) dp[0][j] = j;
        // 递推
        for (int i = 1; i <= w1.length; i++) {
            for (int j = 1; j <= w2.length; j++) {
                // 最后字符相同，不需要编辑
                if (w1[i - 1] == w2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
                    // 最后字符不同，枚举增、删、改，取最小值
                else dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
        return dp[w1.length][w2.length];
    }

}
