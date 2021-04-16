package cn.jinty.utils;

import cn.jinty.leetcode.ListNode;

/**
 * 链表工具类
 *
 * @author Jinty
 * @date 2020/4/8.
 */
public class ListNodeUtil {

    //链表转换为字符串
    public static String printListNode(ListNode node){
        StringBuilder sb = new StringBuilder();
        while(node!=null){
            sb.append(node.val+",");
            node = node.next;
        }
        return sb.toString();
    }

    //数组构建链表
    public static ListNode buildListNodeFromArray(int[] arr){
        ListNode head = new ListNode();
        if(arr!=null && arr.length>0){
            ListNode tmp = head;
            for(int i:arr){
                ListNode node = new ListNode(i);
                tmp.next = node;
                tmp = tmp.next;
            }
        }
        return head.next;
    }
}