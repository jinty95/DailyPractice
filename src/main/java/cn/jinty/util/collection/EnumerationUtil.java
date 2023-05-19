package cn.jinty.util.collection;

import java.util.*;

/**
 * Enumeration - 工具类
 * 注意：Enum和Enumeration是两个不同的概念，Enum是常量枚举，Enumeration是用于遍历数据集的工具(功能与Iterator相同)
 *
 * @author Jinty
 * @date 2023/5/18
 **/
public final class EnumerationUtil {

    private EnumerationUtil() {
    }

    /**
     * 是否为空
     *
     * @param data Enumeration
     * @return 是否
     */
    public static boolean isEmpty(Enumeration<?> data) {
        return data == null || !data.hasMoreElements();
    }

    /**
     * 是否非空
     *
     * @param data Enumeration
     * @return 是否
     */
    public static boolean isNotEmpty(Enumeration<?> data) {
        return !isEmpty(data);
    }

    /**
     * 转为字符串
     *
     * @param data Enumeration
     * @return 字符串
     */
    public static String toString(Enumeration<?> data) {
        return toString(data, ",");
    }

    /**
     * 转为字符串
     *
     * @param data      Enumeration
     * @param separator 分隔符
     * @return 字符串
     */
    public static String toString(Enumeration<?> data, String separator) {
        if (isEmpty(data)) {
            return "";
        }
        if (separator == null) {
            separator = "";
        }
        StringBuilder sb = new StringBuilder();
        while (data.hasMoreElements()) {
            sb.append(data.nextElement().toString()).append(separator);
        }
        return sb.substring(0, sb.length() - separator.length());
    }

    /**
     * 转为List
     *
     * @param data Enumeration
     * @param <T>  类型
     * @return List
     */
    public static <T> List<T> toList(Enumeration<T> data) {
        if (isEmpty(data)) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        while (data.hasMoreElements()) {
            list.add(data.nextElement());
        }
        return list;
    }

    /**
     * 转为Set
     *
     * @param data Enumeration
     * @param <T>  类型
     * @return Set
     */
    public static <T> Set<T> toSet(Enumeration<T> data) {
        if (isEmpty(data)) {
            return new HashSet<>();
        }
        Set<T> set = new HashSet<>();
        while (data.hasMoreElements()) {
            set.add(data.nextElement());
        }
        return set;
    }

}
