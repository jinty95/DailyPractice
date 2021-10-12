package cn.jinty.design.struct.bridge;

/**
 * 形状抽象类
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public abstract class Shape {

    protected DrawApi drawApi;

    protected Shape(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    public void draw() {
        drawApi.draw();
    }

}
