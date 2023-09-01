package cn.jinty.enums;

import cn.jinty.enums.common.EnumFactory;
import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 枚举 - 文件类型
 *
 * @author Jinty
 * @date 2022/3/17
 **/
@Getter
@AllArgsConstructor
public enum FileTypeEnum implements EnumInterface<String> {

    // 应用
    DOC("doc", "Word文档(2003)", "application/msword"),
    DOCX("docx", "Word文档(2007)", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    XLS("xls", "Excel表格(2003)", "application/vnd.ms-excel"),
    XLSX("xlsx", "Excel表格(2007)", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PPT("ppt", "PPT幻灯片(2003)", "application/vnd.ms-powerpoint"),
    PPTX("pptx", "PPT幻灯片(2007)", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    PPS("pps", "PPS幻灯片(2003)", "application/vnd.ms-powerpoint"),
    PPSX("ppsx", "PPS幻灯片(2007)", "application/vnd.openxmlformats-officedocument.presentationml.slideshow"),
    PDF("pdf", "可携带文档", "application/pdf"),
    RTF("rtf", "富文本格式", "application/rtf"),
    RAR("rar", "RAR压缩包", "application/x-rar-compressed"),
    ZIP("zip", "ZIP压缩包", "application/x-zip-compressed"),
    ZIP_7("7z", "7Z压缩包", "application/x-7z-compressed"),
    Z("z", "Z压缩包", "application/x-compress"),
    TAR("tar", "TAR压缩包", "application/x-tar"),
    GZ("gz", "GZ压缩包", "application/x-gzip"),
    TGZ("tgz", "TGZ压缩包", "application/x-compressed"),
    ISO("iso", "光盘镜像文件", "application/x-iso9660-image"),
    SQL("sql", "数据库脚本", "application/x-sql"),
    JSON("json", "JSON", "application/json"),

    // 邮件
    MHT("mht", "网页归档", "message/rfc822"),
    MHTML("mhtml", "网页归档", "message/rfc822"),

    // 视频
    WMV("wmv", "微软媒体视频", "video/x-ms-wmv"),
    MP4("mp4", "MP4视频", "video/mp4"),
    AVI("avi", "AVI视频", "video/x-msvideo"),
    MKV("mkv", "MKV视频", "video/x-matroska"),
    MOV("mov", "MOV视频", "video/quicktime"),
    FLV("flv", "FLASH视频", "video/x-flv"),
    MPE("mpe", "MPEG视频", "video/mpeg"),
    MPEG("mpeg", "MPEG视频", "video/mpeg"),
    MPG("mpg", "MPEG视频", "video/mpeg"),
    WEBM("webm", "WEBM视频", "video/webm"),

    // 音频
    WMA("wma", "微软媒体音频", "audio/x-ms-wma"),
    WAV("wav", "波形声音文件", "audio/wav"),
    FLAC("flac", "无损音频", "audio/flac"),
    M4A("m4a", "MP4音频", "audio/mp4"),
    MP3("mp3", "MP3音频", "audio/mpeg"),
    MP2("mp2", "MP2音频", "audio/mpeg"),
    MP1("mp1", "MP1音频", "audio/mpeg"),
    MID("mid", "MIDI音频", "audio/midi"),
    MIDI("midi", "MIDI音频", "audio/midi"),
    RMI("rmi", "RMI音频", "audio/midi"),

    // 图片
    BMP("bmp", "位图", "image/bmp"),
    WBMP("wbmp", "无线位图", "image/vnd.wap.wbmp"),
    GIF("gif", "动图", "image/gif"),
    PNG("png", "PNG图片", "image/png"),
    JPE("jpe", "JPEG图片", "image/jpeg"),
    JPEG("jpeg", "JPEG图片", "image/jpeg"),
    JPG("jpg", "JPEG图片", "image/jpeg"),
    ICO("ico", "ICON图标", "image/x-icon"),
    TIF("tif", "标签图像", "image/tiff"),
    TIFF("tiff", "标签图像", "image/tiff"),

    // 文本
    TXT("txt", "文本", "text/plain"),
    MD("md", "MD", "text/markdown"),
    LOG("log", "LOG", "text/plain"),
    XML("xml", "XML", "text/xml"),
    HTM("htm", "HTML", "text/html"),
    HTML("html", "HTML", "text/html"),
    CSS("css", "CSS", "text/css"),
    JS("js", "JS", "text/javascript"),
    CSV("csv", "CSV", "text/csv"),

    JAVA("java", "JAVA", "text/plain"),
    ;

    // 编码 (文件后缀)
    private final String code;
    // 描述
    private final String desc;
    // MIME类型
    private final String mimeType;

    /**
     * 是否为文本类型
     *
     * @param fileType 文件类型
     * @return 是否
     */
    public static boolean isText(FileTypeEnum fileType) {
        if (fileType == null) {
            return false;
        }
        return fileType.getMimeType().startsWith("text") || fileType == SQL || fileType == JSON;
    }

    /**
     * 是否为文本类型
     *
     * @param fileType 文件类型
     * @return 是否
     */
    public static boolean isText(String fileType) {
        return isText((FileTypeEnum) EnumFactory.getByTypeAndCode(FileTypeEnum.class.getSimpleName(), fileType));
    }

}
