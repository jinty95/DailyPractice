package leetcode.function;

import cn.jinty.leetcode.function.Fun6;
import cn.jinty.util.ArrayUtil;
import org.junit.Test;

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

}
