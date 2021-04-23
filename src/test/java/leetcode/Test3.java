package leetcode;

import cn.jinty.leetcode.Node;
import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.Trie;
import cn.jinty.leetcode.function.Fun3;
import cn.jinty.utils.BinaryTreeUtil;
import cn.jinty.utils.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testIsScramble(){
        System.out.println(fun3.isScramble("abcde","caeff"));
        System.out.println(fun3.isScramble("great","rgtea"));
        System.out.println(fun3.isScramble("abcdefghijklmnopq","efghijklmnopqcadb"));
        System.out.println(fun3.isScramble("eebaacbcbcadaaedceaaacadccd","eadcaacabaddaceacbceaabeccd"));
    }

    @Test
    public void testContainsNearbyAlmostDuplicate(){
        System.out.println(fun3.containsNearbyAlmostDuplicate(
                new int[]{1,5,9,1,5,9},2,3)
        );
        System.out.println(fun3.containsNearbyAlmostDuplicate(
                new int[]{-2147483648,2147483647},1,1)
        );
    }

    @Test
    public void testTreeToDoublyList(){
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        System.out.println(root);
        System.out.println(fun3.treeToDoublyList(root));
    }

    @Test
    public void testReversePairs(){
        int[] nums = {9,8,4,6,5,4,1,2,3};
        System.out.println(fun3.reversePairs(nums));
    }

    @Test
    public void testStrStr(){
        String s1 = "hello world";
        String s2 = "el";
        String s3 = "hello world !";
        String s4 = "hello world";
        System.out.println(fun3.strStr(s1,s2));
        System.out.println(fun3.strStr(s1,s3));
        System.out.println(fun3.strStr(s1,s4));
    }

    @Test
    public void testCopyRandomList(){
        Node head = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        head.next = n1;
        n1.next = n2;
        head.random = n2;
        n1.random = head;
        n2.random = n1;
        System.out.println(ListNodeUtil.printNode(head));
        System.out.println(ListNodeUtil.printNode(fun3.copyRandomList(head)));
    }

    @Test
    public void testNumDecodings(){
        System.out.println(fun3.numDecodings("111"));
        System.out.println(fun3.numDecodings("10101011"));
        System.out.println(fun3.numDecodings("2612321"));
        System.out.println(fun3.numDecodings("00000"));
        System.out.println(fun3.numDecodings("10001"));
    }

    @Test
    public void testMaxSumSubmatrix(){
        int[][] matrix = {
                {1,0,1},
                {0,-2,3}
        };
        System.out.println(fun3.maxSumSubmatrix(matrix,2));
    }

    @Test
    public void testLargestDivisibleSubset(){
        int[] arr = {2,3,4,8};
        System.out.println(Arrays.toString(arr));
        System.out.println(fun3.largestDivisibleSubset(arr));
    }

    @Test
    public void testStrToInt(){
        System.out.println(fun3.strToInt("00000000000"));
        System.out.println(fun3.strToInt("2147483648"));
        System.out.println(fun3.strToInt("-2147483649"));
        System.out.println(fun3.strToInt("abc 1234"));
        System.out.println(fun3.strToInt("9898989 aaa"));
        System.out.println(fun3.strToInt("555555555555555"));
        System.out.println(fun3.strToInt("-1818181"));
        System.out.println(fun3.strToInt("+100"));
    }

    @Test
    public void testTreeNode(){
        //构造
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(7);
        root.left = left;
        root.right = right;
        right.left = new TreeNode(8);
        right.right = new TreeNode(10);
        //DFS遍历
        List<Integer> pre = new ArrayList<>();
        TreeNode.preOrder(root,pre,true);
        System.out.println("前序遍历: "+pre);
        List<Integer> in = new ArrayList<>();
        TreeNode.inOrder(root,in,true);
        System.out.println("中序遍历: "+in);
        List<Integer> post = new ArrayList<>();
        TreeNode.postOrder(root,post,true);
        System.out.println("后序遍历: "+post);
        //BFS遍历
        System.out.println("层次遍历: "+TreeNode.bfs(root));
        //序列化
        System.out.println("序列化: "+TreeNode.serialize(root));
        //反序列化
        System.out.println("反序列化: "+TreeNode.deserialize("[5, 3, 7, null, null, 8, 10, null, null, null, null]"));
    }

    @Test
    public void testIntegerBreak(){
        System.out.println(fun3.integerBreak(10));
        System.out.println(fun3.integerBreak(54));
        System.out.println(fun3.integerBreak(450));
    }

}
