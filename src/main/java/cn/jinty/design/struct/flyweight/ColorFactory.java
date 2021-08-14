package cn.jinty.design.struct.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 颜色工厂
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class ColorFactory {

    private static Map<String,Color> map = new HashMap<>();

    public static Color getColor(String name){
        Color color = map.get(name);
        if(color==null){
            color = new Color(name);
            map.put(name,color);
        }
        return color;
    }

}
