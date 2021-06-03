package struct.tree;

import cn.jinty.struct.tree.BinaryIndexTree;
import org.junit.Test;

/**
 * 二进制索引树 - 测试
 *
 * @author jinty
 * @date 2021/6/1
 **/
public class BinaryIndexTreeTest {

    @Test
    public void test(){
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        BinaryIndexTree bit = new BinaryIndexTree(nums);
        System.out.println(bit.sumRange(0,2));
        bit.update(0,1);
        System.out.println(bit.sumRange(0,2));
        bit.update(0,2);
        System.out.println(bit.sumRange(0,2));
    }

}
