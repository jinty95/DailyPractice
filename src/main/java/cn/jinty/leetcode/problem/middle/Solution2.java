package cn.jinty.leetcode.problem.middle;

import cn.jinty.struct.tree.TreeNode;
import cn.jinty.struct.tree.Trie;

import java.util.*;

/**
 * LeetCode - 中等题
 *
 * @author Jinty
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
        int lcs = longestCommonSubsequence(word1, word2);
        return word1.length() - lcs + word2.length() - lcs;
    }

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列，返回 0。
     * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 两个字符串的公共子序列是这两个字符串所共同拥有的子序列。
     *
     * @param text1 字符串
     * @param text2 字符串
     * @return 最长公共子序列的长度
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // 动态规划 ：时间复杂度O(MN)，空间复杂度O(MN)
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        // dp[i][j]表示text1[0...i-1]与text2[0...j-1]的最大公共子序列的长度
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * @param nums 数组
     * @return 最长递增子序列长度
     */
    public int lengthOfLIS(int[] nums) {
        /*// 1、动态规划 ：时间复杂度O(N^2)，空间复杂度O(N)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
        }
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] > maxLen) maxLen = dp[i];
        }
        return maxLen;*/

        // 2、贪心 + 二分查找 ：时间复杂度O(NlogN)，空间复杂度O(N)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // d[i]表示长度为i的递增子序列的末尾元素的最小值，d[i]关于i单调递增
        int[] d = new int[nums.length + 1];
        int len = 1;
        d[len] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (d[len] < nums[i]) {
                d[++len] = nums[i];
            } else {
                int left = 1, right = len, pos = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
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

    /**
     * 211. 添加与搜索单词 - 数据结构设计
     * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     * 搜索时 word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     */
    public static class WordDictionary {

        private final Trie trie;

        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return trie.searchWithPoint(word);
        }

    }

    /**
     * 638. 大礼包
     * 在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
     * 给你一个整数数组 prices 表示物品价格，其中 prices[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。
     * 还有一个数组 specials 表示大礼包，specials[i] 的长度为 n + 1 ，其中 specials[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 specials[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
     * 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。
     *
     * @param prices   价格
     * @param specials 礼包
     * @param needs    需求
     * @return 最低价格
     */
    public int shoppingOffers(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs) {
        // 不买礼包时的总价格
        int sum = 0;
        for (int i = 0; i < needs.size(); i++) {
            sum += needs.get(i) * prices.get(i);
        }
        // 去掉不可购买的礼包
        specials.removeIf(special -> !canBuySpecial(special, needs, prices));
        if (specials.size() == 0) {
            return sum;
        }
        // 剩余礼包，枚举每个礼包买零个或多个
        minPrice = sum;
        backtrack(prices, specials, needs, 0, sum);
        return minPrice;
    }

    // 最低价格
    private int minPrice;

    // 回溯算法
    private void backtrack(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs, int idx, int sum) {
        if (idx == specials.size()) {
            return;
        }
        for (int i = idx; i < specials.size(); i++) {
            List<Integer> special = specials.get(i);
            if (canBuySpecial(special, needs, prices)) {
                // 总价减去优惠额度，并占用购物数量
                int origin = 0;
                for (int j = 0; j < needs.size(); j++) {
                    origin += prices.get(j) * special.get(j);
                    needs.set(j, needs.get(j) - special.get(j));
                }
                int discount = origin - special.get(needs.size());
                minPrice = Math.min(minPrice, sum - discount);
                backtrack(prices, specials, needs, i, sum - discount);
                // 释放购物数量
                for (int j = 0; j < needs.size(); j++) {
                    needs.set(j, needs.get(j) + special.get(j));
                }
            }
        }
    }

    // 礼包是否可购买：购物数量不超，且价格比原来更低
    private boolean canBuySpecial(List<Integer> special, List<Integer> needs, List<Integer> prices) {
        int origin = 0;
        for (int i = 0; i < needs.size(); i++) {
            if (special.get(i) > needs.get(i)) {
                return false;
            }
            origin += special.get(i) * prices.get(i);
        }
        return special.get(special.size() - 1) < origin;
    }

    /**
     * 240. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix 矩阵
     * @param target 目标
     * @return 是否存在
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        /*// 1、二分查找：时间复杂度O(M * logN)
        int row = matrix.length, col = matrix[0].length;
        // 左上最小，右下最大
        if (target < matrix[0][0] && target > matrix[row - 1][col - 1]) {
            return false;
        }
        // 逐行进行二分查找
        for (int[] arr : matrix) {
            if (target < arr[0]) return false;
            if (binarySearch(arr, target)) {
                return true;
            }
        }
        return false;*/

        // 2、Z字形查找：时间复杂度O(M + N)
        int row = matrix.length, col = matrix[0].length;
        // 从右上往左下查找，每次查找可以去除一行或一列
        int i = 0, j = col - 1;
        while (i < row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    // 二分查找
    @SuppressWarnings("unused")
    private boolean binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 869. 重新排序得到 2 的幂
     * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
     * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
     *
     * @param n 正整数
     * @return 能否重排得到 2 的幂
     */
    public boolean reorderedPowerOf2(int n) {
        // 根据所有 2 的幂构建哈希表
        Set<String> powerOf2s = new HashSet<>();
        int num = 1;
        powerOf2s.add(String.valueOf(num));
        for (int i = 0; i < 30; i++) {
            num *= 2;
            powerOf2s.add(numberToStringAsc(num));
        }
        // 在哈希表中检索 n
        return powerOf2s.contains(numberToStringAsc(n));
    }

    // 将数字转为字符串并升序
    private String numberToStringAsc(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    /**
     * 260. 只出现一次的数字 III
     * 给定一个整数数组 numbers，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
     * 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     *
     * @param numbers 数组
     * @return 只出现一次的两个元素
     */
    public int[] singleNumber(int[] numbers) {
        /*// 1、计数：时间复杂度O(N)，空间复杂度O(N)
        if (numbers.length == 2) return numbers;
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : numbers) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        int[] ans = new int[2];
        int i = 0;
        for (int number : map.keySet()) {
            if (map.get(number) == 1) {
                ans[i++] = number;
            }
        }
        return ans;*/

        //2、异或：时间复杂度O(N)，空间复杂度O(1)
        // 设只出现一次的两个数为 x1 和 x2，则 xor = x1 ^ x2
        int xor = 0;
        for (int number : numbers) {
            xor ^= number;
        }
        // 求 xor 的最低位 1
        int lowBit = (xor & -xor);
        // 所有数与最低位 1 相与，根据是否得 0 划分两组，组内异或，剩下出现一次的数
        int[] ans = new int[2];
        for (int number : numbers) {
            if ((number & lowBit) == 0) {
                ans[0] ^= number;
            } else {
                ans[1] ^= number;
            }
        }
        return ans;
    }

    /**
     * 299. 猜数字游戏
     * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
     * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
     * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls", 公牛），
     * 有多少位属于数字猜对了但是位置不对（称为 "Cows", 奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
     * 给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。
     * 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。
     * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
     *
     * @param secret 秘密数字
     * @param guess  猜测数字 (secret.length == guess.length)
     * @return 结果
     */
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        // 剩余秘密数字：数字 -> 次数
        int[] remainSecret = new int[10];
        // 猜测数字是否为公牛
        boolean[] isBullGuess = new boolean[guess.length()];
        // 求公牛数量
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            if (c == guess.charAt(i)) {
                bulls++;
                isBullGuess[i] = true;
            } else {
                remainSecret[c - '0']++;
            }
        }
        // 求母牛数量
        for (int i = 0; i < guess.length(); i++) {
            if (!isBullGuess[i]) {
                char c = guess.charAt(i);
                int num = c - '0';
                if (remainSecret[num] > 0) {
                    cows++;
                    remainSecret[num]--;
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

    /**
     * 375. 猜数字大小 II
     * 我们正在玩一个猜数游戏，游戏规则如下：
     * 我从 1 到 n 之间选择一个数字。你来猜我选了哪个数字。如果你猜到正确的数字，就会 赢得游戏 。
     * 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
     * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。
     * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
     *
     * @param n 数字 (1 <= n <= 200)
     * @return 确保获胜的最小现金数
     */
    public int getMoneyAmount(int n) {
        // 动态规划：时间复杂度O(n^3)，空间复杂度O(n^2)
        // 定义：dp[i][j]表示在[i + 1 ... j + 1]范围内保证获胜的最小现金数
        // 递推：dp[i][j] = Min(k + 1 + Max(dp[i][k - 1], dp[k + 1][j]) (i <= k <= j)
        int[][] dp = new int[n][n];
        // 枚举区间长度
        for (int len = 1; len < n; len++) {
            // 枚举区间起点
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][i + len] = Integer.MAX_VALUE;
                // 枚举区间内选中数字
                for (int k = i; k <= j; k++) {
                    dp[i][i + len] = Math.min(
                            dp[i][i + len],
                            k + 1 + Math.max(k == i ? 0 : dp[i][k - 1], k == j ? 0 : dp[k + 1][j])
                    );
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 319. 灯泡开关
     * 初始时有 n 个灯泡处于关闭状态。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
     * 找出并返回 n 轮后有多少个亮着的灯泡。
     *
     * @param n 灯泡数量 (0 <= n <= 10^9)
     * @return 亮灯数量
     */
    public int bulbSwitch(int n) {

        /*// 1、暴力搜索：时间复杂度O(n^2)
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int switchCount = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    switchCount++;
                }
            }
            if (switchCount % 2 != 0) {
                count++;
            }
        }
        return count;*/

        // 2、数学分析：时间复杂度O(1)
        // 对于第 k 个灯泡，它被切换的次数恰好就是 k 的约数个数。对于 k 而言，如果它有约数 x，那么一定有约数 k/x。
        // 因此当 x^2 = k 时，约数都是成对出现的。这就说明，只有当 k 是完全平方数时，它才会有奇数个约数，否则一定有偶数个约数。
        // 所以题目转变为求 1...n 的完全平方数数量，答案即为 n^(1/2)
        return (int) Math.sqrt(n);

    }

    /**
     * 318. 最大单词长度乘积
     * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
     * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
     *
     * @param words 单词数组
     * @return 最大单词长度乘积
     */
    public int maxProduct(String[] words) {
        // 1、暴力搜索 + 剪枝 ：时间复杂度O(n^2)
        int max = 0;
        // 按长度倒序
        Arrays.sort(words, ((o1, o2) -> o2.length() - o1.length()));
        // 两层遍历
        for (int i = 0; i < words.length; i++) {
            boolean[] letter = new boolean[26];
            for (char c : words[i].toCharArray()) {
                letter[c - 'a'] = true;
            }
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                // 剪枝：当前单词长度乘积比 max 小，后续的单词肯定更小
                int product = words[i].length() * words[j].length();
                if (max > product) {
                    break;
                }
                // 判断是否不含公共字母
                boolean flag = true;
                for (char c : words[j].toCharArray()) {
                    if (letter[c - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    max = product;
                    break;
                }
            }
        }
        return max;
    }

    /**
     * 397. 整数替换
     * 给定一个正整数 n ，你可以做如下操作：
     * 如果 n 是偶数，则用 n / 2 替换 n 。
     * 如果 n 是奇数，则可以用 n + 1 或 n - 1替换 n 。
     * n 变为 1 所需的最小替换次数是多少？
     *
     * @param n 正整数 (1 <= n <= 2^31 - 1)
     * @return n 变为 1 所需的最小替换次数
     */
    public int integerReplacement(int n) {
        // 最小正整数：不需处理
        if (n == 1) {
            return 0;
        }
        // 最大正整数：1次加1，31次除2
        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        // 偶数：除2
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        }
        // 奇数：枚举加1或减1
        return 1 + Math.min(integerReplacement(n + 1), integerReplacement(n - 1));
    }

    /**
     * 1410. HTML 实体解析器
     * HTML 实体解析器 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
     * HTML 里这些特殊字符和它们对应的字符实体包括：
     * 双引号：字符实体为 &quot; ，对应的字符是 " 。
     * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
     * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
     * 大于号：字符实体为 &gt; ，对应的字符是 > 。
     * 小于号：字符实体为 &lt; ，对应的字符是 < 。
     * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
     * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
     *
     * @param text 文本
     * @return 解析结果
     */
    public String entityParser(String text) {
        /*// 1、使用String的replace方法
        text = text.replace("&quot;", "\"");
        text = text.replace("&apos;", "'");
        text = text.replace("&gt;", ">");
        text = text.replace("&lt;", "<");
        text = text.replace("&frasl;", "/");
        // 这个&会干扰解析，所以最后处理
        text = text.replace("&amp;", "&");
        return text;*/

        // 2、遍历检索
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != '&') {
                res.append(c);
            } else {
                if (i + 3 < text.length() && text.startsWith("&gt;", i)) {
                    res.append('>');
                    i += 3;
                } else if (i + 3 < text.length() && text.startsWith("&lt;", i)) {
                    res.append('<');
                    i += 3;
                } else if (i + 4 < text.length() && text.startsWith("&amp;", i)) {
                    res.append('&');
                    i += 4;
                } else if (i + 5 < text.length() && text.startsWith("&quot;", i)) {
                    res.append('\"');
                    i += 5;
                } else if (i + 5 < text.length() && text.startsWith("&apos;", i)) {
                    res.append('\'');
                    i += 5;
                } else if (i + 6 < text.length() && text.startsWith("&frasl;", i)) {
                    res.append('/');
                    i += 6;
                } else {
                    res.append(c);
                }
            }
        }
        return res.toString();
    }

    /**
     * 1305. 两棵二叉搜索树中的所有元素
     * 给你 root1 和 root2 这两棵二叉搜索树。
     * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
     *
     * @param root1 二叉树1
     * @param root2 二叉树2
     * @return 升序排序的所有整数
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // 分别进行中序遍历
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);
        // 两个有序表合并
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                list.add(list1.get(i++));
            } else {
                list.add(list2.get(j++));
            }
        }
        while (i < list1.size()) {
            list.add(list1.get(i++));
        }
        while (j < list2.size()) {
            list.add(list2.get(j++));
        }
        return list;
    }

    // 中序遍历
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 1110. 删点成林
     * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
     * 如果节点值在 delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
     * 返回森林中的每棵树。你可以按任意顺序组织答案。
     *
     * @param root   二叉树
     * @param delete 需要删除的节点
     * @return 森林
     */
    public List<TreeNode> delNodes(TreeNode root, int[] delete) {
        // 构建节点值与父节点的映射
        Map<Integer, TreeNode> map = new HashMap<>();
        nodeToParent(root, map);
        // 使用哈希表收集二叉树
        Set<TreeNode> set = new HashSet<>();
        set.add(root);
        // 每删除一个节点，将它与父节点断开连接，同时它的两个子节点成为两棵新的二叉树
        for (int del : delete) {
            TreeNode parent = map.get(del);
            TreeNode delNode;
            // 只有根节点没有父节点
            if (parent == null) {
                delNode = root;
            } else if (parent.left != null && parent.left.val == del) {
                delNode = parent.left;
                parent.left = null;
            } else {
                delNode = parent.right;
                parent.right = null;
            }
            set.remove(delNode);
            if (delNode.left != null) {
                set.add(delNode.left);
            }
            if (delNode.right != null) {
                set.add(delNode.right);
            }
        }
        return new ArrayList<>(set);
    }

    // 构建节点值与父节点的映射
    private void nodeToParent(TreeNode root, Map<Integer, TreeNode> map) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            map.put(root.left.val, root);
            nodeToParent(root.left, map);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            nodeToParent(root.right, map);
        }
    }

    /**
     * 763. 划分字母区间
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 返回一个表示每个字符串片段的长度的列表。
     *
     * @param s 字符串 (只包含小写字母 'a' 到 'z')
     * @return 划分的各片段长度
     */
    public List<Integer> partitionLabels(String s) {
        /*// 1、合并区间
        // 记录每个字母的最左和最右位置
        int[][] locations = new int[26][];
        for (int i = 0; i < s.length(); i++) {
            int k = s.charAt(i) - 'a';
            if (locations[k] == null) {
                locations[k] = new int[]{i, i};
            } else {
                locations[k][1] = i;
            }
        }
        // 合并交叉区间，最后剩下的各区间长度即为答案
        List<int[]> locationList = Arrays.stream(locations)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(o -> o[0]))
                .collect(Collectors.toList());
        Deque<int[]> deque = new LinkedList<>();
        for (int[] location : locationList) {
            if (deque.isEmpty()) {
                deque.offerLast(location);
            } else {
                if (deque.peekLast()[1] < location[0]) {
                    deque.offerLast(location);
                } else {
                    int[] pre = deque.pollLast();
                    int[] merge = new int[]{pre[0], Math.max(pre[1], location[1])};
                    deque.offerLast(merge);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!deque.isEmpty()) {
            int[] location = deque.pollFirst();
            result.add(location[1] - location[0] + 1);
        }
        return result;*/

        // 2、合并区间
        List<Integer> result = new ArrayList<>();
        // 记录每个字母的最右位置
        int[] right = new int[26];
        for (int i = 0; i < s.length(); i++) {
            right[s.charAt(i) - 'a'] = i;
        }
        // 使用start和end界定区间，动态更新end模拟区间合并
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, right[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }

    /**
     * 373. 查找和最小的K对数字
     * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
     * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
     *
     * @param nums1 升序数组1 (1 <= nums1.length <= 10^4)
     * @param nums2 升序数组2 (1 <= nums2.length <= 10^4)
     * @param k     数对个数 (1 <= k <= 1000)
     * @return 最小的 k 对数字
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /*// 1、枚举 + 优先队列 ：时间复杂度O(M * N * logK)，空间复杂度O(K)
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                k, ((o1, o2) -> o2[0] + o2[1] - o1[0] - o1[1])
        );
        // 枚举所有的数对，存入长度为k的优先队列，最终队列内的数对即为答案
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (queue.size() < k) {
                    queue.offer(new int[]{num1, num2});
                } else {
                    int[] peek = queue.peek();
                    if (num1 + num2 < peek[0] + peek[1]) {
                        queue.poll();
                        queue.offer(new int[]{num1, num2});
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] num = queue.poll();
            result.add(Arrays.asList(num[0], num[1]));
        }
        Collections.reverse(result);
        return result;*/

        /*// 2、双指针 ：时间复杂度O(K * M)，空间复杂度O(M)
        // index[i]表示数组1的第i个元素对应数组2中可用的最小元素的索引
        int[] index = new int[nums1.length];
        List<List<Integer>> result = new ArrayList<>();
        k = Math.min(nums1.length * nums2.length, k);
        while (k-- > 0) {
            int min = Integer.MAX_VALUE, a = -1;
            // 枚举数组1的所有元素，分别与数组2中可用的最小元素相加，取和最小的一组
            for (int i = 0; i < nums1.length; i++) {
                if (index[i] < nums2.length) {
                    int sum = nums1[i] + nums2[index[i]];
                    if (sum < min) {
                        min = sum;
                        a = i;
                    }
                }
            }
            result.add(Arrays.asList(nums1[a], nums2[index[a]++]));
        }
        return result;*/

        // 3、双指针 + 优先队列 ：时间复杂度O(K * logM)，空间复杂度O(M)
        // 队列存放的元素为数组[i,j]，i为数组1的元素的索引，j为i对应数组2中可用的最小元素的索引
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                nums1.length, (o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]
        );
        for (int i = 0; i < nums1.length; i++) {
            queue.add(new int[]{i, 0});
        }
        List<List<Integer>> result = new ArrayList<>();
        k = Math.min(nums1.length * nums2.length, k);
        while (k-- > 0) {
            int[] next = queue.poll();
            if (next[1] + 1 < nums2.length) {
                queue.offer(new int[]{next[0], next[1] + 1});
            }
            result.add(Arrays.asList(nums1[next[0]], nums2[next[1]]));
        }
        return result;
    }

    /**
     * 1695. 删除子数组的最大得分
     * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
     * 返回 只删除一个 子数组可获得的 最大得分 。
     *
     * @param nums 数组 (1 <= nums.length <= 10^5)
     * @return 最大得分
     */
    public int maximumUniqueSubarray(int[] nums) {
        // 双指针 + 哈希表 ：时间复杂度O(N)，空间复杂度O(N)
        int result = 0, temp = 0;
        // 双指针分别标识子数组的首尾下标
        int i = 0, j = 0;
        // 使用哈希表存放子数组中的数字
        Set<Integer> set = new HashSet<>();
        while (j < nums.length) {
            while (i < j && set.contains(nums[j])) {
                set.remove(nums[i]);
                temp -= nums[i++];
            }
            set.add(nums[j]);
            temp += nums[j++];
            result = Math.max(result, temp);
        }
        return result;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词指由相同字母重排列形成的字符串（包括相同的字符串）。
     *
     * @param s 字符串 (1 <= s.length <= 3 * 10^4 仅含小写字母)
     * @param p 字符串 (1 <= p.length <= 3 * 10^4 仅含小写字母)
     * @return 异位词子串的起始索引
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口：时间复杂度O(N)，空间复杂度O(1)
        List<Integer> res = new ArrayList<>();
        // arr1收集p的各字母数量
        int[] arr1 = new int[26];
        for (char c : p.toCharArray()) {
            arr1[c - 'a']++;
        }
        // arr2收集s的子串的各字母数量
        int[] arr2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr2[s.charAt(i) - 'a']++;
            int start = i - p.length() + 1;
            if (start < 0) {
                continue;
            }
            // 判断是否为异位词
            if (equals(arr1, arr2)) {
                res.add(start);
            }
            arr2[s.charAt(start) - 'a']--;
        }
        return res;
    }

    // 判断两个数组是否相等
    private boolean equals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1234. 替换子串得到平衡字符串
     * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
     * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
     * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
     * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
     * 请返回待替换子串的最小可能长度。如果原字符串自身就是一个平衡字符串，则返回 0。
     *
     * @param s 字符串
     * @return 替换子串的最小可能长度
     */
    public int balancedString(String s) {
        // 滑动窗口 ：时间复杂度O(N)，空间复杂度O(1)
        // 统计字符个数，然后将字符个数超过 n / 4 的字符通过滑动窗口找出来
        // 因为少的字符必定是从多的字符修改过来的，那么我们只需要找到多出来的字符所构成的最短的子串即可
        int avg = s.length() / 4;
        int countQ = 0, countW = 0, countE = 0, countR = 0;
        for (char c : s.toCharArray()) {
            if (c == 'Q') {
                countQ++;
            } else if (c == 'W') {
                countW++;
            } else if (c == 'E') {
                countE++;
            } else {
                countR++;
            }
        }
        if (countQ == avg && countW == avg && countE == avg && countR == avg) {
            return 0;
        }
        countQ = countQ > avg ? countQ - avg : 0;
        countW = countW > avg ? countW - avg : 0;
        countE = countE > avg ? countE - avg : 0;
        countR = countR > avg ? countR - avg : 0;
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int tempQ = 0, tempW = 0, tempE = 0, tempR = 0;
        while (j < s.length()) {
            char c = s.charAt(j);
            if (c == 'Q' && countQ != 0) {
                tempQ++;
            } else if (c == 'W' && countW != 0) {
                tempW++;
            } else if (c == 'E' && countE != 0) {
                tempE++;
            } else if (c == 'R' && countR != 0) {
                tempR++;
            }
            while (tempQ >= countQ && tempW >= countW && tempE >= countE && tempR >= countR) {
                res = Math.min(res, j - i + 1);
                char a = s.charAt(i);
                if (a == 'Q' && countQ != 0) {
                    tempQ--;
                } else if (a == 'W' && countW != 0) {
                    tempW--;
                } else if (a == 'E' && countE != 0) {
                    tempE--;
                } else if (a == 'R' && countR != 0) {
                    tempR--;
                }
                i++;
            }
            j++;
        }
        return res;
    }

    /**
     * 1034. 边界着色
     * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
     * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。
     * 连通分量的边界是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
     * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的连通分量的边界进行着色，并返回最终的网格 grid 。
     *
     * @param grid  二维网格 (1 <= m, n <= 50)
     * @param row   行号
     * @param col   列号
     * @param color 颜色
     * @return 二维网格
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        // 深度优先搜索
        List<int[]> borders = new ArrayList<>();
        Set<Long> seen = new HashSet<>();
        colorBorder(grid, row, col, color, borders, seen);
        for (int[] border : borders) {
            grid[border[0]][border[1]] = color;
        }
        return grid;
    }

    // 递归函数
    private void colorBorder(int[][] grid, int row, int col, int color, List<int[]> borders, Set<Long> seen) {
        int m = grid.length, n = grid[0].length;
        // 去重，保证每个坐标只被遍历一次
        long locate = (long) row << 32 | (long) col & 0xFFFFFFFFL;
        if (!seen.add(locate)) {
            return;
        }
        // 需要染色的点满足的条件：在网格边界、或相邻的点存在不同色
        if (row == 0 || row == m - 1 || col == 0 || col == n - 1
                || grid[row - 1][col] != grid[row][col] || grid[row + 1][col] != grid[row][col]
                || grid[row][col - 1] != grid[row][col] || grid[row][col + 1] != grid[row][col]) {
            borders.add(new int[]{row, col});
        }
        if (row > 0 && grid[row - 1][col] == grid[row][col]) colorBorder(grid, row - 1, col, color, borders, seen);
        if (row < m - 1 && grid[row + 1][col] == grid[row][col]) colorBorder(grid, row + 1, col, color, borders, seen);
        if (col > 0 && grid[row][col - 1] == grid[row][col]) colorBorder(grid, row, col - 1, color, borders, seen);
        if (col < n - 1 && grid[row][col + 1] == grid[row][col]) colorBorder(grid, row, col + 1, color, borders, seen);
    }

    /**
     * 794. 有效的井字游戏
     * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
     * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。以下是井字游戏的规则：
     * 1、玩家轮流将字符放入空位（' '）中，玩家 1 先手。
     * 2、玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
     * 3、'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 4、当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
     * 5、当所有位置非空时，也算为游戏结束。
     *
     * @param board 3 x 3 数组
     * @return 是否有效
     */
    public boolean validTicTacToe(String[] board) {
        // 统计步数
        int countX = 0, countO = 0;
        for (String one : board) {
            for (char c : one.toCharArray()) {
                if (c == 'X') {
                    countX++;
                } else if (c == 'O') {
                    countO++;
                }
            }
        }
        // 先手与后手要么步数一致，要先手多一步
        if (!(countX == countO || countX == countO + 1)) {
            return false;
        }
        // 如果先手获胜，先手多一步
        if (win(board, 'X') && countX != countO + 1) {
            return false;
        }
        // 如果后手获胜，步数相等
        if (win(board, 'O') && countX != countO) {
            return false;
        }
        return true;
    }

    // 是否能够获胜
    private boolean win(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            // 行
            if (c == board[i].charAt(0) && c == board[i].charAt(1) && c == board[i].charAt(2)) {
                return true;
            }
            // 列
            if (c == board[0].charAt(i) && c == board[1].charAt(i) && c == board[2].charAt(i)) {
                return true;
            }
        }
        // 对角线
        if (c == board[0].charAt(0) && c == board[1].charAt(1) && c == board[2].charAt(2)) {
            return true;
        }
        if (c == board[0].charAt(2) && c == board[1].charAt(1) && c == board[2].charAt(0)) {
            return true;
        }
        return false;
    }

    /**
     * 807. 保持城市天际线
     * 在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。
     * 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。高度 0 也被认为是建筑物。
     * 最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。
     * 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。
     * 建筑物高度可以增加的最大总和是多少？
     *
     * @param grid 二维数组
     * @return 增加的最大总和
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // 先求垂直方向和水平方向的天际线，对于每个点求两个方向上分别可以增大的数值，取其中小值，求和即为答案。
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.min(row[i] - grid[i][j], col[j] - grid[i][j]);
            }
        }
        return sum;
    }

    /**
     * 851. 喧闹和富有
     * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
     * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。
     * richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
     * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
     *
     * @param richer 财富比较
     * @param quiet  安静值
     * @return 钱不比"我"少的最安静的人
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // 1、深度优先搜索
        // 记录每个人对应的已知比他更有钱的人
        List<List<Integer>> richers = new ArrayList<>();
        for (int i = 0; i < quiet.length; i++) {
            richers.add(new ArrayList<>());
        }
        for (int i = 0; i < richer.length; i++) {
            richers.get(richer[i][1]).add(richer[i][0]);
        }
        // 记录答案，初始化为-1，表示待求解
        int[] ans = new int[quiet.length];
        for (int i = 0; i < quiet.length; i++) {
            ans[i]--;
        }
        // 找到更有钱的人里面最安静的那个
        for (int i = 0; i < quiet.length; i++) {
            loudAndRich(ans, richers, quiet, i);
        }
        return ans;
    }

    private void loudAndRich(int[] ans, List<List<Integer>> richers, int[] quiet, int k) {
        // 避免重复计算
        if (ans[k] >= 0) {
            return;
        }
        // 初始化为自己
        ans[k] = k;
        // 枚举已知的比 k 更有钱的人，比这些人更有钱的人肯定比 k 更有钱
        for (int i = 0; i < richers.get(k).size(); i++) {
            int p = richers.get(k).get(i);
            loudAndRich(ans, richers, quiet, p);
            if (quiet[ans[k]] > quiet[ans[p]]) {
                ans[k] = ans[p];
            }
        }
    }

    /**
     * 419. 甲板上的战舰
     * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
     * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。
     * 两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
     *
     * @param board 二维平面
     * @return 战舰数量
     */
    public int countBattleships(char[][] board) {
        // 深度优先搜索：时间复杂度O(MN)，空间复杂度O(1)
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 'X') {
                    continue;
                }
                count++;
                findAndMarkWholeShip(board, i, j);
            }
        }
        return count;
    }

    // 寻找并标记一艘完整的战舰
    private void findAndMarkWholeShip(char[][] board, int i, int j) {
        board[i][j] = 'Y';
        if (i > 0 && board[i - 1][j] == 'X') {
            findAndMarkWholeShip(board, i - 1, j);
        }
        if (i < board.length - 1 && board[i + 1][j] == 'X') {
            findAndMarkWholeShip(board, i + 1, j);
        }
        if (j > 0 && board[i][j - 1] == 'X') {
            findAndMarkWholeShip(board, i, j - 1);
        }
        if (j < board[0].length - 1 && board[i][j + 1] == 'X') {
            findAndMarkWholeShip(board, i, j + 1);
        }
    }

    /**
     * 539. 最小时间差
     * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     *
     * @param timePoints 时间列表
     * @return 最小时间差(分钟数)
     */
    public int findMinDifference(List<String> timePoints) {
        // 时间转为分钟数，倒序，遍历，两两求差，取最小
        // 注意：两个时间相差不会超过12小时，所以将最小时间加24小时作为最大时间，形成一个环
        List<Integer> minutes = new ArrayList<>();
        timePoints.forEach(timePoint -> minutes.add(getMinute(timePoint)));
        minutes.sort(((o1, o2) -> o2 - o1));
        minutes.add(0, minutes.get(minutes.size() - 1) + 1440);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.size(); i++) {
            int diff = minutes.get(i - 1) - minutes.get(i);
            res = Math.min(res, Math.min(diff, 1440 - diff));
        }
        return res;
    }

    // 将时间转为对应的分钟数
    private int getMinute(String timePoint) {
        return ((timePoint.charAt(0) - '0') * 10 + timePoint.charAt(1) - '0') * 60
                + (timePoint.charAt(3) - '0') * 10 + timePoint.charAt(4) - '0';
    }

    /**
     * 1996. 游戏中弱角色的数量
     * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击和防御。
     * 给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
     * 如果存在一个其他角色的攻击和防御等级都严格高于该角色的攻击和防御等级，则认为该角色为弱角色。
     *
     * @param properties 角色属性 (2 <= properties.length <= 10^5)
     * @return 弱角色的数量
     */
    public int numberOfWeakCharacters(int[][] properties) {
        /*// 1、排序 + 两层循环：时间复杂度O(N^2)
        int cnt = 0;
        // 按攻击和防御的和升序
        Arrays.sort(properties, (Comparator.comparingInt(o -> o[0] + o[1])));
        // 遍历每个角色，与和大的比较，并且先跟和最大的比较
        for (int i = 0; i < properties.length; i++) {
            for (int j = properties.length - 1; j > i; j--) {
                if (properties[i][0] < properties[j][0] && properties[i][1] < properties[j][1]) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;*/

        // 2、排序 + 一层循环：时间复杂度O(N * logN)
        int cnt = 0;
        // 按照攻击力降序，攻击力相等则按防御力升序
        Arrays.sort(properties, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        }));
        // 从左向右遍历，保留从左到当前的最大防御力，如果比当前的大，则当前角色是弱角色
        int maxDefense = 0;
        for (int i = 0; i < properties.length; i++) {
            if (maxDefense > properties[i][1]) {
                cnt++;
            }
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }
        return cnt;
    }

    /**
     * 1765. 地图中的最高点
     * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由陆地和水域单元格组成的地图。
     * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个陆地格子。
     * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个水域格子。
     * 你需要按照如下规则给每个单元格安排高度：
     * 1、每个格子的高度都必须是非负的。
     * 2、如果一个格子是是水域，那么它的高度必须为 0。
     * 3、任意相邻的格子高度差 至多为 1。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。
     * 找到一种安排高度的方案，使得矩阵中的最高高度值最大。
     * 请你返回一个大小为 m x n 的整数矩阵 height，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回任意一个。
     *
     * @param isWater 矩阵(是否水域)
     * @return 矩阵(最高高度值最大)
     */
    public int[][] highestPeak(int[][] isWater) {
        /*// 1、暴力破解：时间复杂度O((MN)^2)
        // 0的四周填上1，1的四周填上2，直到所有格子都被填充
        int m = isWater.length, n = isWater[0].length;
        int[][] res = new int[m][n];
        int remain = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = -1;
                if (isWater[i][j] == 1) {
                    res[i][j] = 0;
                    remain--;
                }
            }
        }
        highestPeak(res, 0, remain);
        return res;*/

        // 2、广度优先搜索：时间复杂度O(MN)
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int m = isWater.length, n = isWater[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = -1;
                if (isWater[i][j] == 1) {
                    res[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int height = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] dir : dirs) {
                    int x = point[0] + dir[0], y = point[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && res[x][y] == -1) {
                        res[x][y] = height;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            height++;
        }
        return res;
    }

    private void highestPeak(int[][] res, int num, int remain) {
        if (remain == 0) {
            return;
        }
        int m = res.length, n = res[0].length;
        int next = num + 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res[i][j] == num) {
                    if (i > 0 && res[i - 1][j] == -1) {
                        res[i - 1][j] = next;
                        remain--;
                    }
                    if (i < m - 1 && res[i + 1][j] == -1) {
                        res[i + 1][j] = next;
                        remain--;
                    }
                    if (j > 0 && res[i][j - 1] == -1) {
                        res[i][j - 1] = next;
                        remain--;
                    }
                    if (j < n - 1 && res[i][j + 1] == -1) {
                        res[i][j + 1] = next;
                        remain--;
                    }
                }
            }
        }
        highestPeak(res, next, remain);
    }

    /**
     * 540. 有序数组中的单一元素
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * 请你找出并返回只出现一次的那个数。
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     *
     * @param nums 整数数组
     * @return 单一元素
     */
    public int singleNonDuplicate(int[] nums) {

        // 根据题意可知：存在一个唯一元素，左侧偶数个小元素，相同元素的首元素下标为偶数，右侧偶数个大元素，相同元素的首元素下标为奇数，唯一元素的下标为偶数

        // 二分查找
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (((mid & 1) == 0 && nums[mid] == nums[mid + 1])
                    || ((mid & 1) == 1 && nums[mid - 1] == nums[mid])) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return nums[i];

    }

    /**
     * 57. 插入区间
     * 给你一个无重叠的，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * @param intervals   区间数组
     * @param newInterval 插入区间
     * @return 结果数组
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 一次遍历：时间复杂度O(N)，空间复杂度O(N)，其中N为intervals的长度
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> res = new ArrayList<>();
        // 合并区间
        int[] merge = null;
        // 是否已完成合并
        boolean merged = false;
        for (int[] interval : intervals) {
            // 后区间原样保留
            if (merged) {
                res.add(interval);
                continue;
            }
            // 前区间原样保留
            if (interval[1] < newInterval[0]) {
                res.add(interval);
            } else {
                // 中间区间进行合并
                if (merge == null) {
                    merge = newInterval;
                }
                if (interval[0] <= newInterval[1]) {
                    merge[0] = Math.min(interval[0], merge[0]);
                    merge[1] = Math.max(interval[1], merge[1]);
                } else {
                    res.add(merge);
                    res.add(interval);
                    merged = true;
                }
            }
        }
        // 可能遗漏的合并区间
        if (!merged) {
            if (merge == null) {
                merge = newInterval;
            }
            res.add(merge);
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 99. 恢复二叉搜索树
     * 给你二叉搜索树的根节点 root ，该树中的恰好两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树。
     *
     * @param root 二叉搜索树
     */
    public void recoverTree(TreeNode root) {
        // 1、中序遍历：时间复杂度O(N)，空间复杂度O(N)
        List<TreeNode> nodes = new ArrayList<>();
        collectByInorder(root, nodes);
        TreeNode p1 = null, p2 = null;
        int idx = -1;
        // 当出现前一个节点大于后一个节点时，前一个节点是被交换的较大节点
        for (int i = 0; i < nodes.size() - 1; i++) {
            if (nodes.get(i).val > nodes.get(i + 1).val) {
                p1 = nodes.get(i);
                idx = i + 1;
                break;
            }
        }
        // 在找到被交换的较大节点的前提下，向后寻找比其还大的节点，这个节点的前一个节点是被交换的较小节点
        for (int i = idx; i < nodes.size(); i++) {
            if (p1 != null && nodes.get(i).val > p1.val) {
                p2 = nodes.get(i - 1);
                break;
            }
        }
        // 被交换的较小节点可能在末尾处
        if (p2 == null) {
            p2 = nodes.get(nodes.size() - 1);
        }
        int temp = p1.val;
        p1.val = p2.val;
        p2.val = temp;
    }

    private void collectByInorder(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return;
        }
        collectByInorder(root.left, nodes);
        nodes.add(root);
        collectByInorder(root.right, nodes);
    }

    /**
     * 416. 分割等和子集
     * 给你一个只包含正整数的非空数组。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * @param nums 数组 (1 <= nums.length <= 200 且 1 <= nums[i] <= 100)
     * @return 是否可以等和分割
     */
    public boolean canPartition(int[] nums) {
        // 动态规划：时间复杂度O(NM)，空间复杂度O(NM)
        // 总和必须为偶数
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        // 找到和为总和一半的子集
        sum /= 2;
        // dp[i][j]表示在nums[0...i]中是否存在和为j的子集
        boolean[][] dp = new boolean[nums.length][sum + 1];
        for (int j = 0; j <= sum; j++) {
            if (nums[0] == j) {
                dp[0][j] = true;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j] || (j >= nums[i] && dp[i - 1][j - nums[i]]);
            }
        }
        return dp[nums.length - 1][sum];
    }

    /**
     * 152. 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组，并返回该子数组所对应的乘积。
     * 测试用例的答案是一个32位整数。
     *
     * @param nums 数组 (1 <= nums.length <= 2 * 10^4)
     * @return 最大乘积
     */
    public int maxProductOfSubArray(int[] nums) {
        /*// 1、枚举子数组：时间复杂度O(N^2)，空间复杂度O(1)
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            max = Math.max(max, product);
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];
                max = Math.max(max, product);
            }
        }
        return max;*/

        /*// 2、动态规划：时间复杂度O(N)，空间复杂度O(N)
        // max[i]表示以i为结尾的子数组的最大乘积
        int[] max = new int[nums.length];
        // min[i]表示以i为结尾的子数组的最小乘积
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int res = max[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
            } else {
                max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            }
            res = Math.max(res, max[i]);
        }
        return res;*/

        // 3、动态规划：时间复杂度O(N)，空间复杂度O(1)
        // max表示以当前点为结尾的子数组的最大乘积，min表示以当前点为结尾的子数组的最小乘积
        int res = nums[0], max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = Math.max(nums[i], min * nums[i]);
                min = Math.min(nums[i], temp * nums[i]);
            } else {
                max = Math.max(nums[i], max * nums[i]);
                min = Math.min(nums[i], min * nums[i]);
            }
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 207. 课程表
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。在选修某些课程之前需要一些先修课程。
     * 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则必须先学习课程 bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     * @param numCourses    课程数 (1 <= numCourses <= 10^5)
     * @param prerequisites 先修课程
     * @return 能否完成所有课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 判断有向图无环：时间复杂度O(N + M)，空间复杂度O(N + M)，其中N为prerequisites的长度，M为numCourses的大小
        // 邻接表：存储先修课程 -> 后修课程列表
        Map<Integer, List<Integer>> adjacentList = new HashMap<>();
        // 入度：存储课程的先修课程数量
        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            adjacentList.computeIfAbsent(prerequisite[1], ArrayList::new).add(prerequisite[0]);
            inDegrees[prerequisite[0]]++;
        }
        // 寻找入度为0的点，将其排除，其所有后修课程入度减1，重复这个过程，直到排除所有点，如果存在点不能排除，说明出现环
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            numCourses--;
            List<Integer> next = adjacentList.get(course);
            if (next != null) {
                for (Integer one : next) {
                    inDegrees[one]--;
                    if (inDegrees[one] == 0) {
                        queue.offer(one);
                    }
                }
            }
        }
        return numCourses == 0;
    }

    /**
     * 688. 骑士在棋盘上的概率
     * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。
     * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
     * 0  2  0  2  0
     * 2  0  0  0  2
     * 0  0  1  0  0
     * 2  0  0  0  2
     * 0  2  0  2  0
     * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
     * 骑士继续移动，直到它走了 k 步或离开了棋盘(离开棋盘后立即停止)。返回骑士在棋盘停止移动后仍留在棋盘上的概率。
     *
     * @param n      棋盘阶数
     * @param k      总步数
     * @param row    起点所在行
     * @param column 起点所在列
     * @return 留在棋盘的概率
     */
    public double knightProbability(int n, int k, int row, int column) {
        /*// 1、深度优先搜索：时间复杂度O(8^k)，空间复杂度O(k)
        // 出界则留在棋盘的概率为0
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0.0;
        }
        // 步数为0则留在棋盘的概率为1
        if (k == 0) {
            return 1.0;
        }
        // 步数大于0，向四周八个点走一步，每个点有1/8的概率
        k--;
        return (knightProbability(n, k, row + 2, column + 1)
                + knightProbability(n, k, row + 2, column - 1)
                + knightProbability(n, k, row + 1, column + 2)
                + knightProbability(n, k, row + 1, column - 2)
                + knightProbability(n, k, row - 1, column + 2)
                + knightProbability(n, k, row - 1, column - 2)
                + knightProbability(n, k, row - 2, column + 1)
                + knightProbability(n, k, row - 2, column - 1)
        ) / 8.0;*/

        // 2、动态规划：时间复杂度O(k * n^2)，空间复杂度O(k * n^2)
        // dp[a][i][j]表示从(i,j)出发走a步后留在棋盘的概率
        double[][][] dp = new double[k + 1][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[0][i][j] = 1.0;
            }
        }
        // 定义8个方向的偏移量
        int[][] dirs = new int[][]{{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        // 从(i,j)出发走a步后留在棋盘的概率，等于从周围8个点出发走a-1步后留在棋盘的概率的总和除以8
        for (int a = 1; a <= k; a++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            dp[a][i][j] += dp[a - 1][x][y] / 8.0;
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的二叉搜索树有多少种？返回满足题意的二叉搜索树的种数。
     *
     * @param n 整数 (1 <= n <= 19)
     * @return 二叉搜索树的种数
     */
    public int numTrees(int n) {
        // 动态规划：时间复杂度O(n^2)，空间复杂度O(n)
        if (n == 1) {
            return 1;
        }
        // dp[i]表示由 1 到 i 组成的二叉搜索树的种数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        // 枚举节点数
        for (int i = 3; i <= n; i++) {
            // 枚举根节点
            for (int j = 1; j <= i; j++) {
                dp[i] += Math.max(1, dp[j - 1]) * Math.max(1, dp[i - j]);
            }
        }
        return dp[n];
    }

    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * @param nums 数组
     * @return 最长连续序列的长度
     */
    public int longestConsecutive(int[] nums) {
        /*// 1、排序：时间复杂度O(N * logN)，空间复杂度O(1)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 1;
        int tmp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                tmp++;
                max = Math.max(max, tmp);
            } else if (nums[i] != nums[i - 1]) {
                tmp = 1;
            }
        }
        return max;*/

        // 2、哈希表：时间复杂度O(N)，空间复杂度O(N)
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // 寻找可以作为起点的数，往后加1，看最长能到多长
        for (int cur : set) {
            if (!set.contains(cur - 1)) {
                int tmp = 1;
                int next = cur + 1;
                while (set.contains(next)) {
                    tmp++;
                    next++;
                }
                max = Math.max(max, tmp);
            }
        }
        return max;
    }

    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * @param root 二叉树
     */
    public void flatten(TreeNode root) {
        // 先序遍历：时间复杂度O(N)，空间复杂度O(N)
        List<TreeNode> list = new ArrayList<>();
        preorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            list.get(i - 1).left = null;
            list.get(i - 1).right = list.get(i);
        }
    }

    private void preorder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preorder(root.left, list);
        preorder(root.right, list);
    }

}
