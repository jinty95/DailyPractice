package cn.jinty.design.flyweight;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    private static String[] colors = {"red","green","blue","yellow","white","black"};

    private static int getRandom(int range){
        return (int)(Math.random()*range);
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            Color c = ColorFactory.getColor(colors[getRandom(6)]);
        }
    }
}
