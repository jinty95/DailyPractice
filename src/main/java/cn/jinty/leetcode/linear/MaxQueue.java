package cn.jinty.leetcode.linear;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最大值队列
 *
 * @author jinty
 * @date 2021/3/26
 **/
class MaxQueue {

    //值队列
    Deque<Integer> queue;
    //最大值队列(单调递减队列)
    Deque<Integer> monotoneQueue;

    public MaxQueue() {
        queue = new LinkedList<>();
        monotoneQueue = new LinkedList<>();
    }

    //O(1)
    public int max_value() {
        if(monotoneQueue.isEmpty()) return -1;
        return monotoneQueue.peekFirst();
    }

    //O(N) 如何优化到O(1)呢？
    public void push_back(int value) {
        queue.offerLast(value);
        int popCount = 1;
        while(!monotoneQueue.isEmpty() && monotoneQueue.peekLast()<value){
            monotoneQueue.pollLast();
            popCount++;
        }
        while(popCount>0){
            monotoneQueue.offerLast(value);
            popCount--;
        }
    }

    //O(1)
    public int pop_front() {
        if(queue.isEmpty()) return -1;
        monotoneQueue.pollFirst();
        return queue.pollFirst();
    }

}
