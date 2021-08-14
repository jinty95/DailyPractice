package cn.jinty.design.struct.facade;

/**
 * CPU
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Cpu implements Computer{

    @Override
    public void boot() {
        System.out.println("boot cpu of computer");
    }
}
