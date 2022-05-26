package test.cn.jinty.annotation.cache;

import cn.jinty.annotation.parser.LocalCacheParser;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 注解 - 缓存 - 测试
 *
 * @author Jinty
 * @date 2022/5/26
 **/
public class CacheTest {

    @Test
    public void test() {

        Service service = (Service) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Service.class},
                new ServiceProxy(new ServiceImpl(), new LocalCacheParser())
        );

        System.out.println(service.listByType("BOOK"));
        System.out.println(service.listByType("BOOK"));
        System.out.println(service.listByType("FOOD"));
        System.out.println(service.listByType("FOOD"));
        try {
            Thread.sleep(2100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.listByType("BOOK"));
        System.out.println(service.listByType("BOOK"));

        System.out.println();
        System.out.println(service.getById(1L));
        System.out.println(service.getById(1L));
        System.out.println(service.getById(1L));
        System.out.println(service.getById(2L));
        System.out.println(service.getById(2L));

    }

}
