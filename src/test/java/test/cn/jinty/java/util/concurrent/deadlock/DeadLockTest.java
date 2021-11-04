package test.cn.jinty.java.util.concurrent.deadlock;

import org.junit.Test;

/**
 * 死锁 - 测试
 *
 * @author Jinty
 * @date 2021/7/6
 **/
public class DeadLockTest {

    //对象锁1
    private static final Object lock1 = new Object();
    //对象锁2
    private static final Object lock2 = new Object();

    //由于锁嵌套及锁交替导致死锁
    @Test
    public void deadLock() {
        //线程1
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : 持有锁 [lock1]");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " : 持有锁 [lock2]");
                }
            }
        });
        //线程2
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : 持有锁 [lock2]");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " : 持有锁 [lock1]");
                }
            }
        });
        //启动线程两个子线程
        t1.start();
        t2.start();
        try {
            //主线程等待子线程
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
