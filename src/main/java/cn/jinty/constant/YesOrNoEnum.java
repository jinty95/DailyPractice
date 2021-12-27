package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 是否
 *
 * @author Jinty
 * @date 2021/11/16
 **/
public enum YesOrNoEnum implements EnumInterface {

    NO("N", "否"),
    YES("Y", "是");

    // 编码
    private final String code;
    // 描述
    private final String desc;

    // 构造器
    YesOrNoEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // getter
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    // 枚举静态化
    private final static Map<String, YesOrNoEnum> codeMap;
    private final static Map<String, YesOrNoEnum> descMap;

    static {
        codeMap = new HashMap<>();
        descMap = new HashMap<>();
        for (YesOrNoEnum one : YesOrNoEnum.values()) {
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
    public static YesOrNoEnum parseByCode(String code) {
        return codeMap.get(code);
    }

    // 根据描述解析为枚举对象
    public static YesOrNoEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 编码转为描述
    public static String codeToDesc(String code) {
        YesOrNoEnum res = parseByCode(code);
        if (res == null) {
            return null;
        }
        return res.getDesc();
    }

    // 描述转为编码
    public static String descToCode(String desc) {
        YesOrNoEnum res = parseByDesc(desc);
        if (res == null) {
            return null;
        }
        return res.getCode();
    }

}
