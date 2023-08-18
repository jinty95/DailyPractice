package test.cn.jinty.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * List - 测试
 *
 * @author Jinty
 * @date 2023/8/16
 **/
public class ListTest {

    // set方法的作用是在指定的位置，替换原有元素，前提是该位置存在，否则会报角标越界异常
    // 对于一个空List，不能使用set方法在指定位置添加元素
    @Test
    public void testSet() {
        List<Set<Long>> list = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            list.set(i, new HashSet<>());
        }
        list.forEach(System.out::println);
    }

    // 在指定位置插入一个元素，位置限制为[0, size()]
    @Test
    public void testAdd() {
        List<Set<Long>> list = new ArrayList<>(4);
        for (int i = 1; i < 4; i++) {
            list.add(i, new HashSet<>());
        }
        list.forEach(System.out::println);
    }

}
