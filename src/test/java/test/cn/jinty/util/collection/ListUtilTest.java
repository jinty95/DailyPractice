package test.cn.jinty.util.collection;

import cn.jinty.util.collection.ListUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 列表工具类 - 测试
 *
 * @author Jinty
 * @date 2021/5/14
 */
public class ListUtilTest {

    @Test
    public void testSplitByNum() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(ListUtil.splitByNum(list, 3));
        System.out.println(ListUtil.splitByNum(list, 4));
        System.out.println(ListUtil.splitByNum(list, 5));
    }

    @Test
    public void testAsList() {
        List<Integer> list1 = ListUtil.asList(1, 2, 3);
        System.out.println(list1);
        List<String> list2 = ListUtil.asList("a", "b", "c");
        System.out.println(list2);
    }

    @Test
    public void testToString() {
        List<String> list = ListUtil.asList("A", "B", "C");
        System.out.println(ListUtil.toString(null));
        System.out.println(ListUtil.toString(list));
        System.out.println(ListUtil.toString(list, ", "));
        System.out.println(ListUtil.toString(list, ", ", "(", ")"));
        System.out.println(ListUtil.toString(list, ", ", "(", ")", "\"", "\""));
    }

    @Test
    public void testFromString() {
        System.out.println(ListUtil.fromString("", ""));
        System.out.println(ListUtil.fromString("A,B,C", ","));
        System.out.println(ListUtil.fromString("A\r\nB\nC\rD\tE\fF G,H.I;J", "\\s+|[,.;]"));
    }

    @Test
    public void testSelect() {
        List<Integer> list = ListUtil.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("数据集：" + list);
        System.out.println("大于5：" + ListUtil.select(list, a -> a > 5));
        System.out.println("小于3：" + ListUtil.select(list, a -> a < 3));
        System.out.println("偶数：" + ListUtil.select(list, a -> a % 2 == 0));
        System.out.println("无限制：" + ListUtil.select(list, null));
    }

}
