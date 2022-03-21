package test.cn.jinty.util;

import cn.jinty.constant.FileTypeEnum;
import cn.jinty.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 文件 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/3/17
 **/
public class FileUtilTest {

    @Test
    public void testToBase64DataURL() {
        File file = new File("D:/Users/jintai.wang/Pictures/小仓鼠.jpg");
        try {
            System.out.println(FileUtil.toBase64DataURL(file, FileTypeEnum.JPEG));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
