package cn.jinty.util.io;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;
import cn.jinty.util.collection.CollectionUtil;
import cn.jinty.util.collection.MapUtil;
import cn.jinty.util.object.ObjectUtil;
import cn.jinty.util.string.StringUtil;

import java.io.*;
import java.math.BigDecimal;
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
     * 是否存在文件或目录
     *
     * @param file 文件或目录
     * @return 是否存在
     */
    public static boolean exists(File file) {
        return file != null && file.exists();
    }

    /**
     * 是否存在文件
     *
     * @param file 文件
     * @return 是否存在
     */
    public static boolean existFile(File file) {
        return exists(file) && file.isFile();
    }

    /**
     * 是否存在目录
     *
     * @param file 目录
     * @return 是否存在
     */
    public static boolean existDir(File file) {
        return exists(file) && file.isDirectory();
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
     * @param file .properties文件
     * @return Properties对象
     * @throws IOException IO异常
     */
    public static Properties parseProperties(File file) throws IOException {
        Properties properties = new Properties();
        if (!existFile(file)) {
            return properties;
        }
        try (InputStream is = new FileInputStream(file)) {
            properties.load(is);
            return properties;
        }
    }

    /**
     * 扫描根路径下面的所有文件 (不包括目录)
     *
     * @param root 根路径
     * @return 所有文件
     */
    public static List<File> scanFilesOfRoot(File root) {
        return scanFilesOfRoot(root, new HashSet<>());
    }

    /**
     * 扫描根路径下面的所有文件 (不包括目录)
     *
     * @param root        根路径
     * @param excludeDirs 排除目录名称 (即不扫描这些目录下面的文件)
     * @return 排除指定目录后剩余的所有文件
     */
    public static List<File> scanFilesOfRoot(File root, Set<String> excludeDirs) {
        if (!exists(root)) {
            return new ArrayList<>();
        }
        List<File> results = new ArrayList<>();
        // 如果是文件，直接添加
        if (root.isFile()) {
            results.add(root);
            return results;
        }
        // 如果是目录，扫描目录下的文件
        if (root.isDirectory() && !excludeDirs.contains(root.getName())) {
            File[] files = root.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    results.addAll(scanFilesOfRoot(file, excludeDirs));
                }
            }
        }
        return results;
    }

    /**
     * 扫描根路径下面的所有目录 (不包括文件)
     *
     * @param root 根路径
     * @return 所有目录
     */
    public static List<File> scanDirsOfRoot(File root) {
        if (!exists(root)) {
            return new ArrayList<>();
        }
        List<File> results = new ArrayList<>();
        // 如果是文件，直接返回
        if (root.isFile()) {
            return results;
        }
        // 如果是目录，直接添加，然后递归扫描下一层
        if (root.isDirectory()) {
            results.add(root);
            File[] files = root.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    results.addAll(scanDirsOfRoot(file));
                }
            }
        }
        return results;
    }

    /**
     * 删除文件
     * (如果是目录，那么只有空目录可以删除成功)
     *
     * @param file 文件
     * @return 是否成功删除
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return false;
        }
        boolean flag = file.delete();
        System.out.printf("删除文件[%s]，结果[%s]\n", file.getAbsolutePath(), flag);
        return flag;
    }

    /**
     * 删除文件
     * (如果是目录，那么只有空目录可以删除成功)
     *
     * @param files 文件列表
     * @return 是否成功删除所有文件
     */
    public static boolean deleteFiles(List<File> files) {
        if (CollectionUtil.isEmpty(files)) {
            return false;
        }
        boolean flag = true;
        for (File file : files) {
            flag &= deleteFile(file);
        }
        return flag;
    }

    /**
     * 在给定目录下，删除所有指定类型的文件
     *
     * @param root     目录
     * @param fileType 文件类型 (不区分大小写)
     * @return 是否成功删除所有指定类型的文件
     */
    public static boolean deleteFiles(File root, String fileType) {
        List<File> files = scanFilesOfRoot(root);
        if (CollectionUtil.isEmpty(files)) {
            return false;
        }
        boolean hasMatch = false;
        boolean flag = true;
        int totalCount = 0;
        int successCount = 0;
        for (File file : files) {
            if (StringUtil.equalsIgnoreCase(fileType, FilePathUtil.getFileType(file.getAbsolutePath()))) {
                hasMatch = true;
                boolean success = deleteFile(file);
                flag &= success;
                totalCount++;
                if (success) {
                    successCount++;
                }
            }
        }
        flag &= hasMatch;
        System.out.printf("在目录[%s]下扫描类型为[%s]的文件，扫描到的文件数为[%s]，成功删除的文件数为[%s]，返回[%s]\n",
                root.getAbsolutePath(), fileType, totalCount, successCount, flag);
        return flag;
    }

    /**
     * 删除整个目录 (包括目录及目录下所有内容)
     * 深度优先搜索，先删除目录下的内容，后删除目录本身
     *
     * @param dir 目录
     * @return 是否成功删除整个目录
     */
    public static boolean deleteDir(File dir) {
        if (!exists(dir)) {
            return false;
        }
        if (dir.isFile()) {
            return deleteFile(dir);
        }
        boolean flag = true;
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                flag &= deleteDir(file);
            }
        }
        flag &= dir.delete();
        System.out.printf("删除目录[%s]，结果[%s]\n", dir.getAbsolutePath(), flag);
        return flag;
    }

    /**
     * 删除所有给定的目录 (包括目录及目录下所有内容)
     *
     * @param dirs 目录列表
     * @return 是否成功删除所有给定的目录
     */
    public static boolean deleteDirs(List<File> dirs) {
        if (CollectionUtil.isEmpty(dirs)) {
            return false;
        }
        boolean flag = true;
        for (File dir : dirs) {
            flag &= deleteDir(dir);
        }
        return flag;
    }

    /**
     * 在给定目录下，删除所有指定名称的整个目录
     *
     * @param root    目录
     * @param dirName 目录名称 (不区分大小写)
     * @return 是否成功删除所有指定名称的整个目录
     */
    public static boolean deleteDirs(File root, String dirName) {
        List<File> dirs = scanDirsOfRoot(root);
        if (CollectionUtil.isEmpty(dirs)) {
            return false;
        }
        boolean hasMatch = false;
        boolean flag = true;
        int totalCount = 0;
        int successCount = 0;
        for (File dir : dirs) {
            if (StringUtil.equalsIgnoreCase(dirName, dir.getName())) {
                hasMatch = true;
                boolean success = deleteDir(dir);
                flag &= success;
                totalCount++;
                if (success) {
                    successCount++;
                }
            }
        }
        flag &= hasMatch;
        System.out.printf("在目录[%s]下扫描名称为[%s]的目录，扫描到的目录数为[%s]，成功删除的目录数为[%s]，返回[%s]\n",
                root.getAbsolutePath(), dirName, totalCount, successCount, flag);
        return flag;
    }

    /**
     * 在磁盘上创建文件
     *
     * @param file 文件对象
     * @return 文件对象
     * @throws IOException IO异常
     */
    public static File createFile(File file) throws IOException {
        // 文件为空直接返回空
        if (file == null) {
            return null;
        }
        // 文件存在
        if (file.exists()) {
            // 如果是目录直接返回空
            if (file.isDirectory()) {
                return null;
            }
            // 如果是文件直接返回自身
            return file;
        }
        // 文件不存在，判断目录是否存在，目录不存在时创建目录
        if (createDir(file.getParentFile()) == null) {
            return null;
        }
        // 保证目录存在后，创建文件
        if (!file.createNewFile()) {
            return null;
        }
        System.out.printf("创建文件[%s]\n", file.getAbsolutePath());
        return file;
    }

    /**
     * 磁盘上创建目录
     *
     * @param dir 目录对象
     * @return 目录对象
     */
    public static File createDir(File dir) {
        // 目录为空直接返回空
        if (dir == null) {
            return null;
        }
        // 目录存在
        if (dir.exists()) {
            // 如果是文件直接返回空
            if (dir.isFile()) {
                return null;
            }
            // 如果是目录直接返回自身
            return dir;
        }
        // 目录不存在时创建目录
        if (!dir.mkdirs()) {
            return null;
        }
        System.out.printf("创建目录[%s]\n", dir.getAbsolutePath());
        return dir;
    }

    /**
     * 压缩文件
     *
     * @param file    待压缩文件
     * @param zipFile 压缩包文件
     * @throws IOException IO异常
     */
    public static void zip(File file, File zipFile) throws IOException {
        zip(Collections.singletonList(file), zipFile);
    }

    /**
     * 压缩文件
     *
     * @param files   多个待压缩文件
     * @param zipFile 压缩包文件
     * @throws IOException IO异常
     */
    public static void zip(List<File> files, File zipFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());
             ZipOutputStream zos = new ZipOutputStream(cos)) {
            for (File file : files) {
                zip(file, zos, "");
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param file    待压缩的文件
     * @param zos     压缩输出流
     * @param baseDir 压缩包内部根目录
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
            IOUtil.copy(fis, zos);
        }
    }

    /**
     * 解压文件
     *
     * @param zipFile 压缩包文件
     * @param destDir 解压后的目录路径
     * @throws IOException IO异常
     */
    public static void unzip(File zipFile, String destDir) throws IOException {
        if (!existFile(zipFile) || StringUtil.isBlank(destDir)) {
            return;
        }
        if (!destDir.endsWith(File.separator)) {
            destDir = destDir + File.separator;
        }
        // 开始解压
        ZipFile unzipFile = new ZipFile(zipFile);
        Enumeration<?> enumeration = unzipFile.entries();
        while (enumeration.hasMoreElements()) {
            // 解压对象
            ZipEntry unzipEntry = (ZipEntry) enumeration.nextElement();
            String destPath = destDir + unzipEntry.getName();
            // 如果是目录
            if (unzipEntry.isDirectory()) {
                createDir(new File(destPath));
                continue;
            }
            // 如果是文件
            File file = createFile(new File(destPath));
            assert file != null;
            try (InputStream is = unzipFile.getInputStream(unzipEntry);
                 CheckedInputStream cis = new CheckedInputStream(is, new CRC32());
                 FileOutputStream fos = new FileOutputStream(file)) {
                IOUtil.copy(cis, fos);
            }
        }
    }

    /**
     * 将文件读取为字符串 (UTF-8)
     *
     * @param file 文件
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String read(File file) throws IOException {
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
     * @param content 字符串
     * @param file    文件
     * @throws IOException IO异常
     */
    public static void write(String content, File file) throws IOException {
        if (StringUtil.isBlank(content)) {
            return;
        }
        file = createFile(file);
        if (file == null) {
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes());
        }
    }

    /**
     * 按行读取文件内容 (每行的内容不包括换行符)
     *
     * @param file 文件
     * @return 字符串列表
     * @throws IOException IO异常
     */
    public static List<String> readLine(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        if (!existFile(file)) {
            return lines;
        }
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        }
    }

    /**
     * 按行写入文件内容 (每行的内容添加一个换行符)
     *
     * @param lines 内容行
     * @param file  文件
     * @throws IOException IO异常
     */
    public static void writeLine(List<String> lines, File file) throws IOException {
        if (CollectionUtil.isEmpty(lines)) {
            return;
        }
        file = createFile(file);
        if (file == null) {
            return;
        }
        String newLine = ObjectUtil.ifNull(System.getProperty("line.separator"), "\n");
        byte[] newLineBytes = newLine.getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (String line : lines) {
                fos.write(line.getBytes());
                fos.write(newLineBytes);
            }
        }
    }

    /**
     * 获取两个文件的差异行
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return 差异行(行号 - > 行内容)
     * @throws IOException IO异常
     */
    public static List<Map<Integer, String>> diffLine(File file1, File file2) throws IOException {
        // 公共行
        List<Map<Integer, String>> commonLines = commonLine(file1, file2);
        // 文件1减去公共行
        List<String> lines1 = readLine(file1);
        Map<Integer, String> commonLines1 = commonLines.get(0);
        Map<Integer, String> diffLines1 = new TreeMap<>();
        for (int i = 0; i < lines1.size(); i++) {
            if (commonLines1.containsKey(i + 1)) {
                continue;
            }
            diffLines1.put(i + 1, lines1.get(i));
        }
        // 文件2减去公共行
        List<String> lines2 = readLine(file2);
        Map<Integer, String> commonLines2 = commonLines.get(1);
        Map<Integer, String> diffLines2 = new TreeMap<>();
        for (int i = 0; i < lines2.size(); i++) {
            if (commonLines2.containsKey(i + 1)) {
                continue;
            }
            diffLines2.put(i + 1, lines2.get(i));
        }
        return Arrays.asList(diffLines1, diffLines2);
    }

    /**
     * 获取两个文件的公共行
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return 公共行(行号 - > 行内容)
     * @throws IOException IO异常
     */
    public static List<Map<Integer, String>> commonLine(File file1, File file2) throws IOException {
        List<String> lines1 = readLine(file1);
        List<String> lines2 = readLine(file2);
        int[][] dp = getDpArrayForCommonLine(lines1, lines2);
        int max = dp[lines1.size()][lines2.size()];
        Map<Integer, String> commonLines1 = new TreeMap<>();
        Map<Integer, String> commonLines2 = new TreeMap<>();
        int idx1 = lines1.size();
        int idx2 = lines2.size();
        while (max > 0) {
            if (idx2 > 0 && dp[idx1][idx2] == dp[idx1][idx2 - 1]) {
                idx2--;
            } else if (idx1 > 0 && dp[idx1][idx2] == dp[idx1 - 1][idx2]) {
                idx1--;
            } else {
                commonLines1.put(idx1, lines1.get(idx1 - 1));
                commonLines2.put(idx2, lines2.get(idx2 - 1));
                max--;
                idx1--;
                idx2--;
            }
        }
        return Arrays.asList(commonLines1, commonLines2);
    }

    /**
     * 获取动态规划数组 (用于求两个文件的公共行)
     *
     * @param lines1 文件1的行
     * @param lines2 文件2的行
     * @return 动态规划数组
     */
    private static int[][] getDpArrayForCommonLine(List<String> lines1, List<String> lines2) {
        // dp[i][j]表示lines1[0...i-1]与lines2[0...j-1]的公共行数量
        int[][] dp = new int[lines1.size() + 1][lines2.size() + 1];
        for (int i = 0; i < lines1.size(); i++) {
            for (int j = 0; j < lines2.size(); j++) {
                if (lines1.get(i).equals(lines2.get(j))) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp;
    }

    /**
     * 去重，移除文件中的重复行
     *
     * @param file 文件
     * @throws IOException IO异常
     */
    public static void removeDuplicateLine(File file) throws IOException {
        List<String> lines = readLine(file);
        List<String> newLines = new ArrayList<>(new HashSet<>(lines));
        System.out.printf("文件[%s]，原数据行数[%s]，去重后数据行数[%s]%n",
                file.getAbsolutePath(), lines.size(), newLines.size());
        writeLine(newLines, file);
    }

    /**
     * 在文件中检索指定内容
     *
     * @param file   文件
     * @param search 检索内容
     * @return 所有包含检索内容的行
     * @throws IOException IO异常
     */
    public static Map<Integer, String> searchInFile(File file, String search) throws IOException {
        Map<Integer, String> result = new HashMap<>();
        // 非文本类型，不进行检索，针对二进制文件的文本内容检索无意义
        if (!isTextFile(file)) {
            return result;
        }
        // 按行读取
        List<String> lines = readLine(file);
        // 逐行匹配
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains(search)) {
                result.put(i + 1, line);
            }
        }
        return result;
    }

    /**
     * 在目录下的所有文件中检索指定内容
     *
     * @param dir    目录
     * @param search 检索内容
     * @return 所有包含检索内容的文件及其行
     * @throws IOException IO异常
     */
    public static Map<String, Map<Integer, String>> searchInDir(File dir, String search) throws IOException {
        return searchInDir(dir, search, new HashSet<>());
    }

    /**
     * 在目录下的所有文件中检索指定内容
     *
     * @param dir         目录
     * @param search      检索内容
     * @param excludeDirs 排除目录名称 (即不扫描这些目录下面的文件)
     * @return 所有包含检索内容的文件及其行
     * @throws IOException IO异常
     */
    public static Map<String, Map<Integer, String>> searchInDir(File dir, String search, Set<String> excludeDirs)
            throws IOException {
        List<File> files = scanFilesOfRoot(dir, excludeDirs);
        Map<String, Map<Integer, String>> result = new HashMap<>();
        for (File file : files) {
            Map<Integer, String> map = searchInFile(file, search);
            if (MapUtil.isNotEmpty(map)) {
                result.put(file.getAbsolutePath(), map);
            }
        }
        return result;
    }

    /**
     * 检测是否为文本文件 (区别于二进制文件)
     * 注意：仅通过文件后缀判断，并且只识别有限的文件类型，不一定准确 (比如一个没有后缀的文本，或者后缀比较少见的文本，识别不出来)
     *
     * @param file 文件
     * @return 是否
     */
    public static boolean isTextFile(File file) {
        if (!existFile(file)) {
            return false;
        }
        // 基于文件后缀名判断
        String fileType = FilePathUtil.getFileType(file.getAbsolutePath());
        return FileTypeEnum.isText(fileType);
    }

}
