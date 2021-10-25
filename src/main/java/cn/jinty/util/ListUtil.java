package cn.jinty.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表工具
 *
 * @author jinty
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
     * 列表分页
     *
     * @param list     列表
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param <T>      泛型
     * @return 单页数据
     */
    public static <T> List<T> page(List<T> list, int pageNum, int pageSize) {
        //输入校验
        if (pageNum < 0 || pageSize < 0) {
            throw new IllegalArgumentException("pageNum or pageSize must great than 0");
        }
        if (isEmpty(list)) {
            return new ArrayList<>();
        }
        //起始索引
        int pageStart = (pageNum - 1) * pageSize;
        if (pageStart >= list.size()) {
            return new ArrayList<>();
        }
        //终止索引
        int pageEnd = pageStart + pageSize;
        if (pageEnd > list.size()) {
            pageEnd = list.size();
        }
        return list.subList(pageStart, pageEnd);
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
        return toString(list, "[", "]", ", ");
    }

    /**
     * 列表 -> 字符串
     *
     * @param list     列表
     * @param <T>      泛型
     * @param open     全局起始符
     * @param close    全局终止符
     * @param separate 分隔符
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String open, String close, String separate) {
        return toString(list, open, close, separate, null, null);
    }

    /**
     * 列表 -> 字符串
     *
     * @param list     列表
     * @param open     全局起始符
     * @param close    全局终止符
     * @param separate 分隔符
     * @param before   元素前修饰符
     * @param after    元素后修饰符
     * @param <T>      泛型
     * @return 字符串
     */
    public static <T> String toString(List<T> list, String open, String close, String separate, String before, String after) {
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

}
