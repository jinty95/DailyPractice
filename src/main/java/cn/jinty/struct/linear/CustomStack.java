package cn.jinty.struct.linear;

/**
 * 具有增量操作的栈
 *
 * @author jinty
 * @date 2021/3/3
 **/
public class CustomStack {

    int[] array;  //存储空间
    int pointer = -1;  //指针，标识栈顶

    public CustomStack(int maxSize) {
        array = new int[maxSize];
    }

    public void push(int x) {
        if(pointer>=array.length-1) return;
        array[++pointer] = x;
    }

    public int pop() {
        if(pointer==-1) return -1;
        return array[pointer--];
    }

    public void increment(int k, int val) {
        for(int i=0;i<k && i<=pointer;i++){
            array[i] += val;
        }
    }

}
