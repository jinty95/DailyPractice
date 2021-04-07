package cn.jinty.design.facade;

/**
 * 内存
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Ddr implements Computer{

    @Override
    public void boot() {
        System.out.println("boot ddr of computer");
    }
}
