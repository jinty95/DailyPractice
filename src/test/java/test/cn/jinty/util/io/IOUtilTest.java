package test.cn.jinty.util.io;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.util.io.IOUtil;
import cn.jinty.util.StringUtil;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * IO流 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/8/8
 **/
public class IOUtilTest {

    @Test
    public void testGetBytes() throws IOException {
        byte[] bytes = {0, 0, 0, 1};
        InputStream is = new ByteArrayInputStream(bytes);
        System.out.println(Arrays.toString(IOUtil.getBytes(is)));
        System.out.println(Arrays.toString(IOUtil.getBytes(is)));
        is.reset();
        System.out.println(Arrays.toString(IOUtil.getBytes(is)));
    }

    @Test
    public void testInputStreamToOutputStream() throws IOException {
        // 输出流的字节数组一直在内存中吗？
        byte[] bytes = {0, 0, 0, 1};
        InputStream is = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        IOUtil.inputStreamToOutputStream(is, os);
        System.out.println(Arrays.toString(os.toByteArray()));
        os.flush();
        System.out.println(Arrays.toString(os.toByteArray()));
        os.close();
        System.out.println(Arrays.toString(os.toByteArray()));
    }

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

    @Test
    public void testUnzip() throws IOException {
        // 对于Base64字符串，先解码，再解压，最后转原字符串
        String zip = "";
        byte[] zipBytes = Base64.getDecoder().decode(zip.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(IOUtil.unzip(zipBytes)));
    }

}
