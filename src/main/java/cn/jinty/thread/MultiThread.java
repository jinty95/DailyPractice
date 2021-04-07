package cn.jinty.thread;

import java.util.concurrent.*;

/**
 * 多线程
 *
 * @author jinty
 * @date 2020/10/20
 **/
public class MultiThread {

    public static void main(String[] args) throws Exception{
        multiThreadDemo(10);
    }

    /**
     * 多线程Demo
     * @param threadNum 线程数
     */
    private static void multiThreadDemo(int threadNum){
        Thread[] threads = new Thread[threadNum];
        System.out.println("主线程开始");
        //循环建立多个线程
        for(int i=0;i<threads.length;i++){
            int finalI = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1000);
                        System.out.println("线程"+ finalI +"执行");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            //开启线程
            threads[i].start();
        }
        //等待所有子线程
        for(Thread thread:threads){
            try{
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("主线程结束");
    }

    /**
     * 线程池Demo1 - 固定大小的线程池
     */
    private static void threadPoolDemo1(){
        // 获取计算机有几个核
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("当前计算机CPU为"+processors+"核");
        // 第一种线程池:固定大小的线程池,可以为每个CPU核绑定一定数量的线程数
        int threadNum = processors * 2;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        System.out.println("建立固定线程个数的线程池: 池大小为"+threadNum);
        //加入线程池并执行
        for (int i = 0; i < 4; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        fixedThreadPool.shutdown();
    }

    /**
     * 线程池Demo2 - 缓存线程池，无上限
     */
    private static void threadPoolDemo2(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        cachedThreadPool.shutdown();
    }

    /**
     * 线程池Demo3 - 单一线程池,永远会维护存在且仅存在一个线程
     */
    private static void threadPoolDemo3(){
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + j);
                }
            });
        }
        singleThreadPool.shutdown();
    }

    /**
     * 线程池Demo4 - 固定个数的线程池，相比于第一个固定个数的线程池，可以执行延时任务
     */
    private static void threadPoolDemo4(){
        // scheduledThreadPool.submit(); 执行带有返回值的任务
        // scheduledThreadPool.schedule() 用来执行延时任务.
        // 固定个数的线程池，可以执行延时任务，也可以执行带有返回值的任务。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("hello");
                return Thread.currentThread().getName();
            }
        });
        scheduledThreadPool.submit(ft);

        // 通过FutureTask对象获得返回值.
        try {
            String result = ft.get();
            System.out.println("result : " + result);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 执行延时任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : bomb!");
            }
        }, 3L, TimeUnit.SECONDS);

    }

}
