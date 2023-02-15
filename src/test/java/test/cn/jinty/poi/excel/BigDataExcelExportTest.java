package test.cn.jinty.poi.excel;

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
    // 写入Excel的数据行数
    private static final int TOTAL_ROW_NUMBER = 2000000;
    // 写入Excel的每行列数
    private static final int TOTAL_COLUMN_NUMBER = 10;

    // 使用XSSFWorkbook，所有数据都存储在内存，数据量过大时会导致内存溢出
    @Test
    public void testExport1() {
        this.printVmOptions();
        Workbook wb = null;
        FileOutputStream out = null;
        try {
            System.out.printf("导出%d行Excel%n", TOTAL_ROW_NUMBER);
            long beginTime = System.currentTimeMillis();
            wb = new XSSFWorkbook();
            this.addDataToWorkBook(wb);
            out = new FileOutputStream("D:\\temp\\BigDataExcelExportTest1_" + TOTAL_ROW_NUMBER + ".xlsx");
            wb.write(out);
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，耗时%d毫秒%n", TOTAL_ROW_NUMBER, (endTime - beginTime));
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
        try {
            System.out.printf("导出%d行Excel%n", TOTAL_ROW_NUMBER);
            long beginTime = System.currentTimeMillis();
            // 默认超过100行就写到临时文件
            wb = new SXSSFWorkbook();
            // 设置临时文件不压缩，这样速度快一点
            wb.setCompressTempFiles(false);
            this.addDataToWorkBook(wb);
            out = new FileOutputStream("D:\\temp\\BigDataExcelExportTest2_" + TOTAL_ROW_NUMBER + ".xlsx");
            wb.write(out);
            long endTime = System.currentTimeMillis();
            System.out.printf("导出%d行Excel成功，耗时%d毫秒%n", TOTAL_ROW_NUMBER, (endTime - beginTime));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (wb != null) {
                // 删除临时文件
                wb.dispose();
            }
            this.close(wb, out);
        }
    }

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
    private void addDataToWorkBook(Workbook wb) {
        // 将数据切分到多个表单，避免单表单行数超限
        int totalSheetNumber = TOTAL_ROW_NUMBER / SHEET_MAX_ROW_NUMBER + (TOTAL_ROW_NUMBER % SHEET_MAX_ROW_NUMBER == 0 ? 0 : 1);
        int totalRowNumber = TOTAL_ROW_NUMBER;
        for (int sheetNumber = 1; sheetNumber <= totalSheetNumber; sheetNumber++) {
            // 表单
            Sheet sheet = wb.createSheet("Sheet" + sheetNumber);
            // 当前表单应该有多少行数据
            int sheetRowNumber;
            if (totalRowNumber >= SHEET_MAX_ROW_NUMBER) {
                sheetRowNumber = SHEET_MAX_ROW_NUMBER;
                totalRowNumber -= SHEET_MAX_ROW_NUMBER;
            } else {
                sheetRowNumber = totalRowNumber;
            }
            Row row;
            Cell cell;
            // 标题行
            row = sheet.createRow(0);
            for (int cellNumber = 0; cellNumber < TOTAL_COLUMN_NUMBER; cellNumber++) {
                cell = row.createCell(cellNumber);
                cell.setCellValue("列名" + (cellNumber + 1));
            }
            // 数据行
            for (int rowNumber = 1; rowNumber < sheetRowNumber; rowNumber++) {
                row = sheet.createRow(rowNumber);
                for (int cellNumber = 0; cellNumber < TOTAL_COLUMN_NUMBER; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    cell.setCellValue(RANDOM.nextInt(100));
                }
                if (rowNumber % 10000 == 0) {
                    System.out.printf("当前处理到第%d行%n", (sheetNumber - 1) * SHEET_MAX_ROW_NUMBER + rowNumber);
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
