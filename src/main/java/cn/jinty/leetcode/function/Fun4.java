package cn.jinty.leetcode.function;

import cn.jinty.leetcode.TreeNode;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/4/25
 **/
public class Fun4 {

    /**
     * 897. 递增顺序搜索树
     * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
     * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     *
     * @param root 二叉搜索树
     * @return 递增搜索树
     */
    public TreeNode increasingBST(TreeNode root) {
        if(root==null) return null;
        inOrder(root);
        return headNode;
    }
    private TreeNode preNode;
    private TreeNode headNode;
    private void inOrder(TreeNode root){
        if(root==null) return;
        //递归左子
        inOrder(root.left);
        //业务处理
        if(preNode==null){
            preNode = root;
            headNode = root;
        }else{
            root.left = null;
            preNode.right = root;
            preNode = root;
        }
        //递归右子
        inOrder(root.right);
    }

}
