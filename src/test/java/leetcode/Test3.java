package leetcode;

import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.Trie;
import cn.jinty.leetcode.function.Fun3;
import cn.jinty.utils.BinaryTreeUtil;
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

    @Test
    public void testNumSubarrayBoundedMax(){
        int[] arr = new int[]{2,1,4,3};
        System.out.println(fun3.numSubarrayBoundedMax(arr,2,3));
    }

    @Test
    public void testUglyNumber(){
        System.out.println(fun3.isUglyNumber(10));
        System.out.println(fun3.nthUglyNumber(10));
    }

    @Test
    public void testLargestNumber(){
        int[] nums1 = {30,33,4,1,9,566};
        System.out.println(fun3.largestNumber(nums1));
        int[] nums2 = {0,0,0,0,0};
        System.out.println(fun3.largestNumber(nums2));
        int[] nums3 = {1,0,2,0,0};
        System.out.println(fun3.largestNumber(nums3));
    }

    @Test
    public void testMinDiffInBST(){
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(40);
        root.right = new TreeNode(57);
        System.out.println(fun3.minDiffInBST(root));
    }

    @Test
    public void testTrie(){
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.search("app"));
    }

    @Test
    public void testRob(){
        int[] nums = new int[]{1,9,2,8,100};
        System.out.println(fun3.rob(nums));
    }

    @Test
    public void testBuildTree(){
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        BinaryTreeUtil.bfsPrint(fun3.buildTree(inorder,postorder));
    }

}
