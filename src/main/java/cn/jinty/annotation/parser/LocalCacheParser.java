package cn.jinty.annotation.parser;

import cn.jinty.annotation.Cache;
import cn.jinty.struct.LRUCache;

import java.lang.reflect.Method;

/**
 * 注解 - 本地缓存 - 解析器
 *
 * @author Jinty
 * @date 2022/5/26
 **/
public class LocalCacheParser implements CacheParser {

    private static final LRUCache<String, Object> lruCache = new LRUCache<>(10000);

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Exception {
        // 1、无注解，直接执行
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(obj, args);
        }
        // 2、有注解，增加注解逻辑
        Cache cache = method.getAnnotation(Cache.class);
        StringBuilder sb = new StringBuilder(cache.prefix());
        if (args != null && args.length > 0) {
            sb.append("#");
            for (Object arg : args) {
                sb.append(arg).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        String key = sb.toString();
        // 命中缓存，直接返回
        if (lruCache.containsKey(key)) {
            return lruCache.get(key);
        }
        // 未命中缓存，执行方法，结果存入缓存，然后返回
        Object value = method.invoke(obj, args);
        lruCache.put(key, value, cache.expireTime());
        return value;
    }

}
