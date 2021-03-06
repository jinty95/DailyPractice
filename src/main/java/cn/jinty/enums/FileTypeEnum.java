package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 文件类型
 *
 * @author Jinty
 * @date 2022/3/17
 **/
@Getter
@AllArgsConstructor
public enum FileTypeEnum implements EnumInterface<String> {

    TXT("txt", "文本(txt)", "data:text/plain;base64,"),
    JPEG("jpeg", "图片(jpeg)", "data:image/jpeg;base64,"),
    PNG("png", "图片(png)", "data:image/png;base64,"),
    GIF("gif", "动图(gif)", "data:image/gif;base64,"),
    PDF("pdf", "PDF", "data:application/pdf;base64,"),
    XLS("xls", "Excel(2003及以前)", "data:application/vnd.ms-excel;base64,"),
    XLSX("xlsx", "Excel(2007及以后)", "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,"),
    DOC("doc", "Word(2003及以前)", "data:application/msword;base64,"),
    DOCX("docx", "Word(2007及以前)", "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,"),
    PPT("ppt", "PPT(2003及以前)", "data:application/vnd.ms-powerpoint;base64,"),
    PPTX("pptx", "PPT(2007及以后)", "data:application/vnd.openxmlformats-officedocument.presentationml.presentation;base64,"),
    ;

    // 编码 (文件后缀)
    private final String code;
    // 描述
    private final String desc;
    // Base64DataURL前缀
    private final String prefixOfBase64DataURL;

}
