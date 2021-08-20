package cn.jinty.design.behavior.strategy;

/**
 * 运算接口
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public interface Operation {

    /**
     * 执行运算
     *
     * @param num1 操作数1
     * @param num2 操作数2
     * @return 结果
     */
    long doOperation(int num1,int num2);

}
