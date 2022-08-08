package test.cn.jinty.util;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.util.IOUtil;
import cn.jinty.util.StringUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * IO流 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/8/8
 **/
public class IOUtilTest {

    @Test
    public void testZipAndUnzip() throws IOException {
        String str = StringUtil.repeat("hello", 100);
        System.out.println("原始数据：" + str);
        byte[] origin = str.getBytes();
        System.out.println("原始数据大小：" + origin.length + BinaryUnitEnum.B.name());
        byte[] zip = IOUtil.zip(origin);
        System.out.println("压缩数据大小：" + zip.length + BinaryUnitEnum.B.name());
        origin = IOUtil.unzip(zip);
        System.out.println("解压数据大小：" + origin.length + BinaryUnitEnum.B.name());
        System.out.println("解压数据：" + new String(origin));
    }

}
