package cn.jinty.util;

import cn.jinty.exception.ValidateException;

import java.util.Collection;

/**
 * 校验 - 工具类
 *
 * @author Jinty
 * @date 2021/12/8
 **/
public final class ValidateUtil {

    private ValidateUtil() {
    }

    /**
     * 非空校验 - 对象
     *
     * @param obj 对象
     */
    public static void notNull(Object obj) {
        notNull(obj, "对象不能为空");
    }

    /**
     * 非空校验 - 对象
     *
     * @param obj 对象
     * @param msg 提示信息
     */
    public static void notNull(Object obj, String msg) {
        notFalse(obj != null, msg);
    }

    /**
     * 非空校验 - 字符串
     *
     * @param str 字符串
     */
    public static void notBlank(String str) {
        notBlank(str, "字符串不能为空");
    }

    /**
     * 非空校验 - 字符串
     *
     * @param str 字符串
     * @param msg 提示信息
     */
    public static void notBlank(String str, String msg) {
        notFalse(str != null && str.length() != 0 && str.trim().length() != 0, msg);
    }

    /**
     * 非空校验 - 集合
     *
     * @param coll 集合
     * @param <T>  类型
     */
    public static <T> void notEmpty(Collection<T> coll) {
        notEmpty(coll, "集合不能为空");
    }

    /**
     * 非空校验 - 集合
     *
     * @param coll 集合
     * @param msg  提示信息
     * @param <T>  类型
     */
    public static <T> void notEmpty(Collection<T> coll, String msg) {
        notFalse(coll != null && !coll.isEmpty(), msg);
    }

    /**
     * 非假校验
     *
     * @param flag 任何条件判定的结果
     * @param msg  提示信息
     */
    public static void notFalse(boolean flag, String msg) {
        if (!flag) {
            throw new ValidateException(msg);
        }
    }

}
