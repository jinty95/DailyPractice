package struct;

import cn.jinty.struct.LRUCache;
import org.junit.Test;

/**
 * LRU缓存测试
 *
 * @author jinty
 * @date 2021/5/26
 **/
public class LRUCacheTest {

    @Test
    public void test(){
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        System.out.println(lruCache); // 1,2,3
        lruCache.get(2);
        System.out.println(lruCache); // 1,3,2
        lruCache.get(1);
        System.out.println(lruCache); // 3,2,1
        lruCache.put(4,4);
        System.out.println(lruCache); // 2,1,4
    }

}
