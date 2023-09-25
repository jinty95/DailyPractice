package cn.jinty.util.security;

import cn.jinty.util.string.HexStringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 信息摘要 - 工具类
 *
 * @author Jinty
 * @date 2023/1/12
 */
public final class MessageDigestUtil {

    private MessageDigestUtil() {
    }

    // 摘要算法
    public enum ALGORITHM {
        MD5, SHA1
    }

    /**
     * 生成MD5摘要字符串
     *
     * @param s 内容(字符串)
     * @return 摘要(十六进制字符串)
     */
    public static String md5Str(String s) {
        // 字节流转换成十六进制的字符串形式
        return HexStringUtil.bytesToHex(md(s.getBytes(), ALGORITHM.MD5), false);
    }

    /**
     * 生成MD5摘要字符串
     *
     * @param is 内容(输入流)
     * @return 摘要(十六进制字符串)
     */
    public static String md5Str(InputStream is) {
        // 字节流转换成十六进制的字符串形式
        return HexStringUtil.bytesToHex(md(is, ALGORITHM.MD5), false);
    }

    /**
     * 生成MD5摘要字符串
     *
     * @param file 内容(文件)
     * @return 摘要(十六进制字符串)
     */
    public static String md5Str(File file) {
        // 字节流转换成十六进制的字符串形式
        return HexStringUtil.bytesToHex(md(file, ALGORITHM.MD5), false);
    }

    /**
     * 生成SHA1摘要字符串
     *
     * @param s 内容(字符串)
     * @return 摘要(十六进制字符串)
     */
    public static String sha1Str(String s) {
        // 字节流转换成十六进制的字符串形式
        return HexStringUtil.bytesToHex(md(s.getBytes(), ALGORITHM.SHA1), false);
    }

    /**
     * 生成SHA1摘要字符串
     *
     * @param is 内容(输入流)
     * @return 摘要(十六进制字符串)
     */
    public static String sha1Str(InputStream is) {
        // 字节流转换成十六进制的字符串形式
        return HexStringUtil.bytesToHex(md(is, ALGORITHM.SHA1), false);
    }

    /**
     * 生成SHA1摘要字符串
     *
     * @param file 内容(文件)
     * @return 摘要(十六进制字符串)
     */
    public static String sha1Str(File file) {
        // 字节流转换成十六进制的字符串形式
        return HexStringUtil.bytesToHex(md(file, ALGORITHM.SHA1), false);
    }

    /* 以下为内部函数 */

    /**
     * 生成摘要
     *
     * @param bytes     内容(字节流)
     * @param algorithm 摘要算法
     * @return 摘要(字节流)
     */
    private static byte[] md(byte[] bytes, ALGORITHM algorithm) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance(algorithm.name());
            mdInst.update(bytes);
            return mdInst.digest();
        } catch (Exception e) {
            System.out.printf("生成摘要异常：bytes=%s, algorithm=%s%n", Arrays.toString(bytes), algorithm);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成摘要
     *
     * @param is        内容(输入流)
     * @param algorithm 摘要算法
     * @return 摘要(字节流)
     */
    private static byte[] md(InputStream is, ALGORITHM algorithm) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance(algorithm.name());
            DigestInputStream dis = new DigestInputStream(is, mdInst);
            byte[] buf = new byte[1024];
            while (dis.read(buf) != -1) ;
            return dis.getMessageDigest().digest();
        } catch (Exception e) {
            System.out.printf("生成摘要异常：algorithm=%s%n", algorithm);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成摘要
     *
     * @param file      内容(文件)
     * @param algorithm 摘要算法
     * @return 摘要(字节流)
     */
    private static byte[] md(File file, ALGORITHM algorithm) {
        try {
            return md(new FileInputStream(file), algorithm);
        } catch (Exception e) {
            System.out.printf("生成摘要异常：file=%s, algorithm=%s%n", file.getAbsolutePath(), algorithm);
            e.printStackTrace();
            return null;
        }
    }

}
