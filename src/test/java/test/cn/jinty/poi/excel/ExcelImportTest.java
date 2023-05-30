package test.cn.jinty.poi.excel;

import cn.jinty.util.io.IOUtil;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel导入 - 测试
 *
 * @author Jinty
 * @date 2023/5/30
 **/
public class ExcelImportTest {

    @Test
    public void testImport() {
        InputStream is = null;
        Workbook wb = null;
        try {
            // 解决"Zip bomb detected"报错
            ZipSecureFile.setMinInflateRatio(0);
            // 读取导入文件
            is = readImportFile();
            // 根据输入流创建工作簿对象
            wb = WorkbookFactory.create(is);
            // 读取第一个表单页
            Sheet sheet = wb.getSheetAt(0);
            // 读取所有的行列数据
            List<List<String>> data = readData(wb, sheet);
            System.out.println(data.size());
            System.out.println(data.get(0).size());
            data.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(wb);
            IOUtil.closeQuietly(is);
        }
    }

    /* 以下为内部函数 */

    // 读取导入文件
    private InputStream readImportFile() throws IOException {
        File file = new File("D:\\temp\\BigDataExcelExportTest1_200.xlsx");
        System.out.println("导入文件：" + file.getAbsolutePath());
        return new FileInputStream(file);
    }

    // 读取单元格数据
    private List<List<String>> readData(Workbook wb, Sheet sheet) {
        List<List<String>> data = new ArrayList<>();
        // 有效行数为[minRow,maxRow]
        int minRow = sheet.getFirstRowNum();
        int maxRow = sheet.getLastRowNum();
        for (int i = minRow; i <= maxRow; i++) {
            Row row = sheet.getRow(i);
            List<String> rowData = new ArrayList<>();
            // 有效列数为[minCol,maxCol-1]
            int minCol = row.getFirstCellNum();
            int maxCol = row.getLastCellNum();
            for (int j = minCol; j < maxCol; j++) {
                String cellVal = getCellValue(row, j);
                rowData.add(cellVal);
            }
            data.add(rowData);
        }
        return data;
    }

    // 读取单元格数据，不管列是什么类型，都返回字符串
    // 导入模板的列都设置为文本，导入时内容都读取为字符串，在程序内部再去自行解析转换，兼容性比较好，不容易出问题
    private String getCellValue(Row row, int idx) {
        Cell cell = row.getCell(idx);
        return cell == null ? "" : new DataFormatter().formatCellValue(cell);
    }

}
