package cn.jinty.design.behavior.strategy;

/**
 * 运算上下文
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Context {

    //运算
    private Operation operation;

    //构造器
    public Context(Operation operation){
        this.operation = operation;
    }

    //设置运算
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    //执行运算
    public long doAction(int num1, int num2){
        return operation.doOperation(num1,num2);
    }

}
