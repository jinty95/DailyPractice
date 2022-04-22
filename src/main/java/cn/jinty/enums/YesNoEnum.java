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
public enum YesNoEnum implements EnumInterface<Byte> {

    NO((byte) 0, "否"),
    YES((byte) 1, "是");

    // 编码
    private final Byte code;
    // 描述
    private final String desc;

    // 枚举静态化
    private final static Map<Byte, YesNoEnum> codeMap;
    private final static Map<String, YesNoEnum> descMap;

    static {
        codeMap = new HashMap<>();
        descMap = new HashMap<>();
        for (YesNoEnum one : YesNoEnum.values()) {
            codeMap.put(one.getCode(), one);
            descMap.put(one.getDesc(), one);
        }
    }

    // 判断编码是否在枚举范围内
    public static boolean containsCode(Byte code) {
        return codeMap.containsKey(code);
    }

    // 判断描述是否在枚举范围内
    public static boolean containsDesc(String desc) {
        return descMap.containsKey(desc);
    }

    // 根据编码解析为枚举对象
    public static YesNoEnum parseByCode(Byte code) {
        return codeMap.get(code);
    }

    // 根据描述解析为枚举对象
    public static YesNoEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 编码转为描述
    public static String codeToDesc(Byte code) {
        YesNoEnum res = parseByCode(code);
        if (res == null) {
            return null;
        }
        return res.getDesc();
    }

    // 描述转为编码
    public static Byte descToCode(String desc) {
        YesNoEnum res = parseByDesc(desc);
        if (res == null) {
            return null;
        }
        return res.getCode();
    }

}
