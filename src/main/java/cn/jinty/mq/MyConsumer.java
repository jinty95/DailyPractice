package cn.jinty.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

/**
 * @Description 消息消费者
 * @Author jinty
 * @Date 2019/9/9.
 */
public class MyConsumer {

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
        //6.创建一个消费者（队列模式和主题模式）
        MessageConsumer consumer1=session.createConsumer(queueDestination);
        MessageConsumer consumer2=session.createConsumer(topicDestination);
        //7.创建一个监听器，注册到消费者中
        //队列模式
        consumer1.setMessageListener(new MessageListener(){
            public void onMessage(Message message){
                TextMessage textMessage=(TextMessage)message;
                try{
                    System.out.println("队列订阅消息："+textMessage.getText());
                }catch(JMSException e){
                    e.printStackTrace();
                }
            }
        });
        //主题模式
        consumer2.setMessageListener(new MessageListener(){
            public void onMessage(Message message){
                TextMessage textMessage=(TextMessage)message;
                try{
                    System.out.println("主题订阅消息："+textMessage.getText());
                }catch(JMSException e){
                    e.printStackTrace();
                }
            }
        });
        //8.关闭连接，监听状态下不能关闭连接
        //connection.close();
    }
}
