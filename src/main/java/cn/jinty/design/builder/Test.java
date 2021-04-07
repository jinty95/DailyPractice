package cn.jinty.design.builder;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //建造者模式
        Order order = new Order().id("123").name("我的订单").price(12L);
        Order order1 = new Order().id("111").name("你的订单").price(12L);
        System.out.println(order+"\r\n"+order1);
    }
}
