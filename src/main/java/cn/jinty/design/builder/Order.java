package cn.jinty.design.builder;

/**
 * 订单
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Order {

    private String id;
    private String name;
    private Long price;

    public Order id(String id){
        this.id = id;
        return this;
    }

    public Order name(String name){
        this.name = name;
        return this;
    }

    public Order price(Long price){
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
