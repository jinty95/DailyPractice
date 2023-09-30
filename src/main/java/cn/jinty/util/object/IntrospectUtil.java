package cn.jinty.util.object;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 内省 - 工具类
 *
 * @author Jinty
 * @date 2023/7/13
 **/
public final class IntrospectUtil {

    private IntrospectUtil() {
    }

    /**
     * 获取属性描述器列表
     *
     * @param clazz 类型
     * @return 属性描述器列表
     * @throws IntrospectionException 内省异常
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws IntrospectionException {
        BeanInfo sourceBeanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = sourceBeanInfo.getPropertyDescriptors();
        // 排除"class"，这是"getClass"方法带来的，不是类中定义的一般属性
        PropertyDescriptor[] newPropertyDescriptors = new PropertyDescriptor[propertyDescriptors.length - 1];
        int i = 0;
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }
            newPropertyDescriptors[i++] = propertyDescriptor;
        }
        return newPropertyDescriptors;
    }

    /**
     * 属性名 -> 属性描述器
     *
     * @param clazz 类型
     * @return 属性名 -> 属性描述器
     * @throws IntrospectionException 内省异常
     */
    public static Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> clazz) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        Map<String, PropertyDescriptor> map = new HashMap<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            map.put(propertyDescriptor.getName(), propertyDescriptor);
        }
        return map;
    }

    /**
     * 属性名 -> Setter
     *
     * @param clazz 类型
     * @return 属性名 -> Setter
     * @throws IntrospectionException 内省异常
     */
    public static Map<String, Method> getPropertySetterMap(Class<?> clazz) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        Map<String, Method> map = new HashMap<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            map.put(propertyDescriptor.getName(), propertyDescriptor.getWriteMethod());
        }
        return map;
    }

    /**
     * 属性名 -> Getter
     *
     * @param clazz 类型
     * @return 属性名 -> Getter
     * @throws IntrospectionException 内省异常
     */
    public static Map<String, Method> getPropertyGetterMap(Class<?> clazz) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        Map<String, Method> map = new HashMap<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            map.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod());
        }
        return map;
    }

    /**
     * 根据属性名获取对应的Setter
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @return Setter
     * @throws IntrospectionException 内省异常
     */
    public static Method getSetter(Class<?> clazz, String propertyName) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(propertyName)) {
                return propertyDescriptor.getWriteMethod();
            }
        }
        return null;
    }

    /**
     * 根据属性名获取对应的Getter
     *
     * @param clazz 类型
     * @param propertyName 属性名
     * @return Getter
     * @throws IntrospectionException 内省异常
     */
    public static Method getGetter(Class<?> clazz, String propertyName) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(propertyName)) {
                return propertyDescriptor.getReadMethod();
            }
        }
        return null;
    }

}
