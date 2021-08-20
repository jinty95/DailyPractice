package cn.jinty.design.behavior.command;

/**
 * 股票 - 资源和动作
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Stock {

    private String name = "default stock";
    private int quality = 0;

    public Stock(){}

    public Stock(String name, int quality) {
        this.name = name;
        this.quality = quality;
    }

    public void buy(){
        System.out.printf("Stock Buy [name=%s,quality=%d]%n",name,quality);
    }

    public void sell(){
        System.out.printf("Stock Sell [name=%s,quality=%d]%n",name,quality);
    }

}
