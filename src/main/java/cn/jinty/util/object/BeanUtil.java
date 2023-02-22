package cn.jinty.util.object;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import net.sf.cglib.beans.BeanCopier;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 浅拷贝
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copy(Object source, Object target) {
        BeanCopier.create(source.getClass(), target.getClass(), false).copy(source, target, null);
    }

    /**
     * 浅拷贝
     *
     * @param source 源对象
     * @param clz    目标类型
     * @param <T>    泛型
     * @return 目标对象
     */
    public static <T> T copy(Object source, Class<T> clz)
            throws InstantiationException, IllegalAccessException {
        T target = clz.newInstance();
        copy(source, target);
        return target;
    }

    /**
     * 浅拷贝 - 批量
     *
     * @param sources 源对象列表
     * @param clz     目标类型
     * @param <T>     泛型
     * @return 目标对象列表
     */
    public static <T> List<T> copyList(List<?> sources, Class<T> clz)
            throws InstantiationException, IllegalAccessException {
        List<T> targets = new ArrayList<>();
        for (Object source : sources) {
            targets.add(copy(source, clz));
        }
        return targets;
    }

    private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    /**
     * 深拷贝
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void deepCopy(Object source, Object target) {
        MAPPER.map(source, target);
    }

    /**
     * 深拷贝
     *
     * @param source 源对象
     * @param clz    目标类型
     * @param <T>    泛型
     * @return 目标对象
     */
    public static <T> T deepCopy(Object source, Class<T> clz) {
        return MAPPER.map(source, clz);
    }

    /**
     * 深拷贝 - 批量
     *
     * @param sources 源对象列表
     * @param clz     目标类型
     * @param <T>     泛型
     * @return 目标对象列表
     */
    public static <T> List<T> deepCopyList(List<?> sources, Class<T> clz) {
        List<T> targets = new ArrayList<>();
        for (Object source : sources) {
            targets.add(deepCopy(source, clz));
        }
        return targets;
    }

    /**
     * 将一个Map转为一个Bean
     * (实现上是一种浅拷贝)
     *
     * @param map   Map对象
     * @param clazz 目标类型
     * @param <T>   泛型
     * @return Bean对象
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz)
            throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        if (map == null) {
            return null;
        }
        T bean = clazz.newInstance();
        if (map.isEmpty()) {
            return bean;
        }
        // 借助"内省"获取类的属性及get/set方法
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            Object propertyValue = map.get(propertyName);
            if (propertyValue == null) {
                continue;
            }
            Method setter = descriptor.getWriteMethod();
            setter.invoke(bean, propertyValue);
        }
        return bean;
    }

    /**
     * 将一个Bean转为一个Map
     *
     * @param bean Bean对象
     * @return Map对象
     */
    public static Map<String, Object> beanToMap(Object bean)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        // 借助"内省"获取类的属性及get/set方法
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
            map.put(propertyName, propertyValue);
        }
        return map;
    }

}
