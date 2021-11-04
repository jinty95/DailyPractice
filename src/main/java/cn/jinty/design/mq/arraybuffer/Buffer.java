package cn.jinty.design.mq.arraybuffer;

import cn.jinty.design.mq.Message;

/**
 * 缓冲区 - 存储消息的容器
 *
 * @author Jinty
 * @date 2021/5/14
 **/
public class Buffer {

    //存储消息的数组容器
    private Message[] messages;
    //指向下一个可用空间
    private int pointer = 0;

    //初始化容器，指定容量
    public Buffer(int size) {
        this.messages = new Message[size];
    }

    //将消息存入容器
    public synchronized void put(Message message) {
        try {
            //容器满则阻塞等待，唤醒后继续判断容器是否已满，只有容器不满可以向下执行
            while (pointer == this.messages.length) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        messages[pointer++] = message;
        this.notifyAll();
    }

    //从容器中取出消息
    public synchronized Message take() {
        try {
            //容器空则阻塞等待，唤醒后继续判断容器是否已空，只有容器不空可以向下执行
            while (pointer <= 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = messages[0];
        //取出第一个消息后，后续消息前移一位，指针减一
        for (int i = 1; i < pointer; i++) {
            messages[i - 1] = messages[i];
        }
        pointer -= 1;
        this.notifyAll();
        return message;
    }

}
