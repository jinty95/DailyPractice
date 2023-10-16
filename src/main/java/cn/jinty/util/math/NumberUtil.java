package cn.jinty.util.math;

import cn.jinty.util.string.StringUtil;

/**
 * 数字 - 工具类
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public final class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 数字类型转换
     * 支持6种基本数字类型之间的任意相互转换，包括：Byte, Short, Integer, Long, Float, Double
     *
     * @param num    输入数字
     * @param toType 输出数字类型
     * @param <T>    输入数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number, V extends Number> V castType(T num, Class<V> toType) {
        if (num == null || toType == null) {
            return null;
        }
        Class<?> fromType = num.getClass();
        if (toType == Byte.class) {
            return (V) Byte.valueOf(num.byteValue());
        }
        if (toType == Short.class) {
            return (V) Short.valueOf(num.shortValue());
        }
        if (toType == Integer.class) {
            return (V) Integer.valueOf(num.intValue());
        }
        if (toType == Long.class) {
            return (V) Long.valueOf(num.longValue());
        }
        if (toType == Float.class) {
            return (V) Float.valueOf(num.floatValue());
        }
        if (toType == Double.class) {
            return (V) Double.valueOf(num.doubleValue());
        }
        throw new ClassCastException(String.format("cannot cast type from [%s] to [%s]", fromType.getName(), toType.getName()));
    }

    /**
     * 数字包装类加法，支持空
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T add(T a, T b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        Class<?> type = a.getClass();
        if (type == Byte.class) {
            return (T) Byte.valueOf((byte) (a.byteValue() + b.byteValue()));
        }
        if (type == Short.class) {
            return (T) Short.valueOf((short) (a.shortValue() + b.shortValue()));
        }
        if (type == Integer.class) {
            return (T) Integer.valueOf(a.intValue() + b.intValue());
        }
        if (type == Long.class) {
            return (T) Long.valueOf((a.longValue() + b.longValue()));
        }
        if (type == Float.class) {
            return (T) Float.valueOf(a.floatValue() + b.floatValue());
        }
        if (type == Double.class) {
            return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
        }
        throw new IllegalArgumentException(String.format("do not support add for type [%s]", type.getName()));
    }

    /**
     * 字符串转为数字
     *
     * @param s    字符串
     * @param type 数字类型
     * @param <T>  数字类型
     * @return 数字
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T valueOf(String s, Class<T> type) {
        if (StringUtil.isBlank(s) || type == null) {
            return null;
        }
        if (type == Byte.class) {
            return (T) Byte.valueOf(s);
        }
        if (type == Short.class) {
            return (T) Short.valueOf(s);
        }
        if (type == Integer.class) {
            return (T) Integer.valueOf(s);
        }
        if (type == Long.class) {
            return (T) Long.valueOf(s);
        }
        if (type == Float.class) {
            return (T) Float.valueOf(s);
        }
        if (type == Double.class) {
            return (T) Double.valueOf(s);
        }
        throw new IllegalArgumentException(String.format("cannot parse string to number for type [%s]", type.getName()));
    }

}
