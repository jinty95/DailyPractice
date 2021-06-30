package cn.jinty.leetcode.problem.middle;

import org.junit.Test;

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

}
