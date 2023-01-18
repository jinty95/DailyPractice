package cn.jinty.util.object;

import cn.jinty.annotation.FieldName;
import cn.jinty.entity.KeyValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 对象 - 工具类
 *
 * @author Jinty
 * @date 2022/8/1
 **/
public final class ObjectUtil {

    private ObjectUtil() {
    }

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
     * 是否相等
     *
     * @param o1 对象1
     * @param o2 对象2
     * @return 是否
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
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
     * (包括8种基本类型包装类、字符串、日期、列表)
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
                } else if (field.getType() == Date.class) {
                    field.set(obj, new Date());
                } else if (field.getType() == List.class) {
                    field.set(obj, new ArrayList<>());
                }
            } catch (IllegalAccessException e) {
                System.out.println(String.format("对象空字段设置默认值异常：obj=%s, field=%s, error=%s",
                        obj, field.getName(), e.getClass().getSimpleName()));
            }
        }
    }

    /**
     * 比较对象差异 (浅比较，即只比较第一层字段)
     *
     * @param o1  对象1
     * @param o2  对象2
     * @param <T> 泛型
     * @return 差异描述
     */
    public static <T> List<String> diff(T o1, T o2) {
        List<String> diffs = new ArrayList<>();
        if (o1 == o2) {
            return diffs;
        }
        Class<?> clazz = o1 != null ? o1.getClass() : o2.getClass();
        List<Field> fields = getAllFields(clazz);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object val1 = o1 != null ? field.get(o1) : null;
                Object val2 = o2 != null ? field.get(o2) : null;
                if (!equals(val1, val2)) {
                    FieldName fieldName = field.getAnnotation(FieldName.class);
                    String name = fieldName != null ? fieldName.value() : field.getName();
                    String diff = String.format("[%s] %s -> %s", name, val1, val2);
                    diffs.add(diff);
                }
            } catch (IllegalAccessException e) {
                System.out.println(String.format("比较对象差异异常：o1=%s, o2=%s, field=%s, error=%s",
                        o1, o2, field.getName(), e.getClass().getSimpleName()));
            }
        }
        return diffs;
    }

    /**
     * 对象转为键值对数组
     *
     * @param obj 对象
     * @return 键值对数组
     */
    public static List<KeyValue<String, Object>> toKvPairs(Object obj) {
        List<KeyValue<String, Object>> kvPairs = new ArrayList<>();
        if (isNull(obj)) {
            return kvPairs;
        }
        List<Field> fields = getAllFields(obj.getClass());
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(obj);
                kvPairs.add(new KeyValue<>(key, value));
            } catch (IllegalAccessException e) {
                System.out.println(String.format("对象转为键值对数组异常：obj=%s, field=%s, error=%s",
                        obj, field.getName(), e.getClass().getSimpleName()));
            }
        }
        return kvPairs;
    }

}
