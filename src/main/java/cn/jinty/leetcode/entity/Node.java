package cn.jinty.leetcode.entity;

import java.util.List;

/**
 * 节点
 *
 * @author Jinty
 * @date 2021/3/30
 **/
public class Node {

    public int val;
    public Node next;
    public Node random;
    public Node left;
    public Node right;
    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

}
