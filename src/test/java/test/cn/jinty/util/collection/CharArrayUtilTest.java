package test.cn.jinty.util.collection;

import cn.jinty.util.collection.CharArrayUtil;
import org.junit.Test;

/**
 * 字符数组 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/3/6
 **/
public class CharArrayUtilTest {

    private char[] getCharArray() {
        return "abc".toCharArray();
    }

    @Test
    public void testIndexOf() {
        char[] arr = getCharArray();
        for (char c : arr) {
            System.out.println(CharArrayUtil.indexOf(arr, c));
        }
    }

    @Test
    public void testToString() {
        char[] arr = getCharArray();
        System.out.println(CharArrayUtil.toString(arr, 0, 0));
        System.out.println(CharArrayUtil.toString(arr, 0, 1));
        System.out.println(CharArrayUtil.toString(arr, 0, 2));
        System.out.println(CharArrayUtil.toString(arr, 0, 3));
        System.out.println(CharArrayUtil.toString(arr, 0, 4));
        System.out.println(CharArrayUtil.toString(arr, 1, 4));
    }

    @Test
    public void testCountChar() {
        char[] arr = getCharArray();
        System.out.println(CharArrayUtil.countChar(arr, ' '));
        System.out.println(CharArrayUtil.countChar(arr, 'a'));
        System.out.println(CharArrayUtil.countChar(arr, 'c'));
    }

}
