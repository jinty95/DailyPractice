package test.cn.jinty.config;

import cn.jinty.config.ConfigService;
import org.junit.Test;

import java.util.Arrays;

/**
 * 配置 - Service - 测试
 *
 * @author Jinty
 * @date 2023/1/17
 **/
public class ConfigServiceTest {

    private final ConfigService configService = new ConfigServiceImpl();

    @Test
    public void test() {
        String type = "aaa";
        String key = "bbb";
        System.out.println(configService.get(type, key));
        System.out.println(configService.getValue(type, key));
        System.out.println(configService.getValueOrDefault(type, key, "aaa"));
        System.out.println(configService.list(type));
        System.out.println(configService.listKey(type));
        System.out.println(configService.listKeyOrDefault(type, Arrays.asList("aaa", "bbb")));
    }

}
