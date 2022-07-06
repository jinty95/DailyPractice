package cn.jinty.design.mq.myblockingqueue;

import cn.jinty.design.mq.Message;
import cn.jinty.struct.line.BlockingQueue;

/**
 * 消费者
 *
 * @author Jinty
 * @date 2021/5/14
 **/
public class Consumer implements Runnable {

    private final BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = queue.take();
                System.out.println("[" + Thread.currentThread().getName() + "]消费者消费消息: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
