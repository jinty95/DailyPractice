package cn.jinty.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least Recently Used 最近使用时间最小
 *
 * @author jinty
 * @date 2021/3/15
 **/
public class LRUCache extends LinkedHashMap<Integer,Integer>{

    private int capacity;

    //初始化
    public LRUCache(int capacity) {
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    //查询
    public int get(int key) {
        return super.get(key) == null ? -1 : super.get(key);
    }

    //存入
    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size() > capacity;
    }

}
