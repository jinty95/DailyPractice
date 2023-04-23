package test.cn.jinty.util.object;

import cn.jinty.util.object.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/4/29
 **/
public class BeanUtilTest {

    @Test
    public void testMapToBean() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "mark");
        map.put("age", "18");
        map.put("salary", "9.99");
        map.put("score", "610");
        map.put("birthday", "2023-3-16 00:00:00");
        map.put("isDeleted", "true");
        map.put("remark", "111");
        System.out.println("原Map：" + map);
        try {
            Person1 p1 = BeanUtil.mapToBean(map, Person1.class);
            System.out.println("转成Bean：" + p1);
            map = BeanUtil.beanToMap(p1);
            System.out.println("转回Map：" + map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCopyByIntrospect() {
        System.out.println("基于[内省]拷贝对象");
        long totalBegin = System.currentTimeMillis();
        try {
            Person1 p1 = buildPerson1();
            //System.out.println("原对象：" + p1);
            for (int i = 1; i <= total; i++) {
                //long begin = System.currentTimeMillis();
                Person2 p2 = new Person2();
                BeanUtil.copy(p1, p2);
                //long end = System.currentTimeMillis();
                //System.out.println("拷贝对象" + i + "：" + p2);
                //System.out.println("耗时：" + (end - begin) + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long totalEnd = System.currentTimeMillis();
        System.out.println("总耗时：" + (totalEnd - totalBegin) + "ms");
    }

    @Test
    public void testCopyByReflect() {
        System.out.println("基于[反射]拷贝对象");
        long totalBegin = System.currentTimeMillis();
        try {
            Person1 p1 = buildPerson1();
            //System.out.println("原对象：" + p1);
            for (int i = 1; i <= total; i++) {
                //long begin = System.currentTimeMillis();
                Person2 p2 = new Person2();
                BeanUtil.copyByReflect(p1, p2);
                //long end = System.currentTimeMillis();
                //System.out.println("拷贝对象" + i + "：" + p2);
                //System.out.println("耗时：" + (end - begin) + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long totalEnd = System.currentTimeMillis();
        System.out.println("总耗时：" + (totalEnd - totalBegin) + "ms");
    }

    @Test
    public void testCopyByGetSet() {
        System.out.println("基于[Get/Set]拷贝对象");
        long totalBegin = System.currentTimeMillis();
        try {
            Person1 p1 = buildPerson1();
            //System.out.println("原对象：" + p1);
            for (int i = 1; i <= total; i++) {
                //long begin = System.currentTimeMillis();
                Person2 p2 = new Person2();
                p2.setId(p1.getId());
                p2.setName(p1.getName());
                p2.setAge(p1.getAge());
                p2.setSalary(p1.getSalary());
                p2.setScore(p1.getScore());
                p2.setBirthday(p1.getBirthday());
                p2.setIsDeleted(p1.getIsDeleted());
                p2.setRemark(p1.getRemark());
                //long end = System.currentTimeMillis();
                //System.out.println("拷贝对象" + i + "：" + p2);
                //System.out.println("耗时：" + (end - begin) + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long totalEnd = System.currentTimeMillis();
        System.out.println("总耗时：" + (totalEnd - totalBegin) + "ms");
    }

    private static int total;

    @Test
    public void testCopy() {
        total = 1000000;
        testCopyByIntrospect();
        testCopyByReflect();
        testCopyByGetSet();
        // 测试耗时如下：
        // 内省：1538ms
        // 反射：644ms
        // Get/Set：34ms
    }

    private Person1 buildPerson1() {
        Person1 p1 = new Person1();
        p1.setId(11);
        p1.setName("kin");
        p1.setAge(22);
        p1.setSalary(99.9);
        p1.setScore(100L);
        p1.setBirthday(new Date());
        p1.setIsDeleted(true);
        p1.setRemark("备注一下可以吗");
        return p1;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person1 {
        private int id;
        private String name;
        private Integer age;
        private double salary;
        private Long score;
        private Date birthday;
        private Boolean isDeleted;
        private String remark;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person2 {
        private Integer id;
        private Object name;
        private Number age;
        private Double salary;
        private long score;
        private Date birthday;
        // 这里不能用boolean，否则字段解析不准确：字段名deleted，读方法isDeleted，写方法setDeleted
        private Boolean isDeleted;
        private String remark;
    }

}
