package cn.jinty.struct.line;

/**
 * 阻塞队列 - 在队列上增加阻塞功能
 *
 * @author Jinty
 * @date 2022/7/6
 **/
public class BlockingQueue<T> {

    //队列
    private final Queue<T> queue;

    //构造器(设置最大容量)
    public BlockingQueue(int maxCapacity) {
        queue = new Queue<>(maxCapacity, maxCapacity);
    }

    //从队尾加入一个元素，当队列满时阻塞等待
    public synchronized void put(T element) throws Exception {
        while (queue.isFull()) {
            this.wait();
        }
        queue.offer(element);
        this.notifyAll();
    }

    //从队头取出一个元素，当队列空时阻塞等待
    public synchronized T take() throws Exception {
        while (queue.isEmpty()) {
            this.wait();
        }
        T element = queue.poll();
        this.notifyAll();
        return element;
    }

}
