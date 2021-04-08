package cn.jinty.leetcode.test;

import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.function.Fun3;
import org.junit.Test;

/**
 * LeetCode算法题测试
 *
 * @author jinty
 * @date 2021/4/8
 **/
public class Test3 {

    private Fun3 fun3 = new Fun3();

    @Test
    public void testLowestCommonAncestor(){
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        System.out.println(fun3.lowestCommonAncestor(root,left,right).val);
    }

}
