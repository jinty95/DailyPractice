package test.cn.jinty.util;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;
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

    @Test
    public void testGetSize() {
        File file = new File("D:/Users/jintai.wang/Pictures/小仓鼠.jpg");
        try {
            System.out.println("获取文件大小(字节数)");
            System.out.println(FileUtil.getSize(file));
            System.out.println("获取文件大小(自适应单位)");
            System.out.println(FileUtil.getSizeWithUnit(file));
            System.out.println("获取文件大小(自定义单位)");
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.B));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.KB));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.MB));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.GB));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.TB));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}