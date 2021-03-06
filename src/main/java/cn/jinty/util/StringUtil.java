package cn.jinty.util;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 字符串 - 工具类
 *
 * @author Jinty
 * @date 2021/4/9
 **/
public final class StringUtil {

    // 十六进制字符集
    private static final char[] HEX = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    // 数字
    private static final char[] DIGIT = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    // 字母
    private static final char[] LETTER = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    // 特殊字符
    private static final char[] SPECIAL_CHAR = {
            '~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
            '-', '_', '+', '=', '{', '[', '}', ']', '|', '\\', ':', ';',
            '"', '\'', '<', ',', '>', '.', '?', '/', '\r', '\n', '\t', ' '
    };
    private static final Set<Character> SPECIAL_CHAR_SET = SetUtil.asSet(SPECIAL_CHAR);

    // 随机数
    private static final Random RANDOM = new Random();

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
     * 字符串是否包含字母
     *
     * @param s 字符串
     * @return 是否包含字母
     */
    public static boolean containsLetter(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串是否包含大写字母
     *
     * @param s 字符串
     * @return 是否包含大写字母
     */
    public static boolean containsUpperCase(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串是否包含小写字母
     *
     * @param s 字符串
     * @return 是否包含小写字母
     */
    public static boolean containsLowerCase(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串是否数字
     *
     * @param s 字符串
     * @return 是否数字
     */
    public static boolean containsDigit(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串是否包含特殊字符
     *
     * @param s 字符串
     * @return 是否包含特殊字符
     */
    public static boolean containsSpecialChar(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (SPECIAL_CHAR_SET.contains(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验密码是否符合要求(长度8-16位，且必须包含大写字母、小写字母、数字、特殊字符)
     *
     * @param password 密码
     * @return 是否符合要求
     */
    public static boolean checkPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }
        return containsUpperCase(password) && containsLowerCase(password)
                && containsDigit(password) && containsSpecialChar(password);
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
            sb.append(DIGIT[RANDOM.nextInt(DIGIT.length)]);
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
            sb.append(LETTER[RANDOM.nextInt(LETTER.length)]);
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
            if (RANDOM.nextInt(36) < 10) {
                sb.append(DIGIT[RANDOM.nextInt(DIGIT.length)]);
            } else {
                sb.append(LETTER[RANDOM.nextInt(LETTER.length)]);
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
            sb.append(HEX[(b >>> 4) & 15]);
            // 低4位
            sb.append(HEX[b & 15]);
        }
        return sb.toString();
    }

    /**
     * 字符串追加
     *
     * @param origin   原串
     * @param append   追加串
     * @param separate 分隔符
     * @return 字符串
     */
    public static String append(String origin, String append, String separate) {
        if (isEmpty(origin)) {
            return append;
        }
        return origin + separate + append;
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
            return EMPTY;
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

    /**
     * 驼峰命名 -> 下划线命名
     *
     * @param camel 驼峰字符串
     * @return 下划线字符串
     */
    public static String camelToSnake(String camel) {
        if (isEmpty(camel)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camel.length(); i++) {
            char c = camel.charAt(i);
            // 寻找大写字母，在前面添加下划线，并将其转为小写
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append("_");
                }
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 下划线命名 -> 驼峰命名
     *
     * @param snake   下划线字符串
     * @param upFirst 是否首字符大写
     * @return 驼峰字符串
     */
    public static String snakeToCamel(String snake, boolean upFirst) {
        if (isEmpty(snake)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < snake.length(); i++) {
            char c = snake.charAt(i);
            // 寻找下划线，将其移除，并将后一个字母变为大写
            if (c == '_') {
                upFirst = true;
            } else {
                if (upFirst) {
                    c = Character.toUpperCase(c);
                }
                sb.append(c);
                upFirst = false;
            }
        }
        return sb.toString();
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

}
