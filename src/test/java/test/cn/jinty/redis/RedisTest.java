package test.cn.jinty.redis;

import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.Properties;

/**
 * Redis - 测试
 *
 * @author Jinty
 * @date 2023/4/11
 **/
public class RedisTest {

    private Jedis getConnection() {
        Jedis jedis = null;
        try {
            Properties props = FileUtil.parseProperties(new File(
                    FilePathUtil.getAbsolutePath("/properties/application.properties", true)));
            String host = props.getProperty("redis.host");
            int port = Integer.parseInt(props.getProperty("redis.port"));
            jedis = new Jedis(host, port);
            System.out.printf("Connect To Redis %s:%s%n", host, port);
            System.out.println(jedis.ping());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    @Test
    public void testConnect() {
        getConnection();
    }

    @Test
    public void testSetAndGet() {
        Jedis jedis = getConnection();
        String[][] kvArr = {
                {"A", "1"}, {"B", "2"}, {"C", "你好"}
        };
        for (String[] kv : kvArr) {
            System.out.printf("set: key=%s, val=%s, result=%s\n", kv[0], kv[1], jedis.set(kv[0], kv[1]));
            System.out.printf("get: key=%s, result=%s\n", kv[0], jedis.get(kv[0]));
        }
        System.out.printf("keys: result=%s\n", jedis.keys("*"));
    }

}
