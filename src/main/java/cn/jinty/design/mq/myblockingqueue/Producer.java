package cn.jinty.design.mq.myblockingqueue;

import cn.jinty.design.mq.Message;
import cn.jinty.struct.line.BlockingQueue;

import java.util.Random;

/**
 * 生产者
 *
 * @author Jinty
 * @date 2021/5/14
 **/
public class Producer implements Runnable {

    private final BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Message message = new Message(Message.number.getAndIncrement(), "消息");
                queue.put(message);
                System.out.println("[" + Thread.currentThread().getName() + "]生产者生产消息: " + message);
                Thread.sleep(random.nextInt(1000) + 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
