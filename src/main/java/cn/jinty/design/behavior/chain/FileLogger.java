package cn.jinty.design.behavior.chain;

import java.util.Date;

/**
 * 文件日志
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class FileLogger extends Logger {

    public FileLogger(LEVEL level) {
        this.level = level;
    }

    @Override
    public boolean canWrite(LEVEL level) {
        return level.getLevel() == this.level.getLevel();
    }

    @Override
    public void write(LEVEL level, String msg) {
        System.out.printf("[%s][%s][%s]: %s%n", this.getClass().getSimpleName(), new Date(), level, msg);
    }

}
