package cn.jinty.util.io;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;
import cn.jinty.util.StringUtil;
import cn.jinty.util.collection.CollectionUtil;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.*;

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

    // 文件类型标志
    public static final String FILE_TYPE_MARK = ".";

    /**
     * 获取文件类型
     *
     * @param filePath 文件路径
     * @return 文件类型
     */
    public static String getFileType(String filePath) {
        if (StringUtil.isBlank(filePath)) {
            return StringUtil.EMPTY;
        }
        int index = filePath.lastIndexOf(FILE_TYPE_MARK);
        return filePath.substring(index + 1);
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
        int index2 = filePath.lastIndexOf(FILE_TYPE_MARK);
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
     * 转换文件路径分隔符，使其符合当前系统
     *
     * @param filePath 文件路径
     * @return 文件路径
     */
    public static String convertSeparator(String filePath) {
        if (StringUtil.isBlank(filePath)) {
            return filePath;
        }
        // Windows系统的文件分隔符为 \
        filePath = filePath.replace("\\", File.separator);
        // Linux系统的文件分隔符为 /
        filePath = filePath.replace("/", File.separator);
        return filePath;
    }

    /**
     * 使用文件路径分隔符，将各部分连接起来
     * (在连接点处，两个连续的分隔符仅保留一个)
     *
     * @param filePaths 各部分路径
     * @return 文件路径
     */
    public static String concatBySeparator(String... filePaths) {
        if (filePaths == null || filePaths.length == 0) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (String filePath : filePaths) {
            if (sb.length() == 0) {
                sb.append(filePath);
            } else {
                if (sb.charAt(sb.length() - 1) != File.separatorChar) {
                    sb.append(File.separatorChar);
                }
                if (filePath.startsWith(File.separator)) {
                    sb.append(filePath.substring(1));
                } else {
                    sb.append(filePath);
                }
            }
        }
        return sb.toString();
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
    public static long getSize(File file) throws IOException {
        if (!existFile(file)) {
            return 0L;
        }
        try (InputStream is = new FileInputStream(file)) {
            return IOUtil.getSize(is);
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
        long size = getSize(file);
        BigDecimal targetNum = BinaryUnitEnum.transferUnit(BigDecimal.valueOf(size), BinaryUnitEnum.B, unit);
        return targetNum + unit.getCode();
    }

    /**
     * 获取文件大小，自动选择合适的单位
     *
     * @param file 文件
     * @return 文件大小(带单位且保留两位小数)
     * @throws IOException IO异常
     */
    public static String getSizeWithUnit(File file) throws IOException {
        long size = getSize(file);
        for (BinaryUnitEnum unit : Arrays.asList(B, KB, MB, GB, TB)) {
            if (size < unit.getBytes().intValue()) {
                BinaryUnitEnum targetUnit = unit.getLast() != null ? unit.getLast() : unit;
                BigDecimal targetNum = BinaryUnitEnum.transferUnit(BigDecimal.valueOf(size), BinaryUnitEnum.B, targetUnit);
                return targetNum + targetUnit.getCode();
            }
        }
        return "";
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
    public static File createFile(String filePath) throws IOException {
        if (StringUtil.isBlank(filePath)) {
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
                return null;
            }
        }
        // 创建文件
        if (!file.createNewFile()) {
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
    public static File createFolder(String folderPath) {
        if (StringUtil.isBlank(folderPath)) {
            return null;
        }
        File folder = new File(folderPath);
        if (folder.exists()) {
            return folder;
        }
        if (!folder.mkdirs()) {
            return null;
        }
        return folder;
    }

    /**
     * 删除文件
     * (如果是目录，那么只有空目录可以删除成功)
     *
     * @param filePath 文件路径
     * @return 是否成功删除(不存在的文件视为成功删除)
     */
    public static boolean deleteFile(String filePath) {
        return deleteFiles(Collections.singletonList(filePath));
    }

    /**
     * 删除文件
     * (如果是目录，那么只有空目录可以删除成功)
     *
     * @param filePaths 多个文件路径
     * @return 是否成功删除所有文件(不存在的文件视为成功删除)
     */
    public static boolean deleteFiles(List<String> filePaths) {
        if (CollectionUtil.isEmpty(filePaths)) {
            return true;
        }
        boolean flag = true;
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                flag &= file.delete();
            }
        }
        return flag;
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
            return properties;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return properties;
        }
        try (InputStream is = new FileInputStream(file)) {
            properties.load(is);
            return properties;
        }
    }

    /**
     * 压缩文件
     *
     * @param filePath    待压缩的文件路径
     * @param zipFilePath 压缩包的文件路径
     * @throws IOException IO异常
     */
    public static void zip(String filePath, String zipFilePath) throws IOException {
        zip(Collections.singletonList(filePath), zipFilePath);
    }

    /**
     * 压缩文件
     *
     * @param filePaths   多个待压缩的文件路径
     * @param zipFilePath 压缩包的文件路径
     * @throws IOException IO异常
     */
    public static void zip(List<String> filePaths, String zipFilePath) throws IOException {
        if (CollectionUtil.isEmpty(filePaths) || StringUtil.isBlank(zipFilePath)) {
            return;
        }
        File zipFile = new File(zipFilePath);
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());
             ZipOutputStream zos = new ZipOutputStream(cos)) {
            for (String filePath : filePaths) {
                File file = new File(filePath);
                zip(file, zos, "");
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param file    待压缩的文件
     * @param zos     压缩输出流
     * @param baseDir 文件目录(用于保证压缩后内部目录结构不改变)
     * @throws IOException IO异常
     */
    public static void zip(File file, ZipOutputStream zos, String baseDir) throws IOException {
        if (file == null || zos == null) {
            return;
        }
        if (!file.exists()) {
            return;
        }
        if (baseDir == null) {
            baseDir = "";
        } else if (!baseDir.endsWith(File.separator)) {
            baseDir = baseDir + File.separator;
        }
        // 如果是目录
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            // 目录下为空
            if (subFiles == null || subFiles.length == 0) {
                // 根据ZipEntry.isDirectory方法可知这里用"/"结尾可以表示压缩对象是一个目录
                ZipEntry entry = new ZipEntry(baseDir + file.getName() + "/");
                zos.putNextEntry(entry);
                return;
            }
            // 目录下非空
            for (File subFile : subFiles) {
                zip(subFile, zos, baseDir + file.getName() + File.separator);
            }
            return;
        }
        // 如果是文件
        ZipEntry entry = new ZipEntry(baseDir + file.getName());
        zos.putNextEntry(entry);
        try (FileInputStream fis = new FileInputStream(file)) {
            IOUtil.inputStreamToOutputStream(fis, zos);
        }
    }

    /**
     * 解压文件
     *
     * @param zipFilePath 压缩包的文件路径
     * @param destDir     解压后的目录路径
     * @throws IOException IO异常
     */
    public static void unzip(String zipFilePath, String destDir) throws IOException {
        if (StringUtil.isBlank(zipFilePath) || StringUtil.isBlank(destDir)) {
            return;
        }
        File checkZipFile = new File(zipFilePath);
        if (!checkZipFile.exists()) {
            return;
        }
        if (!destDir.endsWith(File.separator)) {
            destDir = destDir + File.separator;
        }
        // 开始解压
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<?> enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            // 解压对象
            ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
            String destPath = destDir + zipEntry.getName();
            // 如果是目录
            if (zipEntry.isDirectory()) {
                createFolder(destPath);
                continue;
            }
            // 如果是文件
            File file = createFile(destPath);
            assert file != null;
            try (InputStream is = zipFile.getInputStream(zipEntry);
                 CheckedInputStream cis = new CheckedInputStream(is, new CRC32());
                 FileOutputStream fos = new FileOutputStream(file)) {
                IOUtil.inputStreamToOutputStream(cis, fos);
            }
        }
    }

    /**
     * 将文件读取为字符串 (UTF-8)
     *
     * @param filePath 文件路径
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String read(String filePath) throws IOException {
        if (StringUtil.isBlank(filePath)) {
            return StringUtil.EMPTY;
        }
        File file = new File(filePath);
        if (!existFile(file)) {
            return StringUtil.EMPTY;
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            return new String(IOUtil.getBytes(fis));
        }
    }

    /**
     * 将字符串写入文件中 (UTF-8)
     *
     * @param content  字符串
     * @param filePath 文件路径
     * @throws IOException IO异常
     */
    public static void write(String content, String filePath) throws IOException {
        if (StringUtil.isBlank(content) || StringUtil.isBlank(filePath)) {
            return;
        }
        File file = createFile(filePath);
        if (file == null) {
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes());
        }
    }

}
