package cn.jinty.util.object;

import cn.jinty.util.DateUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean - 工具类
 * <p>
 * Bean就是一个数据对象，只包含属性和基础方法(get/set/toString等)，而没有其它功能
 * <p>
 * Bean的解析有"反射"和"内省"两种方式，一般使用"内省"，好处如下：
 * 1、"内省"获取public修饰的get/set，以此访问类的属性，不会破坏类的封装性
 * 2、"内省"有缓存机制，除了首次执行耗时比较长，后续执行耗时都比较短
 *
 * @author Jinty
 * @date 2022/4/29
 **/
public final class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 将Map转为Bean
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
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            String propertyValue = map.get(propertyName);
            if (propertyValue == null) {
                continue;
            }
            Method setter = descriptor.getWriteMethod();
            if (setter == null) {
                continue;
            }
            Class<?> type = descriptor.getPropertyType();
            setter.invoke(bean, ObjectUtil.strToObj(propertyValue, type));
        }
        return bean;
    }

    /**
     * 将Bean转为Map
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
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            // 排除类本身
            if ("class".equals(propertyName)) {
                continue;
            }
            Method getter = descriptor.getReadMethod();
            if (getter == null) {
                continue;
            }
            Object propertyValue = getter.invoke(bean);
            Class<?> type = descriptor.getPropertyType();
            if (propertyValue == null) {
                map.put(propertyName, null);
            } else {
                map.put(propertyName, type == Date.class ? DateUtil.format((Date) propertyValue) : propertyValue.toString());
            }
        }
        return map;
    }

    /**
     * Bean字段拷贝(浅拷贝)，基于内省实现
     *
     * @param source 来源对象
     * @param target 目标对象
     * @throws Exception 异常
     */
    public static void copy(Object source, Object target) throws Exception {
        if (source == null || target == null) {
            return;
        }
        // 来源类字段
        BeanInfo sourceBeanInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] sourcePropertyDescriptors = sourceBeanInfo.getPropertyDescriptors();
        Map<String, PropertyDescriptor> sourcePropertyMap = new HashMap<>();
        for (PropertyDescriptor sourceProperty : sourcePropertyDescriptors) {
            sourcePropertyMap.put(sourceProperty.getName(), sourceProperty);
        }
        // 目标类字段
        BeanInfo targetBeanInfo = Introspector.getBeanInfo(target.getClass());
        PropertyDescriptor[] targetPropertyDescriptors = targetBeanInfo.getPropertyDescriptors();
        // 匹配名称相同的字段，判断可赋值则直接赋值
        for (PropertyDescriptor targetProperty : targetPropertyDescriptors) {
            PropertyDescriptor sourceProperty = sourcePropertyMap.get(targetProperty.getName());
            if (sourceProperty == null
                    || !ClassUtil.isAssignableFrom(targetProperty.getPropertyType(), sourceProperty.getPropertyType())) {
                continue;
            }
            Method getter = sourceProperty.getReadMethod();
            if (getter == null) {
                continue;
            }
            Object value = getter.invoke(source);
            Method setter = targetProperty.getWriteMethod();
            if (setter == null) {
                continue;
            }
            setter.invoke(target, value);
        }
    }

    /**
     * Bean字段拷贝(浅拷贝)，基于反射实现
     *
     * @param source 来源对象
     * @param target 目标对象
     * @throws Exception 异常
     */
    public static void copyByReflect(Object source, Object target) throws Exception {
        if (source == null || target == null) {
            return;
        }
        // 来源类字段
        List<Field> sourceFields = ClassUtil.getAllField(source.getClass());
        Map<String, Field> sourceFieldMap = new HashMap<>();
        for (Field sourceField : sourceFields) {
            sourceFieldMap.put(sourceField.getName(), sourceField);
        }
        // 目标类字段
        List<Field> targetFields = ClassUtil.getAllField(target.getClass());
        // 匹配名称相同的字段，判断可赋值则直接赋值
        for (Field targetField : targetFields) {
            Field sourceField = sourceFieldMap.get(targetField.getName());
            if (sourceField == null || !ClassUtil.isAssignableFrom(targetField.getType(), sourceField.getType())) {
                continue;
            }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            targetField.set(target, sourceField.get(source));
        }
    }

}
