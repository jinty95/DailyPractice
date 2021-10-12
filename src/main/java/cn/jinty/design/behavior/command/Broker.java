package cn.jinty.design.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 经纪人、代理人
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Broker {

    private final List<Order> list = new ArrayList<>();

    public void addOrder(Order order) {
        list.add(order);
    }

    public void executeOrder() {
        list.forEach(Order::execute);
        list.clear();
    }

}
