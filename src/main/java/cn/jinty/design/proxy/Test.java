package cn.jinty.design.proxy;

import java.lang.reflect.Proxy;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        Image image1 = new RealImage("hello.jpg");
        image1.display();
        image1.dye();
        Image image2 = new ProxyImage("a.jpg");
        image2.display();
        image2.dye();
        Image image3 = (Image) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Image.class},
                new DynamicProxyImage("proxy.gif")
        );
        image3.display();
        image3.dye();
    }
}
