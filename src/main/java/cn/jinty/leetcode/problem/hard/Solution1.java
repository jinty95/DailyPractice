package cn.jinty.leetcode.problem.hard;

import cn.jinty.struct.tree.TreeNode;

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
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map = new TreeMap<>();
        verticalTraversal(root,0,0,map);
        //构建结果集
        List<List<Integer>> lists = new ArrayList<>();
        for(Integer col : map.keySet()){
            List<Integer> list = new ArrayList<>();
            lists.add(list);
            for(Integer row : map.get(col).keySet()){
                PriorityQueue<Integer> value = map.get(col).get(row);
                while( ! value.isEmpty()){
                    list.add(value.poll());
                }
            }
        }
        return lists;
    }
    //深度搜索：先序遍历
    private void verticalTraversal(TreeNode root, int row, int col,
                                   TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> colMap){
        if(root==null) return;
        TreeMap<Integer,PriorityQueue<Integer>> rowMap = colMap.computeIfAbsent(col, k -> new TreeMap<>());
        PriorityQueue<Integer> value = rowMap.computeIfAbsent(row, k -> new PriorityQueue<>());
        value.offer(root.val);
        verticalTraversal(root.left, row+1, col-1, colMap);
        verticalTraversal(root.right, row+1, col+1, colMap);
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
        boolean[][] seen = new boolean[n][1<<n];
        //默认情况下所有节点都为(i,2^i,0)
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<n; i++){
            queue.offer(new int[]{i,1<<i,0});
            seen[i][1<<i] = true;
        }
        //循环直到队列为空
        int ans = 0;
        while( ! queue.isEmpty()){
            int[] triple = queue.poll();
            //第一次出现所有节点都已被经过时，得到最短路径
            if(triple[1]==(1<<n)-1){
                ans = triple[2];
                break;
            }
            //搜索相邻节点
            for(int j : graph[triple[0]]){
                //将mask的第j位置为1
                int maskJ = triple[1] | (1<<j);
                if( ! seen[j][maskJ]){
                    queue.offer(new int[]{j,maskJ,triple[2]+1});
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
        for(int i = 0; i < n; i++){
            dp[i] = new HashMap<>();
        }
        //双层循环：i为尾项，j为倒数第二项
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
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
        int[][][] dp = new int[n+1][2][3];
        //边界
        dp[0][0][0] = 1;
        //递推
        for(int i=1; i<=n; i++){
            for(int j=0; j<2; j++){
                for(int k=0; k<3; k++){
                    //A
                    if(j>0){
                        dp[i][j][0] = (dp[i][j][0] + dp[i-1][j-1][k]) % MOD;
                    }
                    //L
                    if(k>0){
                        dp[i][j][k] = (dp[i][j][k] + dp[i-1][j][k-1]) % MOD;
                    }
                    //P
                    dp[i][j][0] = (dp[i][j][0] + dp[i-1][j][k]) % MOD;
                }
            }
        }
        int ans = 0;
        for(int j=0; j<2; j++){
            for(int k=0; k<3; k++){
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
     * @param k 最大项目数量
     * @param w 启动资本
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
        for(int i=0; i<n; i++){
            projects[i] = new int[]{capital[i],profits[i]};
        }
        //按成本升序
        Arrays.sort(projects, Comparator.comparingInt(o -> o[0]));
        //大根堆按利润降序
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->o2-o1);
        //把所有能投的项目都放入大根堆，然后投利润最高的项目，更新本金，重复这个过程
        int i=0;
        while(k>0){
            while(i<n && w>=projects[i][0]){
                pq.offer(projects[i][1]);
                i++;
            }
            if(pq.isEmpty()){
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
     * @param words 单词数组
     * @param maxWidth 文本宽度
     * @return 对齐结果(不改变原单词先后顺序)
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        //当前行宽
        int curWidth = 0;
        //当前行单词组
        List<String> curList = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            //判断当前单词能否加入当前行
            if(curWidth == 0 || curWidth + curList.size() + words[i].length() <= maxWidth){
                //单词加入当前行
                curList.add(words[i]);
                curWidth += words[i].length();
            }else{
                //生成当前行对齐文本
                StringBuilder sb = new StringBuilder();
                if(curList.size() == 1){
                    //单个单词，只须左对齐，右边补空格
                    sb.append(curList.get(0));
                    for(int k = 0; k < maxWidth - curWidth; k++){
                        sb.append(' ');
                    }
                }else{
                    //多个单词，须左右对齐，中间平均插入空格
                    int blankNum = maxWidth - curWidth;
                    int intervalNum = curList.size() - 1;
                    int averageNum = blankNum / intervalNum;
                    int remainNum = blankNum % intervalNum;
                    for(int j = 0; j < curList.size(); j++){
                        sb.append(curList.get(j));
                        if(j < curList.size()-1){
                            for(int k = 0; k < averageNum; k++){
                                sb.append(' ');
                            }
                            if(remainNum>0){
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
        for(int j = 0; j < curList.size(); j++){
            sb.append(curList.get(j));
            if(j < curList.size()-1){
                sb.append(' ');
            }
        }
        for(int k = 0; k < maxWidth - curWidth - curList.size() + 1; k++){
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
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i < 31; i++){
            //根为0，左子0，右子10
            dp[i] = dp[i-1] + dp[i-2];
        }
        int pre = 0; int ans = 0;
        //遍历 n 的每一位
        for(int i = 29; i >= 0; i--){
            int val = 1 << i;
            if((n & val) != 0){
                //当前高度 n 的位为 1 ，则同样高度位为 0 的数都小于 n
                ans += dp[i+1];
                //发现连续 1 ，直接终止
                if(pre==1){
                    break;
                }
                pre = 1;
            }else{
                pre = 0;
            }
            //叶子节点没有子节点，单独处理
            if(i == 0){
                ans++;
            }
        }
        return ans;

    }

}
