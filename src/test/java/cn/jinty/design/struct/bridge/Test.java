package cn.jinty.design.struct.bridge;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        Circle c1 = new Circle(new GreenCircle());
        c1.draw();
        Circle c2 = new Circle(new RedCircle());
        c2.draw();
    }
}
