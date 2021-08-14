package cn.jinty.design.create.factory.product.category;

import cn.jinty.design.create.factory.product.Circle;
import cn.jinty.design.create.factory.product.Square;

/**
 * 形状
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public interface Shape {

    String SHAPE_SQUARE = Square.class.getName();
    String SHAPE_CIRCLE = Circle.class.getName();

    void draw();

}
