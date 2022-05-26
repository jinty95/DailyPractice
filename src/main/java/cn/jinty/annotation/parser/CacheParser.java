package cn.jinty.annotation.parser;

import java.lang.reflect.Method;

/**
 * 注解 - 缓存 - 解析器
 *
 * @author Jinty
 * @date 2022/5/25
 **/
public interface CacheParser {

    /**
     * 解析注解，执行方法
     *
     * @param obj    对象
     * @param method 方法
     * @param args   参数
     * @return 结果
     * @throws Exception 异常
     */
    Object invoke(Object obj, Method method, Object[] args) throws Exception;

}
