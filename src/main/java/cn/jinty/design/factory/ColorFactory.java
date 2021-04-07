package cn.jinty.design.factory;

import cn.jinty.design.factory.product.category.Color;
import cn.jinty.design.factory.product.category.Shape;

/**
 * 颜色工厂
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class ColorFactory extends AbstractFactory{

    /**
     * 获取颜色对象
     * @param name
     * @return
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
