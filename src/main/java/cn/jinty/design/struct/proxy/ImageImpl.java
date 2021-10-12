package cn.jinty.design.struct.proxy;

/**
 * 图片实现
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class ImageImpl implements Image {

    private final String name;

    public ImageImpl(String name) {
        this.name = name;
        load();
    }

    @Override
    public void display() {
        System.out.println("Display Image [" + name + "]");
    }

    @Override
    public void dye() {
        System.out.println("Dye Image [" + name + "]");
    }

    private void load() {
        System.out.println("Loading Image [" + name + "]");
    }

}
