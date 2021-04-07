package cn.jinty.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description 消息生产者
 * @Author jinty
 * @Date 2019/9/9.
 */
public class MyProducer {
    //消息队列服务器地址
    private static String url = "tcp://localhost:61616";
    //队列名
    private static String queueName = "queueTest";
    //主题名
    private static String topicName = "topicTest";

    public static void main(String[]args)throws JMSException {

        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
        //2.创建Connection
        Connection connection=connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建会话
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建一个目标（队列模式和主题模式）
        Destination queueDestination=session.createQueue(queueName);
        Destination topicDestination=session.createTopic(topicName);
        //6.创建一个生产者（队列模式和主题模式）
        MessageProducer queueProducer=session.createProducer(queueDestination);
        MessageProducer topicProducer=session.createProducer(topicDestination);
        //队列模式
        for(int i=0;i<10;i++){
            //7.创建消息
            TextMessage textMessage=session.createTextMessage("队列-"+i);
            //8.发布消息
            queueProducer.send(textMessage);
            System.out.println("队列发布消息："+textMessage.getText());
        }
        //主题模式
        for(int i=0;i<10;i++){
            //7.创建消息
            TextMessage textMessage=session.createTextMessage("主题-"+i);
            //8.发布消息
            topicProducer.send(textMessage);
            System.out.println("主题发布消息："+textMessage.getText());
        }
        //9.关闭连接
        connection.close();
    }
}
