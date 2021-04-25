package leetcode;

import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.function.Fun4;
import org.junit.Test;

/**
 * LeetCode算法题 - 单元测试
 *
 * @author jinty
 * @date 2021/4/25
 **/
public class Test4 {

    private Fun4 fun4 = new Fun4();

    @Test
    public void testIncreasingBST(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(TreeNode.serialize(root));
        TreeNode newHead = fun4.increasingBST(root);
        System.out.println(TreeNode.serialize(newHead));
    }

}
