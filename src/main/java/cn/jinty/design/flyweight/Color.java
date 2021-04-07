package cn.jinty.design.flyweight;

/**
 * 颜色
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Color {

    private String name;

    public Color(String name) {
        this.name = name;
        System.out.println("build a color : name="+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                '}';
    }
}
