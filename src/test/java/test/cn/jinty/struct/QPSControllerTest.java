package test.cn.jinty.struct;

import cn.jinty.struct.QPSController;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * QPS控制器 - 测试
 *
 * @author Jinty
 * @date 2022/6/28
 **/
public class QPSControllerTest {

    @Test
    public void test() {
        QPSController qpsController = new QPSController(100, TimeUnit.SECONDS.toMillis(1));
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            final int j = i;
            Thread t = new Thread(() -> {
                if (qpsController.next()) {
                    System.out.printf("请求%s正常执行\n", j);
                } else {
                    long totalWaitTime = 0L;
                    while (true) {
                        try {
                            long waitTime = qpsController.waitTime();
                            if (waitTime > 0) {
                                totalWaitTime += waitTime;
                                Thread.sleep(waitTime);
                            }
                            if (qpsController.next()) {
                                System.out.printf("请求%s延迟%s毫秒后正常执行\n", j, totalWaitTime);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.printf("请求%s执行异常：%s\n", j, e.getMessage());
                            break;
                        }
                    }
                }
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
