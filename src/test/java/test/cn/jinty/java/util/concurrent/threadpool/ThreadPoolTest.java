package test.cn.jinty.java.util.concurrent.threadpool;

import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程与线程池 - 测试
 *
 * @author Jinty
 * @date 2021/5/25
 **/
public class ThreadPoolTest {

    /**
     * 普通多线程
     */
    @Test
    public void test1() {
        Thread[] threads = new Thread[5];
        System.out.println("主线程开始");
        //循环建立多个线程
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程" + finalI + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            //开启线程
            threads[i].start();
        }
        //主线程等待所有子线程
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("主线程结束");
    }

    /**
     * 基于线程池的多线程 - 固定大小线程池(线程数固定)
     */
    @Test
    public void test2() {
        //获取计算机CPU核数
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("当前计算机CPU为" + processors + "核");
        //CPU核数的两倍作为线程数
        int threadNum = processors * 2;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        System.out.println("创建线程池: 池大小为" + threadNum);
        //任务加入线程池执行
        for (int i = 0; i < 16; i++) {
            fixedThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " : do something")
            );
        }
        //关闭线程池
        fixedThreadPool.shutdown();
        while (true) {
            if (fixedThreadPool.isTerminated()) {
                System.out.println("关闭线程池");
                return;
            }
        }
    }

    /**
     * 基于线程池的多线程 - 缓存线程池(线程数范围[0,Integer.MAX_VALUE])
     */
    @Test
    public void test3() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("创建线程池");
        for (int i = 0; i < 32; i++) {
            cachedThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " : do something")
            );
        }
        cachedThreadPool.shutdown();
        while (true) {
            if (cachedThreadPool.isTerminated()) {
                System.out.println("关闭线程池");
                return;
            }
        }
    }

    /**
     * 基于线程池的多线程 - 单例线程池(线程数恒为1)
     */
    @Test
    public void test4() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        System.out.println("创建线程池");
        for (int i = 0; i < 10; i++) {
            singleThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " : do something")
            );
        }
        singleThreadPool.shutdown();
        while (true) {
            if (singleThreadPool.isTerminated()) {
                System.out.println("关闭线程池");
                return;
            }
        }
    }

    /**
     * 基于线程池的多线程 - 延时线程池(线程数范围[value,Integer.MAX_VALUE])
     */
    @Test
    public void test5() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("创建线程池");
        ScheduledFuture<?> sf = scheduledThreadPool.schedule(
                () -> System.out.println(Thread.currentThread().getName() + " : do something"),
                1L,
                TimeUnit.SECONDS
        );
        try {
            System.out.println(sf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        scheduledThreadPool.shutdown();
        while (true) {
            if (scheduledThreadPool.isTerminated()) {
                System.out.println("关闭线程池");
                return;
            }
        }
    }

    /**
     * 基于线程池的多线程 - 自定义线程池
     */
    @Test
    public void test6() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 4, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4)
        );
        System.out.println("创建线程池");
        for (int i = 0; i < 20; i++) {
            try {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " : do something");
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                System.out.println("关闭线程池");
                return;
            }
        }
    }

    /**
     * 串行与并行共存，将任务分组，组与组之间串行，组内任务并行
     */
    @Test
    public void test7() {
        // 创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                4, 8, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8)
        );
        System.out.println("创建线程池");
        // 创建任务并分组
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final int j = i;
            tasks.add(() -> {
                System.out.printf("线程[%s] 任务编号[%d] 并发执行\n", Thread.currentThread().getName(), j);
                Thread.sleep(1000L);
                return true;
            });
        }
        List<List<Callable<Boolean>>> taskGroups = ListUtil.splitByNum(tasks, 10);
        // 组与组之间串行，组内任务并行
        for (int i = 0; i < taskGroups.size(); i++) {
            System.out.printf("任务组号[%d] 串行执行\n", i);
            List<Callable<Boolean>> taskGroup = taskGroups.get(i);
            List<Future<Boolean>> results = new ArrayList<>();
            for (Callable<Boolean> task : taskGroup) {
                results.add(threadPool.submit(task));
            }
            for (Future<Boolean> result : results) {
                try {
                    result.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        // 关闭线程池
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                System.out.println("关闭线程池");
                return;
            }
        }
    }

}
