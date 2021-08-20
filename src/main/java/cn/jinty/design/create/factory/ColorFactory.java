package cn.jinty.design.create.factory;

import cn.jinty.design.create.factory.product.category.Color;
import cn.jinty.design.create.factory.product.category.Shape;

/**
 * 颜色工厂
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class ColorFactory extends AbstractFactory{

    /**
     * 获取颜色对象
     * @param name 类路径
     * @return 对象
     */
    public Color getColor(String name){
        Color color = null;
        try{
            color = (Color)Class.forName(name).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return color;
    }

    @Override
    public Shape getShape(String name) {
        return null;
    }
}
