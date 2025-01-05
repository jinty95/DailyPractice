package test.cn.jinty.util.string;

import cn.jinty.util.string.NameStringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * 命名字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class NameStringUtilTest {

    @Test
    public void testCamelToSnakeThenUpper() {
        Assert.assertEquals("STRING_UTIL_TEST", NameStringUtil.camelToSnakeThenUpper("stringUtilTest"));
        Assert.assertEquals("STRING_UTIL_TEST", NameStringUtil.camelToSnakeThenUpper("StringUtilTest"));
        Assert.assertEquals("STRING_UTIL_TEST", NameStringUtil.camelToSnakeThenUpper("string_util_test"));
        Assert.assertEquals("12345", NameStringUtil.camelToSnakeThenUpper("12345"));
        Assert.assertEquals("", NameStringUtil.camelToSnakeThenUpper(null));
    }

    @Test
    public void testCamelToSnake() {
        Assert.assertEquals("string_util_test", NameStringUtil.camelToSnake("stringUtilTest"));
        Assert.assertEquals("string_util_test", NameStringUtil.camelToSnake("StringUtilTest"));
        Assert.assertEquals("string_util_test", NameStringUtil.camelToSnake("string_util_test"));
        Assert.assertEquals("12345", NameStringUtil.camelToSnake("12345"));
        Assert.assertEquals("", NameStringUtil.camelToSnake(null));
    }

    @Test
    public void testSnakeToCamel() {
        Assert.assertEquals("StringUtilTest", NameStringUtil.snakeToCamel("string_util_test", true));
        Assert.assertEquals("stringUtilTest", NameStringUtil.snakeToCamel("string_util_test", false));
        Assert.assertEquals("StringUtilTest", NameStringUtil.snakeToCamel("_string_util_test", false));
        Assert.assertEquals("stringutiltest", NameStringUtil.snakeToCamel("StringUtilTest", false));
        Assert.assertEquals("12345", NameStringUtil.snakeToCamel("1_2_3_4_5", false));
        Assert.assertEquals("stringUtilTest", NameStringUtil.snakeToCamel("STRING_UTIL_TEST", false));
    }

}
