package cn.jinty.util.excel;

import cn.jinty.util.io.IOUtil;
import cn.jinty.util.object.IntrospectUtil;
import cn.jinty.util.object.ObjectUtil;
import org.apache.poi.ss.usermodel.*;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导入 - 工具类
 *
 * @author Jinty
 * @date 2023/7/14
 **/
public final class ExcelImportUtil {

    private ExcelImportUtil() {
    }

    /**
     * 读取单元格的值
     * 不管列是什么类型，都返回字符串
     * 导入模板的列都设置为文本，导入时内容都读取为字符串，在程序内部再解析转换为具体类型，兼容性比较好，不容易出问题
     *
     * @param row     行
     * @param cellNum 列号
     * @return 单元格的值
     */
    public static String readFromCell(Row row, int cellNum) {
        Cell cell = row.getCell(cellNum);
        return cell == null ? null : new DataFormatter().formatCellValue(cell);
    }

    /**
     * 读取一行的所有单元格的值
     *
     * @param row 行
     * @return 单元格的值 -> 列号
     */
    public static Map<String, Integer> readFromRow(Row row) {
        Map<String, Integer> cellValueToCellNum = new HashMap<>();
        // 列号范围[minCellNum,maxCellNum)
        int minCellNum = row.getFirstCellNum();
        int maxCellNum = row.getLastCellNum();
        for (int cellNum = minCellNum; cellNum < maxCellNum; cellNum++) {
            cellValueToCellNum.put(readFromCell(row, cellNum), cellNum);
        }
        return cellValueToCellNum;
    }

    /**
     * 从表单页读取标题行
     *
     * @param sheet  表单页
     * @param rowNum 标题行号
     * @param titles 标题列表
     * @return 标题 -> 列号
     */
    public static Map<String, Integer> readTitleFromSheet(Sheet sheet, int rowNum, List<String> titles) {
        Map<String, Integer> titleToCellNum = new HashMap<>();
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return titleToCellNum;
        }
        Map<String, Integer> cellValueToCellNum = readFromRow(row);
        for (String title : titles) {
            titleToCellNum.put(title, cellValueToCellNum.get(title));
        }
        return titleToCellNum;
    }

    /**
     * 从表单页读取数据
     *
     * @param sheet         表单页
     * @param titleRowNum   标题行号
     * @param contentRowNum 数据起始行号
     * @param titles        标题列表
     * @param fields        字段列表
     * @param clazz         数据类型
     * @param <T>           数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readContentFromSheet(Sheet sheet, int titleRowNum, int contentRowNum, List<String> titles,
                                                   List<String> fields, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        // 获取标题对应列号
        Map<String, Integer> titleToCellNum = readTitleFromSheet(sheet, titleRowNum, titles);
        // 获取字段对应描述器
        Map<String, PropertyDescriptor> fieldToDescriptor = IntrospectUtil.getPropertyDescriptorMap(clazz);
        // 行号范围[minRowNum,maxRowNum]
        int minRowNum = sheet.getFirstRowNum();
        int maxRowNum = sheet.getLastRowNum();
        for (int rowNum = Math.max(minRowNum, contentRowNum); rowNum <= maxRowNum; rowNum++) {
            // 读取一行数据
            Row row = sheet.getRow(rowNum);
            // 创建数据对象
            T obj = clazz.newInstance();
            // 遍历所有字段，根据标题获取列号然后从行取值，再通过对应setter反射赋值
            for (int i = 0; i < fields.size(); i++) {
                String field = fields.get(i);
                String title = titles.get(i);
                Integer cellNum = titleToCellNum.get(title);
                PropertyDescriptor descriptor = fieldToDescriptor.get(field);
                if (cellNum != null && descriptor != null && descriptor.getWriteMethod() != null) {
                    // 字符串需要转为字段的具体类型
                    Object value = ObjectUtil.strToObj(readFromCell(row, cellNum), descriptor.getPropertyType());
                    Method setter = descriptor.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
            list.add(obj);
        }
        return list;
    }

    /**
     * 从工作簿的指定表单页读取数据
     *
     * @param workbook      工作簿
     * @param sheetNum      表单页号
     * @param titleRowNum   标题行号
     * @param contentRowNum 数据起始行号
     * @param titles        标题列表
     * @param fields        字段列表
     * @param clazz         数据类型
     * @param <T>           数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readContentFromWorkbook(Workbook workbook, int sheetNum, int titleRowNum,
                                                      int contentRowNum, List<String> titles, List<String> fields,
                                                      Class<T> clazz) throws Exception {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        return readContentFromSheet(sheet, titleRowNum, contentRowNum, titles, fields, clazz);
    }

    /**
     * 从工作簿读取数据
     *
     * @param workbook 工作簿
     * @param titles   标题列表
     * @param fields   字段列表
     * @param clazz    数据类型
     * @param <T>      数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readContentFromWorkbook(Workbook workbook, List<String> titles, List<String> fields,
                                                      Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        // 表单页号范围[minSheetNum,maxSheetNum)
        int minSheetNum = 0;
        int maxSheetNum = workbook.getNumberOfSheets();
        for (int sheetNum = minSheetNum; sheetNum < maxSheetNum; sheetNum++) {
            list.addAll(readContentFromWorkbook(workbook, sheetNum, 0, 1, titles, fields, clazz));
        }
        return list;
    }

    /**
     * 从指定的Excel输入流读取数据
     *
     * @param is     输入流
     * @param titles 标题列表
     * @param fields 字段列表
     * @param clazz  数据类型
     * @param <T>    数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readContentFromInputStream(InputStream is, List<String> titles, List<String> fields,
                                                         Class<T> clazz) throws Exception {
        Workbook workbook = null;
        try {
            // 创建工作簿
            workbook = WorkbookFactory.create(is);
            // 读取数据
            return readContentFromWorkbook(workbook, titles, fields, clazz);
        } finally {
            // 关闭流
            IOUtil.closeQuietly(workbook);
        }
    }

    /**
     * 从指定的Excel文件读取数据
     *
     * @param filePath 文件路径
     * @param titles   标题列表
     * @param fields   字段列表
     * @param clazz    数据类型
     * @param <T>      数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readContentFromFile(String filePath, List<String> titles, List<String> fields,
                                                  Class<T> clazz) throws Exception {
        InputStream is = null;
        try {
            // 读取文件输入流
            is = new FileInputStream(filePath);
            // 读取数据
            return readContentFromInputStream(is, titles, fields, clazz);
        } finally {
            // 关闭流
            IOUtil.closeQuietly(is);
        }
    }

}
