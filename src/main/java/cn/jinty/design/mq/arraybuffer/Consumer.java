package cn.jinty.design.mq.arraybuffer;

import cn.jinty.design.mq.Message;

/**
 * 消费者
 *
 * @author jinty
 * @date 2021/5/14
 **/
public class Consumer implements Runnable{

    private Buffer buffer;

    public Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try{
                Message message = buffer.take();
                System.out.println(
                        "["+Thread.currentThread().getName()+
                                "]消费者消费消息: "+message
                );
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
