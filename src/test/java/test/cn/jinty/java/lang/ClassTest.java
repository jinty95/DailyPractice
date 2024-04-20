package test.cn.jinty.java.lang;

import cn.jinty.Main;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.net.URL;

/**
 * 类 - 测试
 *
 * @author Jinty
 * @date 2024/4/20
 */
public class ClassTest {

    // 测试三种不同场景下的绝对路径获取
    @Test
    public void testGetResource() {
        // 根据相对路径获取绝对路径：JDK中的路径 - null
        URL url = String.class.getResource("ref");
        System.out.println(url);
        // 根据相对路径获取绝对路径：依赖JAR中的路径 - jar:file:/xxx
        url = Feature.class.getResource("deserializer");
        System.out.println(url);
        System.out.println(url.getPath());
        // 根据相对路径获取绝对路径：当前项目中的路径 - file:/xxx
        url = Main.class.getResource("enums");
        System.out.println(url);
        System.out.println(url.getPath());
    }

}
