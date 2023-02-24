package test.cn.jinty.java.util;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务 - 测试
 * <p>
 * Timer是JDK自带的定时任务调度器，原理是用一个小根堆实现的队列来存放任务，任务按照执行时间升序排序。
 * 再用一个线程不断地从队列头获取任务，获取的任务就是最近该执行的任务，当它的执行时间小于等于当前时间时，执行任务。
 *
 * @author Jinty
 * @date 2023/2/27
 **/
public class TimerTest {

    public static class MyTimerTask extends TimerTask {
        long period;

        public MyTimerTask(long period) {
            this.period = period;
        }

        public long getNextExecutionTime() {
            return (period < 0) ? scheduledExecutionTime() - period : scheduledExecutionTime() + period;
        }

        @Override
        public void run() {
            try {
                Thread t = Thread.currentThread();
                System.out.printf("执行任务，当前线程=%s，当前时间=%s，下一次开始时间=%s%n",
                        t.getName(), System.currentTimeMillis(), getNextExecutionTime());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 固定延迟：nextExecutionTime = currentTime + period
    @Test
    public void testSchedule() {
        Timer timer = new Timer();
        System.out.println("创建定时任务执行器");
        timer.schedule(new MyTimerTask(1000), 1000, 1000);
        while (true) {
        }
    }

    // 固定频率：nextExecutionTime = nextExecutionTime + period
    @Test
    public void testScheduleAtFixedRate() {
        Timer timer = new Timer();
        System.out.println("创建定时任务执行器");
        timer.scheduleAtFixedRate(new MyTimerTask(1000), 1000, 1000);
        while (true) {
        }
    }

    // 注意：如果一个任务的执行时间超过了执行周期，那么无论使用固定延迟还是固定速率，它都会延迟整个执行链。

}
