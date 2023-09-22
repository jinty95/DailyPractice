package cn.jinty.mvel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 运算操作符 - 枚举
 *
 * @author Jinty
 * @date 2023/9/5
 **/
@Getter
@AllArgsConstructor
public enum OperationOperatorEnum {

    EQUALS("EQUALS", "%s == \"%s\"", "等于"),
    NOT_EQUALS("NOT_EQUALS", "%s != \"%s\"", "不等于"),
    GREATER_THAN("GREATER_THAN", "%s > \"%s\"", "大于"),
    GREATER_THAN_OR_EQUALS("GREATER_THAN_OR_EQUALS", "%s >= \"%s\"", "大于等于"),
    LESS_THAN("LESS_THAN", "%s < \"%s\"", "小于"),
    LESS_THAN_OR_EQUALS("LESS_THAN_OR_EQUALS", "%s <= \"%s\"", "小于等于"),
    CONTAINS("CONTAINS", "%s.contains(%s)", "包含"),
    NOT_CONTAINS("NOT_CONTAINS", "!(%s.contains(%s))", "不包含"),
    STARTS_WITH("STARTS_WITH", "%s.startsWith(\"%s\")", "匹配前缀(右模糊)"),
    ENDS_WITH("ENDS_WITH", "%s.endsWith(\"%s\")", "匹配后缀(左模糊)"),
    ;

    // 编码
    private final String code;
    // 表达式
    private final String expression;
    // 描述
    private final String desc;

    // 枚举静态化
    private static final Map<String, OperationOperatorEnum> codeMap;
    private static final Map<String, OperationOperatorEnum> descMap;

    static {
        codeMap = new HashMap<>();
        descMap = new HashMap<>();
        for (OperationOperatorEnum one : values()) {
            codeMap.put(one.getCode(), one);
            descMap.put(one.getDesc(), one);
        }
    }

    // 判断编码是否在枚举范围内
    public static boolean containsCode(String code) {
        return codeMap.containsKey(code);
    }

    // 判断描述是否在枚举范围内
    public static boolean containsDesc(String desc) {
        return descMap.containsKey(desc);
    }

    // 根据编码解析为枚举对象
    public static OperationOperatorEnum parseByCode(String code) {
        return codeMap.get(code);
    }

    // 根据描述解析为枚举对象
    public static OperationOperatorEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 编码转为描述
    public static String codeToDesc(String code) {
        OperationOperatorEnum res = parseByCode(code);
        return res == null ? null : res.getDesc();
    }

    // 描述转为编码
    public static String descToCode(String desc) {
        OperationOperatorEnum res = parseByDesc(desc);
        return res == null ? null : res.getCode();
    }

}
