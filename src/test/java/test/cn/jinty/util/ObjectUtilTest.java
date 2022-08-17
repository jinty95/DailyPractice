package test.cn.jinty.util;

import cn.jinty.util.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 对象 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/8/1
 **/
public class ObjectUtilTest {

    @Test
    public void testGetAllFields() {
        for (Field field : ObjectUtil.getAllFields(Obj.class)) {
            System.out.println(field.getName());
        }
    }

    @Test
    public void testSetDefaultWhenNull() {
        Obj obj = new Obj();
        System.out.println("原对象：" + obj);
        ObjectUtil.setDefaultWhenNull(obj);
        System.out.println("设置默认值后：" + obj);
    }

    @Test
    public void testDiff() {
        Obj o1 = new Obj();
        Obj o2 = new Obj();
        o1.setCol11(Arrays.asList(1, 2, 3));
        o2.setCol11(Arrays.asList(1, 3, 2));
        ObjectUtil.setDefaultWhenNull(o2);
        for (String diff : ObjectUtil.diff(o1, o2)) {
            System.out.println(diff);
        }
    }

    /* 以下为内部类 */

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class Obj extends SuperObj {
        private Byte col1;
        private Short col2;
        private Integer col3;
        private Long col4;
        private Float col5;
        private Double col6;
        private Boolean col7;
        private Character col8;
        private String col9;
        private Date col10;
        private List<Integer> col11;
    }

    @Data
    @ToString
    public static class SuperObj {
        private String superCol1;
        private String superCol2;
        private Date superCol3;
    }

}
