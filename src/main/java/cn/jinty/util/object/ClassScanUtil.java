package cn.jinty.util.object;

import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类扫描 - 工具类
 *
 * @author Jinty
 * @date 2024/4/20
 */
public final class ClassScanUtil {

    private ClassScanUtil() {
    }

    // 类文件后缀
    private static final String CLASS_FILE_SUFFIX = ".class";
    // URL路径分隔符 (操作系统无关，恒为/)
    private static final char URL_PATH_SEPARATOR = '/';
    // 包路径分隔符
    private static final char PACKAGE_PATH_SEPARATOR = '.';

    /**
     * 扫描包路径下的所有类
     *
     * @param packagePath 包路径
     * @return 类名 -> 类对象
     * @throws Exception 异常
     */
    public static Map<String, Class> scan(String packagePath) throws Exception {
        System.out.printf("扫描指定包路径下的所有类：packagePath=%s%n", packagePath);
        String urlPath = packagePath.replace(PACKAGE_PATH_SEPARATOR, URL_PATH_SEPARATOR);
        URL url = Thread.currentThread().getContextClassLoader().getResource(urlPath);
        if (url == null) {
            System.out.printf("扫描到的类总数为0：total=%s%n", 0);
            return new HashMap<>();
        }
        String protocol = url.getProtocol();
        Map<String, Class> result = new HashMap<>();
        if ("file".equals(protocol)) {
            result = scanFile(url, packagePath);
        } else if ("jar".equals(protocol)) {
            result = scanJar(url, packagePath);
        }
        System.out.printf("扫描到的类总数：total=%s%n", result.size());
        result.forEach((key, value) -> System.out.printf("扫描到的类：%s -> %s%n", key, value));
        return result;
    }

    /**
     * 在系统中扫描包路径下的所有类
     *
     * @param url         包资源路径
     * @param packagePath 包路径
     * @return 类名 -> 类对象
     * @throws Exception 异常
     */
    private static Map<String, Class> scanFile(URL url, String packagePath) throws Exception {
        Map<String, Class> result = new HashMap<>();
        List<File> entries = FileUtil.scanFilesOfRoot(new File(url.getPath()));
        for (File file : entries) {
            String filePath = file.getPath();
            if (filePath.endsWith(CLASS_FILE_SUFFIX)) {
                String className = FilePathUtil.getClassName(filePath, packagePath);
                result.put(className, Class.forName(className));
            }
        }
        return result;
    }

    /**
     * 在Jar包中扫描包路径下的所有类
     *
     * @param url         包资源路径
     * @param packagePath 包路径
     * @return 类名 -> 类对象
     * @throws Exception 异常
     */
    private static Map<String, Class> scanJar(URL url, String packagePath) throws Exception {
        Map<String, Class> result = new HashMap<>();
        // 获取资源路径所在的Jar包
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        // 获取Jar包中的所有实体 (包括所有目录和文件)
        Enumeration<JarEntry> entries = jarFile.entries();
        // 遍历Jar包中的所有实体
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String filePath = jarEntry.getName();
            // 过滤出指定包路径下的所有类
            if (filePath.endsWith(CLASS_FILE_SUFFIX)) {
                String className = filePath.substring(0, filePath.indexOf('.')).replace(URL_PATH_SEPARATOR, PACKAGE_PATH_SEPARATOR);
                if (className.startsWith(packagePath)) {
                    result.put(className, Class.forName(className));
                }
            }
        }
        return result;
    }

}
