package test.cn.jinty.util.excel;

import cn.jinty.util.excel.ExcelImportUtil;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

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
    public void testRead() {
        String filePath = "D:\\temp\\BigDataExcelExportTest1_500.xlsx";
        System.out.println("导入文件：" + filePath);
        try {
            // 解决"Zip bomb detected"报错
            ZipSecureFile.setMinInflateRatio(0);
            List<Person> data = ExcelImportUtil.readFromFile(filePath, getTitles(), getFields(), Person.class);
            System.out.println("数据总行数：" + data.size());
            System.out.println("数据详情：");
            data.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStreamRead() {
        String filePath = "D:\\temp\\BigDataExcelExportTest1_500.xlsx";
        System.out.println("导入文件：" + filePath);
        try {
            // 解决"Zip bomb detected"报错
            ZipSecureFile.setMinInflateRatio(0);
            List<Person> data = ExcelImportUtil.streamReadFromFile(filePath, getTitles(), getFields(), Person.class);
            System.out.println("数据总行数：" + data.size());
            System.out.println("数据详情：");
            data.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 有两张相同的图片时，只能读取到其中的一张
    @Test
    public void testGetAllPicture() {
        String filePath = "D:\\temp\\ExcelImportWithPic.xlsx";
        System.out.println("导入文件：" + filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            List<XSSFPictureData> picturesData = ((XSSFWorkbook) workbook).getAllPictures();
            for (XSSFPictureData pictureData : picturesData) {
                byte[] bytes = pictureData.getData();
                String picFileName = "D:\\temp\\image_" + UUID.randomUUID().toString() + "." + pictureData.suggestFileExtension();
                FileOutputStream out = new FileOutputStream(picFileName);
                out.write(bytes);
                out.close();
                System.out.println("获取到图片，存储到：" + picFileName);
            }
            workbook.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取图片及其所属的行列（以图片左上角所处的单元格为准）
    // 重复图片也能读取到
    @Test
    public void testGetPictureAndLocation() {
        String filePath = "D:\\temp\\ExcelImportWithPic.xlsx";
        System.out.println("导入文件：" + filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            List<POIXMLDocumentPart> list = sheet.getRelations();
            for (POIXMLDocumentPart part : list) {
                if (part instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) part;
                    List<XSSFShape> shapes = drawing.getShapes();
                    for (XSSFShape shape : shapes) {
                        XSSFPicture picture = (XSSFPicture) shape;
                        XSSFClientAnchor anchor = picture.getPreferredSize();
                        CTMarker marker = anchor.getFrom();
                        String key = marker.getRow() + "-" + marker.getCol();
                        byte[] bytes = picture.getPictureData().getData();
                        String picFileName = "D:\\temp\\picture\\image_" + key + "_" + UUID.randomUUID().toString() + "." + picture.getPictureData().suggestFileExtension();
                        FileOutputStream out = new FileOutputStream(picFileName);
                        out.write(bytes);
                        out.close();
                        System.out.printf("获取到图片，位置为：[%s]，存储到：[%s]%n", key, picFileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
