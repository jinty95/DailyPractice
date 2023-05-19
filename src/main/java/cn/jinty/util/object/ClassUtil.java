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
     * 根据属性名获取类的属性 (包括父类属性)
     *
     * @param clazz     类
     * @param fieldName 属性名
     * @return 属性对象
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        List<Field> fields = getAllField(clazz);
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
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
     * 根据方法名获取类的方法 (包括父类方法)
     *
     * @param clazz      类
     * @param methodName 方法名
     * @return 方法对象
     */
    public static Method getMethod(Class<?> clazz, String methodName) {
        List<Method> methods = getAllMethod(clazz);
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
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
        while (clazz != null) {
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

    // 包装类 -> 基本类型
    private static final Map<Class<?>, Class<?>> WRAP_TO_PRIMITIVE_MAP = new HashMap<>();
    // 基本类型 -> 包装类
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAP_MAP = new HashMap<>();

    static {
        WRAP_TO_PRIMITIVE_MAP.put(Boolean.class, boolean.class);
        WRAP_TO_PRIMITIVE_MAP.put(Byte.class, byte.class);
        WRAP_TO_PRIMITIVE_MAP.put(Short.class, short.class);
        WRAP_TO_PRIMITIVE_MAP.put(Integer.class, int.class);
        WRAP_TO_PRIMITIVE_MAP.put(Long.class, long.class);
        WRAP_TO_PRIMITIVE_MAP.put(Float.class, float.class);
        WRAP_TO_PRIMITIVE_MAP.put(Double.class, double.class);
        WRAP_TO_PRIMITIVE_MAP.put(Character.class, char.class);

        for (Map.Entry<Class<?>, Class<?>> entry : WRAP_TO_PRIMITIVE_MAP.entrySet()) {
            PRIMITIVE_TO_WRAP_MAP.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * 包装类 -> 基本类型
     *
     * @param wrapType 包装类
     * @return 基本类型
     */
    public static Class<?> toPrimitiveType(Class<?> wrapType) {
        return WRAP_TO_PRIMITIVE_MAP.get(wrapType);
    }

    /**
     * 基本类型 -> 包装类
     *
     * @param primitiveType 基本类型
     * @return 包装类
     */
    public static Class<?> toWrapType(Class<?> primitiveType) {
        return PRIMITIVE_TO_WRAP_MAP.get(primitiveType);
    }

    /**
     * 目标类型是否可以被来源类型赋值
     * 1、同类可以相互赋值
     * 2、子类可以赋值给父类
     * 3、基本类型与包装类可以相互赋值
     *
     * @param target 目标类型
     * @param source 来源类型
     * @return 是否
     */
    public static boolean isAssignableFrom(Class<?> target, Class<?> source) {
        if (target.isAssignableFrom(source)) {
            return true;
        }
        if (target.isPrimitive()) {
            return WRAP_TO_PRIMITIVE_MAP.get(source) == target;
        } else {
            return PRIMITIVE_TO_WRAP_MAP.get(source) == target;
        }
    }

}
