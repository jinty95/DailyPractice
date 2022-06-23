package test.cn.jinty.util;

import cn.jinty.util.CompareUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 比较 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/6/23
 **/
public class CompareUtilTest {

    @Test
    public void testNullableComparator() {
        System.out.println("Integer比较");
        List<Integer> list1 = Arrays.asList(null, 5, 1, null, 2, 1, 9);
        System.out.println(list1);
        list1.sort(CompareUtil.nullableComparator());
        System.out.println(list1);
        System.out.println("Double比较");
        List<Double> list2 = Arrays.asList(null, 0.01, 9.99, 3.14, null);
        System.out.println(list2);
        list2.sort(CompareUtil.nullableComparator());
        System.out.println(list2);
        System.out.println("String比较");
        List<String> list3 = Arrays.asList(null, "bbb", "aaa", "ccc", null);
        System.out.println(list3);
        list3.sort(CompareUtil.nullableComparator());
        System.out.println(list3);
    }

}
