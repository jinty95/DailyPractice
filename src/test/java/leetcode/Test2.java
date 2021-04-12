package leetcode;

import cn.jinty.leetcode.ListNode;
import cn.jinty.leetcode.TreeNode;
import cn.jinty.leetcode.function.Fun2;
import cn.jinty.utils.ArrayUtil;
import cn.jinty.utils.BinaryTreeUtil;
import cn.jinty.utils.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法题测试
 *
 * @author jinty
 * @date 2021/3/3
 **/
public class Test2 {

    private Fun2 fun2 = new Fun2();

    @Test
    public void testBuildTree(){
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        TreeNode treeNode = fun2.buildTree(pre,in);
        BinaryTreeUtil.bfsPrint(treeNode);
        List<Integer> list = new ArrayList<>();
        TreeNode.preOrder(list,treeNode);
        System.out.println(list);
    }

    @Test
    public void testReconstructQueue(){
        int[][] people = {
                {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
        };
        ArrayUtil.print2DArray(fun2.reconstructQueue(people));
    }

    @Test
    public void testReverseWords(){
        String s = "  hello world!  ";
        System.out.println(fun2.reverseWords(s));
    }

    @Test
    public void testArrayRankTransform(){
        int[] arr1 = {40,10,20,30,20,10};
        int[] arr2 = {};
        System.out.println(Arrays.toString(fun2.arrayRankTransform(arr1)));
        System.out.println(Arrays.toString(fun2.arrayRankTransform(arr2)));
    }

    @Test
    public void testMaxEnvelopes(){
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3},{6,8}};
        System.out.println(fun2.maxEnvelopes(envelopes));
    }

    @Test
    public void testIsPathCrossing(){
        String path = "NNEEEEESWNNNN";
        System.out.println(fun2.isPathCrossing(path));
        path = "SN";
        System.out.println(fun2.isPathCrossing(path));
        path = "WSSESEEE";
        System.out.println(fun2.isPathCrossing(path));
    }

    @Test
    public void testConvertBiNode(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        BinaryTreeUtil.bfsPrint(fun2.convertBiNode(root));
    }

    @Test
    public void testThreeSum(){
        int[] arr = {-1,0,1,2,-1,8,-4,-4,-1,0,0,0};
        System.out.println(fun2.threeSum(arr));
    }

    @Test
    public void testLongestPalindrome(){
        System.out.println(fun2.longestPalindrome("abbcxxxxxxxxxxxx"));
    }

    @Test
    public void testRemoveDuplicates(){
        System.out.println(fun2.removeDuplicates("abbaacbac"));
    }

    @Test
    public void testMyAtoi(){
        System.out.println(fun2.myAtoi("   -2147483647   "));
    }

    @Test
    public void testFourSum(){
        int[] arr = {-2,-1,-1,1,1,2,2};
        int target = 0;
        System.out.println(fun2.fourSum(arr,target));
    }

    @Test
    public void testMerge(){
        int[][] intervals = {{1,4},{2,6},{6,9},{5,7},{19,20},{99,100},{98,101}};
        ArrayUtil.print2DArray(fun2.merge(intervals));
    }

    @Test
    public void testGroupAnagrams(){
        String[] strs = {"nozzle","onlezz","abc","bca","tall","llat"};
        System.out.println(strs.length);
        List<List<String>> lists = fun2.groupAnagrams(strs);
        int len = 0;
        for(List<String> list : lists){
            len += list.size();
        }
        System.out.println(len);
        System.out.println(fun2.groupAnagrams(strs));
    }

    @Test
    public void testProcessQueries(){
        int[] queries = {4,1,2,2};
        System.out.println(Arrays.toString(fun2.processQueries(queries,4)));
    }

    @Test
    public void testCanJump(){
        int[] arr = {3,2,1,0,4};
        System.out.println(fun2.canJump(arr));
    }

    @Test
    public void testSearch(){
        int[] arr = {4,9,0,1,2,3};
        System.out.println(fun2.search(arr,0));
    }

    @Test
    public void testTrap(){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(fun2.trap(height));
    }

    @Test
    public void testIsValidSerialization(){
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(fun2.isValidSerialization(preorder));
    }

    @Test
    public void testGetLeastNumbers(){
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        System.out.println(Arrays.toString(fun2.getLeastNumbers(arr,4)));
    }

    @Test
    public void testSpiralOrder(){
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        System.out.println(fun2.spiralOrder(matrix));
    }

    @Test
    public void testCanCompleteCircuit(){
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(fun2.canCompleteCircuit(gas,cost));
    }

    @Test
    public void testNumDistinct(){
        String s = "aaaabbccaabc";
        String t = "aaaab";
        System.out.println(fun2.numDistinct(s,t));
    }

    @Test
    public void testHammingWeight(){
        System.out.println(fun2.hammingWeight(31));
    }

    @Test
    public void testValidateStackSequences(){
        System.out.println(fun2.validateStackSequences(
                new int[]{1,2,3,4,5},
                new int[]{5,4,1,2,3}
        ));
    }

    @Test
    public void testFindNthDigit(){
        System.out.println(fun2.findNthDigit(15));
        System.out.println(fun2.findNthDigit(21));
    }

    @Test
    public void testMinNumber(){
        System.out.println(
                fun2.minNumber(new int[]{0,1,10,100,3,32,85,859,32,322})
        );
    }

    @Test
    public void testMaxSlidingWindow(){
        System.out.println(
            Arrays.toString(
                fun2.maxSlidingWindow(
                    new int[]{1,3,1,2,0,5,99,1,4,65,34,22},
                    3
                )
            )
        );
    }

    @Test
    public void testFind132pattern(){
        System.out.println(
                fun2.find132pattern(
                        new int[]{1,3,2,4,5,6,7,8,9,10}
                )
        );
    }

    @Test
    public void testDeleteDuplicates(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(4);
        //System.out.println(ListNodeUtil.printListNode(fun2.deleteDuplicates(head)));
        System.out.println(ListNodeUtil.printListNode(fun2.deleteDuplicate(head)));
    }

    @Test
    public void testLastRemaining(){
        System.out.println(fun2.lastRemaining(10,17));
        System.out.println(fun2.lastRemaining(100,5));
    }

    @Test
    public void testConstructArr(){
        int[] arr = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(fun2.constructArr(arr)));
    }

    @Test
    public void testVerifyPostorder(){
        System.out.println(fun2.verifyPostorder(
                new int[]{10,6,9}
        ));
        System.out.println(fun2.verifyPostorder(
                new int[]{1,2,5,10,6,9,4,3}
        ));
    }

    @Test
    public void testMyPow(){
        System.out.println(fun2.myPow(2,Integer.MIN_VALUE));
        System.out.println(fun2.myPow(2,10));
        System.out.println(fun2.myPow(2,0));
    }

    @Test
    public void testCountDigitOne(){
        //System.out.println(fun2.countDigitOne(100));
        //System.out.println(fun2.countDigitOne(1234));
        System.out.println(fun2.countDigitOne(1410065408));
    }

    @Test
    public void testSearchMatrix(){
        int[][] matrix = {
                {1,3,5,7},
                {9,10,15,19},
                {22,29,33,45}
        };
        System.out.println(fun2.searchMatrix(matrix,3));
    }

    @Test
    public void testSubsetsWithDup(){
        int[] arr = {4,4,4,1,4};
        System.out.println(fun2.subsetsWithDup(arr));
    }

    @Test
    public void testLengthOfLongestSubstring(){
        System.out.println(fun2.lengthOfLongestSubstring("abcdakieulk"));
        System.out.println(fun2.lengthOfLongestSubstring("+-×÷"));
        System.out.println(fun2.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void testIsStraight(){
        System.out.println(fun2.isStraight(new int[]{0,0,1,4,5}));
        System.out.println(fun2.isStraight(new int[]{0,4,6,7,8}));
        System.out.println(fun2.isStraight(new int[]{1,3,5,6,7}));
        System.out.println(fun2.isStraight(new int[]{0,0,2,2,5}));
    }

    @Test
    public void testPermutation(){
        System.out.println(Arrays.toString(fun2.permutation("abcaaa")));
    }

    @Test
    public void testClumsy(){
        System.out.println(fun2.clumsy(10));
    }

    @Test
    public void testFindMin(){
        int[] arr1 = new int[]{4,5,6,7,0,1,2,3};
        System.out.println(fun2.findMin(arr1));
        int[] arr2 = new int[]{50,60,70,80,90,99,5,6,7,8,9,10,20,30,40};
        System.out.println(fun2.findMin(arr2));
    }

}


