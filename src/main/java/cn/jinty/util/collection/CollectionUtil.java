package cn.jinty.util.collection;

import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * 数据集 - 工具类
 * <p>
 * Collection和Set在中文语境下都翻译成"集合"，很容易混淆，这里按以下区分：
 * Collection -> 数据集
 * Set -> 集合，指不包含重复项的数据集
 *
 * @author Jinty
 * @date 2023/2/28
 **/
public final class CollectionUtil {

    private CollectionUtil() {
    }

    /**
     * 是否为空
     *
     * @param coll 数据集
     * @return 是否
     */
    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 是否非空
     *
     * @param coll 数据集
     * @return 是否
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 获取大小
     *
     * @param coll 数据集
     * @return 大小
     */
    public static int size(Collection<?> coll) {
        return coll == null ? 0 : coll.size();
    }

    /**
     * 从数据集中挑选出符合条件的元素
     *
     * @param coll      数据集
     * @param condition 条件
     * @param result    输出结果
     * @param <T>       输入数据集的元素类型
     * @param <R>       输出类型
     * @return 输出结果
     */
    public static <T, R extends Collection<? super T>> R select(Collection<T> coll, Predicate<T> condition, @NotNull R result) {
        if (isEmpty(coll)) {
            return result;
        }
        if (condition == null) {
            result.addAll(coll);
            return result;
        }
        for (T one : coll) {
            if (condition.test(one)) {
                result.add(one);
            }
        }
        return result;
    }

}
