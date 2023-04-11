package test.cn.jinty.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Redis - 测试
 *
 * @author Jinty
 * @date 2023/4/11
 **/
public class RedisTest {

    private Jedis getConnection() {
        String host = "127.0.0.1";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        System.out.printf("%s:%s\n", host, port);
        System.out.println(jedis.ping());
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
                {"A", "1"}, {"B", "2"}, {"C", "3"}
        };
        for (String[] kv : kvArr) {
            System.out.printf("set: key=%s, val=%s, result=%s\n", kv[0], kv[1], jedis.set(kv[0], kv[1]));
            System.out.printf("get: key=%s, result=%s\n", kv[0], jedis.get(kv[0]));
        }
        System.out.printf("keys: result=%s\n", jedis.keys("*"));
    }

}
