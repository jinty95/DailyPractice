package test.cn.jinty.design.mq.arraybuffer;

import cn.jinty.design.mq.arraybuffer.Buffer;
import cn.jinty.design.mq.arraybuffer.Consumer;
import cn.jinty.design.mq.arraybuffer.Producer;

/**
 * 测试
 *
 * @author jinty
 * @date 2021/5/14
 **/
public class Test {

    public static void main(String[] args) {
        //共享消息容器
        Buffer buffer = new Buffer(10);
        //生产者
        new Thread(new Producer(buffer)).start();
        new Thread(new Producer(buffer)).start();
        new Thread(new Producer(buffer)).start();
        //消费者
        new Thread(new Consumer(buffer)).start();
        new Thread(new Consumer(buffer)).start();
        new Thread(new Consumer(buffer)).start();
    }

}
