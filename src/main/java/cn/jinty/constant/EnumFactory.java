package cn.jinty.constant;

import cn.jinty.entity.KeyValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举 - 工厂
 *
 * @author Jinty
 * @date 2021/12/7
 */
public final class EnumFactory {

    // 存储所有的枚举映射
    private static final Map<String, List<KeyValue<String, String>>> map = new HashMap<>();

    static {
        set(YesOrNoEnum.class.getSimpleName(), YesOrNoEnum.values());
    }

    /**
     * 保存枚举类型及对应的键值对列表
     *
     * @param key   枚举类型
     * @param value 键值对列表
     */
    private static void set(String key, EnumInterface[] value) {
        List<KeyValue<String, String>> list = new ArrayList<>();
        for (EnumInterface one : value) {
            list.add(new KeyValue<>(one.getName(), one.getDesc()));
        }
        map.put(key, list);
    }

    /**
     * 根据枚举类型获取对应的键值对列表
     *
     * @param key 枚举类型
     * @return 键值对列表
     */
    public static List<KeyValue<String, String>> get(String key) {
        List<KeyValue<String, String>> list = map.get(key);
        return list == null ? new ArrayList<>() : list;
    }

}
