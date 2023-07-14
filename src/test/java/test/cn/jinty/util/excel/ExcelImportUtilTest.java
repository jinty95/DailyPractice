package test.cn.jinty.util.excel;

import cn.jinty.util.excel.ExcelImportUtil;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.junit.Test;

import java.util.List;

import static test.cn.jinty.util.excel.Person.getFields;
import static test.cn.jinty.util.excel.Person.getTitles;

/**
 * Excel导入 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/5/30
 **/
public class ExcelImportUtilTest {

    @Test
    public void testImport() {
        String filePath = "D:\\temp\\BigDataExcelExportTest1_500.xlsx";
        //String filePath = "D:\\temp\\BigDataExcelExportTest3_2000000_1.xlsx";
        System.out.println("导入文件：" + filePath);
        try {
            // 解决"Zip bomb detected"报错
            ZipSecureFile.setMinInflateRatio(0);
            List<Person> data = ExcelImportUtil.readContentFromFile(filePath, getTitles(), getFields(), Person.class);
            System.out.println("数据总行数：" + data.size());
            System.out.println("数据详情：");
            data.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
