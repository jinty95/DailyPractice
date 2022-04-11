package test.cn.jinty.entity;

import cn.jinty.entity.TreeNode1;
import cn.jinty.entity.TreeNode2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 树型节点 - 测试
 *
 * @author jintai.wang
 * @date 2022/4/11
 **/
public class TreeNodeTest {

    @Test
    public void testTransfer() {
        List<TreeNode2> list2 = new ArrayList<>();
        list2.add(new TreeNode2(1L, "广东省", 1L, 0L));
        list2.add(new TreeNode2(2L, "福建省", 1L, 0L));
        list2.add(new TreeNode2(3L, "广州市", 2L, 1L));
        list2.add(new TreeNode2(4L, "深圳市", 2L, 1L));
        list2.add(new TreeNode2(5L, "厦门市", 2L, 2L));
        list2.add(new TreeNode2(6L, "天河区", 3L, 3L));
        list2.add(new TreeNode2(7L, "湖南省", 1L, 0L));
        List<TreeNode1> list1 = TreeNode2.convert(list2);
        for (TreeNode1 node1 : list1) {
            System.out.println(node1);
        }
    }

}
