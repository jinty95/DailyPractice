package test.cn.jinty.design.behavior.command;

import cn.jinty.design.behavior.command.Broker;
import cn.jinty.design.behavior.command.StockBuy;
import cn.jinty.design.behavior.command.StockSell;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        //代理人
        Broker broker = new Broker();
        //添加命令
        broker.addOrder(new StockBuy());
        broker.addOrder(new StockSell());
        //执行
        broker.executeOrder();
    }

}
