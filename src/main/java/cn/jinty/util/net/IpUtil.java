package cn.jinty.util.net;

import java.util.regex.Pattern;

/**
 * IP地址 - 工具类
 *
 * @author Jinty
 * @date 2021/5/14
 */
public final class IpUtil {

    private IpUtil() {
    }

    // 有效数字：0~9, 10~99, 100~199, 200~249, 250~255
    private static final String VALID_NUMBER = "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
    // IP地址正则表达式
    private static final String REGEX = "^" + VALID_NUMBER + "\\." + VALID_NUMBER + "\\." + VALID_NUMBER + "\\." + VALID_NUMBER + "$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * 获取IP地址正则表达式
     *
     * @return 正则表达式
     */
    public static String getRegex() {
        return REGEX;
    }

    /**
     * 判断IP字符串是否为IP地址
     *
     * @param ip IP字符串
     * @return 是否
     */
    public static boolean isIp(String ip) {
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
        if (!isIp(ip)) {
            throw new IllegalArgumentException(ip + "不是合法的IP地址");
        }
        String[] arr = ip.split("\\.");
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = Integer.parseInt(arr[i]);
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
            int j = (num >> i * 8) & 255;
            sb.append(j);
            if (i != 0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

}
