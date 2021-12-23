package cn.jinty.leetcode.problem.hard;

import cn.jinty.struct.tree.TreeNode;
import cn.jinty.struct.tree.Trie;

import java.util.*;

/**
 * LeetCode - 困难题
 *
 * @author Jinty
 * @date 2021/7/31
 **/
public class Solution1 {

    /**
     * 987. 二叉树的垂序遍历
     * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
     * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
     * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
     * 返回二叉树的 垂序遍历 序列。
     *
     * @param root 二叉树
     * @return 垂序遍历
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //(列号->(行号->节点值列表))
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        verticalTraversal(root, 0, 0, map);
        //构建结果集
        List<List<Integer>> lists = new ArrayList<>();
        for (Integer col : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            lists.add(list);
            for (Integer row : map.get(col).keySet()) {
                PriorityQueue<Integer> value = map.get(col).get(row);
                while (!value.isEmpty()) {
                    list.add(value.poll());
                }
            }
        }
        return lists;
    }

    //深度搜索：先序遍历
    private void verticalTraversal(TreeNode root, int row, int col,
                                   TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> colMap) {
        if (root == null) return;
        TreeMap<Integer, PriorityQueue<Integer>> rowMap = colMap.computeIfAbsent(col, k -> new TreeMap<>());
        PriorityQueue<Integer> value = rowMap.computeIfAbsent(row, k -> new PriorityQueue<>());
        value.offer(root.val);
        verticalTraversal(root.left, row + 1, col - 1, colMap);
        verticalTraversal(root.right, row + 1, col + 1, colMap);
    }

    /**
     * 847. 访问所有节点的最短路径
     * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
     * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
     * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
     *
     * @param graph 无向连通图 (1 <= n <= 12)
     * @return 访问所有节点的最短路径
     */
    public int shortestPathLength(int[][] graph) {
        //1、广度优先搜索
        //定义三元组(cur,mask,len)，其中cur标识当前节点编号，mask标识已经过的节点列表，len标识累计路径长度
        int n = graph.length;
        //记录已经出现过的(cur,mask)，避免陷入重复计算与死循环
        boolean[][] seen = new boolean[n][1 << n];
        //默认情况下所有节点都为(i,2^i,0)
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }
        //循环直到队列为空
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] triple = queue.poll();
            //第一次出现所有节点都已被经过时，得到最短路径
            if (triple[1] == (1 << n) - 1) {
                ans = triple[2];
                break;
            }
            //搜索相邻节点
            for (int j : graph[triple[0]]) {
                //将mask的第j位置为1
                int maskJ = triple[1] | (1 << j);
                if (!seen[j][maskJ]) {
                    queue.offer(new int[]{j, maskJ, triple[2] + 1});
                    seen[j][maskJ] = true;
                }
            }
        }
        return ans;
    }

    /**
     * 446. 等差数列划分 II - 子序列
     * 给你一个整数数组 numbers ，返回 numbers 中 等差子序列 的数目。
     * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
     * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
     * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
     * 题目数据保证答案是一个 32-bit 整数。
     *
     * @param numbers 数组
     * @return 等差子序列数目
     */
    @SuppressWarnings("unchecked")
    public int numberOfArithmeticSlices(int[] numbers) {
        //动态规划
        int ans = 0;
        int n = numbers.length;
        //dp[i][d]表示尾项为numbers[i]，公差为d的等差子序列的个数
        Map<Long, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        //双层循环：i为尾项，j为倒数第二项
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = (long) numbers[i] - numbers[j];
                //存在count个以j为尾项的等差子序列
                int count = dp[j].getOrDefault(d, 0);
                //等差子序列数量增加count
                ans += count;
                //以i为结尾的等差子序列与j对接后，以i为结尾的等差子序列数量增加count+1
                dp[i].put(d, dp[i].getOrDefault(d, 0) + count + 1);
            }
        }
        return ans;
    }

    /**
     * 552. 学生出勤记录 II
     * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
     * 'A'：Absent，缺勤  'L'：Late，迟到  'P'：Present，到场
     * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
     * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
     * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
     * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况数量。
     * 答案可能很大，所以返回对 10^9 + 7 取余 的结果。
     *
     * @param n 整数
     * @return 可能获得出勤奖励的记录情况数量
     */
    public int checkRecord(int n) {
        //1、动态规划
        final int MOD = 1000000007;
        //定义三维dp，dp[i][j][k]表示记录为i，A有j个，最近连续L有k个时的记录情况数
        int[][][] dp = new int[n + 1][2][3];
        //边界
        dp[0][0][0] = 1;
        //递推
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    //A
                    if (j > 0) {
                        dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j - 1][k]) % MOD;
                    }
                    //L
                    if (k > 0) {
                        dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                    }
                    //P
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                ans = (ans + dp[n][j][k]) % MOD;
            }
        }
        return ans;
    }

    /**
     * 502. IPO
     * 假设 力扣 即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
     * 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
     * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
     * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
     * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终的总资本。
     * 答案保证在 32 位有符号整数范围内。
     *
     * @param k       最大项目数量
     * @param w       启动资本
     * @param profits 项目及其收益
     * @param capital 项目及其成本
     * @return 最终的总资本
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        /*//1、贪心算法：时间复杂度O(kn)
        //将成本和收益合在一起
        int n = profits.length;
        int[][] projects = new int[n][2];
        for(int i=0; i<n; i++){
            projects[i] = new int[]{capital[i],profits[i]};
        }
        //按收益降序，收益相同时按成本升序
        Arrays.sort(projects, (o1,o2)->{
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            return o2[1]-o1[1];
        });
        //记录已投项目
        boolean[] invested = new boolean[n];
        //从高收益开始投资，每完成一个投资，本金增大，重新从高收益开始投资，直到投够k个项目或者无法继续投资
        boolean flag = true;
        while(k>0 && flag){
            flag = false;
            for(int i=0; i<n; i++){
                if(invested[i]) continue;
                if(w < projects[i][0]) continue;
                w += projects[i][1];
                invested[i] = true;
                flag = true;
                k--;
                break;
            }
        }
        return w;*/

        //上述做法时间复杂度很高，执行超时

        //2、贪心算法+大根堆：时间复杂度(O(N*logN+K*logN))
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{capital[i], profits[i]};
        }
        //按成本升序
        Arrays.sort(projects, Comparator.comparingInt(o -> o[0]));
        //大根堆按利润降序
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //把所有能投的项目都放入大根堆，然后投利润最高的项目，更新本金，重复这个过程
        int i = 0;
        while (k > 0) {
            while (i < n && w >= projects[i][0]) {
                pq.offer(projects[i][1]);
                i++;
            }
            if (pq.isEmpty()) {
                return w;
            }
            w += pq.poll();
            k--;
        }
        return w;

    }

    /**
     * 68. 文本左右对齐
     * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
     * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
     * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     * 说明:
     * 单词是指由非空格字符组成的字符序列。
     * 每个单词的长度大于 0，小于等于 maxWidth。
     * 输入单词数组 words 至少包含一个单词。
     *
     * @param words    单词数组
     * @param maxWidth 文本宽度
     * @return 对齐结果(不改变原单词先后顺序)
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        //当前行宽
        int curWidth = 0;
        //当前行单词组
        List<String> curList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            //判断当前单词能否加入当前行
            if (curWidth == 0 || curWidth + curList.size() + words[i].length() <= maxWidth) {
                //单词加入当前行
                curList.add(words[i]);
                curWidth += words[i].length();
            } else {
                //生成当前行对齐文本
                StringBuilder sb = new StringBuilder();
                if (curList.size() == 1) {
                    //单个单词，只须左对齐，右边补空格
                    sb.append(curList.get(0));
                    for (int k = 0; k < maxWidth - curWidth; k++) {
                        sb.append(' ');
                    }
                } else {
                    //多个单词，须左右对齐，中间平均插入空格
                    int blankNum = maxWidth - curWidth;
                    int intervalNum = curList.size() - 1;
                    int averageNum = blankNum / intervalNum;
                    int remainNum = blankNum % intervalNum;
                    for (int j = 0; j < curList.size(); j++) {
                        sb.append(curList.get(j));
                        if (j < curList.size() - 1) {
                            for (int k = 0; k < averageNum; k++) {
                                sb.append(' ');
                            }
                            if (remainNum > 0) {
                                sb.append(' ');
                                remainNum--;
                            }
                        }
                    }
                }
                list.add(sb.toString());
                //清空当前行数据
                curWidth = 0;
                curList.clear();
                i--;
            }
        }
        //最后一行
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < curList.size(); j++) {
            sb.append(curList.get(j));
            if (j < curList.size() - 1) {
                sb.append(' ');
            }
        }
        for (int k = 0; k < maxWidth - curWidth - curList.size() + 1; k++) {
            sb.append(' ');
        }
        list.add(sb.toString());
        return list;
    }

    /**
     * 600. 不含连续1的非负整数
     * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
     *
     * @param n 正整数 (1 <= n <= 10^9)
     * @return 小于等于n且不含连续1的非负整数个数
     */
    public int findIntegers(int n) {

        /*//1、枚举：时间复杂度O(n)
        int count = 0;
        for(int i = 0; i <= n; i++){
            if((i & (i >> 1)) == 0){
                count++;
            }
        }
        return count;*/

        //上述做法时间复杂度过高，执行超时

        //2、动态规划：时间复杂度O(log(n))
        //将小于等于 n 的所有数用 01 前缀树表示，n 即为前缀树的右轮廓
        //定义 dp[i] 表示高度为 i 、根结点为 0 的满二叉树中，不包含连续 1 的从根结点到叶结点的路径数量
        int[] dp = new int[31];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < 31; i++) {
            //根为0，左子0，右子10
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int pre = 0;
        int ans = 0;
        //遍历 n 的每一位
        for (int i = 29; i >= 0; i--) {
            int val = 1 << i;
            if ((n & val) != 0) {
                //当前高度 n 的位为 1 ，则同样高度位为 0 的数都小于 n
                ans += dp[i + 1];
                //发现连续 1 ，直接终止
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }
            //叶子节点没有子节点，单独处理
            if (i == 0) {
                ans++;
            }
        }
        return ans;

    }

    /**
     * 212. 单词搜索 II
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母在一个单词中不允许被重复使用。
     *
     * @param board 二维网格
     * @param words 单词组(字典)
     * @return 所有同时在二维网格和字典中出现的单词
     */
    public List<String> findWords(char[][] board, String[] words) {
        //前缀树 + 回溯
        Set<String> ans = new HashSet<>();
        Set<Long> locates = new HashSet<>();
        //1、根据words构建前缀树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        //2、对board做回溯
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrack(board, locates, i, j, trie, new StringBuilder(), ans);
            }
        }
        return new ArrayList<>(ans);
    }

    //回溯
    private void backtrack(char[][] board, Set<Long> locates, int i, int j, Trie trie, StringBuilder word, Set<String> ans) {
        //超出边界
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        //保存坐标，防止重复
        Long locate = (long) i << 32 | (long) j & 0xFFFFFFFFL;
        if (locates.contains(locate)) return;
        locates.add(locate);
        //增加字符
        word.append(board[i][j]);
        //搜索前缀
        Trie.TrieNode trieNode = trie.find(word.toString());
        //前缀不存在，可提前剪枝
        if (trieNode == null) {
            word.deleteCharAt(word.length() - 1);
            locates.remove(locate);
            return;
        }
        //前缀存在，判断是否命中单词
        if (trieNode.flag) {
            ans.add(word.toString());
        }
        //递归
        backtrack(board, locates, i - 1, j, trie, word, ans);
        backtrack(board, locates, i, j - 1, trie, word, ans);
        backtrack(board, locates, i + 1, j, trie, word, ans);
        backtrack(board, locates, i, j + 1, trie, word, ans);
        //回溯
        word.deleteCharAt(word.length() - 1);
        locates.remove(locate);
    }

    /**
     * 639. 解码方法 II
     * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
     * 'A' -> 1, 'B' -> 2, ..., 'Z' -> 26
     * 除了上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字(不包括 '0')。
     * 给你一个字符串 s ，由数字和 '*' 字符组成，返回解码该字符串的方法数目。
     * 由于答案数目可能非常大，返回对 10^9 + 7 取余的结果。
     *
     * @param s 字符串 (1 <= s.length <= 10^5)
     * @return 解码方法
     */
    public int numDecoding(String s) {
        // 动态规划：时间复杂度O(N)，空间复杂度O(N)
        final int MOD = 1000000007;
        // 定义：dp[i]表示s[0...i]的编码方式数量
        int[] dp = new int[s.length()];
        // 边界：dp[0]
        if (s.charAt(0) == '0') return 0;
        if (s.charAt(0) == '*') dp[0] = 9;
        else dp[0] = 1;
        // 递推：dp[i] = dp[i - 2] * a + dp[i - 1] * b
        for (int i = 1; i < s.length(); i++) {
            int dp_i_2 = i > 1 ? dp[i - 2] : 1;
            int dp_i_1 = dp[i - 1];
            // 讨论：cur单独编码，cur与pre一起编码
            char cur = s.charAt(i);
            char pre = s.charAt(i - 1);
            if (cur == '0') {
                if (pre == '*') dp[i] = multiplyAndMod(dp_i_2, 2, MOD);
                else if (pre == '1' || pre == '2') dp[i] = dp_i_2;
                else return 0;
            } else if (cur == '*') {
                if (pre == '*') dp[i] = (multiplyAndMod(dp_i_2, 15, MOD) + multiplyAndMod(dp_i_1, 9, MOD)) % MOD;
                else if (pre == '1') dp[i] = (multiplyAndMod(dp_i_2, 9, MOD) + multiplyAndMod(dp_i_1, 9, MOD)) % MOD;
                else if (pre == '2') dp[i] = (multiplyAndMod(dp_i_2, 6, MOD) + multiplyAndMod(dp_i_1, 9, MOD)) % MOD;
                else dp[i] = multiplyAndMod(dp_i_1, 9, MOD);
            } else {
                if (pre == '*')
                    dp[i] = (cur <= '6' ? multiplyAndMod(dp_i_2, 2, MOD) + dp_i_1 : (dp_i_2 + dp_i_1)) % MOD;
                else if (pre == '1') dp[i] = (dp_i_2 + dp_i_1) % MOD;
                else if (pre == '2') dp[i] = (cur <= '6' ? dp_i_2 + dp_i_1 : dp_i_1) % MOD;
                else dp[i] = dp_i_1;
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * 相乘并求模
     * 两个int直接相乘后求模，会因为数值溢出导致结果错误，故采用分段相加求模
     * 两个int相乘的结果可以用long存储，所以使用long型则可以直接相乘并求模
     *
     * @param a 乘数1
     * @param b 乘数2
     * @return 计算结果
     */
    @SuppressWarnings("SameParameterValue")
    private int multiplyAndMod(int a, int b, int mod) {
        int result = 0;
        for (int i = 1; i <= b; i++) {
            result = (result + a) % mod;
        }
        return result;
    }

    /**
     * 273. 整数转换英文表示
     * 将非负整数 num 转换为其对应的英文表示。
     *
     * @param num 整数
     * @return 英文表示
     */
    public String numberToWords(int num) {
        // 英文表示字面量
        String[] units = {"Zero ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine "};
        String[] tens = {"", "Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        String[] fromTenToTwenty = {"", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
        String[] kilos = {"", "Thousand ", "Million ", "Billion "};
        // 特殊处理 0
        if (num == 0) {
            return units[0].trim();
        }
        // 将输入转为十进制字符串，按三位一组从低位到高位处理
        StringBuilder groups = new StringBuilder();
        String numStr = new StringBuilder(num + "").reverse().toString();
        int j = 0;
        // 最多四组
        for (int i = 0; i < 4; i++) {
            // 每组三位
            StringBuilder group = new StringBuilder(kilos[i]);
            // 保证三位都不为 0
            boolean notZero = false;
            for (int k = 0; k < 3; k++) {
                // 单个数字，忽略 0
                int digit = numStr.charAt(j++) - '0';
                if (digit > 0) {
                    notZero = true;
                    if (k == 0) {
                        // 个位，[11,19]特殊处理
                        if (j < numStr.length() && numStr.charAt(j) == '1') {
                            group.append(fromTenToTwenty[digit]);
                            k++;
                            j++;
                        } else {
                            group.append(units[digit]);
                        }
                    } else if (k == 1) {
                        // 十位
                        group.append(tens[digit]);
                    } else {
                        // 百位
                        group.append("Hundred ").append(units[digit]);
                    }
                }
                // 数字已读完
                if (j == numStr.length()) {
                    break;
                }
            }
            // 汇总
            if (notZero) {
                groups.append(group);
            }
            // 数字已读完
            if (j == numStr.length()) {
                break;
            }
        }
        // 以单词为单位翻转字符串
        StringBuilder ans = new StringBuilder();
        String[] arr = groups.toString().trim().split(" ");
        for (int i = arr.length - 1; i >= 0; i--) {
            ans.append(arr[i]);
            if (i > 0) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    /**
     * 282. 给表达式添加运算符
     * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，
     * 在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
     *
     * @param num    数字字符串
     * @param target 目标值
     * @return 表达式集合
     */
    public List<String> addOperators(String num, int target) {
        // 回溯
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(num, target, ans, sb, 0, 0, 0);
        return ans;
    }

    private void backtrack(String num, int target, List<String> ans, StringBuilder sb, int i, long sum, long last) {
        // 收集答案
        if (i == num.length()) {
            if (sum == target) {
                ans.add(sb.toString());
            }
            return;
        }
        // 符号占位符
        int signIdx = sb.length();
        if (i > 0) {
            sb.append(0);
        }
        // 枚举截取的数字长度(注意：数字可以是单个零但不能有前导零)
        long val = 0;
        for (int j = i; j < num.length() && (j == i || num.charAt(i) != '0'); j++) {
            val = val * 10 + num.charAt(j) - '0';
            sb.append(num.charAt(j));
            if (i == 0) {
                // 开头不能加符号
                backtrack(num, target, ans, sb, j + 1, val, val);
            } else {
                // 枚举"+"、"-"、"*"
                sb.setCharAt(signIdx, '+');
                backtrack(num, target, ans, sb, j + 1, sum + val, val);
                sb.setCharAt(signIdx, '-');
                backtrack(num, target, ans, sb, j + 1, sum - val, -val);
                sb.setCharAt(signIdx, '*');
                backtrack(num, target, ans, sb, j + 1, sum - last + last * val, last * val);
            }
        }
        sb.setLength(signIdx);
    }

    /**
     * 335. 路径交叉
     * 给你一个整数数组 d 。从 X-Y 平面上的点 (0,0) 开始，先向北移动 d[0] 米，然后向西移动 d[1] 米，
     * 向南移动 d[2] 米，向东移动 d[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
     * 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
     *
     * @param d 移动距离
     * @return 是否交叉
     */
    public boolean isSelfCrossing(int[] d) {
        // 边的数量小于4则不可能交叉
        if (d.length < 4) {
            return false;
        }
        // 至少4条边为一组
        for (int i = 3; i < d.length; i++) {
            // 4边交叉
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) {
                return true;
            }
            // 5边交叉
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) {
                return true;
            }
            // 6边交叉
            if (i >= 5 && d[i - 1] <= d[i - 3] && d[i - 2] > d[i - 4]
                    && d[i] + d[i - 4] >= d[i - 2] && d[i - 1] + d[i - 5] >= d[i - 3]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 407. 接雨水 II
     * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     *
     * @param heightMap 二维高度图 (最大200×200)
     * @return 雨水总量
     */
    public int trapRainWater(int[][] heightMap) {

        // 错误算法
        // 一个格子的雨水量 = 东南西北四个最大高度中的最小高度 - 格子高度
        // 上面等式只有四个最大高度都与当前格子相邻时才正确，否则只要中间出现一个缺口，水就会漏出去，所以雨水量会更低

        // 正确算法
        // 一个格子的雨水量 = 东南西北四个相邻格子接水后高度的最小高度 - 格子高度
        // 最外层格子无法接水，所以接水后高度不变，其中，最低高度将决定内部的水量，其相邻格子的接水后高度可以确定，之后更新最外层，重复过程直到所有格子都遍历

        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    visit[i][j] = true;
                    queue.offer(new int[]{i * n + j, heightMap[i][j]});
                }
            }
        }

        int ans = 0;
        int[] dir = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = cur[0] / n + dir[k];
                int y = cur[0] % n + dir[k + 1];
                if (x < 0 || x == m || y < 0 || y == n) {
                    continue;
                }
                if (!visit[x][y]) {
                    if (cur[1] > heightMap[x][y]) {
                        ans += cur[1] - heightMap[x][y];
                    }
                    visit[x][y] = true;
                    queue.add(new int[]{x * n + y, Math.max(heightMap[x][y], cur[1])});
                }
            }
        }
        return ans;

    }

    /**
     * 488. 祖玛游戏
     * 在这个祖玛游戏中，桌面上有一排彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G'、白色 'W'。你的手中也有一些彩球。
     * 你的目标是清空桌面上所有的球。每一回合：
     * 从你手上的彩球中选出任意一颗，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
     * 接着，如果有出现三个或者三个以上且颜色相同的球相连的话，就把它们移除掉。
     * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则继续移除这些球，直到不再满足移除条件。
     * 请你按上述操作步骤移除掉桌上所有球，计算并返回所需的最少球数。如果不能移除桌上所有的球，返回 -1 。
     *
     * @param board 待处理的彩球 (1 <= board.length <= 16)
     * @param hand  手头上的彩球 (1 <= hand.length <= 5)
     * @return 消除彩球的最少球数
     */
    public int findMinStep(String board, String hand) {
        minStep = -1;
        if (board.length() + hand.length() < 3) {
            return minStep;
        }
        char[] arr = hand.toCharArray();
        Arrays.sort(arr);
        dfsForFindMinStep(board, new String(arr), 0);
        return minStep;
    }

    // 最少球数
    private int minStep;

    // 深度优先搜索：在任何位置放置任意一个球，消除后继续放球，直到球用完，记下一个可能解，最终取可能解中的最小值
    private void dfsForFindMinStep(String board, String hand, int step) {
        // 手上无球，桌上有球
        if (hand.isEmpty() && !board.isEmpty()) {
            return;
        }
        // 桌上无球
        if (board.isEmpty()) {
            minStep = (minStep == -1) ? step : Math.min(minStep, step);
            return;
        }
        // 在桌上球的任意球左侧以及最右球的右侧放入任意手上球
        for (int i = 0; i <= board.length(); i++) {
            for (int j = 0; j < hand.length(); j++) {
                // 剪枝1：桌上球与手上球同色，手上球放左放右都一样
                if (i > 0 && hand.charAt(j) == board.charAt(i - 1)) {
                    continue;
                }
                // 剪枝2：两个手上球同色时，拿哪个都一样
                if (j > 0 && hand.charAt(j) == hand.charAt(j - 1)) {
                    continue;
                }
                // 手上球移到桌上
                String nextBoard = board.substring(0, i) + hand.charAt(j) + (i == board.length() ? "" : board.substring(i));
                String nextHand = hand.substring(0, j) + (j == hand.length() - 1 ? "" : hand.substring(j + 1));
                // 桌上球消除
                nextBoard = removeBalls(nextBoard);
                // 递归
                dfsForFindMinStep(nextBoard, nextHand, step + 1);
            }
        }
    }

    // 消除三个或以上的连续同色球
    public String removeBalls(String board) {
        Deque<int[]> stack = new LinkedList<>();
        for (char c : board.toCharArray()) {
            while (!stack.isEmpty() && stack.peek()[0] != c && stack.peek()[1] >= 3) {
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                stack.peek()[1]++;
            } else {
                stack.push(new int[]{c, 1});
            }
        }
        if (!stack.isEmpty() && stack.peek()[1] >= 3) {
            stack.pop();
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            for (int i = 0; i < arr[1]; i++) {
                res.append((char) arr[0]);
            }
        }
        return res.reverse().toString();
    }

    /**
     * 391. 完美矩形
     * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
     * 这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
     * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ，否则返回 false 。
     *
     * @param rectangles 矩形
     * @return 是否完美矩形
     */
    public boolean isRectangleCover(int[][] rectangles) {
        // 完美矩形需要同时满足以下两个条件，缺一不可
        // 1、上下左右四个点只出现一次，其余点都成对出现 2、大矩形面积等于所有小矩形面积之和
        int bottom = Integer.MAX_VALUE, top = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        int areaSum = 0;
        Map<Long, Integer> count = new HashMap<>();
        for (int[] rec : rectangles) {
            // 求小矩形总面积
            areaSum += (rec[3] - rec[1]) * (rec[2] - rec[0]);
            // 求大矩形边界
            bottom = Math.min(bottom, rec[1]);
            top = Math.max(top, rec[3]);
            left = Math.min(left, rec[0]);
            right = Math.max(right, rec[2]);
            // 求顶点出现次数
            countPoint(count, rec[0], rec[3]);
            countPoint(count, rec[0], rec[1]);
            countPoint(count, rec[2], rec[1]);
            countPoint(count, rec[2], rec[3]);
        }
        // 四个顶点坐标
        Set<Long> border = new HashSet<>();
        border.add(int2long(left, top));
        border.add(int2long(right, top));
        border.add(int2long(left, bottom));
        border.add(int2long(right, bottom));
        // 判断顶点对应数量是否符合要求
        for (Long point : count.keySet()) {
            if (border.contains(point)) {
                if (count.get(point) != 1) {
                    return false;
                }
            } else {
                if (count.get(point) % 2 != 0) {
                    return false;
                }
            }
        }
        // 判断面积是否符合要求
        return areaSum == (top - bottom) * (right - left);
    }

    // 使用 long 存储 int 二维坐标
    private long int2long(int a, int b) {
        return (long) a << 32 | (long) b & 0xFFFFFFFFL;
    }

    // 顶点坐标计数
    private void countPoint(Map<Long, Integer> count, int a, int b) {
        long point = int2long(a, b);
        count.put(point, count.getOrDefault(point, 0) + 1);
    }

    /**
     * 30. 串联所有单词的子串
     * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
     *
     * @param s     字符串 (1 <= s.length <= 10^4 小写字母组成)
     * @param words 单词数组 (1 <= words.length <= 5000 小写字母组成)
     * @return 串联子串的起始位置
     */
    public List<Integer> findSubstring(String s, String[] words) {

        // 注意利用单词长度相同这个条件

        // 暴力搜索 + 哈希表
        // 枚举s所有可能是答案的子串，这些子串与words分别通过哈希表计算单词数量，如果最终等到相等的哈希表，说明可以串联形成
        List<Integer> res = new ArrayList<>();
        int wordLen = words[0].length();
        int wordTotalLen = words.length * wordLen;
        if (s.length() < wordTotalLen) {
            return res;
        }
        Map<String, Integer> wMap = new HashMap<>();
        for (String word : words) {
            wMap.put(word, wMap.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i <= s.length() - wordTotalLen; i++) {
            Map<String, Integer> sMap = new HashMap<>();
            String sub = s.substring(i, i + wordTotalLen);
            for (int j = 0; j <= sub.length() - wordLen; j += wordLen) {
                String wordOfSub = sub.substring(j, j + wordLen);
                sMap.put(wordOfSub, sMap.getOrDefault(wordOfSub, 0) + 1);
            }
            if (sMap.size() == wMap.size()) {
                boolean flag = true;
                for (String word : wMap.keySet()) {
                    if (!wMap.get(word).equals(sMap.get(word))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    res.add(i);
                }
            }
        }
        return res;

    }

    /**
     * 458. 可怜的小猪
     * 有 buckets 桶液体，其中正好有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。
     * 为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
     * 喂猪的规则如下：
     * 1、选择若干活猪进行喂养
     * 2、可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
     * 3、小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
     * 4、过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
     * 5、重复这一过程，直到时间用完。
     * 返回在规定时间内判断哪个桶有毒所需的最小猪数。
     *
     * @param buckets       水桶数量
     * @param minutesToDie  冷却时间
     * @param minutesToTest 总测试时间
     * @return 最小的猪数
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        // 将一个猪作为一个维度：
        // 假设有两个猪，则将桶按照正方形摆放，两个猪分别负责x、y两个方向的垂直直线，当一头猪在x中毒，一头猪在y中毒，可以确定(x,y)有毒
        // 如果只能测1次，最大检测4桶，如果可以测2次，最大检测9桶，... ，如果可以检测N次，最大检测(N+1)^2桶
        // 假设有三个猪，则将桶按照正方体摆放，三个猪分别负责x、y、z三个方向的垂直平面，当一头猪在x中毒，一头猪在y中毒，一头猪在z中毒，可以确定(x,y,z)有毒
        // 如果只能测1次，最大检测8桶，如果可以测2次，最大检测27桶，... ，如果可以检测N次，最大检测(N+1)^3桶
        int times = minutesToTest / minutesToDie + 1;
        int pigs = 0;
        while (Math.pow(times, pigs) < buckets) {
            pigs++;
        }
        return pigs;
    }

    /**
     * 786. 第 K 个最小的素数分数
     * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干素数组成，且其中所有整数互不相同。
     * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
     * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
     *
     * @param arr 元素唯一且递增的素数数组 (2 <= arr.length <= 1000)
     * @param k   整数 (1 <= k <= arr.length * (arr.length - 1) / 2)
     * @return 第 K 小的素数分数
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        /*// 1、优先队列 ：时间复杂度O(N^2 * logK)，空间复杂度O(K)
        // 优先队列存储前 K 小的素数分数，并且按照值倒序，比较分数时先通分然后比分子即可
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> o2[0] * o1[1] - o1[0] * o2[1]);
        // 枚举所有的素数分数，存入优先队列
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (pq.size() < k) {
                    pq.offer(new int[]{arr[i], arr[j]});
                } else {
                    int[] peek = pq.peek();
                    if (peek[0] * arr[j] > arr[i] * peek[1]) {
                        pq.poll();
                        pq.offer(new int[]{arr[i], arr[j]});
                    }
                }
            }
        }
        // 队头即为所求答案
        return pq.poll();*/

        // 2、优先队列 ：时间复杂度O(K * logN)，空间复杂度O(N)
        // 优先队列存储素数分数的下标，并且按照值升序
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> arr[o1[0]] * arr[o2[1]] - arr[o2[0]] * arr[o1[1]]);
        // 分子确定，分母越大值越小，分母确定，分子越大值越大
        for (int j = 1; j < arr.length; j++) {
            pq.offer(new int[]{0, j});
        }
        // 每次都弹出最小的一个分数，并补充一个可能为下一个小的分数，第 K 次即可得到第 K 小的分数
        while (k-- > 1) {
            int[] min = pq.poll();
            if (min[0] + 1 < min[1]) {
                pq.offer(new int[]{min[0] + 1, min[1]});
            }
        }
        int[] min = pq.poll();
        return new int[]{arr[min[0]], arr[min[1]]};
    }

    /**
     * 689. 三个无重叠子数组的最大和
     * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
     * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
     *
     * @param nums 数组 (1 <= len <= 2 * 10^4)
     * @param k    单个子数组长度
     * @return 三个无重叠子数组的最大和
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        /*// 1、前缀和 + 暴力搜索 ：时间复杂度O(N^3)，空间复杂度O(N)
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int a = 1; a < nums.length; a++) {
            preSum[a] = preSum[a - 1] + nums[a];
        }
        long maxSum = Long.MIN_VALUE;
        int[] res = new int[3];
        for (int a = 0; a <= nums.length - 3 * k; a++) {
            long sum1 = preSum[a + k - 1] - (a == 0 ? 0 : preSum[a - 1]);
            for (int b = a + k; b <= nums.length - 2 * k; b++) {
                long sum2 = preSum[b + k - 1] - preSum[b - 1];
                for (int c = b + k; c <= nums.length - k; c++) {
                    long sum3 = preSum[c + k - 1] - preSum[c - 1];
                    long sum = sum1 + sum2 + sum3;
                    if (sum > maxSum) {
                        maxSum = sum;
                        res[0] = a;
                        res[1] = b;
                        res[2] = c;
                    }
                }
            }
        }
        return res;*/

        // 2、滑动窗口 ：时间复杂度O(N)，空间复杂度O(1)
        int[] res = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int sum3 = 0, maxSum123 = 0;
        // 三个间隔为 k 的窗口同时滑动
        for (int i = k * 2; i < nums.length; i++) {
            sum1 += nums[i - k * 2];
            sum2 += nums[i - k];
            sum3 += nums[i];
            // 窗口大小达到 k 时定型，不再扩大
            if (i >= k * 3 - 1) {
                // 子数组 1 的最大和
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 3 + 1;
                }
                // 子数组 1 2 的最大和
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - k * 2 + 1;
                }
                // 子数组 1 2 3 的最大和
                if (maxSum12 + sum3 > maxSum123) {
                    maxSum123 = maxSum12 + sum3;
                    res[0] = maxSum12Idx1;
                    res[1] = maxSum12Idx2;
                    res[2] = i - k + 1;
                }
                sum1 -= nums[i - k * 3 + 1];
                sum2 -= nums[i - k * 2 + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return res;
    }

    /**
     * 630. 课程表 III
     * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
     * 其中 courses[i] = [a, b] 表示第 i 门课将会持续上 a 天课，并且必须在不晚于 b 的时候完成。
     * 你的学期从第 1 天开始，且不能同时修读两门及两门以上的课程，返回你最多可以修读的课程数目。
     *
     * @param courses 课程
     * @return 最多可以修读的课程数目
     */
    public int scheduleCourse(int[][] courses) {
        // 以结束时间升序
        Arrays.sort(courses, (c1, c2) -> c1[1] - c2[1]);
        // 储存已选择的课程，按照持续时间降序
        PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> c2[0] - c1[0]);
        int day = 0;
        for (int[] c : courses) {
            if (day + c[0] <= c[1]) {
                // 如果当前课程可以保证在期限前学完，将该课程加入队列
                day += c[0];
                pq.offer(c);
            } else if (!pq.isEmpty() && pq.peek()[0] > c[0]) {
                // 当前课程不能保证在期限前学完，且之前有选过其他课，这时我们找到最长时间的课程，用当前的短课替换
                // 课程变短了，day会前移，这样我们相当于变相给后面的课程增加了选择的区间，以达到选更多课的目的
                day -= pq.poll()[0] - c[0];
                pq.offer(c);
            }
        }
        return pq.size();
    }

    /**
     * 1044. 最长重复子串
     * 给你一个字符串 s ，考虑其所有重复子串：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
     * 返回任意一个可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 ""
     *
     * @param s 字符串
     * @return 最长重复子串
     */
    public String longestDupSubstring(String s) {
        /*// 1、暴力搜索：时间复杂度O(N^4)
        // 枚举所有子串，判断每个子串在原串中的重复次数
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (findDupCount(s, i, j) > 1) {
                    if (ans.length() < j - i + 1) {
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;*/

        // 2、暴力搜索(优化)：时间复杂度O(N^4)
        // 先考虑长的子串，找到一个满足条件的就可以返回了
        for (int len = s.length() - 1; len >= 0; len--) {
            for (int i = 0; i + len < s.length(); i++) {
                if (findDupCount(s, i, i + len) > 1) {
                    return s.substring(i, i + len + 1);
                }
            }
        }
        return "";
    }

    // 找出s[i...j]这个子串在s中的重复次数
    private int findDupCount(String s, int i, int j) {
        int count = 0;
        int k = i;
        for (int a = 0; a < s.length() - (j - i); a++) {
            if (s.charAt(a) != s.charAt(k)) {
                continue;
            }
            boolean flag = true;
            for (int b = a + 1; b <= a + j - i; b++) {
                if (s.charAt(b) != s.charAt(++k)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
            k = i;
        }
        return count;
    }

}
