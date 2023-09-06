package test.cn.jinty.util.excel;

import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;
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
 * 读取Excel文件，转成Sql语句
 *
 * @author Jinty
 * @date 2023/9/6
 **/
public class ExcelToSqlTest {

    @Test
    public void test1() {
        String dir = "D:\\项目文档\\退供(RETURN)\\退供(VIS-RETURN)\\刷数记录\\20230901-3PL历史数据迁移";
        String inputFilePath = FilePathUtil.concatBySeparator(dir, "rv_return_3pl.xlsx");
        List<List<String>> data = readData(inputFilePath);
        String format = "insert into rv_return(type, return_type, vendor_code, warehouse_id, quality_type, model, status, plan_quantity, real_quantity, wms_id, out_time, create_by, create_time, update_by, last_update_time, is_deleted, attr8, oa_id, confirm_time, is_high_value, priority, ods_qty, combo_status, is_store_delivery, is_specially_delivery, vendor_confirm_time, galaxy_id, merge_rule, start_time, category_conf_type, rule_id, business_type, pay_type, source_sys, biz_flag) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
        System.out.println("SQL模板：" + format);
        List<String> sqlList = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> list = data.get(i);
            String sql = String.format(format, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9),
                    list.get(10), list.get(11), list.get(12), list.get(13), list.get(14), list.get(15), list.get(16), list.get(17), list.get(18), list.get(19),
                    list.get(20), list.get(21), list.get(22), list.get(23), list.get(24), list.get(25), list.get(26), list.get(27), list.get(28), list.get(29),
                    list.get(30), list.get(31), list.get(32), list.get(33), list.get(34));
            sqlList.add(sql);
        }
        this.writeSqlToFile(sqlList, FilePathUtil.concatBySeparator(dir, "rv_return_3pl.sql"));
    }

    @Test
    public void test2() {
        String dir = "D:\\项目文档\\退供(RETURN)\\退供(VIS-RETURN)\\刷数记录\\20230901-3PL历史数据迁移";
        String inputFilePath = FilePathUtil.concatBySeparator(dir, "rv_return_address_3pl.xlsx");
        List<List<String>> data = readData(inputFilePath);
        String format = "insert into rv_return_address(rv_return_id, consignee, country, state, city, region, town, postcode, create_time) values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
        System.out.println("SQL模板：" + format);
        List<String> sqlList = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> list = data.get(i);
            String sql = String.format(format, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8));
            sqlList.add(sql);
        }
        this.writeSqlToFile(sqlList, FilePathUtil.concatBySeparator(dir, "rv_return_address_3pl.sql"));
    }

    @Test
    public void test3() {
        String dir = "D:\\项目文档\\退供(RETURN)\\退供(VIS-RETURN)\\刷数记录\\20230901-3PL历史数据迁移";
        String inputFilePath = FilePathUtil.concatBySeparator(dir, "rv_return_item_3pl.xlsx");
        List<List<String>> data = readData(inputFilePath);
        String format = "insert into rv_return_item(rv_return_id, po, inv_type, sku, goods_name, inv_status, stock_quantity, retreat_quantity, plan_quantity, real_quantity, create_time, is_deleted, quality_type, is_clean_return, category_id, category2_id, category3_id, brand_code) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
        System.out.println("SQL模板：" + format);
        List<String> sqlList = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> list = data.get(i);
            String sql = String.format(format, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9),
                    list.get(10), list.get(11), list.get(12), list.get(13), list.get(14), list.get(15), list.get(16), list.get(17));
            sqlList.add(sql);
        }
        this.writeSqlToFile(sqlList, FilePathUtil.concatBySeparator(dir, "rv_return_item_3pl.sql"));
    }

    /* 以下为内部函数 */

    // 将生成的sql输出到指定文件
    private void writeSqlToFile(List<String> sqlList, String outputFilePath) {
        System.out.println("生成SQL的行数：" + sqlList.size());
        try {
            FileUtil.writeLine(sqlList, new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输出SQL文件：" + outputFilePath);
    }

    // 读取单元格数据
    private List<List<String>> readData(String filePath) {
        System.out.println("待处理文件：" + filePath);
        InputStream is = null;
        Workbook wb = null;
        List<List<String>> data = null;
        try {
            // 解决"Zip bomb detected"报错
            ZipSecureFile.setMinInflateRatio(0);
            // 读取文件
            is = new FileInputStream(filePath);
            // 根据输入流创建工作簿对象
            wb = WorkbookFactory.create(is);
            // 读取第一个表单页
            Sheet sheet = wb.getSheetAt(0);
            // 读取所有的行列数据
            data = readData(sheet);
            System.out.println("行数：" + data.size());
            System.out.println("第一行的列数：" + data.get(0).size());
            System.out.println("最后行的列数：" + data.get(data.size() - 1).size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(wb);
            IOUtil.closeQuietly(is);
        }
        return data;
    }

    // 读取单元格数据
    private List<List<String>> readData(Sheet sheet) {
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
    private String getCellValue(Row row, int idx) {
        Cell cell = row.getCell(idx);
        return cell == null ? "" : new DataFormatter().formatCellValue(cell);
    }

}
