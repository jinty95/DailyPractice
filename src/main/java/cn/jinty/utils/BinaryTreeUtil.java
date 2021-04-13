package cn.jinty.utils;

import cn.jinty.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树工具类
 *
 * @author jinty
 * @date 2021/3/3
 **/
public class BinaryTreeUtil {

    /**
     * 层次遍历打印二叉树
     *
     * @param root 二叉树
     * @param <T> 节点数据类型
     */
    public static final <T> void bfsPrint(TreeNode<T> root){
        if(root==null){
            return;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode<T> node = queue.poll();
                if(node!=null){
                    System.out.print(node.val + "  ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }else{
                    System.out.print(null + "  ");
                }
            }
            System.out.println();
        }
    }

}
