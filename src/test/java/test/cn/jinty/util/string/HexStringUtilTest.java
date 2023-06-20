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
    public void testHexToBytes() {
        String hex = "123456789ABCDEFF";
        System.out.println("十六进制数：" + hex);
        byte[] bytes = HexStringUtil.hexToBytes(hex);
        System.out.println("字节数组：" + Arrays.toString(bytes));
        System.out.println("十六进制数：" + HexStringUtil.bytesToHex(bytes));
    }

}
