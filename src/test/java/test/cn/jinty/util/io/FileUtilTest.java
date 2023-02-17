package test.cn.jinty.util.io;

import cn.jinty.Main;
import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;
import cn.jinty.util.io.FileUtil;
import cn.jinty.util.collection.ListUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    public void testGetBytes() {
        File file = getFile();
        try {
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
            System.out.println(FileUtil.toBase64DataURL(file, FileTypeEnum.JPEG));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSize() {
        File file = getFile();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testScanFilesOfRoot() {
        // 以"/"开头，则在classpath下寻找，否则在调用类所在目录下寻找，返回一个URL对象
        // classpath即"/target/classes"目录
        URL url = Main.class.getResource("enums");
        assert url != null;
        String path = url.getPath();
        File root = new File(path);
        List<File> files = FileUtil.scanFilesOfRoot(root);
        System.out.println("根路径：" + path);
        System.out.println("根路径下所有文件：");
        System.out.println(ListUtil.toString(files, "\n"));
    }

    @Test
    public void testCreateFile() {
        // 最后一级路径表示一个没有后缀的文件名
        String filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy".replace("/", File.separator);
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
        String folderPath = "D:/Users/jintai.wang/Pictures/xxx/xxx".replace("/", File.separator);
        System.out.println(folderPath);
        File folder = FileUtil.createFolder(folderPath);
        assert folder != null;
        System.out.println("目录创建成功：folderPath=" + folder.getAbsolutePath());
    }

    @Test
    public void testSplitFilePath() {
        String filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy".replace("/", File.separator);
        System.out.println("文件路径拆分：" + Arrays.toString(FileUtil.splitFilePath(filePath)));
        filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy.txt".replace("/", File.separator);
        System.out.println("文件路径拆分：" + Arrays.toString(FileUtil.splitFilePath(filePath)));
        filePath = "Pictures/yyy/yyy.txt".replace("/", File.separator);
        String[] arr = FileUtil.splitFilePath(filePath);
        System.out.println("文件路径拆分：" + Arrays.toString(arr));
        System.out.println("文件路径重组(扩展名称)：" + arr[0] + File.separator + arr[1] + "_已盖章." + arr[2]);
    }

    @Test
    public void testHasUtf8Bom() {
        File file = new File("D:\\code\\ap\\fcs_ivfs\\src\\main\\webapp\\WEB-INF\\jsp\\contract\\contract_bw.jsp");
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
            URL url = Main.class.getResource("/properties/application.properties");
            assert url != null;
            System.out.println("文件路径：" + url.getPath());
            System.out.println("文件内容：" + FileUtil.parseProperties(url.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZip() {
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
            System.out.printf("压缩文件完成：filePath=%s, zipFilePath=%s, costTime=%sms%n", filePath, zipFilePath, (end - begin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
