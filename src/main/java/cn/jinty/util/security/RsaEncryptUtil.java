package cn.jinty.util.security;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA非对称加密 - 工具类
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public final class RsaEncryptUtil {

    private RsaEncryptUtil() {
    }

    public static final String RSA = "RSA";
    public static final int KEY_SIZE = 2048;

    /**
     * 生成密钥对
     *
     * @return 密钥对
     * @throws Exception 异常
     */
    public static KeyPair genKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取公钥(Base64编码)
     *
     * @param keyPair 密钥对
     * @return 公钥(Base64编码)
     */
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     *
     * @param keyPair 密钥对
     * @return 私钥(Base64编码)
     */
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 将Base64编码后的公钥转换成PublicKey对象
     *
     * @param pubStr Base64编码后的公钥
     * @return PublicKey对象
     * @throws Exception 异常
     */
    public static PublicKey parsePublicKey(String pubStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将Base64编码后的私钥转换成PrivateKey对象
     *
     * @param priStr Base64编码后的私钥
     * @return PrivateKey对象
     * @throws Exception 异常
     */
    public static PrivateKey parsePrivateKey(String priStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 使用公钥加密
     *
     * @param content 原内容
     * @param pubStr  Base64编码后的公钥
     * @return 加密内容
     * @throws Exception 异常
     */
    public static String publicEncrypt(String content, String pubStr) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, parsePublicKey(pubStr));
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
    }

    /**
     * 使用私钥解密
     *
     * @param content 加密内容
     * @param priStr  Base64编码后的私钥
     * @return 原内容
     * @throws Exception 异常
     */
    public static String privateDecrypt(String content, String priStr) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, parsePrivateKey(priStr));
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
    }

    /**
     * 使用私钥加密
     *
     * @param content 原内容
     * @param priStr  Base64编码后的私钥
     * @return 加密内容
     * @throws Exception 异常
     */
    public static String privateEncrypt(String content, String priStr) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, parsePrivateKey(priStr));
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
    }

    /**
     * 使用公钥解密
     *
     * @param content 加密内容
     * @param pubStr  Base64编码后的公钥
     * @return 原内容
     * @throws Exception 异常
     */
    public static String publicDecrypt(String content, String pubStr) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, parsePublicKey(pubStr));
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
    }

}
