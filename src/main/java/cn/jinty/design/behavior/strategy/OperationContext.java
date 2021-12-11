package cn.jinty.design.behavior.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 算术运算 - 上下文
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class OperationContext {

    private final List<Operation> operations;

    public OperationContext() {
        operations = new ArrayList<>();
        operations.add(new OperationAdd());
        operations.add(new OperationSub());
    }

    public long execute(OperationEnum operationEnum, int num1, int num2) {
        for (Operation operation : operations) {
            if (operation.trigger(operationEnum)) {
                return operation.execute(num1, num2);
            }
        }
        throw new UnsupportedOperationException("不支持的操作");
    }

}
