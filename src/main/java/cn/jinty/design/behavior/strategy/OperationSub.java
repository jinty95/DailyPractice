package cn.jinty.design.behavior.strategy;

/**
 * 运算实现 - 减
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class OperationSub implements Operation{

    @Override
    public long doOperation(int num1, int num2) {
        return num1 - num2;
    }

}
