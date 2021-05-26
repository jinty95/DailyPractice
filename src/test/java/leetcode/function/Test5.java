package leetcode.function;

import cn.jinty.leetcode.tree.TreeNode;
import cn.jinty.leetcode.function.Fun5;
import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode算法题 - 测试
 *
 * @author jinty
 * @date 2021/5/8
 **/
public class Test5 {

    private Fun5 fun5 = new Fun5();

    @Test
    public void testMinimumTimeRequired(){
        int[] jobs = {1,2,4,7,8};
        int k = 5;
        System.out.println(fun5.minimumTimeRequired(jobs,k));
    }

    @Test
    public void testIsNumber(){
        System.out.println("+" + "=" + fun5.isNumber("+"));
        System.out.println("." + "=" + fun5.isNumber("."));
        System.out.println("+." + "=" + fun5.isNumber("+."));
        System.out.println("  +.5  " + "=" + fun5.isNumber("  +.5  "));
        System.out.println("4.4.4" + "=" + fun5.isNumber("4.4.4"));
        System.out.println("1a3.14" + "=" + fun5.isNumber("1a3.14"));
        System.out.println("5E1.4" + "=" + fun5.isNumber("5E1.4"));
        System.out.println("12e+" + "=" + fun5.isNumber("12e+"));
        System.out.println("+e15" + "=" + fun5.isNumber("+e15"));
        System.out.println("-1E-16" + "=" + fun5.isNumber("-1E-16"));
        System.out.println("-+5" + "=" + fun5.isNumber("-+5"));
        System.out.println("-5" + "=" + fun5.isNumber("-5"));
    }

    @Test
    public void testMinDays(){
        int[] bloomDay = {1,2,3,4,5,6};
        System.out.println(fun5.minDays(bloomDay,3,2));
        System.out.println(fun5.minDays(bloomDay,3,1));
        System.out.println(fun5.minDays(bloomDay,2,2));
    }

    @Test
    public void testLeafSimilar(){
        TreeNode tree1 = new TreeNode(4);
        tree1.left = new TreeNode(3);
        tree1.right = new TreeNode(5);
        TreeNode tree2 = new TreeNode(4);
        tree2.left = new TreeNode(3);
        tree2.right = new TreeNode(5);
        System.out.println(fun5.leafSimilar(tree1,tree2));
    }

    @Test
    public void testIsMatch(){
        System.out.println(fun5.isMatch("bbbba","b*a"));
        System.out.println(fun5.isMatch("abcd",".*"));
        System.out.println(fun5.isMatch("abb","c*a*b*"));
        System.out.println(fun5.isMatch("abb","c*a*b*b"));
    }

    @Test
    public void testDecode(){
        int[] encoded1 = {3,1};
        System.out.println(Arrays.toString(fun5.decode(encoded1)));
        int[] encoded2 = {6,5,4,6};
        System.out.println(Arrays.toString(fun5.decode(encoded2)));
    }

    @Test
    public void testXorQueries(){
        int[] arr = {1,2,3,4,5,6};
        int[][] queries = {
                {0,1},{2,3},{1,5}
        };
        System.out.println(Arrays.toString(fun5.xorQueries(arr,queries)));
    }

    @Test
    public void testExist(){
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}

        };
        String word1 = "ABCESEEEFS";
        System.out.println(fun5.exist(board,word1));
    }

    @Test
    public void testMovingCount(){
        int m = 16, n = 8;
        int k = 4;
        System.out.println(fun5.movingCount(m,n,k));
    }

    @Test
    public void testNumWays(){
        System.out.println(fun5.numWays(1,5));
        System.out.println(fun5.numWays(3,100));
        System.out.println(fun5.numWays(430,148488));
    }

    @Test
    public void testDicesProbability(){
        System.out.println(Arrays.toString(fun5.dicesProbability(1)));
        System.out.println(Arrays.toString(fun5.dicesProbability(2)));
    }

    @Test
    public void testMissingTwo(){
        System.out.println(Arrays.toString(
                fun5.missingTwo(new int[]{1,2,3,4})
        ));
        System.out.println(Arrays.toString(
                fun5.missingTwo(new int[]{1,2,3,5})
        ));
        System.out.println(Arrays.toString(
                fun5.missingTwo(new int[]{3,4,1,6})
        ));
    }

    @Test
    public void testFirstMissingPositive(){
        System.out.println(fun5.firstMissingPositive(
                new int[]{1,1,1,3,4,5}
        ));
        System.out.println(fun5.firstMissingPositive(
                new int[]{6,2,1,3,4,5}
        ));
    }

    @Test
    public void testUniquePaths(){
        System.out.println(fun5.uniquePaths(3,7));
        System.out.println(fun5.uniquePaths(1,1));
    }

    @Test
    public void testFindMaximumXOR(){
        System.out.println(fun5.findMaximumXOR(new int[]{5}));
        System.out.println(fun5.findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }

    @Test
    public void testRestoreString(){
        String s = "codeleet";
        int[] indices = {4,5,6,7,0,2,1,3};
        System.out.println(fun5.restoreString(s,indices));
    }

    @Test
    public void testStoneGame() {
        System.out.println(fun5.stoneGame(new int[]{5, 3, 4, 5}));
        System.out.println(fun5.stoneGame(new int[]{5, 8, 5, 3}));
    }

    @Test
    public void testIsCousins(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        System.out.println(fun5.isCousins(root,3,7));
    }

    @Test
    public void testCountTriplets(){
        System.out.println(
            fun5.countTriplets(
                new int[]{2,3,1,6,7}
            )
        );
    }

    @Test
    public void testKthLargestValue(){
        int[][] matrix = {
                {5,2},{1,6}
        };
        System.out.println(fun5.kthLargestValue(matrix,1));
        System.out.println(fun5.kthLargestValue(matrix,2));
    }

    @Test
    public void testTopKFrequent(){
        String[] words = {
                "the", "day", "is", "sunny", "the", "day",
                "the", "the", "sunny", "is", "is", "is"
        };
        System.out.println(fun5.topKFrequent(words,4));
    }

    @Test
    public void testMaxUncrossedLines(){
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {2,1,4,3,5};
        System.out.println(fun5.maxUncrossedLines(nums1,nums2));
    }

    @Test
    public void testXorGame(){
        int[] nums1 = {1,1,2};
        System.out.println(fun5.xorGame(nums1));
        int[] nums2 = {1,0};
        System.out.println(fun5.xorGame(nums2));
    }

    @Test
    public void testMaximizeXor(){
        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};
        System.out.println(Arrays.toString(fun5.maximizeXor(nums,queries)));
    }

}
