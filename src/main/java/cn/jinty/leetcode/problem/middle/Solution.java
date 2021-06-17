package cn.jinty.leetcode.problem.middle;

import cn.jinty.leetcode.entity.Node;
import cn.jinty.struct.UnionFind;
import cn.jinty.struct.linear.ListNode;
import cn.jinty.struct.tree.TreeNode;

import java.math.BigInteger;
import java.util.*;

/**
 * LeetCode - 中等题
 *
 * @author jinty
 * @date 2021/6/10
 **/
public class Solution {

    /**
     * 面试题 01.07. 旋转矩阵
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。
     * 请你设计一种算法，将图像旋转 90 度。
     *
     * @param matrix 矩阵
     */
    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix.length-1;
        while(left<right){
            rotate(left++,right--,matrix);
        }
    }
    //对角线确定一个环
    private void rotate(int left,int right,int[][] matrix){
        for(int i=0;left+i<right;i++){
            swap(matrix,left,left+i,right-i,left);
            swap(matrix,right-i,left,right,right-i);
            swap(matrix,right,right-i,left+i,right);
        }
    }
    //原地交换
    private void swap(int[][] arr,int a1, int b1, int a2, int b2){
        if(!(a1==a2 && b1==b2)){
            arr[a1][b1] ^= arr[a2][b2];
            arr[a2][b2] ^= arr[a1][b1];
            arr[a1][b1] ^= arr[a2][b2];
        }
    }

    /**
     * 面试题 17.11. 单词距离
     * 有个内含单词的超大文本文件，给定任意两个单词，
     * 找出在这个文件中这两个单词的最短距离(相隔单词数)。
     *
     * @param words 大文本
     * @param word1 单词1
     * @param word2 单词2
     * @return 最短距离
     */
    public int findClosest(String[] words, String word1, String word2) {
        //遍历文本，分别收集两个单词的位置标记
        List<Integer> stamp1 = new ArrayList<>();
        List<Integer> stamp2 = new ArrayList<>();
        for(int i=0;i<words.length;i++){
            if(words[i].equals(word1)){
                stamp1.add(i);
            }else if(words[i].equals(word2)){
                stamp2.add(i);
            }
        }
        //遍历位置标记，找最小值
        Integer min = Integer.MAX_VALUE;
        for(Integer i : stamp1){
            for(Integer j : stamp2){
                min = Math.min(min,Math.abs(i-j));
            }
        }
        return min;
    }

    /**
     * 328. 奇偶链表
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
     * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *
     * @param head 链表
     * @return 链表
     */
    public ListNode oddEvenList(ListNode head) {
        //特殊情况
        if(head==null || head.next==null || head.next.next==null){
            return head;
        }
        //收集奇数节点
        ListNode cur = head;
        ListNode oddHead = new ListNode(cur.val);
        ListNode oddTail = oddHead;
        while(cur!=null && cur.next!=null && cur.next.next!=null){
            ListNode tmp = new ListNode(cur.next.next.val);
            oddTail.next = tmp;
            oddTail = oddTail.next;
            cur = cur.next.next;
        }
        //收集偶数节点
        cur = head.next;
        ListNode evenHead = new ListNode(cur.val);
        ListNode evenTail = evenHead;
        while(cur!=null && cur.next!=null && cur.next.next!=null){
            ListNode tmp = new ListNode(cur.next.next.val);
            evenTail.next = tmp;
            evenTail = evenTail.next;
            cur = cur.next.next;
        }
        //连接奇偶
        oddTail.next = evenHead;
        return oddHead;

        //原地算法

        //定义四个指针：奇头、奇尾、偶头、偶尾
        //奇偶同步后移，分别收集奇偶，最后连接起来
        //注意：不可以先收集奇再收集偶，收集一次后，原顺序就被打乱了
        /*if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;*/

    }

    /**
     * 1338. 数组大小减半
     * 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
     * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
     *
     * @param arr 数组
     * @return 使数组减半的最小大小
     */
    public int minSetSize(int[] arr) {
        //特殊情况
        if(arr==null || arr.length==0){
            return 0;
        }
        //词频统计
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        //按频率排序
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer,Integer> o1, Map.Entry<Integer,Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());
        //数组减半
        int len = arr.length/2;
        int mid = 0;
        int ans = 0;
        while(mid<len){
            ans++;
            mid += queue.poll().getValue();
        }
        return ans;
    }

    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * @param n 括号的对数
     * @return 括号所有组合
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder record = new StringBuilder();
        generateParenthesis(result,record,0,n);
        return result;
    }
    /**
     * 递归函数 - 回溯
     *
     * @param result 最终结果
     * @param record 当前字符串记录
     * @param left 字符串记录中未配对左括号数量
     * @param remain 剩余括号对
     */
    private void generateParenthesis(List<String> result,StringBuilder record,int left,int remain){
        //全部完成配对
        if(remain==0){
            result.add(record.toString());
        }else{
            if(left==0){
                //没有未配对的左括号
                record.append('(');
                generateParenthesis(result,record,left+1,remain);
                record.deleteCharAt(record.length()-1);
            }else{
                //有未配对的左括号
                if(left<remain){
                    //左括号未用完
                    record.append('(');
                    generateParenthesis(result,record,left+1,remain);
                    record.deleteCharAt(record.length()-1);
                    record.append(')');
                    generateParenthesis(result,record,left-1,remain-1);
                    record.deleteCharAt(record.length()-1);
                }else{
                    //左括号已用完
                    record.append(')');
                    generateParenthesis(result,record,left-1,remain-1);
                    record.deleteCharAt(record.length()-1);
                }
            }
        }
    }

    /**
     * 386. 字典序排数
     * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
     *
     * @param n 给定整数
     * @return 1-n字典顺序
     */
    public List<Integer> lexicalOrder(int n) {
        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> {
            char[] c1 = o1.toString().toCharArray();
            char[] c2 = o2.toString().toCharArray();
            for(int i=0;i<Math.min(c1.length,c2.length);i++){
                if(c1[i]<c2[i]){
                    return -1;
                }
                if(c1[i]>c2[i]) {
                    return 1;
                }
            }
            return o1-o2;
        });
        for(int i=1;i<=n;i++){
            treeSet.add(i);
        }
        return new ArrayList<>(treeSet);
    }

    /**
     * 547、省份数量
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。
     * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     *
     * @param isConnected 城市-城市-是否连接
     * @return 省份数量
     */
    public int findCircleNum(int[][] isConnected) {
        //省份集
        List<Set<Integer>> circle = new ArrayList<>();
        int len = isConnected.length;
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(isConnected[i][j]==1){
                    //找所在省份
                    Set<Integer> province1 = null;
                    Set<Integer> province2 = null;
                    for(Set<Integer> one : circle){
                        if(one.contains(i)) province1 = one;
                        if(one.contains(j)) province2 = one;
                    }
                    //分四种情况讨论
                    if(province1==null && province2==null){
                        province1 = new HashSet<>();
                        province1.add(i); province1.add(j);
                        circle.add(province1);
                    }else if(province1==null){
                        province2.add(i);
                    }else if(province2==null){
                        province1.add(j);
                    }else{
                        if(province1!=province2){
                            province1.addAll(province2);
                            circle.remove(province2);
                        }
                    }
                }
            }
        }
        //返回省份数
        return circle.size();
    }

    /**
     * 面试题 17.07. 婴儿名字
     * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
     * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
     * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。
     * 设计一个算法打印出每个真实名字的实际频率。
     * 注意：如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
     * 在结果列表中，选择 字典序最小 的名字作为真实名字。
     *
     * @param names 名字及频率
     * @param synonyms 本质上相同的名字
     * @return 名字及实际频率
     */
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        UnionFind uf = new UnionFind();
        //初始化并查集
        for(String name : names){
            int idx = name.indexOf("(");
            String str1 = name.substring(0,idx);
            String str2 = name.substring(idx+1,name.length()-1);
            uf.parents.put(str1,str1);
            uf.number.put(str1,Integer.parseInt(str2));
        }
        //合并并查集
        for(String synonym : synonyms){
            int idx = synonym.indexOf(",");
            String str1 = synonym.substring(1,idx);
            String str2 = synonym.substring(idx+1,synonym.length()-1);
            //名字对中可能有没出现过的名字
            if(uf.parents.get(str1)==null){
                uf.parents.put(str1,str1);
                uf.number.put(str1,0);
            }
            if(uf.parents.get(str2)==null){
                uf.parents.put(str2,str2);
                uf.number.put(str2,0);
            }
            uf.union(str1,str2);
        }
        //构建结果
        List<String> answer = new ArrayList<>();
        for(String str : uf.parents.keySet()){
            //只收集顶层节点及其秩
            if(str.equals(uf.parents.get(str))){
                answer.add(str+"("+uf.getNumber(str)+")");
            }
        }
        return answer.toArray(new String[0]);
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 注意：是子串而不是子序列
     *
     * @param s 字符串(由英文字母、数字、符号和空格组成)
     * @return 满足条件的最长子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        //双指针
        int max = 0, left = 0, right = -1;
        //记录词频
        int[] charMap = new int[128];
        //一次遍历，时间复杂度O(N)
        for(int i=0; i<s.length(); i++){
            right++;
            int idx = s.charAt(i);
            charMap[idx]++;
            if(charMap[idx]>1){
                while(left<right){
                    int idx1 = s.charAt(left++);
                    charMap[idx1]--;
                    if(idx==idx1){
                        break;
                    }
                }
            }else{
                max = Math.max(max,right-left+1);
            }
        }
        return max;
    }

    /**
     * 1630. 等差子数组
     * 如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是 等差数列 。
     * 更正式地，数列 s 是等差数列，只需要满足：对于每个有效的 i ， s[i+1] - s[i] == s[1] - s[0] 都成立。
     * 请判断给定边界的子数组能否重新排列后形成等差数列
     *
     * @param nums 原数组
     * @param l 左边界数组
     * @param r 右边界数组
     * @return List<Boolean>
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> list = new ArrayList<>();
        int len = l.length;
        for(int i=0;i<len;i++){
            list.add(checkArithmeticSubarrays(nums,l[i],r[i]));
        }
        return list;
    }
    private boolean checkArithmeticSubarrays(int[] nums, int l, int r){
        int[] arr = Arrays.copyOfRange(nums,l,r+1);
        Arrays.sort(arr);
        int diff = arr[0] - arr[1];
        for(int i=1; i<arr.length-1;i++){
            if(diff != (arr[i] - arr[i+1])){
                return false;
            }
        }
        return true;
    }

    /**
     * 1669. 合并两个链表
     * 给你两个链表list1 和list2，它们包含的元素分别为n个和m个。
     * 请你将list1中第a个节点到第b个节点删除，并将list2接在被删除节点的位置。
     *
     * @param list1 链表1
     * @param a 删除区间左边界
     * @param b 删除区间右边界
     * @param list2 链表2
     * @return 处理后的链表
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        //找a的前一个结点以及b所在结点
        int countA = 0, countB = 0;
        ListNode nodeA = list1, nodeB = list1, tmp = list1;
        while(tmp.next!=null){
            if(countA<a){
                countA++;
                nodeA = tmp;
            }
            tmp = tmp.next;
            if(countB<b){
                countB++;
                nodeB = tmp;
            }
        }
        //找list2的尾结点
        ListNode tail = list2;
        while(tail.next!=null){
            tail = tail.next;
        }
        //连接
        nodeA.next = list2;
        tail.next = nodeB.next;
        return list1;
    }

    /**
     * 950. 按递增顺序显示卡牌
     * 牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。
     * 最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。
     * 现在，重复执行以下步骤，直到显示所有卡牌为止：
     * 1、从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。
     * 2、如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。
     * 3、如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
     * 返回能以递增顺序显示卡牌的牌组顺序。
     *
     * @param deck 给定卡牌(初始序列不需要关注)
     * @return 可以按规则处理后递增显示卡牌的卡牌序列
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        //排序
        Arrays.sort(deck);
        //使用队列模拟操作过程
        Queue<Integer> queue = new LinkedList<>();
        //从大到小遍历卡牌
        for(int i=deck.length-1;i>=0;i--){
            if(queue.isEmpty()){
                queue.offer(deck[i]);
            }else{
                //将队头移到队尾
                queue.offer(queue.poll());
                //小卡牌加入队尾
                queue.offer(deck[i]);
            }
        }
        //逆序读取队列，得到卡牌序列
        int[] ans = new int[deck.length];
        for(int i=deck.length-1;i>=0;i--){
            ans[i] = queue.poll();
        }
        return ans;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 树中没有重复的元素
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     * @return 二叉树
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return buildTree1(preorder,0,preorder.length-1,
                inorder,0,inorder.length-1);
    }
    private TreeNode buildTree1(int[] preorder, int preleft, int preright,
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
        root.left = buildTree1(preorder, preleft+1, preleft+mid-inleft,
                inorder, inleft, mid-1);
        root.right = buildTree1(preorder, preleft+mid-inleft+1, preright,
                inorder, mid+1, inright);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder 中序遍历序列
     * @param postorder 后序遍历序列
     * @return 二叉树
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTree2(inorder,0,inorder.length-1,
                postorder,0,postorder.length-1);
    }
    private TreeNode buildTree2(int[] inorder, int inLeft, int inRight,
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
        root.left = buildTree2(
                inorder, inLeft, inLeft+len-1,
                postorder, postLeft, postLeft+len-1
        );
        root.right = buildTree2(
                inorder, inLeft+len+1, inRight,
                postorder, postLeft+len, postRight-1
        );
        return root;
    }

    /**
     * 406. 根据身高重建队列
     * 每个people[i]=[hi,ki]表示第i个人的身高为hi，前面正好有ki个身高大于或等于hi的人。
     *
     * @param people 乱序队列
     * @return 按规则重建后的队列
     */
    public int[][] reconstructQueue(int[][] people) {
        int[][] ans = new int[people.length][];
        //将乱序队列升序排序
        Arrays.sort(people, ((o1, o2) -> o1[0] - o2[0]));
        //从身高最低者开始重建队列
        for (int[] person : people) {
            int count = person[1];
            for (int j = 0; j < ans.length; j++) {
                if (ans[j] == null && count == 0) {
                    ans[j] = person;
                    break;
                } else {
                    if (ans[j] == null || ans[j][0] == person[0]) {
                        count--;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 15. 三数之和
     * 求所有满足 a + b + c = 0 的三元组集合(集合中不能有重复的三元组)
     *
     * @param nums 数组
     * @return 三元组集合
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
     *
     * @param s 给定字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        //动态规划
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
     * 12. 整数转罗马数字
     *
     * @param num 整数
     * @return 罗马数字字符串
     */
    public String intToRoman(int num) {
        /*StringBuilder sb = new StringBuilder();
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
        return sb.toString();*/

        //上述写法太长，试着将代码缩短一些

        //将数字与其对应的字符存到数组中
        int[] number = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] character = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        //将num从大单位开始分解
        for(int i=0;i<number.length;i++){
            while(num>=number[i]){
                sb.append(character[i]);
                num -= number[i];
            }
        }
        return sb.toString();

    }

    /**
     * 8. 字符串转换整数 (atoi)
     *
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
     * 求所有和为target的四元组(四元组不重复)
     *
     * @param nums 数组
     * @param target 目标值
     * @return 四元组
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
     * 将存在重叠部分的区间合并
     *
     * @param intervals 区间数组
     * @return 重叠部分合并后的区间数组
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
        //最后一个区间
        list.add(new int[]{left,right});
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * @param strs 字符串数组
     * @return 异位词列表集合
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        //哈希表：字符串按字符升序后作为key，值为原字符串组成的列表
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
     * 给你一个待查数组 queries ，数组中的元素为 1 到 m 之间的正整数。请你根据以下规则处理所有待查项 queries[i] ：
     * 一开始，排列 P=[1,2,3,...,m]。对于当前的 i ，请你找出待查项 queries[i] 在排列 P 中的位置（下标从 0 开始），然后将其从原位置移动到排列 P 的起始位置（即下标为 0 处）。
     * 注意， queries[i] 在 P 中的位置就是 queries[i] 的查询结果。请你以数组形式返回待查数组 queries 的查询结果。
     *
     * @param queries 查询 (1 <= queries.length <= m && 1 <= queries[i] <= m)
     * @param m 排列的最大值 (1 <= m <= 10^3)
     * @return 查询结果
     */
    public int[] processQueries(int[] queries, int m) {

        //1、定义一个长度为m的数组，每次查询都遍历这个数组，并做前置操作，时间复杂度O(NM)

        //2、维护一个前置区间和原区间，先在前置区间中查询，未命中再到原区间查询
        int[] res = new int[queries.length];
        //存放被前置的值(列表尾部为最前的一个值)
        List<Integer> record = new ArrayList<>();
        //遍历查询数组
        for(int i=0;i<queries.length;i++){
            //计算前置区间中小于查询值的值的数量
            int lessThen = 0;
            //能否在前置区间中找到查询值，如果可以，那么该值在前置区间中的位置即为查询结果
            boolean foundInRecord = false;
            //遍历前置区间
            for(int j=record.size()-1;j>=0;j--){
                Integer tmp = record.get(j);
                if(tmp==queries[i]){
                    //在前置区间找到查询值
                    res[i] = record.size()-1-j;
                    record.remove(tmp);
                    record.add(tmp);
                    foundInRecord = true;
                    break;
                }else if(tmp<queries[i]){
                    lessThen++;
                }
            }
            //在前置区间找不到查询值
            if(!foundInRecord){
                //原位置 + 前置区间大小 + 前置区间中本来就比查询值小的值的数量
                res[i] = queries[i] - 1 + record.size() - lessThen;
                record.add(queries[i]);
            }
        }
        return res;

    }

    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     *
     * @param nums 数组
     * @return 能否跳到最后一个格子
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
     * 在旋转排序数组中，寻找目标值，返回其所在索引，若不存在返回-1。
     *
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
     * 98. 验证二叉搜索树
     *
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
     *
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
     * 300. 最长递增子序列
     *
     * @param nums 数组
     * @return 最长递增子序列长度
     */
    public int lengthOfLIS(int[] nums) {
        //动态规划
        //dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]) max = Math.max(max,dp[j]);
            }
            dp[i] = max+1;
        }
        int maxLen = 0;
        for(int i=0;i<nums.length;i++){
            if(dp[i]>maxLen) maxLen=dp[i];
        }
        return maxLen;
    }

    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     * @param matrix 矩阵
     * @return 列表
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int up = 0, down = matrix.length-1;
        int left = 0, right = matrix[0].length-1;
        while(up<=down && left<=right){
            //从左向右
            for(int i=left;i<=right;i++){
                list.add(matrix[up][i]);
            }
            //从上到下
            for(int i=up+1;i<=down;i++){
                list.add(matrix[i][right]);
            }
            //从右到左
            if(up<down){
                for(int i=right-1;i>=left;i--){
                    list.add(matrix[down][i]);
                }
            }
            //从下到上
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
     * 在一个加油站可以补充一定的油量，到达下一个加油站需要消耗一定的油量。
     * 能否从某个加油站开始绕一圈回来，能则返回这个加油站的位置索引，否则返回-1。
     *
     * @param gas 加油站的油量
     * @param cost 到下一站的油耗
     * @return 符合要求的起点
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {

        /*//1、两层遍历，时间复杂度O(N^2)
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

        //2、一层遍历，时间复杂度O(N)
        //以ABCD为例，如果从A出发无法到D，那么从AD之间的任何一点出发都无法到达D
        //因为A到B时剩余油量>=0，这种情况下无法到达D，那么直接从B开始油量更少，更不可能到达D
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
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     *
     * @param head 头节点
     * @param left 反转区间左边界 (从1开始计数)
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
     * 剑指 Offer 31. 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     *
     * @param pushed 压入顺序
     * @param popped 弹出顺序
     * @return 是否合法
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        //使用栈模拟过程
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for(int j=0; j<popped.length; j++){
            if(i < pushed.length){
                //栈空先压入元素
                if(stack.isEmpty()) stack.push(pushed[i++]);
                //栈顶与下一个弹出元素比较
                while(i<pushed.length && stack.peek() != popped[j]){
                    stack.push(pushed[i++]);
                }
            }
            if(stack.peek() != popped[j]) return false;
            stack.pop();
        }
        return true;
    }

    /**
     * 400. 第 N 位数字
     * 数字序列为 123456789101112131415…
     *
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
     *
     * @param nums 数组
     * @return 最小的数
     */
    public String minNumber(int[] nums) {
        //A+B与B+A比较字典序，字典序小的表示数值小
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
     * 456. 132 模式
     * 给你一个整数数组 nums ，数组中共有 n 个整数。
     * 132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
     * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
     * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
     *
     * @param nums 数组
     * @return 是否存在132模式
     */
    public boolean find132pattern(int[] nums) {

        /*//1、时间复杂度O(N^2)
        if(nums.length<3) return false;
        //left表示i左边的最小值，为模式1
        int left = nums[0];
        //nums[i]为模式3，nums[j]为模式2
        for(int i=0;i<nums.length;i++){
            //动态更新模式1
            left = Math.min(left,nums[i]);
            //满足模式3
            if(nums[i]>left){
                //寻找模式2
                for(int j=i+1;j<nums.length;j++){
                    if(left<nums[j] && nums[j]<nums[i]) return true;
                }
            }
        }
        return false;*/

        /*//2、时间复杂度O(NlogN)
        if(nums.length<3) return false;
        //leftMin表示i左边的最小值，为模式1
        int leftMin = nums[0];
        //rightAll存储右边的所有值，作为模式2，存储格式为<数字,出现次数>
        TreeMap<Integer,Integer> rightAll = new TreeMap<>();
        for(int num:nums){
            rightAll.put(num,rightAll.getOrDefault(num,0)+1);
        }
        //nums[i]为模式3
        for(int i=0;i<nums.length;i++){
            //动态更新模式1
            leftMin = Math.min(leftMin,nums[i]);
            //动态更新模式2的可用数据集
            int count = rightAll.get(nums[i]);
            if(count==1) rightAll.remove(nums[i]);
            else rightAll.put(nums[i],count-1);
            //符合模式1和3
            if(leftMin<nums[i]){
                //寻找模式2
                Integer key = rightAll.ceilingKey(leftMin+1);
                if(key != null && key < nums[i]) return true;
            }
        }
        return false;*/

        //3、时间复杂度O(N)
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
     * 82. 删除排序链表中的重复元素 II
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
     * 请你删除链表中所有存在数字重复情况的节点，
     * 只保留原始链表中 没有重复出现 的数字。
     *
     * @param head 排序链表
     * @return 结果链表
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
     * 剑指 Offer 66. 构建乘积数组
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
     * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
     * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
     * 不能使用除法。
     *
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
     *
     * @param postorder 后序遍历序列
     * @return 是否为二叉搜索树
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder,0,postorder.length-1);
    }
    //递归分治
    private boolean verifyPostorder(int[] postorder,int begin,int end) {
        if(begin>=end) return true;
        //后序遍历的特点：[左子树][右子树][根]
        //二叉搜索树的特点：[左子树]<[根]<[右子树]
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
        return verifyPostorder(postorder,begin,mid-1)
                && verifyPostorder(postorder,mid,end-1);
    }

    /**
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
     * 找出所有从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
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
    //递归函数
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
     * 74. 搜索二维矩阵
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
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
        //暴力枚举：时间复杂度O(2^N)
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        subsetsWithDup(nums,set,list,0);
        return new ArrayList<>(set);
    }
    //递归函数
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
     *
     * @param A 主树
     * @param B 子树
     * @return B是否为A的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B==null) return false;
        return isSubStructureFromTop(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }
    //从A开始，B的所有节点能否映射到A
    private boolean isSubStructureFromTop(TreeNode A, TreeNode B){
        if(B==null) return true;
        if(A==null) return false;
        if(A.val != B.val) return false;
        return isSubStructureFromTop(A.left, B.left) && isSubStructureFromTop(A.right,B.right);
    }

    /**
     * 剑指 Offer 38. 字符串的排列
     *
     * @param s 字符串
     * @return 所有排列
     */
    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        permutation(set,s.toCharArray(),0);
        return set.toArray(new String[0]);
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
     *            = (10 * 9 / 8 + 7) - (6 * 5 / 4 - 3) - (2 * 1)
     *
     * @param N 正整数
     * @return 笨阶乘结果
     */
    public int clumsy(int N) {
        int sum = 0;
        boolean begin = true;
        while(N>0){
            //以四个数为一组来处理
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
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums 有序数组
     * @return 删除后的数组长度
     */
    public int removeDuplicates(int[] nums) {
        //计数、移位、缩短长度
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
     *
     * @param nums 旋转排序数组 (例如：4,5,6,7,0,1,2,3)
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

    /**
     * 236. 二叉树的最近公共祖先
     *
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

        /*//1、时间复杂度：O(N^2)
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

        //2、时间复杂度：O(N)
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
     * 264. 丑数 II
     * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     * 注意：1 也是丑数
     *
     * @param n 整数
     * @return 第n个丑数
     */
    public int nthUglyNumber(int n) {
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
        //动态规划
        if(s==null || s.length()==0 || s.charAt(0)=='0') return 0;
        //dp[i]表示s[0...i]的解码方法
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for(int i=1;i<s.length();i++){
            char cur = s.charAt(i);
            char pre = s.charAt(i-1);
            int twoNum = (i == 1) ? 1 : dp[i - 2];
            if(cur=='0'){
                //0不能单独解析，必须组成10或20，如果前一个数不是1或2，则无法解析
                if(pre=='0' || pre>'2') return 0;
                dp[i] = twoNum;
            } else {
                //1~9可以单独解析或者与前一个数组合解析
                if(pre=='1' || (pre=='2'&&cur<'7')) dp[i] = dp[i-1] + twoNum;
                else dp[i] = dp[i-1];
            }
        }
        return dp[s.length()-1];
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

        /*//1、回溯算法 时间复杂度O(2^N)
        if(nums==null || nums.length==0) return answer;
        //升序排序
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        //从最大值开始遍历，收集一个倒序的整除子集
        largestDivisibleSubset(nums,nums.length-1,list);
        return answer;*/

        //2、动态规划 时间复杂度O(N^2)
        //解法类似于最长递增子序列
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
    //成员变量
    private List<Integer> answer = null;
    //递归函数
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
        //动态规划
        if(nums==null) return 0;
        //dp[i]表示总和为i的方案数
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i=1;i<dp.length;i++){
            for (int num : nums) {
                //如果i可以拆出一个num，这个num放在末尾，方案数可以新增dp[i-num]
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * 322. 零钱兑换
     * 计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
     * 每种硬币的数量是无限的。
     *
     * @param coins 不同面额的硬币
     * @param amount 目标金额
     * @return 最少硬币数
     */
    public int coinChange(int[] coins, int amount) {

        /*//贪心算法：从面值最大的硬币开始找钱
        Arrays.sort(coins);
        int count = 0;
        int idx = coins.length-1;
        while(amount>0 && idx>=0){
            if(amount>=coins[idx]){
                count++;
                amount -= coins[idx];
            }else{
                idx--;
            }
        }
        return amount==0 ? count : -1;*/

        //贪心算法在某些案例下无法通过
        //例如：[80,90] 160，上述算法得-1，而真实解为2
        //因为贪心拿了大钱，导致后面的钱找不开

        //动态规划 dp[i][j]表示用coins[0..i]换j的最少硬币数
        int[][] dp = new int[coins.length][amount+1];
        //第0列：amount为0，不需要硬币
        //第0行：每凑够一个coins[0]，硬币加一
        for(int i=1;i<=amount;i++){
            dp[0][i] = -1;
            if(i==coins[0]) dp[0][i] = 1;
            else if(i>coins[0]) dp[0][i] = (dp[0][i-coins[0]] == -1 ? -1 : dp[0][i-coins[0]]+1);
        }
        //其余行列
        for(int i=1;i<coins.length;i++){
            for(int j=1;j<=amount;j++){
                dp[i][j] = -1;
                if(dp[i-1][j] != -1){
                    dp[i][j] = dp[i-1][j];
                }
                if(j-coins[i]>=0 && dp[i][j-coins[i]] != -1){
                    dp[i][j] = (dp[i][j]==-1 ? (dp[i][j-coins[i]]+1) : Math.min(dp[i][j],dp[i][j-coins[i]]+1));
                }
            }
        }
        return dp[coins.length-1][amount];

    }

    /**
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     *
     * @param amount 目标金额
     * @param coins 不同面额的硬币
     * @return 组合数
     */
    public int change(int amount, int[] coins) {

        //动态规划：dp[i][j]表示用coins[0..i]换j的组合数
        int[][] dp = new int[coins.length][amount+1];
        //第0列：不需要硬币，故为1种
        for(int i=0;i<coins.length;i++){
            dp[i][0] = 1;
        }
        //第0行：只用coins[0]，合成j只有能或不能
        for(int i=1;i<=amount;i++){
            if(i==coins[0]) dp[0][i] = 1;
            else if(i>coins[0]) dp[0][i] = (dp[0][i-coins[0]]==1 ? 1 : 0);
        }
        //其余行列
        for(int i=1;i<coins.length;i++){
            for(int j=1;j<=amount;j++){
                dp[i][j] = dp[i-1][j] + (j-coins[i]>=0 ? dp[i][j-coins[i]] : 0);
            }
        }
        return dp[coins.length-1][amount];

    }

    /**
     * 1011. 在D天内送达包裹的能力
     * 传送带上的包裹必须在D天内从一个港口运送到另一个港口。传送带上的第i个包裹的重量为weights[i]。
     * 每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     * 返回能在D天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     * @param weights 包裹重量数组
     * @param D 天数
     * @return 最低运载能力
     */
    public int shipWithinDays(int[] weights, int D) {

        //问题分析：把weights分成D份，怎么划分可以使得子数组和的最大值最小

        /*//天数比数组长度长，那么数组可以拆分为一个一个的元素，取其中最大的一个
        if(D>=weights.length){
            int max = weights[0];
            for(int weight : weights) max = Math.max(max,weight);
            return max;
        }
        //计算前缀和
        int[] sum = new int[weights.length];
        sum[0] = weights[0];
        for(int i=1;i<weights.length;i++){
            sum[i] = sum[i-1] + weights[i];
        }*/
        /*//1、暴力递归
        return shipWithinDays(weights,D,0,sum);*/

        //上述递归存在重复计算，时间复杂度较高，计算超时
        //重复计算：第一天[0]，第二天[1,2]，递归剩余，第一天[0,1]，第二天[2]，递归剩余

        /*//2、记忆搜素
        int[][] memory = new int[D+1][weights.length];
        return shipWithinDays(weights,D,0,sum,memory);*/

        //记忆搜索的递归深度太大导致内存溢出
        //记忆搜索中存在递进过程，可以优化为动态规划，避免函数递归

        /*//3、动态规划 dp[i][j]表示在i天内运送[0...j]的货物的最低运载能力
        int[][] dp = new int[D][weights.length];
        //只有1天
        for(int j=0;j<weights.length;j++){
            dp[0][j] = sum[j];
        }
        //大于1天
        for(int i=1;i<D;i++){
            for(int j=0;j<weights.length;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=0;k<j;k++){
                    //前N天运送[0...k]的货物，当前天运送剩余货物
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][k],sum[j]-sum[k]));
                }
            }
        }
        return dp[D-1][weights.length-1];*/

        //上述动态规划的时间复杂度为O(DN^2)，仍然超时

        //4、二分查找
        //首先确定运载能力x的范围[max(weights),sum(weights)]，在这个范围内做二分查找
        //对于一个运载能力x，遍历weights，基于贪心算法计算这种情况所需要的最小天数
        //如果大于D，说明在D天内完成需要更大的运力，否则，在D天内完成只需要更小的运力
        int max = weights[0], sum = 0;
        for(int weight : weights){
            max = Math.max(max,weight);
            sum += weight;
        }
        int left = max, right = sum;
        while(left<right){
            int mid = left + (right-left)/2;
            int day = 1;
            int temp = 0;
            for (int weight : weights) {
                temp += weight;
                if (temp > mid) {
                    day++;
                    temp = weight;
                }
            }
            if(day>D){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;

    }
    //递归函数
    private int shipWithinDays(int[] weights, int D, int idx, int[] sum){
        //数组角标越界，返回0
        if(idx>=weights.length) return 0;
        //记录最大值
        int max = Integer.MAX_VALUE;
        //只剩一天，那么后面的元素只能作为一个子数组
        if(D==1){
            return sum[weights.length-1] - (idx==0 ? 0 : sum[idx-1]);
        }
        //枚举第一天的包裹数，天数减一，剩余包裹递归求解
        for(int i=idx;i<weights.length;i++){
            int curMax = Math.max(
                    (i==idx ? weights[i] : (idx==0 ? sum[i] : sum[i]-sum[idx-1])),
                    shipWithinDays(weights,D-1,i+1,sum)
            );
            max = Math.min(max,curMax);
        }
        return max;
    }
    //递归函数+记忆表
    private int shipWithinDays(int[] weights, int D, int idx, int[] sum, int[][] memory){
        //数组角标越界，返回0
        if(idx>=weights.length) return 0;
        //查找记忆表，命中则直接返回结果
        if(memory[D][idx]!=0) return memory[D][idx];
        //记录最大值
        int max = Integer.MAX_VALUE;
        //只剩一天，那么后面的元素只能作为一个子数组
        if(D==1){
            max = sum[weights.length-1] - (idx==0 ? 0 : sum[idx-1]);
            memory[D][idx] = max;
            return max;
        }
        //枚举第一天的包裹数，天数减一，剩余包裹递归求解
        for(int i=idx;i<weights.length;i++){
            int curMax = Math.max(
                    (i==idx ? weights[i] : (idx==0 ? sum[i] : sum[i]-sum[idx-1])),
                    shipWithinDays(weights,D-1,i+1,sum,memory)
            );
            max = Math.min(max,curMax);
        }
        memory[D][idx] = max;
        return max;
    }

    /**
     * 875. 爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * 珂珂可以决定她吃香蕉的速度 K（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
     * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K为整数）。
     *
     * @param piles 香蕉数量
     * @param h 小时
     * @return 最小速度
     */
    public int minEatingSpeed(int[] piles, int h) {
        //二分查找：先确定速度范围，针对一个速度，计算吃完香蕉的时间，判断时间与h的关系，决定如何缩小速度区间
        //最小速度为1(时间最长)，最大速度为max(piles)(时间最短)
        int max = piles[0];
        for(int pile : piles) max = Math.max(max,pile);
        int left = 1, right = max;
        while(left<right){
            int mid = left + (right-left)/2;
            int hour = 0;
            for(int pile : piles) hour += (pile/mid + (pile%mid==0 ? 0 : 1));
            if(hour>h){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    /**
     * 633. 平方数之和
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
     *
     * @param c 非负整数(0 <= c <= 2^31 - 1)
     * @return 是否
     */
    public boolean judgeSquareSum(int c) {
        /*//枚举a^2的所有整数，直到超出c
        //在c范围内，b^2=c-a^2，对b^2开方求b，判断是否为整数
        if(c==0) return true;
        int i = 1;
        int a2 = i*i;
        while(a2<c){
            int b2 = c - a2;
            if(Math.sqrt(b2)%1==0){
                return true;
            }
            i++;
            a2 = i*i;
        }
        return a2==c;*/

        //上述方法超时

        //费马平方和定理：一个非负整数能够表示为两个整数的平方和，当且仅当该整数的所有形如4k+3的质因子的出现次数均为偶数次
        //例如：441=3*3*7*7，3出现2次，7出现2次，它们都出现偶数次，所以441可以表示为平方和，441=21^2+0^2
        int i=2;
        //i<=c为什么可以优化为i*i<=c？
        //因为i*i>c这种情况下，c一定是最后一个质数，如果c还可以继续分解，则i*i<=c一定成立
        //如果使用i<=c，则剩余最后一个质数，且该质数只有一个时，i需要从上一个质数遍历++到下一个质数，时间比较久
        while(i*i<=c){
            if(c%i==0){
                //能够整除，则统计i质因子的数量
                int count = 0;
                while(c%i==0){
                    count++;
                    c /= i;
                }
                //判断i是否形如4k+3，若是且数量为奇数，返回false
                if(i%4==3 && count%2!=0){
                    return false;
                }
            }else{
                i++;
            }
        }
        return c%4!=3;

    }

    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * @param grid 二维网格
     * @return 数量
     */
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    count++;
                    //将相邻陆地涂色
                    pantIsland(grid,i,j);
                }
            }
        }
        return count;
    }
    private void pantIsland(char[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length) return;
        if(grid[i][j]!='1') return;
        grid[i][j] = '2';
        pantIsland(grid,i-1,j);
        pantIsland(grid,i+1,j);
        pantIsland(grid,i,j-1);
        pantIsland(grid,i,j+1);
    }

    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * @param head 有序链表
     * @return 二叉搜索树
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return new TreeNode(head.val);
        //快慢指针寻找链表中点
        ListNode pre = head;
        ListNode low = head, fast = head;
        while(fast!=null){
            fast = fast.next;
            if(fast!=null){
                fast = fast.next;
                pre = low;
                low = low.next;
            }
        }
        //链表中点作为根节点，前后分别作为根节点的左右子
        TreeNode root = new TreeNode(low.val);
        root.right = sortedListToBST(low.next);
        if(pre!=low){
            pre.next = null;
            root.left = sortedListToBST(head);
        }
        return root;
    }

    /**
     * 973. 最接近原点的 K 个点
     * 有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * @param points 二维数组
     * @param k 正整数
     * @return k个离原点最近的点
     */
    public int[][] kClosest(int[][] points, int k) {
        if(points==null || points.length==0) return null;
        int[][] ans = new int[k][2];
        //排序
        Arrays.sort(points, Comparator.comparingInt(o -> o[0] * o[0] + o[1] * o[1]));
        //取前k个
        if (k >= 0) System.arraycopy(points, 0, ans, 0, k);
        return ans;
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你都需要支付一次手续费。
     *
     * @param prices 价格数组
     * @param fee 手续费
     * @return 最大收益
     */
    public int maxProfit(int[] prices, int fee) {
        if(prices==null || prices.length<2) return 0;
        //动态规划
        //dp[i][j]表示在第i天，不持有(j==0)或持有股票(j==1)，这种情况下的最大收益
        int[][] dp = new int[prices.length][2];
        //第0天不持有，收益为0
        dp[0][0] = 0;
        //第0天持有，收益为-prices[0]
        dp[0][1] = -prices[0];
        for(int i=1;i<prices.length;i++){
            //第i天不持有：第i-1天不持有则今天保持，第i-1天持有则今天卖出，取其中的最大值
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            //第i天持有：第i-1天持有则今天保持，第i-1天不持有则今天买入，取其中的最大值
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 1、你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 2、卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     *
     * @param prices 价格数组
     * @return 最大收益
     */
    public int maxProfitWithFreeze(int[] prices) {
        if(prices==null || prices.length<2) return 0;
        //动态规划 i表示第i天，j取0、1、2，分别代表第i天不持有(非冷冻)、不持有(冷冻)或持有股票，dp[i][j]表示对应情况下的最大收益
        int[][] dp = new int[prices.length][3];
        //第0天不持有，收益为0
        dp[0][0] = 0;
        dp[0][1] = 0;
        //第0天持有，收益为-prices[0]
        dp[0][2] = -prices[0];
        for(int i=1;i<prices.length;i++){
            //第i天不持有(非冷冻)：第i-1天不持有(非冷冻、冷冻)，今天保持
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            //第i天不持有(冷冻)：第i-1天持有，今天卖出
            dp[i][1] = dp[i-1][2]+prices[i];
            //第i天持有：第i-1天持有则今天保持，第i-1天不持有(非冷冻)则今天买入
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][0]-prices[i]);
        }
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
    }

    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * @param nums 数组
     * @return 只出现一次的数字
     */
    public int singleNumber(int[] nums) {

        /*//1、哈希表 时间复杂度O(N) 空间复杂度O(N)
        //统计数字的出现频率
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //找到出现一次的数字
        int ans = 0;
        for(int key : map.keySet()){
            if(map.get(key)==1){
                ans = key;
                break;
            }
        }
        return ans;*/

        //2、位运算 时间复杂度O(N) 空间复杂度O(1)
        //答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数。
        int ans = 0;
        int bit = 0;
        while(bit<32){
            int bitCount = 0;
            for(int num : nums){
                bitCount += (bit == 0 ? num&1 : (num>>>bit)&1);
            }
            ans |= (bit == 0 ? bitCount%3 : (bitCount%3)<<bit);
            bit++;
        }
        return ans;

    }

    /**
     * 554. 砖墙
     * 你现在要画一条自顶向下的、穿过最少砖块的垂线。
     * 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。
     * 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
     *
     * @param wall 一堵矩形的、由 n 行砖块组成的砖墙
     * @return 穿过的砖块数量的最小值
     */
    public int leastBricks(List<List<Integer>> wall) {
        /*//特殊情况
        if(wall==null || wall.size()==0 || wall.get(0)==null) return 0;
        //最多时每行都穿过一个砖
        int min = wall.size();
        //墙的总长度
        int len = 0;
        //所有不同的前缀和
        Set<Integer> preSumSet = new HashSet<>();
        //每行的前缀和
        List<Set<Integer>> preSumList = new ArrayList<>();
        for(List<Integer> row : wall){
            int rowLen = 0;
            Set<Integer> preSum = new HashSet<>();
            for(Integer brickLen : row){
                rowLen += brickLen;
                preSum.add(rowLen);
                preSumSet.add(rowLen);
            }
            if(len==0) len = rowLen;
            preSumList.add(preSum);
        }
        //在某个位置画下垂线，如果能在前缀和中找到，说明从缝隙经过
        for(Integer i : preSumSet){
            if(i==len) continue;
            int count = 0;
            for(Set<Integer> preSum : preSumList){
                if(!preSum.contains(i)) count++;
            }
            min = Math.min(min,count);
        }
        return min;*/

        //遍历砖墙的每一行，从左到右地扫描每一块砖，将除了最右侧的砖块以外的其他砖块的右边缘到砖墙的左边缘的距离加入到哈希表中。
        //遍历该哈希表，找到出现次数最多的砖块边缘，垂线从这里经过时穿过的砖块最少。
        //最少砖块数量 = 砖墙的高度 - 最大砖块边缘数量。
        int min = wall.size();
        Map<Integer,Integer> map = new HashMap<>();
        for(List<Integer> row : wall){
            int sum = 0;
            for(int i=0;i<row.size()-1;i++){
                sum += row.get(i);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int maxCount = 0;
        for(Integer key : map.keySet()){
            maxCount = Math.max(maxCount,map.get(key));
        }
        return min-maxCount;

    }

    /**
     * 740. 删除并获得点数
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
     * 之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * @param nums 正整数数组 (1 <= nums[i] <= 10^4)
     * @return 最大点数
     */
    public int deleteAndEarn(int[] nums) {
        //1、动态规划：解法类似于打家劫舍
        //求数组最大值
        int maxVal = 0;
        for(int num : nums){
            maxVal = Math.max(maxVal,num);
        }
        //i为数字，sum[i]表示数字i在数组中的总和
        int[] sum = new int[maxVal+1];
        for(int num : nums){
            sum[num] += num;
        }
        if(maxVal==1) return sum[1];
        //基于sum数组做动态规划
        int first = sum[1]; int second = Math.max(sum[1],sum[2]);
        for(int i=3;i<sum.length;i++){
            int temp = second;
            second = Math.max(first+sum[i],second);
            first = temp;
        }
        return second;
    }

    /**
     * 1833. 雪糕的最大数量
     * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
     * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
     * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
     * 注意：Tony 可以按任意顺序购买雪糕。
     *
     * @param costs 雪糕价格数组
     * @param coins 现金量
     * @return 最大雪糕数量
     */
    public int maxIceCream(int[] costs, int coins) {

        /*//1、动态规划
        //dp[i][j]代表在0-i的雪糕中用j元能够买到的最大数量
        int[][] dp = new int[costs.length][coins+1];
        //j==0时，什么都买不到
        //i==0时，满足costs[0]元能够买一个
        for(int j=1;j<=coins;j++){
            if(j==costs[0]){
                while(j<=coins) dp[0][j++] = 1;
            }
        }
        //其余情况
        for(int i=1;i<costs.length;i++){
            for(int j=1;j<=coins;j++){
                //不买costs[i]的雪糕
                dp[i][j] = dp[i-1][j];
                //买costs[i]的雪糕
                if(j>=costs[i]) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-costs[i]]+1);
            }
        }
        return dp[costs.length-1][coins];*/

        //上述做法空间复杂度较高，超出空间限制

        //2、贪心算法
        //从最便宜的开始买，直到钱不足，钱可以有剩余
        Arrays.sort(costs);
        int count = 0;
        for(int i=0;i<costs.length;i++){
            if(coins>=costs[i]){
                count++;
                coins -= costs[i];
            }else{
                break;
            }
        }
        return count;

    }

    /**
     * 978. 最长湍流子数组
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     *
     * 注意：是子数组而不是子序列
     *
     * @param arr 数组
     * @return 最大湍流子数组的长度
     */
    public int maxTurbulenceSize(int[] arr) {
        //最大长度
        int maxSize = 1;
        //临时长度
        int tmpSize = 1;
        //下一个符号是否应为大于
        boolean gt = false;
        //遍历原数组
        for(int i=1;i<arr.length;i++){
            if(tmpSize==1){
                //子数组只有一个元素时，下一个符号可大于或小于，等于则忽略
                if(arr[i-1]<arr[i]){
                    tmpSize++;
                    gt = true;
                }else if(arr[i-1]>arr[i]){
                    tmpSize++;
                    gt = false;
                }
            }else{
                //子数组大于1个元素时，下一个符号由gt指定
                if(gt){
                    if(arr[i-1]>arr[i]){
                        //符合gt，子数组加1，gt反转
                        tmpSize++;
                        gt = false;
                    }else if(arr[i-1]<arr[i]){
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 2;
                        gt = true;
                    }else{
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 1;
                    }
                }else{
                    if(arr[i-1]<arr[i]){
                        //符合gt，子数组加1，gt反转
                        tmpSize++;
                        gt = true;
                    }else if(arr[i-1]>arr[i]){
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 2;
                        gt = false;
                    }else{
                        //不符合gt，当前子数组作为一个结果计入答案中，并重置子数组
                        maxSize = Math.max(maxSize,tmpSize);
                        tmpSize = 1;
                    }
                }
            }
        }
        maxSize = Math.max(maxSize,tmpSize);
        return maxSize;
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     * 实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 数值（按顺序）可以分成以下几个部分：
     * 1、若干空格 2、一个小数或者整数 3、(可选)一个'e'或'E'，后面跟着一个整数 4、若干空格
     * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
     *
     * @param s 字符串
     * @return 是否表示数值
     */
    public boolean isNumber(String s) {
        if(s==null) return false;
        s = s.trim();
        if(s.length()==0) return false;
        //小数点个数
        int countPoint = 0;
        //E(e)个数
        int countE = 0;
        //数字个数
        int countNumBeforeE = 0;
        int countNumAfterE = 0;
        //排除非法情况
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='+' || c=='-'){
                //加减号只能在首部或者E(e)后面
                if(i!=0){
                    char pre = s.charAt(i-1);
                    if(pre!='E' && pre!='e'){
                        return false;
                    }
                }
            }else if(c=='.'){
                //小数点不能超过1个
                if(countPoint==1) return false;
                //E(e)后面不能出现小数点
                if(countE==1) return false;
                countPoint++;
            }else if(c=='E' || c=='e'){
                //E(e)不能超过1个
                if(countE==1) return false;
                countE++;
            }else if(c>='0' && c<='9'){
                if(countE==0) countNumBeforeE++;
                else countNumAfterE++;
            }else{
                return false;
            }
        }
        return countE==0 ? countNumBeforeE>0 : countNumBeforeE>0 && countNumAfterE>0;
    }

    /**
     * 1482. 制作 m 束花所需的最少天数
     * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
     * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
     * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
     * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
     *
     * @param bloomDay n朵花各自的开花时间
     * @param m 花束数
     * @param k 每束花的花朵数
     * @return 最少天数
     */
    public int minDays(int[] bloomDay, int m, int k) {
        //二分查找
        //锁定天数范围，在范围内二分，对于一个给定天数，判断是否可以制作m束花
        int flowerNum = m * k;
        if(flowerNum > bloomDay.length) return -1;
        if(flowerNum == bloomDay.length){
            return Arrays.stream(bloomDay).max().getAsInt();
        }
        //天数范围为数组最小值(一朵开花)~数组最大值(全部开花)
        int left = bloomDay[0], right = bloomDay[0];
        for(int day : bloomDay){
            left = Math.min(left,day);
            right = Math.max(right,day);
        }
        while(left < right){
            int mid = left+(right-left)/2;
            if(canMakeFlower(bloomDay,m,k,mid)){
                //mid天可以制作m束花，则可能存在更小的天数解
                right = mid;
            }else{
                //mid天不可制作m束花，则需要更多的天数
                left = mid + 1;
            }
        }
        return left;
    }
    //判断在给定天数下能否制作m束花
    private boolean canMakeFlower(int[] bloomDay, int m, int k, int day){
        int num = 0;
        for (int i : bloomDay) {
            if (i <= day) {
                num++;
                if (num == k) {
                    m--;
                    if (m == 0) return true;
                    num = 0;
                }
            } else {
                num = 0;
            }
        }
        return false;
    }

    /**
     * 1734. 解码异或后的排列
     * 给你一个整数数组 perm ，它是前 n 个 正整数 的排列，且 n 是个 奇数 。
     * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
     *
     * @param encoded 密文
     * @return 原文
     */
    public int[] decode(int[] encoded) {
        //基于条件找规律，求出perm[0]
        //1. perm是[1,n]的排列，基于此可以得到所有元素的异或结果
        int[] perm = new int[encoded.length+1];
        int total = 0;
        for(int i=1;i<=perm.length;i++){
            total ^= i;
        }
        //2. n为奇数，且 encoded[i] = perm[i] ^ perm[i + 1]
        //  encoded[0] = perm[0] ^ perm[1]
        //  encoded[1] = perm[1] ^ perm[2]
        //  encoded[2] = perm[2] ^ perm[3]
        //  encoded[3] = perm[3] ^ perm[4]
        //有上述条件可知，基于encoded[2k+1]可以得到除了perm[0]外所有元素的异或结果
        int odd = 0;
        for(int i=1;i<encoded.length;i+=2){
            odd ^= encoded[i];
        }
        //得到perm[0]
        perm[0] = total ^ odd;
        //递推得到答案
        for(int i=1;i<perm.length;i++){
            perm[i] = perm[i-1] ^ encoded[i-1];
        }
        return perm;
    }

    /**
     * 1310. 子数组异或查询
     * 有一个正整数数组arr，现给你一个对应的查询数组queries，其中queries[i] = [Li,Ri]。
     * 对于每个查询i，请你计算从Li到Ri的XOR值（即arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     * 并返回一个包含给定查询queries所有结果的数组。
     *
     * @param arr 数组
     * @param queries 子数组异或
     * @return 结果
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        //前缀异或结果
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            prefix[i] = prefix[i-1] ^ arr[i];
        }
        //子数组异或查询
        int[] result = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            result[i] = prefix[queries[i][1]]
                    ^ (queries[i][0] > 0 ? prefix[queries[i][0]-1] : 0);
        }
        return result;
    }

    /**
     * 79. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * @param board 二维网格 [1,6][1,6]
     * @param word 单词 [1,15]
     * @return 是否存在
     */
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        if(row*col < word.length()) return false;
        char[] words = word.toCharArray();
        //存放已匹配的坐标
        Set<Long> set = new HashSet<>();
        //枚举起点
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(exist(board,i,j,set,words,0)){
                    return true;
                }
            }
        }
        return false;
    }
    //递归回溯 以(i,j)为起点搜索words[idx,words.length-1]
    private boolean exist(char[][] board, int i, int j, Set<Long> set, char[] words, int idx){
        //完成words匹配
        if(idx==words.length) return true;
        //用一个long来保存(i,j)坐标
        Long locate = (long)i << 32 | (long)j & 0xFFFFFFFFL;
        //坐标已被占用
        if(set.contains(locate)) return false;
        //当前字符不匹配
        if(board[i][j]!=words[idx]) return false;
        //当前字符匹配
        if(idx==words.length-1) return true;
        //保存坐标
        set.add(locate);
        //向上下左右四个方向递归
        if(i>0 && exist(board,i-1,j,set,words,idx+1)) return true;
        if(i<board.length-1 && exist(board,i+1,j,set,words,idx+1)) return true;
        if(j>0 && exist(board,i,j-1,set,words,idx+1)) return true;
        if(j<board[0].length-1 && exist(board,i,j+1,set,words,idx+1)) return true;
        //移除坐标
        set.remove(locate);
        return false;
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0,0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     * 请问该机器人能够到达多少个格子？
     *
     * @param m 行
     * @param n 列
     * @param k 限制
     * @return 范围大小(格子数)
     */
    public int movingCount(int m, int n, int k) {
        //计算数位和
        int len = Math.max(m,n);
        int[] arr = new int[len];
        for(int i=0;i<len;i++){
            int temp = i;
            while(temp>=10){
                arr[i] += temp%10;
                temp /= 10;
            }
            arr[i] += temp;
        }
        //递归求解
        Set<Long> set = new HashSet<>();
        return movingCount(m,n,k,arr,0,0,set);
    }
    //递归函数
    private int movingCount(int m, int n, int k, int[] arr, int i, int j, Set<Long> set){
        //超出范围
        if(i<0 || i>=m || j<0 || j>=n) return 0;
        //不可进入的点
        if(arr[i]+arr[j]>k) return 0;
        //用long保存坐标(i,j)
        Long locate = (long)i << 32 | (long)j & 0xFFFFFFFFL;
        //已经走过的点
        if(set.contains(locate)) return 0;
        int count = 1;
        set.add(locate);
        //当前点可以进入，则向上下左右走
        count += movingCount(m,n,k,arr,i-1,j,set);
        count += movingCount(m,n,k,arr,i+1,j,set);
        count += movingCount(m,n,k,arr,i,j-1,set);
        count += movingCount(m,n,k,arr,i,j+1,set);
        return count;
    }

    /**
     * 1269. 停在原地的方案数
     * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     * 由于答案可能会很大，请返回方案数 模 1000000007 后的结果。
     *
     * @param steps 步骤数
     * @param arrLen 数组长度
     * @return 方案数
     */
    public int numWays(int steps, int arrLen) {
        //动态规划
        //含义：dp[i][j]表示在 i+1 步操作之后，指针位于数组下标 j 的方案数
        //由于steps最远只能到arr[steps]，所以当数组长度很长时，只取steps+1长度的数组
        int len = Math.min(arrLen, steps + 1);
        int[][] dp = new int[steps][len];
        //枚举最后一步，得到递推方程：dp[i][j] = dp[i-1][j]+dp[i-1][j-1]+dp[i-1][j+1]
        for(int j=0;j<len && j<2;j++){
            dp[0][j] = 1;
        }
        for(int i=1;i<steps;i++){
            for(int j=0;j<len;j++){
                //j>i+1时，j一定到不了，可以提前剪枝
                if(j>i+1) break;
                dp[i][j] = dp[i-1][j];
                if(j>0) dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % 1000000007;
                if(j<len-1) dp[i][j] = (dp[i][j] + dp[i-1][j+1]) % 1000000007;
            }
        }
        return dp[steps-1][0];
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     *
     * @param n 骰子个数
     * @return 点数概率
     */
    public double[] dicesProbability(int n) {
        //确定值范围
        int min = n;
        int max = n*6;
        double[] answer = new double[max-min+1];
        //确定所有可能出现的情况总数
        int sum = 6;
        int count = n;
        while(count>1){
            sum *= 6;
            count--;
        }
        //确定每个值对应的情况数
        //dp[i][j]表示用i+1个骰子得到j的情况数
        int[][] dp = new int[n][max+1];
        //1个骰子，值为1~6的情况各有一种
        for(int j=1;j<=6;j++){
            dp[0][j] = 1;
        }
        //大于1个骰子：枚举最后一个骰子的值，剩余值由剩余骰子组成
        for(int i=1;i<n;i++){
            //i+1个骰子的值范围
            int minJ = i+1, maxJ = minJ*6;
            for(int j=i+1;j<=maxJ;j++){
                //最后一个骰子的值可以是1~6
                for(int k=1; k<=6 && j>k; k++){
                    dp[i][j] += dp[i-1][j-k];
                }
            }
        }
        //计算每种值对应的概率
        for(int k=0;k<answer.length;k++){
            answer[k] = dp[n-1][k+min]*1.0 / sum;
        }
        return answer;
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角。机器人每次只能向下或者向右移动一步。
     * 机器人试图达到网格的右下角。问总共有多少条不同的路径？
     *
     * @param m 行
     * @param n 列
     * @return 路径数
     */
    public int uniquePaths(int m, int n) {
        //动态规划
        //dp[i][j]表示从起点到达(i,j)的路径数
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0] = 1;
        }
        for(int j=0;j<n;j++){
            dp[0][j] = 1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                //由机器人只能向下或向右得到递推方程
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 421. 数组中两个数的最大异或值
     * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     * 1 <= nums.length <= 2 * 10^4
     * 0 <= nums[i] <= 2^31 - 1
     *
     * @param nums 数组
     * @return 最大异或值
     */
    public int findMaximumXOR(int[] nums) {

        /*//1、时间复杂度O(N^2)
        //因为输入数组是正数，所以异或结果最小为0
        int max = 0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                max = Math.max(max,nums[i]^nums[j]);
            }
        }
        return max;*/

        //2、时间复杂度O(31N)=O(N)
        //异或性质：a ^ b = c 则 b = a ^ c
        //从最高位到最低位逐位确定x的值，尽量让高位为1，如果不能达到才置位0
        int x = 0;
        loop : for(int k=30;k>=0;k--){
            //使用哈希表保存所有数从最高位开始到第 k 个二进制位为止的部分
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num >> k);
            }
            //假设x的下一位为1
            int xNext = 2*x+1;
            for(int num : nums){
                //判断x的下一位是否可以为1
                if(set.contains(xNext ^ (num >> k))){
                    x = xNext;
                    continue loop;
                }
            }
            //x的下一位不能为1
            x = xNext - 1;
        }
        return x;

    }

    /**
     * 877. 石子游戏
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子piles[i]。
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     * 亚历克斯和李轮流进行，亚历克斯先开始。每回合，玩家从行的开始或结束处取走整堆石头。
     * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回true，当李赢得比赛时返回false。
     *
     * @param piles 石子堆数组 (2 <= piles.length <= 500 且 piles.length 是偶数)
     * @return 先手能否获胜
     */
    public boolean stoneGame(int[] piles) {

        /*//1、暴力递归：时间复杂度O(2^N)
        return stoneGame(piles,0,0,0,piles.length-1,0);*/

        //2、动态规划：时间复杂度O(N^2)
        //dp[i][j]表示在piles[i...j]中先手多拿的石子数(相应的，-dp[i][j]表示后手多拿的石子数)
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = piles[i];
        }
        //枚举区间长度
        for(int len=1;len<n;len++){
            //枚举起点
            for(int i=0;i<n-len;i++){
                //计算终点
                int j = i+len;
                //递推方程：先手拿左边石子或拿右边石子，子问题中当前先手成为后手
                dp[i][j] = Math.max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
            }
        }
        return dp[0][n-1] > 0;

    }
    /**
     * 暴力递归：枚举所有情况
     *
     * @param piles 石子堆数组
     * @param sum0 先手分数
     * @param sum1 后手分数
     * @param left 左指针
     * @param right 右指针
     * @param player 当前玩家：0为Alex，1为Lee
     * @return 当前玩家能否取胜
     */
    private boolean stoneGame(int[] piles,int sum0,int sum1,int left,int right,int player){
        if(left > right){
            if(player==0) return sum0 > sum1;
            else return sum1 > sum0;
        }
        if(player == 0){
            //玩家0有两种选择，选择其中一种可以使得玩家1必输，则玩家0必赢
            return ! stoneGame(piles,sum0+piles[left],sum1,left+1,right,1) ||
                    ! stoneGame(piles,sum0+piles[right],sum1,left,right-1,1);
        }else{
            //玩家1有两种选择，选择其中一种可以使得玩家0必输，则玩家1必赢
            return ! stoneGame(piles,sum0,sum1+piles[left],left+1,right,0) ||
                    ! stoneGame(piles,sum0,sum1+piles[right],left,right-1,0);
        }
    }

    /**
     * 1442. 形成两个异或相等数组的三元组数目
     * 从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     * a 和 b 定义如下：
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     *
     * @param arr 数组 1 <= arr.length <= 300
     * @return 三元组数目
     */
    public int countTriplets(int[] arr) {

        int count = 0;
        //前缀异或
        int[] preXor = new int[arr.length];
        preXor[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            preXor[i] = preXor[i-1] ^ arr[i];
        }

        /*//1、枚举所有三元组 时间复杂度O(N^3)
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j;k<arr.length;k++){
                    int a = (i==0) ? preXor[j-1] : preXor[i-1] ^ preXor[j-1];
                    int b = preXor[j-1] ^ preXor[k];
                    if(a==b) count++;
                }
            }
        }
        return count;*/

        //2、枚举所有的二元组 时间复杂度O(N^2)
        //异或性质：若 a ^ b = b ^ c 则 a = c
        //判断 preXor[i-1]^preXor[j-1]==preXor[j-1]^preXor[k]
        //只需 preXor[i-1]==preXor[k] j可以取[i+1,k]的任意值
        for(int i=0;i<arr.length-1;i++){
            for(int k=i+1;k<arr.length;k++){
                if((i==0 && preXor[k]==0) || (i>0 && preXor[i-1]==preXor[k])){
                    count += (k-i);
                }
            }
        }
        return count;

    }

    /**
     * 1738. 找出第 K 大的异或坐标值
     * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
     * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
     * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
     *
     * @param matrix 矩阵 1 <= m, n <= 1000
     * @param k 正整数
     * @return 第K大的异或坐标值
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        //前缀异或值
        int[][] preXor = new int[m][n];
        preXor[0][0] = matrix[0][0];
        for(int j=1;j<n;j++){
            preXor[0][j] = preXor[0][j-1] ^ matrix[0][j];
        }
        for(int i=1;i<m;i++){
            preXor[i][0] = preXor[i-1][0] ^ matrix[i][0];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                preXor[i][j] = preXor[i-1][j] ^ preXor[i][j-1] ^ preXor[i-1][j-1] ^ matrix[i][j];
            }
        }
        //找前k个大值，存入小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(queue.size()<k) queue.offer(preXor[i][j]);
                else if(preXor[i][j]>queue.peek()){
                    queue.poll();
                    queue.offer(preXor[i][j]);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 692. 前K个高频单词
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * @param words 单词数组
     * @param k 整数
     * @return 前K个高频单词
     */
    public List<String> topKFrequent(String[] words, int k) {
        //收集词频 时间O(N)、空间O(N)
        Map<String,Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        //小根堆排序 时间O(NlogK)、空间O(K)
        PriorityQueue<Map.Entry<String,Integer>> queue = new PriorityQueue<>((o1, o2) -> {
            if(o1.getValue().equals(o2.getValue())){
                return o2.getKey().compareTo(o1.getKey());
            }
            return o1.getValue() - o2.getValue();
        });
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(queue.size()<k){
                queue.offer(entry);
            }else{
                Map.Entry<String,Integer> min = queue.peek();
                if(entry.getValue().equals(min.getValue())){
                    if(entry.getKey().compareTo(min.getKey()) < 0){
                        queue.poll();
                        queue.offer(entry);
                    }
                }else{
                    if(entry.getValue()>min.getValue()){
                        queue.poll();
                        queue.offer(entry);
                    }
                }
            }
        }
        //构建结果
        List<String> ans = new ArrayList<>();
        while( ! queue.isEmpty()){
            ans.add(queue.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }

    /**
     * 1035. 不相交的线
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
     * 1、nums1[i] == nums2[j]
     * 2、绘制的直线不与任何其他连线（非水平线）相交。
     * 3、每个数字只能属于一条连线。
     * 以这种方法绘制线条，返回可以绘制的最大连线数。
     *
     * @param nums1 直线1(数字数组) 1 <= nums1.length <= 500
     * @param nums2 直线2(数字数组) 1 <= nums2.length <= 500
     * @return 最大连线数
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        //动态规划
        //dp[i][j]表示nums1[0...i]与nums2[0...j]的最大连线数
        int[][] dp = new int[nums1.length][nums2.length];
        dp[0][0] = (nums1[0]==nums2[0] ? 1 : 0);
        for(int i=1;i<nums1.length;i++){
            dp[i][0] = (dp[i-1][0]==1 || nums1[i]==nums2[0] ? 1 : 0);
        }
        for(int j=1;j<nums2.length;j++){
            dp[0][j] = (dp[0][j-1]==1 || nums1[0]==nums2[j] ? 1 : 0);
        }
        for(int i=1;i<nums1.length;i++){
            for(int j=1;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    //尾部相等，最后一对组成一个连线
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    //尾部不相等，最后一对不能组成连线
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[nums1.length-1][nums2.length-1];
    }

    /**
     * 475. 供暖器
     * 设计一个有固定加热半径的供暖器向所有房屋供暖，使得在加热器的加热半径范围内的每个房屋都可以获得供暖。
     * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
     * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
     *
     * @param houses 房子位置
     * @param heaters 加热器位置
     * @return 最小半径
     */
    public int findRadius(int[] houses, int[] heaters) {
        //排序
        Arrays.sort(houses);
        Arrays.sort(heaters);
        //房子在左，供暖器在右，求每个房子到供暖器的最短距离
        int[] leftDis = new int[houses.length];
        int j = 0;
        for(int i=0;i<houses.length;i++){
            if(j==heaters.length){
                leftDis[i] = Integer.MAX_VALUE;
            }else if(houses[i]<=heaters[j]){
                leftDis[i] = heaters[j]-houses[i];
            }else{
                j++;
                i--;
            }
        }
        //房子在右，供暖器在左，求每个房子到供暖器的最短距离
        int[] rightDis = new int[houses.length];
        j = heaters.length-1;
        for(int i=houses.length-1;i>=0;i--){
            if(j==-1){
                rightDis[i] = Integer.MAX_VALUE;
            }else if(houses[i]>=heaters[j]){
                rightDis[i] = houses[i]-heaters[j];
            }else{
                j--;
                i++;
            }
        }
        //对于每个房子，求到左右供暖器的最小距离，所有最小距离中的最大值即为加热半径
        int radix = 0;
        for(int i=0;i<houses.length;i++){
            radix = Math.max(radix,Math.min(leftDis[i],rightDis[i]));
        }
        return radix;
    }

    /**
     * 1262. 可被三整除的最大和
     * 给你一个整数数组 nums，请你找出并返回能被三整除的最大和。
     *
     * @param nums 数组  1 <= nums.length <= 4 * 10^4
     * @return 能被三整除的最大和
     */
    public int maxSumDivThree(int[] nums) {

        /*//1、递归回溯：时间复杂度O(2^N)
        maxSumDivThree(nums,0,0);
        return maxSumDivThree;*/

        //上述做法复杂度太高，执行超时

        //2、动态规划：时间复杂度O(3N)
        //dp[i][j]表示数组nums[0...i]模3得j的最大和
        int[][] dp = new int[nums.length][3];
        for(int j=0;j<3;j++){
            dp[0][j] = nums[0]%3 == j ? nums[0] : 0;
        }
        for(int i=1;i<nums.length;i++){
            int mod = nums[i]%3;
            for(int j=0;j<3;j++){
                dp[i][j] = Math.max(mod == j ? nums[i] : 0, dp[i-1][j]);
                int offset = (j+3-mod)%3;
                if(dp[i-1][offset]!=0){
                    dp[i][j] = Math.max(
                            dp[i][j], dp[i-1][offset] + nums[i]
                    );
                }
            }
        }
        return dp[nums.length-1][0];

    }
    private int maxSumDivThree = 0;
    //递归函数：枚举所有组合
    private void maxSumDivThree(int[] nums,int index,int sum){
        if(sum%3==0){
            maxSumDivThree = Math.max(maxSumDivThree,sum);
        }
        for(int i=index;i<nums.length;i++){
            sum += nums[i];
            maxSumDivThree(nums,i+1,sum);
            sum -= nums[i];
        }
    }

    /**
     * 1190. 反转每对括号间的子串
     * 给出一个字符串 s（仅含有小写英文字母和括号）。
     * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
     * 注意，您的结果中 不应 包含任何括号。
     *
     * @param s 字符串
     * @return 结果
     */
    public String reverseParentheses(String s) {
        //借助栈完成括号内的反转操作
        Deque<Character> deque = new LinkedList<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c==')'){
                //找到一个右括号，则把对应区间内字符取出
                List<Character> list = new ArrayList<>();
                while(deque.peek()!='('){
                    list.add(deque.pop());
                }
                //删除对应的左括号
                deque.poll();
                //区间字符反转后重新入栈
                for(Character one : list){
                    deque.push(one);
                }
            }else{
                //除了右括号的字符直接入栈
                deque.push(c);
            }
        }
        //从栈中取出最终结果
        StringBuilder sb = new StringBuilder();
        while( ! deque.isEmpty()){
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }

    /**
     * 289. 生命游戏
     * 生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 1、如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 2、如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 3、如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 4、如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
     *
     * @param board 二维网格面板
     */
    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        //下一个状态
        int[][] next = new int[row][col];
        int[] around = {-1,0,1};
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //基于周围8个点统计活细胞数量
                int live = 0;
                for (int a : around) {
                    if ((i == 0 && a == -1) || (i == row - 1 && a == 1)) continue;
                    for (int b : around) {
                        if ((j == 0 && b == -1) || (j == col - 1 && b == 1)) continue;
                        if (a == 0 && b == 0) continue;
                        if (board[i + a][j + b] == 1) {
                            live++;
                        }
                    }
                }
                //推出当前点的下一个状态
                if(board[i][j]==0){
                    if(live==3) next[i][j]=1;
                }else{
                    if(live==2 || live==3) next[i][j]=1;
                }
            }
        }
        //将下一个状态写回原数组
        for(int i=0;i<row;i++){
            System.arraycopy(next[i], 0, board[i], 0, col);
        }
    }

    /**
     * 6. Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *          P   A   H   N
     *          A P L S I I G
     *          Y   I   R
     * 之后，输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * @param s 字符串
     * @param numRows 行数
     * @return 新字符串
     */
    public String convert(String s, int numRows) {

        /*//解法1：时间复杂度O(NlogN)
        //不需要处理的情况
        if(numRows==1 || s.length()<=numRows) return s;
        //记录每个字符的坐标位置
        int[][] pos = new int[s.length()][3];
        int i=0, j=0, k=0;
        boolean down = true;
        while(k<s.length()){
            if(down){
                if(i==numRows-1) down = false;
                else{
                    //下降
                    pos[k] = new int[]{s.charAt(k), i++, j};
                    k++;
                }
            }else{
                if(i==0) down = true;
                else{
                    //上升
                    pos[k] = new int[]{s.charAt(k), i--, j++};
                    k++;
                }
            }
        }
        //先按行后按列排序
        Arrays.sort(pos,(o1,o2)->{
            if(o1[1]==o2[1]) return o1[2]-o2[2];
            return o1[1]-o2[1];
        });
        //构建结果字符串
        StringBuilder result = new StringBuilder();
        for (int[] po : pos) {
            result.append((char) po[0]);
        }
        return result.toString();*/

        //解法2：时间复杂度O(N)
        if(numRows==1) return s;
        StringBuilder sb = new StringBuilder();
        //Z字形变换是一个周期性结构，且行数增一周期增二，对于每一行，一个周期会贡献1~2个元素
        int cycle = 2*numRows-2;
        //遍历行
        for(int i=0;i<numRows;i++){
            //收集属于该行的元素
            for(int j=i;j<s.length();j+=cycle){
                sb.append(s.charAt(j));
                if(i>0 && i<numRows-1){
                    int k = j-i+cycle-i;
                    if(k<s.length()) sb.append(s.charAt(k));
                }
            }
        }
        return sb.toString();

    }

    /**
     * 139. 单词拆分
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
     * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明：
     * 1、拆分时可以重复使用字典中的单词。
     * 2、字典中没有重复的单词。
     *
     * @param s 字符串
     * @param wordDict 字典
     * @return 字符串能否由字典的单词组成
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        /*//1、暴力递归
        //将列表转为哈希表
        Set<String> wordSet = new HashSet<>(wordDict);
        return wordBreak1(s, wordSet);*/

        /*//2、记忆搜索
        //将列表转为哈希表
        Set<String> wordSet = new HashSet<>(wordDict);
        //创建记忆表
        Map<Integer,Boolean> map = new HashMap<>();
        return wordBreak2(s, 0, map, wordSet);*/

        //3、动态规划
        //将列表转为哈希表
        Set<String> wordSet = new HashSet<>(wordDict);
        //dp[i]表示s[i...len-1]能否拆分
        boolean[] dp = new boolean[s.length()+1];
        dp[s.length()] = true;
        //i为子串起点
        for(int i=s.length()-1;i>=0;i--){
            StringBuilder sb = new StringBuilder();
            //j为子串终点，j+1为剩余串
            for(int j=i;j<s.length();j++){
                sb.append(s.charAt(j));
                //若s[i...j]能在字典找到，判断s[j+1...len-1]能否拆分
                if(wordSet.contains(sb.toString())){
                    if(dp[j+1]){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];

    }
    //递归函数1：暴力递归
    private boolean wordBreak1(String s, Set<String> wordSet){
        if(s.equals("")) return true;
        //枚举字符串从0开始的子串，若能匹配单词，则将剩余部分递归
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i));
            if(wordSet.contains(sb.toString())){
                if(wordBreak1(s.substring(sb.length()),wordSet)) return true;
            }
        }
        return false;
    }
    //递归函数2：使用记忆表避免重复计算
    private boolean wordBreak2(String s, int begin, Map<Integer,Boolean> map, Set<String> wordSet){
        if(begin==s.length()) return true;
        //枚举字符串从0开始的子串，若能匹配单词，则将剩余部分递归
        StringBuilder sb = new StringBuilder();
        for(int i=begin;i<s.length();i++){
            //收集字符
            sb.append(s.charAt(i));
            //匹配到单词
            if(wordSet.contains(sb.toString())){
                //剩余字符串的起点
                int nextBegin = begin + sb.length();
                //查记忆表
                Boolean flag = map.get(nextBegin);
                //查不到则递归求解
                if(flag==null){
                    flag = wordBreak2(s, nextBegin, map, wordSet);
                    map.put(nextBegin,flag);
                }
                if(flag) return true;
            }
        }
        return false;
    }

    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * @param nums 数组，满足以下条件
     *               1 <= nums.length <= 20
     *               0 <= nums[i] <= 1000
     *               0 <= sum(nums[i]) <= 1000
     * @param target 目标值，满足以下条件
     *               -1000 <= target <= 100
     * @return 符合条件的表达式数目
     */
    public int findTargetSumWays(int[] nums, int target) {

        /*//1、暴力递归：时间复杂度O(2^N)
        findTargetSumWays(nums,0,0,target);
        return targetSumWays;*/

        //2、动态规划：时间复杂度O(N*Range)
        //dp[i][j]表示用nums[0...i]构造值为j的表达式数目
        //j的取值范围为[-1000,1000]，用[0,2000]表示
        int[][] dp = new int[nums.length][2001];
        dp[0][1000-nums[0]] += 1;
        dp[0][1000+nums[0]] += 1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<=2000;j++){
                if(dp[i-1][j]>0){
                    //加
                    dp[i][j+nums[i]] += dp[i-1][j];
                    //减
                    dp[i][j-nums[i]] += dp[i-1][j];
                }
            }
        }
        return dp[nums.length-1][1000+target];

    }
    //全局变量
    private int targetSumWays = 0;
    //递归函数
    private void findTargetSumWays(int[] nums, int index, int sum, int target){
        if(index==nums.length){
            if(sum==target) targetSumWays++;
            return;
        }
        //加
        findTargetSumWays(nums,index+1,sum+nums[index],target);
        //减
        findTargetSumWays(nums,index+1,sum-nums[index],target);
    }

    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 表示和的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int carry = 0;
        while(l1!=null || l2!=null){
            //按位求和
            int sum = carry;
            if(l1!=null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                sum += l2.val;
                l2 = l2.next;
            }
            //求进位
            carry = sum >= 10 ? 1 : 0;
            //保留低位
            sum %= 10;
            //构建节点
            temp.next = new ListNode(sum);
            temp = temp.next;
        }
        //可能遗漏的进位
        if(carry==1){
            temp.next = new ListNode(1);
        }
        return head.next;
    }

    /**
     * 477. 汉明距离总和
     * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
     * 计算一个数组中，任意两个数之间汉明距离的总和。
     *
     * @param nums 数组  数组的长度不超过10^4
     * @return 汉明距离总和
     */
    public int totalHammingDistance(int[] nums) {

        /*//1、暴力枚举：时间复杂度O(N^2)，最大为10^8，可能会超时
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                sum += Integer.bitCount(nums[i]^nums[j]);
            }
        }
        return sum;*/

        //2、数位01计数：时间复杂度O(32N)，最大为32*10^4
        //固定一个位，统计该位1和0出现的数量，二者的乘积即为该位贡献的汉明距离
        int sum = 0;
        for(int i=0;i<=31;i++){
            int count0 = 0;
            int count1 = 0;
            for(int num : nums){
                if(((num>>i)&1)==0){
                    count0++;
                }else{
                    count1++;
                }
            }
            sum += count0 * count1;
        }
        return sum;

    }

    /**
     * 621. 任务调度器
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的 最短时间 。
     *
     * @param tasks 任务列表
     *              1 <= task.length <= 10^4
     *              tasks[i]是大写英文字母
     * @param n 相同任务的间隔时间
     *              0 <= n <= 100
     * @return 最短时间
     */
    public int leastInterval(char[] tasks, int n) {
        if(n==0) return tasks.length;
        //词频统计
        int[] freqs = new int[26];
        for(char task : tasks){
            freqs[task-'A']++;
        }
        //最高频率
        int maxFreq = 0;
        //最高频率的出现次数
        int count = 0;
        for(int freq : freqs){
            if(freq>maxFreq){
                maxFreq = freq;
                count = 1;
            }else if(freq==maxFreq){
                count += 1;
            }
        }
        //出现次数最多的字符按n间隔执行，需要的最短时间
        int maxLen = (maxFreq-1) * (n+1) + count;
        //若数组长度大于最短时间，则最短时间为数组长度
        return Math.max(tasks.length, maxLen);
    }

    /**
     * 279. 完全平方数
     * 给定正整数 n ，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * 完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * @param n 正整数  1 <= n <= 10^4
     * @return 组成n的完全平方数最少数量
     */
    public int numSquares(int n) {
        //动态规划
        //所有小于等于n的完全平方数
        List<Integer> squares = new ArrayList<>();
        for(int i=1;i*i<=n;i++){
            squares.add(i*i);
        }
        //dp[i][j]表示用squares[0...i]的完全平方数组成j的最少个数
        int[][] dp = new int[squares.size()][n+1];
        //j==0时最少个数为0
        //i==0时最少个数为j
        for(int j=1;j<=n;j++){
            dp[0][j] = j;
        }
        for(int i=1;i<squares.size();i++){
            for(int j=1;j<=n;j++){
                //不使用square[i]
                dp[i][j] = dp[i-1][j];
                //使用1个或以上square[i]
                int square = squares.get(i);
                for(int k=1; k*square<=j ; k++){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k*square]+k);
                }
            }
        }
        return dp[squares.size()-1][n];
    }

    /**
     * 739. 每日温度
     * 请根据每日气温列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * @param temperatures 气温列表
     * @return 结果列表
     */
    public int[] dailyTemperatures(int[] temperatures) {
        //单调栈(保存索引)
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[temperatures.length];
        for(int i=0; i<temperatures.length; i++){
            while( ! stack.isEmpty() && temperatures[stack.peek()]<temperatures[i]){
                int j = stack.pop();
                ans[j] = i-j;
            }
            stack.push(i);
        }
        while( ! stack.isEmpty()){
            ans[stack.pop()] = 0;
        }
        return ans;
    }

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * @param nums 数组
     * @return 重复数字
     */
    public int findDuplicate(int[] nums) {
        //一次遍历：时间复杂度O(N)
        //把nums[i]放在nums[i]-1的位置，出现值重复时可以得到重复数
        for(int i=0;i<nums.length;i++){
            int next = nums[i]-1;
            if(next==i) continue;
            if(nums[next]==nums[i]) return nums[i];
            else{
                nums[next] ^= nums[i];
                nums[i] ^= nums[next];
                nums[next] ^= nums[i];
                i--;
            }
        }
        return -1;
    }

    /**
     * 560. 和为K的子数组
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * @param nums 数组(数组长度为[1, 20,000])
     * @param k 整数
     * @return 和为k的子数组个数
     */
    public int subarraySum(int[] nums, int k) {

        /*//1、前缀和+枚举子数组：时间复杂度O(N^2)
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            prefixSum[i] = prefixSum[i-1]+nums[i];
        }
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                int sum;
                if(i==0) sum = prefixSum[j];
                else sum = prefixSum[j]-prefixSum[i-1];
                if(sum==k) count++;
            }
        }
        return count;*/

        /*//2、枚举子数组：时间复杂度O(N^2)
        int count = 0;
        for(int i=0;i<nums.length;i++){
            int sum = 0;
            for(int j=i;j<nums.length;j++){
                sum += nums[j];
                if(sum==k) count++;
            }
        }
        return count;*/

        //3、前缀和+哈希表：时间复杂度O(N)
        int count = 0;
        //哈希表保存"前缀和->出现次数"
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        //前缀和pre[i]表示nums[0...i]的和
        int[] pre = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(i==0) pre[i] = nums[i];
            else pre[i] = pre[i-1]+nums[i];
            //哈希表中已经收集了pre[0...i-1]，和为pre[i]-k的个数，即为以i结尾的子数组符合条件的个数
            count += map.getOrDefault(pre[i]-k,0);
            //nums[0...i]可能是符合条件的子数组
            //if(pre[i]==k) count += 1;
            //pre[i]存入哈希表
            map.put(pre[i],map.getOrDefault(pre[i],0)+1);
        }
        return count;

    }

    /**
     * 5774. 使用服务器处理任务
     * 给你两个 下标从 0 开始 的整数数组 servers 和 tasks ，长度分别为 n 和 m 。servers[i] 是第 i 台服务器的 权重 ，而 tasks[j] 是处理第 j 项任务 所需要的时间（单位：秒）。
     * 你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 0 项任务在第 0 秒可以开始处理，相应地，第 j 项任务在第 j 秒可以开始处理。
     * 处理第 j 项任务时，你需要为它分配一台 权重最小 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 下标最小 的服务器。如果一台空闲服务器在第 t 秒分配到第 j 项任务，那么在 t + tasks[j] 时它将恢复空闲状态。
     * 如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 尽可能早地处理剩余任务。 如果有多项任务等待分配，则按照 下标递增 的顺序完成分配。
     * 如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
     * 构建长度为 m 的答案数组 ans ，其中 ans[j] 是第 j 项任务分配的服务器的下标。
     *
     * @param servers  服务器列表
     * @param tasks 任务列表
     * @return 分配结果
     */
    public int[] assignTasks(int[] servers, int[] tasks) {
        //使用两个小根堆，分别维护空闲服务器及工作中服务器
        PriorityQueue<int[]> idle = new PriorityQueue<>((o1, o2) -> {
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            return o1[1]-o2[1];
        });
        PriorityQueue<int[]> busy = new PriorityQueue<>(((o1, o2) -> {
            if(o1[2]==o2[2]){
                if(o1[1]==o2[1]) return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
            return o1[2]-o2[2];
        }));
        //复制服务器
        int[][] copyServers = new int[servers.length][3];
        for(int i=0;i<servers.length;i++){
            //索引
            copyServers[i][0] = i;
            //权重
            copyServers[i][1] = servers[i];
            //可用时间
            copyServers[i][2] = 0;
            //空闲服务器初始化
            idle.offer(copyServers[i]);
        }
        //当前时间
        int curTime = 0;
        //分配任务
        int[] ans = new int[tasks.length];
        for(int i=0;i<tasks.length;i++){
            curTime = Math.max(curTime,i);
            //服务器恢复空闲状态
            while( ! busy.isEmpty() && busy.peek()[2]<=curTime){
                idle.offer(busy.poll());
            }
            //若不存在空闲服务器
            if(idle.isEmpty()){
                curTime = busy.peek()[2];
                while( ! busy.isEmpty() && busy.peek()[2]<=curTime){
                    idle.offer(busy.poll());
                }
            }
            //分配服务器
            int[] server = idle.poll();
            ans[i] = server[0];
            server[2] = curTime+tasks[i];
            busy.offer(server);
        }
        return ans;
    }

    /**
     * 1878. 矩阵中最大的三个菱形和
     * 给你一个 m x n 的整数矩阵 grid 。
     * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。
     * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。如果不同的和少于三个，则将它们全部返回。
     *
     * @param grid 网格 (1 <= grid[i][j] <= 10^5)
     * @return 最大的三个菱形和
     */
    public int[] getBiggestThree(int[][] grid) {
        //前缀和+枚举菱形
        int row = grid.length, col = grid[0].length;
        //sum1[i][j]表示grid[i][j]向左上角直到边界的和
        int[][] sum1 = new int[row][col];
        //sum2[i][j]表示grid[i][j]向右上角直到边界的和
        int[][] sum2 = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==0 || j==0){
                    sum1[i][j] = grid[i][j];
                }else{
                    sum1[i][j] = sum1[i-1][j-1] + grid[i][j];
                }
                if(i==0 || j==col-1){
                    sum2[i][j] = grid[i][j];
                }else{
                    sum2[i][j] = sum2[i-1][j+1] + grid[i][j];
                }
            }
        }
        int[] ans = new int[3];
        //枚举菱形所占正方形的左上角坐标以及边长
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int k=1;k+i<=row && k+j<=col;k+=2){
                    //求菱形和
                    int sum;
                    if(k==1) sum = grid[i][j];
                    else{
                        int midI = i+k/2, midJ = j+k/2, maxI = i+k-1, maxJ = j+k-1;
                        sum = sum2[midI][j] - (i==0 ? 0 : sum2[i-1][midJ+1])
                                + sum1[midI][maxJ] - (i==0 ? 0 : sum1[i-1][midJ-1])
                                + sum2[maxI][midJ] - (maxJ==col-1 ? 0 : sum2[midI-1][maxJ+1])
                                + sum1[maxI][midJ] - (j==0 ? 0 : sum1[midI-1][j-1])
                                - grid[midI][j] - grid[midI][maxJ] - grid[maxI][midJ] - grid[i][midJ];
                    }
                    //保存最大的三个和，不重复
                    if(sum==ans[0] || sum==ans[1] || sum==ans[2]) continue;
                    int min = 0;
                    for(int a=1;a<3;a++){
                        if(ans[a]<ans[min]){
                            min = a;
                        }
                    }
                    ans[min] = Math.max(sum,ans[min]);
                }
            }
        }
        Arrays.sort(ans);
        if(ans[1]==0) return new int[]{ans[2]};
        if(ans[0]==0) return new int[]{ans[2],ans[1]};
        return new int[]{ans[2],ans[1],ans[0]};
    }

    /**
     * 423. 从英文中重建数字
     * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
     * 注意:
     * 1、输入只包含小写英文字母。
     * 2、输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
     * 3、输入字符串的长度小于 50,000。
     *
     * @param s 英文字符串
     * @return 数字字符串
     */
    public String originalDigits(String s) {
        //字母出现次数
        int[] map = new int[26];
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)-'a']++;
        }
        //数字及其数量
        int[] num = new int[10];
        num[0] = map[25]; //z的数量确定0的数量
        num[2] = map[22]; //w的数量确定2的数量
        num[4] = map[20]; //u的数量确定4的数量
        num[6] = map[23]; //x的数量确定6的数量
        num[8] = map[6]; //g的数量确定8的数量
        num[1] = map[14] - num[0] - num[2] - num[4]; //o的数量减去0,2,4的数量确定1的数量
        num[3] = map[17] - num[0] - num[4]; //r的数量减去0,4的数量确定3的数量
        num[5] = map[5] - num[4]; //f的数量减去4的数量确定5的数量
        num[7] = map[18] - num[6]; //s的数量减去6的数量确定7的数量
        num[9] = map[8] - num[5] - num[6] - num[8]; //i的数量减去5,6,8的数量确定9的数量
        //构建数字字符串
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num.length;i++){
            if(num[i]>0){
                for(int j=0;j<num[i];j++){
                    sb.append(i);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。
     * 同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
     * 你按照如下规则进行一场游戏：
     * 1、你从第 0 天开始吃糖果。
     * 2、你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
     * 3、在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
     * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：
     * 在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；
     * 否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
     *
     * @param candiesCount 糖果数量
     * @param queries 数组[最喜欢的糖果-最喜欢的天数-每天食量]
     * @return 能否在最喜欢的那天吃到最喜欢的糖果
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        //糖果数量前缀和
        long[] preSum = new long[candiesCount.length];
        preSum[0] = candiesCount[0];
        for(int i=1;i<candiesCount.length;i++) preSum[i] = preSum[i-1]+candiesCount[i];
        //每天最低食量1，最高食量queries[i][2]
        boolean[] ans = new boolean[queries.length];
        for(int i=0;i<queries.length;i++){
            long favoriteDay = queries[i][1]+1;
            //favoriteDay天的最低累计食量
            long minCandy = favoriteDay;
            //favoriteDay天的最高累计食量
            long maxCandy = favoriteDay * queries[i][2];
            //最喜欢糖果前缀和是否在最低及最高累计食量的范围内
            ans[i] = minCandy <= preSum[queries[i][0]]
                    && maxCandy > (queries[i][0]==0 ? 0 : preSum[queries[i][0]-1]);
        }
        return ans;
    }

    /**
     * 523. 连续的子数组和
     * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     * 子数组大小 至少为 2 ，且子数组元素总和为 k 的倍数。如果存在，返回 true ；否则，返回 false 。
     *
     * @param nums 数组
     *        1 <= nums.length <= 10^5
     *        0 <= nums[i] <= 10^9
     *        0 <= sum(nums[i]) <= 2^31 - 1
     * @param k 整数
     *        1 <= k <= 2^31 - 1
     * @return 是否存在
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        /*//1、暴力枚举：时间复杂度O(N^2)，可能超时
        for(int i=0;i<nums.length-1;i++){
            int sum = nums[i];
            for(int j=i+1;j<nums.length;j++){
                sum += nums[j];
                if(sum % k == 0){
                    return true;
                }
            }
        }
        return false;*/

        //2、前缀和+哈希表：时间复杂度O(N)
        //哈希表保存<前缀和对k取模的余数，首次出现的前缀和索引>
        Map<Integer,Integer> map = new HashMap<>();
        //第一个元素特殊处理
        int sum = nums[0];
        map.put(sum % k,0);
        //第二个元素开始遍历
        for(int i=1;i<nums.length;i++){
            sum += nums[i];
            int mod = sum % k;
            if(mod==0) return true;
            //如果存在两个相同的余数，则这两个余数之间的区间和是k的倍数，若区间长度大于1，则是符合题意的一个子数组
            Integer index = map.get(mod);
            if(index==null) map.put(mod,i);
            else if(i-index>1) return true;
        }
        return false;
    }

    /**
     * 525. 连续数组
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     *
     * @param nums 数组
     *             1 <= nums.length <= 10^5
     *             nums[i] 不是 0 就是 1
     * @return 最长连续子数组长度
     */
    public int findMaxLength(int[] nums) {
        //前缀和+哈希表：时间复杂度O(N)
        int max = 0;
        //前缀和表示nums[0...i]中0比1多出的数量
        int pre = 0;
        //哈希表表示<前缀和，数组下标>
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) pre++;
            else pre--;
            //如果有两个前缀0比1多出的数量相同，则之间子数组0和1数量相同
            if(map.get(pre)==null) map.put(pre,i);
            else max = Math.max(max,i-map.get(pre));
        }
        return max;
    }

    /**
     * 16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * @param nums 数组
     * @param target 目标值
     * @return 最接近目标的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        //排序+双指针：时间复杂度O(N^2)
        Arrays.sort(nums);
        int ans = 0;
        int diff = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            //左右指针
            int left = i+1, right=nums.length-1;
            while(left < right){
                //三数和
                int sum = nums[i] + nums[left] + nums[right];
                //等于目标值直接返回
                if(sum==target) return sum;
                //求差值
                int abs = Math.abs(sum-target);
                if(abs<diff){
                    diff = abs;
                    ans = sum;
                }
                //指针移动
                if(sum>target){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return ans;
    }

    /**
     * 907. 子数组的最小值之和
     * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 为 arr 的每个（连续）子数组。
     * 由于答案可能很大，因此返回答案模 10^9 + 7 。
     *
     * @param arr 数组
     *            1 <= arr.length <= 3 * 10^4
     *            1 <= arr[i] <= 3 * 10^4
     * @return 子数组的最小值总和
     */
    public int sumSubarrayMins(int[] arr) {

        /*//1、暴力枚举：时间复杂度O(N^2)
        int sum = 0;
        for(int i=0;i<arr.length;i++){
            int min = Integer.MAX_VALUE;
            for(int j=i;j<arr.length;j++){
                min = Math.min(min,arr[j]);
                sum += min;
                sum %= 1_000_000_007;
            }
        }
        return sum;*/

        //2、单调栈：时间复杂度O(N)
        //栈保存元素的下标，并按照元素的值从栈底到栈顶单调递增
        Deque<Integer> s = new LinkedList<>();
        long res = 0;
        //枚举所有元素arr[j]，计算以arr[j]为最小值的子数组数量
        for (int i = 0; i <= arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] > (i == arr.length ? 0 : arr[i])) {
                //出现递减，说明栈顶为一个凸起，即左右两个相邻元素都比它小
                //所有以arr[j]为最小值的子数组在(k,i)之间，子数组数量为(i-j)*(j-k)
                int j = s.pop();
                int k = s.isEmpty() ? -1 : s.peek();
                res = (res + ((long)arr[j] * (i - j) * (j - k)) % 1000000007) % 1000000007;
            }
            s.push(i);
        }
        return (int)res;

    }

    /**
     * 474. 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *
     * @param strs 字符串数组 (1 <= strs.length <= 600)
     * @param m 0的最大个数
     * @param n 1的最大个数
     * @return 最大子集大小
     */
    public int findMaxForm(String[] strs, int m, int n) {

        //1、暴力枚举：每个字符串有取或不取两种选择，时间复杂度为O(2^k)，执行会超时

        //2、动态规划：类似于01背包问题的解题思路，时间复杂度为O(k * m * n)
        /*//dp[k][i][j]表示strs[0...k]的最多有i个0和j个1的最大子集大小
        int[][][] dp = new int[strs.length][m+1][n+1];
        for(int k=0;k<strs.length;k++){
            int num0 = countChar(strs[k],'0');
            int num1 = strs[k].length() - num0;
            for(int i=0;i<=m;i++){
                for(int j=0;j<=n;j++){
                    if(k==0) dp[k][i][j] = (i>=num0 && j>=num1 ? 1 : 0);
                    else dp[k][i][j] = Math.max(dp[k-1][i][j],
                            i>=num0 && j>=num1 ? dp[k-1][i-num0][j-num1]+1 : 0
                    );
                }
            }
        }
        return dp[strs.length-1][m][n];*/

        //3、动态规划：空间压缩(由于dp[k]只取决于dp[k-1]，更早的历史不会再用到，所以不需要保留，可以滚动覆盖更新)
        int[][] dp = new int[m+1][n+1];
        for(int k=0;k<strs.length;k++){
            int num0 = countChar(strs[k],'0');
            int num1 = strs[k].length() - num0;
            for(int i=m;i>=0;i--){
                for(int j=n;j>=0;j--){
                    if(k==0) dp[i][j] = (i>=num0 && j>=num1 ? 1 : 0);
                    else dp[i][j] = Math.max(dp[i][j],
                            i>=num0 && j>=num1 ? dp[i-num0][j-num1]+1 : 0
                    );
                }
            }
        }
        return dp[m][n];

    }
    //计算字符串中指定字符的数量
    private int countChar(String str, char c){
        int num = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == c) num++;
        }
        return num;
    }

    /**
     * 1049. 最后一块石头的重量 II
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     *
     * @param stones 石头数组
     *               1 <= stones.length <= 30
     *               1 <= stones[i] <= 100
     * @return 最后一块石头的最小可能重量
     */
    public int lastStoneWeightII(int[] stones) {

        /*//1、排序后从大到小遍历，两两相减，重复这个过程直到只剩一个数不为0，时间复杂度O(N*logN*N*logN)
        if(stones==null || stones.length==0) return 0;
        if(stones.length==1) return stones[0];
        boolean flag = false;
        do {
            Arrays.sort(stones);
            if(stones[stones.length-2]==0) return stones[stones.length-1];
            for(int i=stones.length-2;i>=0;i-=2){
                if(stones[i]==0) break;
                else{
                    stones[i+1] -= stones[i];
                    stones[i] = 0;
                    flag = true;
                }
            }
            System.out.println(Arrays.toString(stones));
        } while (flag);
        return stones[stones.length-1];*/

        //上述做法得到的结果有误，例如[31,26,33,21,40]得到9而实际答案为5

        /*//2、动态规划：在数组每一个数前面添加正号或负号，求最小非负数和
        //dp[i][j]表示stones[0...i]添加正号或负号是否可以得到j
        //j的范围为[-3000,3000]，用[0,6000]表示
        boolean[][] dp = new boolean[stones.length][6001];
        dp[0][3000+stones[0]] = true;
        dp[0][3000-stones[0]] = true;
        for(int i=1;i<stones.length;i++){
            for(int j=0;j<=6000;j++){
                if(dp[i-1][j]){
                    dp[i][j+stones[i]] = true;
                    dp[i][j-stones[i]] = true;
                }
            }
        }
        for(int j=3000;j<=6000;j++){
            if(dp[stones.length-1][j]){
                return j-3000;
            }
        }
        return 0;*/

        //3、动态规划：范围根据实际数组决定
        int sum = 0;
        for(int stone : stones){
            sum += stone;
        }
        int range = sum * 2 + 1;
        boolean[][] dp = new boolean[stones.length][range];
        dp[0][sum+stones[0]] = true;
        dp[0][sum-stones[0]] = true;
        for(int i=1;i<stones.length;i++){
            for(int j=0;j<range;j++){
                if(dp[i-1][j]){
                    dp[i][j+stones[i]] = true;
                    dp[i][j-stones[i]] = true;
                }
            }
        }
        for(int j=sum;j<range;j++){
            if(dp[stones.length-1][j]){
                return j-sum;
            }
        }
        return 0;

    }

    /**
     * 第 54 场双周赛 第3题 最大的幻方
     * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。
     * 幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。
     * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
     *
     * @param grid 矩阵(1 <= m, n <= 50)
     * @return 最大幻方的边长
     */
    public int largestMagicSquare(int[][] grid) {
        //前缀和
        int row = grid.length, col = grid[0].length;
        int maxLen = Math.min(row,col);
        //行
        int[][] rowPre = new int[row][col];
        //列
        int[][] colPre = new int[row][col];
        //左对角
        int[][] leftPre = new int[row][col];
        //右对角
        int[][] rightPre = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //行
                if(j==0) rowPre[i][j] = grid[i][j];
                else rowPre[i][j] = rowPre[i][j-1] + grid[i][j];
                //列
                if(i==0) colPre[i][j] = grid[i][j];
                else colPre[i][j] = colPre[i-1][j] + grid[i][j];
                //左对角
                if(i==0 || j==0) leftPre[i][j] = grid[i][j];
                else leftPre[i][j] = leftPre[i-1][j-1] + grid[i][j];
                //右对角
                if(i==0 || j==col-1) rightPre[i][j] = grid[i][j];
                else rightPre[i][j] = rightPre[i-1][j+1] +grid[i][j];
            }
        }
        //最小幻方边长为1
        int max = 1;
        //枚举起点
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //枚举幻方边长
                loop : for(int l=2; l<=maxLen && i+l<=row && j+l<=col; l++){
                    //起始行
                    int sum = rowPre[i][j+l-1] - (j==0 ? 0 : rowPre[i][j-1]);
                    //其余行
                    for(int a=i+1;a<i+l;a++){
                        int rowSum = rowPre[a][j+l-1] - (j==0 ? 0 : rowPre[a][j-1]);
                        if(sum != rowSum) continue loop;
                    }
                    //所有列
                    for(int b=j;b<j+l;b++){
                        int colSum = colPre[i+l-1][b] - (i==0 ? 0 : colPre[i-1][b]);
                        if(sum != colSum) continue loop;
                    }
                    //左对角
                    int leftSum = leftPre[i+l-1][j+l-1] - (i==0 || j==0 ? 0 : leftPre[i-1][j-1]);
                    if(sum != leftSum) continue;
                    //右对角
                    int rightSum = rightPre[i+l-1][j] - (i==0 || j+l==col ? 0 : rightPre[i-1][j+l]);
                    if(sum != rightSum) continue;
                    //找到符合条件的幻方
                    max = Math.max(max,l);
                }
            }
        }
        return max;
    }

    /**
     * 第 245 场周赛 第2题 可移除字符的最大数目
     * 给你两个字符串 s 和 p ，其中 p 是 s 的一个 子序列 。同时，给你一个元素 互不相同 且下标 从 0 开始 计数的整数数组 removable ，该数组是 s 中下标的一个子集（s 的下标也 从 0 开始 计数）。
     * 请你找出一个整数 k（0 <= k <= removable.length），选出 removable 中的 前 k 个下标，然后从 s 中移除这些下标对应的 k 个字符。
     * 整数 k 需满足：在执行完上述步骤后， p 仍然是 s 的一个 子序列 。更正式的解释是，对于每个 0 <= i < k ，先标记出位于 s[removable[i]] 的字符，接着移除所有标记过的字符，然后检查 p 是否仍然是 s 的一个子序列。
     * 返回你可以找出的 最大 k ，满足在移除字符后 p 仍然是 s 的一个子序列。s 和 p 都由小写英文字母组成。
     *
     * @param s 原串
     * @param p 子序列
     * @param removable 移除
     * @return 可移除的最大数目
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        /*//1、暴力破解：时间复杂度O(NM)，N = s.length，M = removable.length
        int k = 0;
        //标识是否删除：0否，1是
        int[] map = new int[s.length()];
        for (int aRemovable : removable) {
            map[aRemovable] = 1;
            if (!isSubSerial(s, p, map)) break;
            k++;
        }
        return k;*/

        //上述做法超时

        //2、二分查找：时间复杂度O(NlogM)，N = s.length，M = removable.length
        int k = 0;
        int left = 0, right = removable.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            Set<Integer> remove = new HashSet<>();
            for(int i=0;i<=mid;i++){
                remove.add(removable[i]);
            }
            if(isSubSerial(s,p,remove)){
                k = mid+1;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return k;
    }
    //1、判断p是否为s的子序列
    private boolean isSubSerial(String s, String p, int[] map){
        int i=0, j=0;
        while(i<s.length() && j<p.length()){
            if (map[i]==0 && s.charAt(i)==p.charAt(j)) j++;
            i++;
        }
        return j==p.length();
    }
    //2、判断p是否为s的子序列
    private boolean isSubSerial(String s, String p, Set<Integer> remove){
        int i=0, j=0;
        while(i<s.length() && j<p.length()){
            if (!remove.contains(i) && s.charAt(i)==p.charAt(j)) j++;
            i++;
        }
        return j==p.length();
    }

    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     *
     * @param s 字符串 (1 <= s.length <= 10^4)(s仅由小写英文字母组成)
     * @param k 整数 (1 <= k <= 10^5)
     * @return 子串长度
     */
    public int longestSubstring(String s, int k) {
        /*//1、暴力破解：枚举所有子串，时间复杂度O(N^2)
        int maxLen = 0;
        if(k<=1) return s.length();
        if(k>s.length()) return maxLen;
        for(int i=0;i<s.length();i++){
            int[] map = new int[26];
            for(int j=i;j<s.length();j++){
                map[s.charAt(j)-'a']++;
                if(isLegalSubstring(map,k)) maxLen = Math.max(maxLen,j-i+1);
            }
        }
        return maxLen;*/

        //2、分治+递归：时间复杂度O(26N)
        //统计字符频率，若存在频率小于k的字符，则符合条件的子串不可能包含这个字符，将原串分割，然后递归求解；若不存在频率小于k的字符，则原串为答案
        int[] map = new int[26];
        for(int i=0;i<s.length();i++) map[s.charAt(i)-'a']++;
        char split = 0;
        for(int i=0;i<26;i++){
            if(map[i]!=0 && map[i]<k) split = (char)(i+'a');
        }
        if(split==0) return s.length();
        int maxLen = 0;
        String[] strs = s.split(String.valueOf(split));
        for(String str : strs){
            if(str.length()==0) continue;
            maxLen = Math.max(maxLen,longestSubstring(str,k));
        }
        return maxLen;
    }
    //判断是否为合法子串
    private boolean isLegalSubstring(int[] map, int k){
        for(int one : map){
            if(one!=0 && one<k) return false;
        }
        return true;
    }

    /**
     * 面试题 17.14. 最小K个数
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     *
     * @param arr 数组
     * @param k 整数
     * @return 最小K个数
     */
    public int[] smallestK(int[] arr, int k) {
        /*//1、排序：时间复杂度O(NlogN)
        Arrays.sort(arr);
        int len = Math.min(k,arr.length);
        int[] ans = new int[len];
        System.arraycopy(arr,0,ans,0,len);
        return ans;*/

        //2、大根堆：时间复杂度O(NlogK)
        if(k<1) return new int[]{};
        PriorityQueue<Integer> queue = new PriorityQueue<>(k,(o1,o2)->o2-o1);
        for (int a : arr) {
            if (queue.size() < k) queue.offer(a);
            else if (queue.peek() > a) {
                queue.poll();
                queue.offer(a);
            }
        }
        int[] ans = new int[queue.size()];
        for(int i=ans.length-1;i>=0;i--){
            ans[i] = queue.poll();
        }
        return ans;
    }

}
