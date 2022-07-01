package cn.jinty.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 重试 - 工具类
 *
 * @author Jinty
 * @date 2022/6/23
 **/
public final class RetryUtil {

    // 日志模板
    private static final String LOG_TEMPLATE_1 = "[%s]执行失败：param=%s, retry=%s";
    private static final String LOG_TEMPLATE_2 = "[%s]执行失败：retry=%s";
    private static final String LOG_TEMPLATE_3 = "[%s]执行失败，重试%d次仍然失败";

    /**
     * 重试 - 单输入单输出函数
     *
     * @param func  函数
     * @param param 函数入参
     * @param desc  函数描述
     * @param retry 函数重试次数
     * @param <T>   函数入参类型
     * @param <R>   函数出参类型
     * @return 函数执行结果
     */
    public static <T, R> R retryForFunction(Function<T, R> func, T param, String desc, int retry) {
        int i = 0;
        do {
            try {
                return func.apply(param);
            } catch (Exception e) {
                System.out.println(String.format(LOG_TEMPLATE_1, desc, param, i));
                e.printStackTrace();
                i++;
            }
        } while (i <= retry);
        throw new RuntimeException(String.format(LOG_TEMPLATE_3, desc, retry));
    }

    /**
     * 重试 - 单输入无输出函数
     *
     * @param func  函数
     * @param param 函数入参
     * @param desc  函数描述
     * @param retry 函数重试次数
     * @param <T>   函数入参类型
     */
    public static <T> void retryForConsumer(Consumer<T> func, T param, String desc, int retry) {
        int i = 0;
        do {
            try {
                func.accept(param);
                return;
            } catch (Exception e) {
                System.out.println(String.format(LOG_TEMPLATE_1, desc, param, i));
                e.printStackTrace();
                i++;
            }
        } while (i <= retry);
        throw new RuntimeException(String.format(LOG_TEMPLATE_3, desc, retry));
    }

    /**
     * 重试 - 无输入单输出函数
     *
     * @param func  函数
     * @param desc  函数描述
     * @param retry 函数重试次数
     * @param <T>   函数出参类型
     * @return 函数执行结果
     */
    public static <T> T retryForSupplier(Supplier<T> func, String desc, int retry) {
        int i = 0;
        do {
            try {
                return func.get();
            } catch (Exception e) {
                System.out.println(String.format(LOG_TEMPLATE_2, desc, i));
                e.printStackTrace();
                i++;
            }
        } while (i <= retry);
        throw new RuntimeException(String.format(LOG_TEMPLATE_3, desc, retry));
    }

}
