package design.chain;

import cn.jinty.design.chain.ConsoleLogger;
import cn.jinty.design.chain.FileLogger;
import cn.jinty.design.chain.Logger;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        Logger logger = getLogChain();
        logger.log(1,"哈哈哈哈");
        logger.log(3,"嘻嘻嘻嘻");
    }

    private static Logger getLogChain(){
        Logger consoleLogger = new ConsoleLogger(Logger.INFO);
        Logger fileLogger = new FileLogger(Logger.ERROR);
        consoleLogger.setNextLogger(fileLogger);
        return consoleLogger;
    }

}
