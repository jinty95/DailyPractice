package cn.jinty.util;

import cn.jinty.struct.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树工具类
 *
 * @author jinty
 * @date 2021/3/3
 **/
public final class BinaryTreeUtil {

    /**
     * 层次遍历打印二叉树
     *
     * @param root 二叉树
     */
    public static void bfsPrint(TreeNode root){
        if(root==null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
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
