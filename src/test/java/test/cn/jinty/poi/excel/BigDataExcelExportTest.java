package test.cn.jinty.poi.excel;

import cn.jinty.util.io.FileUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 大数据Excel导出 - 测试
 *
 * @author Jinty
 * @date 2023/2/15
 **/
public class BigDataExcelExportTest {

    // 随机数
    private static final Random RANDOM = new Random();

    // 单个Sheet最大1048576
    private static final int SHEET_MAX_ROW_NUMBER = 1048576;

    // 每个Excel的数据行数
    private static final int FILE_MAX_ROW_NUMBER = 500000 - 1;

    // 使用XSSFWorkbook，所有数据都存储在内存，数据量过大时会导致内存溢出
    @Test
    public void testExport1() {
        this.printVmOptions();
        Workbook wb = null;
        FileOutputStream out = null;
        int totalRowNumber = 2000000;
        int totalColumnNumber = 10;
        try {
            System.out.printf("导出%d行Excel%n", totalRowNumber);
            long beginTime = System.currentTimeMillis();
            wb = new XSSFWorkbook();
            this.addDataToWorkBook(wb, totalRowNumber, totalColumnNumber);
            out = new FileOutputStream("D:\\temp\\BigDataExcelExportTest1_" + totalRowNumber + ".xlsx");
            wb.write(out);
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，耗时%d毫秒%n", totalRowNumber, (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(wb, out);
        }
    }

    // 使用SXSSFWorkbook，默认超过100行就写到临时文件，不会一直占用内存，避免内存溢出
    // 临时文件名称为poi-sxssf-sheet-xxx.xml，Windows系统默认存放在\AppData\Local\Temp\poifiles，Linux系统默认存放在/tmp/poifiles
    @Test
    public void testExport2() {
        this.printVmOptions();
        this.modifyTmpDir();
        SXSSFWorkbook wb = null;
        FileOutputStream out = null;
        int totalRowNumber = 2000000;
        int totalColumnNumber = 10;
        try {
            System.out.printf("导出%d行Excel%n", totalRowNumber);
            long beginTime = System.currentTimeMillis();
            // 默认超过100行就写到临时文件
            wb = new SXSSFWorkbook();
            // 设置临时文件不压缩，这样速度快一点
            wb.setCompressTempFiles(false);
            this.addDataToWorkBook(wb, totalRowNumber, totalColumnNumber);
            out = new FileOutputStream("D:\\temp\\BigDataExcelExportTest2_" + totalRowNumber + ".xlsx");
            wb.write(out);
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，耗时%d毫秒%n", totalRowNumber, (endTime - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                // 删除临时文件
                wb.dispose();
            }
            this.close(wb, out);
        }
    }

    // 单个Excel太大时很难打开，所以对于大数据量，可以切分成多个Excel导出，最后压成一个压缩包
    @Test
    public void testExport3() {

        long beginTime = System.currentTimeMillis();
        this.printVmOptions();
        this.modifyTmpDir();
        int totalRowNumber = 2000000;
        int totalColumnNumber = 10;

        // 判断文件数量，以及最后一个文件的数据行数
        int lastFileRowNumber = totalRowNumber % FILE_MAX_ROW_NUMBER;
        int fileCount = totalRowNumber / FILE_MAX_ROW_NUMBER;
        if (lastFileRowNumber == 0) {
            lastFileRowNumber = FILE_MAX_ROW_NUMBER;
        } else {
            fileCount++;
        }
        System.out.printf("导出%d行Excel%n", totalRowNumber);
        System.out.printf("分成%d个文件导出，每个文件%d行(最后一个文件%d行)%n", fileCount, FILE_MAX_ROW_NUMBER, lastFileRowNumber);
        List<String> fileNames = new ArrayList<>();

        // 生成多个Excel文件
        for (int i = 1; i <= fileCount; i++) {
            SXSSFWorkbook wb = null;
            FileOutputStream out = null;
            try {
                wb = new SXSSFWorkbook();
                wb.setCompressTempFiles(false);
                int fileRowNumber = i == fileCount ? lastFileRowNumber : FILE_MAX_ROW_NUMBER;
                this.addDataToWorkBook(wb, fileRowNumber, totalColumnNumber);
                String fileName = "D:\\temp\\BigDataExcelExportTest3_" + totalRowNumber + "_" + i + ".xlsx";
                fileNames.add(fileName);
                out = new FileOutputStream(fileName);
                wb.write(out);
                System.out.printf("第%d个文件导出完成，文件名=%s%n%n", i, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (wb != null) {
                    wb.dispose();
                }
                this.close(wb, out);
            }
        }

        // 将多个Excel文件压缩为一个压缩包，然后删除这些Excel文件
        try {
            String zipFilePath = "D:\\temp\\BigDataExcelExportTest3.zip";
            FileUtil.zip(fileNames, zipFilePath);
            System.out.printf("压缩多个Excel文件，生成压缩包=%s%n", zipFilePath);
            FileUtil.deleteFiles(fileNames);
            System.out.printf("删除多个Excel文件，文件列表=%s%n%n", fileNames);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("导出%d行Excel成功，耗时%d毫秒%n", totalRowNumber, (endTime - beginTime));

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

    // 添加数据到工作表
    private void addDataToWorkBook(Workbook wb, int totalRowNumber, int totalColumnNumber) {
        // 将数据切分到多个表单，避免单表单行数超限
        int lastSheetRowNumber = totalRowNumber % SHEET_MAX_ROW_NUMBER;
        int sheetCount = totalRowNumber / SHEET_MAX_ROW_NUMBER;
        if (lastSheetRowNumber == 0) {
            lastSheetRowNumber = SHEET_MAX_ROW_NUMBER;
        } else {
            sheetCount++;
        }
        for (int i = 1; i <= sheetCount; i++) {
            // 表单
            Sheet sheet = wb.createSheet("Sheet" + i);
            // 特殊处理最后一个表单的行数
            int sheetRowNumber = i == sheetCount ? lastSheetRowNumber : SHEET_MAX_ROW_NUMBER;
            Row row;
            Cell cell;
            // 标题行
            row = sheet.createRow(0);
            for (int cellNumber = 0; cellNumber < totalColumnNumber; cellNumber++) {
                cell = row.createCell(cellNumber);
                cell.setCellValue("列名" + (cellNumber + 1));
            }
            // 数据行
            for (int rowNumber = 1; rowNumber < sheetRowNumber; rowNumber++) {
                row = sheet.createRow(rowNumber);
                for (int cellNumber = 0; cellNumber < totalColumnNumber; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    cell.setCellValue(RANDOM.nextInt(100));
                }
                if (rowNumber % 10000 == 0) {
                    System.out.printf("当前处理到第%d行%n", (i - 1) * SHEET_MAX_ROW_NUMBER + rowNumber);
                }
            }
        }
    }

    // 关闭资源
    private void close(Workbook wb, OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (wb != null) {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
