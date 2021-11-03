package cn.jinty.leetcode.problem.hard;

import cn.jinty.struct.tree.TreeNode;
import cn.jinty.struct.tree.Trie;

import java.util.*;

/**
 * LeetCode - 困难题
 *
 * @author jinty
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

}
