package cn.jinty.design.mq;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息
 *
 * @author jinty
 * @date 2021/5/14
 **/
public class Message {

    //消息全局唯一标号
    public static AtomicInteger number = new AtomicInteger(0);

    private int id;

    private String name;

    public Message(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
