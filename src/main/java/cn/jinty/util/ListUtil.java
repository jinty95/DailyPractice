package cn.jinty.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表 - 工具类
 *
 * @author Jinty
 * @date 2020/12/8
 **/
public final class ListUtil {

    /**
     * 列表按数量分组
     *
     * @param list 原始列表
     * @param num  每组个数
     * @param <T>  泛型
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
     * @param <T>   泛型
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
     * 是否为空
     *
     * @param list 列表
     * @param <T>  泛型
     * @return 是否为空
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    /**
     * 是否非空
     *
     * @param list 列表
     * @param <T>  泛型
     * @return 是否非空
     */
    @SuppressWarnings("unused")
    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }

    /**
     * 列表 -> 字符串
     *
     * @param list 列表
     * @param <T>  泛型
     * @return 字符串
     */
    public static <T> String toString(List<T> list) {
        return toString(list, ",");
    }

    /**
     * 列表 -> 字符串
     *
     * @param list     列表
     * @param separate 分隔符
     * @param <T>      泛型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String separate) {
        return toString(list, separate, null, null);
    }

    /**
     * 列表 -> 字符串
     *
     * @param list     列表
     * @param separate 分隔符
     * @param open     全局起始符
     * @param close    全局终止符
     * @param <T>      泛型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String separate, String open, String close) {
        return toString(list, separate, open, close, null, null);
    }

    /**
     * 列表 -> 字符串
     *
     * @param list     列表
     * @param separate 分隔符
     * @param open     全局起始符
     * @param close    全局终止符
     * @param before   元素前修饰符
     * @param after    元素后修饰符
     * @param <T>      泛型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String separate, String open, String close, String before, String after) {
        if (isEmpty(list)) {
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
            if (separate != null && i != list.size() - 1) {
                sb.append(separate);
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
     * @param s        字符串
     * @param separate 分隔符
     * @return 列表
     */
    public static List<String> fromString(String s, String separate) {
        if (StringUtil.isEmpty(s)) {
            return new ArrayList<>();
        }
        return asList(s.split(separate));
    }

}
