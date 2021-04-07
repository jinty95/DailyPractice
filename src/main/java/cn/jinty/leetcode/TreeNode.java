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

    public static <T> void preOrder(List<T> list, TreeNode<T> root){
        if(root==null){
            list.add(null);
        }else{
            list.add(root.val);
            preOrder(list,root.left);
            preOrder(list,root.right);
        }
    }

}
