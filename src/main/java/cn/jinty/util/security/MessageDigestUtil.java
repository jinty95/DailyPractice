package cn.jinty.util.security;

import cn.jinty.util.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 信息摘要 - 工具类
 *
 * @author Jinty
 * @date 2023/1/12
 */
public final class MessageDigestUtil {

    private MessageDigestUtil() {
    }

    /**
     * 生成MD5摘要
     *
     * @param bytes 原内容(字节流)
     * @return 摘要(字节流)
     * @throws Exception 异常
     */
    public static byte[] md5(byte[] bytes) throws Exception {
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(bytes);
        return mdInst.digest();
    }

    /**
     * 生成MD5摘要
     *
     * @param s 原内容(字符串)
     * @return 摘要(字节流)
     * @throws Exception 异常
     */
    public static byte[] md5(String s) throws Exception {
        return s == null ? new byte[0] : md5(s.getBytes());
    }

    /**
     * 生成MD5摘要字符串
     *
     * @param s 原内容(字符串)
     * @return 摘要(十六进制字符串)
     * @throws Exception 异常
     */
    public static String md5Str(String s) throws Exception {
        // 字节流转换成十六进制的字符串形式
        return StringUtil.bytesToHex(md5(s));
    }

    /**
     * 生成SHA1摘要
     *
     * @param bytes 原内容(字节流)
     * @return 摘要(字节流)
     * @throws Exception 异常
     */
    public static byte[] sha1(byte[] bytes) throws Exception {
        MessageDigest mdInst = MessageDigest.getInstance("SHA1");
        mdInst.update(bytes);
        return mdInst.digest();
    }

    /**
     * 生成SHA1摘要
     *
     * @param s 原内容(字符串)
     * @return 摘要(字节流)
     * @throws Exception 异常
     */
    public static byte[] sha1(String s) throws Exception {
        return s == null ? new byte[0] : sha1(s.getBytes());
    }

    /**
     * 生成SHA1摘要字符串
     *
     * @param s 原内容(字符串)
     * @return 摘要(十六进制字符串)
     * @throws Exception 异常
     */
    public static String sha1Str(String s) throws Exception {
        // 字节流转换成十六进制的字符串形式
        return StringUtil.bytesToHex(sha1(s));
    }

}
