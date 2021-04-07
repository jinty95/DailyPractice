package cn.jinty.design.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Broker {

    private List<Order> list = new ArrayList<>();

    public void addOrder(Order order){
        list.add(order);
    }

    public void executeOrder(){
        if(list!=null){
            list.forEach(one -> {
                one.execute();
            });
        }
        list.clear();
    }

}
