package test.cn.jinty.util;

import cn.jinty.util.ObjectUtil;
import lombok.ToString;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

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
    }

    @ToString
    public static class SuperObj {
        private String super1;
        private String super2;
        private Date super3;
    }

}
