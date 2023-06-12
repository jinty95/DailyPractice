package cn.jinty.design.behavior.chain;

/**
 * 日志记录器 - 抽象类
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public abstract class Logger {

    // 日志级别 - 枚举
    public enum LEVEL {
        TRACE(1), DEBUG(2), INFO(3), WARN(4), ERROR(5);

        private final int level;

        LEVEL(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    // 日志级别
    protected LEVEL level;

    // 下一个日志记录器
    protected Logger nextLogger;

    // 是否可以输出日志
    protected abstract boolean canWrite(LEVEL level);

    // 输出日志
    protected abstract void write(LEVEL level, String msg);

    // 设置下一个日志记录器
    public void setNextLogger(Logger logger) {
        this.nextLogger = logger;
    }

    // 打印指定级别的日志
    public void log(LEVEL level, String msg) {
        if (canWrite(level)) {
            write(level, msg);
        }
        if (nextLogger != null) {
            nextLogger.log(level, msg);
        }
    }

}
