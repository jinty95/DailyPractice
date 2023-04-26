package cn.jinty.util.io;

import cn.jinty.util.StringUtil;

import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;

/**
 * 文件路径 - 工具类
 *
 * @author Jinty
 * @date 2023/4/14
 **/
public final class FilePathUtil {

    private FilePathUtil() {
    }

    /**
     * 根据"类文件路径+基础包名"获取类全限定名
     *
     * @param classFilePath 类文件路径
     * @param basePackage   基础包名
     * @return 类全限定名
     */
    public static String getClassName(String classFilePath, String basePackage) {
        if (StringUtil.isBlank(classFilePath) || StringUtil.isBlank(basePackage)) {
            return StringUtil.EMPTY;
        }
        classFilePath = convertSeparator(classFilePath).replace(File.separator, ".");
        int index1 = classFilePath.indexOf(basePackage);
        int index2 = classFilePath.lastIndexOf(".class");
        if (index1 == -1 || index2 == -1) {
            return StringUtil.EMPTY;
        }
        return classFilePath.substring(index1, index2);
    }

    /**
     * 获取文件名称 (不包含文件后缀)
     *
     * @param filePath 文件路径
     * @return 文件名称
     */
    public static String getFileName(String filePath) {
        if (StringUtil.isBlank(filePath)) {
            return StringUtil.EMPTY;
        }
        filePath = convertSeparator(filePath);
        int index1 = filePath.lastIndexOf(File.separator);
        int index2 = filePath.lastIndexOf('.');
        return filePath.substring(index1 + 1, index2);
    }

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
        int index = filePath.lastIndexOf('.');
        return filePath.substring(index + 1);
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
        // 直接用File.separator，如果值为'\'，会抛出IllegalArgumentException：character to be escaped is missing
        String replacement = Matcher.quoteReplacement(File.separator);
        // Windows系统的文件分隔符为'\'
        // Linux系统的文件分隔符为'/'
        return filePath.replaceAll("[\\\\/]+", replacement);
    }

    /**
     * 使用文件路径分隔符，将各部分连接起来
     *
     * @param filePaths 各部分路径
     * @return 文件路径
     */
    public static String concatBySeparator(String... filePaths) {
        if (filePaths == null || filePaths.length == 0) {
            return StringUtil.EMPTY;
        }
        String filePath = StringUtil.join(File.separator, (Object[]) filePaths);
        return convertSeparator(filePath);
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
        filePath = convertSeparator(filePath);
        int index1 = filePath.lastIndexOf(File.separator);
        int index2 = filePath.lastIndexOf('.');
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
     * 根据相对路径获取绝对路径
     * 1、当path以"/"开头，那么在classpath(即/target/classes)下寻找资源
     * 2、当path不以"/"开头，那么在FilePathUtil.class所在的目录下寻找资源
     *
     * @param path       路径
     * @param isRelative 是否相对
     * @return 绝对路径
     */
    public static String getAbsolutePath(String path, boolean isRelative) {
        return getAbsolutePath(path, isRelative, FilePathUtil.class);
    }

    /**
     * 根据相对路径获取绝对路径
     * 1、当path以"/"开头，那么在classpath(即/target/classes)下寻找资源
     * 2、当path不以"/"开头，那么在relativeClass所在的目录下寻找资源
     *
     * @param path          路径
     * @param isRelative    是否相对
     * @param relativeClass 相对的类
     * @return 绝对路径
     */
    public static String getAbsolutePath(String path, boolean isRelative, Class<?> relativeClass) {
        if (StringUtil.isBlank(path)) {
            return StringUtil.EMPTY;
        }
        if (!isRelative) {
            return path;
        }
        if (relativeClass == null) {
            relativeClass = FilePathUtil.class;
        }
        URL url = relativeClass.getResource(path);
        if (url == null) {
            return StringUtil.EMPTY;
        }
        return url.getPath();
    }

}
