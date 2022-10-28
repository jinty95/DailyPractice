package cn.jinty.util;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static cn.jinty.enums.BinaryUnitEnum.*;

/**
 * 文件 - 工具类
 *
 * @author Jinty
 * @date 2022/3/17
 **/
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * 是否在硬盘上存在对应文件(不是目录)
     *
     * @param file 文件
     * @return 是否
     */
    public static boolean existFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    /**
     * 文件 -> 字节数组
     *
     * @param file 文件
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] getBytes(File file) throws IOException {
        if (!existFile(file)) {
            return new byte[0];
        }
        try (InputStream is = new FileInputStream(file)) {
            return IOUtil.getBytes(is);
        }
    }

    /**
     * 文件 => Base64DataURL
     *
     * @param file     文件
     * @param fileType 文件类型
     * @return Base64DataURL
     * @throws IOException IO异常
     */
    public static String toBase64DataURL(File file, FileTypeEnum fileType) throws IOException {
        byte[] bytes = getBytes(file);
        if (bytes.length == 0) {
            return "";
        }
        String prefix = String.format("data:%s;base64,", fileType.getMimeType());
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return prefix + base64;
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小(单位为Byte)
     * @throws IOException IO异常
     */
    public static int getSize(File file) throws IOException {
        byte[] bytes = getBytes(file);
        return bytes.length;
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
                return transferUnit(size, unit.getLast() != null ? unit.getLast() : unit);
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

    /**
     * 扫描根路径下面的所有文件
     *
     * @param root 根路径
     * @return 所有文件
     */
    public static List<File> scanFilesOfRoot(File root) {
        if (root == null || !root.exists()) {
            return new ArrayList<>();
        }
        List<File> results = new ArrayList<>();
        // 如果是文件，直接添加
        if (root.isFile()) {
            results.add(root);
            return results;
        }
        // 如果是目录，扫描目录下的文件
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    results.addAll(scanFilesOfRoot(file));
                }
            }
        }
        return results;
    }

    /**
     * 根据绝对路径在磁盘上创建文件
     *
     * @param filePath 绝对路径
     * @return 文件对象
     * @throws IOException IO异常
     */
    @SuppressWarnings("all")
    public static File createFile(String filePath) throws IOException {
        if (StringUtil.isBlank(filePath)) {
            System.out.println(String.format("创建文件失败，路径为空：path=%s", filePath));
            return null;
        }
        // 文件存在直接返回
        File file = new File(filePath);
        if (file.exists()) {
            return file;
        }
        // 文件不存在，判断目录是否存在，不存在时创建目录
        File folder = file.getParentFile();
        if (folder != null && !folder.exists()) {
            if (!folder.mkdirs()) {
                System.out.println(String.format("创建目录失败：path=%s", folder.getAbsolutePath()));
                return null;
            }
        }
        // 创建文件
        if (!file.createNewFile()) {
            System.out.println(String.format("创建文件失败：path=%s", filePath));
            return null;
        }
        return file;
    }

    /**
     * 根据绝对路径在磁盘上创建目录
     *
     * @param folderPath 绝对路径
     * @return 目录对象
     */
    @SuppressWarnings("all")
    public static File createFolder(String folderPath) {
        if (StringUtil.isBlank(folderPath)) {
            System.out.println(String.format("创建目录失败，路径为空：path=%s", folderPath));
            return null;
        }
        File folder = new File(folderPath);
        if (folder.exists()) {
            return folder;
        }
        if (!folder.mkdirs()) {
            System.out.println(String.format("创建目录失败：path=%s", folderPath));
            return null;
        }
        return folder;
    }

    /**
     * 拆分文件路径
     *
     * @param filePath 文件路径
     * @return 文件目录、文件名、后缀(可能无后缀)
     */
    public static String[] splitFilePath(String filePath) {
        String[] arr = new String[3];
        if (StringUtil.isBlank(filePath)) {
            return arr;
        }
        int index1 = filePath.lastIndexOf(File.separator);
        int index2 = filePath.lastIndexOf(".");
        arr[0] = filePath.substring(0, index1);
        if (index2 < index1) {
            arr[1] = filePath.substring(index1 + 1);
        } else {
            arr[1] = filePath.substring(index1 + 1, index2);
            arr[2] = filePath.substring(index2 + 1);
        }
        return arr;
    }

    /**
     * 检查文件是否带有UTF-8对应的BOM
     *
     * @param file 文件
     * @return 是否
     * @throws IOException IO异常
     */
    public static boolean hasUtf8Bom(File file) throws IOException {
        byte[] bytes = getBytes(file);
        return bytes.length >= 3 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF;
    }

    /**
     * 解析.properties文件
     *
     * @param filePath 文件路径
     * @return Properties对象
     * @throws IOException IO异常
     */
    public static Properties parseProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        if (StringUtil.isBlank(filePath)) {
            System.out.println(String.format("解析.properties文件失败，文件路径为空：filePath=%s", filePath));
            return properties;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(String.format("解析.properties文件失败，文件不存在：filePath=%s", filePath));
            return properties;
        }
        try (InputStream is = new FileInputStream(file)) {
            properties.load(is);
            return properties;
        }
    }

}
