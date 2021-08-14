package cn.jinty.design.struct.bridge;

/**
 * 圆形
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Circle extends Shape{

    public Circle(DrawApi drawApi){
        super(drawApi);
    }

    @Override
    public void draw() {
        super.draw();
    }

}
