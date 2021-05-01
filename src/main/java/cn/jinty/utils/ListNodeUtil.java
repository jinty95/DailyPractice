package cn.jinty.utils;

import cn.jinty.leetcode.ListNode;
import cn.jinty.leetcode.Node;

/**
 * 链表工具类
 *
 * @author Jinty
 * @date 2020/4/8.
 */
public class ListNodeUtil {

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
