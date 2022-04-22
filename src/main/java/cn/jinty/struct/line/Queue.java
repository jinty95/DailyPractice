package cn.jinty.struct.line;

/**
 * 队列 - 基于数组(环形数组)实现
 *
 * @author Jinty
 * @date 2021/7/14
 **/
public class Queue<T> {

    //默认初始容量
    private static final int DEFAULT_INIT_CAPACITY = 2;

    //最大容量
    private static final int MAX_CAPACITY = 1 << 30;

    //默认增量，0表示倍增
    private static final int DEFAULT_INCREMENT = 0;

    //增量
    private final int increment;

    //元素数量
    private int size = 0;

    //数组
    private Object[] data;

    //队头指针
    private int head = 0;

    //队尾指针
    private int tail = 0;

    //构造器
    public Queue() {
        this(DEFAULT_INIT_CAPACITY);
    }

    public Queue(int initCapacity) {
        this(initCapacity, DEFAULT_INCREMENT);
    }

    public Queue(int initCapacity, int increment) {
        this.data = new Object[initCapacity];
        this.increment = increment;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        //队头队尾重合表示空
        return head == tail;
    }

    //判断队列是否已满
    public boolean isFull() {
        //队尾下一个为队头表示满，所以数组可用大小为真实大小减1
        return next(tail) == head;
    }

    //队列元素数量
    public int size() {
        return size;
    }

    //队列容器大小
    public int capacity() {
        return data.length;
    }

    //入队
    public void offer(T element) {
        if (isFull()) {
            expand();
        }
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("队列已满，无法添加元素");
        }
        data[tail] = element;
        tail = next(tail);
        size++;
    }

    //出队
    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty()) return null;
        T element = (T) data[head];
        head = next(head);
        size--;
        return element;
    }

    //计算下一个索引
    private int next(int index) {
        return (index + 1) % data.length;
    }

    //扩容
    private void expand() {
        //到达最大容量，不再扩容
        if (data.length == MAX_CAPACITY) return;
        //新容量
        int newCapacity = increment == 0 ? data.length * 2 : data.length + increment;
        newCapacity = Math.min(newCapacity, MAX_CAPACITY);
        //复制
        Object[] newData = new Object[newCapacity];
        int newHead = 0, newTail = 0;
        while (!isEmpty()) {
            newData[newTail++] = data[head];
            head = next(head);
        }
        data = newData;
        head = newHead;
        tail = newTail;
    }

}
