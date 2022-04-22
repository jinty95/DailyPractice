package cn.jinty.util;

import cn.jinty.struct.line.ListNode;

import java.util.Random;

/**
 * 链表 - 工具类
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
    public static ListNode getIntersection(ListNode headA, ListNode headB) {
        // 快慢指针：先计算两个链表长度，长链表先走，两个链表等长时一起走，第一个相遇点即为交点
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

    /**
     * 随机获取链表的一个节点，各个节点被获取的概率相等
     *
     * @param head 链表
     * @return 节点
     */
    public static ListNode random(ListNode head) {
        // 蓄水池算法
        ListNode result = head;
        Random random = new Random();
        head = head.next;
        int i = 1;
        while (head != null) {
            i++;
            // 1/n概率选择第n个节点，(n-1)/n概率保持原来的选择
            if (random.nextInt(i) == 0) {
                result = head;
            }
            head = head.next;
        }
        return result;
    }

    /**
     * 将链表按数值进行升序排序
     *
     * @param head 链表
     * @return 升序链表
     */
    public static ListNode sort(ListNode head) {
        // 归并排序：时间复杂度O(NlogN)，空间复杂度O(logN)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMidNode(head);
        ListNode head1 = sort(mid.next);
        mid.next = null;
        ListNode head2 = sort(head);
        return merge(head1, head2);
    }

    /**
     * 合并有序链表(升序)
     *
     * @param head1 有序链表1
     * @param head2 有序链表2
     * @return 有序链表
     */
    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(), tmp = head;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                tmp.next = head1;
                tmp = tmp.next;
                head1 = head1.next;
            } else {
                tmp.next = head2;
                tmp = tmp.next;
                head2 = head2.next;
            }
        }
        if (head1 != null) {
            tmp.next = head1;
        }
        if (head2 != null) {
            tmp.next = head2;
        }
        return head.next;
    }

    /**
     * 寻找链表的中间节点
     *
     * @param head 链表
     * @return 中间节点
     */
    public static ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
