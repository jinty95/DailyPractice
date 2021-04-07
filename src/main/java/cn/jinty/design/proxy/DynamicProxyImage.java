package cn.jinty.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 图片动态代理
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class DynamicProxyImage implements InvocationHandler{

    private Image img;

    public DynamicProxyImage(String name){
        this.img = new RealImage(name);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I am dynamic proxy before");
        Object obj = method.invoke(img,args);
        System.out.println("I am dynamic proxy after");
        return obj;
    }
}
