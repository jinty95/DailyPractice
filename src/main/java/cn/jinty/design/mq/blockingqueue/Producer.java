package cn.jinty.design.mq.blockingqueue;

import cn.jinty.design.mq.Message;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 *
 * @author jinty
 * @date 2021/5/14
 **/
public class Producer implements Runnable{

    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try{
                Message message = new Message(Message.number.getAndIncrement(),"消息");
                queue.put(message);
                System.out.println(
                        "["+Thread.currentThread().getName()+
                                "]生产者生产消息: "+message
                );
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
