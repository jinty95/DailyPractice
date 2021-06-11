package cn.jinty.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类
 *
 * @author jinty
 * @date 2019/12/5.
 */
public final class EncryptUtil {

    //AES算法：参数分别代表 算法名称/加密模式/数据填充方式
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    //AES加密器
    private static Cipher cipher = null;
    static {
        try {
            cipher = Cipher.getInstance(AES_ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * AES秘钥 - 随机生成
     *
     * @return 秘钥字符串
     */
    public static byte[] generateAesKey(){
        try{
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.指定密钥为128比特
            keygen.init(128);
            //3.随机生成密钥
            SecretKey originalKey=keygen.generateKey();
            //4.获得密钥的字节数组
            return originalKey.getEncoded();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES加密
     *
     * @param plaintext 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception 异常
     */
    public static String aesEncrypt(String plaintext, String key) throws Exception {
        //初始化
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        //加密
        byte[] b = cipher.doFinal(plaintext.getBytes());
        //采用base64进行转码,避免乱码
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * AES解密
     *
     * @param ciphertext 密文
     * @param key 密钥
     * @return 明文
     * @throws Exception 异常
     */
    public static String aesDecrypt(String ciphertext, String key) throws Exception {
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        //采用base64进行转码,避免乱码
        byte[] encryptBytes = Base64.getDecoder().decode(ciphertext);
        //解码
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 异或加密
     *
     * @param plaintext 明文
     * @param key 数字密钥
     * @return 密文
     */
    public static String xorEncrypt(String plaintext, int key){
        byte[] textByte = plaintext.getBytes();
        for(int i=0;i<textByte.length;i++){
            textByte[i] ^= key;
        }
        return Base64.getEncoder().encodeToString(textByte);
    }

    /**
     * 异或解密
     *
     * @param ciphertext 密文
     * @param key 数字密钥
     * @return 明文
     */
    public static String xorDecrypt(String ciphertext, int key){
        byte[] textByte = Base64.getDecoder().decode(ciphertext);
        for(int i=0;i<textByte.length;i++){
            textByte[i] ^= key;
        }
        return new String(textByte);
    }

    /**
     * MD5加密
     *
     * @param s 输入字符串
     * @return MD5加密字节数组
     */
    public static byte[] md5(String s) {
        try {
            // 获得MD5摘要算法对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(s.getBytes());
            // 获得密文
            return mdInst.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5加密
     *
     * @param s 输入字符串
     * @return MD5加密串
     */
    public static String md5Str(String s) {
        // 获得密文
        byte[] md = md5(s);
        // 把密文转换成十六进制的字符串形式
        return StringUtil.byteToHexString(md);
    }

    /**
     * SHA1加密
     *
     * @param s 输入字符串
     * @return SHA1加密字节数组
     */
    public static byte[] sha1(String s){
        try{
            // 获得SHA1摘要算法对象
            MessageDigest mdInst = MessageDigest.getInstance("SHA1");
            // 使用指定的字节更新摘要
            mdInst.update(s.getBytes());
            // 获得密文
            return mdInst.digest();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * SHA1加密
     *
     * @param s 输入字符串
     * @return SHA1加密串
     */
    public static String sha1Str(String s){
        // 获得密文
        byte[] md = sha1(s);
        // 把密文转换成十六进制的字符串形式
        return StringUtil.byteToHexString(md);
    }

}
