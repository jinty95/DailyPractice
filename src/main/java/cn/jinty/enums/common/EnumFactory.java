package cn.jinty.enums.common;

import cn.jinty.entity.KeyValue;
import cn.jinty.enums.*;

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
    private static final Map<String, List<KeyValue<String, String>>> MAP = new HashMap<>();

    static {
        set(YesNoEnum.class.getSimpleName(), YesNoEnum.values());
        set(ResultEnum.class.getSimpleName(), ResultEnum.values());
        set(OperationEnum.class.getSimpleName(), OperationEnum.values());
        set(EntityEnum.class.getSimpleName(), EntityEnum.values());
        set(ErrorEnum.class.getSimpleName(), ErrorEnum.values());
        set(FileTypeEnum.class.getSimpleName(), FileTypeEnum.values());
        set(BinaryUnitEnum.class.getSimpleName(), BinaryUnitEnum.values());
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
        MAP.put(key, list);
    }

    /**
     * 根据枚举类型获取对应的键值对列表
     *
     * @param key 枚举类型
     * @return 键值对列表
     */
    public static List<KeyValue<String, String>> get(String key) {
        List<KeyValue<String, String>> list = MAP.get(key);
        return list == null ? new ArrayList<>() : list;
    }

}
