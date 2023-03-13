package cn.jinty.util.object;

import cn.jinty.annotation.FieldName;
import cn.jinty.util.DateUtil;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

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
     * 如果值为空，返回默认值
     *
     * @param value        值
     * @param defaultValue 默认值
     * @param <T>          类型
     * @return 值或默认值
     */
    public static <T> T ifNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 返回第一个非空值
     *
     * @param values 值数组
     * @param <T>    类型
     * @return 第一个非空值
     */
    @SafeVarargs
    public static <T> T firstNotNull(T... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        for (T value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
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
     * 给对象空字段设置默认值
     * (包括8种基本类型包装类、字符串、日期、大整数、大小数、列表、集合、映射)
     *
     * @param obj 对象
     * @param <T> 类型
     */
    public static <T> void setDefault(T obj) {
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
                } else if (field.getType() == BigInteger.class) {
                    field.set(obj, BigInteger.ZERO);
                } else if (field.getType() == BigDecimal.class) {
                    field.set(obj, BigDecimal.ZERO);
                } else if (field.getType() == List.class) {
                    field.set(obj, new ArrayList<>());
                } else if (field.getType() == Set.class) {
                    field.set(obj, new HashSet<>());
                } else if (field.getType() == Map.class) {
                    field.set(obj, new HashMap<>());
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
     * @param <T> 类型
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

    // 字符串转对象所支持的所有类型(字符串、八种基础类型、大整数、大小数、日期)
    @SuppressWarnings("rawtypes")
    public static Class[] STR_TO_OBJ_SUPPORTED_CLASS = new Class[]{
            String.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class,
            Character.class, Boolean.class, BigInteger.class, BigDecimal.class, Date.class
    };
    public static Set<String> STR_TO_OBJ_SUPPORTED_CLASS_NAME = Arrays.stream(STR_TO_OBJ_SUPPORTED_CLASS)
            .map(Class::getName).collect(Collectors.toSet());

    /**
     * 将一个字符串转成指定类型的对象
     *
     * @param str   字符串
     * @param clazz 目标类型
     * @param <T>   类型
     * @return 目标对象
     */
    public static <T> T strToObj(String str, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        return strToObj(str, clazz.getName());
    }

    /**
     * 将一个字符串转成指定类型的对象
     *
     * @param str       字符串
     * @param className 目标类型名称
     * @param <T>       类型
     * @return 目标对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T strToObj(String str, String className) {
        if (isNull(str) || isNull(className)) {
            return null;
        }
        if (!STR_TO_OBJ_SUPPORTED_CLASS_NAME.contains(className)) {
            throw new IllegalArgumentException("不支持将字符串转成该类型：" + className);
        }
        Object obj = null;
        if (String.class.getName().equals(className)) {
            obj = str;
        } else if (Byte.class.getName().equals(className)) {
            obj = Byte.parseByte(str);
        } else if (Short.class.getName().equals(className)) {
            obj = Short.parseShort(str);
        } else if (Integer.class.getName().equals(className)) {
            obj = Integer.parseInt(str);
        } else if (Long.class.getName().equals(className)) {
            obj = Long.parseLong(str);
        } else if (Float.class.getName().equals(className)) {
            obj = Float.parseFloat(str);
        } else if (Double.class.getName().equals(className)) {
            obj = Double.parseDouble(str);
        } else if (Character.class.getName().equals(className)) {
            obj = str.charAt(0);
        } else if (Boolean.class.getName().equals(className)) {
            obj = Boolean.parseBoolean(str);
        } else if (BigInteger.class.getName().equals(className)) {
            obj = new BigInteger(str);
        } else if (BigDecimal.class.getName().equals(className)) {
            obj = new BigDecimal(str);
        } else if (Date.class.getName().equals(className)) {
            if (str.length() <= 11) {
                obj = DateUtil.parseDateCompatibly(str);
            } else {
                obj = DateUtil.parseDatetimeCompatibly(str);
            }
        }
        return (T) obj;
    }

}
