package test.cn.jinty.util.io;

import cn.jinty.enums.BinaryUnitEnum;
import cn.jinty.enums.FileTypeEnum;
import cn.jinty.util.JdbcUtil;
import cn.jinty.util.StringUtil;
import cn.jinty.util.collection.ListUtil;
import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
    public void testHasUtf8Bom() {
        String filePath = "D:/temp/tmp.txt";
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
            String path = FilePathUtil.getAbsolutePath("/properties/application.properties", true);
            System.out.println("文件路径：" + path);
            System.out.println("文件内容：");
            Properties prop = FileUtil.parseProperties(new File(path));
            for (Object key : prop.keySet()) {
                System.out.println("key=" + key + ", value=" + prop.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testScanFilesOfRoot() {
        String path = FilePathUtil.getAbsolutePath("/cn/jinty/enums", true);
        File root = new File(path);
        List<File> files = FileUtil.scanFilesOfRoot(root);
        System.out.println("根路径：" + path);
        System.out.println("根路径下所有文件：");
        System.out.println(ListUtil.toString(files, "\n"));
    }

    @Test
    public void testDeleteFile() {
        // 非空目录，无法删除
        String dirPath = "D:/Users/jintai.wang/Pictures/xxx";
        System.out.println(dirPath);
        System.out.println("删除成功：" + FileUtil.deleteFile(new File(dirPath)));
        // 不存在的文件，无法删除
        dirPath = "D:/Users/jintai.wang/Pictures/xxx/xxx/xxx.txt";
        System.out.println(dirPath);
        System.out.println("删除成功：" + FileUtil.deleteFile(new File(dirPath)));
    }

    @Test
    public void testDeleteFiles() {
        FileUtil.deleteFiles(new File("D:/code/vis/return-inv"), "iml");
    }

    @Test
    public void testCreateFile() {
        // 最后一级路径表示一个没有后缀的文件名
        String filePath = "D:/Users/jintai.wang/Pictures/yyy/yyy";
        System.out.println(filePath);
        try {
            File file = FileUtil.createFile(new File(filePath));
            assert file != null;
            System.out.println("文件创建成功：filePath=" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDir() {
        String dirPath = "D:/Users/jintai.wang/Pictures/xxx/xxx";
        System.out.println(dirPath);
        File dir = FileUtil.createDir(new File(dirPath));
        assert dir != null;
        System.out.println("目录创建成功：dirPath=" + dir.getAbsolutePath());
        try {
            File file = FileUtil.createFile(new File(dirPath));
            assert file != null;
            System.out.println("文件创建成功：filePath=" + file.getAbsolutePath());
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
            FileUtil.zip(new File(filePath), new File(zipFilePath));
            long end = System.currentTimeMillis();
            System.out.printf("压缩文件完成：filePath=%s, zipFilePath=%s, costTime=%sms%n",
                    filePath, zipFilePath, (end - begin));
            // 解压文件
            String destDir = "D:/temp/picture/Pictures";
            FileUtil.unzip(new File(zipFilePath), destDir);
            System.out.printf("解压文件完成：zipFilePath=%s, destDir=%s, costTime=%sms%n",
                    zipFilePath, destDir, (System.currentTimeMillis() - end));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        String filePath = FilePathUtil.getAbsolutePath("/txt/sensitive_word.txt", true);
        try {
            System.out.println(filePath);
            System.out.println(FileUtil.read(new File(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite() {
        String filePath = FilePathUtil.getAbsolutePath("/txt", true) + File.separator + StringUtil.random(10);
        try {
            System.out.println(filePath);
            FileUtil.write("哈哈哈哈", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadLine() {
        String filePath = FilePathUtil.getAbsolutePath("/txt/sensitive_word.txt", true);
        try {
            List<String> lines = FileUtil.readLine(new File(filePath));
            System.out.println(filePath);
            for (int i = 0; i < lines.size(); i++) {
                System.out.printf("第%d行：%s\n", (i + 1), lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDiffLine() {
        String filePath1 = "D:\\temp\\tmp1.txt";
        String filePath2 = "D:\\temp\\tmp2.txt";
        try {
            List<Map<Integer, String>> diffLines = FileUtil.diffLine(new File(filePath1), new File(filePath2));
            System.out.println("两个文件的差异");
            System.out.println();
            Map<Integer, String> diffLines1 = diffLines.get(0);
            diffLines1.forEach((key, value) -> System.out.println("- " + key + ": " + value));
            System.out.println();
            Map<Integer, String> diffLines2 = diffLines.get(1);
            diffLines2.forEach((key, value) -> System.out.println("+ " + key + ": " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDiffDbCreateTable() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://ip:3306/%s";
            String user = "user";
            String password = "password";
            String db1 = "xxx1";
            String db2 = "xxx2";
            String targetFile = "D:\\temp\\%s.sql";
            Connection conn1 = JdbcUtil.getConnection(driver, String.format(url, db1), user, password);
            Connection conn2 = JdbcUtil.getConnection(driver, String.format(url, db2), user, password);
            String filePath1 = String.format(targetFile, db1);
            String filePath2 = String.format(targetFile, db2);
            FileUtil.write(ListUtil.toString(JdbcUtil.getAllCreateTable(conn1), ";\n\n"), new File(filePath1));
            FileUtil.write(ListUtil.toString(JdbcUtil.getAllCreateTable(conn2), ";\n\n"), new File(filePath2));
            List<Map<Integer, String>> diffLines = FileUtil.diffLine(new File(filePath1), new File(filePath2));
            System.out.println("比较两个数据库的建表语句差异");
            System.out.println();
            Map<Integer, String> diffLines1 = diffLines.get(0);
            diffLines1.forEach((key, value) -> System.out.println("- " + key + ": " + value));
            System.out.println();
            Map<Integer, String> diffLines2 = diffLines.get(1);
            diffLines2.forEach((key, value) -> System.out.println("+ " + key + ": " + value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
