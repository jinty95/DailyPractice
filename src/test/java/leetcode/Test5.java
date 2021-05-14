package leetcode;

import cn.jinty.leetcode.TreeNode;
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

}