package test.cn.jinty.java.util;

import org.junit.Test;

import java.util.Locale;

/**
 * 本地 - 测试
 *
 * @author Jinty
 * @date 2024/4/18
 */
public class LocaleTest {

    @Test
    public void test1() {
        Locale locale = Locale.getDefault();
        System.out.println("本机语言及国家：" + locale.getLanguage() + "_" + locale.getCountry());
    }

}
