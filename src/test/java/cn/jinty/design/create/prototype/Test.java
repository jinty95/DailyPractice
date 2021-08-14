package cn.jinty.design.create.prototype;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //原型模式 - 克隆
        Data data1 = new Data();
        data1.setId("111");
        data1.setName("我的数据");
        Data data2 = (Data) data1.clone();
        System.out.println(data1);
        System.out.println(data2);
    }

}
