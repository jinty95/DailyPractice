package cn.jinty.util;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Base64;

import static cn.jinty.enums.BinaryUnitEnum.*;

/**
 * 文件 - 工具类
 *
 * @author Jinty
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
        if (!file.exists() || !file.isFile()) {
            return "";
        }
        try (InputStream is = new FileInputStream(file)) {
            String prefix = fileType.getPrefixOfBase64DataURL();
            String base64 = Base64.getEncoder().encodeToString(IOStreamUtil.inputStreamToBytes(is));
            return prefix + base64;
        }
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小(单位为Byte)
     * @throws IOException IO异常
     */
    public static int getSize(File file) throws IOException {
        if (!file.exists() || !file.isFile()) {
            return 0;
        }
        try (InputStream is = new FileInputStream(file)) {
            return IOStreamUtil.inputStreamToBytes(is).length;
        }
    }

    /**
     * 获取文件大小，可定制单位
     *
     * @param file 文件
     * @param unit 单位
     * @return 文件大小(带单位且保留两位小数)
     * @throws IOException IO异常
     */
    public static String getSizeWithUnit(File file, BinaryUnitEnum unit) throws IOException {
        if (unit == null) {
            throw new IllegalArgumentException("文件单位不能为空");
        }
        if (unit.getBytes().compareTo(GB.getBytes()) > 0) {
            throw new IllegalArgumentException("文件单位最大支持GB");
        }
        int size = getSize(file);
        return transferUnit(size, unit);
    }

    /**
     * 获取文件大小，自动选择合适的单位
     *
     * @param file 文件
     * @return 文件大小(带单位且保留两位小数)
     * @throws IOException IO异常
     */
    public static String getSizeWithUnit(File file) throws IOException {
        int size = getSize(file);
        for (BinaryUnitEnum unit : Arrays.asList(B, KB, MB, GB, TB)) {
            if (size < unit.getBytes().intValue()) {
                if (B == unit) {
                    return transferUnit(size, unit);
                } else {
                    return transferUnit(size, unit.getLast());
                }
            }
        }
        return "";
    }

    /**
     * 单位换算
     *
     * @param size 字节数
     * @param unit 单位
     * @return 换算结果
     */
    private static String transferUnit(int size, BinaryUnitEnum unit) {
        int scale = B == unit ? 0 : 2;
        BigDecimal bd = BigDecimal.valueOf(size).divide(BigDecimal.valueOf(unit.getBytes().intValue()), scale, RoundingMode.HALF_UP);
        return bd + unit.getCode();
    }

}