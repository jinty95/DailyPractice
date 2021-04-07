package cn.jinty.design.command;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.addOrder(new StockBuy());
        broker.addOrder(new StockSell());
        broker.executeOrder();
    }
}
