package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 特殊字符
 * (取键盘上的所有特殊字符，半角)
 *
 * @author Jinty
 * @date 2023/3/8
 **/
@Getter
@AllArgsConstructor
public enum SpecialCharEnum implements EnumInterface<Character> {

    TILDE('~', "波浪线"),
    BACK_QUOTE('`', "反单引号"),

    EXCLAMATION('!', "感叹号"),
    AT('@', "@"),
    POUND('#', "井号"),
    DOLLAR('$', "美元符"),
    PERCENT('%', "百分号"),
    CARET('^', "脱字符"),
    AND('&', "与符号"),
    ASTERISK('*', "星号"),
    LEFT_BRACKET('(', "左括号"),
    RIGHT_BRACKET(')', "右括号"),

    MINUS('-', "减号、连字符"),
    UNDER_LINE('_', "下划线"),

    PLUS('+', "加号"),
    EQUAL('=', "等号"),

    OPENING_BRACE('{', "左大括号"),
    LEFT_SQUARE_BRACKET('[', "左中括号"),

    CLOSING_BRACE('}', "右大括号"),
    RIGHT_SQUARE_BRACKET(']', "右中括号"),

    VERTICAL_BAR('|', "竖线、或符号"),
    BACK_SLASH('\\', "反斜杠"),

    COLON(':', "冒号"),
    SEMICOLON(';', "分号"),

    DOUBLE_QUOTE('"', "双引号"),
    SINGLE_QUOTE('\'', "单引号"),

    LESS_THEN('<', "小于号"),
    COMMA(',', "逗号"),

    GREATER_THEN('>', "大于号"),
    FULL_STOP('.', "句号"),

    QUESTION('?', "问号"),
    SLASH('/', "斜杠"),

    RETURN('\r', "回车"),
    NEWLINE('\n', "换行"),
    TABLE('\t', "制表符"),
    SPACE(' ', "空格"),
    ;

    // 字符
    private final Character code;
    // 描述
    private final String desc;

}
