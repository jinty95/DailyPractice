package test.cn.jinty.design.mq.blockingqueue;

import cn.jinty.design.mq.Message;
import cn.jinty.design.mq.blockingqueue.Consumer;
import cn.jinty.design.mq.blockingqueue.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试
 *
 * @author Jinty
 * @date 2021/5/14
 **/
public class Test {

    public static void main(String[] args) {
        //消息队列
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        //生产者
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer(queue)).start();
        }
        //消费者
        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

}
