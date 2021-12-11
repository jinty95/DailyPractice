package cn.jinty.design.behavior.strategy;

/**
 * 算术运算 - 接口
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public interface Operation {

    /**
     * 是否触发执行
     *
     * @param operationEnum 运算
     * @return 是否
     */
    boolean trigger(OperationEnum operationEnum);

    /**
     * 执行
     *
     * @param num1 操作数1
     * @param num2 操作数2
     * @return 结果
     */
    long execute(int num1, int num2);

}
