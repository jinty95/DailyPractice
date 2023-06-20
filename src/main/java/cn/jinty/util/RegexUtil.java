package cn.jinty.util;

import java.util.regex.Pattern;

/**
 * 正则表达式 - 工具类
 *
 * @author Jinty
 * @date 2023/2/10
 **/
public final class RegexUtil {

    private RegexUtil() {
    }

    // 匹配任意一个字符
    public static final String ALL = "[\\s\\S]";
    // 匹配任意一个"空白符"
    public static final String BLANK = "\\s";
    // 匹配任意一个非"空白符"
    public static final String NOT_BLANK = "\\S";
    // 匹配任意一个"数字"
    public static final String NUMBER = "\\d";
    // 匹配任意一个非"数字"
    public static final String NOT_NUMBER = "\\D";
    // 匹配任意一个"字母"
    public static final String LETTER = "[a-zA-Z]";
    // 匹配任意一个非"字母"
    public static final String NOT_LETTER = "[^a-zA-Z]";
    // 匹配任意一个"数字/字母/下划线"
    public static final String NUMBER_LETTER_UNDERLINE = "\\w";
    // 匹配任意一个非"数字/字母/下划线"
    public static final String NOT_NUMBER_LETTER_UNDERLINE = "\\W";
    // 匹配任意一个"汉字"
    public static final String CHINESE = "[\\u4E00-\\u9FA5]";
    // 匹配任意一个非"汉字"
    public static final String NOT_CHINESE = "[^\\u4E00-\\u9FA5]";

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
