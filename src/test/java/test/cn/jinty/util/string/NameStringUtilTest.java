package test.cn.jinty.util.string;

import cn.jinty.util.string.NameStringUtil;
import org.junit.Test;

/**
 * 命名字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class NameStringUtilTest {

    @Test
    public void testCamelToSnake() {
        System.out.println(NameStringUtil.camelToSnake("stringUtilTest"));
        System.out.println(NameStringUtil.camelToSnake("StringUtilTest"));
        System.out.println(NameStringUtil.camelToSnake("StringUtilTest1"));
        System.out.println(NameStringUtil.camelToSnake("string_util_test"));
        System.out.println(NameStringUtil.camelToSnake("12345"));
        System.out.println(NameStringUtil.camelToSnake("ElectronicReceiptExternal"));
    }

    @Test
    public void testSnakeToCamel() {
        System.out.println(NameStringUtil.snakeToCamel("string_util_test", true));
        System.out.println(NameStringUtil.snakeToCamel("string_util_test", false));
        System.out.println(NameStringUtil.snakeToCamel("_string_util_test", false));
        System.out.println(NameStringUtil.snakeToCamel("StringUtilTest", false));
        System.out.println(NameStringUtil.snakeToCamel("1_2_3_4_5", false));
    }

}
