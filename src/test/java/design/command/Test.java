package design.command;

import cn.jinty.design.command.Broker;
import cn.jinty.design.command.StockBuy;
import cn.jinty.design.command.StockSell;

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
