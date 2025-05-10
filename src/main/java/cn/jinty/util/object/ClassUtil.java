package cn.jinty.util.object;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类/反射 - 工具类
 *
 * @author Jinty
 * @date 2023/3/15
 **/
public final class ClassUtil {

    private ClassUtil() {
    }

    // 类文件后缀
    private static final String CLASS_FILE_SUFFIX = ".class";
    // URL路径分隔符 (操作系统无关，恒为/)
    private static final char URL_PATH_SEPARATOR = '/';
    // 包路径分隔符
    private static final char PACKAGE_PATH_SEPARATOR = '.';

    // 类属性缓存
    private static final Map<Class<?>, Map<String, Field>> CLASS_FILED_CACHE = new ConcurrentHashMap<>();
    // 类方法缓存
    private static final Map<Class<?>, Map<String, Method>> CLASS_METHOD_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取对象的类型
     *
     * @param obj 对象
     * @return 类型
     */
    public static Class<?> getClass(Object obj) {
        return obj == null ? null : obj.getClass();
    }

    /**
     * 获取类属性映射（包括当前类及其所有父类属性）
     *
     * @param clazz 类
     * @return 属性名 -> 属性
     */
    private static Map<String, Field> getClassFieldMap(Class<?> clazz) {
        return CLASS_FILED_CACHE.computeIfAbsent(clazz, key -> {
            Map<String, Field> classFieldMap = new LinkedHashMap<>();
            Class<?> curClass = clazz;
            while (curClass != null) {
                // 按子类到父类的顺序获取属性，同名属性取子类的
                for (Field field : curClass.getDeclaredFields()) {
                    classFieldMap.putIfAbsent(field.getName(), field);
                }
                curClass = curClass.getSuperclass();
            }
            return Collections.unmodifiableMap(classFieldMap);
        });
    }

    /**
     * 获取类的所有属性 (包括父类属性)
     *
     * @param clazz 类
     * @return 所有属性
     */
    public static List<Field> getAllField(Class<?> clazz) {
        return new ArrayList<>(getClassFieldMap(clazz).values());
    }

    /**
     * 根据属性名获取类的属性 (包括父类属性)
     *
     * @param clazz     类
     * @param fieldName 属性名
     * @return 属性对象
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        return getClassFieldMap(clazz).get(fieldName);
    }

    /**
     * 获取类方法映射（包括当前类及其所有父类方法）
     *
     * @param clazz 类
     * @return 方法名 -> 方法
     */
    private static Map<String, Method> getClassMethodMap(Class<?> clazz) {
        return CLASS_METHOD_CACHE.computeIfAbsent(clazz, key -> {
            Map<String, Method> classMethodMap = new LinkedHashMap<>();
            Class<?> curClass = clazz;
            while (curClass != null) {
                // 按子类到父类的顺序获取方法，同名方法取子类的
                for (Method method : curClass.getDeclaredMethods()) {
                    classMethodMap.putIfAbsent(method.getName(), method);
                }
                curClass = curClass.getSuperclass();
            }
            return Collections.unmodifiableMap(classMethodMap);
        });
    }

    /**
     * 获取类的所有方法 (包括父类方法)
     *
     * @param clazz 类
     * @return 所有方法
     */
    public static List<Method> getAllMethod(Class<?> clazz) {
        return new ArrayList<>(getClassMethodMap(clazz).values());
    }

    /**
     * 根据方法名获取类的方法 (包括父类方法)
     *
     * @param clazz      类
     * @param methodName 方法名
     * @return 方法对象
     */
    public static Method getMethod(Class<?> clazz, String methodName) {
        return getClassMethodMap(clazz).get(methodName);
    }

    /**
     * 获取类实现的所有接口
     *
     * @param clazz 类
     * @return 所有接口
     */
    public static List<Class<?>> getAllInterface(Class<?> clazz) {
        Set<Class<?>> infSet = new HashSet<>();
        getAllInterface(clazz, infSet);
        return new ArrayList<>(infSet);
    }

    /**
     * 获取类实现的所有接口
     *
     * @param clazz  类
     * @param infSet 接口集合 (去重)
     */
    private static void getAllInterface(Class<?> clazz, Set<Class<?>> infSet) {
        while (clazz != null) {
            // 获取当前类的接口列表
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> inf : interfaces) {
                if (infSet.add(inf)) {
                    getAllInterface(inf, infSet);
                }
            }
            // 获取当前类的父类
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * 获取类的所有父类
     *
     * @param clazz 类
     * @return 所有父类
     */
    public static List<Class<?>> getAllSuperclass(Class<?> clazz) {
        List<Class<?>> result = new ArrayList<>();
        while (clazz != null) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null) {
                break;
            }
            result.add(superclass);
            clazz = superclass;
        }
        return result;
    }

    /**
     * 获取类的所有超类 (包括父类和接口)
     *
     * @param clazz 类
     * @return 所有超类
     */
    public static List<Class<?>> getAllSuper(Class<?> clazz) {
        List<Class<?>> result = new ArrayList<>();
        // 父类
        result.addAll(getAllSuperclass(clazz));
        // 接口
        result.addAll(getAllInterface(clazz));
        return result;
    }

    // 包装类 -> 基本类型
    private static final Map<Class<?>, Class<?>> WRAP_TO_PRIMITIVE_MAP = new HashMap<>();
    // 基本类型 -> 包装类
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAP_MAP = new HashMap<>();

    static {
        WRAP_TO_PRIMITIVE_MAP.put(Boolean.class, boolean.class);
        WRAP_TO_PRIMITIVE_MAP.put(Byte.class, byte.class);
        WRAP_TO_PRIMITIVE_MAP.put(Short.class, short.class);
        WRAP_TO_PRIMITIVE_MAP.put(Integer.class, int.class);
        WRAP_TO_PRIMITIVE_MAP.put(Long.class, long.class);
        WRAP_TO_PRIMITIVE_MAP.put(Float.class, float.class);
        WRAP_TO_PRIMITIVE_MAP.put(Double.class, double.class);
        WRAP_TO_PRIMITIVE_MAP.put(Character.class, char.class);

        for (Map.Entry<Class<?>, Class<?>> entry : WRAP_TO_PRIMITIVE_MAP.entrySet()) {
            PRIMITIVE_TO_WRAP_MAP.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * 包装类 -> 基本类型
     *
     * @param wrapType 包装类
     * @return 基本类型
     */
    public static Class<?> toPrimitiveType(Class<?> wrapType) {
        return WRAP_TO_PRIMITIVE_MAP.get(wrapType);
    }

    /**
     * 基本类型 -> 包装类
     *
     * @param primitiveType 基本类型
     * @return 包装类
     */
    public static Class<?> toWrapType(Class<?> primitiveType) {
        return PRIMITIVE_TO_WRAP_MAP.get(primitiveType);
    }

    /**
     * 目标类型是否可以被来源类型赋值
     * 1、同类可以相互赋值
     * 2、子类可以赋值给父类
     * 3、基本类型与包装类可以相互赋值
     *
     * @param target 目标类型
     * @param source 来源类型
     * @return 是否
     */
    public static boolean isAssignableFrom(Class<?> target, Class<?> source) {
        if (target.isAssignableFrom(source)) {
            return true;
        }
        if (target.isPrimitive()) {
            return WRAP_TO_PRIMITIVE_MAP.get(source) == target;
        } else {
            return PRIMITIVE_TO_WRAP_MAP.get(source) == target;
        }
    }

    /**
     * 扫描包路径下的所有类
     *
     * @param packagePath 包路径
     * @return 类名 -> 类对象
     * @throws Exception 异常
     */
    public static Set<Class<?>> scanClasses(String packagePath) throws Exception {
        System.out.printf("扫描指定包路径下的所有类：packagePath=%s%n", packagePath);
        String urlPath = packagePath.replace(PACKAGE_PATH_SEPARATOR, URL_PATH_SEPARATOR);
        URL url = Thread.currentThread().getContextClassLoader().getResource(urlPath);
        if (url == null) {
            System.out.printf("扫描到的类总数为0：total=%s%n", 0);
            return new HashSet<>();
        }
        String protocol = url.getProtocol();
        Set<Class<?>> result = new HashSet<>();
        if ("file".equals(protocol)) {
            result = scanFile(new File(url.getPath()), packagePath);
        } else if ("jar".equals(protocol)) {
            result = scanJar(url, packagePath);
        }
        System.out.printf("扫描到的类总数：total=%s%n", result.size());
        result.forEach((value) -> System.out.printf("扫描到的类：%s%n", value));
        return result;
    }

    /**
     * 在系统中扫描包路径下的所有类
     *
     * @param root        文件根路径
     * @param packagePath 包路径
     * @return 类名 -> 类对象
     * @throws Exception 异常
     */
    private static Set<Class<?>> scanFile(File root, String packagePath) throws Exception {
        Set<Class<?>> result = new HashSet<>();
        if (root == null || !root.exists()) {
            return result;
        }
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    result.addAll(scanFile(file, packagePath));
                }
            }
        } else if (root.isFile()) {
            String filePath = root.getPath();
            if (filePath.endsWith(CLASS_FILE_SUFFIX)) {
                String classFilePath = filePath.replace(File.separatorChar, PACKAGE_PATH_SEPARATOR);
                int index1 = classFilePath.indexOf(packagePath);
                int index2 = classFilePath.lastIndexOf(CLASS_FILE_SUFFIX);
                String className = classFilePath.substring(index1, index2);
                result.add(Class.forName(className));
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
    private static Set<Class<?>> scanJar(URL url, String packagePath) throws Exception {
        Set<Class<?>> result = new HashSet<>();
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
                    result.add(Class.forName(className));
                }
            }
        }
        return result;
    }

}
