package cn.jinty.util.excel;

import cn.jinty.util.PageUtil;
import cn.jinty.util.io.IOUtil;
import cn.jinty.util.object.IntrospectUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Excel导出 - 工具类
 *
 * @author Jinty
 * @date 2023/7/13
 **/
public final class ExcelExportUtil {

    private ExcelExportUtil() {
    }

    // .xls文件单Sheet最大行数 (2^16)
    public static final int SHEET_MAX_ROW_NUMBER_OF_XLS = 65536;
    // .xlsx文件单Sheet最大行数 (2^20)
    public static final int SHEET_MAX_ROW_NUMBER_OF_XLSX = 1048576;

    /**
     * 将标题行写入表单页
     *
     * @param sheet     表单页
     * @param rowNum    行号
     * @param cellStyle 单元格样式
     * @param titles    标题列表
     * @return 下一个行号
     */
    public static int writeTitleToSheet(Sheet sheet, int rowNum, CellStyle cellStyle, List<String> titles) {
        Row row = sheet.createRow(rowNum++);
        for (int i = 0; i < titles.size(); i++) {
            // 创建单元格并赋值
            Cell cell = row.createCell(i);
            if (cellStyle != null) {
                cell.setCellStyle(cellStyle);
            }
            cell.setCellValue(titles.get(i));
        }
        return rowNum;
    }

    /**
     * 将数据行写入表单页
     *
     * @param sheet     表单页
     * @param rowNum    行号
     * @param cellStyle 单元格样式
     * @param fields    字段列表
     * @param contents  数据列表
     * @param <T>       数据类型
     * @return 下一个行号
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    public static <T> int writeContentToSheet(Sheet sheet, int rowNum, CellStyle cellStyle,
                                              List<String> fields, List<T> contents) throws Exception {
        // 获取数据类型
        Class<T> clazz = (Class<T>) contents.get(0).getClass();
        // 获取数据类型对应的字段及其Getter
        Map<String, Method> getterMap = IntrospectUtil.getPropertyGetterMap(clazz);
        for (T content : contents) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < fields.size(); i++) {
                // 获取字段对应的值
                Object value = "";
                Method getter = getterMap.get(fields.get(i));
                if (getter != null) {
                    value = getter.invoke(content);
                }
                // 创建单元格并赋值
                Cell cell = row.createCell(i);
                if (cellStyle != null) {
                    cell.setCellStyle(cellStyle);
                }
                cell.setCellValue(String.valueOf(value));
            }
        }
        return rowNum;
    }

    /**
     * 将数据写入工作簿
     * (注意：标题列表和字段列表必须一一对应，不能错位，否则导出错位)
     *
     * @param workbook          工作簿
     * @param sheetMaxRowNumber 表单页最大行数
     * @param titles            标题列表
     * @param fields            字段列表
     * @param contents          数据列表
     * @param <T>               数据类型
     * @throws Exception 异常
     */
    public static <T> void writeToWorkbook(Workbook workbook, int sheetMaxRowNumber, List<String> titles,
                                           List<String> fields, List<T> contents) throws Exception {
        // 计算表单页总数
        int lastSheetRowNumber = contents.size() % sheetMaxRowNumber;
        int sheetCount = contents.size() / sheetMaxRowNumber;
        if (lastSheetRowNumber != 0) {
            sheetCount++;
        }
        // 创建单元格样式
        CellStyle titleStyle = ExcelFormatUtil.createTitleStyle(workbook);
        CellStyle contentStyle = ExcelFormatUtil.createContentStyle(workbook);
        for (int i = 0; i < sheetCount; i++) {
            // 创建表单页
            Sheet sheet = workbook.createSheet("Sheet" + i);
            // 写入标题行
            int rowNum = 0;
            rowNum = ExcelExportUtil.writeTitleToSheet(sheet, rowNum, titleStyle, titles);
            // 写入数据行
            rowNum = ExcelExportUtil.writeContentToSheet(sheet, rowNum, contentStyle, fields,
                    PageUtil.getPage(contents, i + 1, sheetMaxRowNumber));
        }
    }

    /**
     * 将数据写入工作簿，输出到输出流
     *
     * @param os                输出流
     * @param sheetMaxRowNumber 表单页最大行数
     * @param titles            标题列表
     * @param fields            字段列表
     * @param contents          数据列表
     * @param <T>               数据类型
     * @throws Exception 异常
     */
    public static <T> void writeToOutputStream(OutputStream os, int sheetMaxRowNumber, List<String> titles,
                                               List<String> fields, List<T> contents) throws Exception {
        SXSSFWorkbook workbook = null;
        try {
            // 使用SXSSFWorkbook，默认超过100行就写到临时文件，不会一直占用内存，避免内存溢出
            // 这里设置每200行写一次临时文件
            workbook = new SXSSFWorkbook(200);
            // 设置临时文件不压缩，这样速度快一点
            workbook.setCompressTempFiles(false);
            // 将数据写入工作簿
            writeToWorkbook(workbook, sheetMaxRowNumber, titles, fields, contents);
            // 输出到输出流
            workbook.write(os);
        } finally {
            // 删除临时文件
            if (workbook != null) {
                workbook.dispose();
            }
            // 关闭流
            IOUtil.closeQuietly(workbook);
        }
    }

    /**
     * 将数据写入工作簿，输出为Excel文件
     *
     * @param filePath          文件路径
     * @param sheetMaxRowNumber 表单页最大行数
     * @param titles            标题列表
     * @param fields            字段列表
     * @param contents          数据列表
     * @param <T>               数据类型
     * @throws Exception 异常
     */
    public static <T> void writeToFile(String filePath, int sheetMaxRowNumber, List<String> titles,
                                       List<String> fields, List<T> contents) throws Exception {
        FileOutputStream fos = null;
        try {
            // 构造输出流
            fos = new FileOutputStream(filePath);
            // 将数据写入工作簿，输出到输出流
            writeToOutputStream(fos, sheetMaxRowNumber, titles, fields, contents);
        } finally {
            // 关闭流
            IOUtil.closeQuietly(fos);
        }
    }

}
