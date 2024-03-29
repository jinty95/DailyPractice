package test.cn.jinty.util.security;

import cn.jinty.util.security.MessageDigestUtil;
import org.junit.Test;

import java.io.File;

/**
 * 信息摘要 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public class MessageDigestUtilTest {

    @Test
    public void test() {
        String str = "Hello";
        try {
            String md5 = MessageDigestUtil.md5Str(str);
            String sha1 = MessageDigestUtil.sha1Str(str);
            System.out.println(str);
            System.out.println("MD5：" + md5 + ", length=" + md5.length());
            System.out.println("SHA1：" + sha1 + ", length=" + sha1.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        File file = new File("C:\\Users\\wangjintai\\Documents\\TombRaider.log");
        String md5 = MessageDigestUtil.md5Str(file);
        String sha1 = MessageDigestUtil.sha1Str(file);
        System.out.println(file.getAbsolutePath());
        System.out.println("MD5：" + md5 + ", length=" + md5.length());
        System.out.println("SHA1：" + sha1 + ", length=" + sha1.length());
    }

}
