package cn.jinty.util.excel;

import cn.jinty.util.io.IOUtil;
import cn.jinty.util.object.IntrospectUtil;
import cn.jinty.util.object.ObjectUtil;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.openxml4j.util.ZipSecureFile;
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
     * 读取一个单元格
     * 不管列是什么类型，都返回字符串
     * 导入模板的列都设置为文本，导入时内容都读取为字符串，在程序内部再解析转换为具体类型，兼容性比较好，不容易出问题
     *
     * @param row     行
     * @param cellNum 列号
     * @return 单元格的值
     */
    public static String readFromCell(Row row, int cellNum) {
        Cell cell = row.getCell(cellNum);
        DataFormatter dataFormatter = new DataFormatter();
        dataFormatter.setUseCachedValuesForFormulaCells(true);
        return cell == null ? null : dataFormatter.formatCellValue(cell);
    }

    /**
     * 读取一行
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
     * 读取一行，转为对应的数据对象
     *
     * @param row            行
     * @param titleToCellNum 标题映射列号
     * @param titles         标题列表
     * @param fields         字段列表
     * @param clazz          数据类型
     * @param <T>            数据类型
     * @return 数据对象
     * @throws Exception 异常
     */
    public static <T> T readFromRow(Row row, Map<String, Integer> titleToCellNum, List<String> titles,
                                    List<String> fields, Class<T> clazz) throws Exception {
        // 获取字段对应描述器
        Map<String, PropertyDescriptor> fieldToDescriptor = IntrospectUtil.getPropertyDescriptorMap(clazz);
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
        return obj;
    }

    /**
     * 读取标题行，转为标题和列号的映射
     *
     * @param row    行
     * @param titles 标题列表
     * @return 标题 -> 列号
     */
    public static Map<String, Integer> readTitleFromRow(Row row, List<String> titles) {
        Map<String, Integer> titleToCellNum = new HashMap<>();
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
    public static <T> List<T> readFromSheet(Sheet sheet, int titleRowNum, int contentRowNum, List<String> titles,
                                            List<String> fields, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        // 标题对应列号
        Map<String, Integer> titleToCellNum = new HashMap<>();
        int rowNum = 0;
        for (Row row : sheet) {
            if (rowNum == titleRowNum) {
                // 标题行
                titleToCellNum = readTitleFromRow(row, titles);
            } else if (rowNum >= contentRowNum) {
                // 内容行
                list.add(readFromRow(row, titleToCellNum, titles, fields, clazz));
            }
            rowNum++;
        }
        return list;
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
    public static <T> List<T> readFromWorkbook(Workbook workbook, List<String> titles, List<String> fields,
                                               Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        // 表单页号范围[minSheetNum,maxSheetNum)
        int minSheetNum = 0;
        int maxSheetNum = workbook.getNumberOfSheets();
        for (int sheetNum = minSheetNum; sheetNum < maxSheetNum; sheetNum++) {
            list.addAll(readFromSheet(workbook.getSheetAt(sheetNum), 0, 1, titles, fields, clazz));
        }
        return list;
    }

    /**
     * 从指定的Excel输入流读取数据
     *
     * @param is       输入流
     * @param titles   标题列表
     * @param fields   字段列表
     * @param clazz    数据类型
     * @param byStream 是否通过流式读取
     * @param <T>      数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readFromInputStream(InputStream is, List<String> titles, List<String> fields,
                                                  Class<T> clazz, boolean byStream) throws Exception {
        Workbook workbook = null;
        try {
            // 创建工作簿
            if (byStream) {
                workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(4096).open(is);
            } else {
                workbook = WorkbookFactory.create(is);
            }
            // 读取数据
            return readFromWorkbook(workbook, titles, fields, clazz);
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
     * @param byStream 是否通过流式读取
     * @param <T>      数据类型
     * @return 数据列表
     * @throws Exception 异常
     */
    public static <T> List<T> readFromFile(String filePath, List<String> titles, List<String> fields,
                                           Class<T> clazz, boolean byStream) throws Exception {
        InputStream is = null;
        try {
            // 读取文件输入流
            is = new FileInputStream(filePath);
            // 读取数据
            return readFromInputStream(is, titles, fields, clazz, byStream);
        } finally {
            // 关闭流
            IOUtil.closeQuietly(is);
        }
    }

    /*
     * 流式读取Excel
     * 原理：滑动窗口，限定读入内存中的数据大小，将正在解析的数据读到内存缓冲区中，一次操作只加载一定量的数据。
     * 优点：避免将整个表格直接加载到内存，占用大量内存，从而导致内存溢出。
     * 局限：因为内存中仅加载部分数据，故牺牲了随机访问的能力，仅能通过顺序遍历访问Sheet的所有Row，不能通过索引精准访问某个Row。
     * 注意：for(Row row : sheet)只能遍历一次，第一次读取中断时，第二次会从上次中断位置继续读取，读取结束时，再次调用得到空。
     *
     * 普通读取与流式读取的使用区别：只在创建Workbook有区别，其它代码是一样的。
     */

    /**
     * 从表单页读取数据，将表格数据作为二维数组输出
     *
     * @param sheet 表单页
     * @return 二维数组
     */
    public static List<List<String>> simpleReadFromSheet(Sheet sheet) {
        List<List<String>> data = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            // 有效列数为[minCol,maxCol-1]
            int minCol = row.getFirstCellNum();
            int maxCol = row.getLastCellNum();
            for (int j = minCol; j < maxCol; j++) {
                String cellVal = readFromCell(row, j);
                rowData.add(cellVal);
            }
            data.add(rowData);
        }
        return data;
    }

    /**
     * 从指定的Excel文件读取数据，将表格作为二维数组输出
     *
     * @param filePath 文件路径
     * @param byStream 是否通过流式读取
     * @return 二维数组
     * @throws Exception 异常
     */
    public static List<List<String>> simpleReadFromFile(String filePath, boolean byStream) throws Exception {
        InputStream is = null;
        Workbook wb = null;
        try {
            // 解决"Zip bomb detected"报错
            ZipSecureFile.setMinInflateRatio(0);
            // 读取文件
            is = new FileInputStream(filePath);
            // 根据输入流创建工作簿对象
            if (byStream) {
                wb = StreamingReader.builder().rowCacheSize(1000).bufferSize(4096).open(is);
            } else {
                wb = WorkbookFactory.create(is);
            }
            // 读取第一个表单页
            Sheet sheet = wb.getSheetAt(0);
            // 读取所有的行列数据
            return simpleReadFromSheet(sheet);
        } finally {
            // 关闭流
            IOUtil.closeQuietly(wb);
            IOUtil.closeQuietly(is);
        }
    }

}
