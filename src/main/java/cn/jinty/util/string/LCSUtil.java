package cn.jinty.util.string;

import cn.jinty.util.collection.ArrayUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * LCS - 工具类
 * LCS是以下两个词语的缩写
 * 1、LongestCommonSubsequence (最长公共子序列)
 * 2、LongestCommonSubstring (最长公共子串)
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class LCSUtil {

    private LCSUtil() {
    }

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
        String common = LCSUtil.longestCommonSubsequence(s1, s2);
        return new String[]{removeSubsequence(s1, common), removeSubsequence(s2, common)};
    }

}
