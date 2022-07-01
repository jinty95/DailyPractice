package test.cn.jinty.util;

import cn.jinty.struct.line.ListNode;
import cn.jinty.util.ListNodeUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 链表工具 - 测试
 *
 * @author Jinty
 * @date 2021/6/4
 **/
public class ListNodeUtilTest {

    @Test
    public void testGetIntersection() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = c;
        b.next = c;
        System.out.println(a);
        System.out.println(b);
        System.out.println(ListNodeUtil.getIntersection(a, b));
    }

    @Test
    public void testReverse() {
        ListNode a = ListNodeUtil.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(a);
        System.out.println(ListNodeUtil.reverse(a));
    }

    @Test
    public void testRandom() {
        ListNode head = ListNodeUtil.fromArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        int[] count = new int[10];
        for (int i = 0; i < 10000; i++) {
            count[ListNodeUtil.random(head).val]++;
        }
        System.out.println("等概率随机获取链表的1个节点，执行10000次，各节点出现频次统计");
        System.out.println(Arrays.toString(count));
    }

    @Test
    public void testRandomN() {
        ListNode head = ListNodeUtil.fromArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        int[] count = new int[10];
        for (int i = 0; i < 10000; i++) {
            ListNode[] results = ListNodeUtil.randomN(head, 2);
            for (ListNode result : results) {
                count[result.val]++;
            }
        }
        System.out.println("等概率随机获取链表的2个节点，执行10000次，各节点出现频次统计");
        System.out.println(Arrays.toString(count));
    }

    @Test
    public void testSort() {
        System.out.println(ListNodeUtil.sort(ListNodeUtil.fromArray(new int[]{4, 2, 1, 3})));
        System.out.println(ListNodeUtil.sort(ListNodeUtil.fromArray(new int[]{5, 4, 1, 2, 3})));
        System.out.println(ListNodeUtil.sort(ListNodeUtil.fromArray(new int[]{6, 8, 7, 4, 5})));
    }

    @Test
    public void testMerge() {
        System.out.println(ListNodeUtil.merge(
                ListNodeUtil.fromArray(new int[]{1, 3, 5, 7, 9}), ListNodeUtil.fromArray(new int[]{2, 4, 6, 8, 10})
        ));
        System.out.println(ListNodeUtil.merge(
                null, ListNodeUtil.fromArray(new int[]{2, 4, 6, 8, 10})
        ));
        System.out.println(ListNodeUtil.merge(
                null, null
        ));
    }

    @Test
    public void testGetMidNode() {
        System.out.println(ListNodeUtil.getMidNode(ListNodeUtil.fromArray(new int[]{1, 2})).val);
        System.out.println(ListNodeUtil.getMidNode(ListNodeUtil.fromArray(new int[]{1, 2, 3})).val);
        System.out.println(ListNodeUtil.getMidNode(ListNodeUtil.fromArray(new int[]{1, 2, 3, 4})).val);
    }

}
