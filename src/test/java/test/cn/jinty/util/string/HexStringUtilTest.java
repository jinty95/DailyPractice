package test.cn.jinty.util.string;

import cn.jinty.util.string.HexStringUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 十六进制字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class HexStringUtilTest {

    @Test
    public void testBytesToHex() {
        String[] arr = {
                "中国", "Hello"
        };
        boolean[] flags = {true, false};
        for (String a : arr) {
            for (boolean flag : flags) {
                String hex = HexStringUtil.bytesToHex(a.getBytes(), flag);
                System.out.printf("字符串[%s]，转为十六进制字符串[%s]，转回原字符串[%s]%n",
                        a, hex, new String(HexStringUtil.hexToBytes(hex, flag)));
            }
        }
    }

    @Test
    public void testHexToBytes() {
        String[] arr = {
                "\\xe4\\xb8\\xad\\xe5\\x9b\\xbd",
                "\\x48\\x65\\x6c\\x6c\\x6f",
                "\\xE6\\x9D\\x83\\xE5\\xA8\\x81\\xE6\\x8C\\x87\\xE5\\x8D\\x97"
        };
        for (String a : arr) {
            System.out.println(new String(HexStringUtil.hexToBytes(a, true)));
        }
    }

}
