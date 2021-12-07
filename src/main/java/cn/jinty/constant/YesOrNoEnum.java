package cn.jinty.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 是否
 *
 * @author Jinty
 * @date 2021/11/16
 **/
public enum YesOrNoEnum implements EnumInterface {

    NO("否"),
    YES("是");

    // 描述
    private final String desc;

    // 构造器
    YesOrNoEnum(String desc) {
        this.desc = desc;
    }

    // getter
    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getDesc() {
        return desc;
    }

    // 枚举静态化
    private final static Map<String, YesOrNoEnum> nameMap;
    private final static Map<String, YesOrNoEnum> descMap;

    static {
        nameMap = new HashMap<>();
        descMap = new HashMap<>();
        for (YesOrNoEnum one : YesOrNoEnum.values()) {
            nameMap.put(one.name(), one);
            descMap.put(one.getDesc(), one);
        }
    }

    // 判断名称是否在枚举范围内
    public static boolean containsName(String name) {
        return nameMap.containsKey(name);
    }

    // 判断描述是否在枚举范围内
    public static boolean containsDesc(String desc) {
        return descMap.containsKey(desc);
    }

    // 根据名称解析为枚举对象
    public static YesOrNoEnum parseByName(String name) {
        return nameMap.get(name);
    }

    // 根据描述解析为枚举对象
    public static YesOrNoEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 名称转为描述
    public static String nameToDesc(String name) {
        YesOrNoEnum res = parseByName(name);
        if (res == null) {
            return null;
        }
        return res.getDesc();
    }

    // 描述转为名称
    public static String descToName(String desc) {
        YesOrNoEnum res = parseByDesc(desc);
        if (res == null) {
            return null;
        }
        return res.name();
    }

}
