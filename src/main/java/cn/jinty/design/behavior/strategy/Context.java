package cn.jinty.design.behavior.strategy;

/**
 * 上下文
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Context {

    private Operation operation;

    public Context(Operation operation){
        this.operation = operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int doAction(int num1, int num2){
        return operation.doOperation(num1,num2);
    }
}
