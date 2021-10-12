package cn.jinty.design.create.factory;

import cn.jinty.design.create.factory.product.category.Color;
import cn.jinty.design.create.factory.product.category.Shape;

/**
 * 形状工厂
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class ShapeFactory extends AbstractFactory {

    /**
     * 获取形状对象
     *
     * @param name 类路径
     * @return 对象
     */
    public Shape getShape(String name) {
        Shape s = null;
        try {
            s = (Shape) Class.forName(name).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Color getColor(String name) {
        return null;
    }
}
