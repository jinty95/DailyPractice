package test.cn.jinty.util;

import cn.jinty.util.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    public void testTrim() {
        System.out.println(StringUtil.trim("  1  "));
        System.out.println(StringUtil.trim("\"hello\"", "\""));
        System.out.println(StringUtil.trim("  \"  hello  \"  ", "\""));
        System.out.println(StringUtil.trim(" ' hello world ' ", "'"));
        System.out.println(StringUtil.trim("ok yes", "$$"));
        System.out.println(StringUtil.trim(" $$ ok yes $$ ", "$$"));
        System.out.println(StringUtil.trim(StringUtil.trim(" \"' ok yes '\" ", "\""), "'"));
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
    public void testIsLikeCnPhoneNum() {
        String s1 = null;
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
        s1 = "99988889999";
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
        s1 = "12088889999";
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
        s1 = "15688889999";
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
        s1 = "13688889999";
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
        s1 = "19988889999";
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
        s1 = "199AAAABBBB";
        System.out.println(s1 + " 可能是国内手机号码：" + StringUtil.isLikeCnPhoneNum(s1));
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
    public void testHexToBytes() {
        String hex = "123456789ABCDEFF";
        System.out.println("十六进制数：" + hex);
        System.out.println("字节数组：" + Arrays.toString(StringUtil.hexToBytes(hex)));
        System.out.println("十六进制数：" + StringUtil.bytesToHex(StringUtil.hexToBytes(hex)));
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

    @Test
    public void testCountOccur() {
        System.out.println(StringUtil.countOccur("", null));
        System.out.println(StringUtil.countOccur("AAAAAAA", "AA"));
        System.out.println(StringUtil.countOccur("ABABABA", "ABA"));
    }

    @Test
    public void testLongestCommonSubstring() {
        String s1 = "Hello";
        String s2 = "He is very well";
        System.out.println(StringUtil.longestCommonSubstringLength(s1, s2));
        System.out.println(StringUtil.longestCommonSubstring(s1, s2));
        s1 = "I am very well now";
        s2 = "He is very well too";
        System.out.println(StringUtil.longestCommonSubstringLength(s1, s2));
        System.out.println(StringUtil.longestCommonSubstring(s1, s2));
    }

    @Test
    public void testlongestCommonSubsequence() {
        String s1 = "选择器可以通过\" \"分割，实现对某种嵌套的元素中最内层的元素生效。";
        String s2 = "如果 .b {} 对class=\"b\"的元素不生效，可以通过改用这种嵌套的方式实现。";
        System.out.println(StringUtil.longestCommonSubsequenceLength(s1, s2));
        System.out.println(StringUtil.longestCommonSubsequence(s1, s2));
        s1 = "ABCBD";
        s2 = "ABD";
        System.out.println(StringUtil.longestCommonSubsequenceLength(s1, s2));
        System.out.println(StringUtil.longestCommonSubsequence(s1, s2));
    }

    @Test
    public void testDiff() {
        String s1 = "选择器可以通过\" \"分割，实现对某种嵌套的元素中最内层的元素生效。";
        String s2 = "如果 .b {} 对class=\"b\"的元素不生效，可以通过改用这种嵌套的方式实现。";
        String common = StringUtil.longestCommonSubsequence(s1, s2);
        String[] diffs = StringUtil.diff(s1, s2);
        System.out.println("文本1：" + s1);
        System.out.println("文本2：" + s2);
        System.out.println("公共部分：" + common);
        for (String diff : diffs) {
            System.out.println("差异部分：" + diff);
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入两行文本，获取最长公共子串、最长公共子序列、差异部分");
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        System.out.println();
        System.out.println("文本1：" + s1);
        System.out.println("文本2：" + s2);
        System.out.println();
        System.out.println("最长公共子串：" + StringUtil.longestCommonSubstring(s1, s2));
        System.out.println("最长公共子序列：" + StringUtil.longestCommonSubsequence(s1, s2));
        for (String diff : StringUtil.diff(s1, s2)) {
            System.out.println("差异部分：" + diff);
        }
    }

}
