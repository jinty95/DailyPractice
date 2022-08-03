package cn.jinty.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IO流 - 工具类
 *
 * @author Jinty
 * @date 2022/3/21
 **/
public final class IOUtil {

    /**
     * 输入流转为字节数组
     *
     * @param is 输入流
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] getBytes(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        /*// 由于available()返回的是一个估计值，可能导致读取数据不完整
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        return bytes;*/
        // 循环从输入流读取，写入缓冲区，然后再读取缓存区，写入输出流
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            return os.toByteArray();
        }
    }
    
}
