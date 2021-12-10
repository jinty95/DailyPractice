package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 操作
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public enum OperationEnum implements EnumInterface {

    INSERT("新增"),
    UPDATE("更新"),
    DELETE("删除"),
    SELECT("查询");

    // 描述
    private final String desc;

    // 构造器
    OperationEnum(String desc) {
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
    private final static Map<String, OperationEnum> nameMap;
    private final static Map<String, OperationEnum> descMap;

    static {
        nameMap = new HashMap<>();
        descMap = new HashMap<>();
        for (OperationEnum one : OperationEnum.values()) {
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
    public static OperationEnum parseByName(String name) {
        return nameMap.get(name);
    }

    // 根据描述解析为枚举对象
    public static OperationEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 名称转为描述
    public static String nameToDesc(String name) {
        OperationEnum res = parseByName(name);
        if (res == null) {
            return null;
        }
        return res.getDesc();
    }

    // 描述转为名称
    public static String descToName(String desc) {
        OperationEnum res = parseByDesc(desc);
        if (res == null) {
            return null;
        }
        return res.name();
    }

}
