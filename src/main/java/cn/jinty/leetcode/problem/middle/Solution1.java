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
                int number = next.poll();
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

}
