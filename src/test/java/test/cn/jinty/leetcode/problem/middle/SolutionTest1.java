package test.cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.problem.middle.Solution1;
import cn.jinty.struct.tree.TreeNode;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * LeetCode - 算法题 - 测试
 *
 * @author jinty
 * @date 2021/6/25
 **/
public class SolutionTest1 {

    private final Solution1 solution = new Solution1();

    @Test
    public void testOpenLock(){
        String[] deadEnds = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(solution.openLock(deadEnds,target));
    }

    @Test
    public void testSnakesAndLadders(){
        int[][] board = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println(solution.snakesAndLadders(board));
    }

    @Test
    public void testIsValidSudoku(){
        char[][] sudoku = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(solution.isValidSudoku(sudoku));
    }

    @Test
    public void testCalculate(){
        System.out.println(solution.calculate("1+5*3-2/1"));
        System.out.println(solution.calculate("8+9-2*5/3"));
        System.out.println(solution.calculate("14"));
        System.out.println(solution.calculate("1+1-2+3"));
    }

    @Test
    public void testMultiply(){
        System.out.println(solution.multiply("5","10"));
        System.out.println(solution.multiply("123","456"));
    }

    @Test
    public void testRestoreIpAddresses(){
        System.out.println(solution.restoreIpAddresses("0000"));
        System.out.println(solution.restoreIpAddresses("255255255255"));
        System.out.println(solution.restoreIpAddresses("101023"));
        System.out.println(solution.restoreIpAddresses("20210702"));
    }

    @Test
    public void testFrequencySort(){
        System.out.println(solution.frequencySort("hello"));
        System.out.println(solution.frequencySort("congratulation"));
    }

    @Test
    public void testNextPermutation(){
        int[] numbers = {0,1,4,6,5,3,2};
        solution.nextPermutation(numbers);
        System.out.println(Arrays.toString(numbers));
        numbers = new int[]{0,1,2,3,4,5,6};
        solution.nextPermutation(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    @Test
    public void testDisplayTable(){
        List<String> order1 = ListUtil.asList("David","3","Ceviche");
        List<String> order2 = ListUtil.asList("Corina","10","Beef Burrito");
        List<String> order3 = ListUtil.asList("David","3","Fried Chicken");
        List<List<String>> orders = ListUtil.asList(order1,order2,order3);
        System.out.println(solution.displayTable(orders));
    }

    @Test
    public void testCountPairs(){
        System.out.println(solution.countPairs(new int[]{1,3,5,7,9}));
        System.out.println(solution.countPairs(new int[]{3,3,3,1,1,1,7}));
        System.out.println(solution.countPairs(new int[]{64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64}));
    }

    @Test
    public void testNumSubArraysWithSum(){
        System.out.println(solution.numSubArraysWithSum(new int[]{0,1,0,1,0},2));
        System.out.println(solution.numSubArraysWithSum(new int[]{0,0,0,0,0},0));
    }

    @Test
    public void testHIndex(){
        System.out.println(solution.hIndex(new int[]{1,0,3,5,6}));
        System.out.println(solution.hIndex(new int[]{6,6,6,6,6,6}));
        System.out.println(solution.hIndex(new int[]{100,100,100}));
    }

    @Test
    public void testCombinationSum2(){
        System.out.println(solution.combinationSum2(new int[]{1,1,1,1,2},3));
        System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }

    @Test
    public void testMinAbsoluteSumDiff(){
        System.out.println(solution.minAbsoluteSumDiff(
                new int[]{1,7,5}, new int[]{2,3,5}
        ));
        System.out.println(solution.minAbsoluteSumDiff(
                new int[]{1,10,4,4,2,7}, new int[]{9,3,5,1,7,4}
        ));
        System.out.println(solution.minAbsoluteSumDiff(
                new int[]{100,10,101}, new int[]{99,1,99}
        ));
    }

    @Test
    public void testMaximumElementAfterDecrementingAndRearranging(){
        System.out.println(solution.maximumElementAfterDecrementingAndRearranging(
                new int[]{2,1,2,2,1}
        ));
        System.out.println(solution.maximumElementAfterDecrementingAndRearranging(
                new int[]{1,2,2,100,100}
        ));
    }

    @Test
    public void testMaxFrequency(){
        System.out.println(solution.maxFrequency(
                new int[]{1,4,8,13},5
        ));
        System.out.println(solution.maxFrequency(
                new int[]{1,2,4},5
        ));
    }

    @Test
    public void testGrayCode(){
        System.out.println(solution.grayCode(2));
        System.out.println(solution.grayCode(3));
    }

    @Test
    public void testRestoreArray(){
        System.out.println(Arrays.toString(solution.restoreArray(
                new int[][]{{1, 3}, {-4, 1}, {2, 4}, {-4, 4}}
        )));
    }

    @Test
    public void testMaximalSquare(){
        System.out.println(solution.maximalSquare(
                new char[][]{
                        {'1','1'},{'1','1'},{'1','1'}
                }
        ));
    }

    @Test
    public void testDistanceK(){
        TreeNode root = TreeNode.deserialize("[1,2,3,null,null,null,null]");
        TreeNode target = root.left;
        System.out.println(solution.distanceK(root,target,1));
        System.out.println(solution.distanceK(root,target,2));
    }

    @Test
    public void testPathInZigZagTree(){
        System.out.println(solution.pathInZigZagTree(1));
        System.out.println(solution.pathInZigZagTree(14));
        System.out.println(solution.pathInZigZagTree(26));
    }

    @Test
    public void testNetworkDelayTime(){
        System.out.println(solution.networkDelayTime(
                new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2
        ));
        System.out.println(solution.networkDelayTime(
                new int[][]{{1,2,1}}, 2, 2
        ));
        System.out.println(solution.networkDelayTime(
                new int[][]{{1,2,1},{2,1,3}}, 2, 2
        ));
        System.out.println(solution.networkDelayTime(
                new int[][]{{1,2,1},{2,3,2},{1,3,4}}, 3, 1
        ));
    }

    @Test
    public void testFindUnsortedSubArray(){
        System.out.println(solution.findUnsortedSubArray(new int[]{2,6,4,8,10,9,15}));
        System.out.println(solution.findUnsortedSubArray(new int[]{1,2,3,4,5,6,7}));
    }

    @Test
    public void testTriangleNumber(){
        System.out.println(solution.triangleNumber(new int[]{2,2,3,4}));
        System.out.println(solution.triangleNumber(new int[]{1,2,3,4,5,6,7,8}));
    }

    @Test
    public void testEventualSafeNodes(){
        System.out.println(solution.eventualSafeNodes(
                new int[][]{
                        {1,2},{2,3},{5},{0},{5},{},{}
                }
        ));
    }

    @Test
    public void testCircularArrayLoop(){
        System.out.println(solution.circularArrayLoop(new int[]{1}));
        System.out.println(solution.circularArrayLoop(new int[]{2,-1,1,2,2}));
        System.out.println(solution.circularArrayLoop(new int[]{-1,-2,-3,-4,-5}));
        System.out.println(solution.circularArrayLoop(new int[]{-2,-3,-9}));
        System.out.println(solution.circularArrayLoop(new int[]{1,5,100}));
    }

    @Test
    public void testNumberOfArithmeticSlices(){
        System.out.println(solution.numberOfArithmeticSlices(new int[]{1,2,3,4,5,6,7,8,9,10}));
        System.out.println(solution.numberOfArithmeticSlices(new int[]{1,2,3,4,5,6,7,8,10,12,14,16}));
    }

    @Test
    public void testLongestPalindromeSubSeq(){
        System.out.println(solution.longestPalindromeSubSeq("hello"));
        System.out.println(solution.longestPalindromeSubSeq("bbbab"));
    }

    @Test
    public void testUnhappyFriends(){
        System.out.println(solution.unhappyFriends(
                4,
                new int[][]{{1, 2, 3},{3, 2, 0},{3, 1, 0},{1, 2, 0}},
                new int[][]{{0, 1},{2, 3}}
        ));
        System.out.println(solution.unhappyFriends(
                6,
                new int[][]{{1,4,3,2,5},{0,5,4,3,2},{3,0,1,5,4},{2,1,4,0,5},{2,1,0,3,5},{3,4,2,0,1}},
                new int[][]{{3,1},{2,0},{5,4}}
        ));
    }

    @Test
    public void testFindPaths(){
        System.out.println(solution.findPaths(2,2,2,0,0));
        System.out.println(solution.findPaths(1,3,3,0,1));
        System.out.println(solution.findPaths(7,6,13,0,2));
    }

    @Test
    public void testCountArrangement(){
        System.out.println(solution.countArrangement(2));
        System.out.println(solution.countArrangement(15));
    }

    @Test
    public void testCompress(){
        System.out.println(solution.compress(new char[]{'a','b','b','c','c'}));
        System.out.println(solution.compress(new char[]{'a','b','b','b'}));
        System.out.println(solution.compress(new char[]{'a','a','b','b','b','c','c','c','c','c','c'}));
    }

    @Test
    public void testEscapeGhosts(){
        System.out.println(solution.escapeGhosts(
                new int[][]{{1,0},{0,3}}, new int[]{0,1}
        ));
    }

    @Test
    public void testFindCheapestPrice(){
        System.out.println(solution.findCheapestPrice(
                3, new int[][]{{0,1,100},{1,2,100},{0,2,500}},
                0, 2, 1
        ));
        System.out.println(solution.findCheapestPrice(
                17, new int[][]{{0,12,28},{5,6,39},{8,6,59},{13,15,7},{13,12,38},{10,12,35},{15,3,23},
        {7,11,26},{9,4,65},{10,2,38},{4,7,7},{14,15,31},{2,12,44},{8,10,34},{13,6,29},{5,14,89},{11,16,13},
        {7,3,46},{10,15,19},{12,4,58},{13,16,11},{16,4,76},{2,0,12},{15,0,22},{16,12,13},{7,1,29},{7,14,100},
        {16,1,14},{9,6,74},{11,1,73},{2,11,60},{10,11,85},{2,5,49},{3,4,17},{4,9,77},{16,3,47},{15,6,78},
        {14,1,90},{10,5,95},{1,11,30},{11,0,37},{10,4,86},{0,8,57},{6,14,68},{16,8,3},{13,0,65},{2,13,6},
        {5,13,5},{8,11,31},{6,10,20},{6,2,33},{9,1,3},{14,9,58},{12,3,19},{11,2,74},{12,14,48},{16,11,100},
        {3,12,38},{12,13,77},{10,9,99},{15,13,98},{15,12,71},{1,4,28},{7,0,83},{3,5,100},{8,9,14},{15,11,57},
        {3,6,65},{1,3,45},{14,7,74},{2,10,39},{4,8,73},{13,5,77},{10,0,43},{12,9,92},{8,2,26},{1,7,7},
        {9,12,10},{13,11,64},{8,13,80},{6,12,74},{9,7,35},{0,15,48},{3,7,87},{16,9,42},{5,16,64},{4,5,65},
        {15,14,70},{12,0,13},{16,14,52},{3,10,80},{14,11,85},{15,2,77},{4,11,19},{2,7,49},{10,7,78},
        {14,6,84},{13,7,50},{11,6,75},{5,10,46},{13,8,43},{9,10,49},{7,12,64},{0,10,76},{5,9,77},{8,3,28},
        {11,9,28},{12,16,87},{12,6,24},{9,15,94},{5,7,77},{4,10,18},{7,2,11},{9,5,41}},
                13, 4, 13
        ));
        System.out.println(solution.findCheapestPrice(
                13, new int[][]{{11,12,74},{1,8,91},{4,6,13},{7,6,39},{5,12,8},{0,12,54},{8,4,32},{0,11,4},{4,0,91},
        {11,7,64},{6,3,88},{8,5,80},{11,10,91},{10,0,60},{8,7,92},{12,6,78},{6,2,8},{4,3,54},{3,11,76},
        {3,12,23},{11,6,79},{6,12,36},{2,11,100},{2,5,49},{7,0,17},{5,8,95},{3,9,98},{8,10,61},{2,12,38},
        {5,7,58},{9,4,37},{8,6,79},{9,0,1},{2,3,12},{7,10,7},{12,10,52},{7,2,68},{12,2,100},{6,9,53},
        {7,4,90},{0,5,43},{11,2,52},{11,8,50},{12,4,38},{7,9,94},{2,7,38},{3,7,88},{9,12,20},{12,0,26},
        {10,5,38},{12,8,50},{0,2,77},{11,0,13},{9,10,76},{2,6,67},{5,6,34},{9,7,62},{5,3,67}},
                10, 1, 10
        ));
    }

    @Test
    public void testAllPathsSourceTarget(){
        System.out.println(solution.allPathsSourceTarget(
                new int[][]{
                        {4,3,1},{3,2,4},{3},{4},{}
                }
        ));
    }

    @Test
    public void testNumRescueBoats(){
        System.out.println(solution.numRescueBoats(
                new int[]{1,2}, 3
        ));
        System.out.println(solution.numRescueBoats(
                new int[]{3,3,4,5}, 5
        ));
        System.out.println(solution.numRescueBoats(
                new int[]{3,3,1,2}, 3
        ));
    }

    @Test
    public void testCorpFlightBookings(){
        System.out.println(Arrays.toString(solution.corpFlightBookings(
                new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5
        )));
    }

    @Test
    public void testCompareVersion(){
        System.out.println(solution.compareVersion("1.0","1"));
        System.out.println(solution.compareVersion("1.0.002","1.0.2"));
        System.out.println(solution.compareVersion("1.0.0024","1.0.2"));
        System.out.println(solution.compareVersion("0.0.001","0.1.001"));
    }

    @Test
    public void testRand10(){
        System.out.println(solution.rand10());
        System.out.println(solution.rand10());
        System.out.println(solution.rand10());
        System.out.println(solution.rand10());
        System.out.println(solution.rand10());
    }

}
