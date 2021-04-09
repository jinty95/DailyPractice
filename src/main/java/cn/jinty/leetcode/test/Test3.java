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

    @Test
    public void testFindMin(){
        int[] arr = new int[]{4,4,5,5,6,6,0,1,2,3,3,3,3};
        System.out.println(fun3.findMin(arr));
    }

    @Test
    public void testPairSums(){
        int[] arr = new int[]{4,5,6,7,1,2,3,7,9,8,2};
        System.out.println(fun3.pairSums(arr,10));
    }

}
