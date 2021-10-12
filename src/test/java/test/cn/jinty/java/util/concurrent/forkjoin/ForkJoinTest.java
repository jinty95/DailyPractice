package test.cn.jinty.java.util.concurrent.forkjoin;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 分治归并线程池 - 测试
 *
 * @author jinty
 * @date 2021/7/9
 **/
public class ForkJoinTest {

    @Test
    public void test() {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Long> future = pool.submit(new ComputeTask(1, 100000));
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
