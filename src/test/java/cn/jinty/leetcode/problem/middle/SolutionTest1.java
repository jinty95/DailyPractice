package cn.jinty.leetcode.problem.middle;

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

}
