package cn.jinty.leetcode.problem.middle;

import cn.jinty.struct.tree.Trie;
import cn.jinty.util.ArrayUtil;
import cn.jinty.util.MathUtil;

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

    /**
     * 211. 添加与搜索单词 - 数据结构设计
     * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     * 搜索时 word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     */
    public static class WordDictionary {

        private Trie trie;

        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return trie.findWithPoint(word);
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

}
