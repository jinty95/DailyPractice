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
        return sourceBeanInfo.getPropertyDescriptors();
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

}
