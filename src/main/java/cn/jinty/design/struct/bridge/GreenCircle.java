package cn.jinty.design.struct.bridge;

/**
 * 绿色的圆形
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class GreenCircle implements DrawApi{

    @Override
    public void draw() {
        System.out.println("I am drawing a GreenCircle");
    }
}
