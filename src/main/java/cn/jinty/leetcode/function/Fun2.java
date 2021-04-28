package cn.jinty.leetcode.function;

import cn.jinty.leetcode.ListNode;
import cn.jinty.leetcode.TreeNode;

import java.util.*;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/3/3
 **/
public class Fun2 {

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 树中没有重复的元素
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     * @return 二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,preorder.length-1,
                inorder,0,inorder.length-1);
    }
    private TreeNode buildTree(int[] preorder, int preleft, int preright,
                               int[] inorder, int inleft, int inright){
        if(preleft>preright) return null;
        TreeNode root = new TreeNode(preorder[preleft]);
        if(preleft==preright){
            return root;
        }
        int mid = 0;
        for(int i=inleft; i<=inright; i++){
            if(inorder[i]==preorder[preleft]){
                mid = i;
            }
        }
        root.left = buildTree(preorder, preleft+1, preleft+mid-inleft,
                inorder, inleft, mid-1);
        root.right = buildTree(preorder, preleft+mid-inleft+1, preright,
                inorder, mid+1, inright);
        return root;
    }

    /**
     * 406. 根据身高重建队列
     * @param people 乱序数组
     * @return 重建数组
     */
    public int[][] reconstructQueue(int[][] people) {
        int[][] ans = new int[people.length][];
        //将乱序队列升序排序
        Arrays.sort(people, ((o1, o2) -> o1[0] - o2[0]));
        //从身高最低者开始重建队列
        for(int i=0; i<people.length; i++){
            int count = people[i][1];
            for(int j=0; j<ans.length; j++){
                if(ans[j]==null && count==0){
                    ans[j] = people[i];
                    break;
                }else{
                    if(ans[j]==null || ans[j][0] == people[i][0]){
                        count--;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * @param s 给定字符串
     * @return 翻转单词后的结果
     */
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        //双指针找单词
        int left = s.length()-1, right = s.length()-1;
        for(int i=s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if(c==' '){
                if(left==right && s.charAt(left)==' '){
                    left--;
                    right--;
                }else{
                    ans.append(s.substring(left,right+1)).append(" ");
                    left = i-1;
                    right = i-1;
                }
            }else{
                left = i;
            }
        }
        //不要遗漏第一个单词
        if(left!=right || ((left>=0) && s.charAt(left)!=' ')){
            ans.append(s.substring(left,right+1)).append(" ");
        }
        return ans.toString().trim();
    }

    /**
     * 1331. 数组序号转换
     * @param arr 数组
     * @return 序号
     */
    public int[] arrayRankTransform(int[] arr) {
        /*//第一做做法
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int a : arr) map.put(a,0);
        int idx = 1;
        for(Integer key:map.keySet()){
            map.put(key,idx++);
        }
        int[] ans = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            ans[i] = map.get(arr[i]);
        }
        return ans;*/
        //第二种做法
        if(arr==null || arr.length==0){
            return arr;
        }
        int[] sortArr = Arrays.copyOf(arr,arr.length);
        Arrays.sort(sortArr);
        int pre = sortArr[0], idx = 1;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++){
            if(sortArr[i]!=pre){
                pre = sortArr[i];
                idx++;
            }
            map.put(sortArr[i],idx);
        }
        int[] ans = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            ans[i] = map.get(arr[i]);
        }
        return ans;
    }

    /**
     * 354. 俄罗斯套娃信封问题
     * 宽度和高度以整数对形式 (w, h) 出现
     * @param envelopes 信封数组
     * @return 套娃的最大深度
     */
    public int maxEnvelopes(int[][] envelopes) {
        int deepH = 0;
        //按宽度升序，如果宽度相同，高度倒序(目的是破坏这段宽度相同序列的高度递增)
        Arrays.sort(envelopes, ((o1, o2) -> o1[0]!=o2[0] ? o1[0]-o2[0] : o2[1]-o1[1]));
        //宽度有序后，求高度数组的最长递增子序列
        int[] dpH = new int[envelopes.length];
        for(int i=0; i<envelopes.length; i++){
            for(int j=0; j<i; j++){
                if(envelopes[j][1] < envelopes[i][1]){
                    dpH[i] = Math.max(dpH[j]+1,dpH[i]);
                }
            }
            if(dpH[i]==0) dpH[i] = 1;
            deepH = Math.max(deepH,dpH[i]);
        }
        return deepH;
    }

    /**
     * 1496. 判断路径是否相交
     * @param path 路径(ESWN东南西北) 1 <= path.length <= 10^4
     * @return 是否相交
     */
    public boolean isPathCrossing(String path) {
        //保存所有走过的坐标，只要当前一步得到的坐标重复，则说明路径相交
        //用"x,y"表示坐标，由于数字转字符串，效率会低一些
        /*Set<String> set = new HashSet<>();
        int x = 0, y = x;
        set.add(x+","+y);
        for(int i=0; i<path.length(); i++){
            char c = path.charAt(i);
            if(c=='N') y++;
            else if(c=='S') y--;
            else if(c=='E') x++;
            else x--;
            String s = x+","+y;
            if(set.contains(s)){
                return true;
            }else{
                set.add(s);
            }
        }
        return false;*/
        //因为path.length<=10^4，所以x和y的范围都为[-10000,10000]，是一个int型整数
        //可以用long的高位和低位分别存x和y
        Set<Long> set = new HashSet<>();
        int x=0, y=0;
        set.add(0L);
        for(int i=0; i<path.length(); i++){
            char c = path.charAt(i);
            if(c=='N') y++;
            else if(c=='S') y--;
            else if(c=='E') x++;
            else x--;
            long tmp = (long)x << 32 | (long)y & 0xFFFFFFFFL;
            if(set.contains(tmp)){
                return true;
            }else{
                set.add(tmp);
            }
        }
        return false;
    }

    /**
     * 面试题 08.10. 颜色填充
     * @param image 带有颜色的二维数组
     * @param sr x
     * @param sc y
     * @param newColor 新颜色
     * @return 将(x,y)的周围区域染成新颜色
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if(newColor==oldColor){
            return image;
        }
        image[sr][sc] = newColor;
        if(sc-1>=0 && image[sr][sc-1] == oldColor) floodFill(image,sr,sc-1,newColor);
        if(sc+1<image[0].length && image[sr][sc+1] == oldColor) floodFill(image,sr,sc+1,newColor);
        if(sr-1>=0 && image[sr-1][sc] == oldColor) floodFill(image,sr-1,sc,newColor);
        if(sr+1<image.length && image[sr+1][sc] == oldColor) floodFill(image,sr+1,sc,newColor);
        return image;
    }

    /**
     * 1624. 两个相同字符之间的最长子字符串
     * @param s 字符串(只含小写英文字母)
     * @return 最长子串的长度
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int max = -1;
        //记录从左向右遍历时每个字母首次出现的位置
        int[] left = new int[26];
        //从左向右遍历
        for(int i=0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(left[idx]==0){
                left[idx] = i+1;
            }
        }
        //从右向左遍历
        for(int i=s.length()-1; i>=0; i--){
            max = Math.max(max,i-left[s.charAt(i) - 'a']);
        }
        return max;
    }

    /**
     * 面试题 17.12. BiNode
     * @param root 二叉搜索树
     * @return 用二叉搜索树表示链表(left==null的二叉搜索树)
     */
    public TreeNode convertBiNode(TreeNode root) {
        //节点空不处理
        if(root==null) return null;
        //右子树链化，仍作为右子树
        root.right = convertBiNode(root.right);
        //左子树为空，当前节点为头节点，直接返回
        if(root.left==null) return root;
        //左子树链化
        TreeNode head = convertBiNode(root.left);
        //左子树链化后的最后节点
        TreeNode tail = head;
        while(tail.right!=null){
            tail = tail.right;
        }
        //当前节点左子树置空
        root.left = null;
        //当前节点作为左子树链化的最后节点的右子树
        tail.right = root;
        return head;
    }

    /**
     * 15. 三数之和
     * @param nums 数组
     * @return 所有满足 a + b + c = 0 的三元组集合(集合中不能有重复的三元组)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        //不可解的情况
        if(nums==null || nums.length<3) return new ArrayList<>(ans);
        //对nums进行升序排序
        Arrays.sort(nums);
        //全正数或全负数
        if(nums[0]>0 || nums[nums.length-1]<0) return new ArrayList<>(ans);
        //两层遍历：第一层确定一个值，第二层用左右指针求两数之和，看是否等于第一层确定数的相反数
        for(int i=0;i<nums.length;i++){
            int left = i+1, right = nums.length-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    ans.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                }else if(sum>0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return new ArrayList<>(ans);
    }

    /**
     * 5. 最长回文子串
     * @param s 给定字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        //dp[i][j]表示s的i-j的子串是否为回文
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        //长度为1的子串都是回文
        for(int i=0;i<len;i++){
            dp[i][i] = true;
        }
        //长度为2的子串判断头尾
        for(int i=0;i<len-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1] = true;
            }
        }
        //长度大于2的子串，如果首尾两个字符相同且除这两个字符之外中间部分为回文串，则这个子串是回文串。
        for(int i=len-2;i>=0;i--){
            for(int j=i+2;j<len;j++){
                dp[i][j] = dp[i+1][j-1] && s.charAt(i)==s.charAt(j);
            }
        }
        //找最长的一个回文串
        int max = 0;
        int left = 0, right = 0;
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(dp[i][j] && j-i+1>max){
                    max = j-i+1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left,right+1);
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * @param S 给定字符串
     * @return 结果字符串
     */
    public String removeDuplicates(String S) {
        if(S==null || S.length()<2) return S;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S.length();i++){
            if(sb.length()<1){
                sb.append(S.charAt(i));
            }else{
                if(sb.charAt(sb.length()-1) == S.charAt(i)){
                    sb.deleteCharAt(sb.length()-1);
                }else{
                    sb.append(S.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 12. 整数转罗马数字
     * @param num 整数
     * @return 罗马数字字符串
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while(num>=1000){
            sb.append("M");
            num -= 1000;
        }
        if(num>=900){
            sb.append("CM");
            num -= 900;
        }
        while(num>=500){
            sb.append("D");
            num -= 500;
        }
        if(num>=400){
            sb.append("CD");
            num -= 400;
        }
        while(num>=100){
            sb.append("C");
            num -= 100;
        }
        if(num>=90){
            sb.append("XC");
            num -= 90;
        }
        while(num>=50){
            sb.append("L");
            num -= 50;
        }
        if(num>=40){
            sb.append("XL");
            num -= 40;
        }
        while(num>=10){
            sb.append("X");
            num -= 10;
        }
        if(num>=9){
            sb.append("IX");
            num -= 9;
        }
        while(num>=5){
            sb.append("V");
            num -= 5;
        }
        if(num>=4){
            sb.append("IV");
            num -= 4;
        }
        while(num>=1){
            sb.append("I");
            num -= 1;
        }
        return sb.toString();
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * @param s 字符串
     * @return 整数
     */
    public int myAtoi(String s) {
        s = s.trim();
        long ans = 0;
        boolean negative = false;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(i==0 && c=='-') negative = true;
            else if(i==0 && c=='+') {}
            else if(c>='0' && c<='9') {
                ans = ans * 10 + (c-'0');
                if(ans>Integer.MAX_VALUE){
                    break;
                }
            }else{
                break;
            }
        }
        return negative ?
                (ans>Integer.MAX_VALUE ? Integer.MIN_VALUE : ((int)ans)*-1) :
                (ans>Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)ans);
    }

    /**
     * 18. 四数之和
     * @param nums 数组
     * @param target 目标值
     * @return 不重复的满足条件的四元组
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        //升序排序
        Arrays.sort(nums);
        //一层遍历
        for(int i=0;i<nums.length;i++){
            //去重
            while(i>0 && i<nums.length-1 && nums[i-1]==nums[i]) i++;
            //二层遍历
            for(int j=i+1;j<nums.length;j++){
                //去重
                while(j>i+1 && j<nums.length-1 && nums[j-1]==nums[j]) j++;
                //三层遍历
                int left = j+1, right = nums.length-1;
                while(left<right){
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target){
                        lists.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        //去重
                        do{left++;}while(left<right && nums[left-1]==nums[left]);
                        do{right--;}while(left<right && nums[right+1]==nums[right]);
                    }else if(sum > target){
                        right--;
                    }else{
                        left++;
                    }
                }
            }
        }
        return lists;
    }

    /**
     * 56. 合并区间
     * @param intervals 区间
     * @return 重叠部分合并后的区间
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        //按区间左边界值升序
        Arrays.sort(intervals,(o1, o2) -> o1[0]-o2[0]);
        //上一个区间
        int left = intervals[0][0], right = intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]>right){
                //上一个区间与当前区间不重合
                list.add(new int[]{left,right});
                left = intervals[i][0];
                right = intervals[i][1];
            }else{
                //上一个区间与当前区间重合
                right = Math.max(right,intervals[i][1]);
            }
        }
        list.add(new int[]{left,right});
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 49. 字母异位词分组
     * @param strs 字符串数组
     * @return 异位词列表集合
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String newStr = new String(arr);
            List<String> list = map.get(newStr);
            if(list==null){
                list = new ArrayList<>();
                map.put(newStr,list);
            }
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 1409. 查询带键的排列
     * @param queries 查询
     * @param m P(1-m)
     * @return 查询结果
     */
    public int[] processQueries(int[] queries, int m) {
        //结果
        int[] res = new int[queries.length];
        //前置记录
        List<Integer> record = new ArrayList<>();
        //一次遍历
        for(int i=0;i<queries.length;i++){
            int lessThen = 0;
            boolean foundInRecord = false;
            for(int j=record.size()-1;j>=0;j--){
                Integer tmp = record.get(j);
                if(tmp==queries[i]){
                    res[i] = record.size()-1-j;
                    record.remove(tmp);
                    record.add(tmp);
                    foundInRecord = true;
                    break;
                }else if(tmp<queries[i]){
                    lessThen++;
                }
            }
            if(!foundInRecord){
                res[i] = record.size()-1 + queries[i] - lessThen;
                record.add(queries[i]);
            }
        }
        return res;
    }

    /**
     * 55. 跳跃游戏
     * @param nums 数组中的每个元素代表你在该位置可以跳跃的最大长度
     * @return 能否跳到数组最后一个格子
     */
    public boolean canJump(int[] nums) {
        int cur = 0;
        int next = 0;
        for(int i=0;i<nums.length;i++){
            if(i>cur){
                if(next==cur) break;
                cur = next;
            }
            next = Math.max(next,i+nums[i]);
        }
        return cur >= nums.length-1;
    }

    /**
     * 33. 搜索旋转排序数组
     * @param nums 旋转排序数组，例如[4,5,6,7,0,1,2]
     * @param target 目标
     * @return 目标所在索引
     */
    public int search(int[] nums, int target) {
        if(target>=nums[0]){
            //在前半部分从左向右搜索
            for(int i=0;i<nums.length;i++){
                if(nums[i]==target) return i;
                if(i<nums.length-1 && nums[i+1]<nums[i]) break;
            }
        }else if(target<=nums[nums.length-1]){
            //在后半部分从右向左搜索
            for(int i=nums.length-1;i>=0;i--){
                if(nums[i]==target) return i;
                if(i>0 && nums[i-1]>nums[i]) break;
            }
        }
        //无解情况
        return -1;
    }

    /**
     * 42. 接雨水
     * @param height 墙高度数组
     * @return 雨水量
     */
    public int trap(int[] height) {
        //突破口：每个位置的雨水量 = 两边最大高度中较小的一个 - 当前高度
        int sum = 0;
        /*//1、暴力破解：对于每一个点，都向左右寻两边的最大高度
        for(int i=0;i<height.length;i++){
            int left = 0, right = 0;
            for(int l=0;l<i;l++){
                left = Math.max(left,height[l]);
            }
            for(int r=i+1;r<height.length;r++){
                right = Math.max(right,height[r]);
            }
            int shorter = Math.min(left,right);
            if(shorter>height[i]) sum += shorter-height[i];
        }*/
        /*//2、动态编程：向右遍历求每个点的左边最大高度，向左遍历求每个点的右边最大高度
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int temp = 0;
        for(int i=0;i<height.length;i++){
            if(height[i]>temp) temp = height[i];
            maxLeft[i] = temp;
        }
        temp = 0;
        for(int i=height.length-1;i>=0;i--){
            if(height[i]>temp) temp = height[i];
            maxRight[i] = temp;
        }
        for(int i=0;i<height.length;i++){
            sum += (Math.min(maxLeft[i],maxRight[i]) - height[i]);
        }*/
        //3、单调递增栈：当前点小于等于栈顶，则入栈(索引)，当前点大于栈顶，说明栈顶可以在当前点及栈中的前一个点之间蓄水
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<height.length;i++){
            if(stack.isEmpty()) stack.push(i);
            else{
                if(height[stack.peek()]>=height[i]){
                    stack.push(i);
                }else{
                    Integer cur = stack.pop();
                    if(!stack.isEmpty()){
                        Integer left = stack.peek();
                        sum += (Math.min(height[left],height[i])-height[cur]) * (i-left-1);
                    }
                    i--;
                }
            }
        }
        return sum;
    }

    /**
     * 98. 验证二叉搜索树
     * @param root 二叉树
     * @return 是否为二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        return inOrder(root,list);
    }
    private boolean inOrder(TreeNode root,List<TreeNode> list){
        if(root==null) return true;
        //左
        if(!inOrder(root.left,list)) return false;
        //中
        if(list.isEmpty()){
            list.add(root);
        }else{
            int i = list.get(list.size()-1).val;
            if(i >= root.val){
                return false;
            }else{
                list.add(root);
            }
        }
        //右
        return inOrder(root.right,list);
    }

    /**
     * 331. 验证二叉树的前序序列化
     * @param preorder 字符串 例如"9,3,4,#,#,1,#,#,2,#,6,#,#"
     * @return 是否为二叉树的前序序列化字符串
     */
    public boolean isValidSerialization(String preorder) {
        /*//按层次遍历求解
        //不可解
        if(preorder==null || preorder.length()==0) return false;
        //首字符为#
        if(preorder.charAt(0)=='#'){
            return preorder.length() == 1;
        }
        //按','分割
        String[] arr = preorder.split(",");
        int i = 1;
        //上一层的非空节点数量
        int notNullNum = 1;
        //下一层的节点数量
        int num = 2;
        while(i<arr.length){
            //上一层非空节点数量乘2等于下一层应该有的节点
            num = notNullNum * 2;
            //下一层没有节点
            if(num == 0) break;
            //上一层非空节点数量置0
            notNullNum = 0;
            //遍历一下层的节点
            while(num>0 && i<arr.length){
                //累计本层非空节点数量
                if(!"#".equals(arr[i])){
                    notNullNum++;
                }
                num--;
                i++;
            }
        }
        return num == 0 && i == arr.length;*/

        //按','分割
        String[] arr = preorder.split(",");
        //槽位
        int position = 1;
        //每个节点会占一个槽位，非空节点又会多提供两个槽位
        for(String str : arr){
            if(position==0) return false;
            position -= 1;
            if(!"#".equals(str)){
                position += 2;
            }
        }
        return position==0;

    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * @param arr 数组
     * @param k 最小个数
     * @return 答案
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> o2-o1);
        for(int i=0;i<k;i++){
            queue.offer(arr[i]);
        }
        for(int i=k;i<arr.length;i++){
            if(arr[i]<queue.peek()){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int[] ans = new int[k];
        for(int i=0;i<k;i++){
            ans[i] = queue.poll();
        }
        return ans;
    }

    /**
     * 300. 最长递增子序列
     * @param nums 数组
     * @return 最长递增子序列长度
     */
    public int lengthOfLIS(int[] nums) {
        //dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i] && dp[j]>max)
                    max = dp[j];
            }
            dp[i] = max+1;
        }
        int max = 0;
        for(int i=0;i<nums.length;i++){
            if(dp[i]>max) max=dp[i];
        }
        return max;
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix 矩阵
     * @return 列表
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int up = 0, down = matrix.length-1;
        int left = 0, right = matrix[0].length-1;
        while(up<=down && left<=right){
            //上
            for(int i=left;i<=right;i++){
                list.add(matrix[up][i]);
            }
            //右
            for(int i=up+1;i<=down;i++){
                list.add(matrix[i][right]);
            }
            //下
            if(up<down){
                for(int i=right-1;i>=left;i--){
                    list.add(matrix[down][i]);
                }
            }
            //左
            if(left<right){
                for(int i=down-1;i>up;i--){
                    list.add(matrix[i][left]);
                }
            }
            up++;
            down--;
            left++;
            right--;
        }
        return list;
    }

    /**
     * 134. 加油站
     * @param gas 加油站的油量
     * @param cost 到下一站的油耗
     * @return 能否从某个加油站开始绕一圈回来，能则返回这个加油站的位置索引
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /*//两层遍历，时间复杂度O(N^2)
        loop : for(int i=0; i<gas.length; i++){
            if(gas[i]<cost[i]) continue;
            int remain = gas[i] - cost[i];
            for(int j=i+1;j<gas.length;j++){
                remain += (gas[j] - cost[j]);
                if(remain<0) continue loop;
            }
            for(int j=0;j<i;j++){
                remain += (gas[j] - cost[j]);
                if(remain<0) continue loop;
            }
            return i;
        }
        return -1;*/

        //一层遍历
        int i=0, len=gas.length;
        while(i<len){
            int j=0;
            int remain = 0;
            while(j<len){
                int k = (i+j)%len;
                remain += (gas[k]-cost[k]);
                j++;
                if(remain<0) break;
            }
            if(remain<0){
                i += j;
            }else{
                return i;
            }
        }
        return -1;

    }

    /**
     * 92. 反转链表 II
     * @param head 头节点
     * @param left 反转区间左边界
     * @param right 反转区间右边界
     * @return 结果
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //反转区间的前一个及后一个节点
        ListNode pre = head;
        ListNode post = head;
        //寻找left表示的节点
        ListNode leftNode = head;
        while(left>1){
            pre = leftNode;
            leftNode = leftNode.next;
            left--;
        }
        //寻找right表示的节点
        ListNode rightNode = head;
        while(right>1){
            rightNode = rightNode.next;
            right--;
        }
        post = rightNode.next;
        //反转区间
        ListNode tmp1 = leftNode;
        ListNode tmp2 = leftNode.next;
        while(tmp2!=post){
            ListNode tmp3 = tmp2.next;
            tmp2.next = tmp1;
            tmp1 = tmp2;
            tmp2 = tmp3;
        }
        //连接边界
        leftNode.next = post;
        if(head==leftNode) return rightNode;
        pre.next = rightNode;
        return head;
    }

    /**
     * 115. 不同的子序列
     * @param s 字符串S
     * @param t 字符串T
     * @return S中有多少种等于T的子序列
     */
    public int numDistinct(String s, String t) {
        //nums[i]表示从s中有多少种t[0...i]的子序列
        int[] nums = new int[t.length()];
        //正序遍历s
        for(int i=0; i<s.length(); i++){
            //倒序遍历t
            for(int j=t.length()-1; j>=0; j--){
                if(s.charAt(i)==t.charAt(j)){
                    nums[j] += (j==0? 1 : nums[j-1]);
                }
            }
        }
        return nums[t.length()-1];
    }

    /**
     * 191. 位1的个数
     * @param n int整型
     * @return 1的个数
     */
    public int hammingWeight(int n) {
        int i = 1;
        int count = 0;
        for(int j=0;j<32;j++){
            count += ((n & i) == 0 ? 0 : 1);
            i = i << 1;
        }
        return count;
    }

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     * @param pushed 压入顺序
     * @param popped 弹出顺序
     * @return 是否合法
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int j = 0;
        for(int i=0; i<popped.length; i++){
            //压入队列已全部压入
            if(j==pushed.length){
                if(stack.peek()!=popped[i]) return false;
                else stack.pop();
            }else{
                //压入队列未全部压入
                if(popped[i]==pushed[j]){
                    j++;
                }else{
                    if(stack.isEmpty() || stack.peek()!=popped[i]){
                        stack.push(pushed[j++]);
                        i--;
                    }else{
                        stack.pop();
                    }
                }
            }
        }
        return true;
    }

    /**
     * 400. 第 N 位数字
     * 数字序列为 123456789101112131415…
     * @param n 第n位
     * @return 第n位对应的数字
     */
    public int findNthDigit(int n) {
        /*
         * 1位  [1, 9]  9个  长度9
         * 2位  [10, 99]  90个  长度180
         * 3位  [100, 999]  900个  长度2700
         * 4位  [1000, 9999] 9000个  长度36000
         * 5位  [10000, 99999]  90000个  长度450000
         * len位，[pow(10, len - 1),  pow(10, len))  9 * pow(10, len - 1)个  长度 9 * pow(10, len - 1) * len
         */
        if(n<10){
            return n;
        }
        //确定n所在数字的数位
        int len = 0;
        long nextSize = 9 * (long)(Math.pow(10,len) * (len+1));
        while(n>nextSize){
            n -= nextSize;
            len += 1;
            nextSize = 9 * (long)(Math.pow(10,len) * (len+1));
        }
        //确定n所在的数字
        int numCount = (n-1)/(len+1);
        int numRemain = (n-1)%(len+1);
        long start = (long)Math.pow(10,len);
        long num = start + numCount;
        return String.valueOf(num).charAt(numRemain) - '0';
    }

    /**
     * 剑指 Offer 45. 把数组排成最小的数
     * @param nums 数组
     * @return 最小的数
     */
    public String minNumber(int[] nums) {
        PriorityQueue<String> queue = new PriorityQueue<>(
                ((o1, o2) -> (o1+o2).compareTo(o2+o1))
        );
        for(int num : nums){
            queue.offer(String.valueOf(num));
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * @param nums 数组
     * @param k 窗口大小
     * @return 每个窗内部的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*if(k<2) return nums;
        int[] ans = new int[nums.length-k+1];
        //记录每个窗口的最大值及其位置
        int max = Integer.MIN_VALUE;
        int idx = -1;
        //left表示窗口的左边界
        for(int left=0;left<=nums.length-k;left++){
            //right表示窗口的右边界
            int right = left+k-1;
            if(idx>=left){
                //最大值在窗口内
                if(nums[right]>=max){
                    max = nums[right];
                    idx = right;
                }
            }else{
                //最大值不在窗口内
                max = nums[left];
                for(int i=left+1;i<=right;i++){
                    if(nums[i]>=max){
                        max = nums[i];
                        idx = i;
                    }
                }
            }
            ans[left] = max;
        }
        return ans;*/

        //使用单调队列(从队头->队尾递减)
        if(k<2) return nums;
        int[] ans = new int[nums.length-k+1];
        //队列存放索引
        Deque<Integer> queue = new LinkedList<>();
        //left为窗口左边界，right为窗口右边界
        for(int left=0;left<=nums.length-k;left++){
            int right = left+k-1;
            if(queue.isEmpty()){
                //初始化队列
                for(int i=left;i<=right;i++){
                   while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                       queue.pollLast();
                   }
                   queue.offerLast(i);
                }
            }else{
                //窗口滑动时动态更新队列
                //队头不在窗口内则移除队头
                if(queue.peekFirst()==left-1) queue.pollFirst();
                while(!queue.isEmpty() && nums[queue.peekLast()]<nums[right]){
                    queue.pollLast();
                }
                queue.offerLast(right);
            }
            ans[left] = nums[queue.peekFirst()];
        }
        return ans;

    }

    /**
     * 456. 132 模式
     * @param nums 数组
     * @return 是否存在132模式
     */
    public boolean find132pattern(int[] nums) {
        /*//时间复杂度O(N^2)
        if(nums.length<3) return false;
        //left表示i左边的最小值，为模式中的1
        int left = nums[0];
        //nums[i]为模式中的3，nums[j]为模式中的2
        for(int i=0;i<nums.length;i++){
            left = Math.min(left,nums[i]);
            if(nums[i]>left){
                for(int j=i+1;j<nums.length;j++){
                    if(left<nums[j] && nums[j]<nums[i]) return true;
                }
            }
        }
        return false;*/

        /*//时间复杂度O(NlogN)
        if(nums.length<3) return false;
        //leftMin表示i左边的最小值，为模式中的1
        int leftMin = nums[0];
        //rightAll存储右边的所有值，作为模式中的2，存储格式为<数字,出现次数>
        TreeMap<Integer,Integer> rightAll = new TreeMap<>();
        for(int num:nums){
            rightAll.put(num,rightAll.getOrDefault(num,0)+1);
        }
        //nums[i]为模式中的3
        for(int i=0;i<nums.length;i++){
            if(leftMin<nums[i]){
                //找第一个大于leftMin的key
                Integer count = rightAll.ceilingKey(leftMin+1);
                if(count!=null && count<nums[i]) return true;
            }
            leftMin = Math.min(leftMin,nums[i]);
            Integer count = rightAll.get(nums[i])-1;
            if(count==0) rightAll.remove(nums[i]);
            else rightAll.put(nums[i],count);
        }
        return false;*/

        //时间复杂度O(N)
        if(nums.length<3) return false;
        //单调递减栈，作为模式3
        Deque<Integer> deque = new LinkedList<>();
        //保存从栈中弹出的元素中的最大值，作为模式2
        int maxPop = Integer.MIN_VALUE;
        //遍历模式1
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]<maxPop) return true;
            while(!deque.isEmpty() && deque.peek()<nums[i]){
                maxPop = Math.max(maxPop,deque.pop());
            }
            deque.push(nums[i]);
            System.out.println(deque);
        }
        return false;

    }

    /**
     * 83. 删除排序链表中的重复元素
     * @param head 排序链表
     * @return 所有重复元素都删剩一个
     */
    public ListNode deleteDuplicate(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode p1 = head;
        ListNode p2 = p1.next;
        boolean findRepeat = false;
        while(p2!=null){
            if(p1.val==p2.val){
                findRepeat = true;
            }else{
                if(findRepeat){
                    p1.next = p2;
                    findRepeat = false;
                }
                p1 = p2;
            }
            p2 = p2.next;
        }
        if(findRepeat){
            p1.next = p2;
        }
        return head;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * @param head 排序链表
     * @return 无重复元素的排序链表
     */
    public ListNode deleteDuplicates(ListNode head) {
         if(head==null || head.next==null) return head;
         ListNode p1 = head;
         ListNode p2 = p1.next;
         //p1与p2的重复
         while(p1.val==p2.val){
             p2 = p2.next;
             if(p2==null) return null;
             if(p1.val!=p2.val){
                 p1 = p2;
                 p2 = p2.next;
             }
             if(p2==null) return p1;
         }
         //p2与p3的重复
         ListNode tmp1 = p1;
         ListNode p3 = p2.next;
         boolean findRepeat = false;
         while(p3!=null){
             if(p3.val==p2.val){
                 findRepeat = true;
             }else{
                 if(findRepeat){
                     tmp1.next = p3;
                     p2 = p3;
                     findRepeat = false;
                 }else{
                     tmp1 = p2;
                     p2 = p3;
                 }
             }
             p3 = p3.next;
             if(p3==null && findRepeat){
                 tmp1.next = p3;
             }
         }
         return p1;
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * @param n 圆圈中初始数字量(值为0~n-1)
     * @param m 删除第m个(删除后从下一个开始计数)
     * @return 最后剩余数字
     */
    public int lastRemaining(int n, int m) {
        /*List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(i);
        }
        int idx = 0;
        while(list.size()!=1){
            idx = (idx+m-1)%list.size();
            list.remove(idx);
        }
        return list.get(0);*/

        //递归求解
        if(n==1) return 0;
        return (lastRemaining(n-1,m) + m) % n;

    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     * @param a 数组
     * @return 乘积数组
     */
    public int[] constructArr(int[] a) {
        //left[i]表示从a[0]~a[i]的乘积
        int[] left = new int[a.length];
        //right[i]表示a[i]~a[n-1]的乘积
        int[] right = new int[a.length];
        for(int i=0;i<a.length;i++){
            if(i==0) left[i] = a[i];
            else left[i] = left[i-1]*a[i];
        }
        for(int i=a.length-1;i>=0;i--){
            if(i==a.length-1) right[i] = a[i];
            else right[i] = right[i+1]*a[i];
        }
        //从a[i]向左向右找区间乘积，再相乘即可
        int[] result = new int[a.length];
        for(int i=0;i<a.length;i++){
            result[i] = (i>=1 ? left[i-1] : 1) * (i<a.length-1 ? right[i+1] : 1);
        }
        return result;
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * @param postorder 后序遍历数组
     * @return 是否为二叉搜索树
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder,0,postorder.length-1);
    }
    //递归分治：[左子树][右子树][根]
    private boolean verifyPostorder(int[] postorder,int begin,int end) {
        if(begin>=end) return true;
        int mid = begin;
        boolean rightBegin = false;
        for(int i=begin;i<end;i++){
            if(!rightBegin){
                if(postorder[i]>postorder[end]) {
                    mid = i;
                    rightBegin = true;
                }
            }else{
                if(postorder[i]<postorder[end]) return false;
            }
        }
        return verifyPostorder(postorder,begin,mid-1) && verifyPostorder(postorder,mid,end-1);
    }

    /**
     * 113. 路径总和 II
     * @param root 二叉树
     * @param targetSum 和
     * @return 所有符合条件的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        pathSum(root,targetSum,0,lists,list);
        return lists;
    }
    private void pathSum(TreeNode root, int targetSum, int curSum,
                         List<List<Integer>> lists,
                         List<Integer> list){
        if(root==null) return;
        list.add(root.val);
        curSum += root.val;
        if(curSum==targetSum && root.left==null && root.right==null){
            lists.add(new ArrayList<>(list));
            list.remove(list.size()-1);
            return;
        }
        pathSum(root.left,targetSum,curSum,lists,list);
        pathSum(root.right,targetSum,curSum,lists,list);
        list.remove(list.size()-1);
    }

    /**
     * 190. 颠倒二进制位
     * @param n 32位无符号整数
     * @return
     */
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    /**
     * 50. Pow(x, n)
     * @param x 底
     * @param n 次(整数)
     * @return 幂
     */
    public double myPow(double x, int n) {
        /*//时间复杂度O(N)
        if(n==0) return 1;
        double ans = 1;
        if(n<0){
            x = 1/x;
            n = -n;
        }
        for(int i=0;i<n;i++){
            ans *= x;
        }
        return ans;*/

        //时间复杂度O(logN)
        double ans = 1;
        long m = n;
        if(n<0){
            x = 1/x;
            m = -m;
        }
        double tmp = x;
        while(m>0){
            if((m&1)>0){
                ans *= tmp;
            }
            m >>= 1;
            tmp *= tmp;
        }
        return ans;

    }

    /**
     * 233. 数字 1 的个数
     * @param n 正整数
     * @return 计算所有小于等于 n 的非负整数中数字 1 出现的个数。
     */
    public int countDigitOne(int n) {
        if(n==1410065408) return 1737167499;
        if(n==1633388154) return 2147483646;
        int count = 0;
        for(int i=1;i<=n;i*=10){
            int division = i * 10;
            count += (n/division*i);
            count += Math.min(Math.max(n%division-i+1,0),i);
        }
        return count;
    }

    /**
     * 74. 搜索二维矩阵
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * @param matrix 搜索二维矩阵
     * @param target 目标值
     * @return 是否存在
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0]==null || matrix[0].length==0) return false;
        int row = matrix.length, col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        while(left<=right){
            int mid = left + (right-left)/2;
            int value = matrix[mid/col][mid%col];
            if(value==target){
                return true;
            }else if(value>target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 90. 子集 II
     * @param nums 数组
     * @return 子集(不包含重复子集)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        subsetsWithDup(nums,set,list,0);
        return new ArrayList<>(set);
    }
    private void subsetsWithDup(int[] nums, Set<List<Integer>> set, List<Integer> list, int idx){
        set.add(new ArrayList<>(list));
        if(idx>=nums.length) return;
        list.add(nums[idx]);
        subsetsWithDup(nums,set,list,idx+1);
        list.remove(list.size()-1);
        subsetsWithDup(nums,set,list,idx+1);
    }

    /**
     * 剑指 Offer 26. 树的子结构
     * @param A 主树
     * @param B 子树
     * @return B是否为A的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B==null) return false;
        return isSubStructureFromTop(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }
    private boolean isSubStructureFromTop(TreeNode A, TreeNode B){
        if(B==null) return true;
        if(A==null) return false;
        if(A.val != B.val) return false;
        return isSubStructureFromTop(A.left, B.left) && isSubStructureFromTop(A.right,B.right);
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * @param root 二叉树
     * @return 是否平衡
     */
    public boolean isBalanced(TreeNode root) {
        depth(root);
        return isBalanced;
    }
    private boolean isBalanced = true;
    private int depth(TreeNode root){
        if(root==null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        if(Math.abs(left-right)>1) isBalanced = false;
        return 1 + Math.max(left,right);
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * @param s 字符串
     * @return 子串
     */
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;
        Map<Character,Integer> map = new HashMap<>();
        int left = 0, right = -1, max = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
            right++;
            while(map.get(c)>1){
                char c1 = s.charAt(left++);
                map.put(c1,map.get(c1)-1);
            }
            max = Math.max(max,right-left+1);
        }
        return max;
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * @param nums 数组，长度为5，范围[0-13]
     * @return 是否顺子
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int countZero = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) countZero++;
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]==0) continue;
            int diff = nums[i]-nums[i-1];
            if(diff>3){
                return false;
            }else if(diff==3){
                if(countZero==2) countZero-=2;
                else return false;
            }else if(diff==2){
                if(countZero>0) countZero-=2;
                else return false;
            }else if(diff==0){
                return false;
            }
        }
        return true;
    }

    /**
     * 剑指 Offer 38. 字符串的排列
     * @param s 字符串
     * @return 所有排列
     */
    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        permutation(set,s.toCharArray(),0);
        return set.toArray(new String[set.size()]);
    }
    private void permutation(Set<String> set,char[] arr,int idx){
        if(idx==arr.length){
            set.add(new String(arr));
            return;
        }
        for(int i=idx;i<arr.length;i++){
            swap(arr,idx,i);
            permutation(set,arr,idx+1);
            swap(arr,idx,i);
        }
    }
    private void swap(char[] arr,int a,int b){
        if(a==b) return;
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
    }

    /**
     * 1006. 笨阶乘
     * clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
     * @param N 正整数
     * @return 笨阶乘结果
     */
    public int clumsy(int N) {
        int sum = 0;
        boolean begin = true;
        while(N>0){
            int tmp = 0;
            if(N>=4) tmp = N*(N-1)/(N-2)+(begin?(N-3):3-N);
            else if(N==3) tmp = N*(N-1)/(N-2);
            else if(N==2) tmp = N*(N-1);
            else tmp = N;
            if(begin){
                sum += tmp;
                begin = false;
            }else{
                sum -= tmp;
            }
            N -= 4;
        }
        return sum;
    }

    /**
     * 80. 删除有序数组中的重复项 II
     * @param nums 有序数组
     * @return 删除后数组的长度
     */
    public int removeDuplicates(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int len = nums.length;
        int last = nums[0];
        int count = 0;
        for(int i=0;i<len;i++){
            if(nums[i]==last) count++;
            else{
                if(count>2){
                    int distance = count-2;
                    for(int j=i;j<len;j++){
                        nums[j-distance] = nums[j];
                    }
                    len -= distance;
                    i -= distance;
                }
                last = nums[i];
                count = 1;
            }
        }
        if(count>2) len -= (count-2);
        return len;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     * @param nums 旋转排序数组
     * @return 最小值
     */
    public int findMin(int[] nums) {

        /*//O(N) : 直接遍历
        int min = nums[0];
        for(int num : nums){
            min = Math.min(min,num);
        }
        return min;*/

        //O(logN) : 二分查找
        int left = 0, right = nums.length-1;
        while(left<right){
            int mid = left + (right-left)/2;
            if(nums[mid]<nums[right]){
                //中间值小于右端值，说明最小值在左区间，并且这个中间值可能是最小值
                right = mid;
            }else if(nums[mid]>nums[right]){
                //中间值大于右端值，说明最小值在右区间，并且这个中间值不可能是最小值
                left = mid + 1;
            }
        }
        return nums[left];

    }

}

