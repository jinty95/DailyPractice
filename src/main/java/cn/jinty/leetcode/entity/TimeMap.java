package cn.jinty.leetcode.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基于时间的键值存储
 *
 * @author Jinty
 * @date 2021/7/10
 **/
public class TimeMap {

    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    /**
     * 存储键值对(值与时间戳相关)
     *
     * @param key       键
     * @param value     时间戳对应的值
     * @param timestamp 时间戳
     */
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> treeMap = map.computeIfAbsent(key, k -> new TreeMap<>());
        treeMap.put(timestamp, value);
    }

    /**
     * 根据键查询时间戳对应的值
     * 寻找 timestamp_prev <= timestamp 对应的所有值
     * 如果有多个这样的值，则返回对应最大的timestamp_prev的那个值
     *
     * @param key       键
     * @param timestamp 时间戳
     * @return 时间戳对应的值
     */
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null) return "";
        Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
        return entry == null ? "" : entry.getValue();
    }

}
