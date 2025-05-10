package cn.jinty.util.math;

import cn.jinty.util.collection.CollectionUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

/**
 * BigDecimal - 工具类
 *
 * @author Jinty
 * @date 2025/5/10
 */
public final class BigDecimalUtil {

    private BigDecimalUtil() {
    }

    /* 对于无法计算的场景，统一返回0 */

    /**
     * 对象转为BigDecimal
     *
     * @param obj 对象
     * @return BigDecimal
     */
    public static BigDecimal valueOf(Object obj) {
        if (obj == null) {
            return BigDecimal.ZERO;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            System.out.printf("对象转为BigDecimal异常，obj=%s, error=%s%n", obj, e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    /**
     * 加法
     *
     * @param a 数字a
     * @param b 数字b
     * @return 和
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return BigDecimal.ZERO;
        }
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a.add(b);
    }

    /**
     * 减法
     *
     * @param a 数字a
     * @param b 数字b
     * @return 差
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return null;
        }
        if (a == null) {
            return BigDecimal.ZERO.subtract(b);
        }
        if (b == null) {
            return a;
        }
        return a.subtract(b);
    }

    /**
     * 乘法
     *
     * @param a 数字a
     * @param b 数字b
     * @return 积
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return BigDecimal.ZERO;
        }
        return a.multiply(b);
    }

    /**
     * 除法
     *
     * @param a 数字a
     * @param b 数字b
     * @return 商
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return divide(a, b, 10);
    }

    /**
     * 除法
     *
     * @param a     数字a
     * @param b     数字b
     * @param scale 精度（小数保留位）
     * @return 商
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b, int scale) {
        if (a == null || b == null) {
            return BigDecimal.ZERO;
        }
        return a.divide(b, scale, RoundingMode.HALF_UP);
    }

    /**
     * 加权平均计算
     * (值1 * 权重1 + 值2 * 权重2 + ...) / (权重1 + 权重2 + ...)
     *
     * @param list         数据对象列表
     * @param valueGetter  值获取方法
     * @param weightGetter 权重获取方法
     * @param <T>          数据对象类型
     * @return 计算结果
     */
    public static <T> BigDecimal calWeightAverage(List<T> list, Function<T, BigDecimal> valueGetter, Function<T, BigDecimal> weightGetter) {
        if (CollectionUtil.isEmpty(list) || valueGetter == null || weightGetter == null) {
            return BigDecimal.ZERO;
        }
        if (list.size() == 1) {
            return valueOf(valueGetter.apply(list.get(0)));
        }
        BigDecimal valueWeightSum = BigDecimal.ZERO;
        BigDecimal weightSum = BigDecimal.ZERO;
        for (T a : list) {
            valueWeightSum = add(valueWeightSum, multiply(valueGetter.apply(a), weightGetter.apply(a)));
            weightSum = add(weightSum, weightGetter.apply(a));
        }
        return divide(valueWeightSum, weightSum);
    }

}
