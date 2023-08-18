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
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    // 十六进制字符 -> 十进制数字
    private static final Map<Character, Integer> HEX_TO_DECIMAL;

    static {
        HEX_TO_DECIMAL = new HashMap<>();
        for (int i = 0; i < HEX.length; i++) {
            HEX_TO_DECIMAL.put(HEX[i], i);
        }
    }

    // 十六进制转义字符
    private static final String ESCAPE = "\\x";

    /**
     * 字节数组转为十六进制字符串
     *
     * @param bytes      字节数组
     * @param withEscape 是否带有转义字符
     * @return 十六进制字符串
     */
    public static String bytesToHex(byte[] bytes, boolean withEscape) {
        if (bytes == null || bytes.length == 0) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 是否带上转义字符
            if (withEscape) {
                sb.append(ESCAPE);
            }
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
     * @param hex        十六进制字符串
     * @param withEscape 是否带有转义字符
     * @return 字节数组
     */
    public static byte[] hexToBytes(String hex, boolean withEscape) {
        if (StringUtil.isEmpty(hex)) {
            return new byte[0];
        }
        byte[] bytes = new byte[hex.length() / (withEscape ? 4 : 2)];
        int j = 0;
        for (int i = 0; i < hex.length(); i += 2) {
            // 是否带上转义字符
            if (withEscape) {
                i += 2;
            }
            // 高4位
            int high = HEX_TO_DECIMAL.get(Character.toLowerCase(hex.charAt(i)));
            // 低4位
            int low = HEX_TO_DECIMAL.get(Character.toLowerCase(hex.charAt(i + 1)));
            bytes[j++] = (byte) ((high << 4) | low);
        }
        return bytes;
    }

}
