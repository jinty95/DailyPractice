package cn.jinty.design.facade;

/**
 * 电脑启动器
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class ComputerStarter {

    private Computer cpu;
    private Computer ddr;

    ComputerStarter(){
        cpu = new Cpu();
        ddr = new Ddr();
    }

    public void bootCpu(){
        cpu.boot();
    }

    public void bootDdr(){
        ddr.boot();
    }
}
