package cn.jinty.leetcode.problem.easy;

import cn.jinty.leetcode.entity.Employee;
import cn.jinty.struct.linear.ListNode;
import cn.jinty.struct.tree.TreeNode;

import java.util.*;

/**
 * LeetCode - 简单题
 *
 * @author jinty
 * @date 2021/6/10
 **/
public class Solution {

    /**
     * 1356. 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     * 请你返回排序后的数组。
     *
     * @param arr 数组
     * @return 排序结果
     */
    public int[] sortByBits(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int count1 = 0;
            int count2 = 0;
            int standard = 1;
            int n = 32;
            while(n>0){
                if((o1 & standard)>0){
                    count1++;
                }
                if((o2 & standard)>0){
                    count2++;
                }
                n--;
                standard = standard << 1;
            }
            if(count1==count2){
                return o1-o2;
            }
            return count1 - count2;
        });
        for(int i:arr){
            queue.add(i);
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = queue.poll();
        }
        return arr;
    }

    /**
     * 面试题 05.07. 配对交换
     * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令
     * （也就是说，位0与位1交换，位2与位3交换，以此类推）。
     *
     * @param num 整数
     * @return 整数
     */
    public int exchangeBits(int num) {
        int ans = 0;
        int[] map = new int[32];
        for(int i=0;i<32;i++){
            map[i] = ( num & (1<<i) ) > 0 ? 1 : 0;
        }
        for(int i=1;i<32;i=i+2){
            ans |= map[i]<<(i-1);
        }
        for(int i=0;i<32;i=i+2){
            ans |= map[i]<<(i+1);
        }
        return ans;
    }

    /**
     * 面试题 08.06. 汉诺塔问题
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
     * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。
     * 移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
     * 编写程序，将所有盘子从第一根柱子移到最后一根柱子。
     *
     * @param A 柱子A
     * @param B 柱子B
     * @param C 柱子C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, B, C);
    }
    //递归函数：表示将n个圆盘从A移到C
    private void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C){
        if(n == 1){
            C.add(A.remove(A.size() - 1));
        }else{
            //把A的前n-1个圆盘经过辅助C放到B上
            hanota(n - 1, A, C, B);
            //把A的最底部圆盘放到C上
            C.add(A.remove(A.size() - 1));
            //把B的所有圆盘经过辅助A放到C上
            hanota(n - 1, B, A, C);
        }
    }

    /**
     * 1763. 最长的美好子字符串
     * 当一个字符串包含的每一种字母的大写和小写形式同时出现，就称这个字符串是美好字符串。
     * 如果有多个答案，返回最早出现的美好子字符串。
     *
     * @param s 字符串
     * @return 美好子字符串
     */
    public String longestNiceSubstring(String s) {
        String ans = "";
        int len = s.length();
        //起点
        for(int i=0;i<len-1;i++){
            //终点
            for(int j=i+1;j<len;j++){
                //采用位运算，对1做(0-25位)的左移，小写计入a，大写计入b，最后比较ab即可
                int a = 0, b = 0;
                for(int k=i;k<=j;k++){
                    char c = s.charAt(k);
                    if(c >= 'a' && c <= 'z'){
                        a |= 1 << (c-'a');
                    }else{
                        b |= 1 << (c-'A');
                    }
                }
                if(a==b && j-i+1 > ans.length()){
                    ans = s.substring(i,j+1);
                }
            }
        }
        return ans;
    }

    /**
     * 1716. 计算力扣银行的钱
     * Hercy 想要为购买第一辆车存钱。他每天都往力扣银行里存钱。
     * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。
     * 在接下来每一个周一，他都会比前一个周一多存入 1 块钱。
     * 请返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
     *
     * @param n 总天数
     * @return 总额
     */
    public int totalMoney(int n) {
        int sum = 0;
        int monday = 1;
        int tmp = monday;
        for(int i=1;i<=n;i++){
            sum += tmp++;
            if(i%7==0){
                monday++;
                tmp=monday;
            }
        }
        return sum;
    }

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     *
     * @param target 目标值
     * @return 结果
     */
    public int[][] findContinuousSequence(int target) {
        //双指针
        List<int[]> lists = new ArrayList<>();
        int left=1;
        int sum = 0;
        for(int right=1; right<=target; right++){
            if(sum < target){
                sum += right;
            }else{
                if(sum == target) {
                    lists.add(buildSequenceArray(left, right - 1));
                }
                sum -= left++;
                right--;
            }
        }
        return lists.toArray(new int[lists.size()][]);
    }
    private int[] buildSequenceArray(int i, int j){
        int[] ans = new int[j-i+1];
        for(int k=0; k<ans.length; k++){
            ans[k] = i++;
        }
        return ans;
    }

    /**
     * 面试题 16.17. 连续数列
     * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
     *
     * @param nums 数列
     * @return 总和最大的连续数列的总和值
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        //前缀数列和
        int pre = 0;
        for (int num : nums) {
            //如果当前数字加上前缀数列和更小，那么抛弃前缀，当前数字独自作为一个数列
            pre = Math.max(pre + num, num);
            //根据当前数列判断是否要更新最大值
            max = Math.max(max, pre);
        }
        return max;
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
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
                    ans.append(s, left, right+1).append(" ");
                    left = i-1;
                    right = i-1;
                }
            }else{
                left = i;
            }
        }
        //不要遗漏第一个单词
        if(left!=right || ((left>=0) && s.charAt(left)!=' ')){
            ans.append(s, left, right+1).append(" ");
        }
        return ans.toString().trim();
    }

    /**
     * 1331. 数组序号转换
     * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
     *
     * @param arr 数组
     * @return 序号
     */
    public int[] arrayRankTransform(int[] arr) {

        /*//1、使用TreeMap实现去重与排序
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

        //2、复制数组排序，再使用Map做数值与序号的映射
        if(arr==null || arr.length==0){
            return arr;
        }
        int[] sortArr = Arrays.copyOf(arr,arr.length);
        Arrays.sort(sortArr);
        int pre = sortArr[0], idx = 1;
        Map<Integer,Integer> map = new HashMap<>();
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
     * 1496. 判断路径是否相交
     * 给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。
     * 机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。
     * 如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。
     *
     * @param path 路径 (1 <= path.length <= 10^4)
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
            long location = (long)x << 32 | (long)y & 0xFFFFFFFFL;
            if(set.contains(location)){
                return true;
            }else{
                set.add(location);
            }
        }
        return false;

    }

    /**
     * 面试题 08.10. 颜色填充
     * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
     * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
     * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
     * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
     *
     * @param image 图片
     * @param sr 行坐标
     * @param sc 列坐标
     * @param newColor 新颜色
     * @return 染色后的图片
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
     * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。
     * 如果不存在这样的子字符串，返回 -1 。
     * 子字符串 是字符串中的一个连续字符序列。
     *
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
     * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
     * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
     * 返回转换后的单向链表的头节点。
     *
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
     * 1047. 删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 S ，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     *
     * @param S 给定字符串
     * @return 结果字符串
     */
    public String removeDuplicates(String S) {
        //使用栈实现
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
     * 13. 罗马数字转整数
     * 罗马数字包含以下七种字符：I， V， X， L，C，D 和 M。
     * 分别对应：1， 5， 10， 50， 100， 500， 1000
     * 特殊情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * @param s 罗马数字字符串
     * @return 整数
     */
    public int romanToInt(String s) {
        int num = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='M'){
                num += 1000;
            }else if(c=='D'){
                num += 500;
            }else if(c=='C'){
                if(i+1<s.length()){
                    char c1 = s.charAt(i+1);
                    if(c1=='M'){
                        num += 900;
                        i++;
                        continue;
                    }
                    if(c1=='D'){
                        num += 400;
                        i++;
                        continue;
                    }
                }
                num += 100;
            }else if(c=='L'){
                num += 50;
            }else if(c=='X'){
                if(i+1<s.length()){
                    char c1 = s.charAt(i+1);
                    if(c1=='C'){
                        num += 90;
                        i++;
                        continue;
                    }
                    if(c1=='L'){
                        num += 40;
                        i++;
                        continue;
                    }
                }
                num += 10;
            }else if(c=='V'){
                num += 5;
            }else if(c=='I'){
                if(i+1<s.length()){
                    char c1 = s.charAt(i+1);
                    if(c1=='X'){
                        num += 9;
                        i++;
                        continue;
                    }
                    if(c1=='V'){
                        num += 4;
                        i++;
                        continue;
                    }
                }
                num += 1;
            }
        }
        return num;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     *
     * @param arr 数组
     * @param k 整数
     * @return 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        //大根堆
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
     * 191. 位1的个数
     *
     * @param n 整数
     * @return 位1的个数
     */
    public int hammingWeight(int n) {

        /*//1、枚举32位
        int i = 1;
        int count = 0;
        for(int j=0;j<32;j++){
            count += ((n & i) == 0 ? 0 : 1);
            i = i << 1;
        }
        return count;*/

        //2、枚举位1
        int count = 0;
        while(n!=0){
            n -= (n&(-n));
            count++;
        }
        return count;

    }

    /**
     * 83. 删除排序链表中的重复元素
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素只出现一次。
     *
     * @param head 排序链表
     * @return 结果链表
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
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，
     * 每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
     * 求出这个圆圈里剩下的最后一个数字。
     *
     * @param n 圆圈大小
     * @param m 删除间隔
     * @return 最后剩余数字
     */
    public int lastRemaining(int n, int m) {

        /*//1、模拟过程
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(i);
        }
        int idx = 0;
        while(list.size()!=1){
            idx = (idx+m-1)%list.size();
            list.remove(idx);
        }
        return list.get(0);*/

        //2、递归 (约瑟夫环)
        return n==1 ? 0 : (lastRemaining(n-1,m) + m) % n;

    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
     * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     *
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
        if( ! isBalanced) return 0;
        int left = depth(root.left);
        if( ! isBalanced) return 0;
        int right = depth(root.right);
        if(Math.abs(left-right)>1) isBalanced = false;
        return 1 + Math.max(left,right);
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     * @param nums 数组，长度为5，范围[0-13]
     * @return 是否为顺子
     */
    public boolean isStraight(int[] nums) {
        //排序
        Arrays.sort(nums);
        //大小王数量
        int countZero = 0;
        for (int num : nums) {
            if (num == 0) countZero++;
        }
        //大小王可以看成任意数
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]==0) continue;
            //相邻牌的数值距离
            int diff = nums[i]-nums[i-1];
            if(diff>3){
                return false;
            }else if(diff==3){
                if(countZero==2) countZero-=2;
                else return false;
            }else if(diff==2){
                if(countZero>0) countZero-=1;
                else return false;
            }else if(diff==0){
                return false;
            }
        }
        return true;
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
     * 783. 二叉搜索树节点最小距离
     * 给你一个二叉搜索树的根节点 root ，返回树中任意两不同节点值之间的最小差值。
     *
     * @param root 二叉搜索树
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
     * 28. 实现 strStr()
     * 给定两个字符串haystack和needle ，请在haystack字符串中找出needle字符串出现的第一个位置（下标从0开始）。如果不存在，则返回-1。
     *
     * @param haystack 主串
     * @param needle 子串
     * @return 首次出现的位置
     */
    public int strStr(String haystack, String needle) {

        /*//1、直接调包
        if(haystack==null) return -1;
        if(needle==null) return 0;
        return haystack.indexOf(needle);*/

        //2、暴力枚举
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
     * 897. 递增顺序搜索树
     * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
     * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     *
     * @param root 二叉搜索树
     * @return 递增搜索树
     */
    public TreeNode increasingBST(TreeNode root) {
        if(root==null) return null;
        inOrderForIncreasingBST(root);
        return headNode;
    }
    private TreeNode preNode;
    private TreeNode headNode;
    private void inOrderForIncreasingBST(TreeNode root){
        if(root==null) return;
        //递归左子
        inOrderForIncreasingBST(root.left);
        //业务处理
        if(preNode==null){
            preNode = root;
            headNode = root;
        }else{
            root.left = null;
            preNode.right = root;
            preNode = root;
        }
        //递归右子
        inOrderForIncreasingBST(root.right);
    }

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     *
     * @param root 二叉搜索树
     * @param low 小值
     * @param high 大值
     * @return 范围和
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if(root==null) return sum;
        sum += (root.val>=low) ? rangeSumBST(root.left,low,high) : 0;
        sum += (root.val>=low && root.val<=high) ? root.val : 0;
        sum += (root.val<=high) ? rangeSumBST(root.right,low,high) : 0;
        return sum;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
     * 设计一个算法来计算你所能获取的最大利润。返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * @param prices 价格数组
     * @return 最大收益
     */
    public int maxProfit(int[] prices) {
        //最大收益
        int maxProfit = 0;
        if(prices==null || prices.length==0) return maxProfit;
        //最小价格，滑动更新
        int minPrice = prices[0];
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices 价格数组
     * @return 最大收益
     */
    public int maxProfit2(int[] prices) {
        //最大收益
        int maxProfit = 0;
        if(prices==null || prices.length==0) return maxProfit;
        //动态更新的最小及最大价格
        int minPrice = prices[0];
        int maxPrice = prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i-1]>prices[i]){
                //存在获利区间
                if(maxPrice>minPrice){
                    maxProfit += maxPrice - minPrice;
                }
                //价格下降
                minPrice = prices[i];
                maxPrice = prices[i];
            }else{
                //价格上升
                maxPrice = prices[i];
            }
        }
        //存在获利区间
        if(maxPrice>minPrice){
            maxProfit += maxPrice - minPrice;
        }
        return maxProfit;
    }

    /**
     * 690. 员工的重要性
     * 输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
     *
     * @param employees 员工列表
     * @param id 员工id
     * @return 员工和他所有下属的重要度之和
     */
    public int getImportance(List<Employee> employees, int id) {

        /*//1、暴力破解：时间复杂度O(N^2)
        int importance = 0;
        for(Employee employee : employees){
            if(employee.id == id){
                importance += employee.importance;
                if(employee.subordinates!=null && employee.subordinates.size()>0){
                    for(Integer subId : employee.subordinates){
                        importance += getImportance(employees, subId);
                    }
                }
                break;
            }
        }
        return importance;*/

        //2、哈希表：时间复杂度O(N)
        //将根据id查employee的复杂度从0(N)降低到O(1)
        Map<Integer,Employee> map = new HashMap<>();
        for(Employee employee : employees){
            map.put(employee.id,employee);
        }
        return getImportance(map,id);

    }
    //深度优先遍历
    private int getImportance(Map<Integer,Employee> map, int id){
        int importance = 0;
        Employee employee = map.get(id);
        importance += employee.importance;
        if(employee.subordinates!=null){
            for(Integer subId : employee.subordinates){
                importance += getImportance(map,subId);
            }
        }
        return importance;
    }

    /**
     * 7. 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1]，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * @param x 整数
     * @return 反转结果
     */
    public int reverse(int x) {

        /*//1、使用字符串收集反转后的结果，再解析为数字
        StringBuilder sb = new StringBuilder();
        if(x<0){
            x = -x;
            sb.append('-');
        }
        while(x!=0){
            sb.append(x%10);
            x /= 10;
        }
        try{
            return Integer.parseInt(sb.toString());
        }catch(Exception e){
            return 0;
        }*/

        //2、只使用数学方法，不借助字符串，不需要处理正负号
        //弹出 num = x % 10; x /= 10;
        //压入 res = res * 10 + num;
        int res = 0;
        while (x != 0) {
            int tmp = res * 10 + x % 10;
            if (tmp / 10 != res) { // 溢出!!!
                return 0;
            }
            res = tmp;
            x /= 10;
        }
        return res;

    }

    /**
     * 872. 叶子相似的树
     * 一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个叶值序列。
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     *
     * @param root1 二叉树1
     * @param root2 二叉树2
     * @return 是否相似
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if(root1==root2) return true;
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2 = new ArrayList<>();
        preOrder(root1,leaf1);
        preOrder(root2,leaf2);
        return leaf1.equals(leaf2);
    }
    private void preOrder(TreeNode root, List<Integer> leaf){
        if(root==null) return;
        if(root.left==null && root.right==null){
            leaf.add(root.val);
            return;
        }
        preOrder(root.left,leaf);
        preOrder(root.right,leaf);
    }

    /*
     * 993. 二叉树的堂兄弟节点
     * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
     * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
     *
     * @param root 根节点
     * @param x 节点值1
     * @param y 节点值2
     * @return x和y是否为堂兄弟
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer,Integer> map = new HashMap<>();
        nodeParentMap(root,map);
        int depthX = 0, depthY = 0;
        Integer valX = x, valY = y;
        while(valX!=null){
            depthX++;
            valX = map.get(valX);
        }
        while(valY!=null){
            depthY++;
            valY = map.get(valY);
        }
        return depthX==depthY && !map.get(x).equals(map.get(y));
    }
    //用哈希表存储节点与父节点的映射
    private void nodeParentMap(TreeNode root, Map<Integer,Integer> map){
        if(root==null) return;
        if(root.left!=null){
            map.put(root.left.val,root.val);
            nodeParentMap(root.left,map);
        }
        if(root.right!=null){
            map.put(root.right.val,root.val);
            nodeParentMap(root.right,map);
        }
    }

}
