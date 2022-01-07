package cn.jinty.constant.common;

import cn.jinty.constant.OperationEnum;
import cn.jinty.constant.EntityEnum;
import cn.jinty.constant.ResultEnum;
import cn.jinty.constant.YesOrNoEnum;
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
        set(ResultEnum.class.getSimpleName(), ResultEnum.values());
        set(OperationEnum.class.getSimpleName(), OperationEnum.values());
        set(EntityEnum.class.getSimpleName(), EntityEnum.values());
    }

    /**
     * 保存枚举类型及对应的键值对列表
     *
     * @param key   枚举类型
     * @param value 键值对列表
     */
    private static <T> void set(String key, EnumInterface<T>[] value) {
        List<KeyValue<String, String>> list = new ArrayList<>();
        for (EnumInterface<T> one : value) {
            list.add(new KeyValue<>(String.valueOf(one.getCode()), one.getDesc()));
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
