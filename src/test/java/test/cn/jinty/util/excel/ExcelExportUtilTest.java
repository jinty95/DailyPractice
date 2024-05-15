package test.cn.jinty.util.excel;

import cn.jinty.Main;
import cn.jinty.util.ChineseUtil;
import cn.jinty.util.excel.ExcelExportUtil;
import cn.jinty.util.io.FileUtil;
import cn.jinty.util.io.IOUtil;
import cn.jinty.util.string.RandomStringUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static test.cn.jinty.util.excel.Person.getFields;
import static test.cn.jinty.util.excel.Person.getTitles;

/**
 * Excel导出 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/2/15
 **/
public class ExcelExportUtilTest {

    // 随机数
    private static final Random RANDOM = new Random();

    // 限制每个Sheet的数据行数
    private static final int SHEET_MAX_ROW_NUMBER = 50000;

    // 限制每个Excel的数据行数
    private static final int FILE_MAX_ROW_NUMBER = 200000;

    // 使用XSSFWorkbook，所有数据都存储在内存，数据量过大时会导致内存溢出
    @Test
    public void testBigDataExcelExport1() {
        this.printVmOptions();
        this.modifyTmpDir();
        Workbook wb = null;
        FileOutputStream out = null;
        int totalRowNumber = 500;
        System.out.printf("导出%d行Excel%n", totalRowNumber);
        String filePath = "D:\\temp\\BigDataExcelExportTest1_" + totalRowNumber + ".xlsx";
        try {
            //String filePath = File.createTempFile("BigDataExcelExportTest1_", ".xlsx").getAbsolutePath();
            long beginTime = System.currentTimeMillis();
            wb = new XSSFWorkbook();
            ExcelExportUtil.writeToWorkbook(wb, SHEET_MAX_ROW_NUMBER, getTitles(), getFields(), getContents(totalRowNumber));
            out = new FileOutputStream(filePath);
            wb.write(out);
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，文件路径%s，耗时%d毫秒%n", totalRowNumber, filePath, (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(out);
            IOUtil.closeQuietly(wb);
        }
    }

    // 使用SXSSFWorkbook，默认超过100行就写到临时文件，不会一直占用内存，避免内存溢出
    // 临时文件名称为poi-sxssf-sheet-xxx.xml，Windows系统默认存放在\AppData\Local\Temp\poifiles，Linux系统默认存放在/tmp/poifiles
    @Test
    public void testBigDataExcelExport2() {
        this.printVmOptions();
        this.modifyTmpDir();
        int totalRowNumber = 500000;
        System.out.printf("导出%d行Excel%n", totalRowNumber);
        String filePath = "D:\\temp\\BigDataExcelExportTest2_" + totalRowNumber + ".xlsx";
        try {
            long beginTime = System.currentTimeMillis();
            ExcelExportUtil.writeToFile(filePath, SHEET_MAX_ROW_NUMBER, getTitles(), getFields(), getContents(totalRowNumber));
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，文件路径%s，耗时%d毫秒%n", totalRowNumber, filePath, (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 单个Excel太大时很难打开，所以对于大数据量，可以切分成多个Excel导出，最后压成一个压缩包
    @Test
    public void testBigDataExcelExport3() {

        long beginTime = System.currentTimeMillis();
        this.printVmOptions();
        this.modifyTmpDir();
        int totalRowNumber = 2000000;

        // 判断文件数量，以及最后一个文件的数据行数
        int lastFileRowNumber = totalRowNumber % FILE_MAX_ROW_NUMBER;
        int fileCount = totalRowNumber / FILE_MAX_ROW_NUMBER;
        if (lastFileRowNumber == 0) {
            lastFileRowNumber = FILE_MAX_ROW_NUMBER;
        } else {
            fileCount++;
        }
        System.out.printf("导出%d行Excel%n", totalRowNumber);
        System.out.printf("分成%d个文件导出，每个文件%d行(最后一个文件%d行)%n%n", fileCount, FILE_MAX_ROW_NUMBER, lastFileRowNumber);
        List<String> filePaths = new ArrayList<>();

        // 生成多个Excel文件
        for (int i = 1; i <= fileCount; i++) {
            String filePath = "D:\\temp\\BigDataExcelExportTest3_" + totalRowNumber + "_" + i + ".xlsx";
            try {
                int fileRowNumber = i == fileCount ? lastFileRowNumber : FILE_MAX_ROW_NUMBER;
                ExcelExportUtil.writeToFile(filePath, SHEET_MAX_ROW_NUMBER, getTitles(), getFields(), getContents(fileRowNumber));
                filePaths.add(filePath);
                System.out.printf("第%d个文件导出完成，文件路径=%s%n%n", i, filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 将多个Excel文件压缩为一个压缩包，然后删除这些Excel文件
        String zipFilePath = "D:\\temp\\BigDataExcelExportTest3.zip";
        try {
            FileUtil.zip(filePaths.stream().map(File::new).collect(Collectors.toList()), new File(zipFilePath));
            System.out.printf("压缩多个Excel文件，生成压缩包=%s%n", zipFilePath);
            FileUtil.deleteFiles(filePaths.stream().map(File::new).collect(Collectors.toList()));
            System.out.printf("删除多个Excel文件，文件列表=%s%n%n", filePaths);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("导出%d行Excel成功，文件路径%s，耗时%d毫秒%n", totalRowNumber, zipFilePath, (endTime - beginTime));

    }

    // 数据为空时，导出一个只有标题的Excel，而不是一个无法打开的Excel
    @Test
    public void testNullDataExcelExport() {
        this.printVmOptions();
        this.modifyTmpDir();
        int totalRowNumber = 0;
        System.out.printf("导出%d行Excel%n", totalRowNumber);
        String filePath = "D:\\temp\\NullDataExcelExportTest_" + totalRowNumber + ".xlsx";
        try {
            long beginTime = System.currentTimeMillis();
            ExcelExportUtil.writeToFile(filePath, SHEET_MAX_ROW_NUMBER, getTitles(), getFields(), getContents(totalRowNumber));
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，文件路径%s，耗时%d毫秒%n", totalRowNumber, filePath, (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取Excel模板，写入数据，然后导出
    @Test
    public void testReadFromTemplateAndExport() {
        InputStream is = null;
        Workbook wb = null;
        FileOutputStream os = null;
        String templatePath = "/excel/person_excel_template.xlsx";
        String filePath = "D:\\temp\\ReadFromTemplateAndExportTest.xlsx";
        try {
            is = Main.class.getResourceAsStream(templatePath);
            // 这种又读又写的，无法使用流式读写功能，所以要注意这里存在内存溢出的风险
            wb = WorkbookFactory.create(is);
            Sheet sheet1 = wb.getSheetAt(0);
            ExcelExportUtil.writeContentToSheet(sheet1, 2, null, getFields(), getContents(5));
            Sheet sheet2 = wb.getSheetAt(1);
            ExcelExportUtil.writeContentToSheet(sheet2, 2, null, getFields(), getContents(5));
            // 只有两个sheet，超出就抛异常了
            /*Sheet sheet3 = wb.getSheetAt(2);
            ExcelExportUtil.writeContentToSheet(sheet3, 2, null, getFields(), getContents(5));*/
            os = new FileOutputStream(filePath);
            wb.write(os);
            System.out.println("读取Excel模板：" + templatePath);
            System.out.println("往Excel模板中填充数据并导出：" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            IOUtil.closeQuietly(os);
            IOUtil.closeQuietly(wb);
            IOUtil.closeQuietly(is);
        }
    }

    /* 以下为内部函数 */

    // 打印JAVA虚拟机参数
    private void printVmOptions() {
        long xmsMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long xmxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms" + xmsMemory + "M");
        System.out.println("-Xmx" + xmxMemory + "M");
        System.out.println();
    }

    // 修改临时文件路径
    private void modifyTmpDir() {
        String tmpDir = System.getProperty(TempFile.JAVA_IO_TMPDIR);
        System.out.println("默认临时文件目录为：" + tmpDir);
        String customTmpDir = "D:\\temp";
        System.setProperty(TempFile.JAVA_IO_TMPDIR, customTmpDir);
        System.out.println("修改临时文件目录为：" + customTmpDir);
        System.out.println();
    }

    // 数据列表
    private List<Person> getContents(int size) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Person person = new Person();
            person.setId((long) (i + 1));
            person.setName(ChineseUtil.randomFullName());
            person.setAge(RANDOM.nextInt(100));
            person.setIdCard(RandomStringUtil.randomDigit(18));
            person.setPhoneNum("1" + RandomStringUtil.randomDigit(10));
            person.setRemark(ChineseUtil.random(10));
            list.add(person);
        }
        return list;
    }

}

// java.lang.IllegalAccessException: Class xx can not access a member of class yy with modifiers "public"
// 虽然异常依然指向了成员，但是该成员是由public来修饰，证明成员的访问权限没有问题。关键还是在于是类的访问权限不够，此时需要提升类的访问权限。
/*@Data
@NoArgsConstructor
class Person {
    private Long id;
    private String name;
    private Integer age;
    private String idCard;
    private String phoneNum;
    private String remark;
}*/
