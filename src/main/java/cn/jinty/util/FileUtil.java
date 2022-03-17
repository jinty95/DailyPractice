package cn.jinty.util;

import cn.jinty.constant.FileTypeEnum;

import java.io.*;
import java.util.Base64;

/**
 * 文件 - 工具类
 *
 * @author jintai.wang
 * @date 2022/3/17
 **/
public final class FileUtil {

    /**
     * 文件 => Base64DataURL
     *
     * @param file     文件
     * @param fileType 文件类型
     * @return Base64DataURL
     * @throws IOException IO异常
     */
    public static String toBase64DataURL(File file, FileTypeEnum fileType) throws IOException {
        if (!file.exists()) {
            return "";
        }
        if (!file.isFile()) {
            return "";
        }
        FileInputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        return fileType.getPrefixOfBase64DataURL() + Base64.getEncoder().encodeToString(bytes);
    }

}
