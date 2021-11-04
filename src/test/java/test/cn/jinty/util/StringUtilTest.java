package test.cn.jinty.util;

import cn.jinty.util.StringUtil;
import org.junit.Test;

/**
 * 字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/6/3
 **/
public class StringUtilTest {

    @Test
    public void testRandom() {
        System.out.println(StringUtil.randomNumber(10));
        System.out.println(StringUtil.randomLetter(10));
        System.out.println(StringUtil.random(10));
    }

}
