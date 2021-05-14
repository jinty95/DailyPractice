package util;

import cn.jinty.util.IpUtil;
import org.junit.Test;

/**
 * IP工具类 - 测试
 *
 * @author jinty
 * @date 2021/5/15
 */
public class IpUtilTest {

    @Test
    public void test(){
        String s1 = "192.168.0.1";
        String s2 = "256.256.0.0";
        System.out.println(IpUtil.ip2int(s1));
        System.out.println(IpUtil.int2ip(IpUtil.ip2int(s1)));
        System.out.println(IpUtil.ip2int(s2));
    }

}
