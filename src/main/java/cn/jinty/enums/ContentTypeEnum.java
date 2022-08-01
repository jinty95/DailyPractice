package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HTTP - Content-Type - 枚举
 *
 * @author Jinty
 * @date 2022/7/6
 **/
@Getter
@AllArgsConstructor
public enum ContentTypeEnum implements EnumInterface<String> {

    // 常用的请求体格式
    URL_ENCODED("application/x-www-form-urlencoded", "URL编码"),
    FORM_DATA("multipart/form-data", "FORM表单"),
    JSON("application/json;charset=utf-8", "JSON"),
    // 常见的响应体格式
    XML("application/xml", "XML"),
    TEXT("text/plain", "TEXT"),
    HTML("text/html", "HTML"),
    OCTET_STREAM("application/octet-stream", "二进制数据"),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
