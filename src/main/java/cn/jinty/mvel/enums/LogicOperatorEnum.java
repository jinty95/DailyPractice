package cn.jinty.mvel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 逻辑操作符 - 枚举
 *
 * @author Jinty
 * @date 2023/9/5
 **/
@Getter
@AllArgsConstructor
public enum LogicOperatorEnum {

    AND("AND", "%s && %s", "与"),
    OR("OR", "%s || %s", "或"),
    NOT("NOT", "!%s", "非"),
    ;

    // 编码
    private final String code;
    // 表达式
    private final String expression;
    // 描述
    private final String desc;

    // 枚举静态化
    private static final Map<String, LogicOperatorEnum> codeMap;
    private static final Map<String, LogicOperatorEnum> descMap;

    static {
        codeMap = new HashMap<>();
        descMap = new HashMap<>();
        for (LogicOperatorEnum one : values()) {
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
    public static LogicOperatorEnum parseByCode(String code) {
        return codeMap.get(code);
    }

    // 根据描述解析为枚举对象
    public static LogicOperatorEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 编码转为描述
    public static String codeToDesc(String code) {
        LogicOperatorEnum res = parseByCode(code);
        return res == null ? null : res.getDesc();
    }

    // 描述转为编码
    public static String descToCode(String desc) {
        LogicOperatorEnum res = parseByDesc(desc);
        return res == null ? null : res.getCode();
    }

}
