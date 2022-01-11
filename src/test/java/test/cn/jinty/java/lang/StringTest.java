package test.cn.jinty.java.lang;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 字符串 - 测试
 *
 * @author jintai.wang
 * @date 2022/1/11
 **/
public class StringTest {

    @Test
    public void testConstructByUCS2() {
        // 通过Unicode构建字符串
        String str = "\u2600\u2601\u231A\u270A\u270B\u270C";
        System.out.println(str);
        System.out.printf("string length = %d, byte array length = %d%n", str.length(), str.getBytes(StandardCharsets.UTF_8).length);
    }

    @Test
    public void testConstructByUCS4() {
        // 超出16位的Unicode无法正确识别
        String str1 = "\u1D11E";
        System.out.println(str1);
        // 将上述Unicode转成UTF-16编码，得到4个字节，转为2个Unicode，可以正确识别为1个字符
        String str2 = "\uD834\uDD1E";
        System.out.println(str2);
        System.out.println(str2.codePointAt(0));
        System.out.println(str2.codePointCount(0, str2.length()));
        // UCS2与UCS4混合
        String str3 = "\uD834\uDD1EUCS2";
        System.out.println(str3);
        System.out.println(str3.codePointCount(0, str3.length()));
    }

}
