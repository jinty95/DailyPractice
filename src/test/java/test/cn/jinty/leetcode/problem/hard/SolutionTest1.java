package test.cn.jinty.leetcode.problem.hard;

import cn.jinty.leetcode.problem.hard.Solution1;
import cn.jinty.struct.tree.TreeNode;
import org.junit.Test;

import java.util.List;

/**
 * LeetCode - 困难题 - 测试
 *
 * @author Jinty
 * @date 2021/7/31
 **/
public class SolutionTest1 {

    private final static Solution1 solution = new Solution1();

    @Test
    public void testVerticalTraversal() {
        TreeNode root = TreeNode.deserialize(
                "[1,2,3,4,6,5,7,null,null,null,null,null,null,null,null]"
        );
        System.out.println(solution.verticalTraversal(root));
    }

    @Test
    public void testShortestPathLength() {
        System.out.println(solution.shortestPathLength(
                new int[][]{{1, 2, 3}, {0}, {0}, {0}}
        ));
    }

    @Test
    public void testNumberOfArithmeticSlices() {
        System.out.println(solution.numberOfArithmeticSlices(new int[]{1, 2, 3}));
        System.out.println(solution.numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
    }

    @Test
    public void testCheckRecord() {
        System.out.println(solution.checkRecord(1));
        System.out.println(solution.checkRecord(2));
        System.out.println(solution.checkRecord(10101));
    }

    @Test
    public void testFindMaximizedCapital() {
        System.out.println(solution.findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(solution.findMaximizedCapital(2, 2, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
    }

    @Test
    public void testFullJustify() {
        List<String> list1 = solution.fullJustify(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16
        );
        for (String str : list1) {
            System.out.println(str);
        }
        System.out.println();
        List<String> list2 = solution.fullJustify(
                new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, 20
        );
        for (String str : list2) {
            System.out.println(str);
        }
    }

    @Test
    public void testFindIntegers() {
        System.out.println(solution.findIntegers(5));
        System.out.println(solution.findIntegers(100));
    }

    @Test
    public void testFindWords() {
        System.out.println(solution.findWords(
                new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"}
        ));
        System.out.println(solution.findWords(
                new char[][]{{'a', 'a'}},
                new String[]{"aaa"}
        ));
    }

    @Test
    public void testNumDecoding() {
        System.out.println(solution.numDecoding("1*"));
        System.out.println(solution.numDecoding("20**"));
        System.out.println(solution.numDecoding("*************"));
    }

    @Test
    public void testNumberToWords() {
        System.out.println(solution.numberToWords(123));
        System.out.println(solution.numberToWords(12_345));
        System.out.println(solution.numberToWords(1_234_567));
        System.out.println(solution.numberToWords(123_456_789));
        System.out.println(solution.numberToWords(123_000_789));
    }

    @Test
    public void testAddOperators() {
        System.out.println(solution.addOperators("105", 5));
        System.out.println(solution.addOperators("123", 6));
    }

    @Test
    public void testIsSelfCrossing() {
        System.out.println(solution.isSelfCrossing(new int[]{2, 1, 1, 2}));
        System.out.println(solution.isSelfCrossing(new int[]{1, 2, 3, 4}));
        System.out.println(solution.isSelfCrossing(new int[]{1000, 1000, 1000, 2000}));
    }

    @Test
    public void testTrapRainWater() {
        System.out.println(solution.trapRainWater(new int[][]{
                {12, 13, 1, 12},
                {13, 4, 13, 12},
                {13, 8, 6, 6},
                {12, 13, 12, 3},
                {13, 13, 13, 1}
        }));
    }

    @Test
    public void testFindMinStep() {
        System.out.println(solution.findMinStep("R", "W"));
        System.out.println(solution.findMinStep("RRWWYY", "RWY"));
        System.out.println(solution.findMinStep("WBRRBBWW", "BR"));
        System.out.println(solution.findMinStep("RRYGGYYRRYGGYYRR", "GGBBB"));
    }

    @Test
    public void testIsRectangleCover() {
        System.out.println(solution.isRectangleCover(
                new int[][]{
                        {1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}
                }
        ));
    }

    @Test
    public void testFindSubstring() {
        System.out.println(solution.findSubstring("a", new String[]{"a", "b", "c"}));
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    @Test
    public void testPoorPigs() {
        System.out.println(solution.poorPigs(4, 10, 10));
        System.out.println(solution.poorPigs(1000, 15, 60));
        System.out.println(solution.poorPigs(1000, 15, 15));
        System.out.println(solution.poorPigs(100000, 15, 60));
    }

}
