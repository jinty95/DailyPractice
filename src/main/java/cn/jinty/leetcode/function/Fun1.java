package cn.jinty.leetcode.function;

import cn.jinty.struct.linear.ListNode;
import cn.jinty.struct.UnionFind;

import java.util.*;

/**
 * @Description LeetCode算法题题解
 * @Author jinty
 * @Date 2019/9/21.
 */
public class Fun1 {

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
     * 1356. 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     * 请你返回排序后的数组。
     *
     * @param arr 数组
     * @return 排序结果
     */
    public int[] sortByBits(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
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
            }
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
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
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
            }
        });
        for(int i=1;i<=n;i++){
            treeSet.add(i);
        }
        return new ArrayList<>(treeSet);
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
            if(str.equals(uf.parents.get(str))){
                answer.add(str+"("+uf.number.get(str)+")");
            }
        }
        return answer.toArray(new String[answer.size()]);
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
            int idx = (int)s.charAt(i);
            charMap[idx]++;
            if(charMap[idx]>1){
                while(left<right){
                    int idx1 = (int)s.charAt(left++);
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

}
