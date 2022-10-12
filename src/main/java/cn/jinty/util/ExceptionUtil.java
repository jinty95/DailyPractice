package cn.jinty.util;

/**
 * 异常 - 工具类
 *
 * @author Jinty
 * @date 2022/7/8
 **/
public final class ExceptionUtil {

    private ExceptionUtil() {
    }

    /**
     * 获取异常信息
     *
     * @param e 异常
     * @return 异常信息
     */
    public static String getMessage(Throwable e) {
        return e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
    }

    /**
     * 获取最根本的异常信息
     *
     * @param e 异常
     * @return 最根本的异常信息
     */
    public static String getDeepMessage(Throwable e) {
        return e.getCause() != null ? getDeepMessage(e.getCause()) : getMessage(e);
    }

    /**
     * 获取异常堆栈
     *
     * @param e 异常
     * @return 异常堆栈
     */
    public static String getStackTrace(Throwable e) {
        return getStackTrace(e, false);
    }

    /**
     * 获取异常堆栈
     *
     * @param e       异常
     * @param isCause 是否为另一个异常的原因
     * @return 异常堆栈
     */
    private static String getStackTrace(Throwable e, boolean isCause) {
        StringBuilder sb = new StringBuilder();
        if (isCause) {
            sb.append("Caused by: ");
        }
        sb.append(e.getClass().getName());
        if (e.getMessage() != null) {
            sb.append(": ").append(e.getMessage());
        }
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(("\n\tat ")).append(element.toString());
        }
        if (e.getCause() != null) {
            sb.append("\n").append(getStackTrace(e.getCause(), true));
        }
        return sb.toString();
    }

}
