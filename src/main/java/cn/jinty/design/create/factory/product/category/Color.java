package cn.jinty.design.create.factory.product.category;

import cn.jinty.design.create.factory.product.Green;
import cn.jinty.design.create.factory.product.Red;

/**
 * 颜色
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public interface Color {

    String COLOR_RED = Red.class.getName();
    String COLOR_GREEN = Green.class.getName();

    void dye();

}
