package test.cn.jinty.java.util.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 计算任务 - 计算begin~end的和
 *
 * @author Jinty
 * @date 2021/7/9
 **/
public class ComputeTask extends RecursiveTask<Long> {

    //起始数
    private final long begin;
    //终止数
    private final long end;
    //最小计算区间
    private static final long minLen = 1000L;

    public ComputeTask(long begin, long end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long result = 0L;
        //不可继续切分
        if (end - begin <= minLen) {
            for (long i = begin; i <= end; i++) {
                result += i;
            }
            return result;
        }
        //可继续切分
        long mid = begin + (end - begin) / 2;
        //二分
        ComputeTask left = new ComputeTask(begin, mid);
        ComputeTask right = new ComputeTask(mid + 1, end);
        //分治递归
        left.fork();
        right.fork();
        //结果归并
        return left.join() + right.join();
    }

}
