package cn.jinty.design.flyweight;

import java.util.HashSet;
import java.util.Set;

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
        Set<Color> set = new HashSet<>();
        for(int i=0;i<100;i++){
            Color c = ColorFactory.getColor(colors[getRandom(6)]);
            set.add(c);
        }
        System.out.println(set.size());
    }

}
