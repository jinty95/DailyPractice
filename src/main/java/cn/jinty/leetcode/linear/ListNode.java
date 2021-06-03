package cn.jinty.leetcode.linear;

/**
 * 链表节点类
 *
 * @author Jinty
 * @date 2020/4/8.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int x) { val = x; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode node = this;
        while(node!=null){
            sb.append(node.val).append(',');
            node = node.next;
        }
        return sb.substring(0,sb.length()-1);
    }

}
