package test.cn.jinty.java.util;

import org.junit.Test;

import java.util.*;

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

    // Arrays.asList方法返回的List对象是一个内部类，没有实现add和remove方法，不能对元素进行增减
    @Test
    public void testArraysAsList() {
        // 不可增减元素的List
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        this.testArraysAsList(list);
        System.out.println();
        // 可增减元素的List
        list = new ArrayList<>(list);
        this.testArraysAsList(list);
    }

    private void testArraysAsList(List<Integer> list) {
        System.out.println(list);
        try {
            list.add(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
        try {
            list.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }

}
