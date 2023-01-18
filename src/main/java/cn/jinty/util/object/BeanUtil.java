package cn.jinty.util.object;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import net.sf.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象拷贝 - 工具类
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
     * @param clz    目标类
     * @param <T>    泛型
     * @return 目标对象
     */
    public static <T> T copy(Object source, Class<T> clz) throws InstantiationException, IllegalAccessException {
        T target = clz.newInstance();
        copy(source, target);
        return target;
    }

    /**
     * 浅拷贝 - 批量
     *
     * @param sources 源对象列表
     * @param clz     目标类
     * @param <T>     泛型
     * @return 目标对象列表
     */
    public static <T> List<T> copyList(List<?> sources, Class<T> clz) throws InstantiationException, IllegalAccessException {
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
     * @param clz    目标类
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
     * @param clz     目标类
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

}
