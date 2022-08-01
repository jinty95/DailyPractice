package cn.jinty.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对象 - 工具类
 *
 * @author Jinty
 * @date 2022/8/1
 **/
public final class ObjectUtil {

    /**
     * 是否为空
     *
     * @param obj 对象
     * @return 是否
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 是否非空
     *
     * @param obj 对象
     * @return 是否
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    /**
     * 获取类的所有属性(包括父类属性)
     *
     * @param clazz 类
     * @return 所有属性
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> result = new ArrayList<>();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            result.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    /**
     * 对象空字段设置默认值
     * (包括8种基本类型包装类和字符串，共支持9种类型)
     *
     * @param obj 对象
     * @param <T> 泛型
     */
    public static <T> void setDefaultWhenNull(T obj) {
        if (isNull(obj)) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = getAllFields(clazz);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object val = field.get(obj);
                if (isNotNull(val)) {
                    continue;
                }
                if (field.getType() == Byte.class) {
                    field.set(obj, (byte) 0);
                } else if (field.getType() == Short.class) {
                    field.set(obj, (short) 0);
                } else if (field.getType() == Integer.class) {
                    field.set(obj, 0);
                } else if (field.getType() == Long.class) {
                    field.set(obj, (long) 0);
                } else if (field.getType() == Float.class) {
                    field.set(obj, 0.00f);
                } else if (field.getType() == Double.class) {
                    field.set(obj, 0.00d);
                } else if (field.getType() == Boolean.class) {
                    field.set(obj, false);
                } else if (field.getType() == Character.class) {
                    field.set(obj, ' ');
                } else if (field.getType() == String.class) {
                    field.set(obj, "");
                }
            } catch (IllegalAccessException e) {
                System.out.println(String.format("对象空字段设置默认值异常：obj=%s, field=%s, error=%s",
                        obj, field.getName(), e.getClass().getSimpleName()));
            }
        }
    }

}
