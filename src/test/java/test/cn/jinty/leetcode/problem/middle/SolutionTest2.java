package test.cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.problem.middle.Solution2;
import cn.jinty.struct.tree.TreeNode;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode - 算法题 - 测试
 *
 * @author Jinty
 * @date 2021/9/20
 */
public class SolutionTest2 {

    private final Solution2 solution = new Solution2();

    @Test
    public void testFindNumberOfLIS() {
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(solution.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
        System.out.println(solution.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
    }

    @Test
    public void testMinDistance() {
        System.out.println(solution.minDistance("sea", "eat"));
        System.out.println(solution.minDistance("hello", "hell"));
    }

    @Test
    public void testLongestCommonSubsequence() {
        System.out.println(solution.longestCommonSubsequence("sea", "eat"));
        System.out.println(solution.longestCommonSubsequence("hello", "hell"));
    }

    @Test
    public void testLengthOfLIS() {
        System.out.println(solution.lengthOfLIS(
                new int[]{1, 2, 3, 4, 5}
        ));
        System.out.println(solution.lengthOfLIS(
                new int[]{1, 3, 1, 4, 5, 2, 3, 6, 8, 9, 10}
        ));
    }

    @Test
    public void testComputeArea() {
        System.out.println(solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        System.out.println(solution.computeArea(0, 0, 2, 2, 4, 4, 10, 10));
    }

    @Test
    public void testFractionToDecimal() {
        System.out.println(solution.fractionToDecimal(1, 2));
        System.out.println(solution.fractionToDecimal(1, 3));
        System.out.println(solution.fractionToDecimal(4, 333));
        System.out.println(solution.fractionToDecimal(-50, 8));
        System.out.println(solution.fractionToDecimal(-1, Integer.MIN_VALUE));
        System.out.println(solution.fractionToDecimal(Integer.MIN_VALUE, -1));
    }

    @Test
    public void testCountAndSay() {
        System.out.println(solution.countAndSay(1));
        System.out.println(solution.countAndSay(2));
        System.out.println(solution.countAndSay(3));
        System.out.println(solution.countAndSay(5));
        System.out.println(solution.countAndSay(30));
    }

    @Test
    public void testMajorityElement() {
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));
        System.out.println(solution.majorityElement(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testWordDictionary() {
        Solution2.WordDictionary wd = new Solution2.WordDictionary();
        System.out.println(wd.search("a"));
        wd.addWord("a");
        System.out.println(wd.search("a"));
        wd.addWord("abc");
        System.out.println(wd.search("a.c"));
    }

    @Test
    public void testShoppingOffers() {
        System.out.println(solution.shoppingOffers(
                ListUtil.asList(2, 5),
                ListUtil.asList(ListUtil.asList(3, 0, 5), ListUtil.asList(1, 2, 10)),
                ListUtil.asList(3, 2)
        ));
        System.out.println(solution.shoppingOffers(
                ListUtil.asList(9, 9),
                ListUtil.asList(ListUtil.asList(1, 1, 1)),
                ListUtil.asList(2, 2)
        ));
    }

    @Test
    public void testSearchMatrix() {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(solution.searchMatrix(matrix, 5));
        System.out.println(solution.searchMatrix(matrix, 99));
    }

    @Test
    public void testReorderedPowerOf2() {
        System.out.println(solution.reorderedPowerOf2(46));
        System.out.println(solution.reorderedPowerOf2(128));
    }

    @Test
    public void testSingleNumber() {
        System.out.println(Arrays.toString(solution.singleNumber(new int[]{0, 1})));
        System.out.println(Arrays.toString(solution.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }

    @Test
    public void testGetHint() {
        System.out.println(solution.getHint("2021", "2020"));
        System.out.println(solution.getHint("2020", "1919"));
    }

    @Test
    public void testGetMoneyAmount() {
        System.out.println(solution.getMoneyAmount(2)); // 1
        System.out.println(solution.getMoneyAmount(10)); // 16
        System.out.println(solution.getMoneyAmount(100)); // 400
    }

    @Test
    public void testBulbSwitch() {
        System.out.println(solution.bulbSwitch(1));
        System.out.println(solution.bulbSwitch(3));
        System.out.println(solution.bulbSwitch(100));
        System.out.println(solution.bulbSwitch(99999));
    }

    @Test
    public void testMaxProduct() {
        System.out.println(solution.maxProduct(new String[]{"a", "aaa", "aa"}));
        System.out.println(solution.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }

    @Test
    public void testIntegerReplacement() {
        System.out.println(solution.integerReplacement(8));
        System.out.println(solution.integerReplacement(7));
        System.out.println(solution.integerReplacement(2147483647));
    }

    @Test
    public void testEntityParser() {
        System.out.println(solution.entityParser("Stay home! Practice on Leetcode :)"));
        System.out.println(solution.entityParser("x &gt; y &amp;&amp; x &lt; y is always false"));
        System.out.println(solution.entityParser("&amp;gt;"));
        System.out.println(solution.entityParser("&amp; is an HTML entity but &ambassador; is not."));
    }

    @Test
    public void testGetAllElements() {
        System.out.println(solution.getAllElements(
                TreeNode.deserialize("[3,2,5,null,null,null,null]"),
                TreeNode.deserialize("[4,1,6,null,null,null,null]")
        ));
    }

    @Test
    public void testDelNodes() {
        System.out.println(solution.delNodes(
                TreeNode.deserialize("[2,1,3,null,null,null,null]"),
                new int[]{2}
        ));
    }

    @Test
    public void testPartitionLabels() {
        System.out.println(solution.partitionLabels("abc"));
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
    }

    @Test
    public void testKSmallestPairs() {
        System.out.println(solution.kSmallestPairs(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 3));
        System.out.println(solution.kSmallestPairs(new int[]{1, 3, 5}, new int[]{2, 4, 6}, 6));
        System.out.println(solution.kSmallestPairs(new int[]{1}, new int[]{1, 2}, 3));
    }

    @Test
    public void testMaximumUniqueSubarray() {
        System.out.println(solution.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
        System.out.println(solution.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}));
    }

    @Test
    public void testFindAnagrams() {
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("banana", "an"));
    }

    @Test
    public void testBalancedString() {
        System.out.println(solution.balancedString("QWER"));
        System.out.println(solution.balancedString("QQQQ"));
        System.out.println(solution.balancedString("WQWRQQQW"));
    }

}
