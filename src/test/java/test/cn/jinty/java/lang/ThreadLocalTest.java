package test.cn.jinty.java.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 线程局部变量
 *
 * @author Jinty
 * @date 2023/5/24
 **/
public class ThreadLocalTest {

    private static final Random RANDOM = new Random();

    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    @Test
    public void test() {
        List<Thread> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(new Thread(() -> {
                try {
                    set();
                    get();
                } finally {
                    remove();
                }
                get();
            }));
        }
        for (Thread t : list) {
            t.start();
        }
        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void set() {
        int val = RANDOM.nextInt(100);
        THREAD_LOCAL.set(val);
        System.out.printf("线程局部变量[设置]：currentThread=%s, val=%s%n", Thread.currentThread(), val);
    }

    private void get() {
        Integer val = THREAD_LOCAL.get();
        System.out.printf("线程局部变量[获取]：currentThread=%s, val=%s%n", Thread.currentThread(), val);
    }

    private void remove() {
        // 使用完手动调用remove，避免内存泄漏
        THREAD_LOCAL.remove();
        System.out.printf("线程局部变量[移除]：currentThread=%s%n", Thread.currentThread());
    }

}
