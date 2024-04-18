package test.cn.jinty.java.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网址 - 测试
 *
 * @author Jinty
 * @date 2024/4/18
 */
public class InetAddressTest {

    @Test
    public void test1() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("本机IP：" + address.getHostAddress() + "，主机名：" + address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
