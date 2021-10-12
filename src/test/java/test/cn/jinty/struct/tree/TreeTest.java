package test.cn.jinty.struct.tree;

import cn.jinty.struct.tree.TreeNode;
import org.junit.Test;

/**
 * 二叉树 - 测试
 *
 * @author jinty
 * @date 2021/6/7
 **/
public class TreeTest {

    @Test
    public void test() {
        //构造
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(7);
        root.left = left;
        root.right = right;
        right.left = new TreeNode(8);
        right.right = new TreeNode(10);
        //DFS遍历
        System.out.println("前序遍历: " + root.preOrder());
        System.out.println("中序遍历: " + root.inOrder());
        System.out.println("后序遍历: " + root.postOrder());
        //BFS遍历
        System.out.println("层次遍历: " + root.bfs());
        //序列化
        System.out.println("序列化: " + root.serialize());
        //反序列化
        System.out.println("反序列化: " + TreeNode.deserialize("[5, 3, 7, null, null, 8, 10, null, null, null, null]"));
    }

}
