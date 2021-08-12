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

}