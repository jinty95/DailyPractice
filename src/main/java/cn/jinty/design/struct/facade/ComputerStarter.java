package cn.jinty.design.struct.facade;

/**
 * 电脑启动器
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class ComputerStarter {

    private Computer cpu;
    private Computer ddr;

    public ComputerStarter(){
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
