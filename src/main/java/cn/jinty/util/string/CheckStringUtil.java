package cn.jinty.util.string;

import cn.jinty.enums.SpecialCharEnum;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查字符串 - 工具类
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class CheckStringUtil {

    private CheckStringUtil() {
    }

    // 特殊字符
    private static final Set<Character> SPECIAL_CHAR_SET = new HashSet<>();

    static {
        for (SpecialCharEnum specialCharEnum : SpecialCharEnum.values()) {
            SPECIAL_CHAR_SET.add(specialCharEnum.getCode());
        }
    }

    /**
     * 字符串是否全为字母
     *
     * @param s 字符串
     * @return 是否全为字母
     */
    public static boolean isLetter(String s) {
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // 数值正则式
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("[+-]?[0-9]+(.[0-9]+)?");

    /**
     * 字符串是否是一个数值 (可带正负号及小数点)
     *
     * @param s 字符串
     * @return 是否
     */
    public static boolean isNumeric(String s) {
        if (StringUtil.isEmpty(s)) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(s).matches();
    }

    /**
     * 字符串是否全为大写字母
     *
     * @param s 字符串
     * @return 是否全为大写字母
     */
    public static boolean isUpperCase(String s) {
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
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
        if (StringUtil.isEmpty(s)) {
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
     * 检查密码是否符合要求(长度8-16位，且必须包含大写字母、小写字母、数字、特殊字符)
     *
     * @param password 密码
     * @return 是否
     */
    public static boolean checkPassword(String password) {
        if (StringUtil.isEmpty(password)) {
            return false;
        }
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }
        return containsUpperCase(password) && containsLowerCase(password)
                && containsDigit(password) && containsSpecialChar(password);
    }

    /**
     * 检查手机号码是否符合要求(长度11位，纯数字，第一位为1，第二位为[3,9])
     *
     * @param phoneNum 手机号码
     * @return 是否
     */
    public static boolean checkPhoneNum(String phoneNum) {
        if (StringUtil.isEmpty(phoneNum)) {
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

}
