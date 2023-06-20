package cn.jinty.util.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 字符串 - 工具类
 *
 * @author Jinty
 * @date 2021/4/9
 **/
public final class StringUtil {

    private StringUtil() {
    }

    // 空串
    public static final String EMPTY = "";

    // 换行(LineFeed) - Linux系统换行符
    public static final char LF = '\n';
    // 回车(CarriageReturn) - Mac系统换行符
    public static final char CR = '\r';
    // 回车换行 - Windows系统换行符
    public static final String CRLF = "\r\n";

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
     * 字符串非空判断
     *
     * @param s 字符串
     * @return 是否非空
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
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
     * 字符串非空白判断
     *
     * @param s 字符串
     * @return 是否非空白
     */
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
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
     * 字符串判等 (忽略大小写)
     *
     * @param a 字符串
     * @param b 字符串
     * @return 是否相等 (忽略大小写)
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equalsIgnoreCase(b);
    }

    /**
     * 字符串长度
     *
     * @param s 字符串
     * @return 长度
     */
    public static int length(String s) {
        if (s == null) {
            return 0;
        }
        return s.length();
    }

    /**
     * 截取子串
     *
     * @param s      字符串
     * @param start  起始索引
     * @param length 长度
     * @return 子串
     */
    public static String substring(String s, int start, int length) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        if (start < 0 || start >= s.length() || length <= 0) {
            return EMPTY;
        }
        int end = Math.min(start + length, s.length());
        return s.substring(start, end);
    }

    /**
     * 统计子串出现次数
     *
     * @param s   字符串
     * @param sub 子串
     * @return 出现次数
     */
    public static int count(String s, String sub) {
        if (isEmpty(s) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int pos = 0;
        int index;
        while ((index = s.indexOf(sub, pos)) != -1) {
            count++;
            pos = index + sub.length();
        }
        return count;
    }

    /**
     * 去除字符串的前后"空白符"
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String trim(String s) {
        if (s == null) {
            return EMPTY;
        }
        return s.trim();
    }

    /**
     * 去除字符串的前后"空白符及指定字符"
     *
     * @param s    字符串
     * @param trim 指定字符
     * @return 字符串
     */
    public static String trim(String s, String trim) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        s = s.trim();
        if (isEmpty(trim)) {
            return s;
        }
        while (s.startsWith(trim)) {
            s = s.substring(trim.length()).trim();
        }
        while (s.endsWith(trim)) {
            s = s.substring(0, s.length() - trim.length()).trim();
        }
        return s;
    }

    /**
     * 将字符串转为大写
     *
     * @param s 字符串
     * @return 大写字符串
     */
    public static String toUpperCase(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        return s.toUpperCase();
    }

    /**
     * 将字符串转为小写
     *
     * @param s 字符串
     * @return 小写字符串
     */
    public static String toLowerCase(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        return s.toLowerCase();
    }

    /**
     * 将首字母转为大写
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String upperFirst(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        String upperFirst = String.valueOf(Character.toUpperCase(s.charAt(0)));
        return s.length() == 1 ? upperFirst : (upperFirst + s.substring(1));
    }

    /**
     * 将首字母转为小写
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String lowerFirst(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        String lowerFirst = String.valueOf(Character.toLowerCase(s.charAt(0)));
        return s.length() == 1 ? lowerFirst : (lowerFirst + s.substring(1));
    }

    /**
     * 是否包含目标字符串
     *
     * @param s      字符串
     * @param target 目标字符串
     * @return 是否
     */
    public static boolean contains(String s, String target) {
        if (s == null || target == null) {
            return false;
        }
        return s.contains(target);
    }

    /**
     * 替换命中匹配的字符串 (文本匹配替换)
     *
     * @param s           字符串
     * @param target      用于匹配的字符串
     * @param replacement 用于替换的字符串
     * @return 字符串
     */
    public static String replace(String s, String target, String replacement) {
        if (s == null) {
            return EMPTY;
        }
        if (target == null || replacement == null) {
            return s;
        }
        return s.replace(target, replacement);
    }

    /**
     * 替换首个命中匹配的字符串 (正则匹配替换)
     *
     * @param s           字符串
     * @param regex       用于匹配的正则表达式
     * @param replacement 用于替换的字符串 (支持用"$数字"捕捉正则匹配结果)
     * @return 字符串
     */
    public static String replaceFirst(String s, String regex, String replacement) {
        if (s == null) {
            return EMPTY;
        }
        if (regex == null || replacement == null) {
            return s;
        }
        return s.replaceFirst(regex, replacement);
    }

    /**
     * 替换所有命中匹配的字符串 (正则匹配替换)
     *
     * @param s           字符串
     * @param regex       用于匹配的正则表达式
     * @param replacement 用于替换的字符串 (支持用"$数字"捕捉正则匹配结果)
     * @return 字符串
     */
    public static String replaceAll(String s, String regex, String replacement) {
        if (s == null) {
            return EMPTY;
        }
        if (regex == null || replacement == null) {
            return s;
        }
        return s.replaceAll(regex, replacement);
    }

    /**
     * 字符串追加
     *
     * @param origin    原串
     * @param append    追加串
     * @param separator 分隔符
     * @return 字符串
     */
    public static String append(String origin, String append, String separator) {
        if (isEmpty(origin)) {
            return append == null ? EMPTY : append;
        }
        if (append == null || separator == null) {
            return origin;
        }
        return origin + separator + append;
    }

    /**
     * 将集合以分隔符连接成字符串
     *
     * @param coll      集合
     * @param separator 分隔符
     * @return 字符串
     */
    public static String join(Collection<?> coll, String separator) {
        if (coll == null || coll.isEmpty() || separator == null) {
            return EMPTY;
        }
        StringBuilder res = new StringBuilder();
        for (Object one : coll) {
            res.append(one == null ? null : one.toString()).append(separator);
        }
        return res.substring(0, res.length() - separator.length());
    }

    /**
     * 将对象数组以分隔符连接成字符串
     *
     * @param separator 分隔符
     * @param arr       对象数组
     * @return 字符串
     */
    public static String join(String separator, Object... arr) {
        if (arr == null || arr.length == 0 || separator == null) {
            return EMPTY;
        }
        StringBuilder res = new StringBuilder();
        for (Object one : arr) {
            res.append(one == null ? null : one.toString()).append(separator);
        }
        return res.substring(0, res.length() - separator.length());
    }

    /**
     * 将字符串按照分隔符切分为多个字符串
     *
     * @param s         字符串
     * @param separator 分隔符 (支持正则表达式)
     * @return 多个字符串
     */
    public static String[] split(String s, String separator) {
        if (s == null || separator == null) {
            return new String[0];
        }
        return s.split(separator);
    }

    /**
     * 将字符串按照分隔符切分，返回切分后的第一个字符串
     *
     * @param s         字符串
     * @param separator 分隔符 (支持正则表达式)
     * @return 第一个字符串
     */
    public static String splitAndGetFirst(String s, String separator) {
        String[] arr = split(s, separator);
        return arr.length == 0 ? EMPTY : arr[0];
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
        return repeat(s, EMPTY, times);
    }

    /**
     * 字符串复制，带分隔符
     * repeat("a", ",", 3) => "a,a,a"
     *
     * @param s         字符串
     * @param separator 分隔符
     * @param times     复制次数
     * @return 字符串
     */
    public static String repeat(String s, String separator, int times) {
        if (s == null || times <= 0) {
            return EMPTY;
        }
        if (separator == null) {
            return times == 1 ? s : EMPTY;
        }
        StringBuilder res = new StringBuilder(s);
        for (int i = 1; i < times; i++) {
            res.append(separator);
            res.append(s);
        }
        return res.toString();
    }

    /**
     * 字符串左填充，使达到指定长度
     *
     * @param s      字符串
     * @param length 目标长度
     * @param pad    填充字符
     * @return 字符串
     */
    public static String leftPad(String s, int length, char pad) {
        if (s == null) {
            s = EMPTY;
        }
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        for (int i = sb.length(); i < length; i++) {
            sb.append(pad);
        }
        sb.reverse();
        return sb.toString();
    }

    /**
     * 字符串右填充，使达到指定长度
     *
     * @param s      字符串
     * @param length 目标长度
     * @param pad    填充字符
     * @return 字符串
     */
    public static String rightPad(String s, int length, char pad) {
        if (s == null) {
            s = EMPTY;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = sb.length(); i < length; i++) {
            sb.append(pad);
        }
        return sb.toString();
    }

    /**
     * 字符串 -> 二进制字符串
     *
     * @param s 字符串
     * @return 二进制字符串
     */
    public static String toBinaryString(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        return toBinaryString(s.getBytes());
    }

    /**
     * 字节流 -> 二进制字符串
     *
     * @param bytes 字节流
     * @return 二进制字符串
     */
    public static String toBinaryString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toBinaryString(b)).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 字节 -> 二进制字符串
     *
     * @param b 字节
     * @return 二进制字符串
     */
    public static String toBinaryString(byte b) {
        StringBuilder sb = new StringBuilder();
        // 逐位与1，得到每一位的数字
        for (int i = 0; i < 8; i++) {
            sb.append(b & 1);
            b >>= 1;
        }
        // 翻转
        return sb.reverse().toString();
    }

    /**
     * 移除4字节编码的字符
     *
     * @param s 输入字符串
     * @return 输出字符串
     */
    public static String remove4ByteChar(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        byte[] bytes = s.getBytes();
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            // UTF-8的4字节编码以11110开头
            if ((bytes[i] & 0xF8) == 0xF0) {
                i += 3;
            } else {
                byteList.add(bytes[i]);
            }
        }
        bytes = new byte[byteList.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteList.get(i);
        }
        return new String(bytes);
    }

}
