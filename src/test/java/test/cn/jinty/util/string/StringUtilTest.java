package test.cn.jinty.util.string;

import cn.jinty.util.string.StringUtil;
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
    public void testCRLF() {
        System.out.println("开始");
        System.out.print("LF：" + StringUtil.LF);
        System.out.print("CR：" + StringUtil.CR);
        System.out.print("CRLF：" + StringUtil.CRLF);
        System.out.println("结束");
    }

    @Test
    public void testIsEmpty() {
        System.out.println(StringUtil.isEmpty(null));
        System.out.println(StringUtil.isEmpty(""));
        System.out.println(StringUtil.isEmpty("abc"));
        System.out.println();
        System.out.println(StringUtil.isNotEmpty(null));
        System.out.println(StringUtil.isNotEmpty(""));
        System.out.println(StringUtil.isNotEmpty("abc"));
    }

    @Test
    public void testIsBlank() {
        System.out.println(StringUtil.isBlank(null));
        System.out.println(StringUtil.isBlank(""));
        System.out.println(StringUtil.isBlank("    "));
        System.out.println(StringUtil.isBlank("\r\n\t"));
        System.out.println(StringUtil.isBlank("abc"));
        System.out.println();
        System.out.println(StringUtil.isNotBlank(null));
        System.out.println(StringUtil.isNotBlank(""));
        System.out.println(StringUtil.isNotBlank("    "));
        System.out.println(StringUtil.isNotBlank("\r\n\t"));
        System.out.println(StringUtil.isNotBlank("abc"));
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
    public void testCount() {
        System.out.println(StringUtil.count("", null));
        System.out.println(StringUtil.count("AAAAAAA", "AA"));
        System.out.println(StringUtil.count("ABABABA", "ABA"));
    }

    @Test
    public void testTrim() {
        System.out.println(StringUtil.trim("  1  "));
        System.out.println(StringUtil.trim("\"hello\"", "\""));
        System.out.println(StringUtil.trim("  \"  hello  \"  ", "\""));
        System.out.println(StringUtil.trim(" ' hello world ' ", "'"));
        System.out.println(StringUtil.trim("ok yes", "$"));
        System.out.println(StringUtil.trim(" $ $ ok yes $ $ ", "$"));
        System.out.println(StringUtil.trim(StringUtil.trim(" \"' ok yes '\" ", "\""), "'"));
    }

    @Test
    public void testToUpperCase() {
        System.out.println(StringUtil.toUpperCase(null));
        System.out.println(StringUtil.toUpperCase(""));
        System.out.println(StringUtil.toUpperCase("abc"));
    }

    @Test
    public void testToLowerCase() {
        System.out.println(StringUtil.toLowerCase(null));
        System.out.println(StringUtil.toLowerCase(""));
        System.out.println(StringUtil.toLowerCase("Abc"));
    }

    @Test
    public void testUpperFirst() {
        System.out.println(StringUtil.upperFirst(null));
        System.out.println(StringUtil.upperFirst(""));
        System.out.println(StringUtil.upperFirst("1"));
        System.out.println(StringUtil.upperFirst("嘿"));
        System.out.println(StringUtil.upperFirst("a"));
        System.out.println(StringUtil.upperFirst("abc"));
        System.out.println(StringUtil.upperFirst("helloWorld"));
    }

    @Test
    public void testLowerFirst() {
        System.out.println(StringUtil.lowerFirst(null));
        System.out.println(StringUtil.lowerFirst(""));
        System.out.println(StringUtil.lowerFirst("1"));
        System.out.println(StringUtil.lowerFirst("呵呵"));
        System.out.println(StringUtil.lowerFirst("A"));
        System.out.println(StringUtil.lowerFirst("Abc"));
        System.out.println(StringUtil.lowerFirst("HelloWorld"));
    }

    @Test
    public void testContains() {
        System.out.println(StringUtil.contains(null, null));
        System.out.println(StringUtil.contains(null, "A"));
        System.out.println(StringUtil.contains("ABC", "A"));
        System.out.println(StringUtil.contains("ABC", "D"));
    }

    @Test
    public void testReplace() {
        System.out.println(StringUtil.replace("ABCABC", "B", "D"));
        System.out.println(StringUtil.replaceFirst("ABCABC", "B", "D"));
        System.out.println(StringUtil.replaceAll("ABCABC", "^([A-Z]{2})([A-Z]{2})([A-Z]{2})$", "$2"));
    }

    @Test
    public void testReplaceAll() {
        String[] arr = {
                "http://xxx.com/yyy/zzz?id=1",
                "http://xxx.abc.com/yyy/zzz?id=1",
                "http://xxx.abc.abc.com/yyy/zzz?id=1",
                "http://xxx.abc.com/yyy/zzz?id=1,http://xxx.abc.com/yyy/zzz?id=2",
                "http://xxx.abc.com/yyy/zzz?id=1,http://xxx.abc.com/yyy/zzz?id=2,http://xxx.abc.com/yyy/zzz?id=3",
                "http://xxx.abc.com/yyy/zzz?id=1,http://xxx.abc.com/yyy/zzz?id=2,http://xxx.abc.abc.com/yyy/zzz?id=3,,,"
        };
        // 将.abc.com替换为.abc.abc.com，如果为.abc.abc.com则不替换
        String regex = "^(?!.*\\.abc\\.abc\\.com.*)(.*)(\\.abc\\.com)(.*)$";
        String replacement = "$1.abc.abc.com$3";
        for (String s : arr) {
            String[] split = StringUtil.split(s, ",");
            for (int i = 0; i < split.length; i++) {
                split[i] = StringUtil.replaceAll(split[i], regex, replacement);
            }
            System.out.println(StringUtil.join(",", (Object[]) split));
        }
    }

    @Test
    public void testAppend() {
        System.out.println(StringUtil.append(null, null, null));
        System.out.println(StringUtil.append(null, "1", ","));
        System.out.println(StringUtil.append("1", null, null));
        System.out.println(StringUtil.append("", "1", ","));
        System.out.println(StringUtil.append("1", "2", ","));
        System.out.println(StringUtil.append("1,2", "3", ","));
    }

    @Test
    public void testJoin1() {
        System.out.println(StringUtil.join(null, ""));
        System.out.println(StringUtil.join(Arrays.asList(null, null), ""));
        System.out.println(StringUtil.join(new ArrayList<>(), ""));
        System.out.println(StringUtil.join(Arrays.asList('D', 'E', 'F', 'G'), ""));
        System.out.println(StringUtil.join(Arrays.asList(1, 2, 3), ","));
        System.out.println(StringUtil.join(Arrays.asList("我们", "你们", "他们"), "和"));
    }

    @Test
    public void testJoin2() {
        System.out.println(StringUtil.join("_", null));
        System.out.println(StringUtil.join(null, null, null));
        System.out.println(StringUtil.join("_", null, 2, null));
        System.out.println(StringUtil.join("", 'D', 'E', 'F', 'G'));
        System.out.println(StringUtil.join(",", 1, 2, 3));
        System.out.println(StringUtil.join(",", new int[]{1, 2, 3}));
        System.out.println(StringUtil.join(",", new Integer[]{1, 2, 3}));
        System.out.println(StringUtil.join("和", new String[]{"我们", "你们", "他们"}));
        System.out.println(StringUtil.join("和", new String[]{"我们", "你们", "他们"}, new String[]{"我们", "你们", "他们"}));
    }

    @Test
    public void testSplit() {
        String[][] arr = {
                {null, ","},
                {"   ", null},
                {"   ", ""},
                {"A,B,C", ","},
                {"A\r\nB\nC\rD\tE\fF G,H, I,  J,,,, K , L", "[\\s,]+"},
                {"A,,,,", ","}
        };
        for (String[] a : arr) {
            System.out.println(Arrays.toString(StringUtil.split(a[0], a[1])));
            System.out.println(StringUtil.splitAndGetFirst(a[0], a[1]));
        }
    }

    @Test
    public void testRepeat() {
        System.out.println(StringUtil.repeat(null, 0));
        System.out.println(StringUtil.repeat("abc", 0));
        System.out.println(StringUtil.repeat("", 100));
        System.out.println(StringUtil.repeat("a", 1));
        System.out.println(StringUtil.repeat("a", 5));
        System.out.println(StringUtil.repeat("ok", 10));
        System.out.println(StringUtil.repeat("hello", 5));
    }

    @Test
    public void testRepeat1() {
        System.out.println(StringUtil.repeat(null, null, 1));
        System.out.println(StringUtil.repeat("a", null, 2));
        System.out.println(StringUtil.repeat("a", null, 1));
        System.out.println(StringUtil.repeat("", "-", 5));
        System.out.println(StringUtil.repeat("a", "-", 5));
        System.out.println(StringUtil.repeat("aa", ",", 10));
    }

    @Test
    public void testLeftPad() {
        System.out.println(StringUtil.leftPad(null, 10, '0'));
        System.out.println(StringUtil.leftPad(null, -1, '0'));
        System.out.println(StringUtil.leftPad("12306", -1, '0'));
        System.out.println(StringUtil.leftPad("12306", 10, '0'));
    }

    @Test
    public void testRightPad() {
        System.out.println(StringUtil.rightPad(null, 10, '0'));
        System.out.println(StringUtil.rightPad(null, -1, '0'));
        System.out.println(StringUtil.rightPad("12306", -1, '0'));
        System.out.println(StringUtil.rightPad("12306", 10, '0'));
    }

    @Test
    public void testToBinaryString() {
        // "0" -> 48
        System.out.println(StringUtil.toBinaryString("0123456789"));
        // "A" -> 65
        System.out.println(StringUtil.toBinaryString("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        // "a" -> 97
        System.out.println(StringUtil.toBinaryString("abcdefghijklmnopqrstuvwxyz"));
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

}
