package cn.jinty.util;

import cn.jinty.struct.linear.ListNode;

/**
 * 链表工具类
 *
 * @author Jinty
 * @date 2020/4/8.
 */
public final class ListNodeUtil {

    /**
     * 根据数组构建链表
     *
     * @param arr 数组
     * @return 链表
     */
    public static ListNode fromArray(int[] arr) {
        ListNode head = new ListNode();
        if (arr != null && arr.length > 0) {
            ListNode tmp = head;
            for (int i : arr) {
                tmp.next = new ListNode(i);
                tmp = tmp.next;
            }
        }
        return head.next;
    }

    /**
     * 获取两个链表的第一个交点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 第一个交点或null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //快慢指针：先计算两个链表长度，长链表先走，两个链表等长时一起走，第一个相遇点即为交点
        if (headA == null || headB == null) return null;
        int lenA = headA.getLength();
        int lenB = headB.getLength();
        ListNode longer = lenA > lenB ? headA : headB;
        ListNode another = lenA > lenB ? headB : headA;
        int diff = Math.abs(lenA - lenB);
        while (diff > 0) {
            longer = longer.next;
            diff--;
        }
        while (longer != null) {
            if (longer == another) return longer;
            longer = longer.next;
            another = another.next;
        }
        return null;
    }

    /**
     * 翻转单链表(无环)
     *
     * @param head 原链表
     * @return 翻转链表
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = head.next;
        while (next != null) {
            head.next = pre;
            pre = head;
            head = next;
            next = next.next;
        }
        head.next = pre;
        return head;
    }

}
