package cn.jinty.java.util.concurrent.delayqueue;

import cn.jinty.util.DateUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列 - 测试
 *
 * @author jinty
 * @date 2021/5/25
 **/
public class DelayQueueTest {

    @Test
    public void test(){
        DelayQueue<Order> queue = new DelayQueue<>();
        //创建三个订单，分别在5秒、10秒、15秒后取消
        Order order1 = new Order(1,5, TimeUnit.SECONDS);
        Order order2 = new Order(2,10, TimeUnit.SECONDS);
        Order order3 = new Order(3,15, TimeUnit.SECONDS);
        //存入延时队列
        queue.put(order1);
        queue.put(order2);
        queue.put(order3);
        //延时取消
        try{
            while( ! queue.isEmpty()){
                //阻塞等待
                Order order = queue.take();
                System.out.println(order + " is cancel at " + DateUtil.format(new Date()));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
