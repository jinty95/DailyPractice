package cn.jinty.struct.linear;

import java.util.Arrays;

/**
 * 栈 - 基于数组实现
 *
 * @author jinty
 * @date 2021/6/11
 **/
public class Stack<T> {

    //存储容器
    private Object[] data;

    //初始容量，默认2
    private static final int initCapacity = 2;

    //最大容量，默认2^30
    private static final int maxCapacity = 1 << 30;

    //增量，默认0，表示倍增
    private int increment = 0;

    //元素个数
    private int size = 0;

    //指针，指向栈顶
    private int pointer = -1;

    //构造器
    public Stack() {
        data = new Object[initCapacity];
    }
    public Stack(int initCapacity){
        data = new Object[initCapacity];
    }
    public Stack(int initCapacity, int increment){
        this(initCapacity);
        this.increment = increment;
    }

    //容量
    public int capacity(){
        return data.length;
    }

    //元素个数
    public int size(){
        return size;
    }

    //是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //压入
    public void push(T element) {
        if(pointer == data.length-1){
            expand();
        }
        if(pointer == data.length-1){
            throw new ArrayIndexOutOfBoundsException("栈已满，无法添加元素");
        }
        data[++pointer] = element;
        size++;
    }

    //弹出
    @SuppressWarnings("unchecked")
    public T pop() {
        if(pointer == -1){
            return null;
        }
        size--;
        return (T) data[pointer--];
    }

    //查看栈顶
    @SuppressWarnings("unchecked")
    public T peek(){
        if(pointer == -1){
            return null;
        }
        return (T) data[pointer];
    }

    //扩容
    private void expand(){
        //到达最大容量，不再扩容
        if(data.length==maxCapacity) return;
        //新容量
        int newCapacity = increment==0 ? data.length * 2 : data.length + increment;
        if(newCapacity > maxCapacity){
            newCapacity = maxCapacity;
        }
        //复制新容器
        data = Arrays.copyOf(data, newCapacity, Object[].class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=pointer;i>=0;i--){
            sb.append(data[i]).append(", ");
        }
        return sb.substring(0,sb.length()-1);
    }

}