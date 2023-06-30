package cn.jinty.util.math;

/**
 * 数字类型转换 - 工具类
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public final class NumberCastUtil {

    private NumberCastUtil() {
    }

    // 类型转换异常提示
    private static final String TYPE_CAST_ERROR_INFO = "cannot cast type from [%s] to [%s]";

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
    public static <T, V extends Number> V cast(T num, Class<V> toType) {
        if (num == null || toType == null) {
            return null;
        }
        Class<?> fromType = num.getClass();
        if (fromType == toType) {
            return (V) num;
        }
        if (fromType == Byte.class) {
            return castByte((Byte) num, toType);
        }
        if (fromType == Short.class) {
            return castShort((Short) num, toType);
        }
        if (fromType == Integer.class) {
            return castInteger((Integer) num, toType);
        }
        if (fromType == Long.class) {
            return castLong((Long) num, toType);
        }
        if (fromType == Float.class) {
            return castFloat((Float) num, toType);
        }
        if (fromType == Double.class) {
            return castDouble((Double) num, toType);
        }
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

    /**
     * 数字类型转换
     * 支持Byte转换为其它6种基本数字类型
     *
     * @param num    输入数字(Byte类型)
     * @param toType 输出数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <V extends Number> V castByte(Byte num, Class<V> toType) {
        if (num == null || toType == null) {
            return null;
        }
        Class<?> fromType = num.getClass();
        if (toType == Byte.class) {
            return (V) num;
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
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

    /**
     * 数字类型转换
     * 支持Short转换为其它6种基本数字类型
     *
     * @param num    输入数字(Short类型)
     * @param toType 输出数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <V extends Number> V castShort(Short num, Class<V> toType) {
        if (num == null || toType == null) {
            return null;
        }
        Class<?> fromType = num.getClass();
        if (toType == Byte.class) {
            return (V) Byte.valueOf(num.byteValue());
        }
        if (toType == Short.class) {
            return (V) num;
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
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

    /**
     * 数字类型转换
     * 支持Integer转换为其它6种基本数字类型
     *
     * @param num    输入数字(Integer类型)
     * @param toType 输出数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <V extends Number> V castInteger(Integer num, Class<V> toType) {
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
            return (V) num;
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
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

    /**
     * 数字类型转换
     * 支持Long转换为其它6种基本数字类型
     *
     * @param num    输入数字(Long类型)
     * @param toType 输出数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <V extends Number> V castLong(Long num, Class<V> toType) {
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
            return (V) num;
        }
        if (toType == Float.class) {
            return (V) Float.valueOf(num.floatValue());
        }
        if (toType == Double.class) {
            return (V) Double.valueOf(num.doubleValue());
        }
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

    /**
     * 数字类型转换
     * 支持Float转换为其它6种基本数字类型
     *
     * @param num    输入数字(Float类型)
     * @param toType 输出数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <V extends Number> V castFloat(Float num, Class<V> toType) {
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
            return (V) num;
        }
        if (toType == Double.class) {
            return (V) Double.valueOf(num.doubleValue());
        }
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

    /**
     * 数字类型转换
     * 支持Double转换为其它6种基本数字类型
     *
     * @param num    输入数字(Double类型)
     * @param toType 输出数字类型
     * @param <V>    输出数字类型
     * @return 输出数字
     */
    @SuppressWarnings("unchecked")
    public static <V extends Number> V castDouble(Double num, Class<V> toType) {
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
            return (V) num;
        }
        throw new ClassCastException(String.format(TYPE_CAST_ERROR_INFO, fromType.getName(), toType.getName()));
    }

}
