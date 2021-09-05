package test.cn.jinty.struct.linear;

import cn.jinty.struct.linear.Queue;
import org.junit.Test;

/**
 * 队列 - 测试
 *
 * @author jinty
 * @date 2021/7/14
 **/
public class QueueTest {

    @Test
    public void test(){
        Queue<Integer> queue = new Queue<>();
        System.out.println("队列为空："+queue.isEmpty());
        for(int i=1;i<=100;i++){
            queue.offer(i);
            System.out.println("元素入队："+i + " 容器大小："+queue.capacity());
        }
        System.out.println("队列已满："+queue.isFull());
        System.out.println("队列元素数量："+queue.size());
        while( ! queue.isEmpty()){
            System.out.println("元素出队："+queue.poll());
        }
        System.out.println("队列元素数量："+queue.size());
        System.out.println("队列为空："+queue.isEmpty());
    }

}
