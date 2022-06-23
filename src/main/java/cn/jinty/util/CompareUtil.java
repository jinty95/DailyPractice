package cn.jinty.util;

import java.util.Comparator;

/**
 * 比较 - 工具类
 *
 * @author Jinty
 * @date 2022/6/23
 **/
public final class CompareUtil {

    /**
     * 可空比较
     * (支持被比较的操作数为空，且空为最小)
     *
     * @param o1  操作数1
     * @param o2  操作数2
     * @param <T> 实现Comparable的任意类
     * @return 比较结果，0表示相等，-1表示小于，1表示大于
     */
    public static <T extends Comparable<T>> int nullableCompare(T o1, T o2) {
        if (o1 == null) {
            return o2 == null ? 0 : -1;
        }
        return o2 == null ? 1 : o1.compareTo(o2);
    }

    /**
     * 可空比较器
     *
     * @param <T> 实现Comparable的任意类
     * @return 比较器
     */
    public static <T extends Comparable<T>> Comparator<T> nullableComparator() {
        return (CompareUtil::nullableCompare);
    }

}
