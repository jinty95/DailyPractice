package leetcode.tree;

import cn.jinty.leetcode.tree.SegmentTree;
import org.junit.Test;

/**
 * 线段树 - 测试
 *
 * @author jinty
 * @date 2021/5/31
 **/
public class SegmentTreeTest {

    @Test
    public void test(){
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        SegmentTree segmentTree = new SegmentTree(nums);
        System.out.println(segmentTree.sumRange(0,2));
        segmentTree.update(0,1);
        System.out.println(segmentTree.sumRange(0,2));
        segmentTree.update(0,2);
        System.out.println(segmentTree.sumRange(0,2));
    }

}
