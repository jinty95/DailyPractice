package cn.jinty.util;

import cn.jinty.leetcode.linear.ListNode;

/**
 * 链表工具类
 *
 * @author Jinty
 * @date 2020/4/8.
 */
public final class ListNodeUtil {

    //数组构建链表
    public static ListNode fromArray(int[] arr){
        ListNode head = new ListNode();
        if(arr!=null && arr.length>0){
            ListNode tmp = head;
            for(int i:arr){
                tmp.next = new ListNode(i);
                tmp = tmp.next;
            }
        }
        return head.next;
    }

}
