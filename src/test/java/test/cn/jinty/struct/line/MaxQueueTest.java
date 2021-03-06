package test.cn.jinty.struct.line;

import cn.jinty.struct.line.MaxQueue;
import org.junit.Test;

/**
 * 最大值队列 - 测试
 *
 * @author Jinty
 * @date 2021/6/3
 **/
public class MaxQueueTest {

    @Test
    public void test() {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.pushBack(1);
        maxQueue.pushBack(2);
        maxQueue.pushBack(3);
        System.out.println(maxQueue.queue);
        System.out.println(maxQueue.maxQueue);
        System.out.println(maxQueue.maxValue());
        maxQueue.popFront();
        System.out.println(maxQueue.queue);
        System.out.println(maxQueue.maxQueue);
        System.out.println(maxQueue.maxValue());
    }

}
