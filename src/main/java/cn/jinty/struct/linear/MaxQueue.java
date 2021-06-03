package cn.jinty.struct.linear;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最大值队列
 *
 * @author jinty
 * @date 2021/3/26
 **/
public class MaxQueue {

    //原值队列
    public Deque<Integer> queue;
    //最大值队列(单调递减队列)
    public Deque<Integer> maxQueue;

    //构造器
    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    //获取队列的最大值，时间复杂度O(1)
    public int maxValue() {
        if(maxQueue.isEmpty()) return -1;
        return maxQueue.peekFirst();
    }

    //从队列尾部插入一个元素
    //不满足单调递减的元素出最大值队列后，不需要再次入队列，插入N个元素，本方法最多操作N次，平均时间复杂度O(1)
    public void pushBack(int value) {
        queue.offerLast(value);
        while(!maxQueue.isEmpty() && maxQueue.peekLast()<value){
            maxQueue.pollLast();
        }
        maxQueue.offerLast(value);
    }

    //从队列头部取出一个元素
    //当取出的元素等于最大值队列的队头元素时，最大值队列的队头出队列，时间复杂度O(1)
    public int popFront() {
        if(queue.isEmpty()) return -1;
        int val = queue.pollFirst();
        if(val==maxQueue.peekFirst()){
            maxQueue.pollFirst();
        }
        return val;
    }

}
