package cn.jinty.leetcode.function;

import cn.jinty.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/4/8
 **/
public class Fun3 {

    /**
     * 236. 二叉树的最近公共祖先
     * @param root 二叉树
     * @param p 节点P
     * @param q 节点Q
     * @return 最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        findPath(root,p,pathP);
        findPath(root,q,pathQ);
        int i=0;
        for(;i<Math.min(pathP.size(),pathQ.size());i++){
            if(pathP.get(i)!=pathQ.get(i)) break;
        }
        return pathP.get(i-1);
    }
    //先序遍历寻找target的路径
    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode>path){
        if(root==null) return false;
        //当前节点加入路径
        path.add(root);
        if(root.val == target.val){
            return true;
        }
        //向左搜寻
        boolean flag = findPath(root.left,target,path);
        if(flag) return true;
        //向右搜寻
        flag = findPath(root.right,target,path);
        if(flag) return true;
        //没有找到路径，当前节点需要删除
        path.remove(path.size()-1);
        return false;
    }

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * @param nums 旋转排序数组(存在重复值)
     * @return 最小值
     */
    public int findMin(int[] nums) {
        if(nums == null || nums.length==0) return -1;
        //定义双指针
        int left = 0, right = nums.length-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid]>nums[right]){
                //中间值大于右边值，说明最小值在右区间，且不可能是当前中间值
                left = mid+1;
            }else if(nums[mid]<nums[right]){
                //中间值小于右边值，说明最小值在左区间，且可能为当前中间值
                right = mid;
            }else{
                //中间值等于右边值，说明可以把右边值去除，不会影响最小值判断
                //在数组仅有一个值的情况下，导致时间复杂度从O(logN)退化为O(N)
                right--;
            }
        }
        return nums[left];
    }

    /**
     * 16.24. 数对和
     * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
     *
     * @param nums 数组
     * @param target 目标值
     * @return 和为目标值的数对
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int left = 0, right = nums.length-1;
        while(left<right){
            int sum = nums[left]+nums[right];
            if(sum == target){
                //收集数对
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                lists.add(list);
                //指针往中间靠拢
                left++;
                right--;
            }else if(sum > target){
                right--;
            }else{
                left++;
            }
        }
        return lists;
    }

    /**
     * 795. 区间子数组个数
     * 给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。
     * 求连续、非空且其中“最大元素”满足大于等于L小于等于R的子数组个数。
     *
     * @param A 数组
     * @param L 较小值
     * @param R 较大值
     * @return 子数组个数
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {

        /*//时间复杂度：O(N^2)
        //两层遍历：获取所有子数组，统计满足条件的子数组
        int count = 0;
        for(int i=0;i<A.length;i++){
            int max = -1;
            for(int j=i;j<A.length;j++){
                max = Math.max(max,A[j]);
                if(max<=R && max>=L){
                    count+=1;
                }else if(max>R){
                    break;
                }
            }
        }
        return count;*/

        //时间复杂度：O(N)
        //最大值在[L,R]区间内，可以理解为所有值都必须小于等于R，并且至少有一个值大于等于L
        //用f(x)表示所有元素都小于等于x的子数组数量，则本题答案为f(R)-f(L-1)
        return countSubArray(A,R) - countSubArray(A,L-1);

    }
    //统计所有元素都小于等于target的子数组数量
    private int countSubArray(int[] arr, int target){
        int count = 0;
        //满足条件的子数组长度
        int len = 1;
        for (int j : arr) {
            if (j <= target) {
                //如果有一个新的元素满足条件，那么可以新增len个子数组
                count += len++;
            } else {
                //如果有一个新的元素不满足条件，那么重置len
                len = 1;
            }
        }
        return count;
    }

}
