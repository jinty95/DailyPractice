package cn.jinty.util.math;

/**
 * 数字加法 - 工具类
 *
 * @author Jinty
 * @date 2023/6/30
 **/
public final class NumberAddUtil {

    private NumberAddUtil() {
    }

    /**
     * 加法 - Byte
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public static Byte add(Byte a, Byte b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return (byte) (a + b);
    }

    /**
     * 加法 - Short
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public static Short add(Short a, Short b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return (short) (a + b);
    }

    /**
     * 加法 - Integer
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public static Integer add(Integer a, Integer b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

    /**
     * 加法 - Long
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public static Long add(Long a, Long b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

    /**
     * 加法 - Float
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public static Float add(Float a, Float b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

    /**
     * 加法 - Double
     *
     * @param a 数1
     * @param b 数2
     * @return 两数之和
     */
    public static Double add(Double a, Double b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

}
