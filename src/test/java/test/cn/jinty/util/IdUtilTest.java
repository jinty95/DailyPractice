package test.cn.jinty.util;

import cn.jinty.util.IdUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * ID - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/12/9
 **/
public class IdUtilTest {

    @Test
    public void testUuid() {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtil.uuid());
        }
    }

    @Test
    public void testNumber1() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" +IdUtil.number());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" +IdUtil.number());
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNumber2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtil.number("A"));
            System.out.println(IdUtil.number("B"));
            System.out.println(IdUtil.number("C"));
        }
    }

    @Test
    public void testNumber3() {
        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtil.number("A", 6));
            System.out.println(IdUtil.number("B", 4));
            System.out.println(IdUtil.number("C", 2));
        }
    }

    @Test
    public void testTimestamp() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" +IdUtil.timestamp());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" +IdUtil.timestamp());
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeWithRandom() {
        Set<String> set = new HashSet<>();
        int repeatCount = 0;
        for (int i = 0; i < 1000; i++) {
            String id = IdUtil.timeWithRandom();
            System.out.println(id);
            if (!set.add(id)) {
                repeatCount++;
                System.out.printf("生成重复的ID [%s] %n", id);
            }
        }
        System.out.printf("生成重复的ID一共有 %d 个 %n", repeatCount);
    }

}
