package cn.jinty.design.behavior.chain;

/**
 * 日志抽象类
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public abstract class Logger {

    public static final int INFO = 1;
    public static final int WARN = 2;
    public static final int ERROR = 3;

    protected int level;

    protected Logger nextLogger;

    public void setNextLogger(Logger logger) {
        this.nextLogger = logger;
    }

    public void log(int level, String msg) {
        //日志级别大于等于当前设置的级别才可以打印
        if (this.level <= level) {
            write(level, msg);
        }
        if (nextLogger != null) {
            nextLogger.log(level, msg);
        }
    }

    public abstract void write(int level, String msg);

}
