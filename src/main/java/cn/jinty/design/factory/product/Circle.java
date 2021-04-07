package cn.jinty.design.factory.product;

import cn.jinty.design.factory.product.category.Shape;

/**
 * 圆形
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("I am Shape of Circle");
    }
}
