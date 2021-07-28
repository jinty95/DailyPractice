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

}
