package test.cn.jinty.util.net;

import cn.jinty.util.string.StringUtil;
import cn.jinty.util.net.IpUtil;
import org.junit.Test;

/**
 * IP工具类 - 测试
 *
 * @author Jinty
 * @date 2021/5/15
 */
public class IpUtilTest {

    @Test
    public void testIsIp() {
        System.out.println(IpUtil.getRegex());
        System.out.println(IpUtil.isIp("0.0.0.0"));
        System.out.println(IpUtil.isIp("10.0.0.0"));
        System.out.println(IpUtil.isIp("101.0.0.0"));
        System.out.println(IpUtil.isIp("192.168.0.1"));
        System.out.println(IpUtil.isIp("255.255.255.255"));
        System.out.println(IpUtil.isIp("256.255.0.1"));
        System.out.println(IpUtil.isIp("999.666.555.333"));
        System.out.println(IpUtil.isIp("20220902"));
        System.out.println(IpUtil.isIp(StringUtil.repeat("20220902", 1000)));
    }

    @Test
    public void testIp2int() {
        String s1 = "192.168.0.1";
        String s2 = "255.255.255.255";
        System.out.println(IpUtil.ip2int(s1));
        System.out.println(IpUtil.int2ip(IpUtil.ip2int(s1)));
        System.out.println(IpUtil.ip2int(s2));
        System.out.println(IpUtil.int2ip(IpUtil.ip2int(s2)));
    }

}
