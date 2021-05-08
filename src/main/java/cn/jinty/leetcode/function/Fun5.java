package cn.jinty.leetcode.function;

import java.util.Arrays;

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

}
