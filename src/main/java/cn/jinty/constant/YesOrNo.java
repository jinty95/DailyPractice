package cn.jinty.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 是否
 *
 * @author Jinty
 * @date 2021/11/16
 **/
public enum YesOrNo {

    NO("否"),
    YES("是");

    // 描述
    private final String desc;

    // 构造器
    YesOrNo(String desc) {
        this.desc = desc;
    }

    // getter
    public String getDesc() {
        return desc;
    }

    // 枚举静态化
    private final static Map<String, YesOrNo> map;
    static {
        map = new HashMap<>();
        for (YesOrNo one : YesOrNo.values()) {
            map.put(one.name(), one);
        }
    }

    // 根据名称解析为枚举
    public static YesOrNo parse(String name) {
        return map.get(name);
    }

    // 根据名称解析为描述
    public static String parseToDesc(String name) {
        YesOrNo res = parse(name);
        if (res == null) {
            return null;
        }
        return res.getDesc();
    }

    // 判断名称是否在枚举范围内
    public static boolean contains(String name) {
        return parse(name) != null;
    }

}
