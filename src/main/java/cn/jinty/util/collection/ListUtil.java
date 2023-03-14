package cn.jinty.util.collection;

import cn.jinty.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 列表 - 工具类
 *
 * @author Jinty
 * @date 2020/12/8
 **/
public final class ListUtil {

    private ListUtil() {
    }

    /**
     * 列表按数量分组
     *
     * @param list 原始列表
     * @param num  每组个数
     * @param <T>  类型
     * @return 分组结果
     */
    public static <T> List<List<T>> splitByNum(List<T> list, int num) {
        List<List<T>> splitList = new ArrayList<>();
        if (list.isEmpty()) {
            return splitList;
        }
        int size = list.size();
        int p = 0;
        while (p < size) {
            List<T> oneList = new ArrayList<>();
            for (int i = 0; i < num && p < size; i++) {
                oneList.add(list.get(p++));
            }
            splitList.add(oneList);
        }
        return splitList;
    }

    /**
     * 数组转列表
     *
     * @param array 数组
     * @param <T>   类型
     * @return 列表
     */
    @SafeVarargs
    @SuppressWarnings("all")
    public static <T> List<T> asList(T... array) {
        List<T> list = new ArrayList<>();
        for (T one : array) {
            list.add(one);
        }
        return list;
    }

    /**
     * 列表 -> 字符串
     *
     * @param list 列表
     * @param <T>  类型
     * @return 字符串
     */
    public static <T> String toString(List<T> list) {
        return toString(list, ",");
    }

    /**
     * 列表 -> 字符串
     *
     * @param list      列表
     * @param separator 分隔符
     * @param <T>       类型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String separator) {
        return toString(list, separator, null, null);
    }

    /**
     * 列表 -> 字符串
     *
     * @param list      列表
     * @param separator 分隔符
     * @param open      全局起始符
     * @param close     全局终止符
     * @param <T>       类型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String separator, String open, String close) {
        return toString(list, separator, open, close, null, null);
    }

    /**
     * 列表 -> 字符串
     *
     * @param list      列表
     * @param separator 分隔符
     * @param open      全局起始符
     * @param close     全局终止符
     * @param before    元素前修饰符
     * @param after     元素后修饰符
     * @param <T>       类型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String separator, String open, String close, String before, String after) {
        if (CollectionUtil.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (open != null) {
            sb.append(open);
        }
        for (int i = 0; i < list.size(); i++) {
            if (before != null) {
                sb.append(before);
            }
            sb.append(list.get(i));
            if (after != null) {
                sb.append(after);
            }
            if (separator != null && i != list.size() - 1) {
                sb.append(separator);
            }
        }
        if (close != null) {
            sb.append(close);
        }
        return sb.toString();
    }

    /**
     * 字符串 -> 列表
     *
     * @param s         字符串
     * @param separator 分隔符 (支持正则表达式)
     * @return 列表
     */
    public static List<String> fromString(String s, String separator) {
        return asList(StringUtil.split(s, separator));
    }

    /**
     * 从列表中挑选出符合条件的元素
     *
     * @param list      列表
     * @param condition 条件
     * @param <T>       类型
     * @return 符合条件的元素列表
     */
    public static <T> List<T> select(List<T> list, Predicate<T> condition) {
        return CollectionUtil.select(list, condition, new ArrayList<>());
    }

    /**
     * 求笛卡尔积
     *
     * @param lists 多个数据组
     * @param <T>   元素类型
     * @return 笛卡尔积
     */
    public static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
        List<List<T>> results = new ArrayList<>();
        return cartesianProduct(lists, lists.size() - 1, results);
    }

    /**
     * 求笛卡尔积
     *
     * @param lists   多个数据组
     * @param index   当前应处理的数据组
     * @param <T>     元素类型
     * @param results 笛卡尔积
     */
    private static <T> List<List<T>> cartesianProduct(List<List<T>> lists, int index, List<List<T>> results) {
        if (CollectionUtil.isEmpty(lists) || index < 0 || index >= lists.size()) {
            return results;
        }
        List<List<T>> nextResults = new ArrayList<>();
        for (T one : lists.get(index)) {
            if (CollectionUtil.isEmpty(results)) {
                nextResults.add(asList(one));
            } else {
                for (List<T> result : results) {
                    List<T> nextResult = new ArrayList<>(result);
                    nextResult.add(0, one);
                    nextResults.add(nextResult);
                }
            }
        }
        return cartesianProduct(lists, index - 1, nextResults);
    }

}
