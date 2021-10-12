package cn.jinty.design.struct.proxy;

/**
 * 图片实现代理
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class ImageProxy implements Image {

    private final Image image;

    public ImageProxy(Image image) {
        this.image = image;
    }

    @Override
    public void display() {
        System.out.println("--- I am proxy before ---");
        image.display();
        System.out.println("--- I am proxy after ---");
    }

    @Override
    public void dye() {
        System.out.println("--- I am proxy before ---");
        image.dye();
        System.out.println("--- I am proxy after ---");
    }

}
