package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 是否
 *
 * @author Jinty
 * @date 2021/11/16
 **/
@Getter
@AllArgsConstructor
public enum YesNoEnum implements EnumInterface<Integer> {

    N(0, "否"),
    Y(1, "是");

    // 编码
    private final Integer code;
    // 描述
    private final String desc;

    // 枚举静态化
    private static final Map<Integer, YesNoEnum> codeMap;
    private static final Map<String, YesNoEnum> descMap;

    static {
        codeMap = new HashMap<>();
        descMap = new HashMap<>();
        for (YesNoEnum one : values()) {
            codeMap.put(one.getCode(), one);
            descMap.put(one.getDesc(), one);
        }
    }

    // 判断编码是否在枚举范围内
    public static boolean containsCode(Integer code) {
        return codeMap.containsKey(code);
    }

    // 判断描述是否在枚举范围内
    public static boolean containsDesc(String desc) {
        return descMap.containsKey(desc);
    }

    // 根据编码解析为枚举对象
    public static YesNoEnum parseByCode(Integer code) {
        return codeMap.get(code);
    }

    // 根据描述解析为枚举对象
    public static YesNoEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 编码转为描述
    public static String codeToDesc(Integer code) {
        YesNoEnum res = parseByCode(code);
        return res == null ? null : res.getDesc();
    }

    // 描述转为编码
    public static Integer descToCode(String desc) {
        YesNoEnum res = parseByDesc(desc);
        return res == null ? null : res.getCode();
    }

}
