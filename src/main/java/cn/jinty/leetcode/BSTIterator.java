package cn.jinty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树迭代器
 *
 * @author jinty
 * @date 2021/3/28
 **/
class BSTIterator {

    private List<Integer> data;
    private int pointer;

    public BSTIterator(TreeNode root) {
        data = new ArrayList<>();
        inOrder(root,data);
    }

    private void inOrder(TreeNode root,List<Integer> data){
        if(root==null) return;
        inOrder(root.left,data);
        data.add((Integer) root.val);
        inOrder(root.right, data);
    }

    public int next() {
        return data.get(pointer++);
    }

    public boolean hasNext() {
        return pointer<data.size();
    }

}
