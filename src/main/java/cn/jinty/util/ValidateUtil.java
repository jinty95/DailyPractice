package cn.jinty.util;

import java.util.Collection;

/**
 * 校验 - 工具类
 *
 * @author Jinty
 * @date 2021/12/8
 **/
public final class ValidateUtil {

    /**
     * 非空校验 - 对象
     *
     * @param obj 对象
     * @throws Exception 异常
     */
    public static void notNull(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("对象不能为空");
        }
    }

    /**
     * 非空校验 - 对象
     *
     * @param obj 对象
     * @param msg 提示信息
     * @throws Exception 异常
     */
    public static void notNull(Object obj, String msg) throws Exception {
        if (obj == null) {
            throw new Exception(msg);
        }
    }

    /**
     * 非空校验 - 字符串
     *
     * @param str 字符串
     * @throws Exception 异常
     */
    public static void notBlank(String str) throws Exception {
        if (str == null || str.length() == 0 || str.trim().length() == 0) {
            throw new Exception("字符串不能为空");
        }
    }

    /**
     * 非空校验 - 字符串
     *
     * @param str 字符串
     * @param msg 提示信息
     * @throws Exception 异常
     */
    public static void notBlank(String str, String msg) throws Exception {
        if (str == null || str.length() == 0 || str.trim().length() == 0) {
            throw new Exception(msg);
        }
    }

    /**
     * 非空校验 - 集合
     *
     * @param collection 集合
     * @param <T>        泛型
     * @throws Exception 异常
     */
    public static <T> void notEmpty(Collection<T> collection) throws Exception {
        if (collection == null || collection.isEmpty()) {
            throw new Exception("集合不能为空");
        }
    }

    /**
     * 非空校验 - 集合
     *
     * @param collection 集合
     * @param msg        提示信息
     * @param <T>        泛型
     * @throws Exception 异常
     */
    public static <T> void notEmpty(Collection<T> collection, String msg) throws Exception {
        if (collection == null || collection.isEmpty()) {
            throw new Exception(msg);
        }
    }

}
