package cn.jinty.util.security;

import cn.jinty.util.MathUtil;

import java.util.Base64;

/**
 * 自定义加密 - 工具类
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public final class CustomEncryptUtil {

    private CustomEncryptUtil() {
    }

    /**
     * 异或加密
     *
     * @param content 原文
     * @param key     数字密钥
     * @return 密文
     */
    public static String xorEncrypt(String content, int key) {
        byte[] textBytes = content.getBytes();
        byte[] keyBytes = MathUtil.intToFourByte(key);
        for (int i = 0; i < textBytes.length; i++) {
            for (byte keyByte : keyBytes) {
                textBytes[i] = (byte) (textBytes[i] ^ keyByte);
            }
        }
        return Base64.getEncoder().encodeToString(textBytes);
    }

    /**
     * 异或解密
     *
     * @param content 密文
     * @param key     数字密钥
     * @return 原文
     */
    public static String xorDecrypt(String content, int key) {
        byte[] textBytes = Base64.getDecoder().decode(content);
        byte[] keyBytes = MathUtil.intToFourByte(key);
        for (int i = 0; i < textBytes.length; i++) {
            for (byte keyByte : keyBytes) {
                textBytes[i] = (byte) (textBytes[i] ^ keyByte);
            }
        }
        return new String(textBytes);
    }

}
