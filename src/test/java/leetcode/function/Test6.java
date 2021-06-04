package leetcode.function;

import cn.jinty.struct.linear.ListNode;
import cn.jinty.leetcode.function.Fun6;
import cn.jinty.util.ArrayUtil;
import cn.jinty.util.ListNodeUtil;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode算法题 - 测试
 *
 * @author jinty
 * @date 2021/5/24
 **/
public class Test6 {

    private Fun6 fun6 = new Fun6();

    @Test
    public void testStrangePrinter(){
        System.out.println(fun6.strangePrinter("aba"));
        System.out.println(fun6.strangePrinter("abcbaaba"));
    }

    @Test
    public void testFindRadius(){
        int[] houses = {282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
        int[] heater = {823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612};
        System.out.println(fun6.findRadius(houses,heater));
    }

    @Test
    public void testMaxSumDivThree(){
        int[] nums = {3,5,6,1,8};
        System.out.println(fun6.maxSumDivThree(nums));
    }

    @Test
    public void testReverseParentheses(){
        String s = "(ed(et(oc))el)";
        System.out.println(fun6.reverseParentheses(s));
    }

    @Test
    public void testGameOfLife(){
        int[][] board = {
                {0,1,0},{0,0,1},{1,1,1},{0,0,0}
        };
        ArrayUtil.print2DArray(board);
        fun6.gameOfLife(board);
        System.out.println();
        ArrayUtil.print2DArray(board);
    }

    @Test
    public void testConvert(){
        System.out.println(fun6.convert("PAYPALISHIRING",3));
        System.out.println(fun6.convert("PAYPALISHIRING",4));
        System.out.println(fun6.convert("ABCD",1));
    }

    @Test
    public void testWordBreak(){
        System.out.println(fun6.wordBreak(
                "leetcode", ListUtil.asList("leet","code"))
        );
        System.out.println(fun6.wordBreak(
                "anappleaday", ListUtil.asList("an","apple","a","day"))
        );
    }

    @Test
    public void testFindTargetSumWays(){
        int[] nums = {0,0,0,0,0,0,0,0,1};
        System.out.println(fun6.findTargetSumWays(nums,1));
    }

    @Test
    public void testAddTwoNumbers(){
        ListNode l1 = ListNodeUtil.fromArray(new int[]{9,9,9});
        ListNode l2 = new ListNode(1);
        System.out.println(fun6.addTwoNumbers(l1,l2));
    }

    @Test
    public void testMergeKLists(){
        ListNode l1 = ListNodeUtil.fromArray(new int[]{1,3,5,7,9});
        ListNode l2 = ListNodeUtil.fromArray(new int[]{2,4,6,8,10});
        ListNode l3 = ListNodeUtil.fromArray(new int[]{10,11,12,13});
        ListNode[] lists = new ListNode[]{l1,l2,l3};
        System.out.println(fun6.mergeKLists(lists));
    }

    @Test
    public void testTotalHammingDistance(){
        System.out.println(fun6.totalHammingDistance(new int[]{4,14,2}));
        System.out.println(fun6.totalHammingDistance(new int[]{1,2,3,4,5,6,7,8,9,10}));
    }

    @Test
    public void testLeastInterval(){
        System.out.println(fun6.leastInterval(new char[]{'A','B','C','D'},2));
        System.out.println(fun6.leastInterval(new char[]{'A','A','A'},2));
        System.out.println(fun6.leastInterval(new char[]{'A','A','A','B','B','B'},2));
        System.out.println(fun6.leastInterval(new char[]{'A','A','A','B','B','B','C','C','C','D','D','D'},2));
    }

    @Test
    public void testNumSquares(){
        System.out.println(fun6.numSquares(10));
        System.out.println(fun6.numSquares(12));
    }

    @Test
    public void testDailyTemperatures(){
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(temperatures));
        System.out.println(Arrays.toString(fun6.dailyTemperatures(temperatures)));
    }

    @Test
    public void testFindDuplicate(){
        System.out.println(fun6.findDuplicate(new int[]{1,1,2,3,4,5,6,7,8,9}));
        System.out.println(fun6.findDuplicate(new int[]{1,8,2,3,4,5,6,7,8,9}));
    }

    @Test
    public void testNumSubmatrixSumTarget(){
        int[][] matrix = {
                {0,1,0},
                {1,1,1},
                {0,1,0}
        };
        System.out.println(fun6.numSubmatrixSumTarget(matrix,0));
        System.out.println(fun6.numSubmatrixSumTarget(matrix,1));
    }

    @Test
    public void testSubarraySum(){
        System.out.println(fun6.subarraySum(new int[]{1,1,1,1,1},2));
        System.out.println(fun6.subarraySum(new int[]{-2,2,-2,2,-2,2},2));
        System.out.println(fun6.subarraySum(new int[]{1},0));
    }

    @Test
    public void testAssignTasks(){
        int[] servers1 = {1,2,3,4,5,6};
        int[] tasks1 = {1,1,1,1,1,1};
        System.out.println(Arrays.toString(fun6.assignTasks(servers1,tasks1)));
        int[] servers2 = {31,96,73,90,15,11,1,90,72,9,30,88};
        int[] tasks2 = {87,10,3,5,76,74,38,64,16,64,93,95,60,79,54,26,30,44,64,71};
        System.out.println(Arrays.toString(fun6.assignTasks(servers2,tasks2)));
    }

    @Test
    public void testGetBiggestThree(){
        int[][] grid = {
                {3,  4,  5,  1,  3},
                {3,  3,  4,  2,  3},
                {20, 30, 200,40, 10},
                {1,  5,  5,  4,  1},
                {4,  3,  2,  2,  5}
        };
        System.out.println(Arrays.toString(fun6.getBiggestThree(grid)));
    }

    @Test
    public void testOriginalDigits(){
        System.out.println(fun6.originalDigits("onetwothreefourfive"));
        System.out.println(fun6.originalDigits("sixseveneightninezero"));
    }

    @Test
    public void testCanEat(){
        int[] candiesCount1 = {7,4,5,3,8};
        int[][] queries1 = {{0,2,2},{4,2,4},{2,13,1000000000}};
        System.out.println(
                Arrays.toString(fun6.canEat(candiesCount1,queries1))
        );
        int[] candiesCount2 = {5215,14414,67303,93431,44959,34974,22935,64205,28863,3436,45640,34940,38519,5705,14594,30510,4418,87954,8423,65872,79062,83736,47851,64523,15639,19173,88996,97578,1106,17767,63298,8620,67281,76666,50386,97303,26476,95239,21967,31606,3943,33752,29634,35981,42216,88584,2774,3839,81067,59193,225,8289,9295,9268,4762,2276,7641,3542,3415,1372,5538,878,5051,7631,1394,5372,2384,2050,6766,3616,7181,7605,3718,8498,7065,1369,1967,2781,7598,6562,7150,8132,1276,6656,1868,8584,9442,8762,6210,6963,4068,1605,2780,556,6825,4961,4041,4923,8660,4114};
        int[][] queries2 = {{91,244597,840227137}};
        System.out.println(
                Arrays.toString(fun6.canEat(candiesCount2,queries2))
        );
    }

    @Test
    public void testCheckSubarraySum(){
        int[] nums = {2,4,6,8,10};
        System.out.println(fun6.checkSubarraySum(nums,30));
    }

    @Test
    public void testFindMaxLength(){
        System.out.println(fun6.findMaxLength(new int[]{0,0,0,0,1,1,1,1}));
        System.out.println(fun6.findMaxLength(new int[]{0,1,0,1,0,1,0,1}));
    }

    @Test
    public void testThreeSumClosest(){
        System.out.println(fun6.threeSumClosest(
                new int[]{1,1,1,1,1,1,1,1,1},
                4
        ));
    }

}
