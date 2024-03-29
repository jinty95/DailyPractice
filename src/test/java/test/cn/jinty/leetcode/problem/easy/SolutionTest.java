package test.cn.jinty.leetcode.problem.easy;

import cn.jinty.leetcode.entity.Employee;
import cn.jinty.leetcode.entity.Node;
import cn.jinty.leetcode.problem.easy.Solution;
import cn.jinty.struct.line.ListNode;
import cn.jinty.struct.tree.TreeNode;
import cn.jinty.util.collection.ListNodeUtil;
import cn.jinty.util.collection.ListUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode - 简单题 - 测试
 *
 * @author Jinty
 * @date 2021/6/10
 **/
public class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    public void testSortByBits() {
        int[] arr = {4, 5, 6, 8};
        System.out.println(Arrays.toString(arr));
        int[] ans = solution.sortByBits(arr);
        System.out.println(Arrays.toString(ans));
    }

    @Test
    public void testExchangeBits() {
        int num = 123;
        System.out.println(num);
        System.out.println(solution.exchangeBits(num));
    }

    @Test
    public void testHanota() {
        long begin = System.currentTimeMillis();
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        for (int i = 15; i > 0; i--) {
            A.add(i);
        }
        System.out.println("A列表=" + A);
        System.out.println("B列表=" + B);
        System.out.println("C列表=" + C);
        solution.hanota(A, B, C);
        System.out.println("移动结果");
        System.out.println("A列表=" + A);
        System.out.println("B列表=" + B);
        System.out.println("C列表=" + C);
        long end = System.currentTimeMillis();
        System.out.println("总耗时=" + (end - begin) + "豪秒");
    }

    @Test
    public void testLongestNiceSubstring() {
        System.out.println(solution.longestNiceSubstring("AaBbCcdOiER"));
        System.out.println(solution.longestNiceSubstring("AaBBb"));
    }

    @Test
    public void testTotalMoney() {
        System.out.println(solution.totalMoney(7));
        System.out.println(solution.totalMoney(12));
        System.out.println(solution.totalMoney(30));
    }

    @Test
    public void testFindContinuousSequence() {
        System.out.println(Arrays.deepToString(solution.findContinuousSequence(15)));
        System.out.println(Arrays.deepToString(solution.findContinuousSequence(5)));
    }

    @Test
    public void testMaxSubArray() {
        System.out.println(solution.maxSubArray(new int[]{-1, -1, 0, 1}));
        System.out.println(solution.maxSubArray(new int[]{-1, -1, 0, 1, 9, -9}));
    }

    @Test
    public void testReverseWords() {
        String s = "  hello world!  ";
        System.out.println(solution.reverseWords(s));
    }

    @Test
    public void testArrayRankTransform() {
        int[] arr1 = {40, 10, 20, 30, 20, 10};
        int[] arr2 = {};
        System.out.println(Arrays.toString(solution.arrayRankTransform(arr1)));
        System.out.println(Arrays.toString(solution.arrayRankTransform(arr2)));
    }

    @Test
    public void testIsPathCrossing() {
        String path = "NNEEEEESWNNNN";
        System.out.println(solution.isPathCrossing(path));
        path = "SN";
        System.out.println(solution.isPathCrossing(path));
        path = "WSSESEEE";
        System.out.println(solution.isPathCrossing(path));
    }

    @Test
    public void testFloodFill() {
        int[][] image = {{1, 1, 1}, {1, 0, 1}, {0, 0, 0}};
        System.out.println(Arrays.deepToString(solution.floodFill(image, 0, 0, 3)));
    }

    @Test
    public void testMaxLengthBetweenEqualCharacters() {
        System.out.println(solution.maxLengthBetweenEqualCharacters("abc"));
        System.out.println(solution.maxLengthBetweenEqualCharacters("abccba"));
    }

    @Test
    public void testConvertBiNode() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(TreeNode.bfs(root));
        System.out.println(TreeNode.bfs(solution.convertBiNode(root)));
    }

    @Test
    public void testRemoveDuplicates() {
        System.out.println(solution.removeDuplicates("abaabbbac"));
        System.out.println(solution.removeDuplicates("aaaa"));
    }

    @Test
    public void testRomanToInt() {
        System.out.println(solution.romanToInt("MMDCCC"));
        System.out.println(solution.romanToInt("MMMMDCCCLVII"));
    }

    @Test
    public void testGetLeastNumbers() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(solution.getLeastNumbers(arr, 4)));
        System.out.println(Arrays.toString(solution.getLeastNumbers(arr, 1)));
    }

    @Test
    public void testHammingWeight() {
        System.out.println(Integer.bitCount(31));
        System.out.println(solution.hammingWeight(31));
        System.out.println(Integer.bitCount(15));
        System.out.println(solution.hammingWeight(15));
    }

    @Test
    public void testDeleteDuplicates() {
        ListNode head2 = ListNodeUtil.fromArray(
                new int[]{1, 1, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5}
        );
        System.out.println(solution.deleteDuplicate(head2));
    }

    @Test
    public void testLastRemaining() {
        System.out.println(solution.lastRemaining(10, 5));
        System.out.println(solution.lastRemaining(100, 2));
    }

    @Test
    public void testIsBalanced() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        System.out.println(solution.isBalanced(root));
    }

    @Test
    public void testIsStraight() {
        System.out.println(solution.isStraight(new int[]{0, 0, 1, 4, 5}));
        System.out.println(solution.isStraight(new int[]{0, 4, 6, 7, 8}));
        System.out.println(solution.isStraight(new int[]{1, 3, 5, 6, 7}));
        System.out.println(solution.isStraight(new int[]{0, 0, 2, 2, 5}));
    }

    @Test
    public void testUglyNumber() {
        System.out.println(solution.isUglyNumber(10));
        System.out.println(solution.isUglyNumber(12));
    }

    @Test
    public void testMinDiffInBST() {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(40);
        root.right = new TreeNode(57);
        System.out.println(solution.minDiffInBST(root));
    }

    @Test
    public void testRemoveElement() {
        int[] arr = new int[]{1, 1, 1, 1, 1, 4, 5, 6, 8};
        System.out.println(solution.removeElement(arr, 1));
        System.out.println(solution.removeElement(arr, 8));
    }

    @Test
    public void testStrStr() {
        String s1 = "hello world";
        String s2 = "el";
        String s3 = "hello world !";
        String s4 = "hello world";
        System.out.println(solution.strStr(s1, s2));
        System.out.println(solution.strStr(s1, s3));
        System.out.println(solution.strStr(s1, s4));
    }

    @Test
    public void testIncreasingBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(TreeNode.serialize(root));
        TreeNode newHead = solution.increasingBST(root);
        System.out.println(TreeNode.serialize(newHead));
    }

    @Test
    public void testRangeSumBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(9);
        System.out.println(solution.rangeSumBST(root, 3, 9));
        System.out.println(solution.rangeSumBST(root, 3, 8));
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {1, 9, 3, 10, 9, 88, 100};
        System.out.println(solution.maxProfit(prices));
    }

    @Test
    public void testMaxProfit2() {
        int[] prices = {1, 9, 3, 10, 9, 88, 100};
        System.out.println(solution.maxProfit2(prices));
    }

    @Test
    public void testGetImportance() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, 10, Arrays.asList(2, 3, 4)));
        list.add(new Employee(2, 5, null));
        list.add(new Employee(3, 7, null));
        list.add(new Employee(4, 8, null));
        System.out.println(solution.getImportance(list, 1));
        System.out.println(solution.getImportance(list, 2));
    }

    @Test
    public void testReverse() {
        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(1534236469));
    }

    @Test
    public void testLeafSimilar() {
        TreeNode tree1 = new TreeNode(4);
        tree1.left = new TreeNode(3);
        tree1.right = new TreeNode(5);
        System.out.println(solution.leafSimilar(tree1, tree1));
        TreeNode tree2 = new TreeNode(4);
        tree2.left = new TreeNode(3);
        tree2.right = new TreeNode(5);
        System.out.println(solution.leafSimilar(tree1, tree2));
    }

    @Test
    public void testIsCousins() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        System.out.println(solution.isCousins(root, 3, 7));
        System.out.println(solution.isCousins(root, 5, 3));
    }

    @Test
    public void testPeakIndexInMountainArray() {
        int[] arr = {0, 1, 2, 3, 4, 3, 2, 1};
        System.out.println(solution.peakIndexInMountainArray(arr));
    }

    @Test
    public void testReadBinaryWatch() {
        System.out.println(solution.readBinaryWatch(1));
        System.out.println(solution.readBinaryWatch(4));
    }

    @Test
    public void testConvertToTitle() {
        System.out.println(solution.convertToTitle(28));
        System.out.println(solution.convertToTitle(26));
        System.out.println(solution.convertToTitle(52));
        System.out.println(solution.convertToTitle(703));
    }

    @Test
    public void testNumWays() {
        System.out.println(solution.numWays(
                5,
                new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}},
                3
        ));
    }

    @Test
    public void testAddStrings() {
        System.out.println(solution.addStrings("45", "55"));
        System.out.println(solution.addStrings("1", "99999999999999999999999"));
    }

    @Test
    public void testFindErrorNumbers() {
        System.out.println(Arrays.toString(solution.findErrorNumbers(new int[]{1, 1})));
        System.out.println(Arrays.toString(solution.findErrorNumbers(new int[]{1, 1, 2, 3, 4, 6, 7, 8})));
    }

    @Test
    public void testMajorityElement() {
        System.out.println(solution.majorityElement(new int[]{1, 2, 3}));
        System.out.println(solution.majorityElement(new int[]{1, 1, 1, 2, 3, 4, 1}));
    }

    @Test
    public void testSearch() {
        System.out.println(solution.search(new int[]{2, 2, 5, 5, 6}, 5));
        System.out.println(solution.search(new int[]{1, 2}, 3));
    }

    @Test
    public void testMaximumTime() {
        System.out.println(solution.maximumTime("1?:5?"));
        System.out.println(solution.maximumTime("?0:15"));
    }

    @Test
    public void testFindSecondMinimumValue() {
        System.out.println(solution.findSecondMinimumValue(
                TreeNode.deserialize("[1,1,3,null,null,3,7,null,null,null,null]")
        ));
    }

    @Test
    public void testKWeakestRows() {
        int[][] arr = new int[][]{{1, 1, 1, 0}, {1, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}};
        System.out.println(Arrays.toString(solution.kWeakestRows(
                arr, 3
        )));
        System.out.println(Arrays.toString(solution.kWeakestRows(
                arr, 1
        )));
    }

    @Test
    public void testTribonacci() {
        System.out.println(solution.tribonacci(0));
        System.out.println(solution.tribonacci(3));
        System.out.println(solution.tribonacci(25));
    }

    @Test
    public void testReverseVowels() {
        System.out.println(solution.reverseVowels("hello"));
        System.out.println(solution.reverseVowels("leetcode"));
        System.out.println(solution.reverseVowels("Aa"));
    }

    @Test
    public void testReverseStr() {
        System.out.println(solution.reverseStr("hello", 1));
        System.out.println(solution.reverseStr("congratulation", 2));
    }

    @Test
    public void testSumOddLengthSubArrays() {
        System.out.println(solution.sumOddLengthSubArrays(
                new int[]{1, 4, 2, 5, 3}
        ));
    }

    @Test
    public void testBalancedStringSplit() {
        System.out.println(solution.balancedStringSplit("RLRRLLRLRL"));
        System.out.println(solution.balancedStringSplit("LR"));
    }

    @Test
    public void testLengthOfLastWord() {
        System.out.println(solution.lengthOfLastWord("hello world "));
        System.out.println(solution.lengthOfLastWord("good job"));
    }

    @Test
    public void testLicenseKeyFormatting() {
        System.out.println(solution.licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(solution.licenseKeyFormatting("2-5g-3-J", 2));
    }

    @Test
    public void testThirdMax() {
        System.out.println(solution.thirdMax(new int[]{1, 2}));
        System.out.println(solution.thirdMax(new int[]{1, 2, 2, 2, 3}));
    }

    @Test
    public void testFindComplement() {
        System.out.println(solution.findComplement(5));
        System.out.println(solution.findComplement(666));
    }

    @Test
    public void testMinMoves() {
        System.out.println(solution.minMoves(new int[]{1, 1, 1}));
        System.out.println(solution.minMoves(new int[]{1, 2, 3}));
        System.out.println(solution.minMoves(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testNextGreaterElement() {
        System.out.println(Arrays.toString(solution.nextGreaterElement(
                new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}
        )));
    }

    @Test
    public void testMissingNumber() {
        System.out.println(solution.missingNumber(new int[]{0, 1, 2}));
        System.out.println(solution.missingNumber(new int[]{0, 1, 2, 4}));
        System.out.println(solution.missingNumber(new int[]{2, 0, 3}));
    }

    @Test
    public void testMaxCount() {
        System.out.println(solution.maxCount(3, 3, new int[][]{{2, 2}, {3, 3}}));
        System.out.println(solution.maxCount(4, 4, new int[][]{{1, 2}, {3, 1}}));
    }

    @Test
    public void testFindPoisonedDuration() {
        System.out.println(solution.findPoisonedDuration(new int[]{1, 2}, 2));
        System.out.println(solution.findPoisonedDuration(new int[]{1, 4}, 2));
    }

    @Test
    public void testDetectCapitalUse() {
        System.out.println(solution.detectCapitalUse("China"));
        System.out.println(solution.detectCapitalUse("USA"));
        System.out.println(solution.detectCapitalUse("HellO"));
    }

    @Test
    public void testFindTilt() {
        System.out.println(solution.findTilt(TreeNode.deserialize("[1,2,3,null,null,null,null]")));
    }

    @Test
    public void testFindLHS() {
        System.out.println(solution.findLHS(new int[]{1, 1, 1, 1, 1}));
        System.out.println(solution.findLHS(new int[]{1, 2, 2, 1, 3, 1, 2, 3, 2, 1}));
        System.out.println(solution.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
    }

    @Test
    public void testMaxDepth() {
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        root.children = ListUtil.asList(child1, child2);
        System.out.println(solution.maxDepth(root));
        System.out.println(solution.maxDepth(null));
    }

}
