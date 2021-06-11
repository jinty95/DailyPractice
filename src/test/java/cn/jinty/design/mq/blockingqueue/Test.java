package cn.jinty.design.mq.blockingqueue;

import cn.jinty.design.mq.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试
 *
 * @author jinty
 * @date 2021/5/14
 **/
public class Test {

    public static void main(String[] args) {
        //共享消息容器
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        //生产者
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        //消费者
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

}
