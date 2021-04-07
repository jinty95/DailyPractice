package cn.jinty.design.strategy;

/**
 * 运算 - 减
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class OperationSub implements Operation{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
