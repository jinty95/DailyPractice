package test.cn.jinty.design.behavior.strategy;

import cn.jinty.design.behavior.strategy.OperationContext;
import cn.jinty.design.behavior.strategy.OperationEnum;

/**
 * 策略模式 - 算术运算 - 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        OperationContext operationContext = new OperationContext();
        System.out.println(operationContext.execute(OperationEnum.ADD, 1, 1));
        System.out.println(operationContext.execute(OperationEnum.ADD, 5, 25));
        System.out.println(operationContext.execute(OperationEnum.SUBTRACT, 100, 25));
        System.out.println(operationContext.execute(OperationEnum.SUBTRACT, 100, 101));
        System.out.println(operationContext.execute(OperationEnum.MULTIPLY, 9, 9));
    }

}
