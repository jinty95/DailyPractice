package cn.jinty.util.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES对称加密 - 工具类
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public final class AesEncryptUtil {

    private AesEncryptUtil() {
    }

    public static final String AES = "AES";
    public static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";
    public static final int KEY_SIZE = 128;

    /**
     * 生成AES密钥
     *
     * @return AES密钥
     */
    public static SecretKey genAesKey() throws Exception {
        // 1、构造密钥生成器，指定为AES算法，不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance(AES);
        // 2、指定密钥为128比特(注意：不能任意指定，大小必须为128比特、192比特、256比特其中之一)
        keygen.init(KEY_SIZE);
        // 3、随机生成密钥
        return keygen.generateKey();
    }

    /**
     * 获取AES密钥(Base64编码)
     *
     * @param secretKey AES密钥对象
     * @return AES密钥(Base64编码)
     */
    public static String getAesKey(SecretKey secretKey) {
        byte[] bytes = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 将Base64编码后的密钥转换成SecretKey对象
     *
     * @param keyStr Base64编码后的密钥
     * @return SecretKey对象
     */
    public static SecretKey parseAesKey(String keyStr) {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(keyBytes, AES);
    }

    /**
     * AES加密
     *
     * @param content 原内容
     * @param keyStr  Base64编码后的密钥
     * @return 加密内容
     * @throws Exception 异常
     */
    public static String aesEncrypt(String content, String keyStr) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, parseAesKey(keyStr));
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
    }

    /**
     * AES解密
     *
     * @param content 加密内容
     * @param keyStr  Base64编码后的密钥
     * @return 原内容
     * @throws Exception 异常
     */
    public static String aesDecrypt(String content, String keyStr) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, parseAesKey(keyStr));
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
    }

}
