package cn.jinty.util.object;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 类 - 工具类
 *
 * @author Jinty
 * @date 2023/3/15
 **/
public final class ClassUtil {

    private ClassUtil() {
    }

    /**
     * 获取类的所有属性 (包括父类属性)
     *
     * @param clazz 类
     * @return 所有属性
     */
    public static List<Field> getAllField(Class<?> clazz) {
        List<Field> result = new ArrayList<>();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            result.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    /**
     * 获取类的所有方法 (包括父类方法)
     *
     * @param clazz 类
     * @return 所有方法
     */
    public static List<Method> getAllMethod(Class<?> clazz) {
        List<Method> result = new ArrayList<>();
        while (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            result.addAll(Arrays.asList(methods));
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    /**
     * 获取类实现的所有接口
     *
     * @param clazz 类
     * @return 所有接口
     */
    public static List<Class<?>> getAllInterface(Class<?> clazz) {
        Set<Class<?>> infSet = new HashSet<>();
        getAllInterface(clazz, infSet);
        return new ArrayList<>(infSet);
    }

    /**
     * 获取类实现的所有接口
     *
     * @param clazz  类
     * @param infSet 接口集合 (去重)
     */
    private static void getAllInterface(Class<?> clazz, Set<Class<?>> infSet) {
        while (clazz != null) {
            // 获取当前类的接口列表
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> inf : interfaces) {
                if (infSet.add(inf)) {
                    getAllInterface(inf, infSet);
                }
            }
            // 获取当前类的父类
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * 获取类的所有父类
     *
     * @param clazz 类
     * @return 所有父类
     */
    public static List<Class<?>> getAllSuperclass(Class<?> clazz) {
        List<Class<?>> result = new ArrayList<>();
        while (true) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null) {
                break;
            }
            result.add(superclass);
            clazz = superclass;
        }
        return result;
    }

    /**
     * 获取类的所有超类 (包括父类和接口)
     *
     * @param clazz 类
     * @return 所有超类
     */
    public static List<Class<?>> getAllSuper(Class<?> clazz) {
        List<Class<?>> result = new ArrayList<>();
        // 父类
        result.addAll(getAllSuperclass(clazz));
        // 接口
        result.addAll(getAllInterface(clazz));
        return result;
    }

}
