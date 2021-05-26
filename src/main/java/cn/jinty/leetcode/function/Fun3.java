package cn.jinty.leetcode.function;

import cn.jinty.leetcode.entity.Node;
import cn.jinty.leetcode.tree.TreeNode;

import java.math.BigInteger;
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

        //1、使用List<TreeNode>分别收集p和q的路径，找最后一个公共节点
        /*List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        findPath(root,p,pathP);
        findPath(root,q,pathQ);
        int i=0;
        for(;i<Math.min(pathP.size(),pathQ.size());i++){
            if(pathP.get(i)!=pathQ.get(i)) break;
        }
        return pathP.get(i-1);*/

        //2、使用Map<TreeNode,TreeNode>保存节点及其父节点，基于Map得到路径，找最后一个公共节点
        /*Map<TreeNode,TreeNode> map = new HashMap<>();
        nodeParentMap(root,map);
        List<TreeNode> pathP = new ArrayList<>();
        while(p!=null){
            pathP.add(p);
            p = map.get(p);
        }
        List<TreeNode> pathQ = new ArrayList<>();
        while(q!=null){
            pathQ.add(q);
            q = map.get(q);
        }
        int i=pathP.size()-1, j=pathQ.size()-1;
        while(i>=0 && j>=0){
            if(pathP.get(i)!=pathQ.get(j)){
                return pathP.get(i+1);
            }
            i--;
            j--;
        }
        return pathP.get(i+1);*/

        //3、后序遍历，只有最近公共祖先p和q会出现在节点两侧
        if(root==null) return null;
        if(root==p || root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null) return root;
        return left != null ? left : right;

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
    //先序遍历构建所有节点与父节点的映射关系
    private void nodeParentMap(TreeNode root, Map<TreeNode,TreeNode> map){
        if(root==null) return;
        if(root.left!=null){
            map.put(root.left,root);
        }
        if(root.right!=null){
            map.put(root.right,root);
        }
        nodeParentMap(root.left,map);
        nodeParentMap(root.right,map);
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
                min = Math.min(min,root.val-pre.val);
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

    /**
     * 220. 存在重复元素 III
     * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在两个不同下标 i 和 j，
     * 使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
     *
     * @param nums 数组
     * @param k 整数
     * @param t 整数
     * @return 是否存在
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        /*//暴力穷举，时间复杂度O(kn)，执行超时
        for(int i=0;i<nums.length;i++){
            int j = Math.max(i - k, 0);
            for( ; j<nums.length && j<=i+k; j++){
                if(j==i) continue;
                if(Math.abs((long)nums[i]-(long)nums[j])<=t) return true;
            }
        }
        return false;*/

        /*//上述过程存在重复计算，可以用记忆搜索优化，时间复杂度O(kn/2)，执行超时
        //dp[i][j]表示nums[i]-nums[j]是否<=t，-1为否，0为未知，1为是
        int[][] dp = new int[nums.length][nums.length];
        for(int i=0;i<nums.length;i++){
            int j = Math.max(i - k, 0);
            for( ; j<nums.length && j<=i+k; j++){
                if(j==i) continue;
                if(dp[i][j]==0 && dp[j][i]==0){
                    if(Math.abs((long)nums[i]-(long)nums[j])<=t){
                        dp[i][j] = 1;
                        dp[j][i] = 1;
                        return true;
                     }else{
                        dp[i][j] = -1;
                        dp[j][i] = -1;
                    }
                }
            }
        }
        return false;*/

        //使用滑动窗口+有序集合，时间复杂度O(nlogk)
        //对于i，需要与左边k个求绝对值，与右边k个求绝对值，但是右边的计算其实是多余的，所以只需要考虑i的左边k个
        //使用TreeSet有序集合作为滑动窗口，窗口中最多存放k个元素，超出时旧的一个元素去除，新的元素加入
        //从左向右遍历nums，更新滑动窗口，如果窗口中存在一个值，介于[nums[i]-t,nums[i]+t]之间，那么返回true
        TreeSet<Long> set = new TreeSet<>();
        for(int i=0;i<nums.length;i++){
            Long ceiling = set.floor((long)nums[i]+t);
            if(ceiling!=null && ceiling>=(long)nums[i]-t){
                return true;
            }
            set.add((long)nums[i]);
            if(i>=k){
                set.remove((long)nums[i-k]);
            }
        }
        return false;

    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * @param nums 数组
     * @param val 整数
     * @return 移除后的数组长度
     */
    public int removeElement(int[] nums, int val) {
        if(nums==null || nums.length==0) return 0;
        //双指针
        int slow = 0, fast = 0;
        while(fast<nums.length){
            if(nums[fast]!=val){
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
     * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * @param root 二叉搜索树
     * @return 双向链表
     */
    public Node treeToDoublyList(Node root) {
        if(root==null) return root;
        //收集节点路径
        List<Node> list = new ArrayList<>();
        inOrder(root,list);
        //根据相邻节点更新每个节点的指针域
        for(int i=0;i<list.size();i++){
            Node now = list.get(i);
            if(i==0) now.left = list.get(list.size()-1);
            else now.left = list.get(i-1);
            if(i==list.size()-1) now.right = list.get(0);
            else now.right = list.get(i+1);
        }
        return list.get(0);
    }
    //中序遍历，收集节点
    private void inOrder(Node root,List<Node> list){
        if(root==null) return;
        inOrder(root.left,list);
        list.add(root);
        inOrder(root.right,list);
    }

    /**
     * 剑指 Offer 51. 数组中的逆序对
     *
     * @param nums 数组
     * @return 逆序对的数量
     */
    public int reversePairs(int[] nums) {

        /*//暴力枚举 时间复杂度O(N^2)
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    count++;
                }
            }
        }
        return count;*/

        /*//单调递减栈 时间复杂度：数组递减情况下O(N)，数组递增情况下O(N^2)
        int count = 0;
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(queue.isEmpty()) queue.push(nums[i]);
            else{
                if(nums[i]<queue.peek()){
                    count += queue.size();
                    queue.push(nums[i]);
                }else{
                    List<Integer> list = new ArrayList<>();
                    while(!queue.isEmpty() && nums[i]>=queue.peek()){
                        list.add(queue.pop());
                    }
                    count += queue.size();
                    queue.push(nums[i]);
                    for(int j=list.size()-1;j>=0;j--){
                        queue.push(list.get(j));
                    }
                }
            }
        }
        return count;*/

        //归并排序 过程中计算逆序对 时间复杂度O(NlogN)
        if(nums==null || nums.length==0) return 0;
        mergeSort(nums,0,nums.length-1);
        return reversePairCount;

    }
    //成员变量记录逆序对数量
    private int reversePairCount = 0;
    //归并排序
    private void mergeSort(int[] arr,int begin,int end){
        if(begin<end){
            //+的优先级比>>>高，所以要加括号
            int mid = begin + ((end-begin)>>>1);
            mergeSort(arr,begin,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,begin,mid,end);
        }
    }
    //有序表的合并
    private void merge(int[] arr,int begin,int mid,int end){
        int[] temp = new int[end-begin+1];
        int i=0;
        int leftBgin = begin;
        int rightBegin = mid+1;
        while(leftBgin<=mid && rightBegin<=end){
            if(arr[leftBgin]<=arr[rightBegin]){
                temp[i++] = arr[leftBgin++];
            }else{
                temp[i++] = arr[rightBegin++];
                reversePairCount += (mid - leftBgin + 1);
            }
        }
        while(leftBgin<=mid){
            temp[i++] = arr[leftBgin++];
        }
        while(rightBegin<=end){
            temp[i++] = arr[rightBegin++];
        }
        for(int j=begin;j<=end;j++){
            arr[j] = temp[j-begin];
        }
    }

    /**
     * 28. 实现 strStr()
     * 给定两个字符串haystack和needle ，请在haystack字符串中找出needle字符串出现的第一个位置（下标从0开始）。如果不存在，则返回-1。
     *
     * @param haystack 主串
     * @param needle 子串
     * @return 首次出现的位置
     */
    public int strStr(String haystack, String needle) {

        /*//直接调包
        if(haystack==null) return -1;
        if(needle==null) return 0;
        return haystack.indexOf(needle);*/

        //枚举
        if(haystack==null) return -1;
        if(needle==null || needle.length()==0) return 0;
        int max = haystack.length()-needle.length();
        for(int i=0;i<=max;i++){
            boolean found = true;
            for(int j=0;j<needle.length();j++){
                if(haystack.charAt(i+j)!=needle.charAt(j)){
                    found = false;
                    break;
                }
            }
            if(found) return i;
        }
        return -1;

    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     *
     * @param head 链表
     * @return 复制链表
     */
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        //新节点与旧节点的映射
        Map<Node,Node> newToOld = new HashMap<>();
        //旧节点与新节点的映射
        Map<Node,Node> oldToNew = new HashMap<>();
        //新建节点，保证next一致
        Node newHead = null;
        Node tmp = newHead;
        while(head!=null){
            if(tmp==null){
                newHead = new Node(head.val);
                tmp = newHead;
            } else {
                tmp.next = new Node(head.val);
                tmp = tmp.next;
            }
            newToOld.put(tmp,head);
            oldToNew.put(head,tmp);
            head = head.next;
        }
        //实现random一致
        tmp = newHead;
        while(tmp!=null){
            //根据新节点找到对应旧节点，找到旧节点的random，再找这个random对应的新节点
            tmp.random = oldToNew.get(newToOld.get(tmp).random);
            tmp = tmp.next;
        }
        return newHead;
    }

    /**
     * 91. 解码方法
     * 一条包含字母A-Z的消息通过以下映射进行了编码 ：'A' -> 1 'B' -> 2 ... 'Z' -> 26
     * 要解码已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
     *
     * @param s 数字字符串
     * @return 解码种数
     */
    public int numDecodings(String s) {
        //非法情况
        if(s==null || s.length()==0 || s.charAt(0)=='0') return 0;
        //动态规划
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for(int i=1;i<s.length();i++){
            char cur = s.charAt(i);
            char pre = s.charAt(i-1);
            if(cur=='0'){
                //非法情况：0不能与前一个数构成10或20
                if(pre=='0' || pre>'2') return 0;
                dp[i] = (i==1) ? 1 : dp[i-2];
            } else {
                if(pre=='0') dp[i] = dp[i-1];
                else if(pre=='1' || (pre=='2'&&cur<'7')) dp[i] = dp[i-1] + ((i==1) ? 1 : dp[i-2]);
                else dp[i] = dp[i-1];
            }
        }
        return dp[s.length()-1];
    }

    /**
     * 363. 矩形区域不超过K的最大数值和
     * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
     * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
     *
     * @param matrix 矩阵
     * @param k 整数
     * @return 不超过k的最大数值和
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix==null || matrix.length==0) return -1;
        Integer ans = null;
        int row = matrix.length;
        int col = matrix[0].length;
        //sum[i][j]表示matrix[0][0]到matrix[i][j]矩阵区域的数值和
        int[][] sum = new int[row][col];
        sum[0][0] = matrix[0][0];
        //第一行
        for(int i=1;i<col;i++){
            sum[0][i] = sum[0][i-1] + matrix[0][i];
        }
        //第一列
        for(int i=1;i<row;i++){
            sum[i][0] = sum[i-1][0] + matrix[i][0];
        }
        //其它
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + matrix[i][j];
            }
        }
        //需要求任意点与matrix[i][j]组成的矩阵区域的数值和
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int m=i;m<row;m++){
                    for(int n=j;n<col;n++){
                        int diff = 0;
                        if(i==0&&j==0) diff = sum[m][n];
                        else if(i==0) diff = sum[m][n]-sum[m][j-1];
                        else if(j==0) diff = sum[m][n]-sum[i-1][n];
                        else diff = sum[m][n]-sum[m][j-1]-sum[i-1][n]+sum[i-1][j-1];
                        if(diff<=k){
                            if(ans==null) ans = diff;
                            else ans = Math.max(ans,diff);
                            //最大为k，可以提前剪枝
                            if(ans==k) return ans;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 368. 最大整除子集
     * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
     * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
     * answer[i] % answer[j] == 0 且 answer[j] % answer[i] == 0
     * 如果存在多个有效解子集，返回其中任何一个均可。
     *
     * @param nums 数组
     * @return 最大整除子集
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {

        /*//回溯算法 时间复杂度O(2^N)
        if(nums==null || nums.length==0) return answer;
        //升序排序
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        //从最大值开始遍历，收集一个倒序的整除子集
        largestDivisibleSubset(nums,nums.length-1,list);
        return answer;*/

        //动态规划 时间复杂度O(N^2)
        if(nums==null || nums.length==0) return null;
        //升序排序
        Arrays.sort(nums);
        //构建dp数组，dp[i]表示以nums[i]为最大值的子集大小
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = dp[0];
        for(int i=1;i<nums.length;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i]%nums[j]==0){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max,dp[i]);
        }
        //根据dp数组，反推子集
        List<Integer> list = new ArrayList<>();
        Integer maxVal = null;
        for(int i=dp.length-1; i>=0; i--){
            if(dp[i]==max){
                if(maxVal==null || maxVal%nums[i]==0){
                    list.add(nums[i]);
                    maxVal=nums[i];
                    max--;
                }
            }
        }
        return list;

    }
    private List<Integer> answer = null;
    private void largestDivisibleSubset(int[] nums, int idx,List<Integer> list){
        if(idx==-1){
            //元素收集完毕，判断是否比answer长
            if(answer==null || answer.size()<list.size()) answer = new ArrayList<>(list);
        }else{
            //从最大值开始遍历，收集一个倒序的整除子集
            for(int i=idx;i>=0;i--){
                //如果从当前往下收集已经不可能大于answer，提前终止
                if(answer!=null && list.size()+i+1 <= answer.size()) break;
                //与整除子集中的最小值比较即可，若能整除，子集中的其它值肯定也能整除
                if(list.size()==0 || list.get(list.size()-1)%nums[i]==0){
                    list.add(nums[i]);
                    //递归
                    largestDivisibleSubset(nums,i-1,list);
                    //回溯
                    list.remove(list.size()-1);
                }else if(i==0){
                    //递归：保证idx可以走到-1
                    largestDivisibleSubset(nums,i-1,list);
                }
            }
        }
    }

    /**
     * 剑指 Offer 67. 把字符串转换成整数
     *
     * @param str 字符串
     * @return 整数
     */
    public int strToInt(String str) {
        //空串
        if(str==null) return 0;
        str = str.trim();
        if(str.length()==0) return 0;
        //收集有效数字
        StringBuilder sb = new StringBuilder();
        //是否为负数
        boolean negative = false;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(i==0 && c=='-') negative=true;
            else if(i==0 && c=='+') negative = false;
            else if(c>='0' && c<='9'){
                //去除无意义的前导0
                if(sb.length()==0 && c=='0') continue;
                sb.append(c);
            }
            else break;
        }
        str = sb.toString();
        //无法解析
        if(str.length()==0) return 0;
        //溢出
        if(str.length()>10) return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        if(str.length()==10 && negative && str.compareTo("2147483648")>0) return Integer.MIN_VALUE;
        if(str.length()==10 && !negative && str.compareTo("2147483647")>0) return Integer.MAX_VALUE;
        //正常情况
        int answer = 0;
        int multiple = 1;
        for(int i=str.length()-1;i>=0;i--){
            int d = str.charAt(i)-'0';
            answer += d*multiple;
            multiple *= 10;
        }
        return negative ? -answer : answer;
    }

    /**
     * 343. 整数拆分
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
     * 返回你可以获得的最大乘积。
     *
     * @param n 整数(>1)
     * @return 最大乘积
     */
    public int integerBreak(int n) {
        /*//枚举，时间复杂度O(N^N)
        int max = 0;
        //整数至少拆成两个，每个数的范围都在[1,n-1]
        //枚举其中的一个数，讨论另外的数
        for(int i=1;i<n;i++){
            //如果其中有一个数为i，那么另一个数为n-i，它可以继续拆，也可以不拆
            max = Math.max(
                    Math.max(i*(n-i), i*integerBreak(n-i)),
                    max
            );
        }
        return max;*/

        //从枚举法中可以看出，f(n)的计算依赖于f(1)~f(n-1)，而且存在重复计算
        //例如：f(10)的求解需要计算f(1)~f(9)，而f(11)的求解需要计算f(1)~f(10)
        //那么可以用记忆表记录已经求解过的答案，基于这些答案推出下一步的答案，形成递推过程

        //动态规划，时间复杂度O(N^2)
        //dp[i]表示i拆分后的最大乘积
        int[] dp = new int[n+1];
        dp[1] = 1; dp[2] = 1;
        for(int i=3;i<=n;i++){
            for(int j=1;j<i;j++){
                dp[i] = Math.max(dp[i],j*(i-j));
                dp[i] = Math.max(dp[i],j*dp[i-j]);
            }
        }
        return dp[n];

    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * @param n 绳子长度
     * @return 最大乘积
     */
    public int cuttingRope(int n) {
        //动态规划，时间复杂度O(N^2)
        int max = 0;
        //dp[i]表示i拆分后的最大乘积
        //这里需要使用大整数，因为只有加法可以阶段性取余，乘法必须先计算全部值的乘积，最后才可以取余
        BigInteger[] dp = new BigInteger[n+1];
        dp[1] = BigInteger.valueOf(1);
        dp[2] = BigInteger.valueOf(1);
        for(int i=3;i<=n;i++){
            dp[i] = BigInteger.valueOf(0);
            for(int j=1;j<i;j++){
                BigInteger bj = BigInteger.valueOf(j);
                BigInteger b1 = bj.multiply(BigInteger.valueOf(i-j));
                BigInteger b2 = bj.multiply(dp[i-j]);
                BigInteger greater = b1.compareTo(b2)>0 ? b1 : b2;
                dp[i]= dp[i].compareTo(greater)>0 ? dp[i] : greater;
            }
        }
        return dp[n].remainder(BigInteger.valueOf(1000000007)).intValue();
    }

    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
     * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * 注意：
     * 1、顺序不同的序列被视作不同的组合。
     * 2、数组中的元素可以重复利用。
     *
     * @param nums 数组(大于0的正整数)
     * @param target 目标(大于0的正整数)
     * @return 组合总和
     */
    public int combinationSum4(int[] nums, int target) {
        if(nums==null) return 0;
        //动态规划
        //dp[i]表示总和为i的方案数
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i=1;i<dp.length;i++){
            for (int num : nums) {
                //如果i可以拆出一个num，那么方案数新增dp[i-num]
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
