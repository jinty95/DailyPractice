package test.cn.jinty.annotation.cache;

import cn.jinty.annotation.parser.CacheParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务 - 代理
 *
 * @author Jinty
 * @date 2022/5/26
 **/
public class ServiceProxy implements InvocationHandler {

    private final Service service;

    private final CacheParser cacheParser;

    ServiceProxy(Service service, CacheParser cacheParser) {
        this.service = service;
        this.cacheParser = cacheParser;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.cacheParser.invoke(service, method, args);
    }

}
