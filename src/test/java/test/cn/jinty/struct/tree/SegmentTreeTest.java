package test.cn.jinty.struct.tree;

import cn.jinty.struct.tree.SegmentTree;
import org.junit.Test;

/**
 * 线段树 - 测试
 *
 * @author Jinty
 * @date 2021/5/31
 **/
public class SegmentTreeTest {

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SegmentTree segmentTree = new SegmentTree(nums);
        System.out.println(segmentTree.sumRange(0, 8));
        segmentTree.update(0, 2);
        System.out.println(segmentTree.sumRange(0, 8));
        segmentTree.update(0, 3);
        System.out.println(segmentTree.sumRange(0, 8));
    }

}
