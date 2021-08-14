package cn.jinty.design.create.factory.product;

import cn.jinty.design.create.factory.product.category.Color;

/**
 * 绿色
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Green implements Color {

    @Override
    public void dye() {
        System.out.println("I am Color of Green");
    }
}
