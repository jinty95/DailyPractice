package test.cn.jinty.design.struct.facade;

import cn.jinty.design.struct.facade.ComputerStarter;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        ComputerStarter starter = new ComputerStarter();
        starter.bootCpu();
        starter.bootDdr();
    }
}
