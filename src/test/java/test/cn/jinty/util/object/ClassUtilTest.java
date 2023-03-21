package test.cn.jinty.util.object;

import cn.jinty.entity.TreeNode1;
import cn.jinty.entity.TreeNode2;
import cn.jinty.entity.tuple.Pair;
import cn.jinty.util.collection.ListUtil;
import cn.jinty.util.object.ClassUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 类 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/3/15
 **/
public class ClassUtilTest {

    @Test
    public void testGetAllField() {
        System.out.println("类：" + TreeNode1.class.getName());
        System.out.println("所有字段：");
        for (Field field : ClassUtil.getAllField(TreeNode1.class)) {
            System.out.println(field.getName());
        }
    }

    @Test
    public void testGetAllMethod() {
        System.out.println("类：" + TreeNode2.class.getName());
        System.out.println("所有方法：");
        for (Method method : ClassUtil.getAllMethod(TreeNode2.class)) {
            System.out.println(method.getName());
        }
        System.out.println("类：" + Method.class.getName());
        System.out.println("所有方法：");
        for (Method method : ClassUtil.getAllMethod(Method.class)) {
            System.out.println(method.getName());
        }
    }

    @Test
    public void testGetAllInterface() {
        System.out.println("类：" + ArrayList.class.getName());
        System.out.println("所有实现的接口：");
        for (Class<?> clazz : ClassUtil.getAllInterface(ArrayList.class)) {
            System.out.println(clazz.getName());
        }
    }

    @Test
    public void testGetAllSuperclass() {
        System.out.println("类：" + ArrayList.class.getName());
        System.out.println("所有父类：");
        for (Class<?> clazz : ClassUtil.getAllSuperclass(ArrayList.class)) {
            System.out.println(clazz.getName());
        }
    }

    @Test
    public void testGetAllSuper() {
        System.out.println("类：" + ArrayList.class.getName());
        System.out.println("所有超类：");
        for (Class<?> clazz : ClassUtil.getAllSuper(ArrayList.class)) {
            System.out.println(clazz.getName());
        }
    }

    @Test
    public void testIsAssignableFrom() {
        List<Pair<Class<?>, Class<?>>> list = ListUtil.asList(
                new Pair<>(String.class, String.class),
                new Pair<>(Object.class, Object.class),
                new Pair<>(String.class, Object.class),
                new Pair<>(Object.class, String.class),
                new Pair<>(int.class, Integer.class),
                new Pair<>(Integer.class, int.class),
                new Pair<>(boolean.class, Boolean.class),
                new Pair<>(Boolean.class, boolean.class)
        );
        for (Pair<Class<?>, Class<?>> pair : list) {
            Class<?> target = pair.getLeft();
            Class<?> source = pair.getRight();
            System.out.printf("%s 可以被 %s 赋值: %s%n",
                    target.getName(), source.getName(), ClassUtil.isAssignableFrom(target, source));
        }
    }

}
