package test.cn.jinty.util;

import cn.jinty.util.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void testToString() {
        System.out.println(StringUtil.toString(null));
        System.out.println(StringUtil.toString("ABC"));
        System.out.println(StringUtil.toString(Integer.valueOf(1)));
        System.out.println(StringUtil.toString(Double.valueOf(1.0)));
    }

    @Test
    public void testEquals() {
        System.out.println(StringUtil.equals(null, null));
        System.out.println(StringUtil.equals(null, ""));
        System.out.println(StringUtil.equals("", null));
        System.out.println(StringUtil.equals("", ""));
        System.out.println(StringUtil.equals("a", "b"));
    }

    @Test
    public void testLength() {
        System.out.println(StringUtil.length(null));
        System.out.println(StringUtil.length(""));
        System.out.println(StringUtil.length("哈哈哈"));
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
    public void testContains() {
        String s1 = "Hello", s2 = "123@qq.com";
        System.out.println(s1 + " 包含字母：" + StringUtil.containsLetter(s1));
        System.out.println(s1 + " 包含大写字母：" + StringUtil.containsUpperCase(s1));
        System.out.println(s1 + " 包含小写字母：" + StringUtil.containsLowerCase(s1));
        System.out.println(s1 + " 包含数字：" + StringUtil.containsDigit(s1));
        System.out.println(s1 + " 包含特殊字符：" + StringUtil.containsSpecialChar(s1));
        System.out.println(s2 + " 包含字母：" + StringUtil.containsLetter(s2));
        System.out.println(s2 + " 包含大写字母：" + StringUtil.containsUpperCase(s2));
        System.out.println(s2 + " 包含小写字母：" + StringUtil.containsLowerCase(s2));
        System.out.println(s2 + " 包含数字：" + StringUtil.containsDigit(s2));
        System.out.println(s2 + " 包含特殊字符：" + StringUtil.containsSpecialChar(s2));
    }

    @Test
    public void testCheckPassword() {
        String s1 = "123456";
        System.out.println(s1 + " 是合法密码：" + StringUtil.checkPassword(s1));
        s1 = "Abc123";
        System.out.println(s1 + " 是合法密码：" + StringUtil.checkPassword(s1));
        s1 = "Abc123?";
        System.out.println(s1 + " 是合法密码：" + StringUtil.checkPassword(s1));
        s1 = "Abcd123?";
        System.out.println(s1 + " 是合法密码：" + StringUtil.checkPassword(s1));
        s1 = "Abcd123?123456789";
        System.out.println(s1 + " 是合法密码：" + StringUtil.checkPassword(s1));
    }

    @Test
    public void testRandom() {
        System.out.println("随机数字字符串");
        System.out.println(StringUtil.randomDigit(5));
        System.out.println(StringUtil.randomDigit(10));
        System.out.println(StringUtil.randomDigit(12));
        System.out.println("随机字母字符串");
        System.out.println(StringUtil.randomLetter(5));
        System.out.println(StringUtil.randomLetter(10));
        System.out.println(StringUtil.randomLetter(12));
        System.out.println("随机数字字母字符串");
        System.out.println(StringUtil.random(5));
        System.out.println(StringUtil.random(10));
        System.out.println(StringUtil.random(12));
    }

    @Test
    public void testAppend() {
        System.out.println(StringUtil.append(null, "1", ","));
        System.out.println(StringUtil.append(null, null, null));
        System.out.println(StringUtil.append("1", null, null));
        System.out.println(StringUtil.append("", "1", ","));
        System.out.println(StringUtil.append("1", "2", ","));
        System.out.println(StringUtil.append("1,2", "3", ","));
    }

    @Test
    public void testJoin() {
        System.out.println(StringUtil.join(null, ""));
        System.out.println(StringUtil.join(new ArrayList<>(), ""));
        System.out.println(StringUtil.join(Arrays.asList('D', 'E', 'F', 'G'), ""));
        System.out.println(StringUtil.join(Arrays.asList(1, 2, 3), ","));
        System.out.println(StringUtil.join(Arrays.asList("我们", "你们", "他们"), "和"));
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

    @Test
    public void testCamelToSnake() {
        System.out.println(StringUtil.camelToSnake("stringUtilTest"));
        System.out.println(StringUtil.camelToSnake("StringUtilTest"));
        System.out.println(StringUtil.camelToSnake("StringUtilTest1"));
        System.out.println(StringUtil.camelToSnake("string_util_test"));
        System.out.println(StringUtil.camelToSnake("12345"));
        System.out.println(StringUtil.camelToSnake("ElectronicReceiptExternal"));
    }

    @Test
    public void testSnakeToCamel() {
        System.out.println(StringUtil.snakeToCamel("string_util_test", true));
        System.out.println(StringUtil.snakeToCamel("string_util_test", false));
        System.out.println(StringUtil.snakeToCamel("_string_util_test", false));
        System.out.println(StringUtil.snakeToCamel("StringUtilTest", false));
        System.out.println(StringUtil.snakeToCamel("1_2_3_4_5", false));
    }

    @Test
    public void testRemove4ByteChar() {
        System.out.println(StringUtil.remove4ByteChar("你好"));
        System.out.println(StringUtil.remove4ByteChar("Hello"));
        System.out.println(StringUtil.remove4ByteChar("1234"));
        System.out.println(StringUtil.remove4ByteChar("\uD834\uDD1EOK"));
        System.out.println(StringUtil.remove4ByteChar("\uD834OK"));
        System.out.println(StringUtil.remove4ByteChar("\u2600\u2601\u231A\u270A\u270B\u270C"));
    }

    @Test
    public void testToBinaryString() {
        System.out.println(StringUtil.toBinaryString("\uD834\uDD1E".getBytes()));
    }

    @Test
    public void testSubstring() {
        System.out.println(StringUtil.substring(null, 0, 0));
        System.out.println(StringUtil.substring("hello", -1, 4));
        System.out.println(StringUtil.substring("hello", 5, 4));
        System.out.println(StringUtil.substring("hello", 0, 1));
        System.out.println(StringUtil.substring("hello", 0, 2));
        System.out.println(StringUtil.substring("hello", 0, 5));
        System.out.println(StringUtil.substring("hello", 0, 15));
        System.out.println(StringUtil.substring("hello world yes oh yeah", 0, 5) + "...");
    }

}
