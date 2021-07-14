package cn.jinty.leetcode.problem.middle;

import java.util.*;

/**
 * LeetCode - 中等题
 *
 * @author jinty
 * @date 2021/6/25
 **/
public class Solution1 {

    /**
     * 752. 打开转盘锁
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转(顺时针或逆时针)：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能对一个拨轮旋转一个单位。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadEnds 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     *
     * @param deadEnds 死亡数字
     * @param target 目标值(target不在deadEnds之中)
     * @return 最小旋转次数
     */
    public int openLock(String[] deadEnds, String target) {
        //1、广度优先搜索：时间复杂度O(8*10^4)
        //从初始状态开始，枚举所有下一步状态，基于下一步所有状态，重复上述过程，纪录步数，直到找到target或所有可能状态都已经出现
        if("0000".equals(target)) return 0;
        Set<String> deadTarget = new HashSet<>(Arrays.asList(deadEnds));
        if(deadTarget.contains("0000")) return -1;
        //用哈希表记录已经出现过的状态
        Set<String> set = new HashSet<>();
        set.add("0000");
        //用队列记录每一步的所有状态
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,0,0});
        //记录步数
        int count = 1;
        //循环直到队列为空
        while( ! queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] a = queue.poll();
                assert a != null;
                //4位数，每位有2种变化，总共8种变化
                for(int j=0;j<4;j++){
                    for(int k=0;k<2;k++){
                        int temp = a[j];
                        //顺时针
                        if(k==0) a[j] = a[j]==9 ? 0 : a[j]+1;
                        //逆时针
                        else a[j] = a[j]==0 ? 9 : a[j]-1;
                        String str = numArrToStr(a);
                        if(target.equals(str)) return count;
                        if(!deadTarget.contains(str) && !set.contains(str)){
                            set.add(str);
                            queue.offer(new int[]{a[0],a[1],a[2],a[3]});
                        }
                        a[j] = temp;
                    }
                }
            }
            count++;
        }
        return -1;
    }
    //数字数组转字符串
    private String numArrToStr(int[] arr){
        if(arr==null) return null;
        StringBuilder sb = new StringBuilder();
        for(int a : arr) sb.append(a);
        return sb.toString();
    }

    /**
     * 909. 蛇梯棋
     * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
     * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
     * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
     * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
     * 1、选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
     * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
     * 2、传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。
     * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
     * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
     *
     * @param board 平面棋盘 (2 <= board.length = board[0].length <= 20)
     * @return 最少步数
     */
    public int snakesAndLadders(int[][] board) {
        //1、广度优先搜索
        //从起点出发，枚举下一步的所有落点，基于下一步的落点集，重复上述过程，记录步数，直到到达终点或所有可能性都已出现
        int row = board.length, col = board[0].length;
        int end = row*col;
        //记录已经走过的点
        int[] pass = new int[end+1];
        //记录下一步的点集
        Queue<Integer> next = new LinkedList<>();
        //标识起点
        pass[1] = 1;
        next.offer(1);
        //记录步数
        int count = 1;
        //层次遍历
        while( ! next.isEmpty()){
            //枚举本层的所有点
            int size = next.size();
            for(int i=0;i<size;i++){
                Integer number = next.poll();
                assert number != null;
                //枚举点的所有下一步落点
                for(int j=1;j<=6;j++){
                    int nextNumber = number+j;
                    int[] coordinate = numberToCoordinate(nextNumber, row, col);
                    if(board[coordinate[0]][coordinate[1]] != -1){
                        nextNumber = board[coordinate[0]][coordinate[1]];
                    }
                    //到达终点则返回
                    if(nextNumber == end) return count;
                    if(pass[nextNumber]==0){
                        pass[nextNumber] = 1;
                        next.offer(nextNumber);
                    }
                }
            }
            count++;
        }
        return -1;
    }
    //数字编号转为棋盘中的坐标
    private int[] numberToCoordinate(int number, int row, int col){
        int[] coordinate = new int[2];
        //行坐标
        coordinate[0] = row - 1 - (number-1)/col;
        //列坐标
        if((row-1-coordinate[0])%2==0){
            coordinate[1] = (number-1)%col;
        }else{
            coordinate[1] = col - 1 - (number-1)%col;
        }
        //System.out.printf("number(%d),coordinate(%d,%d)%n",number,coordinate[0],coordinate[1]);
        return coordinate;
    }

    /**
     * 36. 有效的数独
     * 请你判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * 注意：
     * 一个有效的数独（部分已被填充）不一定是可解的。
     *
     * @param board 9x9面板
     * @return 是否有效数独
     */
    public boolean isValidSudoku(char[][] board) {
        /*//1、三次遍历
        //行
        for(int i=0;i<board.length;i++){
            int[] seen = new int[10];
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='.') continue;
                if(seen[board[i][j]-'1']==1) return false;
                seen[board[i][j]-'1']=1;
            }
        }
        //列
        for(int j=0;j<board[0].length;j++){
            int[] seen = new int[10];
            for(int i=0;i<board.length;i++){
                if(board[i][j]=='.') continue;
                if(seen[board[i][j]-'1']==1) return false;
                seen[board[i][j]-'1']=1;
            }
        }
        //3x3方格
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                int row = a*3;
                int col = b*3;
                int[] seen = new int[10];
                for(int i=row;i<row+3;i++){
                    for(int j=col;j<col+3;j++){
                        if(board[i][j]=='.') continue;
                        if(seen[board[i][j]-'1']==1) return false;
                        seen[board[i][j]-'1']=1;
                    }
                }
            }
        }
        return true;*/

        //2、一次遍历
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][] cell = new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.') continue;
                //行
                if(row[i][board[i][j]-'1']==1) return false;
                row[i][board[i][j]-'1']=1;
                //列
                if(col[j][board[i][j]-'1']==1) return false;
                col[j][board[i][j]-'1']=1;
                //方格
                int num = i/3 * 3 + j/3;
                if(cell[num][board[i][j]-'1']==1) return false;
                cell[num][board[i][j]-'1']=1;
            }
        }
        return true;
    }

    /**
     * 227. 基本计算器 II
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * s 由整数和运算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * 整数除法仅保留整数部分。
     *
     * @param s 字符串表达式
     * @return 计算结果
     */
    public int calculate(String s) {
        //利用栈实现
        //在入栈的过程中计算乘除，仅剩加减运算，再将减号与数字绑定，仅剩加运算，最后出栈求和即可。
        Deque<Integer> stack = new LinkedList<>();
        char operation = '+';
        //遍历表达式
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            //空格
            if(c==' ') continue;
            //数字
            if(Character.isDigit(c)){
                //解析完整数字
                int num=0, j=i;
                while(j<s.length() && Character.isDigit(s.charAt(j))){
                    num = num * 10 + s.charAt(j)-'0';
                    j++;
                }
                i=j-1;
                //先完成乘除
                if( ! stack.isEmpty() && ('*'==operation || '/'==operation)){
                    int pre = stack.pop();
                    if('*'==operation) num = pre * num;
                    else num = pre / num;
                }
                //减号与数字绑定
                if('-'==operation){
                    num = -num;
                }
                stack.push(num);
            }else{
                //运算符
                operation = c;
            }
        }
        //遍历栈做加法运算
        int result = 0;
        while( ! stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }

    /**
     * 43. 字符串相乘
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，
     * 返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * @param num1 字符串
     * @param num2 字符串
     * @return 乘积
     */
    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) return "0";
        String result = "0";
        //把num2按位拆分，每位与num1相乘，乘后补足0，最后把所有值相加
        for(int i=0;i<num2.length();i++){
            int j = num2.length()-1-i;
            String product = multiply(num1,num2.charAt(j)-'0');
            StringBuilder sb = new StringBuilder(product);
            for(int k=0;k<i;k++){
                sb.append("0");
            }
            product = sb.toString();
            result = add(result,product);
        }
        return result;
    }
    //字符串乘[0~9]
    private String multiply(String num1, int num2){
        if(num2==0) return "0";
        StringBuilder sb = new StringBuilder();
        //进位
        int carry = 0;
        //从低位开始按位相乘
        for(int i=num1.length()-1;i>=0;i--){
            int num = (num1.charAt(i)-'0') * num2 + carry;
            sb.append(num%10);
            carry = num/10;
        }
        if(carry>0) sb.append(carry);
        return sb.reverse().toString();
    }
    //字符串相加
    private String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        //进位
        int carry = 0;
        //从低位开始按位相加
        int i=num1.length()-1, j=num2.length()-1;
        while(i>=0 || j>=0){
            int num = carry + (i>=0?num1.charAt(i)-'0':0) + (j>=0?num2.charAt(j)-'0':0);
            sb.append(num%10);
            carry = num/10;
            i--;
            j--;
        }
        if(carry==1) sb.append(1);
        return sb.reverse().toString();
    }

    /**
     * 93. 复原 IP 地址
     * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     *
     * @param s 字符串 (0 <= s.length <= 3000)
     * @return 有效的IP地址集合
     */
    public List<String> restoreIpAddresses(String s) {
        //递归回溯：枚举ip地址的4个数
        List<String> result = new ArrayList<>();
        if(s.length()<4 || s.length()>12) return result;
        List<Integer> ip = new ArrayList<>();
        restoreIpAddresses(s,0,ip,result);
        return result;
    }
    //递归函数
    private void restoreIpAddresses(String s, int idx, List<Integer> ip, List<String> result){
        if(ip.size()==4){
            //ip地址收集4个数且原字符串用完，即得到一个可行答案
            if(idx==s.length()){
                result.add(buildIp(ip));
            }
            return;
        }
        int num = 0;
        for(int i=idx;i<s.length();i++){
            num = num * 10 + (s.charAt(i)-'0');
            //非法数
            if(num>255) break;
            //递归
            ip.add(num);
            restoreIpAddresses(s,i+1,ip,result);
            //回溯
            ip.remove(ip.size()-1);
            //0作为单独一个数
            if(num==0) break;
        }
    }
    //构建IP字符串
    private String buildIp(List<Integer> ip){
        StringBuilder sb = new StringBuilder();
        for(Integer num : ip){
            sb.append(num).append('.');
        }
        return sb.substring(0,sb.length()-1);
    }

    /**
     * 451. 根据字符出现频率排序
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * @param s 字符串
     * @return 按频率倒序得到的新字符串
     */
    public String frequencySort(String s) {
        //词频统计
        Map<Character,Integer> map = new HashMap<>();
        for(char c :s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        //排序
        List<Map.Entry<Character,Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        //重构
        StringBuilder res = new StringBuilder();
        for(Map.Entry<Character,Integer> entry : entryList){
            for(int i=0;i<entry.getValue();i++){
                res.append(entry.getKey());
            }
        }
        return res.toString();
    }

    /**
     * 31. 下一个排列
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     * @param numbers 数组 (1 <= nums.length <= 100)
     */
    public void nextPermutation(int[] numbers) {
        //从右向左，找到第一个下降点i
        int i=-1;
        for(int j=numbers.length-2;j>=0;j--){
            if(numbers[j]<numbers[j+1]){
                i=j;
                break;
            }
        }
        if(i==-1){
            //不存在从右向左的下降点，说明原排列从左向右降序，那么下一个排列为从左向右升序排列
            Arrays.sort(numbers);
        }else{
            //在下降点i的右区间，找到比i大的最小值，与i交换，然后对i+1开始的右区间升序
            int j=i+1;
            while(j<numbers.length && numbers[i]<numbers[j]){
                j++;
            }
            j--;
            swap(numbers,i,j);
            Arrays.sort(numbers,i+1,numbers.length);
        }
    }
    //元素交换
    private void swap(int[] arr, int a, int b){
        if(a==b) return;
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }

    /**
     * 1418. 点菜展示表
     * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerName,tableNumber,foodItem] ，
     * 其中 customerName 是客户的姓名，tableNumber 是客户所在餐桌的桌号，而 foodItem 是客户点的餐品名称。
     * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
     * 接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
     * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
     *
     * @param orders 订单列表
     * @return 点菜展示表
     */
    public List<List<String>> displayTable(List<List<String>> orders) {
        //哈希表存菜品列表
        TreeSet<String> foods = new TreeSet<>();
        //哈希表存桌号及其订单(桌号->(菜品->数量))
        TreeMap<Integer,Map<String,Integer>> tableAndOrder = new TreeMap<>();
        for(List<String> order : orders){
            Integer tableNumber = Integer.parseInt(order.get(1));
            String foodItem = order.get(2);
            foods.add(foodItem);
            Map<String, Integer> foodAndNum = tableAndOrder.computeIfAbsent(tableNumber, k -> new HashMap<>());
            foodAndNum.put(foodItem,foodAndNum.getOrDefault(foodItem,0)+1);
        }
        //构建结果列表
        List<List<String>> displays = new ArrayList<>();
        //标题
        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(foods);
        displays.add(title);
        //餐桌、菜品和数量
        for(Integer tableNumber : tableAndOrder.keySet()){
            List<String> display = new ArrayList<>();
            display.add(String.valueOf(tableNumber));
            Map<String,Integer> foodAndNum = tableAndOrder.get(tableNumber);
            for(int i=1;i<title.size();i++){
                Integer number = foodAndNum.get(title.get(i));
                if(number==null) number=0;
                display.add(String.valueOf(number));
            }
            displays.add(display);
        }
        return displays;
    }

    /**
     * 1711. 大餐计数
     * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。你可以搭配 任意 两道餐品做一顿大餐。
     * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，
     * 返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 10^9 + 7 取余。
     * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
     *
     * @param deliciousness 餐品列表(1 <= deliciousness.length <= 10^5)(0 <= deliciousness[i] <= 2^20)
     * @return 大餐数量
     */
    public int countPairs(int[] deliciousness) {

        /*//1、枚举：时间复杂度O(N^2)，最大O(10^10)，可能超时
        //二次幂集合
        Set<Integer> pow2 = new HashSet<>();
        int base = 1;
        for(int i=0;i<=21;i++){
            pow2.add(base);
            base *= 2;
        }
        int count = 0, mod = 1000000007;
        //二层遍历
        for(int i=0;i<deliciousness.length;i++){
            for(int j=i+1;j<deliciousness.length;j++){
                if(pow2.contains(deliciousness[i]+deliciousness[j])){
                    count = (count + 1) % mod;
                }
            }
        }
        return count;*/

        //2、哈希表：时间复杂度O(22N)=O(N)
        //二次幂数组
        int[] pow2 = new int[22];
        pow2[0] = 1;
        for(int i=1;i<pow2.length;i++){
            pow2[i] = pow2[i-1]*2;
        }
        //频率统计(数字->出现次数)
        Map<Integer,Integer> numFreq = new HashMap<>();
        for (int delicious : deliciousness) {
            numFreq.put(delicious, numFreq.getOrDefault(delicious, 0) + 1);
        }
        int count = 0, mod = 1000000007;
        //枚举数字
        for(int num : deliciousness){
            //将当前数字从频率中去除：防止匹配自己，也防止后续其它数匹配这个数
            numFreq.put(num,numFreq.get(num)-1);
            //枚举二次幂
            for(int a : pow2){
                int diff = a - num;
                if(numFreq.containsKey(diff)){
                    count = (count + numFreq.get(diff)) % mod;
                }
            }
        }
        return count;

    }

    /**
     * 930. 和相同的二元子数组
     * 给你一个二元数组 numbers ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * 子数组 是数组的一段连续部分。
     *
     * @param numbers 二元数组
     * @param goal 目标
     * @return 子数组数量
     */
    public int numSubArraysWithSum(int[] numbers, int goal) {
        //1、前缀和+哈希表
        int count = 0;
        int pre = 0;
        //哈希表保存(前缀和->出现次数)
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int number : numbers){
            pre += number;
            int diff = pre - goal;
            if(map.containsKey(diff)){
                count += map.get(diff);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return count;
    }

    /**
     * 274. H指数
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
     * h 指数的定义：h 代表“高引用次数”（high citations），
     * 一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
     * 且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
     *
     * @param citations 引用次数
     * @return H指数
     */
    public int hIndex(int[] citations) {

        /*//1、排序+二分查找：时间复杂度O(NlogN)
        if(citations==null || citations.length==0) return 0;
        //排序
        Arrays.sort(citations);
        //二分查找
        int len = citations.length;
        int left = 0, right = len-1;
        int ans = 0;
        while(left<=right){
            //求中点
            int mid = left + (right-left)/2;
            //右区间数量
            int rightCount = len - mid;
            if(citations[mid]<=rightCount){
                //中点引用数小于等于右区间数量，说明中点可能是H指数，向右找更大的H指数
                ans = Math.max(ans,citations[mid]);
                left = mid+1;
            }else{
                //中点引用数大于右区间数量，右区间数量可能是H指数，向左找更大的H指数
                ans = Math.max(ans,rightCount);
                right = mid-1;
            }
        }
        return ans;*/

        //2、排序+遍历：时间复杂度O(NlogN)
        if(citations==null || citations.length==0) return 0;
        Arrays.sort(citations);
        int h = 0;
        for(int i=citations.length-1;i>=0;i--){
            if(citations[i]>h) h++;
            else break;
        }
        return h;

    }

    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 注意：解集不能包含重复的组合。
     *
     * @param candidates 候选数组(1 <= candidates.length <= 100)(1 <= candidates[i] <= 50)
     * @param target 目标(1 <= target <= 30)
     * @return 组合
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //1、回溯算法：时间复杂度O(2^N)，实际上不会到达2^N，因为组合的和大于等于target时就不会往下递归了
        //排序
        Arrays.sort(candidates);
        //单个组合
        List<Integer> result = new ArrayList<>();
        //所有组合，使用Set去重
        Set<List<Integer>> results = new HashSet<>();
        //递归回溯
        combinationSum2(candidates,0,target,0,result,results);
        //返回，Set转List
        return new ArrayList<>(results);
    }
    //递归回溯
    @SuppressWarnings("unused")
    private void combinationSum2(int[] candidates, int idx, int target, int sum, List<Integer> result, Set<List<Integer>> results){
        //等于target，终止递归
        if(sum==target){
            results.add(new ArrayList<>(result));
            return;
        }
        //角标越界，终止递归
        if(idx>=candidates.length) return;
        //遍历原数组
        for(int i=idx;i<candidates.length;i++){
            //大于target，则后续会一直大于target，可以提前返回
            if(sum+candidates[i]>target) break;
            //递归
            result.add(candidates[i]);
            combinationSum2(candidates,i+1,target,sum+candidates[i],result,results);
            //回溯
            result.remove(result.size()-1);
        }
    }

    /**
     * 1818. 绝对差值和
     * 给你两个正整数数组 numbers1 和 numbers2 ，数组的长度都是 n 。
     * 数组 numbers1 和 numbers2 的 绝对差值和 定义为所有 |numbers1[i] - numbers2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
     * 你可以选用 numbers1 中的 任意一个 元素来替换 numbers1 中的 至多 一个元素，以 最小化 绝对差值和。
     * 在替换数组 numbers1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 10^9 + 7 取余 后返回。
     *
     * @param numbers1 数组1 (1 <= len <= 10^5)(1 <= numbers1[i] <= 10^5)
     * @param numbers2 数组2 (1 <= len <= 10^5)(1 <= numbers2[i] <= 10^5)
     * @return 最小绝对差值和
     */
    public int minAbsoluteSumDiff(int[] numbers1, int[] numbers2) {
        //1、有序集合：时间复杂度O(NlogN)，空间复杂度O(N)
        //先计算好绝对差值和，并使用TreeSet存放numbers1，然后遍历numbers1，针对numbers1[i]，在TreeSet中快速寻找环绕numbers2[i]的两个数，
        //分别用它们替换numbers1[i]，看能否使得结果更小，记录过程中出现的最大减小幅度，绝对差值和减去最大减幅即为答案。
        final int MOD = 1000000007;
        TreeSet<Integer> treeSet = new TreeSet<>();
        int absoluteSum = 0;
        for(int i=0;i<numbers1.length;i++){
            absoluteSum = (absoluteSum + Math.abs(numbers1[i]-numbers2[i])) % MOD;
            treeSet.add(numbers1[i]);
        }
        int maxDesc = 0;
        for(int i=0;i<numbers1.length;i++){
            int diff1 = Math.abs(numbers1[i]-numbers2[i]);
            Integer ceil = treeSet.ceiling(numbers2[i]);
            if(ceil!=null){
                int diff2 = Math.abs(ceil-numbers2[i]);
                if(diff1>diff2) maxDesc = Math.max(maxDesc,diff1-diff2);
            }
            Integer floor = treeSet.floor(numbers2[i]);
            if(floor!=null){
                int diff2 = Math.abs(floor-numbers2[i]);
                if(diff1>diff2) maxDesc = Math.max(maxDesc,diff1-diff2);
            }
        }
        return (absoluteSum - maxDesc + MOD) % MOD;
    }

}
