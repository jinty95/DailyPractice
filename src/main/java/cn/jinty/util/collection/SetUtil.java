package cn.jinty.util.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 集合 - 工具类
 *
 * @author Jinty
 * @date 2021/11/13
 */
public final class SetUtil {

    private SetUtil() {
    }

    /**
     * 数组转集合
     *
     * @param array 数组
     * @return 集合
     */
    public static Set<Character> asSet(char[] array) {
        Set<Character> res = new HashSet<>();
        for (char one : array) {
            res.add(one);
        }
        return res;
    }

    /**
     * 数组转集合
     *
     * @param array 数组
     * @param <T>   泛型
     * @return 集合
     */
    @SuppressWarnings("all")
    public static <T> Set<T> asSet(T... array) {
        Set<T> res = new HashSet<>();
        for (T one : array) {
            res.add(one);
        }
        return res;
    }

    /**
     * 求并集
     *
     * @param set1 集合1
     * @param set2 集合2
     * @param <T>  泛型
     * @return 并集
     */
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        if (CollectionUtil.isEmpty(set1)) {
            if (CollectionUtil.isEmpty(set2)) {
                return new HashSet<>();
            }
            return new HashSet<>(set2);
        }
        if (CollectionUtil.isEmpty(set2)) {
            return new HashSet<>(set1);
        }
        Set<T> res = new HashSet<>(set1);
        res.addAll(set2);
        return res;
    }

    /**
     * 求交集
     *
     * @param set1 集合1
     * @param set2 集合2
     * @param <T>  泛型
     * @return 交集
     */
    public static <T> Set<T> intersect(Set<T> set1, Set<T> set2) {
        if (CollectionUtil.isEmpty(set1) || CollectionUtil.isEmpty(set2)) {
            return new HashSet<>();
        }
        Set<T> res = new HashSet<>();
        for (T one : set1) {
            if (set2.contains(one)) {
                res.add(one);
            }
        }
        return res;
    }

    /**
     * 求差集
     *
     * @param set1 集合1
     * @param set2 集合2
     * @param <T>  泛型
     * @return 差集 (元素属于集合1但不属于集合2)
     */
    public static <T> Set<T> subtract(Set<T> set1, Set<T> set2) {
        if (CollectionUtil.isEmpty(set1)) {
            return new HashSet<>();
        }
        if (CollectionUtil.isEmpty(set2)) {
            return new HashSet<>(set1);
        }
        Set<T> res = new HashSet<>();
        for (T one : set1) {
            if (!set2.contains(one)) {
                res.add(one);
            }
        }
        return res;
    }

    /**
     * 从集合中挑选出符合条件的元素
     *
     * @param set       集合
     * @param condition 条件
     * @param <T>       泛型
     * @return 符合条件的元素集合
     */
    public static <T> Set<T> select(Set<T> set, Predicate<T> condition) {
        return CollectionUtil.select(set, condition, new HashSet<>());
    }

}
