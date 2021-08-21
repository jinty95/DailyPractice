package cn.jinty.leetcode.problem.middle;

import cn.jinty.struct.tree.TreeNode;

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
     * @param numbers 数组 (1 <= length <= 100)
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

    /**
     * 1846. 减小和重新排列数组后的最大元素
     * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
     * 1、arr 中第一个元素必须为 1 。
     * 2、任意相邻两个元素的差的绝对值小于等于 1 。
     * 你可以执行以下 2 种操作任意次：
     * 1、减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
     * 2、重新排列 arr 中的元素，你可以以任意顺序重新排列。
     * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的最大值。
     *
     * @param arr 正整数数组
     * @return 最大值
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        //排序+遍历：时间复杂度O(NlogN)
        Arrays.sort(arr);
        arr[0] = 1;
        int pre = 1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]-pre>1){
                arr[i] = pre+1;
            }
            pre = arr[i];
        }
        return pre;
    }

    /**
     * 1838. 最高频元素的频数
     * 元素的 频数 是该元素在一个数组中出现的次数。
     * 给你一个整数数组 numbers 和一个整数 k 。在一步操作中，你可以选择 numbers 的一个下标，并将该下标对应元素的值增加 1 。
     * 执行最多 k 次操作后，返回数组中最高频元素的最大可能频数。
     *
     * @param numbers 数组 (1 <= numbers.length <= 10^5)
     * @param k 最大操作次数
     * @return 最高频元素的最大可能频数
     */
    public int maxFrequency(int[] numbers, int k) {

        /*//1、排序+哈希表
        //排序
        Arrays.sort(numbers);
        //哈希表记录数字、频数、数字第一次出现位置
        Map<Integer,int[]> map = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            int[] value = map.computeIfAbsent(numbers[i],a->new int[]{0,-1});
            //数字第一次出现位置
            if(value[1]==-1) value[1]=i;
            //频数
            value[0]++;
        }
        //对于每个数，都向前操作，记录最大可能频数
        int maxFreq = 0;
        for(Integer key : map.keySet()){
            int[] value = map.get(key);
            int count = value[0];
            int pre = value[1]-1;
            int i = k;
            while(pre>=0 && i>0){
                if(i>=key-numbers[pre]){
                    i-=(key-numbers[pre]);
                    count++;
                    pre--;
                }else{
                    break;
                }
            }
            maxFreq = Math.max(maxFreq,count);
        }
        return maxFreq;*/

        //2、排序+滑动窗口
        //窗口每次右滑一位，需要增加(right-left)*(numbers[right]-numbers[right-1])个数字，判断是否超过k
        Arrays.sort(numbers);
        int ans = 1;
        int left = 0, right = 1, delta = 0;
        while(right<numbers.length){
            delta += (right-left)*(numbers[right]-numbers[right-1]);
            while(delta>k){
                delta -= (numbers[right]-numbers[left]);
                left++;
            }
            ans = Math.max(ans,right-left+1);
            right++;
        }
        return ans;

    }

    /**
     * 89. 格雷编码
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
     * 格雷编码序列必须以 0 开头。
     *
     * @param n 位数
     * @return 格雷编码序列
     */
    public List<Integer> grayCode(int n) {
        //位运算
        //格雷编码的生成过程: G(i) = i ^ (i/2)
        List<Integer> result = new ArrayList<>();
        int size = 1<<n;
        for(int i=0; i<size; i++){
            result.add(i ^ (i>>1));
        }
        return result;
    }

    /**
     * 1743. 从相邻元素对还原数组
     * 存在一个由 n 个不同元素组成的整数数组 numbers ，但你已经记不清具体内容。好在你还记得 numbers 中的每一对相邻元素。
     * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 numbers 中相邻。
     * 题目数据保证所有由元素 numbers[i] 和 numbers[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，
     * 存在形式可能是 [numbers[i], numbers[i+1]] ，也可能是 [numbers[i+1], numbers[i]] 。这些相邻元素对可以 按任意顺序 出现。
     * 返回 原始数组 numbers 。如果存在多种解答，返回 其中任意一个 即可。
     *
     * @param adjacentPairs 相邻元素 (2 <= n <= 10^5)
     * @return 原数组
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        //1、哈希表：时间复杂度O(N)，空间复杂度O(N)
        //因为元素都是唯一的，所以第一个元素和最后一个元素在相邻元素中只会出现一次，其余都是两次
        int[] origin = new int[adjacentPairs.length+1];
        //使用哈希表维护(元素，与该元素相邻的元素组成的列表)
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int[] adjacentPair : adjacentPairs){
            map.computeIfAbsent(adjacentPair[0],k->new ArrayList<>()).add(adjacentPair[1]);
            map.computeIfAbsent(adjacentPair[1],k->new ArrayList<>()).add(adjacentPair[0]);
        }
        //遍历哈希表找出现一次的元素
        int first = 0;
        for(Integer key : map.keySet()){
            if(map.get(key).size()==1){
                first = key;
                break;
            }
        }
        //使用Set去重，从第一个元素开始向后恢复原数组
        Set<Integer> set = new HashSet<>();
        origin[0] = first;
        set.add(first);
        for(int i=1;i<origin.length;i++){
            for(Integer next : map.get(origin[i-1])){
                if(set.contains(next)) continue;
                origin[i] = next;
                set.add(next);
            }
        }
        return origin;
    }

    /**
     * 221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     *
     * @param matrix 矩阵(1 <= m, n <= 300)
     * @return 最大正方形面积
     */
    public int maximalSquare(char[][] matrix) {
        //1、前缀和
        int maxSquare = 0;
        int row = matrix.length, col = matrix[0].length;
        //preSum[i][j]表示matrix[0][0]到matrix[i][j]的子矩阵中'1'的数量
        int[][] preSum = new int[row][col];
        preSum[0][0] = matrix[0][0] == '1' ? 1 : 0;
        for(int i=1; i<row; i++){
            preSum[i][0] = preSum[i-1][0] + (matrix[i][0] == '1' ? 1 : 0);
        }
        for(int j=1; j<col; j++){
            preSum[0][j] = preSum[0][j-1] + (matrix[0][j] == '1' ? 1 : 0);
        }
        for(int i=1; i<row; i++){
            for(int j=1; j<col; j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + (matrix[i][j] == '1' ? 1 : 0);
            }
        }
        //枚举起点(只有'1'作为起点才有意义)
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(matrix[i][j]=='1'){
                    //正方形边长为1
                    maxSquare = Math.max(maxSquare,1);
                    //枚举正方形边长(大于1)
                    for(int len=1; len<Math.min(row,col); len++){
                        int ii = i+len, jj = j+len;
                        if(ii>=row || jj>=col) break;
                        //正方形中1的数量
                        int num1 = preSum[ii][jj] - (j>0 ? preSum[ii][j-1] : 0) - (i>0 ? preSum[i-1][jj] : 0) + (i>0&&j>0 ? preSum[i-1][j-1] : 0);
                        int numSquare = (len+1)*(len+1);
                        if(num1 == numSquare){
                            maxSquare = Math.max(maxSquare,numSquare);
                        }
                    }
                }
            }
        }
        return maxSquare;
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     *
     * @param root 二叉树
     * @param target 目标节点
     * @param k 整数
     * @return 二叉树中到目标节点距离为k的所有节点
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> list = new ArrayList<>();
        if(k==0){
            list.add(target.val);
            return list;
        }
        //深度遍历：子节点映射父节点
        Map<TreeNode,TreeNode> map = new HashMap<>();
        nodeToParent(root,map);
        //广度遍历：从target开始做图的广搜，统计第k层的节点数
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        Set<TreeNode> set = new HashSet<>();
        set.add(target);
        int deep = 1;
        while( ! queue.isEmpty() && deep<=k){
            int size = queue.size();
            while(size>0){
                TreeNode node = queue.poll();
                if(node==null) continue;
                //父节点
                TreeNode parent = map.get(node);
                if(parent!=null && !set.contains(parent)){
                   set.add(parent);
                   if(deep==k) list.add(parent.val);
                   queue.offer(parent);
                }
                //左节点
                if(node.left!=null && !set.contains(node.left)){
                    set.add(node.left);
                    if(deep==k) list.add(node.left.val);
                    queue.offer(node.left);
                }
                //右节点
                if(node.right!=null && !set.contains(node.right)){
                    set.add(node.right);
                    if(deep==k) list.add(node.right.val);
                    queue.offer(node.right);
                }
                size--;
            }
            deep++;
        }
        return list;
    }
    private void nodeToParent(TreeNode root, Map<TreeNode,TreeNode> map){
        if(root==null) return;
        if(root.left!=null){
            map.put(root.left,root);
            nodeToParent(root.left,map);
        }
        if(root.right!=null){
            map.put(root.right,root);
            nodeToParent(root.right,map);
        }
    }

    /**
     * 1104. 二叉树寻路
     * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
     * 在奇数行（即第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
     * 而偶数行（即第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
     * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
     *
     * @param label 标号
     * @return 路径
     */
    public List<Integer> pathInZigZagTree(int label) {
        //如果是正常标号的满二叉树，节点的父节点可以通过除2找到
        //这里是之字形满二叉树，节点的父节点可以通过除2再求对称值找到
        //将数字转为二进制形式，可以发现对称值的二进制存在规律：第一位保持，其它位翻转(100->111,101->110)
        List<Integer> path = new ArrayList<>();
        while(label>0){
            path.add(label);
            label /= 2;
            label ^= Integer.highestOneBit(label)-1;
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * 743. 网络延迟时间
     * 有 n 个网络节点，标记为 1 到 n。给你一个列表 times，表示信号经过 有向 边的传递时间。
     * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     *
     * @param times 网络节点传输时间
     * @param n 节点数
     * @param k 起点
     * @return 延迟时间
     */
    public int networkDelayTime(int[][] times, int n, int k) {

        /*//1、广度优先遍历
        //使用哈希表保存(节点->后继节点集)
        Map<Integer,Map<Integer,Integer>> timesMap = new HashMap<>();
        for(int[] time : times){
            Map<Integer,Integer> map = timesMap.computeIfAbsent(time[0],a->new HashMap<>());
            map.put(time[1],time[2]);
        }
        int maxTime = 0;
        //使用队列保存一个层级的节点集(节点值，当该节点的耗时)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{k,0});
        //保存已经被遍历的节点
        Set<Integer> occurred = new HashSet<>();
        occurred.add(k);
        //进行层次遍历
        while( ! queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] node = queue.poll();
                assert node != null;
                //更新最长耗时
                maxTime = Math.max(maxTime,node[1]);
                //枚举当前节点的所有后继节点
                Map<Integer,Integer> next = timesMap.get(node[0]);
                if(next==null || next.size()==0) continue;
                for(Integer key : next.keySet()){
                    if(occurred.contains(key)) continue;
                    queue.offer(new int[]{key,node[1]+next.get(key)});
                    occurred.add(key);
                }
            }
        }
        //判断是否遍历了全部节点
        return occurred.size()==n ? maxTime : -1;*/

        //上述做法只能用于解决树型网络，对于图型网络会得到错误的结果

        //2、Dijkstra算法
        //求所有节点到 k 的最短路径，其中的最大值即为本题答案
        final int INT_HALF = Integer.MAX_VALUE / 2;
        //构建图
        int[][] graph = new int[n][n];
        for(int[] row : graph){
            Arrays.fill(row, INT_HALF);
        }
        for(int[] time : times){
            graph[time[0]-1][time[1]-1] = time[2];
        }
        //节点到 k 的最短路径
        int[] distances = new int[n];
        Arrays.fill(distances, INT_HALF);
        distances[k-1] = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            //从未使用的节点中找到距离 k 最近的节点
            int a = -1;
            for (int b = 0; b < n; b++) {
                if (!used[b] && (a == -1 || distances[b] < distances[a])) {
                    a = b;
                }
            }
            //将这个节点修改为已使用
            used[a] = true;
            //根据当前节点去更新其它的所有节点
            for (int b = 0; b < n; b++) {
                distances[b] = Math.min(distances[b], distances[a] + graph[a][b]);
            }
        }
        int ans = Arrays.stream(distances).max().getAsInt();
        return ans == INT_HALF ? -1 : ans;

    }

    /**
     * 581. 最短无序连续子数组
     * 给你一个整数数组 numbers ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     *
     * @param numbers 数组
     * @return 最短无序子数组
     */
    public int findUnsortedSubArray(int[] numbers) {
        //双指针
        //两次遍历寻找左右的两个有序区间，中间部分即为需要排序的子数组
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int high = -1, low = -1;
        //从左向右遍历，如果当前n[i]小于左边的最大值，那么这个n[i]肯定要重排序
        for(int i=0;i<numbers.length;i++){
            max = Math.max(max,numbers[i]);
            if(max>numbers[i]){
                high = i;
            }
        }
        //从右向左遍历，如果当前n[i]大于右边的最小值，那么这个n[i]肯定要重排序
        for(int i=numbers.length-1;i>=0;i--){
            min = Math.min(min,numbers[i]);
            if(min<numbers[i]){
                low = i;
            }
        }
        return high==low ? 0 : high-low+1;
    }

    /**
     * 611. 有效三角形的个数
     * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
     *
     * @param numbers 边长数组(数组长度不超过1000)
     * @return 组成三角形的三元组个数
     */
    public int triangleNumber(int[] numbers) {
        /*//1、三层遍历：时间复杂度O(N^3)
        int count = 0;
        //排序
        Arrays.sort(numbers);
        //从小边开始枚举
        for(int i=0;i<numbers.length-2;i++){
            for(int j=i+1;j<numbers.length-1;j++){
                for(int k=j+1;k<numbers.length;k++){
                    //判断是否存在两边之和大于第三边，不满足则提前剪枝
                    if(numbers[i]+numbers[j]>numbers[k]){
                        //System.out.println(numbers[i]+" "+numbers[j]+" "+numbers[k]);
                        count++;
                    }else{
                        break;
                    }
                }
            }
        }
        return count;*/

        //2、二分查找：时间复杂度O(N^2 * logN)
        int count = 0;
        //排序
        Arrays.sort(numbers);
        //从小边开始枚举
        for(int i=0;i<numbers.length-2;i++){
            for(int j=i+1;j<numbers.length-1;j++){
                int idx = binarySearch(numbers, j+1, numbers.length-1, numbers[i]+numbers[j]);
                if(idx>0){
                    count += idx-j;
                }
            }
        }
        return count;
    }
    //在有序数组中二分查找，寻找第一个比target小的元素所在位置
    private int binarySearch(int[] numbers, int left, int right, int target){
        int idx = -1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(target <= numbers[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
                idx = mid;
            }
        }
        return idx;
    }

    /**
     * 802. 找到最终的安全状态
     * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
     * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
     * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
     * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。
     * 图以下述形式给出：graph[i] 是一个列表，graph[i][j] 是一个节点编号，(i,graph[i][j]) 是图的一条有向边。
     *
     * @param graph 图 (1 <= n <= 10^4)
     * @return 安全点集
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //1、循环标记，直到所有节点都被标记：时间复杂度最好为O(N)，最差为O(N^2)
        //标记 i 是否为安全点：-1未知，0否，1是
        int count = 0;
        int[] isSafe = new int[graph.length];
        //默认都标记为未知
        Arrays.fill(isSafe, -1);
        //一次循环如果没有任何点被标记，提前结束，避免死循环
        boolean countChange = true;
        while(countChange && count<graph.length){
            countChange = false;
            for(int i=0; i<isSafe.length; i++){
                //节点已被标记则忽略
                if(isSafe[i] != -1) continue;
                if(graph[i].length==0){
                    //节点没有后继
                    isSafe[i] = 1;
                    count++;
                    countChange = true;
                }else{
                    //节点有后继，根据所有后继节点的标记判断当前节点的标记
                    boolean flag = true;
                    for(int j=0; j<graph[i].length; j++){
                        if(isSafe[graph[i][j]]==-1){
                            flag = false;
                            break;
                        }
                        if(isSafe[graph[i][j]]==0){
                            flag = false;
                            isSafe[i] = 0;
                            count++;
                            countChange = true;
                            break;
                        }
                    }
                    if(flag){
                        isSafe[i] = 1;
                        count++;
                        countChange = true;
                    }
                }
            }
        }
        //收集标记为安全的所有节点
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<isSafe.length; i++){
            if(isSafe[i]==1) ans.add(i);
        }
        return ans;
    }

    /**
     * 457. 环形数组是否存在循环
     * 存在一个不含 0 的 环形 数组 numbers ，每个 numbers[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
     * 如果 numbers[i] 是正数，向前 移动 numbers[i] 步
     * 如果 numbers[i] 是负数，向后 移动 numbers[i] 步
     * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
     * 数组中的 循环 由长度为 k 的下标序列 seq 组成：
     * 1、遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
     * 2、所有 numbers[seq[j]] 应当不是 全正 就是 全负
     * 3、k > 1
     *
     * @param numbers 数组 (1 <= n <= 5000)
     * @return 是否存在循环
     */
    public boolean circularArrayLoop(int[] numbers) {

        /*//1、暴力搜索：时间复杂度O(N^2)
        int n = numbers.length;
        if(n==0 || n==1) return false;
        //枚举起点
        for(int i=0; i<n; i++){
            //是否向前
            boolean forward = numbers[i]>0;
            //记录已经过的点
            Set<Integer> seen = new HashSet<>();
            seen.add(i);
            //向下搜索
            int j = i;
            while(true){
                //计算下一个点的位置
                j = nextIndex(numbers, j);
                //运行方向相反，直接终止
                if(numbers[j]>0 != forward) break;
                //判断是否出现重合
                if(seen.contains(j)){
                    if(seen.size()>1 && i==j) return true;
                    break;
                }else{
                    seen.add(j);
                }
            }
        }
        return false;*/

        //2、快慢指针：时间复杂度O(N)
        //快指针走两步，慢指针走一步，如果存在环，那么两个指针最终一定会相遇
        int n = numbers.length;
        for(int i=0; i<n; i++){
            //值为0直接忽略
            if(numbers[i]==0) continue;
            //定义快慢指针
            int slow = i, fast = nextIndex(numbers,i);
            //保证前进方向与初始方向一致，并且不会走到值为0的点
            while(numbers[slow] * numbers[nextIndex(numbers,slow)]>0
                    && numbers[fast] * numbers[nextIndex(numbers,fast)]>0
                    && numbers[nextIndex(numbers,fast)] * numbers[nextIndex(numbers,nextIndex(numbers,fast))]>0){
                //发现环
                if(slow==fast){
                    if(slow==nextIndex(numbers,slow)){
                        break;
                    }
                    return true;
                }
                slow = nextIndex(numbers,slow);
                fast = nextIndex(numbers,nextIndex(numbers,fast));
            }
            //将已访问过的不满足要求的节点对应的值置为0
            int j=i;
            while(numbers[j]*numbers[nextIndex(numbers,j)]>0){
                //先通过j找到next，然后再把j对应的值置为0，顺序不能颠倒
                int temp = j;
                j = nextIndex(numbers,j);
                numbers[temp] = 0;
            }
        }
        return false;

    }
    //根据当前位置求下一个位置
    private int nextIndex(int[] numbers, int cur){
        int index = (cur + numbers[cur]) % numbers.length;
        return index >= 0 ? index : index + numbers.length;
    }

    /**
     * 413. 等差数列划分
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
     * 给你一个整数数组 numbers ，返回数组 numbers 中所有为等差数组的 子数组 个数。
     * 子数组 是数组中的一个连续序列。
     *
     * @param numbers 数组 (1 <= length <= 5000)
     * @return 等差子数组个数
     */
    public int numberOfArithmeticSlices(int[] numbers) {
        //一次遍历：时间复杂度O(N)
        if(numbers==null || numbers.length<3) return 0;
        //子数组数量
        int count = 0;
        //长度
        int len = 2;
        //差值
        int diff = numbers[0] - numbers[1];
        for(int i=2; i<numbers.length; i++){
            int curDiff = numbers[i-1] - numbers[i];
            if(curDiff==diff){
                len++;
                count += (len-2);
            }else{
                diff = curDiff;
                len = 2;
            }
        }
        return count;
    }

    /**
     * 516. 最长回文子序列
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     *
     * @param s 字符串
     * @return 最长回文子序列长度
     */
    public int longestPalindromeSubSeq(String s) {
        //1、动态规划：时间复杂度O(N^2)，空间复杂度O(N^2)
        char[] arr = s.toCharArray();
        //dp[i][j]表示s[i..j]的最长回文子序列长度
        int[][] dp = new int[arr.length][arr.length];
        //长度为1
        for(int i=0; i<arr.length; i++){
            dp[i][i] = 1;
        }
        //长度为2
        for(int i=0; i<arr.length-1; i++){
            if(arr[i]==arr[i+1]){
                dp[i][i+1] = 2;
            }else{
                dp[i][i+1] = 1;
            }
        }
        //长度大于2
        for(int len=2; len<arr.length; len++){
            for(int i=0; i<arr.length-len; i++){
                int j=i+len;
                //递推方程
                if(arr[i]==arr[j]){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                }
            }
        }
        return dp[0][arr.length-1];
    }

    /**
     * 1583. 统计不开心的朋友
     * 给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
     * 对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。换句话说，排在列表前面的朋友与 i 的亲近程度比排在列表后面的朋友更高。每个列表中的朋友均以 0 到 n-1 之间的整数表示。
     * 所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对。
     * 但是，这样的配对情况可能会是其中部分朋友感到不开心。在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，x 就会不开心：
     * 1、x 与 u 的亲近程度胜过 x 与 y，
     * 2、u 与 x 的亲近程度胜过 u 与 v
     *
     * @param n 总人数 (2 <= n <= 500)
     * @param preferences 亲密列表
     * @param pairs 配对 (每位朋友都 恰好 被包含在一对中)
     * @return 不开心的朋友数目
     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        //定义rank[i][j]，表示在i心目中j的地位
        int[][] rank = new int[n][n];
        for(int i=0; i<preferences.length; i++){
            for(int j=0; j<preferences[i].length; j++){
                rank[i][preferences[i][j]] = j;
            }
        }
        //保存不开心的朋友，避免重复计数
        int unhappy = 0;
        Set<Integer> set = new HashSet<>();
        //枚举所有的(x,y)(u,v)组合
        for(int i=0; i<pairs.length; i++){
            for(int j=0; j<pairs.length; j++){
                if(i==j) continue;
                //判断x是否开心
                if((rank[pairs[i][0]][pairs[j][0]] < rank[pairs[i][0]][pairs[i][1]]
                        && rank[pairs[j][0]][pairs[i][0]] < rank[pairs[j][0]][pairs[j][1]])
                    || (rank[pairs[i][0]][pairs[j][1]] < rank[pairs[i][0]][pairs[i][1]]
                        && rank[pairs[j][1]][pairs[i][0]] < rank[pairs[j][1]][pairs[j][0]])){
                    if( ! set.contains(pairs[i][0])){
                        unhappy++;
                        set.add(pairs[i][0]);
                    }
                }
                //判断y是否开心
                if((rank[pairs[i][1]][pairs[j][1]] < rank[pairs[i][1]][pairs[i][0]]
                        && rank[pairs[j][1]][pairs[i][1]] < rank[pairs[j][1]][pairs[j][0]])
                    || (rank[pairs[i][1]][pairs[j][0]] < rank[pairs[i][1]][pairs[i][0]]
                        && rank[pairs[j][0]][pairs[i][1]] < rank[pairs[j][0]][pairs[j][1]])){
                    if( ! set.contains(pairs[i][1])){
                        unhappy++;
                        set.add(pairs[i][1]);
                    }
                }
            }
        }
        return unhappy;
    }

    /**
     * 576. 出界的路径数
     * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。
     * 你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
     * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
     * 因为答案可能非常大，返回对 10^9 + 7 取余 后的结果。
     *
     * @param m 行 (1 <= m <= 50)
     * @param n 列 (1 <= n <= 50)
     * @param maxMove 最大移动次数 (0 <= maxMove <= 50)
     * @param startRow 起始行
     * @param startColumn 起始列
     * @return 出界的路径数
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

        /*//1、广度优先遍历：时间复杂度O(4 ^ maxMove)
        final int MOD = 1000000007;
        int count = 0;
        int[] row = {-1,0,1,0};
        int[] col = {0,-1,0,1};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow,startColumn});
        while(maxMove>0){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] cur = queue.poll();
                assert cur != null;
                for(int j=0; j<row.length; j++){
                    int nextRow = cur[0]+row[j];
                    int nextCol = cur[1]+col[j];
                    if(nextRow<0 || nextRow>=m || nextCol<0 || nextCol>=n){
                        count = (count + 1) % MOD;
                    }else{
                        queue.offer(new int[]{nextRow,nextCol});
                    }
                }
            }
            maxMove--;
        }
        return count;*/

        //上述做法复杂度太高，执行超时

        //2、动态规划：时间复杂度O(m * n * maxMove)
        final int MOD = 1000000007;
        int count = 0;
        //定义三维数组，dp[i][j][k]表示经过i次移动后，终点位于(j,k)的路径数
        int[][][] dp = new int[maxMove+1][m][n];
        //边界
        dp[0][startRow][startColumn] = 1;
        //移动方向
        int[] row = new int[]{-1,0,1,0};
        int[] col = new int[]{0,-1,0,1};
        //三层遍历
        for(int i=1; i<=maxMove; i++){
            for(int j=0; j<m; j++){
                for(int k=0; k<n; k++){
                    for(int a=0; a<row.length; a++){
                        int nextRow = j+row[a];
                        int nextCol = k+col[a];
                        if(nextRow<0 || nextRow==m || nextCol<0 || nextCol==n){
                            count = (count + dp[i-1][j][k]) % MOD;
                        }else{
                            dp[i][nextRow][nextCol] = (dp[i][nextRow][nextCol] + dp[i-1][j][k]) % MOD;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 526. 优美的排列
     * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
     * 1、第 i 位的数字能被 i 整除
     * 2、i 能被第 i 位上的数字整除
     * 现在给定一个整数 N，请问可以构造多少个优美的排列？
     *
     * @param n 整数 (0<=n<=15)
     * @return 优美排列的数量
     */
    public int countArrangement(int n) {
        //递归回溯：时间复杂度O(N!)，通过剪枝降低复杂度
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = i+1;
        }
        countArrangement(arr, 0);
        return arrangementCount;
    }
    //优美排列的数量
    private int arrangementCount = 0;
    //递归回溯：枚举所有的排列情况
    private void countArrangement(int[] arr, int begin){
        if(begin==arr.length){
            arrangementCount++;
        }
        for(int i=begin; i<arr.length; i++){
            //满足优美的条件，则交换元素，然后递归，回溯时将元素复原
            if(arr[i]%(begin+1)==0 || (begin+1)%arr[i]==0){
                swap(arr, begin, i);
                countArrangement(arr, begin+1);
                swap(arr, begin, i);
            }
        }
    }

    /**
     * 443. 压缩字符串
     * 给你一个字符数组 chars ，请使用下述算法压缩：
     * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
     * 如果这一组长度为 1 ，则将字符追加到 s 中。
     * 否则，需要向 s 追加字符，后跟这一组的长度。
     * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
     * 请在 修改完输入数组后 ，返回该数组的新长度。
     * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
     *
     * @param chars 字符数组
     * @return 压缩后的长度
     */
    public int compress(char[] chars) {
        //双指针：时间复杂度O(N)
        int ans = 0;
        //左指针标识可写位置，右指针用于扫描字符数组
        int left = 0, right = 1;
        //保留前一个字符及其数量
        char pre = chars[0];
        int count = 1;
        while(right <= chars.length){
            if(right == chars.length || chars[right]!=pre){
                //字符
                chars[left++] = pre;
                //数量
                if(count==1){
                    ans += 1;
                }else{
                    String countStr = String.valueOf(count);
                    count = 1;
                    for(int i=0; i<countStr.length(); i++){
                        chars[left++] = countStr.charAt(i);
                    }
                    ans += countStr.length() + 1;
                }
                //重置前一个字符
                if(right != chars.length){
                    pre = chars[right];
                }
            }else{
                //相同字符，数量增一
                count++;
            }
            right++;
        }
        return ans;
    }

}
