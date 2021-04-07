package cn.jinty.design.proxy;

/**
 * 图片实现代理
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class ProxyImage implements Image{

    private Image image;

    public ProxyImage(String name) {
        this.image = new RealImage(name);
    }

    @Override
    public void display() {
        System.out.println("I am proxy before");
        image.display();
        System.out.println("I am proxy after");
    }

    @Override
    public void dye() {
        System.out.println("I am proxy before");
        image.dye();
        System.out.println("I am proxy after");
    }
}
