package cn.jinty.leetcode.function;

import cn.jinty.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode算法题
 *
 * @author jinty
 * @date 2021/5/8
 **/
public class Fun5 {

    /**
     * 1723. 完成所有工作的最短时间
     * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
     * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
     * 请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
     *
     * @param jobs 每项工作需花费的时间
     * @param k 工人数
     * @return 完成所有工作的最短时间
     */
    public int minimumTimeRequired(int[] jobs, int k) {

        //二分查找 + 回溯算法

        //倒序排序，因为先分配大工作能更快找到一个可行的分配方案
        Arrays.sort(jobs);
        int low = 0, high = jobs.length-1;
        while(low<high){
            jobs[low] ^= jobs[high];
            jobs[high] ^= jobs[low];
            jobs[low] ^= jobs[high];
            low++;
            high--;
        }
        //求jobs的最大值及总和
        int max = jobs[0];
        int sum = jobs[0];
        for(int job : jobs){
            max = Math.max(max,job);
            sum += job;
        }
        //当k>=jobs.length时，ans=max。当k=1时，ans=sum。
        int left = max, right = sum;
        while(left<right){
            int mid = left + (right-left)/2;
            //判断在这个时间下k个工人能否完成工作
            if(check(jobs,k,mid)){
                //能完成工作，则缩短工时
                right = mid;
            }else{
                //不能完成工作，则增加工时
                left = mid+1;
            }
        }
        return left;

    }
    //判断在limit时间限制内k个工人能否完成工作
    private boolean check(int[] jobs,int k,int limit){
        //每个工人的负载
        int[] workerLoads = new int[k];
        //回溯
        return backTrack(jobs,workerLoads,0,limit);
    }
    //回溯算法，穷举所有分配情况
    private boolean backTrack(int[] jobs,int[] workerLoads,int jobIndex,int limit){
        if(jobIndex>=jobs.length){
            return true;
        }
        //当前工作
        int job = jobs[jobIndex];
        //分配给任意工人
        for(int i=0;i<workerLoads.length;i++){
            if(workerLoads[i]+job<=limit){
                workerLoads[i] += job;
                boolean flag = backTrack(jobs,workerLoads,jobIndex+1,limit);
                if(flag) return true;
                workerLoads[i] -= job;
            }
            //提前剪枝，如果i工人无法分配到工作，那么显然i+1工人也无法分配到工作
            if(workerLoads[i]==0){
                break;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     * 实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 数值（按顺序）可以分成以下几个部分：
     *     若干空格
     *     一个 小数 或者 整数
     *     （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     *     若干空格
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
     * @param bloomDay 开花时间
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
        for(int i=0;i<bloomDay.length;i++){
            if(bloomDay[i]<=day){
                num++;
                if(num==k){
                    m--;
                    if(m==0) return true;
                    num = 0;
                }
            }else{
                num = 0;
            }
        }
        return false;
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

    /**
     * 10. 正则表达式匹配
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     *
     * @param s 文本串
     * @param p 模式串
     * @return 是否匹配
     */
    public boolean isMatch(String s, String p) {
        /*//空串
        if(s==null || s.length()==0){
            return p == null || p.length() == 0;
        }
        if(p==null || p.length()==0) return false;
        //非空串
        int si=0, pi=0;
        while(si<s.length() && pi<p.length()){
            char c1 = s.charAt(si);
            char c2 = p.charAt(pi);
            if(c2!='.' && c2!='*'){
                if(c1!=c2){
                    if(pi<p.length()-1 && p.charAt(pi+1)=='*'){
                        pi+=2;
                    }else{
                        break;
                    }
                }
                else{
                    si++;
                    pi++;
                }
            }else{
                if(c2=='.'){
                    si++;
                    pi++;
                }else{
                    char pre = p.charAt(pi-1);
                    if(pre=='.'){
                        si++;
                        if(si==s.length()){
                            pi++;
                        }
                    }else{
                        if(c1==pre){
                            si++;
                            if(si==s.length()){
                                pi++;
                            }
                        }else{
                            pi++;
                        }
                    }
                }
            }
        }
        return si>=s.length() && pi>=p.length();*/

        //上述做法无法解决 s与p前段匹配而p仍有剩余 的问题

        //递归算法
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        return isMatch(sArr,pArr,0,0);

    }
    //判断从si及pi开始的s和p能否匹配
    private boolean isMatch(char[] s,char[] p,int si,int pi){
        if(pi==p.length){
            return si==s.length;
        }
        //当pi的后一位不是*
        if(pi==p.length-1 || p[pi+1]!='*'){
            return si!=s.length && (s[si]==p[pi] || p[pi]=='.') && isMatch(s,p,si+1,pi+1);
        }
        //当pi的后一位是*
        while(si!=s.length && p[pi+1]=='*' && (s[si]==p[pi] || p[pi]=='.')){
            //si能匹配pi，枚举匹配零个或多个
            if(isMatch(s,p,si,pi+2)){
                return true;
            }
            si++;
        }
        //si不能匹配pi，直接跳过
        return isMatch(s,p,si,pi+2);
    }

}
