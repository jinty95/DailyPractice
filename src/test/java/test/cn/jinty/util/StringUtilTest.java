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
    public void testIsEmpty() {
        System.out.println(StringUtil.isEmpty(null));
        System.out.println(StringUtil.isEmpty(""));
        System.out.println(StringUtil.isEmpty("abc"));
    }

    @Test
    public void testIsBlank() {
        System.out.println(StringUtil.isBlank(null));
        System.out.println(StringUtil.isBlank(""));
        System.out.println(StringUtil.isBlank("    "));
    }

    @Test
    public void testIsLetter() {
        System.out.println(StringUtil.isLetter("Hello"));
        System.out.println(StringUtil.isLetter("12345"));
    }

    @Test
    public void testIsDigit() {
        System.out.println(StringUtil.isDigit("Hello"));
        System.out.println(StringUtil.isDigit("12345"));
    }

    @Test
    public void testIsUpperCase() {
        System.out.println(StringUtil.isUpperCase("HELLO"));
        System.out.println(StringUtil.isUpperCase("Hello"));
        System.out.println(StringUtil.isUpperCase("12345"));
    }

    @Test
    public void testIsLowerCase() {
        System.out.println(StringUtil.isLowerCase("hello"));
        System.out.println(StringUtil.isLowerCase("Hello"));
        System.out.println(StringUtil.isLowerCase("12345"));
    }

    @Test
    public void testRandom() {
        System.out.println(StringUtil.randomDigit(10));
        System.out.println(StringUtil.randomLetter(10));
        System.out.println(StringUtil.random(10));
        System.out.println(StringUtil.randomDigit(12));
        System.out.println(StringUtil.randomLetter(12));
        System.out.println(StringUtil.random(12));
    }

}
