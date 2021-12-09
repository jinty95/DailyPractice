package test.cn.jinty.util;

import cn.jinty.util.IdUtil;
import org.junit.Test;

/**
 * ID - 工具类 - 测试
 *
 * @author jintai.wang
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
    public void testNumber() {
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
    public void testTimeAndRandom() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1L);
                System.out.println(IdUtil.timeAndRandom());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
