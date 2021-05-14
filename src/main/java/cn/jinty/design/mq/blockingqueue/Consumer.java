package cn.jinty.design.mq.blockingqueue;

import cn.jinty.design.mq.Message;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 *
 * @author jinty
 * @date 2021/5/14
 **/
public class Consumer implements Runnable{

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try{
                Message message = queue.take();
                System.out.println(
                        "["+Thread.currentThread().getName()+
                        "]消费者消费消息: "+message
                );
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
