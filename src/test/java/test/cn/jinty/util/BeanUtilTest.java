package test.cn.jinty.util;

import cn.jinty.entity.KeyValue;
import cn.jinty.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 对象拷贝 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/4/29
 **/
public class BeanUtilTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person1 {
        private int id;
        private String name;
        private double salary;
        private int[] scores;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person2 {
        private int id;
        private String name;
        private Double salary;
        private int[] scores;
    }

    /**
     * 浅拷贝 - 同名字段不同泛型可以拷贝，但是取值时会进行类型转换，可能导致类型转换异常
     */
    @Test
    public void testCopy1() {
        KeyValue<String, String> kv1 = new KeyValue<>("1", "A");
        KeyValue<Integer, Integer> kv2 = new KeyValue<>();
        BeanUtil.copy(kv1, kv2);
        System.out.println("源对象：" + kv1);
        System.out.println("目标对象：" + kv2);
        Integer a = kv2.getValue();
        System.out.println(a);
    }

    /**
     * 浅拷贝 - 同名字段不同类型不会拷贝，直接忽略，即使是"基本类型"和对应的"包装类"也不会拷贝
     */
    @Test
    public void testCopy2() {
        Person1 p1 = new Person1(1, "我", 8999.99, null);
        Person2 p2 = new Person2();
        BeanUtil.copy(p1, p2);
        System.out.println("源对象：" + p1);
        System.out.println("目标对象：" + p2);
    }

    /**
     * 浅拷贝 - 源对象最外层的值直接赋给目标对象，覆盖目标对象的值
     */
    @Test
    public void testCopy3() {
        int[] scores = new int[]{1, 2, 3};
        Person1 p1 = new Person1(1, "我", 8999.99, scores);
        Person2 p2 = new Person2(2, "你", 9999.99, null);
        BeanUtil.copy(p1, p2);
        System.out.println("源对象：" + p1);
        System.out.println("源对象的数组是：" + scores);
        System.out.println("目标对象：" + p2);
        System.out.println("目标对象的数组是：" + scores);
    }

    /**
     * 浅拷贝 - BeanCopier内置了缓存，所以第一次拷贝慢，后续拷贝快
     */
    @Test
    public void testCopy4() {
        Person1 p1 = new Person1(1, "我", 8999.99, null);
        Person2 p2 = new Person2();
        for (int i = 1; i <= 10; i++) {
            long begin = System.currentTimeMillis();
            BeanCopier.create(p1.getClass(), p2.getClass(), false).copy(p1, p2, null);
            long end = System.currentTimeMillis();
            System.out.printf("第%s次拷贝：耗时%sms%n", i, end - begin);
        }
    }

    /**
     * 浅拷贝 - 批量
     */
    @Test
    public void testCopyList() {
        try {
            Person1 p1 = new Person1(1, "我", 8999.99, new int[]{1, 2, 3});
            Person1 p2 = new Person1(2, "你", 9999.99, new int[]{1, 2, 3});
            List<Person1> list1 = Arrays.asList(p1, p2);
            System.out.println("源对象列表：" + list1);
            System.out.println("目标对象列表：" + BeanUtil.copyList(list1, Person2.class));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 深拷贝
     */
    @Test
    public void testDeepCopy() {
        Person1 p1 = new Person1(1, "我", 8999.99, new int[]{1, 2, 3});
        Person2 p2 = new Person2(2, "你", 9999.99, null);
        BeanUtil.deepCopy(p1, p2);
        System.out.println("源对象：" + p1);
        System.out.println("源对象的数组是：" + p1.scores);
        System.out.println("目标对象：" + p2);
        System.out.println("目标对象的数组是：" + p2.scores);
    }

    /**
     * 深拷贝 - 批量
     */
    @Test
    public void testDeepCopyList() {
        Person1 p1 = new Person1(1, "我", 8999.99, new int[]{1, 2, 3});
        Person1 p2 = new Person1(2, "你", 9999.99, new int[]{1, 2, 3});
        List<Person1> list1 = Arrays.asList(p1, p2);
        System.out.println("源对象列表：" + list1);
        System.out.println("目标对象列表：" + BeanUtil.deepCopyList(list1, Person2.class));
    }

}
