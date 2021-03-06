package test.cn.jinty.struct;

import cn.jinty.struct.LRUCache;
import org.junit.Test;

/**
 * LRU缓存测试
 *
 * @author Jinty
 * @date 2021/5/26
 **/
public class LRUCacheTest {

    @Test
    public void test() {
        try {
            LRUCache<Integer, Integer> lruCache = new LRUCache<>(3);
            lruCache.put(1, 1);
            lruCache.put(2, 2);
            lruCache.put(3, 3);
            System.out.println(lruCache); // 1,2,3
            lruCache.get(2);
            System.out.println(lruCache); // 1,3,2
            lruCache.get(1);
            System.out.println(lruCache); // 3,2,1
            lruCache.put(4, 4, 1000L);
            System.out.println(lruCache); // 2,1,4
            Thread.sleep(2000L);
            System.out.println(lruCache); // 2,1
            lruCache.put(5, 5, -1L);
            System.out.println(lruCache); // 2,1,5
            Thread.sleep(2000L);
            System.out.println(lruCache); // 2,1,5
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
