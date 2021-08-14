package cn.jinty.design.struct.proxy;

import java.lang.reflect.Proxy;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //普通类
        Image image1 = new ImageImpl("hello.jpg");
        image1.display();
        image1.dye();
        System.out.println();
        //代理类
        Image image2 = new ImageProxy(new ImageImpl("hello.jpg"));
        image2.display();
        image2.dye();
        System.out.println();
        //动态代理类
        Image image3 = (Image) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Image.class},
                new ImageDynamicProxy(new ImageImpl("hello.jpg"))
        );
        image3.display();
        image3.dye();
        System.out.println();
    }

}
