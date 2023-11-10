package test.cn.jinty.util.collection;

import cn.jinty.util.collection.ListUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表工具类 - 测试
 *
 * @author Jinty
 * @date 2021/5/14
 */
public class ListUtilTest {

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
        System.out.println(ListUtil.fromString(null, ","));
        System.out.println(ListUtil.fromString("   ", ""));
        System.out.println(ListUtil.fromString("A,B,C", ","));
        System.out.println(ListUtil.fromString("A\r\nB\nC\rD\tE\fF G,H, I,  J,,,, K , L", "[\\s,]+"));
    }

    @Test
    public void testEquals() {
        System.out.println(ListUtil.equals(null, null));
        System.out.println(ListUtil.equals(null, new ArrayList<>()));
        System.out.println(ListUtil.equals(new ArrayList<>(), new ArrayList<>()));
        System.out.println(ListUtil.equals(ListUtil.asList(1, 2, 3), ListUtil.asList(1, 2, 3)));
        System.out.println(ListUtil.equals(ListUtil.asList(1, 2, 3), ListUtil.asList(4, 5, 6)));
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

    @Test
    public void textCartesianProduct() {
        List<List<Integer>> lists = ListUtil.asList(
                ListUtil.asList(1, 2, 3),
                ListUtil.asList(4, 5),
                ListUtil.asList(6, 7, 8),
                ListUtil.asList(9, 10)
        );
        List<List<Integer>> results = ListUtil.cartesianProduct(lists);
        System.out.println(results.size());
        results.forEach(System.out::println);
    }

    @Test
    public void testMerge() {
        System.out.println(ListUtil.merge(null, null));
        System.out.println(ListUtil.merge(null, new ArrayList<>()));
        System.out.println(ListUtil.merge(ListUtil.asList(1, 2, 1), null));
        System.out.println(ListUtil.merge(ListUtil.asList(1, 2, 1, 2), ListUtil.asList(1, 2, 1)));
    }

    @Test
    public void testUnion() {
        System.out.println(ListUtil.union(null, null));
        System.out.println(ListUtil.union(null, new ArrayList<>()));
        System.out.println(ListUtil.union(ListUtil.asList(1, 2, 1), null));
        System.out.println(ListUtil.union(ListUtil.asList(1, 2, 1, 2), ListUtil.asList(1, 2, 1)));
        System.out.println(ListUtil.union(ListUtil.asList(1, 2, 1), ListUtil.asList(1, 2, 1, 2)));
    }

    @Test
    public void testSubtract() {
        System.out.println(ListUtil.subtract(null, null));
        System.out.println(ListUtil.subtract(null, new ArrayList<>()));
        System.out.println(ListUtil.subtract(ListUtil.asList(1, 2, 1), null));
        System.out.println(ListUtil.subtract(ListUtil.asList(1, 2, 1, 2), ListUtil.asList(1, 2, 1)));
        System.out.println(ListUtil.subtract(ListUtil.asList(1, 2, 1), ListUtil.asList(1, 2, 1, 2)));
    }

    @Test
    public void testIntersect() {
        System.out.println(ListUtil.intersect(null, null));
        System.out.println(ListUtil.intersect(null, new ArrayList<>()));
        System.out.println(ListUtil.intersect(ListUtil.asList(1, 2, 1), null));
        System.out.println(ListUtil.intersect(ListUtil.asList(1, 2, 1, 2), ListUtil.asList(1, 2, 1)));
        System.out.println(ListUtil.intersect(ListUtil.asList(1, 2, 1), ListUtil.asList(1, 2, 1, 2)));
    }

    private List<Integer> getList() {
        return ListUtil.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    private int[] getArr() {
        return new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    }

    @Test
    public void testSplitByNum() {
        List<Integer> list = this.getList();
        int[] arr = this.getArr();
        for (int num : arr) {
            System.out.println(ListUtil.splitByNum(list, num));
        }
    }

    @Test
    public void testRandomSplitByNum() {
        List<Integer> list = this.getList();
        int[] arr = this.getArr();
        for (int num : arr) {
            System.out.println(ListUtil.randomSplitByNum(list, num));
        }
    }

    @Test
    public void testSplitToNGroup() {
        List<Integer> list = this.getList();
        int[] arr = this.getArr();
        for (int n : arr) {
            System.out.println(ListUtil.splitToNGroup(list, n));
        }
    }

    @Test
    public void testRandomSplitToNGroup() {
        List<Integer> list = this.getList();
        int[] arr = this.getArr();
        for (int n : arr) {
            System.out.println(ListUtil.randomSplitToNGroup(list, n));
        }
    }

}
