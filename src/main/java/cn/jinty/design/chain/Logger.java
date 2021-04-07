package cn.jinty.design.chain;

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

    public void setNextLogger(Logger logger){
        this.nextLogger = logger;
    }

    public void log(int level,String msg){
        if(this.level <= level){
            wirte(msg);
        }
        if(nextLogger!=null){
            nextLogger.log(level,msg);
        }
    }

    public abstract void wirte(String msg);

}
