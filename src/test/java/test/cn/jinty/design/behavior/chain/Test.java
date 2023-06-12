package test.cn.jinty.design.behavior.chain;

import cn.jinty.design.behavior.chain.ConsoleLogger;
import cn.jinty.design.behavior.chain.FileLogger;
import cn.jinty.design.behavior.chain.Logger;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        Logger logger = new ConsoleLogger(Logger.LEVEL.INFO);
        logger.log(Logger.LEVEL.DEBUG, "呵呵呵呵");
        logger.log(Logger.LEVEL.INFO, "哈哈哈哈");
        logger.log(Logger.LEVEL.WARN, "嘻嘻嘻嘻");
        logger.log(Logger.LEVEL.ERROR, "嘿嘿嘿嘿");
        System.out.println();
        logger = getLogChain();
        logger.log(Logger.LEVEL.DEBUG, "呵呵呵呵");
        logger.log(Logger.LEVEL.INFO, "哈哈哈哈");
        logger.log(Logger.LEVEL.WARN, "嘻嘻嘻嘻");
        logger.log(Logger.LEVEL.ERROR, "嘿嘿嘿嘿");
    }

    private static Logger getLogChain() {
        Logger logger1 = new FileLogger(Logger.LEVEL.TRACE);
        Logger logger2 = new FileLogger(Logger.LEVEL.DEBUG);
        Logger logger3 = new FileLogger(Logger.LEVEL.INFO);
        Logger logger4 = new FileLogger(Logger.LEVEL.WARN);
        Logger logger5 = new FileLogger(Logger.LEVEL.ERROR);
        logger1.setNextLogger(logger2);
        logger2.setNextLogger(logger3);
        logger3.setNextLogger(logger4);
        logger4.setNextLogger(logger5);
        return logger1;
    }

}
