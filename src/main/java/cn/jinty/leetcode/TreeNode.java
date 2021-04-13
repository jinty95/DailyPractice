package cn.jinty.leetcode;

import java.util.List;

/**
 * 树节点
 *
 * @author jinty
 * @date 2020/12/25
 **/
public class TreeNode<T> {

    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(){}
    public TreeNode(T val){
        this.val = val;
    }
    public TreeNode(T val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    //前序遍历：收集节点列表
    public static <T> void preOrder(TreeNode<T> root, List<T> list, boolean containNull){
        if(root==null){
            if(containNull) list.add(null);
        }else{
            list.add(root.val);
            preOrder(root.left,list,containNull);
            preOrder(root.right,list,containNull);
        }
    }

    //中序遍历：收集节点列表
    public static <T> void inOrder(TreeNode<T> root, List<T> list, boolean containNull){
        if(root==null){
            if(containNull) list.add(null);
        }else{
            inOrder(root.left,list,containNull);
            list.add(root.val);
            inOrder(root.right,list,containNull);
        }
    }

    //后序遍历：收集节点列表
    public static <T> void postOrder(TreeNode<T> root, List<T> list, boolean containNull){
        if(root==null){
            if(containNull) list.add(null);
        }else{
            postOrder(root.left,list,containNull);
            postOrder(root.right,list,containNull);
            list.add(root.val);
        }
    }

}
