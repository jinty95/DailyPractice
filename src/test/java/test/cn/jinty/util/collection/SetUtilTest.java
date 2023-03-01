package test.cn.jinty.util.collection;

import cn.jinty.util.collection.SetUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集合 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/11/13
 */
public class SetUtilTest {

    @Test
    public void testUnion() {
        Set<Integer> set1 = SetUtil.asSet(1, 2, 3, 4);
        Set<Integer> set2 = SetUtil.asSet(3, 4, 5, 6);
        System.out.println(SetUtil.union(set1, set2));
        System.out.println(SetUtil.union(new HashSet<>(), null));
    }

    @Test
    public void testIntersect() {
        Set<Integer> set1 = SetUtil.asSet(1, 2, 3, 4);
        Set<Integer> set2 = SetUtil.asSet(3, 4, 5, 6);
        System.out.println(SetUtil.intersect(set1, set2));
        System.out.println(SetUtil.intersect(new HashSet<>(), null));
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);
        System.out.println(SetUtil.intersect(new HashSet<>(list1), new HashSet<>(list2)));
    }

    @Test
    public void testSubtract() {
        Set<Integer> set1 = SetUtil.asSet(1, 2, 3, 4);
        Set<Integer> set2 = SetUtil.asSet(3, 4, 5, 6);
        System.out.println(SetUtil.subtract(set1, set2));
        System.out.println(SetUtil.subtract(set2, set1));
        System.out.println(SetUtil.subtract(set1, null));
    }

    @Test
    public void testSelect() {
        Set<Integer> set = SetUtil.asSet(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("数据集：" + set);
        System.out.println("大于5：" + SetUtil.select(set, a -> a > 5));
        System.out.println("小于3：" + SetUtil.select(set, a -> a < 3));
        System.out.println("偶数：" + SetUtil.select(set, a -> a % 2 == 0));
        System.out.println("无限制：" + SetUtil.select(set, null));
    }

}
