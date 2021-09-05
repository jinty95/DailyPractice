package test.cn.jinty.design.struct.bridge;

import cn.jinty.design.struct.bridge.Circle;
import cn.jinty.design.struct.bridge.GreenCircle;
import cn.jinty.design.struct.bridge.RedCircle;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //桥接模式：抽象与实现分离
        Circle c1 = new Circle(new GreenCircle());
        c1.draw();
        Circle c2 = new Circle(new RedCircle());
        c2.draw();
    }

}
