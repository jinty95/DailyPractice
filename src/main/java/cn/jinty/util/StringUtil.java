package cn.jinty.util;

import cn.jinty.util.collection.SetUtil;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 字符串 - 工具类
 *
 * @author Jinty
 * @date 2021/4/9
 **/
public final class StringUtil {

    private StringUtil() {
    }

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
     * 去除字符串的前后"空格"
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String trim(String s) {
        if (isEmpty(s)) {
            return s;
        }
        return s.trim();
    }

    /**
     * 去除字符串的前后"空格及指定字符"
     *
     * @param s    字符串
     * @param trim 指定字符
     * @return 字符串
     */
    public static String trim(String s, String trim) {
        if (isEmpty(s)) {
            return s;
        }
        s = s.trim();
        if (isEmpty(trim)) {
            return s;
        }
        if (s.startsWith(trim)) {
            s = s.substring(trim.length()).trim();
        }
        if (s.endsWith(trim)) {
            s = s.substring(0, s.length() - trim.length()).trim();
        }
        return s;
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
     * 是否可能为国内手机号码
     *
     * @param phoneNum 手机号码
     * @return 是否
     */
    public static boolean isLikeCnPhoneNum(String phoneNum) {
        if (isEmpty(phoneNum)) {
            return false;
        }
        if (phoneNum.length() != 11) {
            return false;
        }
        if (!isDigit(phoneNum)) {
            return false;
        }
        return phoneNum.charAt(0) == '1' && phoneNum.charAt(1) >= '3' && phoneNum.charAt(1) <= '9';
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
            int rand = RANDOM.nextInt(36);
            if (rand < 10) {
                sb.append(DIGIT[rand]);
            } else {
                sb.append(LETTER[rand - 10]);
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
    public static String bytesToHex(byte[] bytes) {
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
     * 十六进制字符串转为字节数组
     *
     * @param hex 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexToBytes(String hex) {
        if (isEmpty(hex)) {
            return new byte[0];
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < HEX.length; i++) {
            map.put(HEX[i], i);
        }
        byte[] bytes = new byte[hex.length() / 2];
        int j = 0;
        for (int i = 0; i < hex.length(); i += 2) {
            int high = map.get(hex.charAt(i));
            int low = map.get(hex.charAt(i + 1));
            bytes[j++] = (byte) ((high << 4) | low);
        }
        return bytes;
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
     * 集合以分隔符连接成字符串
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
        if (separate == null) {
            separate = EMPTY;
        }
        StringBuilder res = new StringBuilder();
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
                    upFirst = false;
                }
                sb.append(c);
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
    public static int countOccur(String s, String sub) {
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

}
