package cn.jinty.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * IO流 - 工具类
 *
 * @author Jinty
 * @date 2022/3/21
 **/
public final class IOStreamUtil {

    /**
     * 输入流转为字节数组
     *
     * @param is 输入流
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] inputStreamToBytes(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        return bytes;
    }
    
}
