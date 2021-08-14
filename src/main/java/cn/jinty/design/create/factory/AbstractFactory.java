package cn.jinty.design.create.factory;

import cn.jinty.design.create.factory.product.category.Color;
import cn.jinty.design.create.factory.product.category.Shape;

/**
 * 抽象工厂
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public abstract class AbstractFactory {

    public static final String FACTORY_COLOR = ColorFactory.class.getName();
    public static final String FACTORY_SHAPE = ShapeFactory.class.getName();

    public abstract Color getColor(String name);
    public abstract Shape getShape(String name);

}
