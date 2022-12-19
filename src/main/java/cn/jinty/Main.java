package cn.jinty;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 主函数
 *
 * @author Jinty
 * @date 2020/11/24
 **/
public class Main {

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("本机IP：" + address.getHostAddress() + "，主机名：" + address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String[] arr = {"abc"};
        for (String a : arr) {
            System.out.println("字符串：" + a + "，转大写：" + a.toUpperCase() + "，长度：" + a.length());
        }

    }

}