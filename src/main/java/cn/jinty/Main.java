package cn.jinty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 * 主函数
 *
 * @author Jinty
 * @date 2020/11/24
 **/
public class Main {

    public static void main(String[] args) {

        System.out.println("当前时间戳：" + System.currentTimeMillis());

        Locale locale = Locale.getDefault();
        System.out.println("本机语言及国家：" + locale.getLanguage() + "_" + locale.getCountry());

        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("本机IP：" + address.getHostAddress() + "，主机名：" + address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println();
        String[] arr = {"abc"};
        for (String a : arr) {
            System.out.printf("字符串=%s, 大写=%s, 小写=%s, 长度=%s%n", a, a.toUpperCase(), a.toLowerCase(), a.length());
        }

    }

}