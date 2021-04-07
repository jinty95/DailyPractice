package cn.jinty.design.bridge;

/**
 * 红色的圆形
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class RedCircle implements DrawApi{

    @Override
    public void draw() {
        System.out.println("I am drawing a RedCircle");
    }

}
