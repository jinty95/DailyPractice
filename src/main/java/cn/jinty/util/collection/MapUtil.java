package cn.jinty.util.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射 - 工具类
 *
 * @author Jinty
 * @date 2023/2/28
 **/
public final class MapUtil {

    private MapUtil() {
    }

    /**
     * 是否为空
     *
     * @param map 映射
     * @return 是否
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 是否非空
     *
     * @param map 映射
     * @return 是否
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 获取大小
     *
     * @param map 映射
     * @return 大小
     */
    public static int size(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    /**
     * 键值对反转
     *
     * @param map 原映射
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 反转映射
     */
    public static <K, V> Map<V, K> invert(Map<K, V> map) {
        Map<V, K> result = new HashMap<>();
        if (isNotEmpty(map)) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                result.put(entry.getValue(), entry.getKey());
            }
        }
        return result;
    }

    /**
     * 根据键获取值，不存在时返回默认值
     *
     * @param map        映射
     * @param key        键
     * @param defaultVal 默认值
     * @param <K>        键类型
     * @param <V>        值类型
     * @return 值
     */
    public static <K, V> V getOrDefault(Map<K, V> map, K key, V defaultVal) {
        if (isEmpty(map)) {
            return defaultVal;
        }
        return map.getOrDefault(key, defaultVal);
    }

}
