package cn.jinty.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串 - 工具类
 *
 * @author Jinty
 * @date 2021/4/9
 **/
public final class StringUtil {

    // 十六进制字符集
    private static final char[] hex = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    // 数字
    private static final char[] digit = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    // 字母
    private static final char[] letter = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    // 随机数
    private static final Random random = new Random();

    // 空串
    public static final String EMPTY = "";

    /**
     * 字符串空判断
     *
     * @param s 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 字符串空白判断
     *
     * @param s 字符串
     * @return 是否空白
     */
    public static boolean isBlank(String s) {
        return isEmpty(s) || s.trim().length() == 0;
    }

    /**
     * 任意对象转字符串
     *
     * @param obj 任意对象
     * @return 字符串
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return EMPTY;
        }
        return obj.toString();
    }

    /**
     * 字符串判等
     *
     * @param a 字符串
     * @param b 字符串
     * @return 是否相等
     */
    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    /**
     * 字符串是否全为字母
     *
     * @param s 字符串
     * @return 是否全为字母
     */
    public static boolean isLetter(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否全为数字
     *
     * @param s 字符串
     * @return 是否全为数字
     */
    public static boolean isDigit(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否全为大写字母
     *
     * @param s 字符串
     * @return 是否全为大写字母
     */
    public static boolean isUpperCase(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否全为小写字母
     *
     * @param s 字符串
     * @return 是否全为小写字母
     */
    public static boolean isLowerCase(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 生成随机字符串(数字)
     *
     * @param length 长度
     * @return 字符串
     */
    public static String randomDigit(int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append(digit[random.nextInt(digit.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串(字母)
     *
     * @param length 长度
     * @return 字符串
     */
    public static String randomLetter(int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append(letter[random.nextInt(letter.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串(数字+字母)
     *
     * @param length 长度
     * @return 字符串
     */
    public static String random(int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            if (random.nextInt(36) < 10) {
                sb.append(digit[random.nextInt(digit.length)]);
            } else {
                sb.append(letter[random.nextInt(letter.length)]);
            }
        }
        return sb.toString();
    }

    /**
     * 字节数组转为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String byteArrToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 高4位
            sb.append(hex[(b >>> 4) & 15]);
            // 低4位
            sb.append(hex[b & 15]);
        }
        return sb.toString();
    }

    /**
     * 字符串连接
     *
     * @param separate 分隔符
     * @param arr      数组(当输入为基本类型数组时，表现为一个元素)
     * @param <T>      泛型
     * @return 字符串
     */
    @SafeVarargs
    public static <T> String join(String separate, T... arr) {
        if (arr == null || arr.length == 0) {
            return EMPTY;
        }
        return join(Arrays.asList(arr), separate);
    }

    /**
     * 字符串连接
     *
     * @param coll     集合
     * @param separate 分隔符
     * @param <T>      泛型
     * @return 字符串
     */
    public static <T> String join(Collection<T> coll, String separate) {
        if (coll == null || coll.isEmpty()) {
            return EMPTY;
        }
        StringBuilder res = new StringBuilder();
        separate = separate != null ? separate : EMPTY;
        for (T one : coll) {
            res.append(one.toString()).append(separate);
        }
        return res.substring(0, res.length() - separate.length());
    }

    /**
     * 字符串复制
     * repeat("a", 3) => "aaa"
     *
     * @param s     字符串
     * @param times 复制次数
     * @return 字符串
     */
    public static String repeat(String s, int times) {
        if (isEmpty(s)) {
            return s;
        }
        if (times <= 0) {
            return EMPTY;
        }
        StringBuilder res = new StringBuilder(s);
        for (int i = 1; i < times; i++) {
            res.append(s);
        }
        return res.toString();
    }

    /**
     * 正则表达式搜索
     *
     * @param text  文本
     * @param regex 正则表达式
     * @return 能否找到符合条件的子串
     */
    public static boolean find(String text, String regex) {
        if (text == null || regex == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text).find();
    }

    /**
     * 正则表达式匹配
     *
     * @param text  文本
     * @param regex 正则表达式
     * @return 是否匹配
     */
    public static boolean matches(String text, String regex) {
        if (text == null || regex == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text).matches();
    }

}
