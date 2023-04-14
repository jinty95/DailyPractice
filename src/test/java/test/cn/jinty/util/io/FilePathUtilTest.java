package test.cn.jinty.util.io;

import cn.jinty.Main;
import cn.jinty.util.DateUtil;
import cn.jinty.util.io.FilePathUtil;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * 文件路径 - 工具类
 *
 * @author Jinty
 * @date 2023/4/14
 **/
public class FilePathUtilTest {

    private File getFile() {
        return new File("D:/Users/jintai.wang/Pictures/小仓鼠.jpg");
    }

    @Test
    public void testGetFileType() {
        File file = getFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(FilePathUtil.getFileType(file.getAbsolutePath()));
    }

    @Test
    public void testConvertSeparator() {
        System.out.println(FilePathUtil.convertSeparator("D:/Users/jintai.wang/Pictures"));
        System.out.println(FilePathUtil.convertSeparator("D://Users//jintai.wang//Pictures"));
        System.out.println(FilePathUtil.convertSeparator("D:///Users////jintai.wang////////Pictures"));
        System.out.println(FilePathUtil.convertSeparator("D:\\Users\\jintai.wang\\Pictures"));
        System.out.println(FilePathUtil.convertSeparator("D:\\\\Users\\\\jintai.wang\\\\\\Pictures"));
        System.out.println(FilePathUtil.convertSeparator("D:///\\\\Users///\\\\jintai.wang///\\\\\\Pictures"));
    }

    @Test
    public void testConcatBySeparator() {
        System.out.println(FilePathUtil.concatBySeparator("D:\\", "\\Users\\", "\\jintai.wang", "Pictures"));
        System.out.println(FilePathUtil.concatBySeparator("D:/", "/Users/", "/jintai.wang", "Pictures"));
        System.out.println(FilePathUtil.concatBySeparator("D:/", "\\Users/", "\\/jintai.wang\\", "\\//\\Pictures"));
        System.out.println(FilePathUtil.concatBySeparator("D:/", "\\Users/", "\\/jintai.wang\\", "\\//\\Pictures//"));
    }

    @Test
    public void testSplitFilePath() {
        String filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy";
        System.out.println("文件路径拆分：" + Arrays.toString(FilePathUtil.splitFilePath(filePath)));
        filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy.txt";
        System.out.println("文件路径拆分：" + Arrays.toString(FilePathUtil.splitFilePath(filePath)));
        filePath = "Pictures/yyy/yyy.txt";
        String[] arr = FilePathUtil.splitFilePath(filePath);
        System.out.println("文件路径拆分：" + Arrays.toString(arr));
        System.out.println("文件路径重组(扩展名称)：" + arr[0] + File.separator + arr[1] + "_已盖章." + arr[2]);
    }

    @Test
    public void testGetAbsolutePath() {
        System.out.println(FilePathUtil.getAbsolutePath("D:/Users/jintai.wang/Pictures", false));
        System.out.println(FilePathUtil.getAbsolutePath("/sql", true));
        System.out.println(FilePathUtil.getAbsolutePath("/template", true));
        System.out.println(FilePathUtil.getAbsolutePath("/cn", true));
        System.out.println(FilePathUtil.getAbsolutePath("/cn/jinty/enums", true));
        System.out.println(FilePathUtil.getAbsolutePath("io/IOUtil.class", true, DateUtil.class));
        System.out.println(FilePathUtil.getAbsolutePath("util/DateUtil.class", true, Main.class));
    }

}
