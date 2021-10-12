package cn.jinty.design.behavior.chain;

/**
 * 控制台日志
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class ConsoleLogger extends Logger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    public void write(int level, String msg) {
        System.out.println("ConsoleLogger write : level=" + level + ",msg=" + msg);
    }

}
