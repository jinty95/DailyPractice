package cn.jinty.leetcode.problem.hard;

import cn.jinty.struct.linear.ListNode;
import cn.jinty.util.ArrayUtil;
import cn.jinty.util.ListNodeUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode - 困难题 - 测试
 *
 * @author jinty
 * @date 2021/6/10
 **/
public class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    public void testMaxEnvelopes(){
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3},{6,8}};
        System.out.println(solution.maxEnvelopes(envelopes));
    }

    @Test
    public void testTrap(){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trap(height));
    }

    @Test
    public void testNumDistinct(){
        String s = "aaaabbccaabc";
        String t = "aaaab";
        System.out.println(solution.numDistinct(s,t));
    }

    @Test
    public void testMaxSlidingWindow(){
        System.out.println(Arrays.toString(
            solution.maxSlidingWindow(
                new int[]{1,3,1,2,0,5,99,1,4,65,34,22}, 3
            )
        ));
    }

    @Test
    public void testCountDigitOne(){
        System.out.println(solution.countDigitOne(100));
        System.out.println(solution.countDigitOne(1234));
        System.out.println(solution.countDigitOne(1410065408));
    }

    @Test
    public void testFindMin(){
        int[] arr = new int[]{4,4,5,5,6,6,0,1,2,3,3,3,3};
        System.out.println(solution.findMin(arr));
    }

    @Test
    public void testIsScramble(){
        System.out.println(solution.isScramble("abcde","caeff"));
        System.out.println(solution.isScramble("great","rgtea"));
        System.out.println(solution.isScramble("abcdefghijklmnopq","efghijklmnopqcadb"));
        System.out.println(solution.isScramble("eebaacbcbcadaaedceaaacadccd","eadcaacabaddaceacbceaabeccd"));
    }

    @Test
    public void testReversePairs(){
        int[] nums = {9,8,4,6,5,4,1,2,3};
        System.out.println(solution.reversePairs(nums));
    }

    @Test
    public void testMaxSumSubmatrix(){
        int[][] matrix = {
                {1,0,1},
                {0,-2,3}
        };
        System.out.println(solution.maxSumSubmatrix(matrix,2));
    }

    @Test
    public void testCanCross(){
        int[] stones1 = {1,9,10,20};
        int[] stones2 = {1,2,3,5,6,8,12,17};
        System.out.println(solution.canCross(stones1));
        System.out.println(solution.canCross(stones2));
    }

    @Test
    public void testReverseKGroup(){
        ListNode head = ListNodeUtil.fromArray(
                new int[]{1,2,3,4,5,6,7,8,9}
        );
        System.out.println(head);
        System.out.println(solution.reverseKGroup(head,9));
    }

    @Test
    public void testMinCost(){
        int[] houses = {0,0,0,0,0};
        int[][] cost = {
                {1,10},{10,1},{10,1},{1,10},{5,1}
        };
        System.out.println(solution.minCost(houses,cost,5,2,3));
    }

    @Test
    public void testMinimumTimeRequired(){
        int[] jobs = {1,2,4,7,8};
        int k = 5;
        System.out.println(solution.minimumTimeRequired(jobs,k));
    }

    @Test
    public void testIsMatch(){
        System.out.println(solution.isMatch("bbbba","b*a"));
        System.out.println(solution.isMatch("abcd",".*"));
        System.out.println(solution.isMatch("abb","c*a*b*"));
        System.out.println(solution.isMatch("abb","c*a*b*b"));
    }

    @Test
    public void testCanMatch(){
        System.out.println(solution.canMatch("aa","b"));
        System.out.println(solution.canMatch("aa","a*"));
        System.out.println(solution.canMatch("aaaa","?*aa*"));
    }

    @Test
    public void testMissingTwo(){
        System.out.println(Arrays.toString(
                solution.missingTwo(new int[]{1,2,3,4})
        ));
        System.out.println(Arrays.toString(
                solution.missingTwo(new int[]{1,2,3,5})
        ));
        System.out.println(Arrays.toString(
                solution.missingTwo(new int[]{3,4,1,6})
        ));
    }

    @Test
    public void testFirstMissingPositive(){
        System.out.println(solution.firstMissingPositive(
                new int[]{1,1,3,4,5}
        ));
        System.out.println(solution.firstMissingPositive(
                new int[]{6,2,1,3,4,5}
        ));
    }

    @Test
    public void testXorGame(){
        int[] nums1 = {1,1,2};
        System.out.println(solution.xorGame(nums1));
        int[] nums2 = {1,0};
        System.out.println(solution.xorGame(nums2));
    }

    @Test
    public void testMaximizeXor(){
        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};
        System.out.println(Arrays.toString(solution.maximizeXor(nums,queries)));
    }

    @Test
    public void testStrangePrinter(){
        System.out.println(solution.strangePrinter("aba"));
        System.out.println(solution.strangePrinter("abcbaaba"));
    }

    @Test
    public void testMergeKLists(){
        ListNode l1 = ListNodeUtil.fromArray(new int[]{1,3,5,7,9});
        ListNode l2 = ListNodeUtil.fromArray(new int[]{2,4,6,8,10});
        ListNode l3 = ListNodeUtil.fromArray(new int[]{10,11,12,13});
        ListNode[] lists = new ListNode[]{l1,l2,l3};
        System.out.println(solution.mergeKLists(lists));
    }

    @Test
    public void testNumSubmatrixSumTarget(){
        int[][] matrix = {
                {0,1,0},
                {1,1,1},
                {0,1,0}
        };
        System.out.println(solution.numSubmatrixSumTarget(matrix,0));
        System.out.println(solution.numSubmatrixSumTarget(matrix,1));
    }

    @Test
    public void testProfitableSchemes(){
        int n = 3;
        int minProfit = 1;
        int[] group = new int[]{1,1,1};
        int[] profit = new int[]{1,1,1};
        System.out.println(solution.profitableSchemes(n,minProfit,group,profit));
        n = 100;
        minProfit = 100;
        group = new int[]{18,58,88,52,54,13,50,66,83,61,100,54,60,80,1,19,78,54,67,20,57,46,12,6,14,43,64,81,30,60,48,53,86,71,51,23,71,87,95,69,11,12,41,36,69,89,91,10,98,31,67,85,16,83,83,14,14,71,33,5,40,61,22,19,34,70,50,21,91,77,4,36,16,38,56,23,68,51,71,38,63,52,14,47,25,57,95,35,58,32,1,39,48,33,89,9,1,95,90,78};
        profit = new int[]{96,77,37,98,66,44,18,37,47,9,38,82,74,12,71,31,80,64,15,45,85,52,70,53,94,90,90,14,98,22,33,39,18,22,10,46,6,19,25,50,33,15,63,93,35,0,76,44,37,68,35,80,70,66,4,88,66,93,49,19,25,90,21,59,17,40,46,79,5,41,2,37,27,92,0,53,57,91,75,0,42,100,16,97,83,75,57,61,73,21,63,97,75,95,84,14,98,47,0,13};
        System.out.println(solution.profitableSchemes(n,minProfit,group,profit));
    }

    @Test
    public void testLargestNumber(){
        int[] cost = {4,3,2,5,6,7,2,5,5};
        int target = 9;
        System.out.println(solution.largestNumber(cost,target));
    }

    @Test
    public void testMinDistance(){
        System.out.println(solution.minDistance("horse","ros"));
        System.out.println(solution.minDistance("intention","execution"));
    }

    @Test
    public void testSmallestGoodBase(){
        System.out.println(solution.smallestGoodBase("13"));
        System.out.println(solution.smallestGoodBase("1000000000000000000"));
    }

    @Test
    public void testMaxPoints(){
        int[][] points1 = {
                {1,1},{3,2},{5,3},{4,1},{2,3},{1,4},{2,0}
        };
        System.out.println(solution.maxPoints(points1));
        int[][] points2 = {
                {0,0},{4,5},{7,8},{8,9},{5,6},{3,4},{1,1}
        };
        System.out.println(solution.maxPoints(points2));
    }

    @Test
    public void testSlidingPuzzle(){
        int[][] board = {
                {4,1,2},
                {5,0,3}
        };
        System.out.println(solution.slidingPuzzle(board));
        board = new int[][]{
                {3,2,4},
                {1,5,0}
        };
        System.out.println(solution.slidingPuzzle(board));
    }

    @Test
    public void testNumBusesToDestination(){
        int[][] routes = {{1,2,7},{3,7,6}};
        System.out.println(solution.numBusesToDestination(routes,1,6));
        routes = new int[][]{{1,2,3},{3,5,6},{6,8,9}};
        System.out.println(solution.numBusesToDestination(routes,1,9));
    }

    @Test
    public void testSolveSudoku(){
        char[][] sudoku = {
            {'5','3','.',  '.','7','.',  '.','.','.'},
            {'6','.','.',  '1','9','5',  '.','.','.'},
            {'.','9','8',  '.','.','.',  '.','6','.'},

            {'8','.','.',  '.','6','.',  '.','.','3'},
            {'4','.','.',  '8','.','3',  '.','.','1'},
            {'7','.','.',  '.','2','.',  '.','.','6'},

            {'.','6','.',  '.','.','.',  '2','8','.'},
            {'.','.','.',  '4','1','9',  '.','.','5'},
            {'.','.','.',  '.','8','.',  '.','7','9'}
        };
        solution.solveSudoku(sudoku);
        ArrayUtil.print2DArray(sudoku);
    }

    @Test
    public void testCalculate(){
        System.out.println(solution.calculate("(1+1)-1+2")); //3
        System.out.println(solution.calculate("- 1 + 2 - 1 ")); //0
        System.out.println(solution.calculate("5-(4-(3-(2-1)))")); //3
        System.out.println(solution.calculate("1-(+1+1)")); //-1
        System.out.println(solution.calculate("1-(-1+1)")); //1
        System.out.println(solution.calculate("+48+-48")); //0
        System.out.println(solution.calculate("3+(4*5)-7/2")); //20
    }

    @Test
    public void testCountOfAtoms(){
        System.out.println(solution.countOfAtoms("H2O"));
        System.out.println(solution.countOfAtoms("Mg(OH)2"));
        System.out.println(solution.countOfAtoms("K4(ON(SO3)2)15"));
    }

    @Test
    public void testGetSkyline(){
        System.out.println(solution.getSkyline(new int[][]{
                {2,9,10},{3,7,15}
        }));
        System.out.println(solution.getSkyline(new int[][]{
                {2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}
        }));
        System.out.println(solution.getSkyline(new int[][]{
                {2,9,10},{3,7,10}
        }));
    }

    @Test
    public void testLongestValidParentheses(){
        System.out.println(solution.longestValidParentheses(""));
        System.out.println(solution.longestValidParentheses("(()"));
        System.out.println(solution.longestValidParentheses(")(()())"));
        System.out.println(solution.longestValidParentheses("()(()"));
        System.out.println(solution.longestValidParentheses("()(())"));
    }

}
