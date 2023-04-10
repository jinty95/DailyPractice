package cn.jinty.util;

import cn.jinty.enums.SpecialCharEnum;
import cn.jinty.util.collection.ArrayUtil;

import java.util.*;

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
    private static final Set<Character> SPECIAL_CHAR_SET = new HashSet<>();

    static {
        for (SpecialCharEnum specialCharEnum : SpecialCharEnum.values()) {
            SPECIAL_CHAR_SET.add(specialCharEnum.getCode());
        }
    }

    // 随机数
    private static final Random RANDOM = new Random();

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
            res.append(one.toString()).append(separator);
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
            res.append(one.toString()).append(separator);
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
        snake = snake.toLowerCase();
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
     * 求两个字符串的最长公共子串
     * (存在多个时，返回最早出现的一个)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子串
     */
    public static String longestCommonSubstring(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return EMPTY;
        }
        int[][] dp = getDpArrayForLongestCommonSubstring(s1, s2);
        int max = ArrayUtil.getMax(dp);
        int[] index = ArrayUtil.indexOf(dp, max);
        assert index != null;
        return s1.substring(index[0] - max, index[0]);
    }

    /**
     * 求两个字符串的最长公共子串的长度
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子串的长度
     */
    public static int longestCommonSubstringLength(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return 0;
        }
        int[][] dp = getDpArrayForLongestCommonSubstring(s1, s2);
        return ArrayUtil.getMax(dp);
    }

    /**
     * 获取动态规划数组 (用于求两个字符串的最长公共子串的长度)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 动态规划数组
     */
    private static int[][] getDpArrayForLongestCommonSubstring(String s1, String s2) {
        // 动态规划
        // dp[i][j]表示s1[0...i-1]与s2[0...j-1]的包含最后一个字符的最长公共子串的长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        return dp;
    }

    /**
     * 求两个字符串的最长公共子序列
     * (满足条件的子序列一定只有一个，不可能存在两个字符不同的最长公共子序列)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子序列
     */
    public static String longestCommonSubsequence(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return EMPTY;
        }
        int[][] dp = getDpArrayForLongestCommonSubsequence(s1, s2);
        int max = dp[s1.length()][s2.length()];
        char[] res = new char[max];
        int idx1 = s1.length();
        int idx2 = s2.length();
        while (max > 0) {
            if (idx2 > 0 && dp[idx1][idx2] == dp[idx1][idx2 - 1]) {
                idx2--;
            } else if (idx1 > 0 && dp[idx1][idx2] == dp[idx1 - 1][idx2]) {
                idx1--;
            } else {
                res[--max] = s1.charAt(idx1 - 1);
                idx1--;
                idx2--;
            }
        }
        return new String(res);
    }

    /**
     * 求两个字符串的最长公共子序列的长度
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最长公共子序列的长度
     */
    public static int longestCommonSubsequenceLength(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return 0;
        }
        int[][] dp = getDpArrayForLongestCommonSubsequence(s1, s2);
        return dp[s1.length()][s2.length()];
    }

    /**
     * 获取动态规划数组 (用于求两个字符串的最长公共子序列的长度)
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 动态规划数组
     */
    private static int[][] getDpArrayForLongestCommonSubsequence(String s1, String s2) {
        // 动态规划
        // dp[i][j]表示s1[0...i-1]与s2[0...j-1]的最大公共子序列的长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp;
    }

    /**
     * 获取两个字符串的差异部分
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 分别去除共同部分留下的差异字符串
     */
    public static String[] diff(String s1, String s2) {
        if (isEmpty(s1) && isEmpty(s2)) {
            return new String[]{EMPTY, EMPTY};
        }
        if (isEmpty(s1)) {
            return new String[]{EMPTY, s2};
        }
        if (isEmpty(s2)) {
            return new String[]{s1, EMPTY};
        }
        String common = longestCommonSubsequence(s1, s2);
        return new String[]{removeSubsequence(s1, common), removeSubsequence(s2, common)};
    }

    /**
     * 从字符串中移除掉一个给定的子序列
     *
     * @param s           字符串 (长度 n)
     * @param subsequence 子序列 (长度 m)
     * @return 移除后的字符串 (长度 n-m)
     */
    public static String removeSubsequence(String s, String subsequence) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        if (isEmpty(subsequence)) {
            return s;
        }
        int start = 0;
        Set<Integer> removeIdx = new HashSet<>();
        for (char sub : subsequence.toCharArray()) {
            int idx = s.indexOf(sub, start);
            removeIdx.add(idx);
            start = idx + 1;
        }
        char[] res = new char[s.length() - subsequence.length()];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (removeIdx.contains(i)) {
                continue;
            }
            res[j++] = s.charAt(i);
        }
        return new String(res);
    }

    /**
     * 添加转义字符 (符号为 \，英文名为 Escape Character)
     * 使得具有特殊含义的字符，呈现其字面上的文本
     * 例如：
     * "\n"表示一个换行符，处理后变成"\\n"，表示文本\n
     * "\\"表示一个反斜杠，处理后变成"\\\\"，表示文本\\
     * "\""表示一个双引号，处理后变成"\\\""，表示文本\"
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String escape(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 移除转义字符 (符号为 \，英文名为 Escape Character)
     * 使得一个字面上的文本，呈现其对应的特殊含义
     * 例如：
     * "\\n"表示文本\n，处理后变成"\n"，表示一个换行符
     * "\\\\"表示文本\\，处理后变成"\\"，表示一个反斜杠
     * "\\\""表示文本\"，处理后变成"\""，表示一个双引号
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String unescape(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        boolean hasBackSlash = false;
        for (char c : s.toCharArray()) {
            if (hasBackSlash) {
                hasBackSlash = false;
                switch (c) {
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    default:
                        sb.append('\\');
                        sb.append(c);
                }
                continue;
            } else if (c == '\\') {
                hasBackSlash = true;
                continue;
            }
            sb.append(c);
        }
        if (hasBackSlash) {
            sb.append('\\');
        }
        return sb.toString();
    }

    /**
     * 递归移除转义字符，直到无法继续移除为止
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String unescapeAll(String s) {
        if (isEmpty(s)) {
            return EMPTY;
        }
        String unescape = unescape(s);
        if (unescape.equals(s)) {
            return unescape;
        }
        return unescapeAll(unescape);
    }

}
