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
        map.put("salary", "9.99");
        map.put("score", "610");
        map.put("birthday", "2023-3-16 00:00:00");
        map.put("isDeleted", "true");
        System.out.println("原Map：" + map);
        try {
            Person p1 = BeanUtil.mapToBean(map, Person.class);
            System.out.println("转成Bean：" + p1);
            map = BeanUtil.beanToMap(p1);
            System.out.println("转回Map：" + map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {
        private int id;
        private String name;
        private double salary;
        private Long score;
        private Date birthday;
        private Boolean isDeleted;
    }

}
