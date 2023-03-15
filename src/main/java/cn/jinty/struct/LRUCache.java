package cn.jinty.struct;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存
 * 存储形式：哈希表 (通过元素键快速获取元素值)
 * 淘汰方式：LRU - Least Recently Used (将最近使用时间最小的元素删除)
 * 过期实现：延时线程池 (在存入元素时建立一个延时任务，到期删除元素)
 * 线程安全：通过 Lock 悲观锁保证
 *
 * @author Jinty
 * @date 2021/3/15
 **/
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    // 最大容量
    private final int capacity;

    // 延时线程池
    private final ScheduledExecutorService executorService;

    // 锁
    private final Lock lock;

    /**
     * 初始化
     *
     * @param capacity 最大容量
     */
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
        this.executorService = new ScheduledThreadPoolExecutor(8);
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    @Override
    public V put(K key, V value) {
        lock.lock();
        try {
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 存入键值对，并设置过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间(毫秒)
     * @return 值
     */
    public V put(K key, V value, long expireTime) {
        lock.lock();
        try {
            value = super.put(key, value);
            if (expireTime > 0) {
                removeExpireEntry(key, expireTime);
            }
            return value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 移除过期元素
     *
     * @param key        元素键
     * @param expireTime 过期时间(毫秒)
     */
    private void removeExpireEntry(Object key, Long expireTime) {
        executorService.schedule(() -> super.remove(key), expireTime, TimeUnit.MILLISECONDS);
    }

}
