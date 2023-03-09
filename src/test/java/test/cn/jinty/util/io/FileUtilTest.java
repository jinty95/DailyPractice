package test.cn.jinty.util.io;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;
import cn.jinty.util.StringUtil;
import cn.jinty.util.collection.ListUtil;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/3/17
 **/
public class FileUtilTest {

    private File getFile() {
        return new File("D:/Users/jintai.wang/Pictures/小仓鼠.jpg");
    }

    @Test
    public void testGetFileType() {
        File file = getFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(FileUtil.getFileType(file.getAbsolutePath()));
    }

    @Test
    public void testConvertSeparator() {
        System.out.println(FileUtil.convertSeparator("D:/Users/jintai.wang/Pictures"));
        System.out.println(FileUtil.convertSeparator("D://Users//jintai.wang//Pictures"));
        System.out.println(FileUtil.convertSeparator("D:///Users////jintai.wang////////Pictures"));
        System.out.println(FileUtil.convertSeparator("D:\\Users\\jintai.wang\\Pictures"));
        System.out.println(FileUtil.convertSeparator("D:\\\\Users\\\\jintai.wang\\\\\\Pictures"));
        System.out.println(FileUtil.convertSeparator("D:///\\\\Users///\\\\jintai.wang///\\\\\\Pictures"));
    }

    @Test
    public void testConcatBySeparator() {
        System.out.println(FileUtil.concatBySeparator("D:\\", "\\Users\\", "\\jintai.wang", "Pictures"));
        System.out.println(FileUtil.concatBySeparator("D:/", "/Users/", "/jintai.wang", "Pictures"));
    }

    @Test
    public void testSplitFilePath() {
        String filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy";
        System.out.println("文件路径拆分：" + Arrays.toString(FileUtil.splitFilePath(filePath)));
        filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy.txt";
        System.out.println("文件路径拆分：" + Arrays.toString(FileUtil.splitFilePath(filePath)));
        filePath = "Pictures/yyy/yyy.txt";
        String[] arr = FileUtil.splitFilePath(filePath);
        System.out.println("文件路径拆分：" + Arrays.toString(arr));
        System.out.println("文件路径重组(扩展名称)：" + arr[0] + File.separator + arr[1] + "_已盖章." + arr[2]);
    }

    @Test
    public void testExistFile() {
        File file = getFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(FileUtil.existFile(file));
    }

    @Test
    public void testGetBytes() {
        File file = getFile();
        try {
            System.out.println(file.getAbsolutePath());
            byte[] bytes = FileUtil.getBytes(file);
            System.out.println(Arrays.toString(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToBase64DataURL() {
        File file = getFile();
        try {
            System.out.println(file.getAbsolutePath());
            System.out.println(FileUtil.toBase64DataURL(file, FileTypeEnum.JPEG));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSize() {
        File file = new File("C:/Software/Windows_20200801.exe");
        try {
            System.out.println(file.getAbsolutePath());
            System.out.println("获取文件大小(字节数)");
            System.out.println(FileUtil.getSize(file));
            System.out.println("获取文件大小(自适应单位)");
            System.out.println(FileUtil.getSizeWithUnit(file));
            System.out.println("获取文件大小(自定义单位)");
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.B));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.KB));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.MB));
            System.out.println(FileUtil.getSizeWithUnit(file, BinaryUnitEnum.GB));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testScanFilesOfRoot() {
        String path = FileUtil.getAbsolutePath("/cn/jinty/enums", true);
        File root = new File(path);
        List<File> files = FileUtil.scanFilesOfRoot(root);
        System.out.println("根路径：" + path);
        System.out.println("根路径下所有文件：");
        System.out.println(ListUtil.toString(files, "\n"));
    }

    @Test
    public void testCreateFile() {
        // 最后一级路径表示一个没有后缀的文件名
        String filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy";
        System.out.println(filePath);
        try {
            File file = FileUtil.createFile(filePath);
            assert file != null;
            System.out.println("文件创建成功：filePath=" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateFolder() {
        String folderPath = "D:/Users/jintai.wang/Pictures/xxx/xxx";
        System.out.println(folderPath);
        File folder = FileUtil.createFolder(folderPath);
        assert folder != null;
        System.out.println("目录创建成功：folderPath=" + folder.getAbsolutePath());
    }

    @Test
    public void testDeleteFile() {
        // 非空目录，无法删除
        String folderPath = "D:/Users/jintai.wang/Pictures/xxx";
        System.out.println(folderPath);
        System.out.println("删除是否成功：" + FileUtil.deleteFile(folderPath));
    }

    @Test
    public void testHasUtf8Bom() {
        String filePath = "D:/code/ap/fcs_ivfs/src/main/webapp/WEB-INF/jsp/contract/contract_bw.jsp";
        File file = new File(filePath);
        System.out.println("文件：" + file.getAbsolutePath());
        try {
            System.out.println("是否带有UTF-8对应的BOM：" + FileUtil.hasUtf8Bom(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseProperties() {
        try {
            String path = FileUtil.getAbsolutePath("/properties/application.properties", true);
            System.out.println("文件路径：" + path);
            System.out.println("文件内容：" + FileUtil.parseProperties(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testZipAndUnzip() {
        try {
            long begin = System.currentTimeMillis();
            // 读：需要保证"D:/Users/jintai.wang/Pictures"存在
            String filePath = "D:/Users/jintai.wang/Pictures";
            // 写：不需要保证"D:/temp/picture/Pictures.zip"存在，但需要保证"D:/temp/picture"存在
            String zipFilePath = "D:/temp/picture/Pictures.zip";
            // 创建目录"D:/temp/picture"
            File zipFile = new File(zipFilePath);
            File zipParentFile = zipFile.getParentFile();
            if (zipParentFile != null && !zipParentFile.exists()) {
                zipParentFile.mkdirs();
            }
            // 压缩文件
            FileUtil.zip(filePath, zipFilePath);
            long end = System.currentTimeMillis();
            System.out.printf("压缩文件完成：filePath=%s, zipFilePath=%s, costTime=%sms%n",
                    filePath, zipFilePath, (end - begin));
            // 解压文件
            String destDir = "D:/temp/picture/Pictures";
            FileUtil.unzip(zipFilePath, destDir);
            System.out.printf("压缩文件完成：zipFilePath=%s, destDir=%s, costTime=%sms%n",
                    zipFilePath, destDir, (System.currentTimeMillis() - end));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        String filePath = FileUtil.getAbsolutePath("/txt/sensitive_word.txt", true);
        try {
            System.out.println(filePath);
            System.out.println(FileUtil.read(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite() {
        String filePath = FileUtil.getAbsolutePath("/txt", true) + File.separator + StringUtil.random(10);
        try {
            System.out.println(filePath);
            FileUtil.write("哈哈哈哈", filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAbsolutePath() {
        System.out.println(FileUtil.getAbsolutePath("D:/Users/jintai.wang/Pictures", false));
        System.out.println(FileUtil.getAbsolutePath("/sql", true));
        System.out.println(FileUtil.getAbsolutePath("/template", true));
        System.out.println(FileUtil.getAbsolutePath("/cn", true));
        System.out.println(FileUtil.getAbsolutePath("/cn/jinty/enums", true));
        System.out.println(FileUtil.getAbsolutePath("IOUtil.class", true));
        System.out.println(FileUtil.getAbsolutePath("DateUtil.class", true));
    }

}
