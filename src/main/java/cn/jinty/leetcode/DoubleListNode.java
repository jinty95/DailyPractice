package cn.jinty.leetcode;

/**
 * 双向链表节点类
 *
 * @author jinty
 * @date 2021/3/15
 **/
public class DoubleListNode {

    public int value;
    public DoubleListNode pre;
    public DoubleListNode next;

    public DoubleListNode(){}

    public DoubleListNode(int value){
        this.value = value;
    }

    public DoubleListNode(int value, DoubleListNode pre, DoubleListNode next){
        this.value = value;
        this.pre = pre;
        this.next = next;
    }

}
