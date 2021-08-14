package cn.jinty.design.create.factory.product;

import cn.jinty.design.create.factory.product.category.Shape;

/**
 * 正方形
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("I am Shape of GreenCircle");
    }
}
