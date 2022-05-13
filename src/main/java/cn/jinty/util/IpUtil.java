package cn.jinty.util;

import java.util.regex.Pattern;

/**
 * IP地址 - 工具类
 *
 * @author Jinty
 * @date 2021/5/14
 */
public final class IpUtil {

    // IP地址格式
    private static final Pattern PATTERN = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");

    /**
     * 判断是否符合IP地址格式
     *
     * @param ip IP字符串
     * @return 是否
     */
    private static boolean isIpFormat(String ip) {
        return PATTERN.matcher(ip).matches();
    }

    /**
     * IP地址转为int型整数
     * 由于IP地址由4个字节组成，故可以用32位的int来表示
     *
     * @param ip IP字符串
     * @return 整数
     */
    public static int ip2int(String ip) {
        if (!isIpFormat(ip)) {
            throw new IllegalArgumentException(ip + "不是合法的IP地址");
        }
        String[] arr = ip.split("\\.");
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = Integer.parseInt(arr[i]);
            if (j > 255) {
                throw new IllegalArgumentException(ip + "不是合法的IP地址");
            }
            result |= j << (arr.length - 1 - i) * 8;
        }
        return result;
    }

    /**
     * int型整数转为IP地址
     *
     * @param num 整数
     * @return IP字符串
     */
    public static String int2ip(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            int j = (num >>> i * 8) & 255;
            sb.append(j);
            if (i != 0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

}
