package cn.jinty.leetcode.function;

import cn.jinty.leetcode.ListNode;
import cn.jinty.leetcode.UnionFind;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @Description LeetCode算法题题解
 * @Author jinty
 * @Date 2019/9/21.
 */
public class Fun1 {

    //大于1的正整数质因数分解
    public List<Integer> decompose(int n){
        if(n<2){
            throw new RuntimeException("该数不存在质因数");
        }
        List<Integer> ans = new ArrayList<>();
        for (int i=2; i<=n; i++) {
            if(n==i){
                ans.add(i);
            }else if(n%i==0){
                ans.add(i);
                n = n/i;
                i--;
            }
        }
        return ans;
    }

    //字符串数组去重
    public String[] getUniqueFromStringArr(String[] strs){
        Set<String> set = new HashSet<>();
        for(String str:strs){
            set.add(str);
        }
        return set.toArray(new String[set.size()]);
    }

    //N×N矩阵旋转90度
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

    //在文本中寻找两个单词的最短距离
    public int findClosest(String[] words, String word1, String word2) {
        /*//先遍历一遍文本，将两个单词位置标记
        int[] stamp = new int[words.length];
        for(int i=0;i<words.length;i++){
            if(words[i].equals(word1)){
                stamp[i] = 1;
            }else if(words[i].equals(word2)){
                stamp[i] = 2;
            }
        }
        //找到2和1的最短距离*/

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

    //根据数字二进制下 1 的数目排序
    //如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
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

    //配对交换：交换某个整数的奇数位和偶数位
    //也就是说，位0与位1交换，位2与位3交换，以此类推
    public int exchangeBits(int num) {
        System.out.println(Integer.toString(num,2));
        int ans = 0;
        int[] map = new int[32];
        for(int i=0;i<32;i++){
            map[i] = ( num & (1<<i) ) > 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(map));
        for(int i=1;i<32;i=i+2){
            ans |= map[i]<<(i-1);
        }
        for(int i=0;i<32;i=i+2){
            ans |= map[i]<<(i+1);
        }
        System.out.println(Integer.toString(ans,2));
        return ans;
    }

    //给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
    //请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
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

        //LeetCode答案：原地算法
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

    //数组大小减半
    //从中选出一个整数集合，并删除这些整数在数组中的每次出现。
    //返回 至少 能删除数组中的一半整数的整数集合的最小大小。
    public int minSetSize(int[] arr) {
        //特殊情况
        if(arr==null || arr.length==0){
            return 0;
        }
        //词频统计
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:arr){
            Integer one = map.get(i);
            if(one==null){
                map.put(i,1);
            }else{
                map.put(i,one+1);
            }
        }
        //按频率排序
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer,Integer> o1, Map.Entry<Integer,Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            queue.add(entry);
        }
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

    //汉诺塔问题
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanoi(A.size(), A, B, C);
    }
    public void hanoi(int n, List<Integer> A, List<Integer> B, List<Integer> C){
        if(n == 1){
            C.add(A.remove(A.size() - 1));
        }else{
            //把A经过辅助C放到B上
            hanoi(n - 1, A, C, B);
            //把A放到C上
            C.add(A.remove(A.size() - 1));
            //把B经过辅助A放到C上
            hanoi(n - 1, B, A, C);
        }
    }

    //List分批
    public void splitList(List<Integer> list,int windowSize){
        int size = list.size();
        int pointer = 0;
        while(pointer<size){
            List<Integer> curList = new ArrayList<>();
            for(int i=0; pointer<size && i<windowSize;i++){
                curList.add(list.get(pointer++));
            }
            System.out.println("当前批次="+curList+", 当前指针="+pointer);
        }
    }

    //判断是否为IP地址
    public boolean isIp(String ip){
        Pattern pattern = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");
        return pattern.matcher(ip).matches();
    }

    //IP地址转为int
    public int ip2int(String ip){
        if(!isIp(ip)){
            throw new RuntimeException(ip+"不是合法的IP地址");
        }
        String[] strs = ip.split("\\.");
        int result = 0;
        for(int i=0;i<strs.length;i++){
            int j = Integer.parseInt(strs[i]);
            if(j>255){
                throw new RuntimeException(ip+"不是合法的IP地址");
            }
            result |= j << (strs.length - 1 - i) * 8;
        }
        return result;
    }

    //int转为ip地址
    public String int2ip(int num){
        StringBuilder sb = new StringBuilder();
        for(int i=3;i>=0;i--){
            int j = (num >>> i * 8) & 255;
            if(j>255){
                throw new RuntimeException(num+"不能转为合法的IP地址");
            }
            sb.append(j);
            if(i!=0){
                sb.append(".");
            }
        }
        return sb.toString();
    }

    //组合和
    public <T> List<List<T>> combination(T[] array){
        List<List<T>> result = new ArrayList<>();
        List<T> record = new ArrayList<>();
        backtrack(0,array,result,record);
        return result;
    }

    /**
     * 回溯算法 - 列举所有组合
     * @param i 当前索引
     * @param array 目标数组
     * @param result 结果
     * @param record 已收集记录
     * @param <T> 泛型
     */
    private <T> void backtrack(
            int i, T[] array,
            List<List<T>> result,
            List<T> record) {
        //结果收集
        result.add(new ArrayList<>(record));
        for (int j = i; j < array.length; j++) {
            //取当前节点
            record.add(array[j]);
            //剩余节点的所有可能组合
            backtrack(j + 1, array, result, record);
            //不取当前节点
            record.remove(record.size() - 1);
        }
    }

    /**
     * 有序数组中进行二分查找
     * @param array 有序数组
     * @param target 目标值
     * @return 目标值所在位置
     */
    public int binarySearch(int[] array,int target){
        int left = 0;
        int right = array.length-1;
        while(left<=right){
            int middle = left + (right-left)/2;
            if(array[middle]==target){
                return middle;
            }else if(array[middle]>target){
                right = middle-1;
            }else{
                left = middle+1;
            }
        }
        return -1;
    }

    /**
     * 计算阶乘 n!
     * @param n 阶乘值
     * @return 阶乘结果
     */
    public long factorial(int n){
        long result = 1;
        for(int i=1;i<=n;i++){
            result *= i;
        }
        return result;
    }

    /**
     * 计算C(n,m)
     * @param n C的下标
     * @param m C的上标
     * @return 结果
     */
    public long combinationNum(int n,int m){
        return factorial(n) / factorial(n-m) / factorial(m);
    }

    /**
     * 打印n对括号的所有合法的（例如，开闭一一对应）组合
     * @param n 括号对
     * @return 括号组合
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder record = new StringBuilder();
        generateParenthesis(result,record,0,n);
        return result;
    }
    /**
     * 递归函数 - 回溯
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
     * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
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
     * 求最长的美好子字符串：
     * 当一个字符串包含的每一种字母的大写和小写形式同时出现，就称这个字符串是美好字符串。
     * 如果有多个答案，返回最早出现的美好子字符串。
     * @param s 输入字符串
     * @return 结果字符串
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
     * @param target 目标值
     * @return 结果
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> lists = new ArrayList<>();
        int left=1;
        int sum = 0;
        for(int right=1; right<=target; right++){
            if(sum == target){
                lists.add(buildSequenceArray(left,right-1));
                sum -= left++;
                right--;
            }else if(sum > target){
                sum -= left++;
                right--;
            }else{
                sum += right;
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
     * @param s 字符串(由英文字母、数字、符号和空格组成)
     * @return 满足条件的最长子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0, left = 0, right = -1;
        int[] charMap = new int[128];
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
     * @param nums 数列
     * @return 总和最大的连续数列的总和值
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int pre = 0;
        for(int i=0; i<nums.length; i++){
            pre = Math.max(pre+nums[i],nums[i]);
            max = Math.max(max,pre);
        }
        return max;
    }
    private int[] buildPrefixSum(int[] nums){
        int[] sum = new int[nums.length];
        int tmp = 0;
        for(int i=0;i<nums.length;i++){
            tmp += nums[i];
            sum[i] = tmp;
        }
        return sum;
    }

    /**
     * 1630. 等差子数组
     * @param nums 原数组
     * @param l 左边界数组
     * @param r 右边界数组
     * @return 在左右边界范围内的子数组能否重组为等差数组。
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
     * @param deck 给定卡牌序列
     * @return 可以按规则处理后递增显示卡牌的卡牌序列
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Queue<Integer> queue = new LinkedList<>();
        for(int i=deck.length-1;i>=0;i--){
            if(queue.isEmpty()){
                queue.offer(deck[i]);
            }else{
                queue.offer(queue.poll());
                queue.offer(deck[i]);
            }
        }
        int[] ans = new int[deck.length];
        for(int i=deck.length-1;i>=0;i--){
            ans[i] = queue.poll();
        }
        return ans;
    }

}


