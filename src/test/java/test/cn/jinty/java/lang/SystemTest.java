package test.cn.jinty.java.lang;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * 系统 - 测试
 *
 * @author Jinty
 * @date 2023/4/13
 **/
public class SystemTest {

    // 环境变量，只支持查询
    @Test
    public void testEnv() {
        String key = "JAVA_HOME";
        String val = System.getenv(key);
        System.out.printf("查询环境变量：key=%s, val=%s\n", key, val);
        Map<String, String> envMap = System.getenv();
        System.out.println("本机所有环境变量：");
        envMap.entrySet().forEach(System.out::println);
    }

    // 系统属性，支持设置及查询
    @Test
    public void testProp() {
        String key = "KEY_A";
        String val = "VAL_A";
        System.setProperty(key, val);
        System.out.printf("设置系统属性：key=%s, val=%s\n", key, val);
        val = System.getProperty(key);
        System.out.printf("查询系统属性：key=%s, val=%s\n", key, val);
        Properties properties = System.getProperties();
        System.out.println("本机所有系统属性：");
        properties.entrySet().forEach(System.out::println);
    }

    // 系统时间
    // currentTimeMillis常用于获取当前时间
    // nanoTime常用于统计一段代码的运行时间
    @Test
    public void testTime() {
        System.out.println("系统当前距离1970-01-01的时间差(毫秒)：" + System.currentTimeMillis());
        System.out.println("系统当前距离某个固定时间的时间差(纳秒)：" + System.nanoTime());
    }

    // 数组复制
    @Test
    public void testArrayCopy() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[arr1.length];
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    // 垃圾回收
    @Test
    public void testGc() {
        System.gc();
    }

    // 结束JVM进程
    @Test
    public void testExit() {
        // 0 - 正常结束
        //System.exit(0);
        // 非0 - 异常结束
        System.exit(10086);
    }

}
