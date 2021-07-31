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

}
