package test.cn.jinty.util.collection;

import cn.jinty.util.collection.MapUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/2/28
 **/
public class MapUtilTest {

    @Test
    public void testInvert() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, String.valueOf((char) (i + 97)));
        }
        System.out.println(map);
        System.out.println(MapUtil.invert(map));
    }

    @Test
    public void testGetOrDefault() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, String.valueOf((char) (i + 97)));
        }
        System.out.println(MapUtil.getOrDefault(null, 1, "a"));
        System.out.println(MapUtil.getOrDefault(map, -1, "-1"));
        System.out.println(MapUtil.getOrDefault(map, 1, "-1"));
    }

}
