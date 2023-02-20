package cn.jinty.util.io;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * IO流 - 工具类
 *
 * @author Jinty
 * @date 2022/3/21
 **/
public final class IOUtil {

    private IOUtil() {
    }

    /**
     * 输入流转为字节数组
     *
     * @param is 输入流
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] getBytes(InputStream is) throws IOException {
        if (is == null) {
            return new byte[0];
        }
        /*// 由于available()返回的是一个估计值，可能导致读取数据不完整
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        return bytes;*/
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            inputStreamToOutputStream(is, os);
            return os.toByteArray();
        }
    }

    /**
     * 从输入流读取，写入到输出流
     *
     * @param is 输入流
     * @param os 输出流
     * @throws IOException IO异常
     */
    public static void inputStreamToOutputStream(InputStream is, OutputStream os) throws IOException {
        if (is == null || os == null) {
            return;
        }
        // 每次都从输入流读取1024个字节，然后写入输出流
        // 这样只需要很少容量的内存，就可以完成大数据量的转移
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
    }

    /**
     * 输入流的字节数量
     * (如果只是想要知道输入流的数据大小，那么不要将其转为byte[]再获取length，因为比较耗内存)
     *
     * @param is 输入流
     * @return 字节数量
     * @throws IOException IO异常
     */
    public static long getSize(InputStream is) throws IOException {
        long count = 0L;
        if (is == null) {
            return count;
        }
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1) {
            count += len;
        }
        return count;
    }

    /**
     * 使用GZIP压缩字节数组
     *
     * @param bytes 原始字节数组
     * @return 压缩字节数组
     * @throws IOException IO异常
     */
    public static byte[] zip(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return bytes;
        }
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             GZIPOutputStream gos = new GZIPOutputStream(os)) {
            gos.write(bytes);
            // 这里必须先close，否则os中数据不完整，在解压时会出现异常 java.io.EOFException: Unexpected end of ZLIB input stream
            gos.close();
            return os.toByteArray();
        }
    }

    /**
     * 使用GZIP解压字节数组
     *
     * @param bytes 压缩字节数组
     * @return 原始字节数组
     * @throws IOException IO异常
     */
    public static byte[] unzip(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return bytes;
        }
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
             GZIPInputStream gis = new GZIPInputStream(is)) {
            return getBytes(gis);
        }
    }

}
