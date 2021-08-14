package cn.jinty.design.create.singleton;

import java.lang.reflect.Constructor;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //单例模式
        Singleton s1 = Singleton.instance();
        System.out.println(s1);
        Singleton s2 = Singleton.instance();
        System.out.println(s2);
        Singleton s3 = Singleton.instance();
        System.out.println(s3);

        //单例模式漏洞
        try{
            Constructor<Singleton> c = Singleton.class.getDeclaredConstructor(null);
            c.setAccessible(true);
            Singleton singleton1 = (Singleton) c.newInstance();
            Singleton singleton2 = (Singleton) c.newInstance();
            System.out.println(singleton1);
            System.out.println(singleton2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
