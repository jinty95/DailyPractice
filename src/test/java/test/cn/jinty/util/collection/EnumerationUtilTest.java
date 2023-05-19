package test.cn.jinty.util.collection;

import cn.jinty.util.collection.EnumerationUtil;
import org.junit.Test;

import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * Enumeration - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/5/18
 **/
public class EnumerationUtilTest {

    public Enumeration<?> getEnumeration() {
        return new StringTokenizer("A,B,C", ",");
    }

    @Test
    public void testToString() {
        System.out.println(EnumerationUtil.toString(getEnumeration()));
        System.out.println(EnumerationUtil.toString(getEnumeration(), null));
        System.out.println(EnumerationUtil.toString(getEnumeration(), " - "));
    }

    @Test
    public void testToList() {
        System.out.println(EnumerationUtil.toList(getEnumeration()));
    }

    @Test
    public void testToSet() {
        System.out.println(EnumerationUtil.toSet(getEnumeration()));
    }

}
