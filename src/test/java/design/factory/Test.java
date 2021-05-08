package design.factory;

import cn.jinty.design.factory.AbstractFactory;
import cn.jinty.design.factory.FactoryProducer;
import cn.jinty.design.factory.product.category.Color;
import cn.jinty.design.factory.product.category.Shape;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //抽象工厂模式
        AbstractFactory factory1 = FactoryProducer.getFactory(AbstractFactory.FACTORY_COLOR);
        Color color1 = factory1.getColor(Color.COLOR_RED);
        color1.dye();
        Color color2 = factory1.getColor(Color.COLOR_GREEN);
        color2.dye();
        AbstractFactory factory2 = FactoryProducer.getFactory(AbstractFactory.FACTORY_SHAPE);
        Shape shape1 = factory2.getShape(Shape.SHAPE_CIRCLE);
        shape1.draw();
        Shape shape2 = factory2.getShape(Shape.SHAPE_SQUARE);
        shape2.draw();
    }
}
