package cn.jinty.design.behavior.strategy;

/**
 * 算术运算 - 加
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class OperationAdd implements Operation {

    @Override
    public boolean trigger(OperationEnum operationEnum) {
        return OperationEnum.ADD == operationEnum;
    }

    @Override
    public long execute(int num1, int num2) {
        return num1 + num2;
    }

}
