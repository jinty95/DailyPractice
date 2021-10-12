package cn.jinty.design.create.builder;

/**
 * 商品
 *
 * @author jinty
 * @date 2021/8/20
 **/
public class Goods {

    private int id;

    private String name;

    private long price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //建造器
    public static class Builder {

        private int id;

        private String name;

        private long price;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(long price) {
            this.price = price;
            return this;
        }

        public Goods build() {
            Goods goods = new Goods();
            goods.setId(this.id);
            goods.setName(this.name);
            goods.setPrice(this.price);
            return goods;
        }

    }

}
