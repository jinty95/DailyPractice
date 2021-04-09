package cn.jinty.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类
 *
 * @author jinty
 * @date 2019/12/5.
 */
public class EncryptionUtil {

    /**
     * 随机生成AES秘钥
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
            SecretKey original_key=keygen.generateKey();
            //4.获得密钥的字节数组
            byte [] raw=original_key.getEncoded();
            return raw;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //注意：这里加密跟网站加密得到了不同的结果，可能由于处理过程不同
    //不同点：这里输入是加密规则，根据它生成秘钥，然后用秘钥加密；网站是直接输入秘钥，然后直接用秘钥加密
    //修改：去掉步骤1-4，直接从第五步开始，输入秘钥，直接加密
    //AES加密
    public static String aesEncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
            //11.将字符串返回
            return AES_encode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //AES解密
    public static String aesDecode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            //9.解密
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //异或加密
    public static String xorEncode(int key,String plaintext){
        byte[] textByte = plaintext.getBytes();
        for(int i=0;i<textByte.length;i++){
            textByte[i] ^= key;
        }
        return Base64.getEncoder().encodeToString(textByte);
    }

    //异或解密
    public static String xorDecode(int key,String ciphertext){
        byte[] textByte = Base64.getDecoder().decode(ciphertext);
        for(int i=0;i<textByte.length;i++){
            textByte[i] ^= key;
        }
        return new String(textByte);
    }

    /**
     * 根据输入字符串生成MD5加密字节数组
     *
     * @param s 输入字符串
     * @return MD5加密字节数组
     */
    public final static byte[] md5(String s) {
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
     * 根据输入字符串生成MD5加密字符串
     *
     * @param s 输入字符串
     * @return MD5加密串
     */
    public final static String md5Str(String s) {
        try {
            // 获得MD5摘要算法对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(s.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            return StringUtil.byteToHexString(md);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据输入字符串生成SHA1加密字节数组
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
     * 根据输入字符串生成SHA1加密字符串
     *
     * @param s 输入字符串
     * @return SHA1加密串
     */
    public static String sha1Str(String s){
        try{
            // 获得SHA1摘要算法对象
            MessageDigest mdInst = MessageDigest.getInstance("SHA1");
            // 使用指定的字节更新摘要
            mdInst.update(s.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            return StringUtil.byteToHexString(md);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
