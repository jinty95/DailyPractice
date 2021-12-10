package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 实体(数据表)
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public enum EntityEnum implements EnumInterface {

    USER("用户"),
    ROLE("角色"),
    PERMISSION("权限");

    // 描述
    private final String desc;

    // 构造器
    EntityEnum(String desc) {
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
    private final static Map<String, EntityEnum> nameMap;
    private final static Map<String, EntityEnum> descMap;

    static {
        nameMap = new HashMap<>();
        descMap = new HashMap<>();
        for (EntityEnum one : EntityEnum.values()) {
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
    public static EntityEnum parseByName(String name) {
        return nameMap.get(name);
    }

    // 根据描述解析为枚举对象
    public static EntityEnum parseByDesc(String desc) {
        return descMap.get(desc);
    }

    // 名称转为描述
    public static String nameToDesc(String name) {
        EntityEnum res = parseByName(name);
        if (res == null) {
            return null;
        }
        return res.getDesc();
    }

    // 描述转为名称
    public static String descToName(String desc) {
        EntityEnum res = parseByDesc(desc);
        if (res == null) {
            return null;
        }
        return res.name();
    }

}
