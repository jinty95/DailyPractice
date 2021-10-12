package test.cn.jinty.util;

import cn.jinty.struct.linear.ListNode;
import cn.jinty.util.ListNodeUtil;
import org.junit.Test;

/**
 * 链表工具 - 测试
 *
 * @author jinty
 * @date 2021/6/4
 **/
public class ListNodeUtilTest {

    @Test
    public void testGetIntersectionNode() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = c;
        b.next = c;
        System.out.println(a);
        System.out.println(b);
        System.out.println(ListNodeUtil.getIntersectionNode(a, b));
    }

    @Test
    public void testReverse() {
        ListNode a = ListNodeUtil.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(a);
        System.out.println(ListNodeUtil.reverse(a));
    }

}
