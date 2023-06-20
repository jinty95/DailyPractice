package test.cn.jinty.util.string;

import cn.jinty.util.string.EscapeStringUtil;
import org.junit.Test;

/**
 * 转义字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class EscapeStringUtilTest {

    @Test
    public void testEscapeAndUnescape() {
        String s = "{\"id\":1,\r\n\"name\":\"2\\3\"}";
        System.out.println(s);
        for (int i = 0; i < 5; i++) {
            s = EscapeStringUtil.escape(s);
            System.out.println(s);
        }
        System.out.println("\n" + s);
        for (int i = 0; i < 5; i++) {
            s = EscapeStringUtil.unescape(s);
            System.out.println(s);
        }
    }

}
