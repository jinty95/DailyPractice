package test.cn.jinty.design.behavior.strategy;

import cn.jinty.design.behavior.strategy.Context;
import cn.jinty.design.behavior.strategy.OperationAdd;
import cn.jinty.design.behavior.strategy.OperationSub;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        //加法策略
        Context context = new Context(new OperationAdd());
        System.out.println(context.doAction(1,5));
        //减法策略
        context.setOperation(new OperationSub());
        System.out.println(context.doAction(100,50));
    }

}
