package cn.jinty.design.struct.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 图片动态代理
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class ImageDynamicProxy implements InvocationHandler{

    private final Image img;

    public ImageDynamicProxy(Image img){
        this.img = img;
    }

    //对原接口的所有方法做前后增强：好处在于增强逻辑只需写一次，不用在每个函数都写一次
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--- I am dynamic proxy before ---");
        Object obj = method.invoke(img,args);
        System.out.println("--- I am dynamic proxy after ---");
        return obj;
    }

}
