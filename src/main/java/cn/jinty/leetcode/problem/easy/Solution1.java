package cn.jinty.leetcode.problem.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * LCP 40. 心算挑战
     * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
     * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
     *
     * @param cards 卡牌数组 (1 <= cards.length <= 10^5)
     * @param cnt   选取卡牌数 (1 <= cnt <= cards.length <= 10^5)
     * @return 最大有效得分
     */
    public int maximumScore(int[] cards, int cnt) {
        // 总和为偶数：取任意个偶数牌，取偶数个奇数牌
        // 总和为最大：先将牌按奇偶划分，然后排序，先取数值大的
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        for (int card : cards) {
            if (card % 2 == 0) {
                evens.add(card);
            } else {
                odds.add(card);
            }
        }
        evens.sort(((o1, o2) -> o2 - o1));
        odds.sort(((o1, o2) -> o2 - o1));
        int res = 0, i = 0, j = 0;
        if (cnt % 2 != 0) {
            if (evens.size() == 0) {
                return res;
            }
            res += evens.get(i++);
            cnt -= 1;
        }
        while (cnt > 0) {
            if (i + 1 < evens.size() && j + 1 < odds.size()) {
                int evenSum = evens.get(i) + evens.get(i + 1);
                int oddSum = odds.get(j) + odds.get(j + 1);
                if (evenSum > oddSum) {
                    res += evenSum;
                    i += 2;
                } else {
                    res += oddSum;
                    j += 2;
                }
            } else if (i + 1 < evens.size()) {
                res += evens.get(i) + evens.get(i + 1);
                i += 2;
            } else if (j + 1 < odds.size()) {
                res += odds.get(j) + odds.get(j + 1);
                j += 2;
            } else {
                break;
            }
            cnt -= 2;
        }
        return cnt == 0 ? res : 0;
    }

    /**
     * 506. 相对名次
     * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都互不相同。
     * 运动员将根据得分决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
     * 名次第 1 的运动员获金牌 "Gold Medal" 。名次第 2 的运动员获银牌 "Silver Medal" 。名次第 3 的运动员获铜牌 "Bronze Medal" 。
     * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
     * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
     *
     * @param score 得分 (1 <= n <= 10^4 且 0 <= score[i] <= 10^6)
     * @return 名次
     */
    public String[] findRelativeRanks(int[] score) {
        // 排序：时间复杂度O(N * logN)，空间复杂度O(N)
        int n = score.length;
        if (n == 1) {
            return new String[]{"Gold Medal"};
        }
        if (n == 2) {
            return score[0] > score[1] ? new String[]{"Gold Medal", "Silver Medal"} : new String[]{"Silver Medal", "Gold Medal"};
        }
        // 按得分排序，排序后要保留原序号
        int[][] scoreAndIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            scoreAndIndex[i] = new int[]{score[i], i};
        }
        Arrays.sort(scoreAndIndex, ((o1, o2) -> o2[0] - o1[0]));
        String[] res = new String[n];
        res[scoreAndIndex[0][1]] = "Gold Medal";
        res[scoreAndIndex[1][1]] = "Silver Medal";
        res[scoreAndIndex[2][1]] = "Bronze Medal";
        for (int i = 3; i < n; i++) {
            res[scoreAndIndex[i][1]] = String.valueOf(i + 1);
        }
        return res;
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     *
     * @param nums 数组
     * @param k 次数
     * @return 数组和
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 贪心：时间复杂度O(N * logN)，空间复杂度O(1)
        // 排序
        Arrays.sort(nums);
        // 从最小负数开始翻转，不翻转正数
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        if (k == 0) {
            return sum;
        }
        // 如果 k 有剩余，说明负数都被翻转，数组全是正数，接下来可能需要翻转一个最小正数
        Arrays.sort(nums);
        return k % 2 == 0 ? sum : sum - nums[0] * 2;
    }

}