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
    private static final String logTemplate1 = "[%s]执行失败：param=%s, error=%s, retry=%s";
    private static final String logTemplate2 = "[%s]执行失败：error=%s, retry=%s";

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
                String log = String.format(logTemplate1, desc, param, e.getMessage(), i);
                System.out.println(log);
                e.printStackTrace();
                i++;
            }
        } while (i <= retry);
        return null;
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
                String log = String.format(logTemplate1, desc, param, e.getMessage(), i);
                System.out.println(log);
                e.printStackTrace();
                i++;
            }
        } while (i <= retry);
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
                String log = String.format(logTemplate2, desc, e.getMessage(), i);
                System.out.println(log);
                e.printStackTrace();
                i++;
            }
        } while (i <= retry);
        return null;
    }

}
