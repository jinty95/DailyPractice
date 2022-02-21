package cn.jinty.leetcode.problem.middle;

/**
 * LeetCode - 中等题
 *
 * @author Jinty
 * @date 2022/2/21
 **/
public class Solution3 {

    /**
     * 838. 推多米诺
     * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
     * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
     * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
     * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
     * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
     * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
     * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
     * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
     * 返回表示最终状态的字符串。
     *
     * @param dominoes 多米诺骨牌 (1 <= 长度 <= 10^5)
     * @return 最终状态
     */
    public String pushDominoes(String dominoes) {
        // 1、双指针：时间复杂度O(N)，空间复杂度O(1)
        // R与L => 直接保持
        // . => 搜索一段连续的.，然后判断其边界的左右分别是什么字符，就可以确定中间所有.的状态
        char[] arr = dominoes.toCharArray();
        int i = 0, n = dominoes.length();
        while (i < n) {
            if (arr[i] != '.') {
                i++;
                continue;
            }
            int j = i;
            while (j < n && arr[j] == '.') {
                j++;
            }
            char left = i == 0 ? 'L' : arr[i - 1];
            char right = j == n ? 'R' : arr[j];
            if (left == right) {
                while (i < j) {
                    arr[i++] = left;
                }
            } else if (left == 'R' && right == 'L') {
                int k = j - 1;
                while (i < k) {
                    arr[i++] = 'R';
                    arr[k--] = 'L';
                }
            }
            i = j;
        }
        return new String(arr);
    }

}
