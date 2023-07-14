package cn.jinty.util.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

/**
 * Excel样式 - 工具类
 *
 * @author Jinty
 * @date 2023/7/10
 **/
public final class ExcelFormatUtil {

    private ExcelFormatUtil() {
    }

    /**
     * 创建标题行的样式
     *
     * @param workbook 工作簿
     * @return 样式
     */
    public static CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置单元格背景色，设置单元格背景色以下两句必须同时设置
        // 设置填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置填充色
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
        // 创建一个字体对象
        Font font = workbook.createFont();
        // 设置字体的高度
        font.setFontHeightInPoints((short) 10);
        // 粗体显示
        font.setBold(true);
        // 设置字体
        style.setFont(font);
        // 设置单元格上、下、左、右的边框线
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 设置自动换行
        style.setWrapText(true);
        // 设置单元格文字显示居中(左右方向)
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格文字显示居中(上下方向)
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格内容为文本(方式1)
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        return style;
    }

    /**
     * 创建内容行的样式
     *
     * @param workbook 工作簿
     * @return 样式
     */
    public static CellStyle createContentStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置单元格上、下、左、右的边框线
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 设置自动换行
        style.setWrapText(true);
        // 设置单元格文字显示居中(左右方向)
        style.setAlignment(HorizontalAlignment.LEFT);
        // 设置单元格文字显示居中(上下方向)
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格内容为文本(方式2)
        style.setDataFormat((short) 49);
        return style;
    }

}
