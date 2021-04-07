package cn.jinty.design.strategy;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println(context.doAction(1,5));
        context.setOperation(new OperationSub());
        System.out.println(context.doAction(100,50));
    }
}
