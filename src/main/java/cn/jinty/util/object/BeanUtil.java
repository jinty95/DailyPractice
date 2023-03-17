package cn.jinty.util.object;

import cn.jinty.util.DateUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean - 工具类
 * Bean就是一个数据对象，只包含属性和基础方法(get/set/toString等)，而没有其它功能
 *
 * @author Jinty
 * @date 2022/4/29
 **/
public final class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 将一个Map转为一个Bean
     *
     * @param map   Map对象
     * @param clazz 目标类型
     * @param <T>   类型
     * @return Bean对象
     * @throws Exception 异常
     */
    public static <T> T mapToBean(Map<String, String> map, Class<T> clazz) throws Exception {
        if (map == null) {
            return null;
        }
        T bean = clazz.newInstance();
        if (map.isEmpty()) {
            return bean;
        }
        // "内省"获取类的属性及get/set方法
        // "内省"通过get/set方法访问类的属性，不会破坏类的封装性
        // "内省"有缓存机制，多次"内省"比多次"反射"的性能消耗更低
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            String propertyValue = map.get(propertyName);
            if (propertyValue == null) {
                continue;
            }
            Method setter = descriptor.getWriteMethod();
            Class<?> type = descriptor.getPropertyType();
            setter.invoke(bean, ObjectUtil.strToObj(propertyValue, type));
        }
        return bean;
    }

    /**
     * 将一个Bean转为一个Map
     *
     * @param bean Bean对象
     * @return Map对象
     * @throws Exception 异常
     */
    public static Map<String, String> beanToMap(Object bean) throws Exception {
        if (bean == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        // "内省"获取类的属性及get/set方法
        // "内省"通过get/set方法访问类的属性，不会破坏类的封装性
        // "内省"有缓存机制，多次"内省"比多次"反射"的性能消耗更低
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            // 排除类本身
            if ("class".equals(propertyName)) {
                continue;
            }
            Method getter = descriptor.getReadMethod();
            Object propertyValue = getter.invoke(bean);
            Class<?> type = descriptor.getPropertyType();
            map.put(propertyName, propertyValue == null ? null :
                    type == Date.class ? DateUtil.format((Date) propertyValue) : propertyValue.toString());
        }
        return map;
    }

}
