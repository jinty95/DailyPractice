package test.cn.jinty.design.mq.myblockingqueue;

import cn.jinty.design.mq.Message;
import cn.jinty.design.mq.myblockingqueue.Consumer;
import cn.jinty.design.mq.myblockingqueue.Producer;
import cn.jinty.struct.line.BlockingQueue;

/**
 * 测试
 *
 * @author Jinty
 * @date 2021/5/14
 **/
public class Test {

    public static void main(String[] args) {
        //消息队列
        BlockingQueue<Message> queue = new BlockingQueue<>(10);
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
