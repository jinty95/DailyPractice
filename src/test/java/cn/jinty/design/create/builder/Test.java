package cn.jinty.design.create.builder;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {

        Order order1 = new Order().id("123").name("我的订单").price(12L);
        Order order2 = new Order().id("111").name("你的订单").price(12L);
        System.out.println(order1);
        System.out.println(order2);

        Goods goods1 = new Goods.Builder().id(1).name("牛奶").price(10L).build();
        Goods goods2 = new Goods.Builder().id(2).name("苹果").price(10L).build();
        System.out.println(goods1);
        System.out.println(goods2);

    }

}
