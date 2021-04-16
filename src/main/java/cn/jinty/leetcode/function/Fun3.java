package cn.jinty.leetcode.function;

import cn.jinty.leetcode.TreeNode;

import java.util.*;

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

    /**
     * 263. 丑数
     * 判断n是否为丑数，丑数就是只包含质因数2、3、5的正整数。
     *
     * @param n 整数
     * @return boolean
     */
    public boolean isUglyNumber(int n){
        if(n<=0) return false;
        int f = 2;
        while(n>1){
            if(n%f==0){
                n=n/f;
            }else{
                f++;
                if(f>5) return false;
            }
        }
        return true;
    }

    /**
     * 264. 丑数 II
     * 给定一个整数 n ，找出第 n 个丑数。
     *
     * @param n 整数
     * @return 第n个丑数
     */
    public int nthUglyNumber(int n) {
        /*int i=1;
        while(n>0){
            if(isUglyNumber(i++)){
                n--;
            }
        }
        return i-1;*/

        //丑数去重
        Set<Long> set = new HashSet<>();
        //小根堆保存丑数
        PriorityQueue<Long> queue = new PriorityQueue<>();
        //存入第一个丑数
        queue.add(1L);
        set.add(1L);
        //求第n个丑数
        int ans = 1;
        while(n>0){
            //由于int值溢出，故使用long
            long ugly = queue.poll();
            ans = (int)ugly;
            n--;
            long ugly2 = ugly * 2;
            long ugly3 = ugly * 3;
            long ugly5 = ugly * 5;
            if(set.add(ugly2)) queue.offer(ugly2);
            if(set.add(ugly3)) queue.offer(ugly3);
            if(set.add(ugly5)) queue.offer(ugly5);
        }
        return ans;

    }

    /**
     * 179. 最大数
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * 注意：输出结果可能非常大，所以需要返回一个字符串而不是整数。
     *
     * @param nums 非负整数数组 例如:[3,30,34]
     * @return 字符串 结果:"34330"
     */
    public String largestNumber(int[] nums) {
        //特殊情况
        if(nums==null || nums.length==0) return "";
        if(nums.length==1) return String.valueOf(nums[0]);
        //数字转为字符串
        String[] strs = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strs[i] = String.valueOf(nums[i]);
        }
        //排序 A+B > B+A
        Arrays.sort(strs,(o1,o2)->(o2+o1).compareTo(o1+o2));
        //构建结果，注意去除前导0
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<nums.length;i++){
            if(sb.length()==0 && strs[i].equals("0")) return "0";
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    /**
     * 783. 二叉搜索树节点最小距离
     *
     * @param root 二叉搜索树(有序)
     * @return 最小距离
     */
    public int minDiffInBST(TreeNode root) {
        return inOrder(root);
    }
    private TreeNode pre = null;
    private int inOrder(TreeNode root){
        int min = Integer.MAX_VALUE;
        if(root!=null){
            //递归左子树
            min = Math.min(min,inOrder(root.left));
            //逻辑处理
            if(pre!=null){
                min = Math.min(min,(Integer) root.val- (Integer) pre.val);
            }
            pre = root;
            //递归右子树
            min = Math.min(min,inOrder(root.right));
        }
        return min;
    }

    /**
     * 213. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * @param nums 数组(房屋的现金)
     * @return 偷窃的最高金额
     */
    public int rob(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int max = 0;
        //保存从第一个屋子到第i个屋子所能偷到的最大值
        int[] dp = new int[nums.length];
        //第一个屋子不偷
        dp[0] = 0; dp[1] = nums[1];
        for(int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        max = dp[nums.length-1];
        //第一个屋子偷
        dp[0] = nums[0]; dp[1] = Math.max(dp[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        max = Math.max(max,dp[nums.length-2]);
        return max;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder 中序遍历
     * @param postorder 后序遍历
     * @return 二叉树
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder,0,inorder.length-1,
                postorder,0,postorder.length-1);
    }
    private TreeNode buildTree(int[] inorder, int inLeft, int inRight,
                               int[] postorder, int postLeft, int postRight){
        if(postLeft>postRight){
            return null;
        }
        //后序遍历的特点：最后一个元素为根节点
        TreeNode root = new TreeNode(postorder[postRight]);
        //中序遍历的特点：中间元素为根节点
        //用len记录根节点的左子树元素个数
        int len = 0;
        for(int i=inLeft;i<=inRight;i++){
            if(inorder[i]==postorder[postRight]) break;
            len++;
        }
        //构造根节点的左右子树
        root.left = buildTree(
                inorder, inLeft, inLeft+len-1,
                postorder, postLeft, postLeft+len-1
        );
        root.right = buildTree(
                inorder, inLeft+len+1, inRight,
                postorder, postLeft+len, postRight-1
        );
        return root;
    }

    /**
     * 87. 扰乱字符串
     * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
     * 如果字符串的长度为 1 ，算法停止
     * 如果字符串的长度 > 1 ，执行下述步骤：
     * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
     * 随机决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
     * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
     *
     * @param s1 字符串1(小写)
     * @param s2 字符串2(小写)
     * @return 是否互为扰乱字符串
     */
    public boolean isScramble(String s1, String s2) {

        /*//长度不同
        if(s1.length()!=s2.length()) return false;
        //值相同
        if(s1.equals(s2)) return true;

        //字符种类数量是否一致
        int[] map = new int[26];
        for(int i=0;i<s1.length();i++){
            map[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            int idx = s2.charAt(i)-'a';
            if(map[idx]==0) return false;
            map[idx]--;
        }

        //s = x + y 或者 s = y + x
        //如果s1、s2互扰，那么在s1中一定存在一个点将s1分为s11,s12，在s2中一定存在一个点将s2分为s21,s22
        //使得s11、s21互扰且s12、s22互扰，或者s11、s22互扰且s12、s21互扰
        for(int i=1;i<s1.length();i++){
            String s11 = s1.substring(0,i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0,i);
            String s22 = s2.substring(i);
            if(isScramble(s11,s21) && isScramble(s12,s22)) return true;
            s21 = s2.substring(0,s1.length()-i);
            s22 = s2.substring(s1.length()-i);
            if(isScramble(s11,s22) && isScramble(s12,s21)) return true;
        }

        return false;*/

        //以上递归操作存在重复计算、可以用记忆搜索优化
        //定义int三维数组dp，dp[i][j][len]记录s1从i开始长度为len的子串与s2从j开始长度为len的子串是否互扰，0为未知，-1为否，1为是
        if(s1.length()!=s2.length()) return false;
        int len = s1.length();
        scrambleS1 = s1;
        scrambleS2 = s2;
        dp = new int[len][len][len+1];
        return isScramble(0,0,len);

    }

    private String scrambleS1;
    private String scrambleS2;
    private int[][][] dp;

    private boolean isScramble(int i,int j,int len){

        //命中记忆
        if(dp[i][j][len]!=0){
            return dp[i][j][len]==1;
        }

        //值相同
        if(scrambleS1.substring(i,i+len).equals(scrambleS2.substring(j,j+len))){
            dp[i][j][len] = 1;
            return true;
        }

        //字符种类数量是否一致
        int[] map = new int[26];
        for(int k=i;k<i+len;k++){
            map[scrambleS1.charAt(k)-'a']++;
        }
        for(int k=j;k<j+len;k++){
            int idx = scrambleS2.charAt(k)-'a';
            if(map[idx]==0){
                dp[i][j][len] = -1;
                return false;
            }
            map[idx]--;
        }

        //枚举分割点
        for(int k=1;k<len;k++){
            //不交换
            if(isScramble(i,j,k) && isScramble(i+k,j+k,len-k)){
                dp[i][j][len] = 1;
                return true;
            }
            //交换
            if(isScramble(i,j+len-k,k) && isScramble(i+k,j,len-k)){
                dp[i][j][len] = 1;
                return true;
            }
        }

        dp[i][j][len] = -1;
        return false;

    }

}
