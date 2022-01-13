package test.cn.jinty.util;

import cn.jinty.util.StringUtil;
import org.junit.Test;

/**
 * 字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/6/3
 **/
@SuppressWarnings("all")
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
    public void testEquals() {
        System.out.println(StringUtil.equals(null, null));
        System.out.println(StringUtil.equals(null, ""));
        System.out.println(StringUtil.equals("", ""));
        System.out.println(StringUtil.equals("a", "b"));
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

    @Test
    public void testConcat() {
        System.out.println(StringUtil.concat(","));
        System.out.println(StringUtil.concat(",", "A", "B", "C"));
        System.out.println(StringUtil.concat("_", "A", "B", "C"));
    }

    @Test
    public void testRepeat() {
        System.out.println(StringUtil.repeat(null, 0));
        System.out.println(StringUtil.repeat("abc", 0));
        System.out.println(StringUtil.repeat("", 1));
        System.out.println(StringUtil.repeat("aa", 5));
        System.out.println(StringUtil.repeat("ok", 10));
        System.out.println(StringUtil.repeat("hello", 5));
    }

    @Test
    public void testFind() {
        System.out.println(StringUtil.find("ABC", "B"));
        System.out.println(StringUtil.find("Hello You Guys", "^H\\w+"));
        System.out.println(StringUtil.find("Hello You Guys", "Guys$"));
        System.out.println(StringUtil.find("Hello You Guys", "^H\\w+ Guys$"));
    }

    @Test
    public void testMatches() {
        System.out.println(StringUtil.matches("ABC", "B"));
        System.out.println(StringUtil.matches("ABC", "ABC"));
        System.out.println(StringUtil.matches("Hello You Guys", "^H\\w+"));
        System.out.println(StringUtil.matches("Hello You Guys", "Guys$"));
        System.out.println(StringUtil.matches("Hello You Guys", ".+\\s+.+\\s+.+"));
    }

    @Test
    public void testFind1() {
        // .不能匹配换行符
        System.out.println(StringUtil.find("换货原因：不符合换货原因：\n 会员的诉求：客户要求换货 已解释没有库存无法换货", "^(?=.*(无法换货|换不了|不能换货).*)(?=.*没有.*)(?=.*库存.*)|(?=.*断货.*)(?=.*换货.*)(?!.*断货王.*)(?!.*退货.*)"));
        System.out.println(StringUtil.find("换货原因：不符合换货原因：会员的诉求：客户要求换货 已解释没有库存无法换货", "^(?=.*(无法换货|换不了|不能换货).*)(?=.*没有.*)(?=.*库存.*)|(?=.*断货.*)(?=.*换货.*)(?!.*断货王.*)(?!.*退货.*)"));
        // 使用单行模式(?s)忽略换行符
        System.out.println(StringUtil.find("换货原因：不符合换货原因：\n 会员的诉求：客户要求换货 已解释没有库存无法换货", "(?s)^(?=.*(无法换货|换不了|不能换货).*)(?=.*没有.*)(?=.*库存.*)|(?=.*断货.*)(?=.*换货.*)(?!.*断货王.*)(?!.*退货.*)"));
    }

    @Test
    public void testMatches1() {
        // .不能匹配换行符
        System.out.println(StringUtil.matches("ABC\n", ".*"));
        // 使用单行模式(?s)忽略换行符
        System.out.println(StringUtil.matches("ABC\n", "(?s).*"));
        // 使用[\s\S]匹配所有字符
        System.out.println(StringUtil.matches("ABC\n", "[\\s\\S]*"));
        // 使用[\d\D]匹配所有字符
        System.out.println(StringUtil.matches("ABC\n", "[\\d\\D]*"));
        // 使用[\w\W]匹配所有字符
        System.out.println(StringUtil.matches("ABC\n", "[\\w\\W]*"));
    }

}
