package cn.jinty.mvel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作符类型 - 枚举
 *
 * @author Jinty
 * @date 2023/9/6
 **/
@Getter
@AllArgsConstructor
public enum OperatorTypeEnum {

    OPERATION("OPERATION", "运算操作符"),
    LOGIC("LOGIC", "逻辑操作符"),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;

    // 枚举静态化
    private static final Map<String, OperatorTypeEnum> codeMap;
    private static final Map<String, OperatorTypeEnum> descMap;

    static {
        codeMap = new HashMap<>();
        descMap = new HashMap<>();
        for (OperatorTypeEnum one : values()) {
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
    public static OperatorTypeEnum parseByCode(String code) {
        return codeMap.get(code);
    }

    // 根据描述解析为枚举对象
    public static OperatorTypeEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 编码转为描述
    public static String codeToDesc(String code) {
        OperatorTypeEnum res = parseByCode(code);
        return res == null ? null : res.getDesc();
    }

    // 描述转为编码
    public static String descToCode(String desc) {
        OperatorTypeEnum res = parseByDesc(desc);
        return res == null ? null : res.getCode();
    }

}
