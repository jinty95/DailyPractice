package test.cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.entity.Node;
import cn.jinty.leetcode.problem.middle.Solution;
import cn.jinty.struct.linear.ListNode;
import cn.jinty.struct.tree.TreeNode;
import cn.jinty.util.ArrayUtil;
import cn.jinty.util.ListNodeUtil;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode - 中等题 - 测试
 *
 * @author Jinty
 * @date 2021/6/10
 **/
public class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    public void testRotate() {
        int[][] matrix = {{2, 29, 20, 26, 16, 28}, {12, 27, 9, 25, 13, 21}, {32, 33, 32, 2, 28, 14}, {13, 14, 32, 27, 22, 26}, {33, 1, 20, 7, 21, 7}, {4, 24, 1, 6, 32, 34}};
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("---分割线---");
        solution.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    @Test
    public void testFindClosest() {
        String[] words = {
                "i", "am", "a", "good", "student", ".", "am", "i", "?"
        };
        System.out.println(solution.findClosest(words, "i", "student"));
        System.out.println(solution.findClosest(words, "i", "a"));
    }

    @Test
    public void testOddEvenList() {
        ListNode head = ListNodeUtil.fromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(head);
        ListNode node = solution.oddEvenList(head);
        System.out.println(node);
    }

    @Test
    public void testMinSetSize() {
        int[] arr = {1, 1, 2, 2, 3, 4, 5, 6};
        System.out.println(solution.minSetSize(arr));
    }

    @Test
    public void testGenerateParenthesis() {
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(2));
    }

    @Test
    public void testLexicalOrder() {
        Long begin = System.currentTimeMillis();
        System.out.println(solution.lexicalOrder(100));
        Long end = System.currentTimeMillis();
        System.out.println("执行耗时：" + (end - begin));
    }

    @Test
    public void testFindCircleNum() {
        int[][] isConnected = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(solution.findCircleNum(isConnected));
    }

    @Test
    public void testTrulyMostPopular() {
        String[] names = {"Mary(30)", "Merry(70)", "Lucy(20)", "Lucky(30)", "Mike(15)"};
        String[] synonyms = {"(Mary,Merry)", "(Lucy,Lucky)", "(Lucy,Lily)"};
        System.out.println(Arrays.toString(solution.trulyMostPopular(names, synonyms)));
    }

    @Test
    public void testLengthOfLongestSubstring() {
        System.out.println(solution.lengthOfLongestSubstring("hello"));
        System.out.println(solution.lengthOfLongestSubstring("world"));
    }

    @Test
    public void testCheckArithmeticSubArrays() {
        System.out.println(solution.checkArithmeticSubArrays(
                new int[]{5, 3, 7, 9, 1},
                new int[]{0, 0, 2},
                new int[]{4, 3, 3}
                )
        );
    }

    @Test
    public void testMergeInBetween() {
        //链表位置从0开始，第0个，第1个，以此类推
        ListNode list1 = ListNodeUtil.fromArray(new int[]{1, 2, 3, 4, 5, 6});
        ListNode list2 = ListNodeUtil.fromArray(new int[]{7, 8, 9});
        System.out.println(solution.mergeInBetween(list1, 2, 3, list2));
    }

    @Test
    public void testDeckRevealedIncreasing() {
        System.out.println(
                Arrays.toString(solution.deckRevealedIncreasing(new int[]{17, 13, 11, 2, 3, 5, 7}))
        );
        System.out.println(
                Arrays.toString(solution.deckRevealedIncreasing(new int[]{17, 13}))
        );
    }

    @Test
    public void testBuildTree1() {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode treeNode = solution.buildTree1(pre, in);
        System.out.println(treeNode.preorder());
        System.out.println(treeNode.inorder());
    }

    @Test
    public void testBuildTree2() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = solution.buildTree2(inorder, postorder);
        System.out.println(root.inorder());
        System.out.println(root.postorder());
    }

    @Test
    public void testReconstructQueue() {
        int[][] people = {
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        };
        System.out.println(Arrays.deepToString(solution.reconstructQueue(people)));
    }

    @Test
    public void testThreeSum() {
        int[] arr = {-1, 0, 1, 2, -1, 8, -4, -4, -1, 0, 0, 0};
        System.out.println(solution.threeSum(arr));
    }

    @Test
    public void testLongestPalindrome() {
        System.out.println(solution.longestPalindrome("aaaa"));
        System.out.println(solution.longestPalindrome("teacher"));
    }

    @Test
    public void testIntToRoman() {
        System.out.println(solution.intToRoman(2800));
        System.out.println(solution.intToRoman(4857));
    }

    @Test
    public void testMyAtoi() {
        System.out.println(solution.myAtoi("   -2147483647   "));
        System.out.println(solution.myAtoi("1995"));
    }

    @Test
    public void testFourSum() {
        int[] arr = {-2, -1, -1, 1, 1, 2, 2};
        int target = 0;
        System.out.println(solution.fourSum(arr, target));
    }

    @Test
    public void testMerge() {
        int[][] intervals = {{1, 4}, {2, 6}, {6, 9}, {5, 7}, {19, 20}, {99, 100}, {98, 101}};
        System.out.println(Arrays.deepToString(solution.merge(intervals)));
    }

    @Test
    public void testGroupAnagrams() {
        String[] strs = {"nozzle", "onlezz", "abc", "bca", "tall", "llat"};
        System.out.println(strs.length);
        List<List<String>> lists = solution.groupAnagrams(strs);
        int len = 0;
        for (List<String> list : lists) {
            len += list.size();
        }
        System.out.println(len);
        System.out.println(solution.groupAnagrams(strs));
    }

    @Test
    public void testProcessQueries() {
        int[] queries = {4, 1, 2, 2};
        System.out.println(Arrays.toString(solution.processQueries(queries, 4)));
    }

    @Test
    public void testCanJump() {
        int[] arr = {3, 2, 1, 0, 4};
        System.out.println(solution.canJump(arr));
    }

    @Test
    public void testSearch() {
        int[] arr = {4, 9, 0, 1, 2, 3};
        System.out.println(solution.search(arr, 0));
    }

    @Test
    public void testIsValidBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        System.out.println(solution.isValidBST(root));
    }

    @Test
    public void testIsValidSerialization() {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(solution.isValidSerialization(preorder));
    }

    @Test
    public void testSpiralOrder() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(solution.spiralOrder(matrix));
    }

    @Test
    public void testCanCompleteCircuit() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(solution.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testReverseBetween() {
        ListNode head = ListNodeUtil.fromArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(solution.reverseBetween(head, 1, 5));
    }

    @Test
    public void testValidateStackSequences() {
        System.out.println(solution.validateStackSequences(
                new int[]{1, 2, 3, 4, 5},
                new int[]{5, 4, 1, 2, 3}
        ));
    }

    @Test
    public void testFindNthDigit() {
        System.out.println(solution.findNthDigit(15));
        System.out.println(solution.findNthDigit(21));
    }

    @Test
    public void testMinNumber() {
        System.out.println(
                solution.minNumber(new int[]{0, 1, 10, 100, 3, 32, 85, 859, 32, 322})
        );
    }

    @Test
    public void testFind132pattern() {
        System.out.println(
                solution.find132pattern(
                        new int[]{1, 3, 2, 4, 5, 6, 7, 8, 9, 10}
                )
        );
    }

    @Test
    public void testDeleteDuplicates() {
        System.out.println(solution.deleteDuplicates(ListNodeUtil.fromArray(
                new int[]{1, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5})));
        System.out.println(solution.deleteDuplicates(ListNodeUtil.fromArray(
                new int[]{1, 1, 2, 3, 3, 3, 4, 5, 5, 5, 6, 7})));
    }

    @Test
    public void testConstructArr() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(solution.constructArr(arr)));
    }

    @Test
    public void testVerifyPostorder() {
        System.out.println(solution.verifyPostorder(
                new int[]{10, 6, 9}
        ));
        System.out.println(solution.verifyPostorder(
                new int[]{1, 2, 5, 10, 6, 9, 4, 3}
        ));
    }

    @Test
    public void testPathSum() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(3);
        System.out.println(solution.pathSum(root, 8));
    }

    @Test
    public void testSearchMatrix() {
        int[][] matrix = {
                {1, 3, 5, 7},
                {9, 10, 15, 19},
                {22, 29, 33, 45}
        };
        System.out.println(solution.searchMatrix(matrix, 3));
    }

    @Test
    public void testSubsetsWithDup() {
        int[] arr = {4, 4, 4, 1, 4};
        System.out.println(solution.subsetsWithDup(arr));
    }

    @Test
    public void testIsSubStructure() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        System.out.println(solution.isSubStructure(root, root));
    }

    @Test
    public void testPermutation() {
        System.out.println(Arrays.toString(solution.permutation("abcaaa")));
        System.out.println(Arrays.toString(solution.permutation("abcd")));
    }

    @Test
    public void testClumsy() {
        System.out.println(solution.clumsy(4));
        System.out.println(solution.clumsy(10));
    }

    @Test
    public void testRemoveDuplicates() {
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 1, 1, 1, 1, 1, 2}));
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4}));
    }

    @Test
    public void testFindMin() {
        int[] arr1 = new int[]{4, 5, 6, 7, 0, 1, 2, 3};
        System.out.println(solution.findMin(arr1));
        int[] arr2 = new int[]{50, 60, 70, 80, 90, 99, 5, 6, 7, 8, 9, 10, 20, 30, 40};
        System.out.println(solution.findMin(arr2));
    }

    @Test
    public void testLowestCommonAncestor() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(4);
        root.left = left;
        root.right = right;
        System.out.println(solution.lowestCommonAncestor(root, left, right).val);
        System.out.println(solution.lowestCommonAncestor(root, left, left).val);
        System.out.println(solution.lowestCommonAncestor(root, right, right).val);
    }

    @Test
    public void testPairSums() {
        int[] arr = new int[]{4, 5, 6, 7, 1, 2, 3, 7, 9, 8, 2};
        System.out.println(solution.pairSums(arr, 10));
    }

    @Test
    public void testNumSubarrayBoundedMax() {
        int[] arr = new int[]{2, 1, 4, 3};
        System.out.println(solution.numSubarrayBoundedMax(arr, 2, 3));
    }

    @Test
    public void testNthUglyNumber() {
        System.out.println(solution.nthUglyNumber(3));
        System.out.println(solution.nthUglyNumber(5));
    }

    @Test
    public void testNthSuperUglyNumber() {
        System.out.println(solution.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        System.out.println(solution.nthSuperUglyNumber(1, new int[]{2, 3, 5}));
    }

    @Test
    public void testLargestNumber() {
        int[] nums1 = {30, 33, 4, 1, 9, 566};
        System.out.println(solution.largestNumber(nums1));
        int[] nums2 = {0, 0, 0, 0, 0};
        System.out.println(solution.largestNumber(nums2));
        int[] nums3 = {1, 0, 2, 0, 0};
        System.out.println(solution.largestNumber(nums3));
    }

    @Test
    public void testRob() {
        int[] nums = new int[]{1, 9, 2, 8, 100};
        System.out.println(solution.rob(nums));
    }

    @Test
    public void testContainsNearbyAlmostDuplicate() {
        System.out.println(solution.containsNearbyAlmostDuplicate(
                new int[]{1, 5, 9, 1, 5, 9}, 2, 3)
        );
        System.out.println(solution.containsNearbyAlmostDuplicate(
                new int[]{-2147483648, 2147483647}, 1, 1)
        );
    }

    @Test
    public void testTreeToDoublyList() {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        System.out.println(root);
        System.out.println(solution.treeToDoublyList(root));
    }

    @Test
    public void testCopyRandomList() {
        Node head = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        head.next = n1;
        n1.next = n2;
        head.random = n2;
        n1.random = head;
        n2.random = n1;
        System.out.println(head);
        System.out.println(solution.copyRandomList(head));
    }

    @Test
    public void testNumDecodings() {
        System.out.println(solution.numDecodings("111"));
        System.out.println(solution.numDecodings("10101011"));
        System.out.println(solution.numDecodings("2612321"));
        System.out.println(solution.numDecodings("00000"));
        System.out.println(solution.numDecodings("10001"));
    }

    @Test
    public void testLargestDivisibleSubset() {
        int[] arr = {2, 3, 4, 8};
        System.out.println(Arrays.toString(arr));
        System.out.println(solution.largestDivisibleSubset(arr));
    }

    @Test
    public void testIntegerBreak() {
        System.out.println(solution.integerBreak(10));
        System.out.println(solution.integerBreak(54));
        System.out.println(solution.integerBreak(450));
    }

    @Test
    public void testCuttingRope() {
        System.out.println(solution.cuttingRope(10));
        System.out.println(solution.cuttingRope(200));
    }

    @Test
    public void testCombinationSum4() {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(solution.combinationSum4(nums, target));
    }

    @Test
    public void testCoinChange() {
        int[] coins = {186, 419, 83, 408};
        int amount1 = 6249;
        int amount3 = 1;
        int amount4 = 0;
        System.out.println(solution.coinChange(coins, amount1));
        System.out.println(solution.coinChange(coins, amount3));
        System.out.println(solution.coinChange(coins, amount4));
    }

    @Test
    public void testChange() {
        int[] coins = {1, 2, 5, 10};
        int amount1 = 20;
        System.out.println(solution.change(amount1, coins));
    }

    @Test
    public void testShipWithinDays() {
        int[] weights = {
                361, 321, 186, 186, 67, 283, 36, 471, 304, 218, 60, 78, 149, 166, 282,
                384, 61, 242, 426, 275, 236, 221, 27, 261, 487, 90, 468, 19, 453, 241
        };
        int D = 15;
        System.out.println(solution.shipWithinDays(weights, D));
    }

    @Test
    public void testMinEatingSpeed() {
        int[] piles = {4, 6, 8, 9, 11, 5};
        int h = 12;
        System.out.println(solution.minEatingSpeed(piles, h));
    }

    @Test
    public void testJudgeSquareSum() {
        System.out.println(solution.judgeSquareSum(1));
        System.out.println(solution.judgeSquareSum(4));
        System.out.println(solution.judgeSquareSum(85));
        System.out.println(solution.judgeSquareSum(12132321));
        System.out.println(solution.judgeSquareSum(Integer.MAX_VALUE));
    }

    @Test
    public void testNumIslands() {
        char[][] grid = {
                {'1', '1', '0'},
                {'0', '0', '0'},
                {'1', '1', '1'}
        };
        System.out.println(solution.numIslands(grid));
    }

    @Test
    public void testSortedListToBST() {
        ListNode head = ListNodeUtil.fromArray(new int[]{3, 4, 5});
        System.out.println(solution.sortedListToBST(head));
    }

    @Test
    public void testKClosest() {
        int[][] points = {
                {1, 3}, {2, -2}
        };
        System.out.println(Arrays.deepToString(solution.kClosest(points, 1)));
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {1, 9, 3, 10, 9, 88, 100};
        System.out.println(solution.maxProfit(prices, 7));
    }

    @Test
    public void testMaxProfitWithFreeze() {
        int[] prices = {1, 9, 3, 10, 9, 88, 100};
        System.out.println(solution.maxProfitWithFreeze(prices));
    }

    @Test
    public void testSingleNumber() {
        int[] nums = {0, 9, 0, 0, 8, 3, 8, 8, 9, 9};
        System.out.println(solution.singleNumber(nums));
    }

    @Test
    public void testLeastBricks() {
        List<List<Integer>> wall = new ArrayList<>();
        List<Integer> row1 = Collections.singletonList(6);
        List<Integer> row2 = Arrays.asList(2, 3);
        List<Integer> row3 = Arrays.asList(1, 5);
        wall.add(row1);
        wall.add(row2);
        wall.add(row3);
        System.out.println(solution.leastBricks(wall));
    }

    @Test
    public void testDeleteAndEarn() {
        int[] nums1 = new int[]{4, 10, 10, 8, 1, 4, 10, 9, 7, 6};
        int[] nums2 = new int[]{1, 1, 1, 1, 1, 1};
        System.out.println(solution.deleteAndEarn(nums1));
        System.out.println(solution.deleteAndEarn(nums2));
    }

    @Test
    public void testMaxIceCream() {
        int[] costs = {1, 2, 3, 4, 1};
        System.out.println(solution.maxIceCream(costs, 7));
        System.out.println(solution.maxIceCream(costs, 9));
    }

    @Test
    public void testMaxTurbulenceSize() {
        int[] arr = {2, 4, 8, 3, 5, 2, 6, 1};
        System.out.println(solution.maxTurbulenceSize(arr));
    }

    @Test
    public void testIsNumber() {
        System.out.println("+" + "=" + solution.isNumber("+"));
        System.out.println("." + "=" + solution.isNumber("."));
        System.out.println("+." + "=" + solution.isNumber("+."));
        System.out.println("  +.5  " + "=" + solution.isNumber("  +.5  "));
        System.out.println("4.4.4" + "=" + solution.isNumber("4.4.4"));
        System.out.println("1a3.14" + "=" + solution.isNumber("1a3.14"));
        System.out.println("5E1.4" + "=" + solution.isNumber("5E1.4"));
        System.out.println("12e+" + "=" + solution.isNumber("12e+"));
        System.out.println("+e15" + "=" + solution.isNumber("+e15"));
        System.out.println("-1E-16" + "=" + solution.isNumber("-1E-16"));
        System.out.println("-+5" + "=" + solution.isNumber("-+5"));
        System.out.println("-5" + "=" + solution.isNumber("-5"));
    }

    @Test
    public void testMinDays() {
        int[] bloomDay = {1, 2, 3, 4, 5, 6};
        System.out.println(solution.minDays(bloomDay, 3, 2));
        System.out.println(solution.minDays(bloomDay, 3, 1));
        System.out.println(solution.minDays(bloomDay, 2, 2));
    }

    @Test
    public void testDecode() {
        int[] encoded1 = {3, 1};
        System.out.println(Arrays.toString(solution.decode(encoded1)));
        int[] encoded2 = {6, 5, 4, 6};
        System.out.println(Arrays.toString(solution.decode(encoded2)));
    }

    @Test
    public void testXorQueries() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[][] queries = {{0, 1}, {2, 3}, {1, 5}};
        System.out.println(Arrays.toString(solution.xorQueries(arr, queries)));
    }

    @Test
    public void testExist() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}

        };
        String word1 = "ABCESEEEFS";
        System.out.println(solution.exist(board, word1));
    }

    @Test
    public void testMovingCount() {
        int m = 16, n = 8;
        int k = 4;
        System.out.println(solution.movingCount(m, n, k));
    }

    @Test
    public void testNumWays() {
        System.out.println(solution.numWays(1, 5));
        System.out.println(solution.numWays(3, 100));
        System.out.println(solution.numWays(430, 148488));
    }

    @Test
    public void testDicesProbability() {
        System.out.println(Arrays.toString(solution.dicesProbability(1)));
        System.out.println(Arrays.toString(solution.dicesProbability(2)));
    }

    @Test
    public void testUniquePaths() {
        System.out.println(solution.uniquePaths(3, 7));
        System.out.println(solution.uniquePaths(1, 1));
    }

    @Test
    public void testFindMaximumXOR() {
        System.out.println(solution.findMaximumXOR(new int[]{5}));
        System.out.println(solution.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    @Test
    public void testStoneGame() {
        System.out.println(solution.stoneGame(new int[]{5, 3, 4, 5}));
        System.out.println(solution.stoneGame(new int[]{5, 8, 5, 3}));
    }

    @Test
    public void testCountTriplets() {
        System.out.println(solution.countTriplets(new int[]{2, 3, 1, 6, 7}));
        System.out.println(solution.countTriplets(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testKthLargestValue() {
        int[][] matrix = {{5, 2}, {1, 6}};
        System.out.println(solution.kthLargestValue(matrix, 1));
        System.out.println(solution.kthLargestValue(matrix, 2));
    }

    @Test
    public void testTopKFrequent() {
        String[] words = {
                "the", "day", "is", "sunny", "the", "day",
                "the", "the", "sunny", "is", "is", "is"
        };
        System.out.println(solution.topKFrequent(words, 4));
    }

    @Test
    public void testMaxUncrossedLines() {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {2, 1, 4, 3, 5};
        System.out.println(solution.maxUncrossedLines(nums1, nums2));
    }

    @Test
    public void testFindRadius() {
        int[] houses = {282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923};
        int[] heater = {823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612};
        System.out.println(solution.findRadius(houses, heater));
    }

    @Test
    public void testMaxSumDivThree() {
        int[] nums = {3, 5, 6, 1, 8};
        System.out.println(solution.maxSumDivThree(nums));
    }

    @Test
    public void testReverseParentheses() {
        String s = "(ed(et(oc))el)";
        System.out.println(solution.reverseParentheses(s));
    }

    @Test
    public void testGameOfLife() {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        System.out.println(Arrays.deepToString(board));
        solution.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }

    @Test
    public void testConvert() {
        System.out.println(solution.convert("ABC", 1));
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void testWordBreak() {
        System.out.println(solution.wordBreak(
                "cn/jinty/leetcode", ListUtil.asList("leet", "code"))
        );
        System.out.println(solution.wordBreak(
                "anappleaday", ListUtil.asList("an", "apple", "a", "day"))
        );
    }

    @Test
    public void testFindTargetSumWays() {
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        System.out.println(solution.findTargetSumWays(nums, 1));
    }

    @Test
    public void testAddTwoNumbers() {
        ListNode l1 = ListNodeUtil.fromArray(new int[]{9, 9, 9});
        ListNode l2 = new ListNode(1);
        System.out.println(solution.addTwoNumbers(l1, l2));
    }

    @Test
    public void testTotalHammingDistance() {
        System.out.println(solution.totalHammingDistance(new int[]{4, 14, 2}));
        System.out.println(solution.totalHammingDistance(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testLeastInterval() {
        System.out.println(solution.leastInterval(new char[]{'A', 'B', 'C', 'D'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'D'}, 2));
    }

    @Test
    public void testNumSquares() {
        System.out.println(solution.numSquares(10));
        System.out.println(solution.numSquares(12));
    }

    @Test
    public void testDailyTemperatures() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(temperatures));
        System.out.println(Arrays.toString(solution.dailyTemperatures(temperatures)));
    }

    @Test
    public void testFindDuplicate() {
        System.out.println(solution.findDuplicate(new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(solution.findDuplicate(new int[]{1, 8, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void testSubarraySum() {
        System.out.println(solution.subarraySum(new int[]{1, 1, 1, 1, 1}, 2));
        System.out.println(solution.subarraySum(new int[]{-2, 2, -2, 2, -2, 2}, 2));
        System.out.println(solution.subarraySum(new int[]{1}, 0));
    }

    @Test
    public void testAssignTasks() {
        int[] servers1 = {1, 2, 3, 4, 5, 6};
        int[] tasks1 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution.assignTasks(servers1, tasks1)));
        int[] servers2 = {31, 96, 73, 90, 15, 11, 1, 90, 72, 9, 30, 88};
        int[] tasks2 = {87, 10, 3, 5, 76, 74, 38, 64, 16, 64, 93, 95, 60, 79, 54, 26, 30, 44, 64, 71};
        System.out.println(Arrays.toString(solution.assignTasks(servers2, tasks2)));
    }

    @Test
    public void testGetBiggestThree() {
        int[][] grid = {
                {3, 4, 5, 1, 3},
                {3, 3, 4, 2, 3},
                {20, 30, 200, 40, 10},
                {1, 5, 5, 4, 1},
                {4, 3, 2, 2, 5}
        };
        System.out.println(Arrays.toString(solution.getBiggestThree(grid)));
    }

    @Test
    public void testOriginalDigits() {
        System.out.println(solution.originalDigits("onetwothreefourfive"));
        System.out.println(solution.originalDigits("sixseveneightninezero"));
    }

    @Test
    public void testCanEat() {
        int[] candiesCount1 = {7, 4, 5, 3, 8};
        int[][] queries1 = {{0, 2, 2}, {4, 2, 4}, {2, 13, 1000000000}};
        System.out.println(Arrays.toString(solution.canEat(candiesCount1, queries1)));
        int[] candiesCount2 = {5215, 14414, 67303, 93431, 44959, 34974, 22935, 64205, 28863, 3436, 45640, 34940, 38519, 5705, 14594, 30510, 4418, 87954, 8423, 65872, 79062, 83736, 47851, 64523, 15639, 19173, 88996, 97578, 1106, 17767, 63298, 8620, 67281, 76666, 50386, 97303, 26476, 95239, 21967, 31606, 3943, 33752, 29634, 35981, 42216, 88584, 2774, 3839, 81067, 59193, 225, 8289, 9295, 9268, 4762, 2276, 7641, 3542, 3415, 1372, 5538, 878, 5051, 7631, 1394, 5372, 2384, 2050, 6766, 3616, 7181, 7605, 3718, 8498, 7065, 1369, 1967, 2781, 7598, 6562, 7150, 8132, 1276, 6656, 1868, 8584, 9442, 8762, 6210, 6963, 4068, 1605, 2780, 556, 6825, 4961, 4041, 4923, 8660, 4114};
        int[][] queries2 = {{91, 244597, 840227137}};
        System.out.println(Arrays.toString(solution.canEat(candiesCount2, queries2)));
    }

    @Test
    public void testCheckSubArraySum() {
        int[] nums = {2, 4, 6, 8, 10};
        System.out.println(solution.checkSubArraySum(nums, 30));
    }

    @Test
    public void testFindMaxLength() {
        System.out.println(solution.findMaxLength(new int[]{0, 0, 0, 0, 1, 1, 1, 1}));
        System.out.println(solution.findMaxLength(new int[]{0, 1, 0, 1, 0, 1, 0, 1}));
    }

    @Test
    public void testThreeSumClosest() {
        System.out.println(solution.threeSumClosest(
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1},
                4
        ));
    }

    @Test
    public void testSumSubarrayMins() {
        int[] nums = {3, 1, 2, 4};
        System.out.println(solution.sumSubarrayMins(nums));
    }

    @Test
    public void testFindMaxForm() {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        System.out.println(solution.findMaxForm(strs, 5, 3));
    }

    @Test
    public void testLastStoneWeightII() {
        int[] stones = new int[]{31, 26, 33, 21, 40};
        System.out.println(solution.lastStoneWeightII(stones));
    }

    @Test
    public void testLargestMagicSquare() {
        int[][] grid = {
                {7, 1, 4, 5, 6},
                {2, 5, 1, 6, 4},
                {1, 5, 4, 3, 2},
                {1, 2, 7, 3, 4}
        };
        System.out.println(solution.largestMagicSquare(grid));
    }

    @Test
    public void testMaximumRemovals() {
        String s = "abcacb";
        String p = "ab";
        int[] removable = {3, 1, 0};
        System.out.println(solution.maximumRemovals(s, p, removable));
    }

    @Test
    public void testLongestSubstring() {
        System.out.println(solution.longestSubstring("abc", 3));
        System.out.println(solution.longestSubstring("aabbbbcc", 3));
    }

    @Test
    public void testSmallestK() {
        int[] arr = {1, 5, 8, 2, 9, 3, 4, 7, 0, 6};
        int k = 2;
        System.out.println(Arrays.toString(solution.smallestK(arr, k)));
    }

    @Test
    public void testMinOperations() {
        int[] arr = {1, 1, 3, 9, 10, 2, 3};
        int k = 10;
        System.out.println(solution.minOperations(arr, k));
    }

    @Test
    public void testMaxLength() {
        System.out.println(solution.maxLength(Arrays.asList("abc", "ad", "efg")));
        System.out.println(solution.maxLength(Arrays.asList("abcdefg", "hijklmn", "opqrst", "uvwxyz")));
    }

}
