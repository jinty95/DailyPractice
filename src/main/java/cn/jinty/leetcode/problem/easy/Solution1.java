package cn.jinty.leetcode.problem.easy;

/**
 * LeetCode - 简单题
 *
 * @author Jinty
 * @date 2021/11/23
 **/
@SuppressWarnings("unused")
public class Solution1 {

    /**
     * 859. 亲密字符串
     * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
     * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
     *
     * @param s    原字符串 (1 <= s.length <= 2 * 10^4 且 s 由小写英文字母组成)
     * @param goal 目标字符串 (1 <= goal.length <= 2 * 10^4 且 goal 由小写英文字母组成)
     * @return 是否为亲密字符串
     */
    public boolean buddyStrings(String s, String goal) {
        /*// 1、暴力搜索：时间复杂度O(N^3)
        if (s == null || goal == null || s.length() < 2 || goal.length() < 2 || s.length() != goal.length()) {
            return false;
        }
        char[] a = s.toCharArray();
        char[] b = goal.toCharArray();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                swap(a, i, j);
                if (equals(a, b)) {
                    return true;
                }
                swap(a, i, j);
            }
        }
        return false;*/

        // 2、分类讨论：时间复杂度O(N)
        if (s == null || goal == null || s.length() < 2 || goal.length() < 2 || s.length() != goal.length()) {
            return false;
        }
        // 字符串相等，判断s中是否有重复字符
        if (s.equals(goal)) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if (count[idx] > 0) {
                    return true;
                }
                count[idx] = 1;
            }
            return false;
        }
        // 字符串不相等，判断是否刚好存在两个不同的位置，且字符刚好相反
        int first = -1, second = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                } else {
                    return false;
                }
            }
        }
        return first != -1 && second != -1
                && s.charAt(first) == goal.charAt(second)
                && s.charAt(second) == goal.charAt(first);
    }

    // 交换元素
    private void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    // 判断字符数组是否相等
    private boolean equals(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

}
