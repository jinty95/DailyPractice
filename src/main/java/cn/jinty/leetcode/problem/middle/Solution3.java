package cn.jinty.leetcode.problem.middle;

import cn.jinty.struct.tree.TreeNode;

import java.util.*;

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

    /**
     * 969. 煎饼排序
     * 给你一个整数数组 arr ，请使用煎饼翻转完成对数组的排序。
     * 一次煎饼翻转的执行过程如下：选择一个整数 k ，1 <= k <= arr.length ，反转子数组 arr[0...k-1]（下标从 0 开始）
     * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
     * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
     *
     * @param arr 数组 (1 <= arr.length <= 100) (arr 是从 1 到 arr.length 整数的一个排列)
     * @return k值序列
     */
    public List<Integer> pancakeSort(int[] arr) {
        // 模拟：时间复杂度O(n^2)，空间复杂度O(n)
        List<Integer> res = new ArrayList<>();
        // 标识 1~n 在原数组中的下标
        int n = arr.length;
        int[] indexes = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            indexes[arr[i]] = i;
        }
        // 每次都找最大值，通过两次翻转(先往前再往后)，将最大值移到最后，重复这个过程直到全体有序
        for (int a = n; a > 0; a--) {
            if (indexes[a] != a - 1) {
                if (indexes[a] != 0) {
                    res.add(indexes[a] + 1);
                    reverse(arr, indexes, 0, indexes[a]);
                }
                res.add(a);
                reverse(arr, indexes, 0, a - 1);
            }
        }
        return res;
    }

    private void reverse(int[] arr, int[] indexes, int i, int j) {
        while (i < j) {
            indexes[arr[i]] = j;
            indexes[arr[j]] = i;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    /**
     * 537. 复数乘法
     * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
     * 实部 是一个整数，取值范围是 [-100, 100]
     * 虚部 也是一个整数，取值范围是 [-100, 100]
     * i2 == -1
     * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
     *
     * @param num1 复数1
     * @param num2 复数2
     * @return 乘积
     */
    public String complexNumberMultiply(String num1, String num2) {
        int[] arr1 = splitComplexNumber(num1);
        int[] arr2 = splitComplexNumber(num2);
        int a = arr1[0] * arr2[0] - arr1[1] * arr2[1];
        int b = arr1[0] * arr2[1] + arr1[1] * arr2[0];
        return a + "+" + b + "i";
    }

    // 将复数拆成实部和虚部
    private int[] splitComplexNumber(String num) {
        int[] res = new int[2];
        Boolean negative1 = false, negative2 = null;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c == 'i') {
                break;
            }
            if (c == '+') {
                negative2 = false;
                continue;
            }
            if (c == '-') {
                if (i == 0) {
                    negative1 = true;
                } else {
                    negative2 = true;
                }
                continue;
            }
            if (negative2 == null) {
                res[0] = res[0] * 10 + (c - '0');
            } else {
                res[1] = res[1] * 10 + (c - '0');
            }
        }
        res[0] = negative1 ? -res[0] : res[0];
        res[1] = negative2 ? -res[1] : res[1];
        return res;
    }

    /**
     * 2055. 蜡烛之间的盘子
     * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支蜡烛。
     * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [left, right] 表示 子字符串 s[left...right] （包含左右端点的字符）。
     * 对于每个查询，你需要找到子字符串中在两支蜡烛之间的盘子的数目。如果一个盘子在子字符串中左边和右边都至少有一支蜡烛，那么这个盘子满足在两支蜡烛之间。
     * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 。
     * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
     *
     * @param s       字符串 (3 <= s.length <= 10^5)
     * @param queries 查询 (1 <= queries.length <= 10^5)
     * @return 结果
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        // 1、前缀和：时间复杂度O(N + Q)，空间复杂度O(N)
        // preSum[i]保存s[0...i]中盘子的数量
        int[] preSum = new int[s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
            preSum[i] = count;
        }
        // lefts[i]保存s[i]左侧最近的'|'的位置，rights[i]保存s[i]右侧最近的'|'的位置
        int[] lefts = new int[s.length()];
        int[] rights = new int[s.length()];
        int tmp = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                tmp = i;
            }
            lefts[i] = tmp;
        }
        tmp = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                tmp = i;
            }
            rights[i] = tmp;
        }
        // 针对一个查询范围，先界定两侧的蜡烛位置，然后求中部的盘子总和
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = rights[queries[i][0]];
            int right = lefts[queries[i][1]];
            if (left != -1 && right != -1 && left < right - 1) {
                result[i] = preSum[right] - preSum[left];
            }
        }
        return result;
    }

    /**
     * 337. 打家劫舍 III
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
     * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。给定二叉树的root，返回在不触动警报的情况下，小偷能够盗取的最高金额。
     *
     * @param root 二叉树根节点 (树的节点数N在[1, 10^4]范围内)
     * @return 在不触动警报的情况下，小偷能够盗取的最高金额
     */
    public int rob(TreeNode root) {
        /*// 1、先序遍历：时间复杂度O(N^2)，空间复杂度O(logN)
        // 设二叉树的高度为K，则时间复杂度为O(4^K)，等价于O((2^K)^2)，等价于O(N^2)
        return preOrderRob(root, true);*/

        // 2、后序遍历：时间复杂度O(N)，空间复杂度O(N)
        Map<TreeNode, Integer> rob = new HashMap<>();
        Map<TreeNode, Integer> notRob = new HashMap<>();
        postOrderRob(root, rob, notRob);
        return Math.max(rob.get(root), notRob.get(root));
    }

    // 先序遍历
    private int preOrderRob(TreeNode root, boolean canRob) {
        if (root == null) {
            return 0;
        }
        // 当前节点可以偷，则分为偷或不偷，取其中金额更高的
        if (canRob) {
            int rob = root.val + preOrderRob(root.left, false) + preOrderRob(root.right, false);
            int notRob = preOrderRob(root.left, true) + preOrderRob(root.right, true);
            return Math.max(rob, notRob);
        }
        // 当前节点不可以偷
        return preOrderRob(root.left, true) + preOrderRob(root.right, true);
    }

    // 后序遍历
    private void postOrderRob(TreeNode root, Map<TreeNode, Integer> rob, Map<TreeNode, Integer> notRob) {
        if (root == null) {
            return;
        }
        postOrderRob(root.left, rob, notRob);
        postOrderRob(root.right, rob, notRob);
        rob.put(root, root.val + notRob.getOrDefault(root.left, 0) + notRob.getOrDefault(root.right, 0));
        notRob.put(root, Math.max(rob.getOrDefault(root.left, 0), notRob.getOrDefault(root.left, 0)) +
                Math.max(rob.getOrDefault(root.right, 0), notRob.getOrDefault(root.right, 0)));
    }

}
