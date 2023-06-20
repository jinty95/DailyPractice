package cn.jinty.util.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 十六进制字符串 - 工具类
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class HexStringUtil {

    private HexStringUtil() {
    }

    // 十六进制字符集
    private static final char[] HEX = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * 字节数组转为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 高4位
            sb.append(HEX[(b >>> 4) & 15]);
            // 低4位
            sb.append(HEX[b & 15]);
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转为字节数组
     *
     * @param hex 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexToBytes(String hex) {
        if (StringUtil.isEmpty(hex)) {
            return new byte[0];
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < HEX.length; i++) {
            map.put(HEX[i], i);
        }
        byte[] bytes = new byte[hex.length() / 2];
        int j = 0;
        for (int i = 0; i < hex.length(); i += 2) {
            int high = map.get(hex.charAt(i));
            int low = map.get(hex.charAt(i + 1));
            bytes[j++] = (byte) ((high << 4) | low);
        }
        return bytes;
    }

}
