package cn.jinty.design.mq.arraybuffer;

import cn.jinty.design.mq.Message;

/**
 * 生产者
 *
 * @author Jinty
 * @date 2021/5/14
 **/
public class Producer implements Runnable {

    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message(Message.number.getAndIncrement(), "消息");
                buffer.put(message);
                System.out.println(
                        "[" + Thread.currentThread().getName() +
                                "]生产者生产消息: " + message
                );
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
