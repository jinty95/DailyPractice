package cn.jinty.util.object;

import cn.jinty.annotation.FieldName;
import cn.jinty.util.DateUtil;
import cn.jinty.util.string.StringUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
     * 是否全部为空
     *
     * @param objects 对象数组
     * @return 是否
     */
    public static boolean isAllNull(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        for (Object value : objects) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回第一个非空对象
     *
     * @param objects 对象数组
     * @param <T>     类型
     * @return 第一个非空对象
     */
    @SafeVarargs
    public static <T> T firstNotNull(T... objects) {
        if (objects == null || objects.length == 0) {
            return null;
        }
        for (T value : objects) {
            if (value != null) {
                return value;
            }
        }
        return null;
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
        List<Field> fields = ClassUtil.getAllField(clazz);
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
                System.out.printf("对象空字段设置默认值异常：obj=%s, field=%s, error=%s%n",
                        obj, field.getName(), e.getClass().getSimpleName());
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
        List<Field> fields = ClassUtil.getAllField(clazz);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object val1 = o1 != null ? field.get(o1) : null;
                Object val2 = o2 != null ? field.get(o2) : null;
                if (!Objects.equals(val1, val2)) {
                    FieldName fieldName = field.getAnnotation(FieldName.class);
                    String name = fieldName != null ? fieldName.value() : field.getName();
                    String diff = String.format("[%s] %s -> %s", name, val1, val2);
                    diffs.add(diff);
                }
            } catch (IllegalAccessException e) {
                System.out.printf("比较对象差异异常：o1=%s, o2=%s, field=%s, error=%s%n",
                        o1, o2, field.getName(), e.getClass().getSimpleName());
            }
        }
        return diffs;
    }

    // 字符串转对象所支持的所有类型(八种基础类型、字符串、大整数、大小数、日期)
    public static Class<?>[] STR_TO_OBJ_SUPPORTED_CLASS = new Class<?>[]{
            Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Character.class,
            boolean.class, byte.class, short.class, int.class, long.class, float.class, double.class, char.class,
            String.class, BigInteger.class, BigDecimal.class, Date.class
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
        if (isNull(str) || isNull(clazz)) {
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
        if (StringUtil.isBlank(str)) {
            return String.class.getName().equals(className) ? (T) str : null;
        }
        if (!STR_TO_OBJ_SUPPORTED_CLASS_NAME.contains(className)) {
            throw new IllegalArgumentException("不支持将字符串转成该类型：" + className);
        }
        Object obj = null;
        if (Boolean.class.getName().equals(className) || boolean.class.getName().equals(className)) {
            obj = Boolean.parseBoolean(str);
        } else if (Byte.class.getName().equals(className) || byte.class.getName().equals(className)) {
            obj = Byte.parseByte(str);
        } else if (Short.class.getName().equals(className) || short.class.getName().equals(className)) {
            obj = Short.parseShort(str);
        } else if (Integer.class.getName().equals(className) || int.class.getName().equals(className)) {
            obj = Integer.parseInt(str);
        } else if (Long.class.getName().equals(className) || long.class.getName().equals(className)) {
            obj = Long.parseLong(str);
        } else if (Float.class.getName().equals(className) || float.class.getName().equals(className)) {
            obj = Float.parseFloat(str);
        } else if (Double.class.getName().equals(className) || double.class.getName().equals(className)) {
            obj = Double.parseDouble(str);
        } else if (Character.class.getName().equals(className) || char.class.getName().equals(className)) {
            obj = str.charAt(0);
        } else if (String.class.getName().equals(className)) {
            obj = str;
        } else if (BigInteger.class.getName().equals(className)) {
            obj = new BigInteger(str);
        } else if (BigDecimal.class.getName().equals(className)) {
            obj = new BigDecimal(str);
        } else if (Date.class.getName().equals(className)) {
            obj = DateUtil.parseCompatibly(str);
        }
        return (T) obj;
    }

    /**
     * 是否对象的所有属性都为空 (通过getter判断)
     *
     * @param obj 对象
     * @return 是否
     * @throws Exception 异常
     */
    public static boolean isAllFieldNull(Object obj) throws Exception {
        if (obj == null) {
            return true;
        }
        for (PropertyDescriptor prop : IntrospectUtil.getPropertyDescriptors(obj.getClass())) {
            Method getter = prop.getReadMethod();
            if (getter == null) {
                continue;
            }
            if (isNotNull(getter.invoke(obj))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取对象的指定属性值
     *
     * @param obj          对象
     * @param propertyName 属性名
     * @return 属性值
     * @throws Exception 异常
     */
    public static Object getPropertyValue(Object obj, String propertyName) throws Exception {
        if (obj == null) {
            return null;
        }
        Method getter = IntrospectUtil.getGetter(obj.getClass(), propertyName);
        if (getter == null) {
            throw new NoSuchMethodException(String.format("there is no getter for [%s] in class [%s]",
                    propertyName, obj.getClass().getName()));
        }
        return getter.invoke(obj);
    }

}
